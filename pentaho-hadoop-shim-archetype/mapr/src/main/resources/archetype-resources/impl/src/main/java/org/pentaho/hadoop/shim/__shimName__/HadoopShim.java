#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*! ******************************************************************************
 *
 * Pentaho
 *
 * Copyright (C) 2024 by Hitachi Vantara, LLC : http://www.pentaho.com
 *
 * Use of this software is governed by the Business Source License included
 * in the LICENSE.TXT file.
 *
 * Change Date: 2029-07-20
 ******************************************************************************/


package ${groupId}.hadoop.shim.${shimName};

import ${groupId}.hadoop.shim.HadoopConfiguration;
import ${groupId}.hadoop.shim.HadoopConfigurationFileSystemManager;
import ${groupId}.hadoop.shim.api.Configuration;
import ${groupId}.hadoop.shim.common.CommonHadoopShim;
import ${groupId}.hadoop.shim.common.ShimUtils;
import ${groupId}.hdfs.vfs.MapRFileProvider;

import java.io.IOException;
import java.util.List;

public class HadoopShim extends CommonHadoopShim {
  protected static final String SUPER_USER = "authentication.superuser.provider";
  protected static final String PROVIDER_LIST = "authentication.provider.list";
  protected static final String DEFAULT_CLUSTER = "/";
  protected static final String MFS_SCHEME = "maprfs://";
  protected static final String[] EMPTY_CONNECTION_INFO = new String[ 2 ];

  static {
    JDBC_DRIVER_MAP.put( "hive2", org.apache.hive.jdbc.HiveDriver.class );
  }

  @Override
  public String[] getNamenodeConnectionInfo( Configuration c ) {
    return EMPTY_CONNECTION_INFO;
  }

  @Override
  public String[] getJobtrackerConnectionInfo( Configuration c ) {
    return EMPTY_CONNECTION_INFO;
  }

  @Override
  public void configureConnectionInformation( String namenodeHost, String namenodePort, String jobtrackerHost,
                                              String jobtrackerPort, Configuration conf, List<String> logMessages )
          throws Exception {
    if ( namenodeHost == null || namenodeHost.length() == 0 ) {
      namenodeHost = DEFAULT_CLUSTER;
      logMessages.add( "Using MapR default cluster for filesystem" );
    } else if ( namenodePort == null || namenodePort.trim().length() == 0 ) {
      logMessages.add( "Using MapR CLDB named cluster: " + namenodeHost
              + " for filesystem" );
      namenodeHost = "/mapr/" + namenodeHost;
    } else {
      logMessages.add( "Using filesystem at " + namenodeHost + ":" + namenodePort );
      namenodeHost = namenodeHost + ":" + namenodePort;
    }

    if ( jobtrackerHost == null || jobtrackerHost.trim().length() == 0 ) {
      jobtrackerHost = DEFAULT_CLUSTER;
      logMessages.add( "Using MapR default cluster for job tracker" );
    } else if ( jobtrackerPort == null || jobtrackerPort.trim().length() == 0 ) {
      logMessages.add( "Using MapR CLDB named cluster: " + jobtrackerHost
              + " for job tracker" );
      jobtrackerHost = "/mapr/" + jobtrackerHost;
    } else {
      logMessages.add( "Using job tracker at " + jobtrackerHost + ":" + jobtrackerPort );
      jobtrackerHost = jobtrackerHost + ":" + jobtrackerPort;
    }

    String fsDefaultName = MFS_SCHEME + namenodeHost;
    String jobTracker = MFS_SCHEME + jobtrackerHost;
    //conf.set( "fs.default.name", fsDefaultName );
    //conf.set( "mapred.job.tracker", jobTracker );
    conf.set( "fs.maprfs.impl", MapRFileProvider.FS_MAPR_IMPL );
  }

  @Override
  public ${groupId}.hadoop.shim.api.Configuration createConfiguration() {
    ${groupId}.hadoop.shim.api.Configuration result;
    // Set the context class loader when instantiating the configuration
    // since org.apache.hadoop.conf.Configuration uses it to load resources
    ClassLoader cl = Thread.currentThread().getContextClassLoader();
    Thread.currentThread().setContextClassLoader( getClass().getClassLoader() );
    try {
      result = new ${groupId}.hadoop.shim.${shimName}.ConfigurationProxyV2();
    } catch ( IOException e ) {
      throw new RuntimeException( "Unable to create configuration for new mapreduce api: ", e );
    } finally {
      Thread.currentThread().setContextClassLoader( cl );
    }
    ShimUtils.asConfiguration( result ).addResource( "hbase-site.xml" );
    ShimUtils.asConfiguration( result ).set( "sqoop.hbase.security.token.skip", "true" );

    return result;
  }

  @Override
  public void onLoad( HadoopConfiguration config, HadoopConfigurationFileSystemManager fsm ) throws Exception {
    validateHadoopHomeWithWinutils();
    fsm.addProvider( config, MapRFileProvider.SCHEME, config.getIdentifier(), new MapRFileProvider() );
    setDistributedCacheUtil( new MapR5DistributedCacheUtilImpl( config ) );
  }
}

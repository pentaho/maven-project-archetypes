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

import ${groupId}.di.core.exception.KettlePluginException;
import ${groupId}.hadoop.shim.HadoopConfiguration;
import ${groupId}.hadoop.shim.HadoopConfigurationFileSystemManager;
import ${groupId}.hadoop.shim.common.DriverProxyInvocationChain;
import ${groupId}.hadoop.shim.common.HadoopShimImpl;
import ${groupId}.hadoop.shim.${shimName}.invocationhandler.HDIDriverInvocationHandler;

import java.sql.Driver;
import java.util.Properties;

public class HadoopShim extends HadoopShimImpl {
  @Override
  public void onLoad( HadoopConfiguration config, HadoopConfigurationFileSystemManager fsm ) throws Exception {
    registerExtraDatabaseTypes( config.getConfigProperties() );
    super.onLoad( config, fsm );
  }

  protected void registerExtraDatabaseTypes( Properties configuration ) throws KettlePluginException {
    String sparkSqlSimbaDriverName =
      configuration.getProperty( "sparksql.simba.driver", "com.simba.spark.jdbc41.Driver" );
    JDBC_POSSIBLE_DRIVER_MAP.put( "SparkSqlSimba", sparkSqlSimbaDriverName );
  }

  @Override
  public Driver getJdbcDriver( String driverType ) {
    try {
      Driver newInstance = null;
      Class<? extends Driver> clazz = JDBC_DRIVER_MAP.get( driverType );
      if ( clazz != null ) {
        newInstance = clazz.newInstance();
        return DriverProxyInvocationChain.getProxy( Driver.class, newInstance, HDIDriverInvocationHandler.class );
      } else {
        clazz = tryToLoadDriver( JDBC_POSSIBLE_DRIVER_MAP.get( driverType ) );
        if ( clazz != null ) {
          newInstance = clazz.newInstance();
          if ( driverType.equals( "Impala" ) ) {
            return DriverProxyInvocationChain.getProxy( Driver.class, newInstance, HDIDriverInvocationHandler.class );
          }
          return newInstance;
        }
        return null;
      }

    } catch ( Exception ex ) {
      throw new RuntimeException( "Unable to load JDBC driver of type: " + driverType, ex );
    }
  }
}

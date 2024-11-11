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
 * Change Date: 2028-08-13
 ******************************************************************************/


package ${groupId}.hadoop.shim.${shimName};

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import ${groupId}.hadoop.shim.HadoopConfiguration;
import ${groupId}.hadoop.shim.common.DistributedCacheUtilImpl;

public class MapR5DistributedCacheUtilImpl extends DistributedCacheUtilImpl {

  public MapR5DistributedCacheUtilImpl( HadoopConfiguration configuration ) {
    super( configuration );
  }

  /**
   * Add an file path to the current set of classpath entries. It adds the file to cache as well.
   * <p/>
   * This is copied from Hadoop 0.20.2 o.a.h.filecache.DistributedCache so we can inject the correct path separator for
   * the environment the cluster is executing in. See {@link ${symbol_pound}getClusterPathSeparator()}.
   *
   * @param file Path of the file to be added
   * @param conf Configuration that contains the classpath setting
   */
  @Override
  public void addFileToClassPath( Path file, Configuration conf )
          throws IOException {

    String classpath = conf.get( "mapred.job.classpath.files" );
    conf.set( "mapred.job.classpath.files", classpath == null ? file.toString()
            : classpath + getClusterPathSeparator() + file.toString() );
    FileSystem fs = FileSystem.get( conf );
    URI uri = fs.makeQualified( file ).toUri();

    DistributedCache.addCacheFile( uri, conf );
  }

  public String getClusterPathSeparator() {
    // Use a comma rather than an OS-specific separator (see https://issues.apache.org/jira/browse/HADOOP-4864)
    return System.getProperty( "hadoop.cluster.path.separator", "," );
  }
}

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
#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*******************************************************************************
 *
 * Pentaho Big Data
 *
 * Copyright (C) 2002-2018 by Hitachi Vantara : http://www.pentaho.com
 *
 *******************************************************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ******************************************************************************/

package ${groupId}.hadoop.shim.${shimName};

import ${groupId}.hadoop.shim.HadoopConfiguration;
import ${groupId}.hadoop.shim.HadoopConfigurationFileSystemManager;
import ${groupId}.hadoop.shim.common.FileSystemProxyV2;
import ${groupId}.hadoop.shim.common.HadoopShimImpl;
import ${groupId}.hadoop.shim.common.ShimUtils;
import ${groupId}.hdfs.vfs.HDFSFileProvider;

import java.io.IOException;

public class HadoopShim extends HadoopShimImpl {

  static {
    JDBC_DRIVER_MAP.put( "hive2", org.apache.hive.jdbc.HiveDriver.class );
  }

  @Override
  public void onLoad( HadoopConfiguration config, HadoopConfigurationFileSystemManager fsm ) throws Exception {
    super.onLoad( config, fsm );
    if ( !fsm.hasProvider( "s3n" ) ) {
      fsm.addProvider( config, "s3n", config.getIdentifier(), new HDFSFileProvider() );
    }
  }

  @Override
  public ${groupId}.hadoop.shim.api.fs.FileSystem getFileSystem(
    ${groupId}.hadoop.shim.api.Configuration conf ) throws IOException {
    // Set the context class loader when instantiating the configuration
    // since org.apache.hadoop.conf.Configuration uses it to load resources
    ClassLoader cl = Thread.currentThread().getContextClassLoader();
    Thread.currentThread().setContextClassLoader( getClass().getClassLoader() );
    try {
      return new FileSystemProxyV2( ShimUtils.asConfiguration( conf ) );
    } finally {
      Thread.currentThread().setContextClassLoader( cl );
    }
  }
}

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

import ${groupId}.di.core.database.DatabaseInterface;
import ${groupId}.di.core.exception.KettlePluginException;
import ${groupId}.di.core.plugins.DatabasePluginType;
import ${groupId}.di.core.plugins.Plugin;
import ${groupId}.di.core.plugins.PluginInterface;
import ${groupId}.di.core.plugins.PluginRegistry;
import ${groupId}.hadoop.shim.HadoopConfiguration;
import ${groupId}.hadoop.shim.HadoopConfigurationFileSystemManager;
import ${groupId}.hadoop.shim.common.HadoopShimImpl;

import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class HadoopShim extends HadoopShimImpl {

  @Override
  public void onLoad( HadoopConfiguration config, HadoopConfigurationFileSystemManager fsm ) throws Exception {
    registerExtraDatabaseTypes( config.getConfigProperties() );
    super.onLoad( config, fsm );
  }

  protected void registerExtraDatabaseTypes( Properties configuration ) throws KettlePluginException {
    /*String hiveSimbaDriverName = configuration.getProperty( "hive2.simba.driver", "com.simba.hive.jdbc41.HS2Driver" );
      JDBC_POSSIBLE_DRIVER_MAP.put( "hive2Simba", hiveSimbaDriverName );*/

    String impalaSimbaDriverName =
      configuration.getProperty( "impala.simba.driver", "com.cloudera.impala.jdbc41.Driver" );
    JDBC_POSSIBLE_DRIVER_MAP.put( "ImpalaSimba", impalaSimbaDriverName );
  }

  protected void registerExtraDatabaseType( String id, String description, String mainClass )
    throws KettlePluginException {
    Map<Class<?>, String> classMap = new HashMap<Class<?>, String>();
    classMap.put( DatabaseInterface.class, mainClass );
    PluginInterface dbPlugin =
      new Plugin(
        new String[] { id }, DatabasePluginType.class, DatabaseInterface.class, "", description, description, null,
        false,
        false, classMap, new ArrayList<String>(), null, null, null, null, null );
    PluginRegistry.getInstance().addClassLoader(
      (URLClassLoader) Thread.currentThread().getContextClassLoader().getParent(), dbPlugin );
    PluginRegistry.getInstance().registerPlugin( DatabasePluginType.class, dbPlugin );
  }

}

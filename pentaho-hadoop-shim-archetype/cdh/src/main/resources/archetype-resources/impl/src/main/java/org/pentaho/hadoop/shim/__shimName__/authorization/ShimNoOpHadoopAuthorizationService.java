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

package ${groupId}.hadoop.shim.${shimName}.authorization;


import ${groupId}.hadoop.shim.${shimName}.HadoopShim;
import ${groupId}.hadoop.shim.common.CommonHadoopShim;
import ${groupId}.hadoop.shim.common.authorization.NoOpHadoopAuthorizationService;

public class ShimNoOpHadoopAuthorizationService extends NoOpHadoopAuthorizationService {

  @Override protected CommonHadoopShim getHadoopShim() {
    return new HadoopShim();
  }
}

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


package ${groupId}.hadoop.shim.${shimName}.authorization;


import ${groupId}.hadoop.shim.${shimName}.HadoopShim;
import ${groupId}.hadoop.shim.common.CommonHadoopShim;
import ${groupId}.hadoop.shim.common.authorization.NoOpHadoopAuthorizationService;

public class ShimNoOpHadoopAuthorizationService extends NoOpHadoopAuthorizationService {

  @Override protected CommonHadoopShim getHadoopShim() {
    return new HadoopShim();
  }
}

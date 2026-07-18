#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*! ******************************************************************************
 *
 * Pentaho
 *
 * Copyright (C) 2024 - 2026 by Pentaho Canada Inc. : http://www.pentaho.com
 *
 * Use of this software is governed by the Business Source License included
 * in the LICENSE.TXT file.
 *
 * Change Date: 2030-06-15
 ******************************************************************************/



package ${groupId}.hadoop.shim.${shimName}.authorization;


import ${groupId}.hadoop.shim.common.CommonHadoopShim;
import ${groupId}.hadoop.shim.common.authorization.NoOpHadoopAuthorizationService;
import ${groupId}.hadoop.shim.${shimName}.HadoopShim;

public class ShimNoOpHadoopAuthorizationService extends NoOpHadoopAuthorizationService {

  @Override protected CommonHadoopShim getHadoopShim() {
    return new HadoopShim() {
      @Override protected String getDefaultJobtrackerPort() {
        return "50300";
      }
    };
  }
}

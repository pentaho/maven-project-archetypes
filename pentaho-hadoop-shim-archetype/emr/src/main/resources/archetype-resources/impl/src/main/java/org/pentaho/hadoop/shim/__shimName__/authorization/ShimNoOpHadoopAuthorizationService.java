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


package ${groupId}.hadoop.shim.${shimName}.authorization;

import ${groupId}.hadoop.shim.common.CommonHadoopShim;
import ${groupId}.hadoop.shim.common.CommonPigShim;
import ${groupId}.hadoop.shim.common.PigShimImpl;
import ${groupId}.hadoop.shim.common.authorization.NoOpHadoopAuthorizationService;
import ${groupId}.hadoop.shim.${shimName}.HadoopShim;


public class ShimNoOpHadoopAuthorizationService extends NoOpHadoopAuthorizationService {

  @Override
  protected CommonHadoopShim getHadoopShim() {
    return new HadoopShim();
  }

  @Override
  protected CommonPigShim getPigShim() {
    return new PigShimImpl() {
      @Override
      public boolean isLocalExecutionSupported() {
        return false;
      }
    };
  }
}

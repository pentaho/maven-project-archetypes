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
#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.StepDataInterface;
import org.pentaho.di.trans.step.StepInterface;
import org.pentaho.di.trans.step.StepMeta;
import org.pentaho.di.trans.step.StepMetaInterface;
import org.pentaho.di.trans.streaming.common.BaseStreamStep;
import org.pentaho.di.trans.streaming.common.FixedTimeStreamWindow;


/**
 * An example step plugin for purposes of demonstrating a strategy for handling streams of data.
 */
public class ${plugin_class_name} extends BaseStreamStep implements StepInterface {

  private static Class<?> PKG = ${plugin_class_name}Meta.class; // for i18n purposes, needed by Translator2!!   ${symbol_dollar}NON-NLS-1${symbol_dollar}

  private ${plugin_class_name}Meta streamMeta;

  public ${plugin_class_name}( StepMeta stepMeta, StepDataInterface stepDataInterface, int copyNr, TransMeta transMeta,
                     Trans trans ) {
    super( stepMeta, stepDataInterface, copyNr, transMeta, trans );
  }

  public boolean init( StepMetaInterface stepMetaInterface, StepDataInterface stepDataInterface ) {
    super.init( stepMetaInterface, stepDataInterface );

    streamMeta = (${plugin_class_name}Meta) stepMetaInterface;

    window = new FixedTimeStreamWindow<>( subtransExecutor, streamMeta.getRowMeta( getStepname(), this ), getDuration(), getBatchSize() );

    source = new ${plugin_class_name}Source( streamMeta, this );
    return true;
  }
}

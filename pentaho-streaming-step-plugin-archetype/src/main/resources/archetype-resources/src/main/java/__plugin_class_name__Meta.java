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
package ${package};

import org.pentaho.di.core.annotations.Step;
import org.pentaho.di.core.injection.Injection;
import org.pentaho.di.core.injection.InjectionSupported;
import org.pentaho.di.core.row.RowMeta;
import org.pentaho.di.core.row.value.ValueMetaString;
import org.pentaho.di.core.variables.VariableSpace;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.StepDataInterface;
import org.pentaho.di.trans.step.StepInterface;
import org.pentaho.di.trans.step.StepMeta;
import org.pentaho.di.trans.step.StepMetaInterface;
import org.pentaho.di.trans.streaming.common.BaseStreamStepMeta;

@Step( id = "${plugin_class_name}", image = "${plugin_class_name}.svg", name = "${plugin_name}",
  description = "${plugin_description}", categoryDescription = "Streaming" )
@InjectionSupported( localizationPrefix = "FileStreamMeta.Injection." )
public class ${plugin_class_name}Meta extends BaseStreamStepMeta implements StepMetaInterface, Cloneable {

  private static Class<?> PKG = ${plugin_class_name}.class; // for i18n purposes, needed by Translator2!!   ${symbol_dollar}NON-NLS-1${symbol_dollar}
  public static final String CONNECTION_URL = "connectionUrl";


  //use injection annotation to automagically get load/save behavior
  //example
  @Injection( name = CONNECTION_URL )
  public String connectionUrl;

  public ${plugin_class_name}Meta() {
    super();
  }

  public Object clone() {
    Object retval = super.clone();
    return retval;
  }

  public void setDefault() {
    //todo: set defaults for all fields
  }

  @Override
  public RowMeta getRowMeta( String origin, VariableSpace space ) {
    RowMeta rowMeta = new RowMeta();
    rowMeta.addValueMeta( new ValueMetaString( "line" ) );
    return rowMeta;
  }


  public StepInterface getStep( StepMeta stepMeta, StepDataInterface stepDataInterface, int cnr, TransMeta tr,
                                Trans trans ) {
    return new ${plugin_class_name}( stepMeta, stepDataInterface, cnr, tr, trans );
  }

  public StepDataInterface getStepData() {
    return new ${plugin_class_name}Data();
  }

  public String getDialogClassName() {
    return "${package}.${plugin_class_name}Dialog";
  }

  public String getConnectionUrl() {
    return connectionUrl;
  }

  public void setConnectionUrl( String connectionUrl ) {
    this.connectionUrl = connectionUrl;
  }
}

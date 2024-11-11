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

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.pentaho.di.core.row.ValueMetaInterface;
import org.pentaho.di.i18n.BaseMessages;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.StepDialogInterface;
import org.pentaho.di.trans.streaming.common.BaseStreamStepMeta;
import org.pentaho.di.ui.core.widget.TextVar;
import org.pentaho.di.ui.trans.step.BaseStreamingDialog;

public class ${plugin_class_name}Dialog extends BaseStreamingDialog implements StepDialogInterface {

  private static Class<?> PKG = ${plugin_class_name}Meta.class; // for i18n purposes, needed by Translator2!!   ${symbol_dollar}NON-NLS-1${symbol_dollar}

  private ${plugin_class_name}Meta streamMeta;

  protected Label wlConnectionUrl;
  protected TextVar wConnectionUrl;

  public ${plugin_class_name}Dialog( Shell parent, Object in, TransMeta tr, String sname ) {
    super( parent, in, tr, sname );
    streamMeta = (${plugin_class_name}Meta) in;
  }

  @Override protected String getDialogTitle() {
    return BaseMessages.getString( PKG, "${plugin_class_name}Dialog.Shell.Title" );
  }

  @Override protected void buildSetup( Composite wSetupComp ) {
    wlConnectionUrl = new Label( wSetupComp, SWT.LEFT );
    props.setLook( wlConnectionUrl );
    wlConnectionUrl.setText( BaseMessages.getString( PKG, "${plugin_class_name}Dialog.ConnectionUrl" ) );
    FormData fdlConnectionUrl = new FormData();
    fdlConnectionUrl.left = new FormAttachment( 0, 0 );
    fdlConnectionUrl.top = new FormAttachment( 0, 0 );
    fdlConnectionUrl.right = new FormAttachment( 50, 0 );
    wlConnectionUrl.setLayoutData( fdlConnectionUrl );

    wConnectionUrl = new TextVar( transMeta, wSetupComp, SWT.SINGLE | SWT.LEFT | SWT.BORDER );
    props.setLook( wConnectionUrl );
    wConnectionUrl.addModifyListener( lsMod );
    FormData fdConnectionUrl = new FormData();
    fdConnectionUrl.left = new FormAttachment( 0, 0 );
    fdConnectionUrl.right = new FormAttachment( 75, 0 );
    fdConnectionUrl.top = new FormAttachment( wlConnectionUrl, 5 );
    wConnectionUrl.setLayoutData( fdConnectionUrl );
  }

  @Override protected void additionalOks( BaseStreamStepMeta meta ) {
    streamMeta.setConnectionUrl( wConnectionUrl.getText() );
  }

  @Override protected void createAdditionalTabs() {
  }

  @Override protected int[] getFieldTypes() {
    return new int[]{ ValueMetaInterface.TYPE_STRING };
  }

  @Override protected String[] getFieldNames() {
    return new String[]{ "line" };
  }

  @Override protected void getData() {
    super.getData();
    if ( streamMeta.getConnectionUrl() != null ) {
      wConnectionUrl.setText( streamMeta.getConnectionUrl() );
    }
  }
}

#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package ${package};

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.*;
import org.pentaho.di.core.Props;
import org.pentaho.di.core.plugins.PluginInterface;
import org.pentaho.di.core.plugins.PluginRegistry;
import org.pentaho.di.core.plugins.StepPluginType;
import org.pentaho.di.i18n.BaseMessages;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.BaseStepMeta;
import org.pentaho.di.trans.step.StepDialogInterface;
import org.pentaho.di.ui.core.ConstUI;
import org.pentaho.di.ui.core.FormDataBuilder;
import org.pentaho.di.ui.core.gui.GUIResource;
import org.pentaho.di.ui.core.widget.TextVar;
import org.pentaho.di.ui.trans.step.BaseStepDialog;

public class ${plugin_class_name}Dialog extends BaseStepDialog implements StepDialogInterface {

  private static Class<?> PKG = ${plugin_class_name}Meta.class; // for i18n purposes, needed by Translator2!!   $NON-NLS-1$

  private static final int MARGIN_SIZE = 15;
  private static final int LABEL_SPACING = 5;
  private static final int ELEMENT_SPACING = 10;

  private static final int LARGE_FIELD = 350;
  private static final int MEDIUM_FIELD = 250;
  private static final int SMALL_FIELD = 75;

  private ${plugin_class_name}Meta meta;

  private ScrolledComposite scrolledComposite;
  private Composite contentComposite;
  private Label wStepNameLabel;
  private Text wStepNameField;
  private Label wSampleLabel1;
  private TextVar wSampleTextField1;
  private Label wSampleLabel2;
  private TextVar wSampleTextField2;
  private Label wSampleLabel3;
  private TextVar wSampleTextField3;
  private Button wInlineButton;
  private Label wSampleLabel4;
  private CCombo wSampleComboBox;
  private Button wRadioButton1;
  private Button wRadioButton2;
  private Button wCheckbox1;
  private Button wCheckbox2;
  private Table wTable;
  private Button wTableButton;
  private Button wCancel;
  private Button wAction;
  private Button wOK;
  private ModifyListener lsMod;
  private Listener lsCancel;
  private Listener lsOK;
  private SelectionAdapter lsDef;
  private boolean changed;

  public ${plugin_class_name}Dialog( Shell parent, Object in, TransMeta tr, String sname ) {
    super( parent, (BaseStepMeta) in, tr, sname );
    meta = (${plugin_class_name}Meta) in;
  }

  public String open() {
    //Set up window
    Shell parent = getParent();
    Display display = parent.getDisplay();

    shell = new Shell( parent, SWT.DIALOG_TRIM | SWT.RESIZE | SWT.MIN | SWT.MAX );
    shell.setMinimumSize( 450, 335 );
    props.setLook( shell);
    setShellImage( shell, meta );

    lsMod = new ModifyListener() {
      public void modifyText( ModifyEvent e ) {
        meta.setChanged();
      }
    };
    changed = meta.hasChanged();

    //15 pixel margins
    FormLayout formLayout = new FormLayout();
    formLayout.marginLeft = MARGIN_SIZE;
    formLayout.marginHeight = MARGIN_SIZE;
    shell.setLayout( formLayout );
    shell.setText( BaseMessages.getString( PKG, "${plugin_class_name}Dialog.Shell.Title" ) );

    //Build a scrolling composite and a composite for holding all content
    scrolledComposite = new ScrolledComposite(shell, SWT.V_SCROLL);
    contentComposite = new Composite(scrolledComposite, SWT.NONE);
    FormLayout contentLayout = new FormLayout();
    contentLayout.marginRight = MARGIN_SIZE;
    contentComposite.setLayout( contentLayout );
    FormData compositeLayoutData = new FormDataBuilder().fullSize()
                                                        .result();
    contentComposite.setLayoutData( compositeLayoutData );
    props.setLook( contentComposite );

    //Step name label and text field
    wStepNameLabel = new Label( contentComposite, SWT.RIGHT );
    wStepNameLabel.setText( BaseMessages.getString( PKG, "${plugin_class_name}Dialog.Stepname.Label" ) );
    props.setLook(wStepNameLabel);
    FormData fdStepNameLabel = new FormDataBuilder().left()
                                                    .top()
                                                    .result();
    wStepNameLabel.setLayoutData( fdStepNameLabel );

    wStepNameField = new Text( contentComposite, SWT.SINGLE | SWT.LEFT | SWT.BORDER );
    wStepNameField.setText( stepname );
    props.setLook(wStepNameField);
    wStepNameField.addModifyListener( lsMod );
    FormData fdStepName = new FormDataBuilder().left()
                                               .top(wStepNameLabel, LABEL_SPACING )
                                               .width( MEDIUM_FIELD )
                                               .result();
    wStepNameField.setLayoutData( fdStepName );

    //Job icon, centered vertically between the top of the label and the bottom of the field.
    Label wicon = new Label( contentComposite, SWT.CENTER );
    wicon.setImage( getImage() );
    FormData fdIcon = new FormDataBuilder().right()
                                           .top( 0, 4 )
                                           .bottom( new FormAttachment(wStepNameField, 0, SWT.BOTTOM ) )
                                           .result();
    wicon.setLayoutData( fdIcon );
    props.setLook( wicon );

    //Spacer between entry info and content
    Label topSpacer = new Label( contentComposite, SWT.HORIZONTAL | SWT.SEPARATOR );
    FormData fdSpacer = new FormDataBuilder().fullWidth()
                                             .top( wStepNameField, MARGIN_SIZE )
                                             .result();
    topSpacer.setLayoutData( fdSpacer );

    //Groups for first type of content
    Group group = new Group( contentComposite, SWT.SHADOW_ETCHED_IN );
    group.setText( BaseMessages.getString( PKG, "${plugin_class_name}Dialog.GroupText" ) );
    FormLayout groupLayout = new FormLayout();
    groupLayout.marginWidth = MARGIN_SIZE;
    groupLayout.marginHeight = MARGIN_SIZE;
    group.setLayout( groupLayout );
    FormData groupLayoutData = new FormDataBuilder().fullWidth()
                                                    .top( topSpacer, MARGIN_SIZE )
                                                    .result();
    group.setLayoutData( groupLayoutData );
    props.setLook( group );

    //350 px (large) label/field
    wSampleLabel1 = new Label( group, SWT.LEFT );
    props.setLook(wSampleLabel1);
    wSampleLabel1.setText( BaseMessages.getString( PKG, "${plugin_class_name}Dialog.TextField350" ) );
    FormData fdlTransformation = new FormDataBuilder().left()
                                                      .top()
                                                      .result();
    wSampleLabel1.setLayoutData( fdlTransformation );

    wSampleTextField1 = new TextVar( transMeta, group, SWT.SINGLE | SWT.LEFT | SWT.BORDER );
    props.setLook(wSampleTextField1);
    FormData fdTransformation = new FormDataBuilder().left()
                                                     .top( wSampleLabel1, LABEL_SPACING )
                                                     .width( LARGE_FIELD )
                                                     .result();
    wSampleTextField1.setLayoutData( fdTransformation );

    //250 px (medium) label/field
    wSampleLabel2 = new Label( group, SWT.LEFT );
    props.setLook(wSampleLabel2);
    wSampleLabel2.setText( BaseMessages.getString( PKG, "${plugin_class_name}Dialog.TextField250" ) );
    FormData fdlTransformation2 = new FormDataBuilder().left()
                                                       .top( wSampleTextField1, ELEMENT_SPACING )
                                                       .result();
    wSampleLabel2.setLayoutData( fdlTransformation2 );

    wSampleTextField2 = new TextVar( transMeta, group, SWT.SINGLE | SWT.LEFT | SWT.BORDER );
    props.setLook(wSampleTextField2);
    FormData fdTransformation2 = new FormDataBuilder().left()
                                                      .top( wSampleLabel2, LABEL_SPACING )
                                                      .width( MEDIUM_FIELD )
                                                      .result();
    wSampleTextField2.setLayoutData( fdTransformation2 );

    //75 px (small) label/field with inline button. 50px fields may be appropriate in some scenarios
    wSampleLabel3 = new Label( group, SWT.LEFT );
    props.setLook(wSampleLabel3);
    wSampleLabel3.setText( BaseMessages.getString( PKG, "${plugin_class_name}Dialog.TextField75" ) );
    FormData fdlTransformation3 = new FormDataBuilder().left()
                                                       .top( wSampleTextField2, ELEMENT_SPACING )
                                                       .result();
    wSampleLabel3.setLayoutData( fdlTransformation3 );

    wSampleTextField3 = new TextVar( transMeta, group, SWT.SINGLE | SWT.LEFT | SWT.BORDER );
    props.setLook(wSampleTextField3);
    FormData fdTransformation3 = new FormDataBuilder().left()
                                                      .top( wSampleLabel3, LABEL_SPACING )
                                                      .width( SMALL_FIELD )
                                                      .result();
    wSampleTextField3.setLayoutData( fdTransformation3 );

    wInlineButton = new Button( group, SWT.PUSH );
    wInlineButton.setText( BaseMessages.getString( PKG, "${plugin_class_name}Dialog.Button" ) );
    FormData fdInlineButton = new FormDataBuilder().left( wSampleTextField3, LABEL_SPACING )
                                                   .top( new FormAttachment( wSampleTextField3, 0, SWT.CENTER ) )
                                                   .result();
    wInlineButton.setLayoutData( fdInlineButton );

    //350px label/combo box
    wSampleLabel4 = new Label( group, SWT.LEFT );
    props.setLook(wSampleLabel4);
    wSampleLabel4.setText( BaseMessages.getString( PKG, "${plugin_class_name}Dialog.Dropdown" ) );
    FormData fdlTransformation4 = new FormDataBuilder().left()
                                                       .top( wSampleTextField3, ELEMENT_SPACING )
                                                       .result();
    wSampleLabel4.setLayoutData( fdlTransformation4 );

    int comboBoxArrowWidth = 18; //The arrow on the right of the combo box does not contribute to its width.
    //We reduce with width of the box to remedy this.

    wSampleComboBox = new CCombo( group, SWT.SINGLE | SWT.LEFT | SWT.BORDER );
    props.setLook(wSampleComboBox);
    FormData fdTransformation4 = new FormDataBuilder().left()
                                                      .top( wSampleLabel4, LABEL_SPACING )
                                                      .width( LARGE_FIELD - comboBoxArrowWidth )
                                                      .result();
    wSampleComboBox.setLayoutData( fdTransformation4 );
    wSampleComboBox.add( BaseMessages.getString( PKG, "${plugin_class_name}Dialog.ComboBoxOption1" ) );
    wSampleComboBox.add( BaseMessages.getString( PKG, "${plugin_class_name}Dialog.ComboBoxOption2" ) );

    //Tabs
    CTabFolder wTabFolder = new CTabFolder( contentComposite, SWT.BORDER );
    props.setLook( wTabFolder, Props.WIDGET_STYLE_TAB );

    CTabItem wTab1 = new CTabItem( wTabFolder, SWT.NONE );
    wTab1.setText( BaseMessages.getString( PKG, "${plugin_class_name}Dialog.Tab1" ) );
    Composite wTab1Contents = new Composite( wTabFolder, SWT.SHADOW_NONE );
    props.setLook( wTab1Contents );
    FormLayout tab1Layout = new FormLayout();
    tab1Layout.marginWidth = MARGIN_SIZE;
    tab1Layout.marginHeight = MARGIN_SIZE;
    wTab1Contents.setLayout( tab1Layout );
    FormData fdTab1 = new FormDataBuilder().fullSize()
                                           .result();
    wTab1Contents.setLayoutData( fdTab1 );
    wTab1.setControl( wTab1Contents );

    CTabItem wTab2 = new CTabItem( wTabFolder, SWT.NONE );
    wTab2.setText( BaseMessages.getString( PKG, "${plugin_class_name}Dialog.Tab2" ) );
    Composite wTab2Contents = new Composite( wTabFolder, SWT.NONE );
    props.setLook( wTab2Contents );
    FormLayout tab2Layout = new FormLayout();
    tab2Layout.marginWidth = MARGIN_SIZE;
    tab2Layout.marginHeight = MARGIN_SIZE;
    wTab2Contents.setLayout( tab2Layout );
    FormData fdTab2 = new FormDataBuilder().fullSize()
                                           .result();
    wTab2Contents.setLayoutData( fdTab2 );
    wTab2.setControl( wTab2Contents );

    wTabFolder.setSelection( 0 );

    //Radio buttons and checkboxes for the first tab
    wRadioButton1 = new Button(wTab1Contents, SWT.RADIO);
    wRadioButton1.setText( BaseMessages.getString( PKG, "${plugin_class_name}Dialog.RadioButton1"));
    wRadioButton1.setBackground( display.getSystemColor( SWT.COLOR_TRANSPARENT ) );
    FormData fdRadioButton1 = new FormDataBuilder().left()
                                                   .top()
                                                   .result();
    wRadioButton1.setLayoutData(fdRadioButton1);

    wRadioButton2 = new Button(wTab1Contents, SWT.RADIO);
    wRadioButton2.setText( BaseMessages.getString( PKG, "${plugin_class_name}Dialog.RadioButton2"));
    wRadioButton2.setBackground( display.getSystemColor( SWT.COLOR_TRANSPARENT ) );
    FormData fdRadioButton2 = new FormDataBuilder().left()
                                                   .top( wRadioButton1, ELEMENT_SPACING )
                                                   .result();
    wRadioButton2.setLayoutData(fdRadioButton2);

    wCheckbox1 = new Button(wTab1Contents, SWT.CHECK);
    wCheckbox1.setText( BaseMessages.getString( PKG, "${plugin_class_name}Dialog.Checkbox1" ) );
    wCheckbox1.setBackground( display.getSystemColor( SWT.COLOR_TRANSPARENT ) );
    FormData fdCheck1 = new FormDataBuilder().left( wRadioButton1, MARGIN_SIZE )
                                             .top()
                                             .result();
    wCheckbox1.setLayoutData(fdCheck1);

    wCheckbox2 = new Button(wTab1Contents, SWT.CHECK);
    wCheckbox2.setText( BaseMessages.getString( PKG, "${plugin_class_name}Dialog.Checkbox2" ) );
    wCheckbox2.setBackground( display.getSystemColor( SWT.COLOR_TRANSPARENT ) );
    FormData fdCheck2 = new FormDataBuilder().left( wRadioButton1, MARGIN_SIZE )
                                             .top( wCheckbox1, ELEMENT_SPACING )
                                             .result();
    wCheckbox2.setLayoutData(fdCheck2);

    //Table and button for the second tab
    wTableButton = new Button( wTab2Contents, SWT.PUSH );
    wTableButton.setText( BaseMessages.getString( PKG, "${plugin_class_name}Dialog.Button" ) );
    FormData fdTableButton = new FormDataBuilder().right()
                                                  .bottom()
                                                  .result();
    wTableButton.setLayoutData( fdTableButton );

    wTable = new Table(wTab2Contents, SWT.MULTI | SWT.BORDER | SWT.NO_SCROLL);
    wTable.setHeaderVisible(true);
    wTable.setLinesVisible(true);
    FormData fdTable = new FormDataBuilder().fullWidth()
                                            .top()
                                            .bottom( wTableButton, -ELEMENT_SPACING )
                                            .result();
    wTable.setLayoutData( fdTable );
    wTable.setItemCount(5);

    int numColumns = 3;
    for(int i = 0; i < numColumns; i++) {
      TableColumn col = new TableColumn( wTable, SWT.NONE );
      col.setResizable(false);
      col.setText( BaseMessages.getString( PKG, "${plugin_class_name}Dialog.TableHeader" ) + " " + ( i + 1 ) );
    }
    wTable.addControlListener(new ControlAdapter() {
      @Override
      public void controlResized( ControlEvent controlEvent ) {
        int tableWidth = wTable.getSize().x;
        int numColumns = wTable.getColumnCount();
        for( TableColumn col : wTable.getColumns() ) {
          col.setWidth( tableWidth / numColumns );
        }
      }
    });

    //Cancel, action and OK buttons for the bottom of the window.
    wCancel = new Button( shell, SWT.PUSH );
    wCancel.setText( BaseMessages.getString( PKG, "System.Button.Cancel" ) );
    FormData fdCancel = new FormDataBuilder().right(100, -MARGIN_SIZE)
                                             .bottom()
                                             .result();
    wCancel.setLayoutData( fdCancel );

    wAction = new Button( shell, SWT.PUSH );
    wAction.setText( BaseMessages.getString( PKG, "${plugin_class_name}Dialog.ActionButton" ) );
    int actionButtonWidth = wAction.computeSize( SWT.DEFAULT, SWT.DEFAULT ).x;
    FormData fdAction = new FormDataBuilder().right( 50, actionButtonWidth / 2 )
                                             .bottom()
                                             .result();
    wAction.setLayoutData( fdAction );

    wOK = new Button( shell, SWT.PUSH );
    wOK.setText( BaseMessages.getString( PKG, "System.Button.OK" ) );
    FormData fdOk = new FormDataBuilder().right( wCancel, -LABEL_SPACING )
                                         .bottom()
                                         .result();
    wOK.setLayoutData( fdOk );

    //Space between bottom buttons and the table, final layout for table
    Label bottomSpacer = new Label( shell, SWT.HORIZONTAL | SWT.SEPARATOR );
    FormData fdhSpacer = new FormDataBuilder().left()
                                              .right(100, -MARGIN_SIZE)
                                              .bottom( wCancel, -MARGIN_SIZE )
                                              .result();
    bottomSpacer.setLayoutData( fdhSpacer );

    FormData fdTabFolder = new FormDataBuilder().fullWidth()
                                                .top( group, MARGIN_SIZE )
                                                .bottom()
                                                .result();
    wTabFolder.setLayoutData( fdTabFolder );

    //Add everything to the scrolling composite
    scrolledComposite.setContent(contentComposite);
    scrolledComposite.setExpandVertical(true);
    scrolledComposite.setExpandHorizontal(true);
    scrolledComposite.setMinSize( contentComposite.computeSize( SWT.DEFAULT, SWT.DEFAULT ) );

    scrolledComposite.setLayout(new FormLayout());
    FormData fdScrolledComposite = new FormDataBuilder().fullWidth()
                                                        .top()
                                                        .bottom( bottomSpacer, -MARGIN_SIZE )
                                                        .result();
    scrolledComposite.setLayoutData(fdScrolledComposite);
    props.setLook(scrolledComposite);

    //Listeners
    lsCancel = new Listener() {
      public void handleEvent( Event e ) {
        cancel();
      }
    };
    lsOK = new Listener() {
      public void handleEvent( Event e ) {
        ok();
      }
    };

    wOK.addListener( SWT.Selection, lsOK );
    wCancel.addListener( SWT.Selection, lsCancel );

    lsDef = new SelectionAdapter() {
      public void widgetDefaultSelected( SelectionEvent e ) {
        ok();
      }
    };
    wStepNameField.addSelectionListener( lsDef );

    shell.addShellListener( new ShellAdapter() {
      public void shellClosed( ShellEvent e ) {
        cancel();
      }
    } );

    //Show shell
    setSize();
    meta.setChanged( changed );
    shell.open();
    while ( !shell.isDisposed() ) {
      if ( !display.readAndDispatch() ) {
        display.sleep();
      }
    }
    return stepname;
  }

  private Image getImage() {
    PluginInterface plugin =
        PluginRegistry.getInstance().getPlugin( StepPluginType.class, stepMeta.getStepMetaInterface() );
    String id = plugin.getIds()[0];
    if ( id != null ) {
      return GUIResource.getInstance().getImagesSteps().get( id ).getAsBitmapForSize( shell.getDisplay(),
          ConstUI.ICON_SIZE, ConstUI.ICON_SIZE );
    }
    return null;
  }

  private void cancel() {
    dispose();
  }

  private void ok() {
    stepname = wStepNameField.getText();
    dispose();
  }
}
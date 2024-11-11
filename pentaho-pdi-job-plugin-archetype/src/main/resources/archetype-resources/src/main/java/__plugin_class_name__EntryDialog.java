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

package ${package};

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.*;
import org.pentaho.di.core.Props;
import org.pentaho.di.core.plugins.JobEntryPluginType;
import org.pentaho.di.core.plugins.PluginInterface;
import org.pentaho.di.core.plugins.PluginRegistry;
import org.pentaho.di.i18n.BaseMessages;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.job.entry.JobEntryDialogInterface;
import org.pentaho.di.job.entry.JobEntryInterface;
import org.pentaho.di.repository.Repository;
import org.pentaho.di.ui.core.ConstUI;
import org.pentaho.di.ui.core.FormDataBuilder;
import org.pentaho.di.ui.core.PropsUI;
import org.pentaho.di.ui.core.gui.GUIResource;
import org.pentaho.di.ui.core.gui.WindowProperty;
import org.pentaho.di.ui.core.widget.TextVar;
import org.pentaho.di.ui.job.entry.JobEntryDialog;
import org.pentaho.di.ui.util.HelpUtils;

public class ${plugin_class_name}EntryDialog extends JobEntryDialog implements JobEntryDialogInterface {
	
  private static Class<?> PKG = ${plugin_class_name}Entry.class; // for i18n purposes, needed by Translator2!!   $NON-NLS-1$	

	private static final int MARGIN_SIZE = 15;
	private static final int LABEL_SPACING = 5;
	private static final int ELEMENT_SPACING = 10;

	private static final int LARGE_FIELD = 350;
	private static final int MEDIUM_FIELD = 250;
	private static final int SMALL_FIELD = 75;

	private ScrolledComposite scrolledComposite;
	private Composite contentComposite;
	private Label wJobnameLabel;
	private Text wJobnameField;
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
	private MyJobEntry jobEntry;

  public ${plugin_class_name}EntryDialog( Shell parent, JobEntryInterface jobEntryInt, Repository rep, JobMeta jobMeta ) {
    super( parent, jobEntryInt, rep, jobMeta );
    jobEntry = (${plugin_class_name}Entry) jobEntryInt;
    
    if ( jobEntry.getName() == null ) {
      jobEntry.setName( jobEntryInt.getName() );
    }
  }

  @Override
  public JobEntryInterface open() {
		//Set up window
    Shell parent = getParent();
    Display display = parent.getDisplay();

    shell = new Shell( parent, SWT.DIALOG_TRIM | SWT.RESIZE | SWT.MIN | SWT.MAX );
    shell.setMinimumSize( 450, 335 );
    props.setLook( shell );
    setShellImage( shell );

    lsMod = new ModifyListener() {
      public void modifyText( ModifyEvent e ) {
        jobEntry.setChanged();
      }
    };
    changed = jobEntry.hasChanged();

    //15 pixel margins
    FormLayout formLayout = new FormLayout();
    formLayout.marginLeft = MARGIN_SIZE;
    formLayout.marginHeight = MARGIN_SIZE;
    shell.setLayout( formLayout );
    shell.setText( BaseMessages.getString( PKG, "${plugin_class_name}EntryDialog.Shell.Title" ) );

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

    //Job name label and text field
    wJobnameLabel = new Label( contentComposite, SWT.RIGHT );
    wJobnameLabel.setText( BaseMessages.getString( PKG, "${plugin_class_name}EntryDialog.Jobname.Label" ) );
    props.setLook(wJobnameLabel);
    FormData fdJobnameLabel = new FormDataBuilder().left()
                                                   .top()
                                                   .result();
    wJobnameLabel.setLayoutData( fdJobnameLabel );

    wJobnameField = new Text( contentComposite, SWT.SINGLE | SWT.LEFT | SWT.BORDER );
    if ( jobEntry.getName() != null ) {
      wJobnameField.setText( jobEntry.getName() );
    }
    props.setLook(wJobnameField);
    wJobnameField.addModifyListener( lsMod );
    FormData fdJobname = new FormDataBuilder().left()
                                              .top( wJobnameLabel, LABEL_SPACING )
                                              .width( MEDIUM_FIELD )
                                              .result();
    wJobnameField.setLayoutData( fdJobname );

    //Job icon, centered vertically between the top of the label and the bottom of the field.
    Label wicon = new Label( contentComposite, SWT.CENTER );
    wicon.setImage( getImage() );
    FormData fdIcon = new FormDataBuilder().right()
                                           .top( 0, 4 )
                                           .bottom( new FormAttachment( wJobnameField, 0, SWT.BOTTOM ) )
                                           .result();
    wicon.setLayoutData( fdIcon );
    props.setLook( wicon );

    //Spacer between entry info and content
    Label topSpacer = new Label( contentComposite, SWT.HORIZONTAL | SWT.SEPARATOR );
    FormData fdSpacer = new FormDataBuilder().fullWidth()
                                             .top( wJobnameField, MARGIN_SIZE )
                                             .result();
    topSpacer.setLayoutData( fdSpacer );

    //Groups for first type of content
    Group group = new Group( contentComposite, SWT.SHADOW_ETCHED_IN );
    group.setText( BaseMessages.getString( PKG, "${plugin_class_name}EntryDialog.GroupText" ) );
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
    wSampleLabel1.setText( BaseMessages.getString( PKG, "${plugin_class_name}EntryDialog.TextField350" ) );
    FormData fdlTransformation = new FormDataBuilder().left()
                                                      .top()
                                                      .result();
    wSampleLabel1.setLayoutData( fdlTransformation );

    wSampleTextField1 = new TextVar( jobMeta, group, SWT.SINGLE | SWT.LEFT | SWT.BORDER );
    props.setLook(wSampleTextField1);
    FormData fdTransformation = new FormDataBuilder().left()
                                                     .top( wSampleLabel1, LABEL_SPACING )
                                                     .width( LARGE_FIELD )
                                                     .result();
    wSampleTextField1.setLayoutData( fdTransformation );

    //250 px (medium) label/field
    wSampleLabel2 = new Label( group, SWT.LEFT );
    props.setLook(wSampleLabel2);
    wSampleLabel2.setText( BaseMessages.getString( PKG, "${plugin_class_name}EntryDialog.TextField250" ) );
    FormData fdlTransformation2 = new FormDataBuilder().left()
                                                       .top( wSampleTextField1, ELEMENT_SPACING )
                                                       .result();
    wSampleLabel2.setLayoutData( fdlTransformation2 );

    wSampleTextField2 = new TextVar( jobMeta, group, SWT.SINGLE | SWT.LEFT | SWT.BORDER );
    props.setLook(wSampleTextField2);
    FormData fdTransformation2 = new FormDataBuilder().left()
                                                      .top( wSampleLabel2, LABEL_SPACING )
                                                      .width( MEDIUM_FIELD )
                                                      .result();
    wSampleTextField2.setLayoutData( fdTransformation2 );

    //75 px (small) label/field with inline button. 50px fields may be appropriate in some scenarios
    wSampleLabel3 = new Label( group, SWT.LEFT );
    props.setLook(wSampleLabel3);
    wSampleLabel3.setText( BaseMessages.getString( PKG, "${plugin_class_name}EntryDialog.TextField75" ) );
    FormData fdlTransformation3 = new FormDataBuilder().left()
                                                       .top( wSampleTextField2, ELEMENT_SPACING )
                                                       .result();
    wSampleLabel3.setLayoutData( fdlTransformation3 );

    wSampleTextField3 = new TextVar( jobMeta, group, SWT.SINGLE | SWT.LEFT | SWT.BORDER );
    props.setLook(wSampleTextField3);
    FormData fdTransformation3 = new FormDataBuilder().left()
                                                      .top( wSampleLabel3, LABEL_SPACING )
                                                      .width( SMALL_FIELD )
                                                      .result();
    wSampleTextField3.setLayoutData( fdTransformation3 );

    wInlineButton = new Button( group, SWT.PUSH );
    wInlineButton.setText( BaseMessages.getString( PKG, "${plugin_class_name}EntryDialog.Button" ) );
    FormData fdInlineButton = new FormDataBuilder().left( wSampleTextField3, LABEL_SPACING )
                                                   .top( new FormAttachment( wSampleTextField3, 0, SWT.CENTER ) )
                                                   .result();
    wInlineButton.setLayoutData( fdInlineButton );

    //350px label/combo box
    wSampleLabel4 = new Label( group, SWT.LEFT );
    props.setLook(wSampleLabel4);
    wSampleLabel4.setText( BaseMessages.getString( PKG, "${plugin_class_name}EntryDialog.Dropdown" ) );
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
    wSampleComboBox.add( BaseMessages.getString( PKG, "${plugin_class_name}EntryDialog.ComboBoxOption1" ) );
    wSampleComboBox.add( BaseMessages.getString( PKG, "${plugin_class_name}EntryDialog.ComboBoxOption2" ) );

    //Tabs
    CTabFolder wTabFolder = new CTabFolder( contentComposite, SWT.BORDER );
    props.setLook( wTabFolder, Props.WIDGET_STYLE_TAB );

    final CTabItem wTab1 = new CTabItem( wTabFolder, SWT.NONE );
    wTab1.setText( BaseMessages.getString( PKG, "${plugin_class_name}EntryDialog.Tab1" ) );
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
    wTab2.setText( BaseMessages.getString( PKG, "${plugin_class_name}EntryDialog.Tab2" ) );
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
    wRadioButton1.setText( BaseMessages.getString( PKG, "${plugin_class_name}EntryDialog.RadioButton1"));
    wRadioButton1.setBackground( display.getSystemColor( SWT.COLOR_TRANSPARENT ) );
    FormData fdRadioButton1 = new FormDataBuilder().left()
                                                   .top()
                                                   .result();
    wRadioButton1.setLayoutData(fdRadioButton1);

    wRadioButton2 = new Button(wTab1Contents, SWT.RADIO);
    wRadioButton2.setText( BaseMessages.getString( PKG, "${plugin_class_name}EntryDialog.RadioButton2"));
    wRadioButton2.setBackground( display.getSystemColor( SWT.COLOR_TRANSPARENT ) );
    FormData fdRadioButton2 = new FormDataBuilder().left()
                                                   .top( wRadioButton1, ELEMENT_SPACING )
                                                   .result();
    wRadioButton2.setLayoutData(fdRadioButton2);

    wCheckbox1 = new Button(wTab1Contents, SWT.CHECK);
    wCheckbox1.setText( BaseMessages.getString( PKG, "${plugin_class_name}EntryDialog.Checkbox1" ) );
    wCheckbox1.setBackground( display.getSystemColor( SWT.COLOR_TRANSPARENT ) );
    FormData fdCheck1 = new FormDataBuilder().left( wRadioButton1, MARGIN_SIZE )
                                             .top()
                                             .result();
    wCheckbox1.setLayoutData(fdCheck1);

    wCheckbox2 = new Button(wTab1Contents, SWT.CHECK);
    wCheckbox2.setText( BaseMessages.getString( PKG, "${plugin_class_name}EntryDialog.Checkbox2" ) );
    wCheckbox2.setBackground( display.getSystemColor( SWT.COLOR_TRANSPARENT ) );
    FormData fdCheck2 = new FormDataBuilder().left( wRadioButton1, MARGIN_SIZE )
                                             .top( wCheckbox1, ELEMENT_SPACING )
                                             .result();
    wCheckbox2.setLayoutData(fdCheck2);

    //Table and button for the second tab
    wTableButton = new Button( wTab2Contents, SWT.PUSH );
    wTableButton.setText( BaseMessages.getString( PKG, "${plugin_class_name}EntryDialog.Button" ) );
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
      col.setText( BaseMessages.getString( PKG, "${plugin_class_name}EntryDialog.TableHeader" ) + " " + ( i + 1 ) );
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
    FormData fdCancel = new FormDataBuilder().right( 100, -MARGIN_SIZE )
                                             .bottom()
                                             .result();
    wCancel.setLayoutData( fdCancel );

    wAction = new Button( shell, SWT.PUSH );
    wAction.setText( BaseMessages.getString( PKG, "${plugin_class_name}EntryDialog.ActionButton" ) );
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
    wSampleTextField1.addSelectionListener( lsDef );
    wJobnameField.addSelectionListener( lsDef );

    shell.addShellListener( new ShellAdapter() {
      public void shellClosed( ShellEvent e ) {
        cancel();
      }
    } );

    //Show shell
    setSize();
    jobEntry.setChanged( changed );
    shell.open();
    shell.pack();
    while ( !shell.isDisposed() ) {
      if ( !display.readAndDispatch() ) {
        display.sleep();
      }
    }
    return jobEntry;
  }
  
  
  private Image getImage() {
    PluginInterface plugin =
	  PluginRegistry.getInstance().getPlugin( JobEntryPluginType.class, jobEntry );
	  String id = plugin.getIds()[0];
	  if ( id != null ) {
	    return GUIResource.getInstance().getImagesJobentries().get( id ).getAsBitmapForSize( shell.getDisplay(),
	      ConstUI.ICON_SIZE, ConstUI.ICON_SIZE );
	  }
	  return null;
  }
  
  private void setShellImage( Shell shell ) {
    if ( jobMeta != null ) {
			PluginInterface plugin = PluginRegistry.getInstance().getPlugin( JobEntryPluginType.class, jobEntry );
			createHelpButton( shell, jobMeta, plugin );
			String id = plugin.getIds()[ 0 ];
			if ( id != null ) {
				shell.setImage( GUIResource.getInstance().getImagesJobentries().get( id ).getAsBitmapForSize(
					shell.getDisplay(), ConstUI.ICON_SIZE, ConstUI.ICON_SIZE ) );
			}
		}
  }
  
  protected Button createHelpButton( final Shell shell, final JobMeta jobMeta, final PluginInterface plugin ) {
    return HelpUtils.createHelpButton( shell, HelpUtils.getHelpDialogTitle( plugin ), plugin );
  }

  private void cancel() {
    dispose();
  }

  private void ok() {
		jobEntry.setName( wJobnameField.getText() );
		dispose();
  }
  
  public void setSize() {
    setSize( shell, -1, -1, true );
  }
	  
  public static void setSize( Shell shell, int minWidth, int minHeight, boolean packIt ) {
    PropsUI props = PropsUI.getInstance();

		WindowProperty winprop = props.getScreen( shell.getText() );
		if ( winprop != null ) {
			winprop.setShell( shell, minWidth, minHeight );
		} else {
			if ( packIt ) {
				shell.pack();
			} else {
				shell.layout();
			}

			winprop = new WindowProperty( shell );
			winprop.setShell( shell, minWidth, minHeight );

			Rectangle shellBounds = shell.getBounds();
			Monitor monitor = shell.getDisplay().getPrimaryMonitor();
			if ( shell.getParent() != null ) {
				monitor = shell.getParent().getMonitor();
			}
			Rectangle monitorClientArea = monitor.getClientArea();

			int middleX = monitorClientArea.x + ( monitorClientArea.width - shellBounds.width ) / 2;
			int middleY = monitorClientArea.y + ( monitorClientArea.height - shellBounds.height ) / 2;

			shell.setLocation( middleX, middleY );
    }
  }
  
  public void dispose() {
    WindowProperty winprop = new WindowProperty( shell );
		props.setScreen( winprop );
		shell.dispose();
  }
}

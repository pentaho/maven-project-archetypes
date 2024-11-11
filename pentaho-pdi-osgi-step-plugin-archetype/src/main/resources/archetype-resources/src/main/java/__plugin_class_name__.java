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

import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.i18n.BaseMessages;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.BaseStep;
import org.pentaho.di.trans.step.StepDataInterface;
import org.pentaho.di.trans.step.StepInterface;
import org.pentaho.di.trans.step.StepMeta;
import org.pentaho.di.trans.step.StepMetaInterface;

/**
 * Describe your step plugin.
 * 
 */
public class ${plugin_class_name} extends BaseStep implements StepInterface {
  
  private static Class<?> PKG = ${plugin_class_name}Meta.class; // for i18n purposes, needed by Translator2!!   $NON-NLS-1$
  
  public ${plugin_class_name}( StepMeta stepMeta, StepDataInterface stepDataInterface, int copyNr, TransMeta transMeta,
    Trans trans ) {
    super( stepMeta, stepDataInterface, copyNr, transMeta, trans );
  }
  
  /**
     * Initialize and do work where other steps need to wait for...
     *
     * @param stepMetaInterface
     *          The metadata to work with
     * @param stepDataInterface
     *          The data to initialize
     */
    public boolean init( StepMetaInterface stepMetaInterface, StepDataInterface stepDataInterface ) {
      return super.init( stepMetaInterface, stepDataInterface );
    }

  public boolean processRow( StepMetaInterface smi, StepDataInterface sdi ) throws KettleException {
    Object[] r = getRow(); // get row, set busy!
    if ( r == null ) {
      // no more input to be expected...
      setOutputDone();
      return false;
    }
    
    putRow( getInputRowMeta(), r ); // copy row to possible alternate rowset(s).

    if ( checkFeedback( getLinesRead() ) ) {
      if ( log.isBasic() )
        logBasic( BaseMessages.getString( PKG, "${plugin_class_name}.Log.LineNumber" ) + getLinesRead() );
    }
      
    return true;
  }
}
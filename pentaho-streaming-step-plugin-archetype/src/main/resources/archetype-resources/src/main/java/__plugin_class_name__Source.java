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

import org.pentaho.di.core.logging.LogChannel;
import org.pentaho.di.core.logging.LogChannelInterface;
import org.pentaho.di.trans.streaming.common.BlockingQueueStreamSource;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static java.util.Collections.singletonList;

/**
 * A simple example implementation of StreamSource which streams rows from a specified file to an iterable.
 * <p>
 * Note that this class is strictly meant as an example and not intended for real use. It uses a simplistic strategy of
 * leaving a BufferedReader open in order to load rows as they come in, without real consideration of error conditions.
 */
public class ${plugin_class_name}Source extends BlockingQueueStreamSource<List<Object>> {

  private static Class<?> PKG = ${plugin_class_name}.class; // for i18n purposes, needed by Translator2!!   ${symbol_dollar}NON-NLS-1${symbol_dollar}
  private final ${plugin_class_name}Meta streamMeta;

  private LogChannelInterface logChannel = new LogChannel( this );
  private final ExecutorService executorService = Executors.newCachedThreadPool();
  private Future<?> future;

  public ${plugin_class_name}Source( ${plugin_class_name}Meta streamMeta, ${plugin_class_name} streamStep ) {
    super( streamStep );
    this.streamMeta = streamMeta;
  }


  @Override public void open() {
    if ( future != null ) {
      logChannel.logError( "open() called more than once" );
      return;
    }
    future = executorService.submit( this::readLoop );
  }

  @Override public void close() {
    super.close();
    future.cancel( true );
  }

  private void readLoop() {
    //this method should continuously call acceptRows with the next row retrieved from your streaming source
    //example:
    while ( true ) {
      acceptRows( singletonList( singletonList( "some value read from your source" ) ) );
    }
  }

}

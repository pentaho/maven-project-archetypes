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

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.util.regex.Pattern;

import org.pentaho.di.core.util.Utils;
import org.pentaho.di.core.exception.KettleJobException;
import org.pentaho.di.core.logging.LogChannel;
import org.pentaho.di.core.logging.LogChannelInterface;

public class ${plugin_class_name} {

  private String _wildcard;
  private String _targetDir;
  private String _sourceDir;

  public ${plugin_class_name}( String source, String target, String wildcard ) {
    _sourceDir = source;
    _targetDir = target;
    _wildcard = wildcard;
  }

  public long process() throws KettleJobException, FileNotFoundException {
    LogChannelInterface log = new LogChannel( this );
    File srcDir = getDir( _sourceDir );
    Pattern pattern = null;
    if ( !Utils.isEmpty( _wildcard ) ) {
      pattern = Pattern.compile( _wildcard );
    }
    final Pattern fpat = pattern;
    FileFilter regexFiler = new FileFilter() {
      @Override
      public boolean accept( File pathname ) {
        if ( fpat == null ) {
          return true;
        }
        if ( fpat.matcher( pathname.getName() ).matches() ) {
          return true;
        }
        return false;
      }
    };
    long files = 0;
    File[] allFiles = srcDir.listFiles( regexFiler );
    File outDir = new File( _targetDir );
    outDir.mkdirs();
    for ( int i = 0; i < allFiles.length; i++ ) {
      File cFile = allFiles[i];
      log.logDetailed( toString(), "processing file '" + cFile + "'" );
      processFile( cFile, outDir );
    }
    return files;
  }

  public File getDir( String dirname ) throws KettleJobException {
    File fl = new File( dirname );
    if ( !fl.isDirectory() ) {
      throw new KettleJobException( "'" + dirname + "' is not a directory" );
    }
    return fl;
  }

  public void processFile( File fl, File outDir ) throws FileNotFoundException {
  }
}

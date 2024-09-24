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
/*******************************************************************************
 *
 * Pentaho Big Data
 *
 * Copyright (C) 2002-2018 by Hitachi Vantara : http://www.pentaho.com
 *
 *******************************************************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ******************************************************************************/
package ${groupId}.hadoop.shim.${shimName};

import static org.junit.Assume.assumeTrue;

import java.io.IOException;

import ${groupId}.hadoop.shim.common.DistributedCacheUtilImplOSDependentTest;

/**
 * These tests are skipped because of having issue with setting permissions on hadoop local file system for mapr on
 * Windows.
 *
 * @see <a href=
 *      "http://jira.pentaho.com/browse/BAD-601?focusedCommentId=294386&page=com.atlassian.jira.plugin.system.issuetabpanels:comment-tabpanel${symbol_pound}comment-294386">BAD-601${symbol_pound}comment-294386</a>
 *      for more details.
 */
public class MapR5DistributedCacheUtilImplOSDependentTest extends DistributedCacheUtilImplOSDependentTest {

  @Override
  public void stageForCache() throws Exception {
    // Don't run this test on Windows env
    assumeTrue( !isWindows() );
    super.stageForCache();
  }

  @Override
  public void stageForCache_destination_exists() throws Exception {
    // Don't run this test on Windows env
    assumeTrue( !isWindows() );
    super.stageForCache_destination_exists();
  }

  @Override
  public void stagePluginsForCache() throws Exception {
    // Don't run this test on Windows env
    assumeTrue( !isWindows() );
    super.stagePluginsForCache();
  }

  @Override
  public void findFiles_hdfs_native() throws Exception {
    // Don't run this test on Windows env
    assumeTrue( !isWindows() );
    super.findFiles_hdfs_native();
  }

  @Override
  public void installKettleEnvironment() throws Exception {
    // Don't run this test on Windows env
    assumeTrue( !isWindows() );
    super.installKettleEnvironment();
  }

  @Override
  public void installKettleEnvironment_additional_plugins() throws Exception {
    // Don't run this test on Windows env
    assumeTrue( !isWindows() );
    super.installKettleEnvironment_additional_plugins();
  }

  @Override
  public void isPmrInstalledAt() throws IOException {
    // Don't run this test on Windows env
    assumeTrue( !isWindows() );
    super.isPmrInstalledAt();
  }

  @Override
  public void configureWithPmr() throws Exception {
    // Don't run this test on Windows env
    assumeTrue( !isWindows() );
    super.configureWithPmr();
  }

}

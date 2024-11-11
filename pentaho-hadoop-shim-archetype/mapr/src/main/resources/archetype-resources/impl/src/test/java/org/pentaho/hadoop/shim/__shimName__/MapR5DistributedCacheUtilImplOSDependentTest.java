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

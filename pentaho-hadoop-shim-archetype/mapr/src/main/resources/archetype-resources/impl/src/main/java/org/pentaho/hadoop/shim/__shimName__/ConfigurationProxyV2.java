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

package ${groupId}.hadoop.shim.${shimName};

import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.security.UserGroupInformation;

import java.io.IOException;

/**
 * User: Dzmitry Stsiapanau Date: 7/22/14 Time: 11:59 AM
 */
public class ConfigurationProxyV2 extends ${groupId}.hadoop.shim.common.ConfigurationProxyV2 {

  protected class JobProxy extends Job {
    private JobProxy( JobConf conf ) throws IOException {
      super( conf );
    }

    void refreshUGI() {
      try {
        this.ugi = UserGroupInformation.getCurrentUser();
      } catch ( IOException e ) {
        throw new RuntimeException( e );
      }
    }
  }

  public ConfigurationProxyV2() throws IOException {
    // create with a null Cluster
    JobConf jobConf = new JobConf( new org.apache.hadoop.conf.Configuration() );
    job = new JobProxy( jobConf );
    job.getConfiguration().addResource( "hdfs-site.xml" );
  }

  public JobConf getJobConf() {
    return (JobConf) getJob().getConfiguration();
  }

  public Job getJob() {
    ( (JobProxy) job ).refreshUGI();
    return job;
  }

}

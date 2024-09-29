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
package ${package};

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyServiceImpl implements MyService {
  private static final Logger logger = LoggerFactory.getLogger(MyServiceImpl.class);

  public String sayHello() {
    logger.debug("saying 'Hello' ...");
    return "Hello";
  }

}
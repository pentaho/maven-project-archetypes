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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyClass {
  private static final Logger logger = LoggerFactory.getLogger(MyClass.class);

  public String sayHello() {
    logger.debug("saying 'Hello' ...");
    return "Hello";
  }

}

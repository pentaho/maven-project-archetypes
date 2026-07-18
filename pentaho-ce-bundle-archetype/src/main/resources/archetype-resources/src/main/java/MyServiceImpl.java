/*! ******************************************************************************
 *
 * Pentaho
 *
 * Copyright (C) 2024 - 2026 by Pentaho Canada Inc. : http://www.pentaho.com
 *
 * Use of this software is governed by the Business Source License included
 * in the LICENSE.TXT file.
 *
 * Change Date: 2030-06-15
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
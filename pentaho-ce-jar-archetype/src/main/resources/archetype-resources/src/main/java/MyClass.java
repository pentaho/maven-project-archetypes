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

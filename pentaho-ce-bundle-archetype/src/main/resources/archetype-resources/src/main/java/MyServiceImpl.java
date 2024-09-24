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
package ${package};

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyServiceImplTest {

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void testSayHello() {
    MyService myService = new MyServiceImpl();
    assertEquals(myService.sayHello(), "Hello");
  }

}

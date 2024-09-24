package ${package};

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyClassTest {

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void testSayHello() {
    MyClass myClass = new MyClass();
    assertEquals(myClass.sayHello(), "Hello");
  }

}

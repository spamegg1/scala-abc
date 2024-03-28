package galileostuff

class GalileoTest extends munit.FunSuite:
  test("checking if subproject 3 works correctly"):
    assertEquals(Galileo.hello, "Hello from Galileo!")

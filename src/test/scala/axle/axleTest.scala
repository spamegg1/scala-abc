package axlestuff

class AxleTest extends munit.FunSuite {
  import Axle._

  test("checking if subproject 2 works correctly") {
    assertEquals(hello, "Hello from Axle!")
  }
}

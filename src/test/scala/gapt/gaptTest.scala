package gaptstuff

class GaptTest extends munit.FunSuite {
  import Gapt._

  test("checking if subproject works correctly") {
    assertEquals(hello, "Hello from Gapt!")
  }
}

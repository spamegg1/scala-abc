package gaptstuff

class GaptTest extends munit.FunSuite:
  test("checking if subproject works correctly"):
    assertEquals(Gapt.hello, "Hello from Gapt!")

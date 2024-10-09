package C.w2

class HW7Suite extends munit.FunSuite:
  /*  constants for testing */
  val (zero, one, two, three, four, five, six, seven, ten) = (
    0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 10.0
  )

  val emptyEnv = Map[String, GeoVal]()

  test("01. Point"):
    /*  initialization */
    val p = Point(three, five)
    assertEquals(p.x, three)
    assertEquals(p.y, five)

    val e1 = p.evalProg(emptyEnv)

    val e2 = p.preprocessProg
    assert(p == e2)

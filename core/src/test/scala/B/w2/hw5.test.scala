package B.w2

class HW5Suite extends munit.FunSuite:
  import MUPL.*

  /*   basic MUPL function */
  val muplFun = Fun(None, "x", Add(Var("x"), Integer(7)))

  /*  recursive MUPL function */
  val muplLength = Fun(
    Some("length"),
    "x",
    ifAunit(
      Var("x"),
      Integer(0),
      Add(
        Integer(1),
        Call(Var("length"), Second(Var("x")))
      )
    )
  )

  /*  MUPL list with 3 elements */
  val muplList = Apair(Integer(1), Apair(Integer(2), Apair(Integer(3), Aunit)))

  /*  nested list */
  val nestedList = Apair(muplList, Apair(muplList, Aunit))

  /*  same list with "add 7" mapped over it */
  val plusSeven =
    Apair(Integer(8), Apair(Integer(9), Apair(Integer(10), Aunit)))

  /*  for convenience */
  val emptyEnv = Map[String, MUPL]()

  test("01. evalUnderEnv: Fun") {
    val obtained = evalUnderEnv(muplFun, emptyEnv)
    val expected = Closure(emptyEnv, muplFun)
    assertEquals(obtained, expected)
  }

  test("02. evalUnderEnv: Var") {
    val varName = Var("x")
    val env = Map[String, MUPL]("x" -> Integer(5))
    val obtained = evalUnderEnv(varName, env)
    val expected = Integer(5)
    assertEquals(obtained, expected)
  }

  test("03. evalUnderEnv: Add") {
    val env = Map[String, MUPL]("x" -> Integer(5))
    val add = Add(Integer(3), Var("x"))
    val obtained = evalUnderEnv(add, env)
    val expected = Integer(8)
    assertEquals(obtained, expected)
  }

  test("04. evalUnderEnv: IfGreater") {
    val env = Map[String, MUPL]("x" -> Integer(5), "y" -> Aunit)
    val ifg = IfGreater(Integer(3), Var("x"), Var("x"), Var("y"))
    val obtained = evalUnderEnv(ifg, env)
    val expected = Aunit
    assertEquals(obtained, expected)
  }

  test("05. evalUnderEnv: Mlet") {
    val mlet = Mlet("x", Integer(1), Add(Integer(5), Var("x")))
    val obtained = evalUnderEnv(mlet, emptyEnv)
    val expected = Integer(6)
    assertEquals(obtained, expected)
  }

  test("06. evalUnderEnv: Apair") {
    val apair = Apair(Add(Integer(1), Var("y")), Add(Integer(3), Var("x")))
    val env = Map("x" -> Integer(4), "y" -> Integer(2))
    val obtained = evalUnderEnv(apair, env)
    val expected = Apair(Integer(3), Integer(7))
    assertEquals(obtained, expected)
  }

  test("07. evalUnderEnv: First") {
    val first = First(
      Apair(Add(Integer(1), Var("y")), Add(Integer(3), Var("x")))
    )
    val env = Map("x" -> Integer(4), "y" -> Integer(2))
    val obtained = evalUnderEnv(first, env)
    val expected = Integer(3)
    assertEquals(obtained, expected)
  }

  test("08. evalUnderEnv: Second") {
    val second = Second(
      Apair(Add(Integer(1), Var("y")), Add(Integer(3), Var("x")))
    )
    val env = Map("x" -> Integer(4), "y" -> Integer(2))
    val obtained = evalUnderEnv(second, env)
    val expected = Integer(7)
    assertEquals(obtained, expected)
  }

  test("09. evalUnderEnv: IsAunit") {
    val env = emptyEnv
    val isaunit = IsAunit(Closure(env, Fun(None, "x", Aunit)))
    val obtained = evalUnderEnv(isaunit, env)
    val expected = Integer(0)
    assertEquals(obtained, expected)
  }

  test("10. evalUnderEnv: Call") {
    val fun = Fun(None, "x", Add(Var("x"), Integer(5)))
    val cls = Closure(emptyEnv, fun)
    val arg = Integer(1)
    val call = Call(cls, arg)
    val obtained = evalUnderEnv(call, emptyEnv)
    val expected = Integer(6)
    assertEquals(obtained, expected)
  }

  test("11. evalUnderEnv: recursive Call") {
    val call = Call(muplLength, muplList)
    val obtained = evalUnderEnv(call, emptyEnv)
    val expected = Integer(3)
    assertEquals(obtained, expected)
  }

  test("12. evalUnderEnv: Aunit") {
    val obtained = evalUnderEnv(Aunit, Map[String, MUPL]())
    val expected = Aunit
    assertEquals(obtained, expected)
  }

  test("13. evalUnderEnv: Integer") {
    val obtained = evalUnderEnv(Integer(3), emptyEnv)
    val expected = Integer(3)
    assertEquals(obtained, expected)
  }

  test("14. evalUnderEnv: Closure") {
    val obtained = evalUnderEnv(Closure(emptyEnv, muplLength), emptyEnv)
    val expected = Closure(emptyEnv, muplLength)
    assertEquals(obtained, expected)
  }

  test("15. evalExp: muplMap") {
    val obtained = evalExp(Call(Call(muplMap, muplFun), muplList))
    assertEquals(obtained, plusSeven)
  }

  test("16. evalExp: recursive muplMap") {
    val obtained = evalExp(Call(Call(muplMap, muplLength), nestedList))
    val expected = Apair(Integer(3), Apair(Integer(3), Aunit))
    assertEquals(obtained, expected)
  }

  test("17. evalExp: muplMapAddN and toScalaList") {
    val addSeven = Call(muplMapAddN, Integer(7))
    val addedList = Call(addSeven, muplList)
    val obtained = toScalaList(evalExp(addedList))
    val expected = List(Integer(8), Integer(9), Integer(10))
    assertEquals(obtained, expected)
  }

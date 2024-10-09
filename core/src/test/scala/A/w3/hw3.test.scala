package A.w3

class HW3Suite extends munit.FunSuite:
  import Pattern.*, Value.*

  test("01. onlyCapitals"):
    val inputs: List[List[String]] = List(
      List("hello", "Spam", "egg")
      // add more test cases here!
    )
    val expected: List[List[String]] = List(
      List("Spam")
      // add more test cases here!
    )
    assertEquals(inputs map onlyCapitals, expected)

  test("02. longestString1"):
    val inputs: List[List[String]] = List(
      List("hello", "Spam", "egg")
      // add more test cases here!
    )
    val expected: List[String] = List(
      "hello"
      // add more test cases here!
    )
    assertEquals(inputs map longestString1, expected)

  test("03. longestString2"):
    val inputs: List[List[String]] = List(
      List("Spam", "eggs")
      // add more test cases here!
    )
    val expected: List[String] = List(
      "eggs"
      // add more test cases here!
    )
    assertEquals(inputs map longestString2, expected)

  test("04. longestStringHelper"):
    val funs: List[(Int, Int) => Boolean] = List(
      (x, y) => x > y,
      (x, y) => x % 2 == 1 && y % 2 == 0
      // add more functions here!
    )
    val hofs: List[List[String] => String] = funs map longestStringHelper
    val inputs: List[List[String]] = List(
      List("Spam", "egg"),
      List("hello", "spamegg")
      // add more test cases here!
    )
    val applied: List[List[String]] = hofs map (hof => (inputs map hof))
    val expected: List[List[String]] = List(
      // this is for the first function in funs
      List(
        "Spam", // this is for the first list in inputs
        "spamegg" // this is for the first list in inputs
        // add more outputs here if you add more inputs above!
      ),
      // this is for the second function in funs
      List(
        "egg", // this is for the first list in inputs
        "spamegg" // this is for the second list in inputs
        // add more outputs here if you add more inputs above!
      )
      // add more results for more functions above!
    )
    assertEquals(applied, expected)

  test("05. longestString3"):
    val inputs: List[List[String]] = List(
      List("hello", "Spam", "egg")
      // add more test cases here!
    )
    val expected: List[String] = List(
      "hello"
      // add more test cases here!
    )
    assertEquals(inputs map longestString3, expected)

  test("06. longestString4"):
    val inputs: List[List[String]] = List(
      List("Spam", "eggs")
      // add more test cases here!
    )
    val expected: List[String] = List(
      "eggs"
      // add more test cases here!
    )
    assertEquals(inputs map longestString4, expected)

  test("07. longestCapitalized"):
    val inputs: List[List[String]] = List(
      List("hello", "Spam", "egg")
      // add more test cases here!
    )
    val expected: List[String] = List(
      "Spam"
      // add more test cases here!
    )
    assertEquals(inputs map longestCapitalized, expected)

  test("08. revString"):
    val inputs: List[String] = List(
      "Spam"
      // add more test cases here!
    )
    val expected: List[String] = List(
      "mapS"
      // add more test cases here!
    )
    assertEquals(inputs map revString, expected)

  test("09. firstAnswer"):
    val funs: List[Int => Option[Int]] =
      List(x => if x > 0 then Some(x) else None
      // add more functions here!
      )
    val hofs: List[List[Int] => Int] = funs map firstAnswer
    val inputs: List[List[Int]] = List(
      List(-1, 0, 1)
      // add more test cases here!
    )
    val applied: List[List[Int]] = hofs map (hof => (inputs map hof))
    val expected: List[List[Int]] = List(
      // this is for the first function in funs
      List(
        1 // this is for the first list in inputs
        // add more outputs here if you add more inputs above!
      )
      // add more results for more functions above!
    )
    assertEquals(applied, expected)

  test("10. allAnswers"):
    val funs: List[Int => Option[List[Int]]] =
      List(x => if x > 1 then Some(List(x, x + 1)) else None
      // add more functions here!
      )
    val hofs: List[List[Int] => Option[List[Int]]] = funs map allAnswers
    val inputs: List[List[Int]] = List(
      List(2, 3)
      // add more test cases here!
    )
    val applied: List[List[Option[List[Int]]]] =
      hofs map (hof => (inputs map hof))
    val expected: List[List[Option[List[Int]]]] = List(
      // this is for the first function in funs
      List(
        Some(List(2, 3, 3, 4)) // this is for the first list in inputs
        // add more outputs here if you add more inputs above!
      )
      // add more results for more functions above!
    )
    assertEquals(applied, expected)

  /* Some patterns and values for testing */
  val pat1 = Wildcard
  val pat2 = Variable("pat2")
  val pat3 = UnitP
  val pat4 = ConstP(17)
  val pat5 = TupleP(List(pat1, pat2, pat3, pat4))
  val pat6 = ConstructorP("pat5", pat5)
  val pat7 = TupleP(
    List(
      pat1,
      TupleP(List(pat5, pat2)),
      pat2,
      pat6,
      TupleP(List(pat6, pat3, pat4)),
      pat6
    )
  )
  val val5 = Tuple(List(Const(1), Const(2), Unit, Const(17)))

  test("11. countWildcards"):
    val inputs: List[Pattern] = List(
      pat7
      // add more test cases here!
    )
    val expected: List[Int] = List(
      5
      // add more test cases here!
    )
    assertEquals(inputs map countWildcards, expected)

  test("12. countWildAndVariableLengths"):
    val inputs: List[Pattern] = List(
      pat5
      // add more test cases here!
    )
    val expected: List[Int] = List(
      5
      // add more test cases here!
    )
    assertEquals(inputs map countWildAndVariableLengths, expected)

  test("13. countSomeVar"):
    val inputs: List[(String, Pattern)] = List(
      ("pat2", pat7)
      // add more test cases here!
    )
    val expected: List[Int] = List(
      6
      // add more test cases here!
    )
    assertEquals(inputs map countSomeVar, expected)

  test("14. checkPat"):
    val inputs: List[Pattern] = List(
      pat5,
      pat7
      // add more test cases here!
    )
    val expected: List[Boolean] = List(
      true,
      false
      // add more test cases here!
    )
    assertEquals(inputs map checkPat, expected)

  test("15. patMatch"):
    val inputs: List[(Value, Pattern)] = List(
      (val5, pat5)
      // add more test cases here!
    )
    val expected: List[Option[List[(String, Value)]]] = List(
      Some(List(("pat2", Const(2))))
      // add more test cases here!
    )
    assertEquals(inputs map patMatch, expected)

  test("16. firstMatch"):
    val values: List[Value] = List(
      Unit
      // add more test cases here!
    )
    val patLists: List[List[Pattern]] = List(
      List(UnitP)
      // add more test cases here!
    )
    val inputs: List[(Value, List[Pattern])] = values zip patLists
    val expected: List[Option[List[(String, Value)]]] = List(
      Some(Nil)
      // add more test cases here!
    )
    assertEquals(inputs map ((v, pl) => firstMatch(v)(pl)), expected)

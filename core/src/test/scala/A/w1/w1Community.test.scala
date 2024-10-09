package A.w1

class Week1CommunitySuite extends munit.FunSuite:
  test("01. alternate"):
    val inputs: List[List[Int]] = List(
      List(1, 2, 3, 4)
      // add more test cases here!
    )
    val expected: List[Int] = List(
      -2
      // add more test cases here!
    )
    assertEquals(inputs map alternate, expected)

  test("02. minMax"):
    val inputs: List[List[Int]] = List(
      List(1, 2, 3)
      // add more test cases here!
    )
    val expected: List[(Int, Int)] = List(
      (1, 3)
      // add more test cases here!
    )
    assertEquals(inputs map minMax, expected)

  test("03. cumsum"):
    val inputs: List[List[Int]] = List(
      List(1, 2, 3)
      // add more test cases here!
    )
    val expected: List[List[Int]] = List(
      List(1, 3, 6)
      // add more test cases here!
    )
    assertEquals(inputs map cumsum, expected)

  test("04. greeting"):
    val inputs: List[Option[String]] = List(
      Some("spam")
      // add more test cases here!
    )
    val expected: List[String] = List(
      "Hello there, spam!"
      // add more test cases here!
    )
    assertEquals(inputs map greeting, expected)

  test("05. myRepeat"):
    val inputs: List[(List[Int], List[Int])] = List(
      (List(1, 2, 3), List(4, 0, 3))
      // add more test cases here!
    )
    val expected: List[List[Int]] = List(
      List(1, 1, 1, 1, 3, 3, 3)
      // add more test cases here!
    )
    assertEquals(inputs map myRepeat, expected)

  test("06. addOpt"):
    val inputs: List[(Option[Int], Option[Int])] = List(
      (None, None)
      // add more test cases here!
    )
    val expected: List[Option[Int]] = List(
      None
      // add more test cases here!
    )
    assertEquals(inputs map addOpt, expected)

  test("07. addAllOpt"):
    val inputs: List[List[Option[Int]]] = List(
      List(None, Some(1))
      // add more test cases here!
    )
    val expected: List[Option[Int]] = List(
      Some(1)
      // add more test cases here!
    )
    assertEquals(inputs map addAllOpt, expected)

  test("08. any"):
    val inputs: List[List[Boolean]] = List(
      List()
      // add more test cases here!
    )
    val expected: List[Boolean] = List(
      false
      // add more test cases here!
    )
    assertEquals(inputs map any, expected)

  test("09. all"):
    val inputs: List[List[Boolean]] = List(
      List()
      // add more test cases here!
    )
    val expected: List[Boolean] = List(
      true
      // add more test cases here!
    )
    assertEquals(inputs map all, expected)

  test("10. myZip"):
    val inputs: List[(List[Int], List[Int])] = List(
      (List(1, 2), List(3))
      // add more test cases here!
    )
    val expected: List[List[(Int, Int)]] = List(
      List((1, 3))
      // add more test cases here!
    )
    assertEquals(inputs map myZip, expected)

  test("11. zipRecycle"):
    val inputs: List[(List[Int], List[Int])] = List(
      (List(1, 2), List(3))
      // add more test cases here!
    )
    val expected: List[List[(Int, Int)]] = List(
      List((1, 3), (2, 3))
      // add more test cases here!
    )
    assertEquals(inputs map zipRecycle, expected)

  test("12. zipOpt"):
    val inputs: List[(List[Int], List[Int])] = List(
      (List(1), List(2, 3))
      // add more test cases here!
    )
    val expected: List[Option[List[(Int, Int)]]] = List(
      None
      // add more test cases here!
    )
    assertEquals(inputs map zipOpt, expected)

  test("13. lookup"):
    val inputs: List[(List[(String, Int)], String)] = List(
      (List(("spam", 42)), "spam")
      // add more test cases here!
    )
    val expected: List[Option[Int]] = List(
      Some(42)
      // add more test cases here!
    )
    assertEquals(inputs map lookup, expected)

  test("14. splitUp"):
    val inputs: List[List[Int]] = List(
      List(1, -2, 3)
      // add more test cases here!
    )
    val expected: List[(List[Int], List[Int])] = List(
      (List(1, 3), List(-2))
      // add more test cases here!
    )
    assertEquals(inputs map splitUp, expected)

  test("15. mySplitAt"):
    val inputs: List[(List[Int], Int)] = List(
      (List(1, 2, 3), 2)
      // add more test cases here!
    )
    val expected: List[(List[Int], List[Int])] = List(
      (List(2, 3), List(1))
      // add more test cases here!
    )
    assertEquals(inputs map mySplitAt, expected)

  test("16. isSorted"):
    val inputs: List[List[Int]] = List(
      List(1, 2, 3)
      // add more test cases here!
    )
    val expected: List[Boolean] = List(
      true
      // add more test cases here!
    )
    assertEquals(inputs map isSorted, expected)

  test("17. isAnySorted"):
    val inputs: List[List[Int]] = List(
      List(3, 2, 1)
      // add more test cases here!
    )
    val expected: List[Boolean] = List(
      true
      // add more test cases here!
    )
    assertEquals(inputs map isAnySorted, expected)

  test("18. sortedMerge"):
    val inputs: List[(List[Int], List[Int])] = List(
      (List(1, 3), List(2, 4))
      // add more test cases here!
    )
    val expected: List[List[Int]] = List(
      List(1, 2, 3, 4)
      // add more test cases here!
    )
    assertEquals(inputs map sortedMerge, expected)

  test("19. qsort"):
    val inputs: List[List[Int]] = List(
      List(3, 2, 1)
      // add more test cases here!
    )
    val expected: List[List[Int]] = List(
      List(1, 2, 3)
      // add more test cases here!
    )
    assertEquals(inputs map qsort, expected)

  test("20. divide"):
    val inputs: List[List[Int]] = List(
      List(1, 2, 3)
      // add more test cases here!
    )
    val expected: List[(List[Int], List[Int])] = List(
      (List(1, 3), List(2))
      // add more test cases here!
    )
    assertEquals(inputs map divide, expected)

  test("21. notSoQuickSort"):
    val inputs: List[List[Int]] = List(
      List(3, 1, 2)
      // add more test cases here!
    )
    val expected: List[List[Int]] = List(
      List(1, 2, 3)
      // add more test cases here!
    )
    assertEquals(inputs map notSoQuickSort, expected)

  test("22. fullDivide"):
    val inputs: List[(Int, Int)] = List(
      (3, 9)
      // add more test cases here!
    )
    val expected: List[(Int, Int)] = List(
      (2, 1)
      // add more test cases here!
    )
    assertEquals(inputs map fullDivide, expected)

  test("23. factorize"):
    val inputs: List[Int] = List(
      20
      // add more test cases here!
    )
    val expected: List[List[(Int, Int)]] = List(
      List((2, 2), (5, 1))
      // add more test cases here!
    )
    assertEquals(inputs map factorize, expected)

  test("24. multiply"):
    val inputs: List[List[(Int, Int)]] = List(
      List((2, 2), (5, 1))
      // add more test cases here!
    )
    val expected: List[Int] = List(
      20
      // add more test cases here!
    )
    assertEquals(inputs map multiply, expected)

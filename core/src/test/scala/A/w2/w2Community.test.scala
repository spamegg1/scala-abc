package A.w2

class Week2CommunitySuite extends munit.FunSuite:
  import Flag.*, IntSet.*, Tree.*, PassFail.*, Nat.*

  test("01. passOrFail"):
    val inputs: List[FinalGrade] = List(
      FinalGrade(id = 10, grade = Some(20))
      // add more test cases here!
    )
    val expected: List[PassFail] = List(
      Fail
      // add more test cases here!
    )
    assertEquals(inputs map passOrFail, expected)

  test("02. hasPassed"):
    val inputs: List[FinalGrade] = List(
      FinalGrade(id = 10, grade = Some(20))
      // add more test cases here!
    )
    val expected: List[Boolean] = List(
      false
      // add more test cases here!
    )
    assertEquals(inputs map hasPassed, expected)

  test("03. numberPassed"):
    val inputs: List[List[FinalGrade]] = List(
      List(FinalGrade(10, Some(20)), FinalGrade(6, Some(80)))
      // add more test cases here!
    )
    val expected: List[Int] = List(
      1
      // add more test cases here!
    )
    assertEquals(inputs map numberPassed, expected)

  test("04. numberMisgraded"):
    val inputs: List[List[(PassFail, FinalGrade)]] = List(
      List(
        (Fail, FinalGrade(grade = Some(80), id = 6)),
        (Pass, FinalGrade(grade = Some(20), id = 10))
      )
      // add more test cases here!
    )
    val expected: List[Int] = List(
      2
      // add more test cases here!
    )
    assertEquals(inputs map numberMisgraded, expected)

  test("05. treeHeight"):
    val inputs: List[Tree[PassFail]] = List(
      Leaf
      // add more test cases here!
    )
    val expected: List[Int] = List(
      0
      // add more test cases here!
    )
    assertEquals(inputs map treeHeight, expected)

  test("06. sumTree"):
    val inputs: List[Tree[Int]] = List(
      Node(4, Leaf, Leaf)
      // add more test cases here!
    )
    val expected: List[Int] = List(
      4
      // add more test cases here!
    )
    assertEquals(inputs map sumTree, expected)

  test("07. gardener"):
    val inputs: List[Tree[Flag]] = List(
      Leaf
      // add more test cases here!
    )
    val expected: List[Tree[Flag]] = List(
      Leaf
      // add more test cases here!
    )
    assertEquals(inputs map gardener, expected)

  test("08. isPositive"):
    val inputs: List[Nat] = List(
      Zero
      // add more test cases here!
    )
    val expected: List[Boolean] = List(
      false
      // add more test cases here!
    )
    assertEquals(inputs map isPositive, expected)

  test("09. pred"):
    val inputs: List[Nat] = List(
      Succ(Zero)
      // add more test cases here!
    )
    val expected: List[Nat] = List(
      Zero
      // add more test cases here!
    )
    assertEquals(inputs map pred, expected)

  test("10. natToInt"):
    val inputs: List[Nat] = List(
      Zero
      // add more test cases here!
    )
    val expected: List[Int] = List(
      0
      // add more test cases here!
    )
    assertEquals(inputs map natToInt, expected)

  test("11. intToNat"):
    val inputs: List[Int] = List(
      1
      // add more test cases here!
    )
    val expected: List[Nat] = List(
      Succ(Zero)
      // add more test cases here!
    )
    assertEquals(inputs map intToNat, expected)

  test("12. add"):
    val inputs: List[(Nat, Nat)] = List(
      (Zero, Succ(Zero))
      // add more test cases here!
    )
    val expected: List[Nat] = List(
      Succ(Zero)
      // add more test cases here!
    )
    assertEquals(inputs map add, expected)

  test("13. sub"):
    val inputs: List[(Nat, Nat)] = List(
      (Succ(Zero), Succ(Zero))
      // add more test cases here!
    )
    val expected: List[Nat] = List(
      Zero
      // add more test cases here!
    )
    assertEquals(inputs map sub, expected)

  test("14. mult"):
    val inputs: List[(Nat, Nat)] = List(
      (Succ(Zero), Succ(Zero))
      // add more test cases here!
    )
    val expected: List[Nat] = List(
      Succ(Zero)
      // add more test cases here!
    )
    assertEquals(inputs map mult, expected)

  test("15. lessThan"):
    val inputs: List[(Nat, Nat)] = List(
      (Succ(Zero), Succ(Zero))
      // add more test cases here!
    )
    val expected: List[Boolean] = List(
      false
      // add more test cases here!
    )
    assertEquals(inputs map lessThan, expected)

  test("16. doesInclude"):
    val inputs: List[(List[Int], Int)] = List(
      (List(1), 1)
      // add more test cases here!
    )
    val expected: List[Boolean] = List(
      true
      // add more test cases here!
    )
    assertEquals(inputs map doesInclude, expected)

  test("17. intersect"):
    val inputs: List[(List[Int], List[Int])] = List(
      (List(1), List(2))
      // add more test cases here!
    )
    val expected: List[List[Int]] = List(
      Nil
      // add more test cases here!
    )
    assertEquals(inputs map intersect, expected)

  test("18. union"):
    val inputs: List[(List[Int], List[Int])] = List(
      (List(1), List(2))
      // add more test cases here!
    )
    val expected: List[List[Int]] = List(
      List(1, 2)
      // add more test cases here!
    )
    assertEquals(inputs map union, expected)

  test("19. range"):
    val inputs: List[(Int, Int)] = List(
      (3, 5)
      // add more test cases here!
    )
    val expected: List[List[Int]] = List(
      List(3, 4, 5)
      // add more test cases here!
    )
    assertEquals(inputs map range, expected)

  test("20. filterDuplicate"):
    val inputs: List[List[Int]] = List(
      List(1, 2, 1)
      // add more test cases here!
    )
    val expected: List[List[Int]] = List(
      List(2, 1)
      // add more test cases here!
    )
    assertEquals(inputs map filterDuplicate, expected)

  test("21. contains"):
    val inputs: List[(IntSet, Int)] = List(
      (Elems(List(1, 2, 3)), 2)
      // add more test cases here!
    )
    val expected: List[Boolean] = List(
      true
      // add more test cases here!
    )
    assertEquals(inputs map contains, expected)

  test("22. toList"):
    val inputs: List[IntSet] = List(
      Elems(List(1, 2, 3))
      // add more test cases here!
    )
    val expected: List[List[Int]] = List(
      List(3, 2, 1)
      // add more test cases here!
    )
    assertEquals(inputs map toList, expected)

  test("23. isEmpty"):
    val inputs: List[IntSet] = List(
      Elems(Nil)
      // add more test cases here!
    )
    val expected: List[Boolean] = List(
      true
      // add more test cases here!
    )
    assertEquals(inputs map isEmpty, expected)

package curriculum
package pla
package w1

class HW1Suite extends munit.FunSuite:
  test("01. isOlder"):
    val inputs: List[(Date, Date)] = List(
      ((1, 2, 3), (2, 3, 4))
      // add more test cases here!
    )
    val expected: List[Boolean] = List(
      true
      // add more test cases here!
    )
    assertEquals(inputs map isOlder, expected)

  test("02. numberInMonth"):
    val inputs: List[(List[Date], Int)] = List(
      (List((2012, 2, 28), (2013, 12, 1)), 2)
      // add more test cases here!
    )
    val expected: List[Int] = List(
      1
      // add more test cases here!
    )
    assertEquals(inputs map numberInMonth, expected)

  test("03. numberInMonths"):
    val inputs: List[(List[Date], List[Int])] = List(
      (List((2012, 2, 28), (2013, 12, 1), (2011, 4, 28)), List(2))
      // add more test cases here!
    )
    val expected: List[Int] = List(
      1
      // add more test cases here!
    )
    assertEquals(inputs map numberInMonths, expected)

  test("04. datesInMonth"):
    val inputs: List[(List[Date], Int)] = List(
      (List((2012, 2, 28), (2013, 12, 1)), 2)
      // add more test cases here!
    )
    val expected: List[List[Date]] = List(
      List((2012, 2, 28))
      // add more test cases here!
    )
    assertEquals(inputs map datesInMonth, expected)

  test("05. datesInMonths"):
    val inputs: List[(List[Date], List[Int])] = List(
      (List((2012, 2, 28), (2013, 12, 1)), List(2))
      // add more test cases here!
    )
    val expected: List[List[Date]] = List(
      List((2012, 2, 28))
      // add more test cases here!
    )
    assertEquals(inputs map datesInMonths, expected)

  test("06. getNth"):
    val inputs: List[(List[String], Int)] = List(
      (List("hi", "there", "how", "are", "you"), 2)
      // add more test cases here!
    )
    val expected: List[String] = List(
      "there"
      // add more test cases here!
    )
    assertEquals(inputs map getNth, expected)

  test("07. dateToString"):
    val inputs: List[Date] = List(
      (2013, 6, 1)
      // add more test cases here!
    )
    val expected: List[String] = List(
      "June 1, 2013"
      // add more test cases here!
    )
    assertEquals(inputs map dateToString, expected)

  test("08. numberBeforeReachingSum"):
    val inputs: List[(Int, List[Int])] = List(
      (5, List(1, 2, 3))
      // add more test cases here!
    )
    val expected: List[Int] = List(
      2
      // add more test cases here!
    )
    assertEquals(inputs map numberBeforeReachingSum, expected)

  test("09. whatMonth"):
    val inputs: List[Int] = List(
      120
      // add more test cases here!
    )
    val expected: List[Int] = List(
      4
      // add more test cases here!
    )
    assertEquals(inputs map whatMonth, expected)

  test("10. monthRange"):
    val inputs: List[(Int, Int)] = List(
      (31, 32)
      // add more test cases here!
    )
    val expected: List[List[Int]] = List(
      List(1, 2)
      // add more test cases here!
    )
    assertEquals(inputs map monthRange, expected)

  test("11. oldest"):
    val inputs: List[List[Date]] = List(
      List((2012, 2, 28), (2011, 3, 31))
      // add more test cases here!
    )
    val expected: List[Option[Date]] = List(
      Some((2011, 3, 31))
      // add more test cases here!
    )
    assertEquals(inputs map oldest, expected)

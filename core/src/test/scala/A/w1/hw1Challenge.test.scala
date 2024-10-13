package curriculum
package pla
package w1

class HW1ChallengeSuite extends munit.FunSuite:
  def check[T, S](inputs: List[T], expected: List[S], fun: T => S): Unit =
    assertEquals(inputs map fun, expected)

  test("01. numberInMonthsChallenge"):
    val inputs: List[(List[Date], List[Int])] = List(
      (List((2012, 2, 28), (2013, 12, 1), (2011, 4, 28)), List(2))
      // add more test cases here!
    )
    val expected: List[Int] = List(
      1
      // add more test cases here!
    )
    check[(List[Date], List[Int]), Int](
      inputs,
      expected,
      numberInMonthsChallenge
    )

  test("02. datesInMonthsChallenge"):
    val inputs: List[(List[Date], List[Int])] = List(
      (List((2012, 2, 28), (2013, 12, 1)), List(2))
      // add more test cases here!
    )
    val expected: List[List[Date]] = List(
      List((2012, 2, 28))
      // add more test cases here!
    )
    check[(List[Date], List[Int]), List[Date]](
      inputs,
      expected,
      datesInMonthsChallenge
    )

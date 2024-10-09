package A.w2

class HW2Suite extends munit.FunSuite:
  import Suit.*, Rank.*, Color.*, Move.*

  test("01. allExceptOption"):
    val inputs: List[(String, List[String])] = List(
      ("hello", List("hello"))
      // add more test cases here!
    )
    val expected: List[Option[List[String]]] = List(
      Some(Nil)
      // add more test cases here!
    )
    assertEquals(inputs map allExceptOption, expected)

  test("02. getSubst1"):
    val inputs: List[(List[List[String]], String)] = List(
      (
        List(
          List("Fred", "Fredrick"),
          List("Elizabeth", "Betty"),
          List("Freddie", "Fred", "F")
        ),
        "Fred"
      )
      // add more test cases here!
    )
    val expected: List[List[String]] = List(
      List("Fredrick", "Freddie", "F")
      // add more test cases here!
    )
    assertEquals(inputs map getSubst1, expected)

  test("03. getSubst2"):
    val inputs: List[(List[List[String]], String)] = List(
      (
        List(
          List("Fred", "Fredrick"),
          List("Elizabeth", "Betty"),
          List("Freddie", "Fred", "F")
        ),
        "Fred"
      )
      // add more test cases here!
    )
    val expected: List[List[String]] = List(
      List("Fredrick", "Freddie", "F")
      // add more test cases here!
    )
    assertEquals(inputs map getSubst2, expected)

  test("04. similarNames"):
    val inputs: List[(List[List[String]], Fullname)] = List(
      (
        List(
          List("Fred", "Fredrick"),
          List("Elizabeth", "Betty"),
          List("Freddie", "Fred", "F")
        ),
        Fullname("Fred", "W", "Smith")
      )
      // add more test cases here!
    )
    val expected: List[List[Fullname]] = List(
      List(
        Fullname("Fred", "W", "Smith"),
        Fullname("Fredrick", "W", "Smith"),
        Fullname("Freddie", "W", "Smith"),
        Fullname("F", "W", "Smith")
      )
      // add more test cases here!
    )
    assertEquals(inputs map similarNames, expected)

  test("05. cardColor"):
    val inputs: List[Card] = List(
      (Hearts, Ace)
      // add more test cases here!
    )
    val expected: List[Color] = List(
      Red
      // add more test cases here!
    )
    assertEquals(inputs map cardColor, expected)

  test("06. cardValue"):
    val inputs: List[Card] = List(
      (Hearts, Ace)
      // add more test cases here!
    )
    val expected: List[Int] = List(
      11
      // add more test cases here!
    )
    assertEquals(inputs map cardValue, expected)

  test("07. removeCard"):
    val inputs: List[(List[Card], Card)] = List(
      (List((Hearts, Ace)), (Hearts, Ace))
      // add more test cases here!
    )
    val expected: List[List[Card]] = List(
      List()
      // add more test cases here!
    )
    assertEquals(inputs map removeCard, expected)

  test("07. removeCard throws IllegalMove"):
    try
      removeCard(List(), (Hearts, Ace))
      fail("IllegalMove was not thrown, not OK")
    catch case _: IllegalMove => () // OK

  test("08. allSameColor"):
    val inputs: List[List[Card]] = List(
      List((Hearts, Ace), (Diamonds, Jack))
      // add more test cases here!
    )
    val expected: List[Boolean] = List(
      true
      // add more test cases here!
    )
    assertEquals(inputs map allSameColor, expected)

  test("09. sumCards"):
    val inputs: List[List[Card]] = List(
      List((Clubs, Ace), (Spades, Num(2)))
      // add more test cases here!
    )
    val expected: List[Int] = List(
      13
      // add more test cases here!
    )
    assertEquals(inputs map sumCards, expected)

  test("10. score"):
    val inputs: List[(List[Card], Int)] = List(
      (List((Hearts, Num(2)), (Clubs, Num(4))), 10)
      // add more test cases here!
    )
    val expected: List[Int] = List(
      4
      // add more test cases here!
    )
    assertEquals(inputs map score, expected)

  test("11. officiate"):
    val inputs: List[(List[Card], List[Move], Int)] = List(
      (List((Hearts, Num(2)), (Clubs, Num(4))), List(Draw), 15)
      // add more test cases here!
    )
    val expected: List[Int] = List(
      6
      // add more test cases here!
    )
    assertEquals(inputs map officiate, expected)

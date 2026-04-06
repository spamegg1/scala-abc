package complex
package finalExam

class TAScheduleSuite extends munit.FunSuite:
  val spam = TA("Spam", 2, List(Slot(1), Slot(3))) /*  set-up TAs for testing  */
  val ham = TA("Ham", 1, List(Slot(3), Slot(4)))
  val egg = TA("Egg", 1, List(Slot(2)))
  val spammers = List(spam, ham, egg)

  test("01. solve"):
    val inputs: List[(TAs, Slots)] = List(
      (Nil, Nil),
      (Nil, List(Slot(1))),
      (List(spam), Nil),
      (List(spam), List(Slot(1))),
      (List(spam), List(Slot(1), Slot(2))),
      (List(spam), List(Slot(1), Slot(3))),
      (List(ham), List(Slot(3))),
      (List(ham), List(Slot(4))),
      (List(ham), List(Slot(3), Slot(4))),
      (List(spam, ham), List(Slot(3))),
      (List(spam, ham), List(Slot(3), Slot(4))),
      (spammers, List(Slot(2), Slot(3))),
      (spammers, List(Slot(1), Slot(2), Slot(3), Slot(4)))
    )

    val expected: List[Option[Schedule]] = List(
      None,
      None,
      None,
      Some(Map(Slot(1) -> spam)),
      None,
      Some(Map(Slot(1) -> spam, Slot(3) -> spam)),
      Some(Map(Slot(3) -> ham)),
      Some(Map(Slot(4) -> ham)),
      None,
      Some(Map(Slot(3) -> spam)),
      Some(Map(Slot(3) -> spam, Slot(4) -> ham)),
      Some(Map(Slot(2) -> egg, Slot(3) -> spam)),
      Some(Map(Slot(1) -> spam, Slot(2) -> egg, Slot(3) -> spam, Slot(4) -> ham))
    )

    assertEquals(inputs map solve, expected)

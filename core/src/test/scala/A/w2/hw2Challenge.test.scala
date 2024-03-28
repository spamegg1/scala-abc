package A.w2

import org.scalacheck.{Gen, Test, Prop}
import org.scalacheck.Prop.forAll

import Suit.*, Rank.*, Color.*, Move.*

class HW2ChallengeSuite extends munit.FunSuite:
  test("01. scoreChallenge") {
    val inputs: List[(List[Card], Int)] = List(
      (List((Hearts, Ace), (Diamonds, Ace), (Clubs, Ace), (Spades, Ace)), 10)
      // add more test cases here!
    )
    val expected: List[Int] = List(
      List(6, 12, 42, 72, 102).min
        // add more test cases here!
    )
    assertEquals(inputs map scoreChallenge, expected)
  }

  test("02. officiateChallenge") {
    val inputs: List[(List[Card], List[Move], Int)] = List(
      (List((Hearts, Ace), (Clubs, Ace)), List(Draw, Draw), 15)
      // add more test cases here!
    )
    val expected: List[Int] = List(
      3
      // add more test cases here!
    )
    assertEquals(inputs map officiateChallenge, expected)
  }

  /*  Properties  */
  type Property = (GameState, List[Move]) => Boolean

  /*  The value of the held cards never exceeds the goal. */
  val prop1: Property = (st: GameState, moves: List[Move]) =>
    val (hand, _, goal, _) = st
    sumCards(hand) <= goal

  /** A card is drawn whenever the goal is more than 10 greater than the value
    * of the held cards.
    */
  val prop2: Property = (st: GameState, moves: List[Move]) =>
    val (hand, _, goal, _) = st
    goal - sumCards(hand) <= 10 ||
    (moves match
      case Nil     => false
      case m :: ms => m == Draw
    )

  /*  If a score of 0 is reached, there must be no more moves. */
  val prop3: Property = (st: GameState, moves: List[Move]) =>
    val (_, _, _, scr) = st
    scr != 0 || moves.isEmpty

  /** If it is possible to reach a score of 0 by discarding a card followed by
    * drawing a card, then this must be done.
    */
  val prop4: Property = (st: GameState, moves: List[Move]) =>
    !possibleToDiscardThenDraw(st) ||
      (moves match
        case Discard(_) :: Draw :: ms => true
        case _                        => false
      )

  /*  play through all moves, check property at each state of game  */
  def checkProp(prop: Property, moves: List[Move], st: GameState): Boolean =
    moves match
      case Nil     => true
      case m :: ms => prop(st, moves) && checkProp(prop, ms, nextState(st)(m))

  /*  generators for Suit, Rank, Card, List[Card], goal (non-negative Int) */
  val suitGen: Gen[Suit] = Gen.oneOf[Suit](Hearts, Diamonds, Clubs, Spades)
  val rankGen: Gen[Rank] = for i <- Gen.choose(2, 10) yield Num(i)
  val cardGen: Gen[Card] =
    for
      suit <- suitGen
      rank <- rankGen
    yield (suit, rank)
  val deckGen: Gen[List[Card]] = Gen.containerOf[List, Card](cardGen)
  val goalGen: Gen[Int] = Gen.choose(0, 100)

  /*  convert a Property to a Prop */
  def convert(p: Property): Prop = forAll(deckGen, goalGen) { (deck, goal) =>
    checkProp(p, carefulPlayer(deck, goal), start(deck, goal))
  }

  test("03a. carefulPlayer: value of held cards never exceeds goal") {
    convert(prop1).check()
  }

  test(
    "03b. carefulPlayer: A card is drawn whenever the goal is more than" +
      " 10 greater than the value of the held cards."
  ) {
    convert(prop2).check()
  }

  test(
    "03c. carefulPlayer: If a score of 0 is reached, " +
      "there must be no more moves"
  ) {
    convert(prop3).check()
  }

  test(
    "03d. carefulPlayer: If it is possible to reach a score of 0 by " +
      "discarding a card followed by drawing a card, then this must be done"
  ) {
    convert(prop4).check()
  }

end HW2ChallengeSuite

package curriculum
package pla
package w2

/*  helpers for scoreChallenge */
def newSums(sums: List[Int], value: Int): List[Int] = sums match
  case Nil => List(value)
  case _   => sums map (_ + value)

def sumCardsChallenge(cards: List[Card]): List[Int] = cards match
  case Nil => List(0)
  case _ =>
    def helper(cs: List[Card], acc: List[Int]): List[Int] = cs match
      case Nil            => acc
      case (_, Ace) :: ds => helper(ds, newSums(acc, 1) ::: newSums(acc, 11))
      case d :: ds        => helper(ds, newSums(acc, cardValue(d)))
    helper(cards, Nil)

/*  computes lowest score for hand cards  */
def scoreChallenge(hand: List[Card], goal: Int): Int =
  val sums: List[Int] = sumCardsChallenge(hand)

  val same: Boolean = allSameColor(hand)

  def prelimFun(sum: Int): Int =
    if sum > goal then (sum - goal) * 3 else goal - sum

  val prelims: List[Int] = sums map prelimFun

  def result(prelim: Int): Int = prelim / (if same then 2 else 1)

  val results: List[Int] = prelims map result

  results.min

/*  helper for officiateChallenge */
def stateChallenge(
    hand: List[Card],
    deck: List[Card],
    moves: List[Move],
    goal: Int
): Int =
  (deck, moves) match
    case (_, Nil) => scoreChallenge(hand, goal)
    case (_, Discard(c) :: ms) =>
      stateChallenge(removeCard(hand, c), deck, ms, goal)
    case (Nil, Draw :: ms) => scoreChallenge(hand, goal)
    case (c :: cs, Draw :: ms) =>
      if sumCardsChallenge(c :: hand) forall (_ > goal)
      then scoreChallenge(c :: hand, goal)
      else stateChallenge(c :: hand, cs, ms, goal)

/*  runs a game, returns score */
def officiateChallenge(cards: List[Card], moves: List[Move], goal: Int): Int =
  stateChallenge(Nil, cards, moves, goal)

/*  CAREFUL PLAYER  */
type GameState = (List[Card], List[Card], Int, Int)

/** Returns the next state after making a move. Checks if the move is possible. If not,
  * returns input state. Checks if the move puts hand total above goal. If so, returns
  * input state.
  */
def nextState(st: GameState)(move: Move): GameState =
  val (hand, deck, goal, scr) = st
  (hand, deck, move) match
    case (_, Nil, Draw) => st
    case (_, c :: cs, Draw) =>
      if sumCards(c :: hand) > goal
      then st
      else (c :: hand, cs, goal, score(c :: hand, goal))
    case (_, _, Discard(c)) =>
      if hand exists (_ == c)
      then
        val newHand = removeCard(hand, c)
        (newHand, deck, goal, score(newHand, goal))
      else st

/** Checks if it's possible to draw without going over goal. This applies only when goal -
  * sum_cards(hand) <= 10.
  */
def possibleToDraw(st: GameState): Boolean =
  val (hand, deck, goal, _) = st
  deck match
    case Nil     => false
    case c :: cs => sumCards(c :: hand) <= goal

/*  Checks if it's possible to reach 0 by discarding-then-drawing. */
def possibleToDiscardThenDraw(st: GameState): Boolean =
  val (hand, deck, _, _) = st
  (hand, deck) match
    case (_, Nil) => false // not possible to draw
    case (Nil, _) => false // not possible to discard
    case (h :: hs, c :: cs) =>
      val discardStates: List[GameState] =
        hand map (card => nextState(st)(Discard(card)))

      val discardThenDrawStates: List[GameState] =
        discardStates map (s => nextState(s)(Draw))

      discardThenDrawStates exists (_._4 == 0) // state with zero score

/*  Assumes it's possible to reach 0 by discarding-then-drawing.  */
def discardThenDraw(st: GameState): (GameState, Card) =
  val (hand, cards, _, _) = st

  val discardStates: List[(GameState, Card)] =
    hand map (card => (nextState(st)(Discard(card)), card))

  val discardThenDrawStates: List[(GameState, Card)] =
    discardStates map ((s, c) => (nextState(s)(Draw), c))

  val zeroStates: List[(GameState, Card)] =
    discardThenDrawStates filter (_._1._4 == 0)

  zeroStates.head // guaranteed to be nonempty

def carefulHelper(st: GameState): List[Move] =
  val (hand, deck, goal, scr) = st
  val drawnState = nextState(st)(Draw)
  if scr == 0 then Nil
  else if goal - sumCards(hand) > 10 || possibleToDraw(st) then
    if deck.nonEmpty then Draw :: carefulHelper(drawnState)
    else List(Draw) // avoid inf loop if attempting to draw on empty deck
  else if possibleToDiscardThenDraw(st) then
    val (newState, discarded) = discardThenDraw(st)
    Discard(discarded) :: Draw :: carefulHelper(newState)
  else Nil

/*  returns starting state  */
def start(deck: List[Card], goal: Int): GameState =
  (Nil, deck, goal, score(Nil, goal))

val carefulPlayer: ((List[Card], Int)) => List[Move] =
  carefulHelper compose start

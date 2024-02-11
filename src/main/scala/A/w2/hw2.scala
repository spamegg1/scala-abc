package A.w2

def spam(x: Int) = x

/*  returns None if string not in list,
 *  or Some list without string if string is in list
 *  assumes string occurs in list at most once  */
def allExceptOption(s: String, lst: List[String]): Option[List[String]] =
  lst match
    case Nil => None
    case x :: xs =>
      val rest = allExceptOption(s, xs)
      (x == s, rest) match
        case (true, None)      => Some(xs)
        case (false, None)     => None
        case (true, Some(ys))  => rest
        case (false, Some(ys)) => Some(x :: ys)

/*  returns list of other strings from lists that contain given string
 *  assumes each list has no repetitions  */
def getSubst1(lst: List[List[String]], s: String): List[String] =
  lst match
    case Nil => Nil
    case x :: xs =>
      allExceptOption(s, x) match
        case None    => getSubst1(xs, s)
        case Some(y) => y ++ getSubst1(xs, s)

/*  returns list of other strings from lists that contain given string
 *  assumes each list has no repetitions, is tail recursive  */
def getSubst2(lst: List[List[String]], s: String): List[String] =
  def helper(
      ls: List[List[String]],
      str: String,
      acc: List[String]
  ): List[String] =
    ls match
      case Nil => acc
      case l :: ls =>
        allExceptOption(str, l) match
          case None    => helper(ls, s, acc)
          case Some(y) => helper(ls, s, acc ++ y)
  helper(lst, s, Nil)

/*   imitating record types of SML/Haskell */
case class Fullname(first: String, middle: String, last: String)

/*  returns list of full names that can be produced
 *  by substituting only the first name */
def similarNames(lst: List[List[String]], name: Fullname): List[Fullname] =
  def subFn(ls: List[String]): List[Fullname] = ls match
    case Nil          => Nil
    case head :: tail => name.copy(first = head) :: subFn(tail)
  name :: subFn(getSubst2(lst, name.first))

/* If the enum cases do not take any parameters,
   they can be listed on the same line separated with commas: */
enum Suit:
  case Clubs, Diamonds, Hearts, Spades

/* If one of the enum cases takes parameters, separate lines are required: */
enum Rank:
  case Jack
  case Queen
  case King
  case Ace
  case Num(i: Int) // assume it is always used with values 2, 3, ..., 10

/* a type alias */
type Card = (Suit, Rank)

enum Color:
  case Red, Black

enum Move:
  case Discard(c: Card)
  case Draw

class IllegalMove extends Exception

/* enum cases require importing */
import Suit.*, Rank.*, Color.*, Move.*

/*  returns color of card  */
def cardColor(card: Card): Color =
  card match
    case (Clubs, _)    => Black
    case (Diamonds, _) => Red
    case (Hearts, _)   => Red
    case (Spades, _)   => Black

/*  returns value of card  */
def cardValue(card: Card): Int =
  card match
    case (_, Num(i)) => i
    case (_, Ace)    => 11
    case _           => 10

/*  remove card from list (only once), throws IllegalMove if card not in list */
def removeCard(hand: List[Card], card: Card): List[Card] =
  hand match
    case Nil => throw new IllegalMove
    case x :: xs =>
      if card == x then xs
      else x :: removeCard(xs, card)

/*  returns true if all cards in list have same color  */
def allSameColor(cards: List[Card]): Boolean = cards match
  case Nil      => true
  case _ :: Nil => true
  case head :: (neck :: rest) =>
    cardColor(head) == cardColor(neck) && allSameColor(neck :: rest)

/*  returns sum of values of cards in list  */
def sumCards(cards: List[Card]): Int =
  def helper(cs: List[Card], acc: Int): Int = cs match
    case Nil     => acc
    case c :: cs => helper(cs, acc + cardValue(c))
  helper(cards, 0)

/*  computes score for held cards  */
def score(hand: List[Card], goal: Int): Int =
  val sum = sumCards(hand)
  val same = allSameColor(hand)
  val prelim =
    if sum > goal
    then (sum - goal) * 3
    else goal - sum

  prelim / (if same then 2 else 1)

/* helper for officiate. Useful to have it outside officiate. */
def state(
    hand: List[Card],
    deck: List[Card],
    moves: List[Move],
    goal: Int
): Int =
  (deck, moves) match
    case (_, Nil)              => score(hand, goal)
    case (_, Discard(c) :: ms) => state(removeCard(hand, c), deck, ms, goal)
    case (Nil, Draw :: ms)     => score(hand, goal)
    case (c :: cs, Draw :: ms) =>
      if sumCards(c :: hand) > goal
      then score(c :: hand, goal)
      else state(c :: hand, cs, ms, goal)

/*  runs a game, returns score */
def officiate(cards: List[Card], moves: List[Move], goal: Int): Int =
  state(Nil, cards, moves, goal)

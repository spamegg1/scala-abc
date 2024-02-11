// PROBLEM:
// Consider the following function that consumes a list of numbers and produces
// the sum of all the odd numbers in the list.
// Use an accumulator to design a tail-recursive version of sum-odds.

// (listof Number) -> Number
// produce sum of all odd numbers of lon
sumOddsNoTr(Nil) == 0
sumOddsNoTr(List(1, 2, 5, 6, 11)) == 17

def sumOddsNoTr(list: List[Int]): Int = list match
  case head :: next =>
    if head % 2 == 1
    then head + sumOddsNoTr(next)
    else sumOddsNoTr(next)
  case Nil => 0

sumOddsFold(Nil) == 0
sumOddsFold(List(1, 2, 5, 6, 11)) == 17

def sumOddsFold(list: List[Int]): Int =
  list.filter(_ % 2 == 1).foldRight(0)(_ + _)

sumOddsTr(Nil) == 0
sumOddsTr(List(1, 2, 5, 6, 11)) == 17

// <template according to (listof Number) + accumulator>
def sumOddsTr(list: List[Int]): Int =
  def helper(ints: List[Int], acc: Int): Int = ints match
    case head :: next => helper(next, acc + (if head % 2 == 1 then head else 0))
    case Nil          => acc
  helper(list, 0)

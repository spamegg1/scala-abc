// PROBLEM:
// (A) Consider the following function that consumes a list of numbers and
// produces the sum of all the numbers in the list. Use the stepper to analyze
// the behavior of this function as the list gets larger and larger.
// (B) Use an accumulator to design a tail-recursive version of sum.
// (listof Number) -> Number
// produce sum of all elements of lon
total(Nil) == 0
total(List(2, 4, 5)) == 11

def total(list: List[Int]): Int = list match
  case head :: next => head + total(next)
  case Nil          => 0

totalTr(Nil) == 0
totalTr(List(2, 4, 5)) == 11

def totalTr(list: List[Int]): Int =
  def helper(ints: List[Int], acc: Int): Int = ints match
    case head :: next => helper(next, acc + head)
    case Nil          => acc
  helper(list, 0)

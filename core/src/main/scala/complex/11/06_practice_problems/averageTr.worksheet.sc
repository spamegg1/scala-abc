// PROBLEM:
// Design a function called average that consumes (listof Number)
// and produces the average of the numbers in the list.

// (listof Number) -> Number
// Produce the average of a list of numbers
// ASSUME: lon contains at least 1 element
average(List(1, 2, 3, 4, 5)) == 3.0
average(List(6, 7, 8, 9, 10)) == 8.0

// <template from (listof Number) + 2 accumulators>
def average(list: List[Int]): Double =
  def helper(ints: List[Int], count: Int, total: Int): Double = ints match
    case head :: next => helper(next, count + 1, total + head)
    case Nil          => total / count
  helper(list, 0, 0)

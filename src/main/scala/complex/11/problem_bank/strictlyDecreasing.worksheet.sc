// PROBLEM:
// Design a function that consumes a list of numbers and produces true if the
// numbers in lon are strictly decreasing. You may assume that the list has at
// least two elements.

// (listof Number) -> Boolean
// Produce true if the numbers in lon are strictly decreasing
// ASSUME: the list has at least two elements
!strictlyDecreasing(List(1, 1))
!strictlyDecreasing(List(1, 2))
strictlyDecreasing(List(2, 1))
!strictlyDecreasing(List(1, 2, 3))
strictlyDecreasing(List(3, 2, 1))

// <template from (listof Number) + accumulator>
def strictlyDecreasing(ints: List[Int]): Boolean =
  def helper(list: List[Int], previous: Int): Boolean = list match
    case head :: next =>
      if head < previous
      then helper(next, head)
      else false
    case Nil => true
  helper(ints.tail, ints.head)

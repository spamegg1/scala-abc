// PROBLEM:
// Design an abstract function called some-pred? (including signature, purpose,
// and tests) to simplify the following two functions. When you are done
// rewrite the original functions to use your new some-pred? function.

// PART A: Design an abstract function
// (X -> Boolean) (listof X) -> Boolean
// produces true if some number in the list fits the predicate
val positive = (x: Int) => x > 0
val negative = (x: Int) => x < 0
val even = (x: Int) => x % 2 == 0
val odd = (x: Int) => x % 2 == 1

!somePred(positive)(Nil)
somePred(positive)(List(1, 4, -1, 3))
!somePred(positive)(List(-1, -5))
somePred(even)(List(1, 5, 2, 7))
somePred(odd)(List(1, 5, 2, 7))
!somePred(odd)(List(2, 4, 6))

// <template from (listof X)>
def somePred[T](pred: T => Boolean)(list: List[T]): Boolean = list match
  case head :: next => pred(head) || somePred(pred)(next)
  case Nil          => false

// PART B: Rewrite the original functions
// ListOfNumber -> Boolean
// produce true if some number in lon is positive
!somePositive(Nil)
somePositive(List(2, -3, -4))
!somePositive(List(-2, -3, -4))

// def somePositive(list: List[Int]): Boolean = somePred(positive)(list)
def somePositive = somePred(positive)

// ListOfNumber -> Boolean
// produce true if some number in lon is negative
!someNegative(Nil)
someNegative(List(2, 3, -4))
!someNegative(List(2, 3, 4))

// <template as call to some-pred?>
def someNegative = somePred(negative)

// For reference, here are the function definitions for the two original
// functions.
// ListOfNumber -> Boolean
// produce true if some number in lon is positive
def somePositiveOld(list: List[Int]): Boolean = list match
  case head :: next => head > 0 || somePositiveOld(next)
  case Nil          => false

// ListOfNumber -> Boolean
// produce true if some number in lon is negative
def someNegativeOld(list: List[Int]): Boolean = list match
  case head :: next => head < 0 || somePositiveOld(next)
  case Nil          => false

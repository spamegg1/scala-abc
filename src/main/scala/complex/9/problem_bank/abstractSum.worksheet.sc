// PROBLEM A:
// Design an abstract function (including signature, purpose, and tests) to
// simplify the two sum-of functions.
val square = (x: Int) => x * x
val strLen = (s: String) => s.length
// (X -> Number) (listof X) -> Number
// Produce sum of calling fn on every element of lox.
abstractSum[String](strLen)(Nil) == 0
abstractSum[String](strLen)(List("a", "bc", "def")) == 6
abstractSum[Int](square)(List(2, 4)) == 20
// <template from (listof X)>
def abstractSum[T](function: T => Int)(list: List[T]): Int = list match
  case head :: next => function(head) + abstractSum(function)(next)
  case Nil          => 0

// PROBLEM B:
// Now re-define the original functions to use the new abstract function.
// Remember, the signature and tests should not change from the original
// functions.
// (listof Number) -> Number
// produce the sum of the squares of the numbers in lon
sumOfSquares(Nil) == 0
sumOfSquares(List(2, 4)) == 20
// <template as call to abstract-sum>
def sumOfSquares = abstractSum[Int](square)

// (listof String) -> Number
// produce the sum of the lengths of the strings in los
sumOfLengths(Nil) == 0
sumOfLengths(List("a", "bc", "def")) == 6
// <template as call to abstract-sum>
def sumOfLengths = abstractSum[String](strLen)

// For reference, here are the function definitions for the two original
// functions.
// (listof Number) -> Number
// produce the sum of the squares of the numbers in lon
def sumOfSquares2(list: List[Int]): Int = list match
  case head :: next => square(head) + sumOfSquares2(next)
  case Nil          => 0

// (listof String) -> Number
// produce the sum of the lengths of the strings in los
def sumOfLengths2(list: List[String]): Int = list match
  case head :: next => strLen(head) + sumOfLengths2(next)
  case Nil          => 0

// PROBLEM:
// (A) Consider the following function that consumes a list of numbers and
//     produces the product of all the numbers in the list. Use the stepper to
//     analyze the behavior of this function as the list gets larger and larger.
// (B) Use an accumulator to design a tail-recursive version of product.

// (listof Number) -> Number
// produce product of all elements of lon
product(Nil) == 1
product(List(2, 3, 4)) == 24
product(List(5, -1, 3)) == -15

def product(ints: List[Int]): Int = ints match
  case head :: next => head * product(next)
  case Nil          => 1

productTr(Nil) == 1
productTr(List(2, 3, 4)) == 24
productTr(List(5, -1, 3)) == -15
// <template according to (listof Number) + accumulator>
def productTr(ints: List[Int]): Int =
  def helper(list: List[Int], acc: Int): Int = list match
    case head :: next => helper(next, acc * head)
    case Nil          => acc
  helper(ints, 1)

// PROBLEM:
// Design a function that consumes a list of elements and a natural n, and
// produces a list where each element is replicated n times.
// (replicate-elm (list "a" "b" "c") 2) should produce
// (list "a" "a" "b" "b" "c" "c")

// (listof X) Natural -> (listof X)
// produce list where elements of lox are replicated n times
replicateElement(Nil, 1) == Nil
replicateElement(List('a', 'b', 'c'), 2) == List(
  'a', 'a', 'b', 'b', 'c', 'c'
)
replicateElement(List(1, 2, 3, 4, 5), 1) == List(1, 2, 3, 4, 5)

// def replicateElement[T](list: List[T], number: Int): List[T] = Nil // stub
// <template from (listof X) + accumulator>
def replicateElement[T](list: List[T], number: Int): List[T] =
  def helper[T](stuff: List[T], count: Int, acc: List[T]): List[T] =
    stuff match
      case head :: next =>
        if count == 0
        then helper(next, number, acc)
        else helper(stuff, count - 1, head :: acc)
      case Nil => acc.reverse
  helper(list, number, Nil)

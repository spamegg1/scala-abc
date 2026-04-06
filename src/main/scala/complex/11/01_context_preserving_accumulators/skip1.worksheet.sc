// PROBLEM:
// Design a function that consumes a list of elements and produces the list
// consisting of only the 1st, 3rd, 5th and so on elements of its input.
//    (skip1 (list "a" "b" "c" "d")) should produce (list "a" "c")

// (listof X) -> (listof X)
// produce list consisting of only the 1st, 3rd, 5th... elements of lox
skipOne(Nil).isEmpty
skipOne(List("a", "b", "c", "d")) == List("a", "c")
skipOne(List(1, 2, 3, 4, 5, 6)) == List(1, 3, 5)

// def skipOne[T](list: List[T]): List[T] = Nil // stub
def skipOne[T](list: List[T]): List[T] =
  def helper[T](stuff: List[T], acc: List[T], count: Int): List[T] = stuff match
    case head :: next =>
      if count == 0
      then helper(next, head :: acc, 1)
      else helper(next, acc, count - 1)
    case Nil => acc.reverse
  helper(list, Nil, 0)

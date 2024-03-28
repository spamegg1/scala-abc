// PROBLEM:
// Design a function that consumes a list of elements lox and a natural number
// n and produces the list formed by including the first element of lox, then
// skipping the next n elements, including an element, skipping the next n
// and so on.
//  (skipn (list "a" "b" "c" "d" "e" "f") 2) should produce (list "a" "d")

// (listof X) Natural -> (listof X)
// produce list containing 1st element of lox, then skip next n, then include...
// (check-expect (skipn empty 0) empty)
val abcdef = List("a", "b", "c", "d", "e", "f")
skipN(Nil, 0).isEmpty
skipN(abcdef, 0) == abcdef
skipN(abcdef, 1) == List("a", "c", "e")
skipN(abcdef, 2) == List("a", "d")
skipN(abcdef, 3) == List("a", "e")

// def skipN[T](list: List[T], n: Int): List[T] = Nil // stub
// templated according to (listof X) and accumulator
// (define (skipn lox0 n)
def skipN[T](list: List[T], n: Int): List[T] =
  def helper[T](stuff: List[T], acc: List[T], count: Int): List[T] = stuff match
    case head :: next =>
      if count == 0
      then helper(next, head :: acc, n)
      else helper(next, acc, count - 1)
    case Nil => acc.reverse
  helper(list, Nil, 0)

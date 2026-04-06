// PROBLEM:
// Design a function that consumes a list of elements lox and a natural number
// n and produces the list formed by dropping every nth element from lox.
// (dropn (list 1 2 3 4 5 6 7) 2) should produce (list 1 2 4 5 7)
// (listof X) Natural -> (listof X)
// produce list formed by dropping every nth element from lox
dropN(Nil, 0).isEmpty
dropN(List('a', 'b', 'c', 'd', 'e', 'f'), 0).isEmpty
dropN(List('a', 'b', 'c', 'd', 'e', 'f'), 1) == List('a', 'c', 'e')
dropN(List('a', 'b', 'c', 'd', 'e', 'f'), 2) == List('a', 'b', 'd', 'e')

// def dropN[T](list: List[T], n: Int): List[T] = Nil // stub
def dropN[T](list: List[T], n: Int): List[T] =
  def helper[T](stuff: List[T], acc: Int): List[T] = stuff match
    case head :: next =>
      if acc == 0
      then helper(next, n)
      else head :: helper(next, acc - 1)
    case Nil => Nil
  helper(list, n)

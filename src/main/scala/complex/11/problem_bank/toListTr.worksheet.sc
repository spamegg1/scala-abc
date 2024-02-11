// PROBLEM:
// Consider the following function that consumes Natural number n and produces a
// list of all the naturals of the form (list 1 2 ... n-1 n) not including 0.
// Use an accumulator to design a tail-recursive version of to-list.

// Natural -> (listof Natural)
// produce (cons n (cons n-1 ... empty)), not including 0
toListNoTr(0) == Nil
toListNoTr(1) == List(1)
toListNoTr(3) == List(1, 2, 3)

// def toList(n: Int): List[Int] = Nil // stub
def toListNoTr(n: Int): List[Int] =
  if n == 0 then Nil
  else toListNoTr(n - 1) ::: List(n)

toListTr(0) == Nil
toListTr(1) == List(1)
toListTr(3) == List(1, 2, 3)

// <template according to Natural + accumulator>
def toListTr(n: Int): List[Int] =
  def helper(m: Int, acc: List[Int]): List[Int] =
    if m == 0 then acc
    else helper(m - 1, m :: acc)
  helper(n, Nil)

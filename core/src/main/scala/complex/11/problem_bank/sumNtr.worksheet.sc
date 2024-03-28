// PROBLEM:
// Consider the following function that consumes Natural number n and produces
// the sum of all the naturals in [0, n].
// Use an accumulator to design a tail-recursive version of sum-n.

// Natural -> Natural
// produce sum of Natural[0, n]
sumNnoTr(0) == 0
sumNnoTr(1) == 1
sumNnoTr(3) == 6

// def sumNnoTr(n: Int): Int = 0 // stub
def sumNnoTr(n: Int): Int =
  if n == 0 then 0
  else n + sumNnoTr(n - 1)

// <template according to Natural + accumulator>
sumNtr(0) == 0
sumNtr(1) == 1
sumNtr(3) == 6

def sumNtr(n: Int): Int =
  def helper(m: Int, acc: Int): Int =
    if m == 0 then acc
    else helper(m - 1, acc + m)
  helper(n, 0)

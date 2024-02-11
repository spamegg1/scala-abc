// PROBLEM:
// Design a function that produces the sum of
// all the naturals from 0 to a given n.
enum Natural:
  case Zero
  case Successor(n: Natural)
// Natural -> Natural
// produce the sum of all the naturals from 0 to n
sumToN(0) == 0
sumToN(1) == 1
sumToN(4) == 10

// def sumToN(n: Int): Int = 0 // stub
def sumToN(n: Int): Int = if n <= 0 then 0 else n + sumToN(n - 1)

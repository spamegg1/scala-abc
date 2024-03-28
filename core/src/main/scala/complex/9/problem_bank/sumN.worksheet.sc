// PROBLEM:
// Complete the design of the following function, by writing out
// the final function definition. Use at least one built in abstract
// function.
// Natural -> Natural
// produce the sume of the first n odd numbers
sumNodds(0) == 0
sumNodds(1) == 1
sumNodds(2) == 1 + 3
sumNodds(3) == 1 + 3 + 5
sumNodds(10) == 1 + 3 + 5 + 7 + 9 + 11 + 13 + 15 + 17 + 19
// HINT: The first n odd numbers are contained in the first 2*n naturals.
// For example (list 0 1 2 3) contains the first 4 naturals and the first
// 2 odd numbers. Also remember that DrRacket has a build in predicate
// function called odd?

// SOLUTION CHOICE ONE:
def sumNodds(n: Int): Int =
  (0 until 2 * n).filter(_ % 2 == 1).sum

// SOLUTION CHOICE TWO:
def sumNodds2(n: Int): Int =
  (for i <- 0 until n yield 2 * i + 1).sum

// PROBLEM:
// Design a function called odd-from-n that consumes a natural number n,
// and produces a list of all the odd numbers from n down to 1.
// Note that there is a primitive function, odd?, that produces true if a
// natural number is odd.

// Natural -> ListOfNatural
// produce a list of the odd numbers between n and 1
oddFromN(0) == Nil
oddFromN(7) == List(7, 5, 3, 1)
oddFromN(8) == List(7, 5, 3, 1)

// def oddFromN(n: Int): List[Int] = Nil // stub
def oddFromN(n: Int): List[Int] =
  if n <= 0 then Nil
  else if n % 2 == 1
  then n :: oddFromN(n - 1)
  else oddFromN(n - 1)

def gcdRecur(a: Int, b: Int): Int =
  // a, b: positive integers
  // returns: a positive integer, the greatest common divisor of a & b.
  if b == 0 then a
  else gcdRecur(b, a % b)

gcdRecur(177, 99)

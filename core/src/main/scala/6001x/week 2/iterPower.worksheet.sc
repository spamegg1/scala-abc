def iterPower(base: Double, exp: Int): Double =
  // base: int or float.
  // exp: int >= 0
  // returns: int or float, base^exp
  // Your code here
  var result = 1.0
  for
    _ <- 0 until exp
  do
    result *= base
  result

iterPower(2, 3)

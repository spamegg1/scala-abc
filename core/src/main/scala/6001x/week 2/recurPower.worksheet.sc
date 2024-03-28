def recurPower(base: Double, exp: Int): Double =
  // base: int or float.
  // exp: int >= 0
  // returns: int or float, base^exp
  // Your code here
  require(exp >= 0)

  if base == 0 then base
  else if exp == 0 then 1
  else base * recurPower(base, exp - 1)

recurPower(1.5, 2)

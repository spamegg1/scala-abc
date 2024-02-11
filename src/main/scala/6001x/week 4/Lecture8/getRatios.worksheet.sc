def getRatios(L1: List[Double], L2: List[Double]) =
  // Assumes: L1 and L2 are lists of equal length of numbers
  // Returns: a list containing L1[i]/L2[i]
  var ratios = List[Double]()
  for
    index <- 0 until L1.length
  do
    try
      ratios = L1(index) / L2(index) :: ratios
    catch
      case _: ArithmeticException =>
        ratios = "NaN".toDouble :: ratios  // NaN = Not a Number
      case _ =>
        throw new IllegalArgumentException("getRatios called with bad arg")
  ratios.reverse

val l1 = List(1.0, 2, 3, 4)
val l2 = List(5.0, 6, 7, 0)

getRatios(l1, l2)

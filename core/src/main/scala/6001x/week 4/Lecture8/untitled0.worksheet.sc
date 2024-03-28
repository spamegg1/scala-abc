def fancyDivide(numbers: List[Double], index: Int) =
  val denom = numbers(index)
  for
    number <- numbers
  yield
    simpleDivide(number, denom)


def simpleDivide(number: Double, denom: Double) =
  try
    number / denom
  catch
    case _: ArithmeticException => 0

println(fancyDivide(List(0, 2, 4), 1))

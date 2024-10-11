// Suppose we rewrite the FancyDivide function to use a helper function.
def fancyDivide(listOfNums: List[Int], index: Int) =
  val denom = listOfNums(index)
  listOfNums.map(simpleDivide(_, denom))

// def simpleDivide(item: Int, denom: Int) = item / denom // BAD
def simpleDivide(item: Int, denom: Int) =
  try item / denom
  catch case _: ArithmeticException => 0

// for the following call:
//     fancyDivide(List(0, 2, 4), 0)
// it raises Exception in thread "main" java.lang.ArithmeticException: / by zero

// Your task is to change the definition of simpleDivide so that
// the call does not raise an exception.
// When dividing by 0, fancyDivide should return a list with all 0 elements.
// Any other error cases should still raise exceptions.
// You should only handle the ArithmeticException.

@main
def simpleDivideBug =
  assert(fancyDivide(List(0, 2, 4), 1) == List(0, 1, 2)) // good case
  assert(fancyDivide(List(0, 2, 4), 0) == List(0, 0, 0)) // exception case
  println("Tests passed!")

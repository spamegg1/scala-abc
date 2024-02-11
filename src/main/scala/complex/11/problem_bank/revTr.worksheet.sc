// PROBLEM:
// Design a function that consumes (listof X) and produces a list of the same
// elements in the opposite order. Use an accumulator. Call the function rev.
// (DrRacket's version is called reverse.) Your function should be tail
// recursive. In this problem only the first step of templating is provided.

// (listof X) -> (listof X)
// produce list with elements of lox in reverse order
reverse(Nil).isEmpty
reverse(List(1)) == List(1)
reverse(List('a', 'b', 'c')) == List('c', 'b', 'a')

//(define (rev lox) empty)
def reverse[T](list: List[T]): List[T] =
  def helper[T](stuff: List[T], reversed: List[T]): List[T] = stuff match
    case head :: next => helper(next, head :: reversed)
    case Nil          => reversed
  helper(list, Nil)

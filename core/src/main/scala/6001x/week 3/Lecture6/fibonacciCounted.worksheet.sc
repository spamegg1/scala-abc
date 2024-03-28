import collection.mutable.Map

var calls = 0

def fib(n: Int): Int =
  calls += 1
  if n == 1 then 1
  else if n == 2 then 2
  else fib(n - 1) + fib(n - 2)

def fibEfficient(n: Int, d: Map[Int, Int]): Int =
  calls += 1
  if d.contains(n) then d(n)
  else
    val ans = fibEfficient(n - 1, d) + fibEfficient(n - 2, d)
    d(n) = ans
    ans


fib(10)
s"function calls ${calls}"

calls = 0

val d = Map(1 -> 1, 2 -> 2)
fibEfficient(10, d)
s"function calls ${calls}"

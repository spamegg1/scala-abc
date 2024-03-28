import collection.mutable.Map

def fib(n: Int): Int =
  if n == 1 then 1
  else if n == 2 then 2
  else fib(n - 1) + fib(n - 2)

def fibEfficient(n: Int, d: Map[Int, Int]): Int =
  if d.contains(n) then d(n)
  else
    val ans = fibEfficient(n - 1, d) + fibEfficient(n - 2, d)
    d(n) = ans
    ans

val d = Map(1 -> 1, 2 -> 2)

// "using fib"
fib(10)
// "using fibEfficient"
fibEfficient(10, d)

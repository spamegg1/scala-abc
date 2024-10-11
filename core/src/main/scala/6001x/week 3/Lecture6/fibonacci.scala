package curriculum

def fib(n: Long): Long =
  if n == 1 then 1
  else if n == 2 then 2
  else fib(n - 1) + fib(n - 2)

def fibEfficient(n: Long, d: MMap[Long, Long]): Long =
  if d.contains(n) then d(n)
  else
    val ans = fibEfficient(n - 1, d) + fibEfficient(n - 2, d)
    d(n) = ans
    ans

@main
def fibTest =
  val d = MMap(1L -> 1L, 2L -> 2L)
  println(fib(10)) // "using fib"
  println(fibEfficient(50, d)) // "using fibEfficient"

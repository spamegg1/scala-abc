var calls = 0

def fib(n: Int): Int =
  calls += 1
  if n == 1 then 1
  else if n == 2 then 2
  else fib(n - 1) + fib(n - 2)

def fibEfficient(n: Int, d: MMap[Int, Int]): Int =
  calls += 1
  if d.contains(n) then d(n)
  else
    val ans = fibEfficient(n - 1, d) + fibEfficient(n - 2, d)
    d(n) = ans
    ans

@main
def fibCount =
  val ten = fib(10)
  println(s"fib function calls ${calls}") // 109

  calls = 0

  val d = MMap(1 -> 1, 2 -> 2)
  val eff = fibEfficient(10, d)
  println(s"fibEfficient function calls ${calls}") // 17

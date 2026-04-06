// assumes num an int >= 0
// returns Fibonacci of num
def fib(num: Int): Int =
  if Seq(0, 1) contains num then 1
  else fib(num - 1) + fib(num - 2)

fib(10)

import math.BigInt

val genFib: LazyList[Int] =
  def helper(fib1: Int, fib2: Int, n: Int): LazyList[Int] =
    fib1 #:: helper(fib2, fib1 + fib2, n + 1)
  helper(0, 1, 0)

genFib
  .take(10)
  .foreach(println)

val fibs: LazyList[BigInt] =
  BigInt(0) #::
  BigInt(1) #::
  fibs.zip(fibs.tail).map{ n =>
    println(s"Adding ${n._1} and ${n._2}")
    n._1 + n._2
  }

fibs
  .take(10)
  .foreach(println)

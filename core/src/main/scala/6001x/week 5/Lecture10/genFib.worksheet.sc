val genFib: LazyList[Long] =
  def helper(fib1: Long, fib2: Long, n: Long): LazyList[Long] =
    fib1 #:: helper(fib2, fib1 + fib2, n + 1)
  helper(0, 1, 0)

genFib.take(10).foreach(println)

val fibs: LazyList[Long] =
  0L #::
    1L #::
    fibs
      .zip(fibs.tail)
      .map: n =>
        println(s"Adding ${n._1} and ${n._2}")
        n._1 + n._2

fibs.drop(20).take(10).foreach(println)

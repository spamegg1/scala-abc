// In addition to map and filter there are several other useful abstract
// list functions built into ISL.  These are listed on the Language page
// of the course web site. Examples of their use are shown below:
List(1, -2, 3, -4).map(_ > 0) == List(true, false, true, false)
List(1, -2, 3, -4).filter(_ < 0) == List(-2, -4)
List(1, 2, 3).foldRight(0)(_ + _) == 6
List(1, 2, 3).foldRight(1)(_ * _) == 6
(0 until 6) == IndexedSeq(0, 1, 2, 3, 4, 5)
(for i <- 0 until 4 yield i * i) == IndexedSeq(0, 1, 4, 9)

// Natural -> Natural
total(List(1, 2, 3)) == 6

// def total(ints: List[Int]): Int = 0 // stub
// def total(ints: List[Int]): Int =
//   ints.foldRight(???)(???) // template
def total(ints: List[Int]): Int =
  ints.foldRight(0)(_ + _)

// Natural -> Natural
// produce the sum of the first n natural numbers
sumTo(3) == 3
sumTo(10) == 45

// def sumTo(n: Int): Int = 0 // stub
// def sumTo(n: Int): Int =
//   (??? until ???).foldRight(???)(???) // template
def sumTo(n: Int): Int =
  (0 until n).foldRight(0)(_ + _)

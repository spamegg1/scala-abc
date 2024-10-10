def isTriangular(k: Int) =
  /** k, a positive integer returns true if k is triangular and false if not
    */
  // YOUR CODE HERE
  var nextTriangular = 0
  var nextAddition = 1
  var result = false

  while nextTriangular <= k
  do
    nextTriangular += nextAddition
    nextAddition += 1
    if k == nextTriangular then result = true
  result

def testIsTriangular =
  /** Tests the isTriangular function.
    */
  assert(isTriangular(1))
  assert(!isTriangular(2))
  assert(isTriangular(3))
  assert(!isTriangular(4))
  assert(!isTriangular(5))
  assert(isTriangular(6))
  assert(!isTriangular(7))
  assert(!isTriangular(8))
  assert(!isTriangular(9))
  assert(isTriangular(10))
  assert(!isTriangular(11))
  assert(!isTriangular(12))
  assert(!isTriangular(13))
  assert(!isTriangular(14))
  assert(isTriangular(15))
  assert(!isTriangular(16))
  assert(!isTriangular(17))
  assert(!isTriangular(18))
  assert(!isTriangular(19))
  assert(!isTriangular(20))
  assert(isTriangular(21))

  assert(isTriangular(5050))
  assert(!isTriangular(5051))

  assert(isTriangular(20100))
  assert(!isTriangular(20101))

  println("tests pass: isTriangular")

def largestOddTimes(L: List[Int]) =
  /** Assumes L is a non-empty list of ints Returns the largest element of L that occurs
    * an odd number of times in L. If no such element exists, returns None
    */
  // Your code here
  // First remove all elements of L that occur an even number of times
  val oddList = ArrayBuffer[Int]()
  for
    elt <- L
    if L.count(_ == elt) % 2 == 1
  do oddList += elt

  // Now return the max of odd_list, else None
  if oddList.nonEmpty
  then Some(oddList.max)
  else None

def testLargestOddTimes =
  /** Tests the largestOddTimes function.
    */
  assert(largestOddTimes(Nil) == None)
  assert(largestOddTimes(List(1)) == Some(1))
  assert(largestOddTimes(List(1, 2)) == Some(2))
  assert(largestOddTimes(List(1, 1)) == None)
  assert(largestOddTimes(List(1, 2, 2)) == Some(1))
  assert(largestOddTimes(List(1, 1, 2, 2)) == None)
  assert(largestOddTimes(List(1, 1, 2, 3)) == Some(3))
  assert(largestOddTimes(List(1, 1, 3, 3)) == None)
  assert(largestOddTimes(List(3, 9, 5, 3, 5, 3)) == Some(9))
  assert(largestOddTimes(List(2, 2, 4, 4)) == None)

  println("tests pass: largestOddTimes")

def dictInterdiff(d1: Map[Int, Int], d2: Map[Int, Int], f: (Int, Int) => AnyVal) =
  /** d1, d2: dicts whose keys and values are integers Returns a tuple of dictionaries
    * according to the instructions above
    */

  // Your code here
  def dictIntersect(d1: Map[Int, Int], d2: Map[Int, Int]) =
    (for
      key <- d1.keys
      if d2.contains(key)
    yield key -> f(d1(key), d2(key))).toMap

  def dictDifference(d1: Map[Int, Int], d2: Map[Int, Int]) =
    (for
      key <- d1.keys
      if !d2.contains(key)
    yield key -> d1(key)).toMap
      ++
        (for
          key <- d2.keys
          if !d1.contains(key)
        yield key -> d2(key)).toMap

  (dictIntersect(d1, d2), dictDifference(d1, d2))

def testDictInterdiff =
  /** Tests the dictInterdiff function.
    */
  def f(a: Int, b: Int) = a + b
  def g(a: Int, b: Int) = a < b

  val d1 = Map(1 -> 30, 2 -> 20, 3 -> 30, 5 -> 80)
  val d2 = Map(1 -> 40, 2 -> 50, 3 -> 60, 4 -> 70, 6 -> 90)
  val d3 = Map(2 -> 30, 4 -> 10, 6 -> 100, 7 -> 20)

  assert(dictInterdiff(d1, d1, f) == (Map(1 -> 60, 2 -> 40, 3 -> 60, 5 -> 160), Map()))
  assert(
    dictInterdiff(d2, d2, f) ==
      (Map(1 -> 80, 2 -> 100, 3 -> 120, 4 -> 140, 6 -> 180), Map())
  )
  assert(dictInterdiff(d3, d3, f) == (Map(2 -> 60, 4 -> 20, 6 -> 200, 7 -> 40), Map()))
  assert(
    dictInterdiff(d1, d1, g) ==
      (Map(1 -> false, 2 -> false, 3 -> false, 5 -> false), Map())
  )
  assert(
    dictInterdiff(d2, d2, g) ==
      (Map(1 -> false, 2 -> false, 3 -> false, 4 -> false, 6 -> false), Map())
  )
  assert(
    dictInterdiff(d3, d3, g) ==
      (Map(2 -> false, 4 -> false, 6 -> false, 7 -> false), Map())
  )

  assert(
    dictInterdiff(d2, d1, f) ==
      (Map(1 -> 70, 2 -> 70, 3 -> 90), Map(4 -> 70, 5 -> 80, 6 -> 90))
  )
  assert(
    dictInterdiff(d1, d3, f) ==
      (Map(2 -> 50), Map(1 -> 30, 3 -> 30, 5 -> 80, 4 -> 10, 6 -> 100, 7 -> 20))
  )
  assert(
    dictInterdiff(d3, d2, f) ==
      (Map(2 -> 80, 4 -> 80, 6 -> 190), Map(1 -> 40, 3 -> 60, 7 -> 20))
  )
  assert(
    dictInterdiff(d1, d2, g) ==
      (Map(1 -> true, 2 -> true, 3 -> true), Map(4 -> 70, 5 -> 80, 6 -> 90))
  )
  assert(
    dictInterdiff(d3, d1, g) ==
      (Map(2 -> false), Map(1 -> 30, 3 -> 30, 5 -> 80, 4 -> 10, 6 -> 100, 7 -> 20))
  )
  assert(
    dictInterdiff(d2, d3, g) ==
      (Map(2 -> false, 4 -> false, 6 -> true), Map(1 -> 40, 3 -> 60, 7 -> 20))
  )

  println("tests pass: dictInterdiff")

// Do not change the Location or Campus classes.
// Location class is the same as in lecture.
case class Location(x: Double, y: Double):
  def move(deltaX: Double, deltaY: Double) = Location(x + deltaX, y + deltaY)

  def distFrom(other: Location) =
    val xDist = x - other.x
    val yDist = y - other.y
    math.sqrt(xDist * xDist + yDist * yDist)

  def equals(other: Location) = x == other.x && y == other.y
  override def toString = "<" + x.toString + "," + y.toString + ">"

class Campus(centerLoc: Location):
  override def toString = centerLoc.toString

class MITCampus(centerLoc: Location, tentLoc: Location = Location(0, 0))
    extends Campus(centerLoc):
  /** A MITCampus is a Campus that contains tents. Assumes centerLoc and tentLoc are
    * Location objects Initializes a new Campus centered at location centerLoc with a tent
    * at location tentLoc
    */

  val tents = ArrayBuffer(tentLoc)

  def addTent(newTentLoc: Location) =
    /** Assumes newTentLoc is a Location Adds newTentLoc to the campus only if the tent is
      * at least 0.5 distance away from all other tents already there. Campus is unchanged
      * otherwise. Returns true if it could add the tent, false otherwise.
      */
    if tents.forall(tent => newTentLoc.distFrom(tent) >= 0.5) then
      tents += newTentLoc
      true
    else false

  def removeTent(tentLoc: Location) =
    /** Assumes tentLoc is a Location Removes tentLoc from the campus. Raises a ValueError
      * if there is not a tent at tentLoc. Does not return anything
      */
    tents.indexOf(tentLoc) match
      case -1 => throw new NoSuchElementException
      case n  => tents.remove(n)

  def getTents =
    /** Returns a list of all tents on the campus. The list should contain the string
      * representation of the Location of a tent. The list should be sorted by the x
      * coordinate of the location.
      */
    (for tentLoc <- tents yield tentLoc.toString).toList.sorted

def testMITCampus =
  /** Tests the MITCampus class.
    */
  val c = MITCampus(Location(1, 2))

  assert(c.addTent(Location(2, 3)))
  assert(c.addTent(Location(1, 2)))
  assert(!c.addTent(Location(0, 0)))
  assert(!c.addTent(Location(2, 3)))

  c.addTent(Location(1, 1))
  c.removeTent(Location(1, 1))
  assert(c.getTents == List("<0.0,0.0>", "<1.0,2.0>", "<2.0,3.0>"))

  println("tests pass: MITCampus")

@main
def mitFinalExam =
  testIsTriangular
  testLargestOddTimes
  testDictInterdiff
  testMITCampus

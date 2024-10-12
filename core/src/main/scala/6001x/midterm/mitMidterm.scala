package curriculum
package mit6001x

def uniqueValues(aDict: Map[Char, Int]) =
  /** aDict: a dictionary that maps to integer values. returns: a list Returns a list,
    * consisting of keys in aDict that map to unique integer values. If no key maps to a
    * unique integer value, returns empty list. The returned list is in increasing order.
    * Example: aDict = Map('a': 1, 'b': 2, 'c': 2, 'd': 3) uniqueValues(aDict) ->
    * List('a', 'd']
    */
  val result = ArrayBuffer[Char]()

  for
    (key, value) <- aDict
    if !aDict.exists((char, int) => int == value && char != key)
  do result += key

  result.toList.sorted

def testUniqueValues =
  /** Tests uniqueValues function.
    */
  val dict1 = Map[Char, Int]()
  val dict2 = Map('a' -> 1)
  val dict3 = Map('a' -> 1, 'b' -> 1)
  val dict4 = Map('d' -> 1, 'z' -> 2, 'a' -> 3)
  val dict5 = Map('b' -> 2, 'd' -> 3, 'c' -> 2, 'a' -> 1)
  val dict6 = Map('a' -> 1, 'b' -> 1, 'c' -> 2, 'd' -> 2, 'e' -> 3, 'f' -> 3)

  assert(uniqueValues(dict1) == Nil)
  assert(uniqueValues(dict2) == List('a'))
  assert(uniqueValues(dict3) == Nil)
  assert(uniqueValues(dict4) == List('a', 'd', 'z'))
  assert(uniqueValues(dict5) == List('a', 'd'))
  assert(uniqueValues(dict6) == Nil)

  print("Testing uniqueValues: PASS")

def laceStringsRecur(s1: String, s2: String) =
  /** s1 and s2 are strings. Returns a new str with elements of s1 and s2 interlaced,
    * beginning with s1. If strings are not of same length, then the extra elements should
    * appear at the end.
    */
  def helpLaceStrings(s1: String, s2: String, out: String): String =
    if s1.isEmpty then out + s2
    else if s2.isEmpty then out + s1
    else helpLaceStrings(s1.drop(1), s2.drop(1), out + s1.take(1) + s2.take(1))

  helpLaceStrings(s1, s2, "")

def testLaceStringsRecur =
  /** Tests laceStringsRecur function.
    */
  val s1 = ""
  val s2 = "a"
  val s3 = "bc"
  val s4 = "qwerty"
  val s5 = "ppppppppppppp"

  assert(laceStringsRecur(s1, s1) == "")
  assert(laceStringsRecur(s1, s2) == "a")
  assert(laceStringsRecur(s2, s1) == "a")
  assert(laceStringsRecur(s1, s3) == "bc")
  assert(laceStringsRecur(s3, s1) == "bc")
  assert(laceStringsRecur(s2, s3) == "abc")
  assert(laceStringsRecur(s3, s2) == "bac")
  assert(laceStringsRecur(s3, s3) == "bbcc")
  assert(laceStringsRecur(s4, s5) == "qpwpeprptpypppppppp")
  assert(laceStringsRecur(s5, s4) == "pqpwpeprptpyppppppp")
  print("Testing laceStringsRecur: PASS")

def generalPoly(L: List[Int]): Int => Double =
  // L, a list of numbers (n0, n1, n2, ... nk)
  // Returns a function, which when applied to a value x, returns the value
  // n0 * x^k + n1 * x^(k-1) + ... nk * x^0
  def f(x: Int) =
    if L.isEmpty then 0.0
    else
      val rest = generalPoly(L.drop(1))
      L(0) * math.pow(x, L.length - 1) + rest(x)
  f

def testGeneralPoly =
  /** Tests generalPoly function.
    */
  val l0 = List[Int]()
  val l1 = List(1)
  val l2 = List(2, 3)
  val l3 = List(4, 5, 6)
  val l4 = List(7, 8, 9, 10)
  val l5 = List(11, 12, 13, 14, 15)
  val l6 = List(-16, -17, -18, -19, -20, -21)
  val l7 = List(9, 8, 7, 6, 5, 4, 3)
  val l8 = List(2, 1, 0, -1, -2, -3, -4, -5)

  val x0 = 0
  val x1 = -1
  val x2 = 1
  val x3 = 5
  val x4 = -5

  assert(generalPoly(l0)(x1) == 0)
  assert(generalPoly(l1)(x2) == 1)
  assert(generalPoly(l2)(x3) == 13)
  assert(generalPoly(l3)(x4) == 81)
  assert(generalPoly(l4)(x1) == 2)
  assert(generalPoly(l5)(x0) == 15)
  assert(generalPoly(l6)(x3) == -63471)
  assert(generalPoly(l7)(x2) == 42)
  assert(generalPoly(l8)(x4) == -141060)
  print("Testing generalPoly: PASS")

// TESTING
@main
def mitMidterm =
  testUniqueValues
  testLaceStringsRecur
  testGeneralPoly

package A.w1

/* COMMUNITY PROBLEMS FOR WEEK 1 */

/* 1.
Write a function
    alternate: List[Int] => Int
that takes a list of numbers and adds them with alternating sign. For example
    alternate(List(1,2,3,4)) = 1 - 2 + 3 - 4 = -2
 */
def alternateHelper(numbers: List[Int], positive: Boolean): Int =
  if numbers.isEmpty then 0
  else if positive then numbers.head + alternateHelper(numbers.tail, false)
  else alternateHelper(numbers.tail, true) - numbers.head

def alternate(numbers: List[Int]): Int = alternateHelper(numbers, true)

/* 2.
Write a function
    minMax: List[Int] => (Int, Int)
that takes a non-empty list of numbers, and returns a pair
    (min, max)
of the minimum and maximum of the numbers in the list.
 */
def minMaxHelper(
    numbers: List[Int],
    minSoFar: Int,
    maxSoFar: Int
): (Int, Int) =
  if numbers.isEmpty then (minSoFar, maxSoFar)
  else if numbers.head < minSoFar
  then minMaxHelper(numbers.tail, numbers.head, maxSoFar)
  else if numbers.head > maxSoFar
  then minMaxHelper(numbers.tail, minSoFar, numbers.head)
  else minMaxHelper(numbers.tail, minSoFar, maxSoFar)

def minMax(numbers: List[Int]): (Int, Int) =
  minMaxHelper(numbers.tail, numbers.head, numbers.head)

/* 3.
Write a function
    cumsum: List[Int] => List[Int]
that takes a list of numbers and returns a list
of the partial sums of those numbers. For example
    cumsum(List(1,4,20)) = List(1,5,25)
 */
def cumsum(numbers: List[Int]): List[Int] =
  if numbers.isEmpty then Nil
  else if numbers.tail.isEmpty then numbers
  else
    numbers.head ::
      cumsum((numbers.head + numbers.tail.head) :: numbers.tail.tail)

/* 4.
Write a function
    greeting: Option[String] => String
that given a string  option Some name returns the string
    "Hello there, ...!"
where the dots would be replaced by name.
Note that the name is given as an option,
so if it is None then replace the dots with "you".
 */
def greeting(name: Option[String]): String =
  if name.isDefined
  then "Hello there, " + name.get + "!"
  else "Hello there, you!"

/* 5.
Write a function
    myRepeat: (List[Int], List[Int]) => List[Int]
that given a list of Integers and another list of nonnegative Integers,
repeats the Integers in the first list according to the numbers
indicated by the second list. For example:
    repeat(List(1, 2, 3), List(4, 0, 3)) = List(1, 1, 1, 1, 3, 3, 3)
 */
def repeatHelper(number: Int, count: Int): List[Int] =
  if count == 0 then Nil
  else number :: repeatHelper(number, count - 1)

def myRepeat(numbers: List[Int], counts: List[Int]): List[Int] =
  if numbers.isEmpty then Nil
  else
    repeatHelper(numbers.head, counts.head) ++
      myRepeat(numbers.tail, counts.tail)

/* 6.
Write a function
    addOpt: (Option[Int], Option[Int]) => Option[Int]
that given two "optional" Integers, adds them if they are both present
(returning Some of their sum),
or returns None if at least one of the two arguments is None.
 */
def addOpt(int1: Option[Int], int2: Option[Int]): Option[Int] =
  if int1.isDefined && int2.isDefined
  then Some(int1.get + int2.get)
  else None

/* 7.
Write a function
    addAllOpt: List[Option[Int]] => Option[Int]
that given a list of "optional" Integers, adds those Integers
that are there (i.e. adds all the Some i). For example:
    addAllOpt(List(Some(1), None, Some(3))) = Some(4).
If the list does not contain any Some(i) is in it, i.e. they are
all None or the list is empty, the function should return None.
 */
def addAllOptHelper(numbers: List[Option[Int]]): Int =
  if numbers.isEmpty then 0
  else if numbers.head.isDefined
  then addAllOptHelper(numbers.tail) + numbers.head.get
  else addAllOptHelper(numbers.tail)

def addAllOpt(numbers: List[Option[Int]]): Option[Int] =
  val total = addAllOptHelper(numbers)
  if total == 0 then None else Some(total)

/* 8.
Write a function
    any: List[Boolean] => Boolean
that given a list of Booleans
returns true if there is at least one of them that is true
otherwise returns false.
(If the list is empty it should return false because there is no true.)
 */
def any(booleans: List[Boolean]): Boolean =
  if booleans.isEmpty then false
  else booleans.head || any(booleans.tail)

/* 9.
Write a function
    all: List[Boolean] => Boolean
that given a list of Booleans returns true if all of them true,
otherwise returns false.
(If the list is empty it should return true because there is no false.)
 */
def all(booleans: List[Boolean]): Boolean =
  if booleans.isEmpty then true
  else booleans.head && all(booleans.tail)

/* 10.
Write a function
    myZip: (List[Int], List[Int]) => (Int, Int)
that given two lists of Integers creates consecutive pairs,
and stops when one of the lists is empty. For example:
    myZip(List(1, 2, 3), List(4, 6)) = List((1, 4), (2, 6))
 */
def myZip(nums1: List[Int], nums2: List[Int]): List[(Int, Int)] =
  if nums1.isEmpty || nums2.isEmpty then Nil
  else (nums1.head, nums2.head) :: myZip(nums1.tail, nums2.tail)

/* 11.
Challenge: Write a version
    zipRecycle
where when one list is empty it starts recycling from its start
until the other list completes. For example:
    zipRecycle(List(1, 2, 3), List(1, 2, 3, 4, 5, 6, 7))
    = List((1, 1), (2, 2), (3, 3), (1, 4), (2, 5), (3, 6), (1, 7))
 */
/* This helper removes elements from the left of a list:
    headRemover (List(1, 2, 3, 4, 5, 6, 7), 3) = List(4, 5, 6, 7)
If the number of elements to be removed is greater than the length of the list,
it returns the empty list:
    headRemover(List(7), 3) = Nil
 */
def headRemover(numbers: List[Int], size: Int): List[Int] =
  if size > numbers.length then Nil
  else if size == 0 then numbers
  else headRemover(numbers.tail, size - 1)

/* We call this helper assuming that nums1.length <= nums2.length */
def zipRecycleHelper(
    nums1: List[Int],
    nums2: List[Int],
    order: Boolean
): List[(Int, Int)] =
  if nums2.isEmpty then Nil
  else
    val zipped = if order then myZip(nums1, nums2) else myZip(nums2, nums1)
    zipped ++ zipRecycleHelper(nums1, headRemover(nums2, nums1.length), order)

/* if nums1.length >= nums2.length
then we switch their places and the order in which pairs get zipped */
def zipRecycle(nums1: List[Int], nums2: List[Int]): List[(Int, Int)] =
  if nums1.isEmpty || nums2.isEmpty then Nil
  else if nums1.length < nums2.length
  then zipRecycleHelper(nums1, nums2, true)
  else zipRecycleHelper(nums2, nums1, false)

/* 12.
Lesser challenge: Write a version
    zipOpt
of myZip with return type Option[List[(Int, Int)]].
This version should return Some of a list
when the original lists have the same length,
and None if they do not.
 */
def zipOpt(nums1: List[Int], nums2: List[Int]): Option[List[(Int, Int)]] =
  if nums1.length == nums2.length
  then Some(myZip(nums1, nums2))
  else None

/* 13.
Write a function
    lookup: (List[(String, Int)], String) => Option[Int]
that takes a list of pairs (s, i) and also a string s2 to look up.
It then goes through the list of pairs looking for the string s2
in the first component.
If it finds a match with corresponding number i, then it returns Some i.
If it does not, it returns None.
 */
def lookup(strints: List[(String, Int)], s2: String): Option[Int] =
  if strints.isEmpty
  then None
  else if strints.head._1 == s2
  then Some(strints.head._2)
  else lookup(strints.tail, s2)

/* 14.
Write a function
    splitUp: List[Int] => (List[Int], List[Int])
that given a list of Integers creates two lists of Integers,
one containing the non-negative entries,
the other containing the negative entries.
Relative order must be preserved:
All non-negative entries must appear in the same
order in which they were on the original list,
and similarly for the negative entries.
 */
def splitUp(numbers: List[Int]): (List[Int], List[Int]) =
  if numbers.isEmpty then (Nil, Nil)
  else
    val listPair = splitUp(numbers.tail)
    val pos = listPair._1
    val neg = listPair._2
    if numbers.head < 0
    then (pos, (numbers.head) :: neg)
    else ((numbers.head) :: pos, neg)

/* 15.
Write a version
    mySplitAt: (List[Int], Int) => (List[Int], List[Int])
of the previous function that takes an extra "threshold" parameter,
and uses that instead of 0 as the separating poInt for the two resulting lists.
 */
def mySplitAt(numbers: List[Int], threshold: Int): (List[Int], List[Int]) =
  if numbers.isEmpty then (Nil, Nil)
  else
    val listPair = mySplitAt((numbers.tail), threshold)
    val bigger = listPair._1
    val smaller = listPair._2
    if numbers.head < threshold
    then (bigger, (numbers.head) :: smaller)
    else ((numbers.head) :: bigger, smaller)

/* 16.
Write a function
    isSorted: List[Int] => Boolean
that given a list of Integers determines whether the list is
sorted in increasing order.
 */
def isSorted(numbers: List[Int]): Boolean =
  numbers.isEmpty || numbers.tail.isEmpty ||
    numbers.head < numbers.tail.head && isSorted(numbers.tail)

/* 17.
Write a function
    isAnySorted : List[Int] => Booleanean,
that given a list of Integers determines whether the list
is sorted in either increasing or decreasing order.
 */
def isAnySorted(numbers: List[Int]): Boolean =
  isSorted(numbers) || isSorted(numbers.reverse)

/* 18.
Write a function
    sortedMerge: (List[Int], List[Int]) => List[Int]
that takes two lists of Integers that are each sorted from smallest to largest,
and merges them Into one sorted list. For example:
    sortedMerge (List(1, 4, 7), List(5, 8, 9)) = List(1, 4, 5, 7, 8, 9)
 */
def sortedMerge(nums1: List[Int], nums2: List[Int]): List[Int] =
  if nums1.isEmpty || nums2.isEmpty
  then nums1 ++ nums2
  else if nums1.head < nums2.head
  then nums1.head :: sortedMerge(nums1.tail, nums2)
  else nums2.head :: sortedMerge(nums1, nums2.tail)

/* 19.
Write a sorting function
    qsort: List[Int] => List[Int]
that works as follows:
Takes the first element out, and uses it as the "threshold" for mySplitAt.
It then recursively sorts the two lists produced by mySplitAt.
Finally it brings the two lists together.
(Don't forget that element you took out, it needs to get back in at some point).
You could use sortedMerge for the "bring together" part,
but you do not need to as all the numbers in one list
are less than all the numbers in the other.)
 */
def qsort(numbers: List[Int]): List[Int] =
  if numbers.isEmpty then Nil
  else
    val lists = mySplitAt(numbers.tail, numbers.head)
    val bigger = lists._1
    val smaller = lists._2
    qsort(smaller) ++ (numbers.head :: qsort(bigger))
    /* sortedMerge(qsort smaller, (numbers.head) :: qsort bigger) also works */

/* 20.
Write a function
    divide: List[Int] => (List[Int], List[Int])
that takes a list of Integers and produces two lists by
alternating elements between the two lists. For example:
    divide (List(1, 2, 3, 4, 5, 6, 7)) = (List(1, 3, 5, 7), List(2, 4, 6))
 */
def divide(numbers: List[Int]): (List[Int], List[Int]) =
  if numbers.isEmpty then (Nil, Nil)
  else if numbers.tail.isEmpty then (numbers, Nil)
  else
    val lists = divide(numbers.tail.tail)
    val firsts = lists._1
    val seconds = lists._2
    (numbers.head :: firsts, numbers.tail.head :: seconds)

/* 21.
Write another sorting function
    notSoQuickSort : List[Int] => List[Int]
that works as follows:
Given the initial list of Integers, splits it in two lists using divide,
then recursively sorts those two lists,
then merges them together with sortedMerge.
 */
def notSoQuickSort(numbers: List[Int]): List[Int] =
  if numbers.isEmpty then Nil
  else if numbers.tail.isEmpty then numbers
  else
    val lists = divide(numbers)
    val firsts = lists._1
    val seconds = lists._2
    sortedMerge(notSoQuickSort(firsts), notSoQuickSort(seconds))

/* 22.
Write a function
    fullDivide: (Int, Int) => (Int, Int) that given two numbers k and n
it attempts to evenly divide k Into n as many times as possible,
and returns a pair (d, n2) where d is the number of times
while n2 is the resulting n after all those divisions.
Examples:
    fullDivide(2, 40) = (3, 5) because 2*2*2*5 = 40 and
    fullDivide(3, 10) = (0, 10) because 3 does not divide 10.
 */
def fullDivide(pair: (Int, Int)): (Int, Int) =
  if pair._2 % pair._1 != 0 then (0, pair._2)
  else
    val quotient = pair._2 / pair._1
    val rest = fullDivide(pair._1, quotient)
    val power = rest._1
    val divisor = rest._2
    (1 + power, divisor)

/* 23.
Using fullDivide, write a function
    factorize: Int => List[(Int, Int)]
that given a number n returns a list of pairs (d, k)
where d is a prime number dividing n and k is the number of times it fits.
The pairs should be in increasing order of prime factor,
and the process should stop when the divisor considered surpasses the square root of n.
If you make sure to use the reduced number n2 given by fullDivide for each next step,
you should not need to test if the divisors are prime:
If a number divides Into n, it must be prime (if it had prime factors,
they would have been earlier prime factors of n and thus reduced earlier).
Examples:
    factorize(20) = List((2, 2), (5, 1))
    factorize(36) = List((2, 2), (3, 2))
    factorize(1) = Nil
 */
def factorizeHelper(current: Int, divisor: Int): List[(Int, Int)] =
  val realDiv = divisor.toDouble
  val realCur = current.toDouble

  /* if we found no divisors up to sqrt of current, then it's prime!
      This is computationally advantageous only for really large primes */
  if realDiv > math.sqrt(realCur)
  then List((current, 1))
  else
    val factors = fullDivide(divisor, current)
    val quotient = factors._2
    val power = factors._1
    if quotient == 1 then List((divisor, power))
    else if power == 0 then factorizeHelper(quotient, divisor + 1)
    else (divisor, power) :: factorizeHelper(quotient, divisor + 1)

def factorize(number: Int): List[(Int, Int)] =
  if number == 1 then Nil
  else factorizeHelper(number, 2)

/* 24.
Write a function
    multiply: List[(Int, Int)] => Int
that given a factorization of a number n as described
in the previous problem computes back the number n.
So this should do the opposite of factorize.
 */
def multiplyHelper(pair: (Int, Int)): Int =
  if pair._2 == 0 then 1
  else pair._1 * multiplyHelper(pair._1, pair._2 - 1)

def multiply(pairlist: List[(Int, Int)]): Int =
  if pairlist.isEmpty then 1
  else multiplyHelper(pairlist.head) * multiply(pairlist.tail)

/* 25.
Challenge (hard): Write a function
    allProducts: List[(Int, Int)] => List[Int]
that given a factorization list result from factorize creates a list of all
possible products produced from using Some or all of those prime factors
no more than the number of times they are available.
This should end up being a list of all the divisors of
the number n that gave rise to the list. Example:
    allProducts(List((2, 2), (5, 1))) = List(1, 2, 4, 5, 10, 20)
For extra challenge, your recursive process should return the numbers
in this order, as opposed to sorting them afterwards.
 */
def allProducts(pairlist: List[(Int, Int)]): List[Int] = Nil

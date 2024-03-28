package B.w1

/*
    Int Int Int -> List
    produces list of numbers from low to high (including low and possibly high)
    separated by stride and in sorted order.
    assumes: stride is positive
 */
def sequence(low: Int, high: Int, stride: Int): List[Int] =
  require(stride > 0)
  if high < low then Nil
  else low :: sequence(low + stride, high, stride)

/*
    (String List) String -> (String List)
    appends each string in list with given suffix
 */
def stringAppendMap(strs: List[String], suffix: String): List[String] =
  strs map (_ + suffix)

/*
    List Int -> Int or Error
    produces ith elt of list where i = n % length of list,
    error if n < 0 or list empty
 */
def listNthMod[T](xs: List[T], n: Int): T =
  require(0 <= n)
  require(xs.nonEmpty)
  xs(n % xs.length)

/*
    Stream Integer -> (Integer List)
    produces list holding first n values produced by stream in order
    assumes: n is non-negative
 */
def streamForNSteps(stream: LazyList[Int], n: Int): List[Int] =
  require(0 <= n)
  if n == 0 then Nil
  else stream.head :: streamForNSteps(stream.tail, n - 1)

/*
    -> Stream
    produces stream of natural numbers except numbers divisible by 5 are negated
    1, 2, 3, 4, -5, 6, 7, 8, 9, -10, 11, ... etc.
    scala> funnyNumberStream.take(10).mkString(" ")
    val res1: String = 1 2 3 4 -5 6 7 8 9 -10
 */
def funnyNumberStream: LazyList[Int] =
  def helper(n: Int): LazyList[Int] =
    val sign = if n % 5 == 0 then -1 else 1
    (sign * n) #:: helper(n + 1)
  helper(1)

/*
    -> Stream
    produces stream that alternates between the strings "dan.jpg" and "dog.jpg"
 */
def danThenDog: LazyList[String] =
  def helper(flag: Boolean): LazyList[String] =
    val hd = if flag then "dan.jpg" else "dog.jpg"
    hd #:: helper(!flag)
  helper(true)
/*
    Stream -> Stream
    produces pairs from stream, with zero added
 */
def streamAddZero[T](stream: LazyList[T]): LazyList[(Int, T)] =
  (0, stream.head) #:: streamAddZero(stream.tail)

/*
    List List -> Stream
    produces stream of pairs of elements from two lists,
    which cycles forever through the two lists
    assumes: both lists are non-empty
 */
def cycleLists[S, T](ss: List[S], ts: List[T]): LazyList[(S, T)] =
  def helper(n: Int): LazyList[(S, T)] =
    (listNthMod(ss, n), listNthMod(ts, n)) #:: helper(n + 1)
  helper(0)

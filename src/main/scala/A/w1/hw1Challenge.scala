package A.w1

def insertHelper(x: Int, xs: List[Int]): List[Int] =
  if xs.isEmpty then List(x)
  else if x <= xs.head then x :: insertSort(xs)
  else xs.head :: insertHelper(x, xs.tail)

def insertSort(xs: List[Int]): List[Int] =
  if xs.isEmpty
  then xs
  else insertHelper(xs.head, xs.tail)

def removeDuplicates(xs: List[Int]): List[Int] =
  val ys = insertSort(xs)
  if ys.isEmpty || ys.tail.isEmpty then ys
  else if ys.head == ys.tail.head
  then removeDuplicates(ys.tail)
  else ys.head :: removeDuplicates(ys.tail)

def numberInMonthsChallenge(dates: List[Date], months: List[Int]): Int =
  numberInMonths(dates, removeDuplicates(months))

def datesInMonthsChallenge(dates: List[Date], months: List[Int]): List[Date] =
  datesInMonths(dates, removeDuplicates(months))

def getNthInt(xs: List[Int], n: Int): Int =
  if n == 1
  then xs.head
  else getNthInt(xs.tail, n - 1)

def reasonableDate(date: Date): Boolean =
  val year = date._1
  val month = date._2
  val day = date._3
  val leap = year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)
  val febLen = if leap then 29 else 28
  val lengths = List(31, febLen, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

  year > 0 &&
  month >= 1 &&
  month <= 12 &&
  day >= 1 &&
  day <= getNthInt(lengths, month)

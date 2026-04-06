package curriculum
package pla
package w1

type Date = (Int, Int, Int) // year, month, day
val months = List(
  "January",
  "February",
  "March",
  "April",
  "May",
  "June",
  "July",
  "August",
  "September",
  "October",
  "November",
  "December"
)

/*  returns true if date1 is older than date2  */
def isOlder(date1: Date, date2: Date): Boolean =
  date1._1 < date2._1 || (date1._1 == date2._1 && date1._2 < date2._2) ||
    (date1._1 == date2._1 && date1._2 == date2._2 && date1._3 < date2._3)

/*  returns how many dates in list are in given month  */
def numberInMonth(dates: List[Date], month: Int): Int =
  if dates.isEmpty then 0
  else
    val rest = numberInMonth(dates.tail, month)
    if month == dates.head._2 then rest + 1 else rest

/*  returns how many dates in date list are in any of the months in months list
 *  Assumes that int list has no numbers repeated  */
def numberInMonths(dates: List[Date], months: List[Int]): Int =
  if months.isEmpty then 0
  else numberInMonth(dates, months.head) + numberInMonths(dates, months.tail)

/*  returns list of dates that are in given month  */
def datesInMonth(dates: List[Date], month: Int): List[Date] =
  if dates.isEmpty then Nil
  else
    val rest = datesInMonth(dates.tail, month)
    if dates.head._2 == month then dates.head :: rest
    else rest

/*  returns list of dates that are in any of the months in the list of months
 *  Assumes that int list has no numbers repeated  */
def datesInMonths(dates: List[Date], months: List[Int]): List[Date] =
  if months.isEmpty then Nil
  else datesInMonth(dates, months.head) ++ datesInMonths(dates, months.tail)

/*  returns nth string in list
 *  Assumes list is nonempty and n >= 1  */
def getNth(strlist: List[String], n: Int): String =
  if n == 1 then strlist.head
  else getNth(strlist.tail, n - 1)

/*  returns string of the form January 20, 2013 (for example)  */
def dateToString(date: Date): String =
  getNth(months, (date._2)) + " " + date._3.toString + ", " + date._1.toString

/*  returns first n such that sum of first n elts of list < sum,
 *  but of first n + 1 elts >= sum
 *  Assumes sum > 0, list has positive numbers,
 *  and entire list sums to more than sum   */
def numberBeforeReachingSum(sum: Int, poslist: List[Int]): Int =
  if sum <= poslist.head then 0
  else 1 + numberBeforeReachingSum(sum - poslist.head, poslist.tail)

/*  returns what month given day[1, 365] is in  */
def whatMonth(day: Int): Int =
  val daysInMonths = List(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
  numberBeforeReachingSum(day, daysInMonths) + 1

/*  returns list of months of all days in between given two days  */
def monthRange(day1: Int, day2: Int): List[Int] =
  if day1 > day2 then Nil
  else whatMonth(day1) :: monthRange(day1 + 1, day2)

/*  returns oldest date in list, NONE if list empty  */
def oldest(dates: List[Date]): Option[Date] =
  if dates.isEmpty then None
  else
    val tlAns = oldest(dates.tail)
    if !tlAns.isDefined || isOlder(dates.head, tlAns.get) then Some(dates.head)
    else tlAns

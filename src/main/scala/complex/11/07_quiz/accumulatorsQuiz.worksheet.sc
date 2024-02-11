//  PROBLEM 1:
//  Assuming the use of at least one accumulator,
//  design a function that consumes a list of strings,
//  and produces the length of the longest string in the list.
// (listof String) -> Natural
// produces length of longest string in list
longest(Nil) == 0
longest(List("a", "b", "c")) == 1
longest(List("a", "bc")) == 2
longest(List("a", "bc", "de")) == 2
longest(List("a", "bc", "def")) == 3
longest(List("abc", "de", "f")) == 3
longest(List("abc", "def", "nopr", "ghi")) == 4

// def longest(strings: List[String]): Int = 0 // stub
def longest(strings: List[String]): Int =
  def helper(list: List[String], soFar: Int): Int = list match
    case head :: next => helper(next, math.max(soFar, head.length))
    case Nil          => soFar
  helper(strings, 0)

//  PROBLEM 2:
//  The Fibbonacci Sequence https://en.wikipedia.org/wiki/Fibonacci_number is
//  the sequence 0, 1, 1, 2, 3, 5, 8, 13,... where the nth element is equal to
//  n-2 + n-1.
//  Design a function that given a list of numbers at least two elements long,
//  determines if the list obeys the fibonacci rule, n-2 + n-1 = n, for every
//  element in the list. The sequence does not have to start at zero, so for
//  example, the sequence 4, 5, 9, 14, 23 would follow the rule.

// (listof Number) -> Boolean
// produce true if list obeys fibonacci rule
// ASSUME: list has at least two numbers in it
// (check-expect (fib? (list 0 0)) true)
isFibonacci(List())
isFibonacci(List(0))
isFibonacci(List(0, 0))
isFibonacci(List(5, 110))
isFibonacci(List(5, 110, 115))
isFibonacci(List(0, 1, 1, 2, 3, 5, 8, 13))
isFibonacci(List(1, 1, 2, 3, 5, 8, 13))
!isFibonacci(List(1, 1, 2, 3, 8, 13))
isFibonacci(List(4, 5, 9, 14, 23))
!isFibonacci(List(4, 5, 9, 13, 24))
!isFibonacci(List(5, 7, 12, 19, 32))

// def isFibonacci(list: List[Int]): Boolean = false // stub
def isFibonacci(list: List[Int]): Boolean = list match
  case Nil                 => true
  case head :: Nil         => true
  case head :: neck :: Nil => true
  case head :: neck :: shoulder :: tail =>
    (shoulder == head + neck) && isFibonacci(neck :: shoulder :: tail)

//  PROBLEM 3:
//  Refactor the function below to make it tail recursive.
// Natural -> Natural
// produces the factorial of the given number
fact(0) == 1
fact(3) == 6
fact(5) == 120

def fact(n: Int): Int =
  def helper(m: Int, acc: Int): Int =
    if m == 0 then acc
    else helper(m - 1, acc * m)
  helper(n, 1)

//  PROBLEM 4:
//  Recall the data definition for Region from the Abstraction Quiz. Use a
//  worklist accumulator to design a tail recursive function that counts the
//  number of regions within and including a given region.
//  So (count-regions CANADA) should produce 7
enum RegionType:
  case Continent, Country, Province, State, City
import RegionType.*

case class Region(
    name: String,
    regionType: RegionType,
    subregions: List[Region]
)

val VANCOUVER = Region("Vancouver", City, Nil)
val VICTORIA = Region("Victoria", City, Nil)
val BC = Region("British Columbia", Province, List(VANCOUVER, VICTORIA))
val CALGARY = Region("Calgary", City, Nil)
val EDMONTON = Region("Edmonton", City, Nil)
val ALBERTA = Region("Alberta", Province, List(CALGARY, EDMONTON))
val CANADA = Region("Canada", Country, List(BC, ALBERTA))

// Region -> Natural
// produce number of regions within and including a given region
countRegions(VANCOUVER) == 1
countRegions(VICTORIA) == 1
countRegions(BC) == 3
countRegions(CALGARY) == 1
countRegions(EDMONTON) == 1
countRegions(ALBERTA) == 3
countRegions(CANADA) == 7

// def countRegions(region: Region): Int = 0 // stub
def countRegions(region: Region): Int =
  def helperOne(reg: Region, soFar: Int, toDo: List[Region]): Int =
    helperList(soFar + 1, reg.subregions ::: toDo)
  def helperList(soFar: Int, toDo: List[Region]): Int = toDo match
    case head :: next => helperOne(head, soFar, next)
    case Nil          => soFar
  helperOne(region, 0, Nil)

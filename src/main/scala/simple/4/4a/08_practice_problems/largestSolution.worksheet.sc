def fnForIntList(intList: List[Int]) = intList match
  case head :: next => ???
  case Nil          => ???

// Template rules used:
//  - one of: 2 cases
//  - atomic distinct: empty
//  - compound: (cons Number ListOfNumber)
//  - self-reference: (rest lon) is ListOfNumber

// Functions:
// PROBLEM:
// Design a function that consumes a list of numbers and produces Some of the
// largest number in the list. You may assume that all numbers in the list are
// greater than 0. If the list is empty, produce None.
// ListOfNumber -> Option Number
// produce the largest number in the given list, or None if empty
largest(Nil) == None
largest(List(60, 42)) == Some(60)
largest(List(10, 20, 50)) == Some(50)

// def largest(intList: List[Int]): Option[Int] = None // stub

// <template from ListOfNumber>
def largest(intList: List[Int]): Option[Int] = intList match
  case head :: next =>
    largest(next) match
      case None        => Some(head)
      case Some(value) => Some(math.max(head, value))
  case Nil => None

// This is a pattern that occurs often when the return type is an Option:
def fnForListOption[T](list: List[T]): Option[T] = list match
  case head :: next =>
    fnForListOption(next) match
      case None        => ??? // use head, Some, None
      case Some(value) => ??? // use head, value, Some, None
  case Nil => ??? // usually None

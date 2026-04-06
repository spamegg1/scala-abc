// Data definitions:
// Remember the data definition for a list of numbers we designed in Lecture 5f:
// (if this data definition does not look familiar, please review the lecture)
// ListOfNumber is one of:
//  - empty
//  - (cons Number ListOfNumber)
// interp. a list of numbers
val intList1 = List[Int]()
val intList2 = List(60, 42)

def fnForIntList(intList: List[Int]) = intList match
  case head :: next => ??? // head, fnForIntList(next)
  case Nil          => ???

// Template rules used:
//  - one of: 2 cases
//  - atomic distinct: empty
//  - compound: (cons Number ListOfNumber)
//  - self-reference: (rest lon) is ListOfNumber

// Functions:
// PROBLEM:
// Design a function that consumes a list of numbers and doubles every number
// in the list. Call it double-all.
// ListOfNumber -> ListOfNumber
// double every number in the given list
doubleAll(intList1) == intList1
doubleAll(intList2) == List(120, 84)

// def doubleAll(intList: List[Int]): List[Int] = Nil // stub
def doubleAll(intList: List[Int]): List[Int] = intList match
  case head :: next => head * 2 :: doubleAll(next)
  case Nil          => Nil

// Data definitions:
// PROBLEM A:
// Design a data definition to represent a list of booleans.
// Call it ListOfBoolean.
// ListOfBoolean is one of:
//  - empty
//  - (cons Boolean ListOfBoolean)
// interp. a list of boolean values
val boolList1 = List[Boolean]()
val boolList2 = List(true, false)

def fnForBoolList(boolList: List[Boolean]) = boolList match
  case head :: next => ???
  case Nil          => ???

// Template rules used:
//  - one of: 2 cases
//  - atomic distinct: empty
//  - compound: (cons Boolean ListOfBoolean)
//  - self-reference: (rest lob) is ListOfBoolean

// we can "expand" this template but it may be unnecessary:
def fnForBoolList2(boolList: List[Boolean]) = boolList match
  case head :: next =>
    head match
      case true  => ???
      case false => ???
  case Nil => ???

// we can rewrite the second template without "nested patterns":
def fnForBoolList3(boolList: List[Boolean]) = boolList match
  case true :: next  => ???
  case false :: next => ???
  case Nil           => ???

// Functions:
// PROBLEM B:
// Design a function that consumes a list of boolean values and produces true
// if every value in the list is true. If the list is empty, your function
// should also produce true. Call it all-true?
// ListOfBoolean -> Boolean
// produces true if all values in the given list are true or the list is empty
allTrue(boolList1)
!allTrue(boolList2)
allTrue(List(true, true, true))

// def allTrue(boolList: List[Boolean]): Boolean = false // stub
// <template from ListOfBoolean>
def allTrue(boolList: List[Boolean]): Boolean = boolList match
  case head :: next => head && allTrue(next) // remember && is short-circuiting
  case Nil          => true

allTrue2(List(true, true, true))

def allTrue2(boolList: List[Boolean]): Boolean = boolList match
  case head :: next =>
    head match
      case true  => allTrue(next)
      case false => false // no need to look further
  case Nil => true

allTrue3(List(true, true, true))

def allTrue3(boolList: List[Boolean]): Boolean = boolList match
  case true :: next  => allTrue(next)
  case false :: next => false
  case Nil           => true

// Data definitions:
// Remember the data definition for a list of strings we designed in Lecture 5c:
def fnForStringList(stringList: List[String]) = stringList match
  case head :: next => ??? // head, fnForStringList(next)
  case Nil          => ???

// Template rules used:
// - one of: 2 cases
// - atomic distinct: empty
// - compound: (cons String ListOfString)
// - atomic non-distinct: (first los) is  String
// - self-reference: (rest los) is ListOfString

// Functions:
// PROBLEM:
// Design a function that consumes a list of strings and determines the total
// number of characters (single letters) in the list.
// Call it total-string-length.
// ListOfString -> Natural
// calculates the total number of characters in the given list
totalStringLength(Nil) == 0
totalStringLength(List("a", "b", "c")) == 3
totalStringLength(List("function", "design")) == 14

// def totalStringLength(stringList: List[String]): Int = 0 // stub

def totalStringLength(stringList: List[String]): Int = stringList match
  case head :: next => head.length + totalStringLength(next)
  case Nil          => 0

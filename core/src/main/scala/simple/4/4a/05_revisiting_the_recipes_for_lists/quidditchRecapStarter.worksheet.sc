// PROBLEM:
// Imagine that you are designing a program that will keep track of
// your favorite Quidditch teams. (http://iqaquidditch.org/).
// (A) Design a data definition to represent a list of Quidditch teams.
// (B) Design a function that consumes ListOfString and produces true if
//     the list includes "UBC".
// ListOfString is one of:
//  - empty
//  - (cons String ListOfString)
// interp. a list of strings
enum ListOfString:
  case EmptyList
  case Constructor(head: String, tail: ListOfString)
import ListOfString.*
// sealed trait ListOfString
// case object EmptyList extends ListOfString
// case class Constructor(head: String, tail: ListOfString) extends ListOfString

val list1 = EmptyList
val list2 = Constructor("McGill", EmptyList)
val list3 = Constructor("UBC", list2)
val list4 = Constructor("McGill", Constructor("UBC", list1))

def fnForListOfString(list: ListOfString) = list match
  case EmptyList               => ???
  case Constructor(head, tail) => ???

// Template rules used:
//  - one of: 2 cases
//  - atomic distinct: empty
//  - compound: (cons String ListOfString)
//  - [                                  ]

// ListOfString -> Boolean
// produce true if los includes "UBC"
!containsUBC(EmptyList)
!containsUBC(list2)
containsUBC(list3)
containsUBC(list4)

// def containsUBC(list: ListOfString): Boolean = false // stub
def containsUBC(list: ListOfString): Boolean = list match
  case EmptyList                => false
  case Constructor("UBC", tail) => true
  case Constructor(_, tail)     => containsUBC(tail)

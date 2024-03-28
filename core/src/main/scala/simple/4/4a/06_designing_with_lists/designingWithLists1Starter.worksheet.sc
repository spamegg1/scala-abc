// PROBLEM:
// You've been asked to design a program having to do with all the owls
// in the owlery.
// (A) Design a data definition to represent the weights of all the owls.
//     For this problem call it ListOfNumber.
// (B) Design a function that consumes the weights of owls and produces
//     the total weight of all the owls.
// (C) Design a function that consumes the weights of owls and produces
//     the total number of owls.

// Data definitions:
// ListOfNumber is one of:
//  - empty
//  - (cons Number ListOfNumber)
// interp. each number in the list is an owl weight in ounces
val intList1 = List[Int]()
val intList2 = List(60, 42)

def fnForIntList(intList: List[Int]) = intList match
  case head :: next => ??? // ???(head, fnForIntList(next))
  case Nil          => ???

// Template rules used:
//  - one of: 2 cases
//  - atomic distinct: empty
//  - compound: (cons Number ListOfNumber)
//  - self-reference: (rest lon) is ListOfNumber

// Functions:
// ListOfNumber -> Number
// produce sum of weights of owls in lon
total(intList1) == 0
total(intList2) == 102

// def total(intList: List[Int]): Int = 0 // stub
def total(intList: List[Int]): Int = intList match
  case head :: next => head + total(next)
  case Nil          => 0

// Problem:
// It is often useful to be able to tell whether the first part of a sequence of
// characters matches a given pattern. In this problem you will design a (somewhat
// limited) way of doing this.
// Assume the following type comments and examples:

// Data Definitions:
// Pattern is one of:
enum Pattern:
  case Alphabetic, Numeric
import Pattern.*
// interp.
//  "A" means the corresponding letter must be alphabetic.
//  "N" means it must be numeric.  For example:
//      (list "A" "N" "A" "N" "A" "N")
//   describes Canadian postal codes like:
//      (list "V" "6" "T" "1" "Z" "4")
// (define PATTERN1 (list "A" "N" "A" "N" "A" "N"))

// Now design a function that consumes Pattern and ListOf1String
// and produces true if the pattern matches the ListOf1String. For example,
// (pattern-match? (list "V" "6" "T" "1" "Z" "4")
//                 (list "A" "N" "A" "N" "A" "N"))
// should produce true. If the ListOf1String is longer than the pattern, but the
// first part matches the whole pattern produce true. If the ListOf1String is
// shorter than the Pattern you should produce false.
// Treat this as the design of a function operating on 2 complex data. After
// your signature and purpose, you should write out a cross product of type
// comments table. You should reduce the number of cases in your cond to 4 using
// the table, and you should also simplify the cond questions using the table.
// You should use the following helper functions in your solution:

// Functions:
// Pattern ListOf1String -> Boolean
// produces true if ListOf1String matches the given pattern
// CROSS PRODUCT OF TYPE COMMENTS TABLE
//
//                                      LO1s
//                             empty        (cons 1String ListOf1String)
//
// P   empty                   true          true
// a
// t  (cons "A" Pattern)       false        (and (alpha? (first los))
// t                                             (match-head? <rests>))
// e
// r  (cons "N" Pattern)       false        (and (numer? (first los))
// n                                             (match-head? <rests>))
patternMatch(Nil, "")
patternMatch(Nil, "a")
!patternMatch(List(Alphabetic), "")
!patternMatch(List(Numeric), "")
!patternMatch(List(Alphabetic), "")
patternMatch(List(Alphabetic, Numeric, Alphabetic), "x3y")
patternMatch(List(Alphabetic, Numeric, Alphabetic), "x3yz")
patternMatch(List(Alphabetic, Numeric, Alphabetic), "x3y4")
!patternMatch(List(Alphabetic, Numeric, Alphabetic), "13y")
patternMatch(List(Numeric, Alphabetic, Numeric), "1a4")
!patternMatch(List(Numeric, Alphabetic, Numeric), "1bc")
patternMatch(
  List(Alphabetic, Numeric, Alphabetic, Numeric, Alphabetic, Numeric),
  "V6T1Z4"
)

// def patternMatch(patterns: List[Pattern], string: String): Boolean = false

// template taken from cross product table
// 6 cases simplifed to 4
def patternMatch(patterns: List[Pattern], string: String): Boolean =
  (patterns, string) match
    // case (Nil, "")                => true // redundant case!
    // case (Alphabetic :: next, "") => false // redundant case!
    // case (Numeric :: next, "")    => false // redundant case!
    case (Nil, _) => true
    case (_, "")  => false // summarizes two cases above
    case (Alphabetic :: next, string) =>
      string(0).isLetter && patternMatch(next, string.drop(1))
    case (Numeric :: next, string) =>
      string(0).isDigit && patternMatch(next, string.drop(1))

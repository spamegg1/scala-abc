import doodle.image.Image
import doodle.image.syntax.all.ImageOps
import doodle.java2d.java2dRenderer
import cats.effect.unsafe.implicits.global

// PROBLEM:
// You're working with a revised version of the LetterGrade data definition that
// you saw in lecture to design a function that produces true if a given
// LetterGrade represents a passing grade in a course. You're working through HtDF
// and have completed the signature, purpose, stub and examples, but you're getting
// an error message. Uncomment the code in the box below and revise it until
// all the tests run (even though several tests still fail).
// LetterGrade is one of:
//  - "A"
//  - "B"
//  - "C"
//  - "D"
//  - "F"
// interp. a grade in a course
enum LetterGrade:
  case A, B, C, D, F
import LetterGrade.*
// <examples are redundant for enumerations>

def fnForLetterGrade(grade: LetterGrade) = grade match
  case A => ???
  case B => ???
  case C => ???
  case D => ???
  case F => ???

// LetterGrade -> Boolean
// produce true if the LetterGrade represents a passing grade
pass(A)
pass(B)
pass(C)
pass(D)
!pass(F)

// def pass(grade: LetterGrade): Boolean = false // stub

def pass(grade: LetterGrade) = grade match
  case A => true
  case B => true
  case C => true
  case D => true
  case F => false

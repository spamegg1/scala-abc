// PROBLEM:
// Using the LetterGrade data definition below design a function that
// consumes a letter grade and produces the next highest letter grade.
// Call your function bump-up.


// Data definitions:

// LetterGrade is one of:
//  - A
//  - B
//  - C
// interp. the letter grade in a course
enum LetterGrade:
  case A, B, C

import LetterGrade.*
// <examples are redundant for enumerations>

// Template
def fnForLetterGrade(letterGrade: LetterGrade) = letterGrade match
  case A => ???
  case B => ???
  case C => ???

// Template rules used:
//  - one-of: 3 cases
//  - atomic distinct: "A"
//  - atomic distinct: "B"
//  - atomic distinct: "C"


// Functions:

// LetterGrade -> LetterGrade
// produce next highest letter grade (no change for A)
bumpUp(A) == A
bumpUp(B) == A
bumpUp(C) == B

// def bumpUp(letterGrade: LetterGrade): LetterGrade = A // stub

// <use template from LetterGrade>
def bumpUp(letterGrade: LetterGrade) = letterGrade match
  case A => A
  case B => A
  case C => B

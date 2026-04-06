import doodle.core.*
import doodle.core.Color.*
import doodle.image.Image
import doodle.image.Image.Elements.*
import doodle.java2d.*
import doodle.reactor.Reactor
import cats.effect.unsafe.implicits.global
import concurrent.duration.FiniteDuration
import doodle.syntax.all.*
import doodle.image.syntax.all.*

// PROBLEM:
// Eva is trying to decide where to go to university. One important factor for her is
// tuition costs. Eva is a visual thinker, and has taken Systematic Program Design,
// so she decides to design a program that will help her visualize the costs at
// different schools. She decides to start simply, knowing she can revise her design
// later.
// The information she has so far is the names of some schools as well as their
// international student tuition costs. She would like to be able to represent that
// information in bar charts like this one:
//         .
// (A) Design data definitions to represent the information Eva has.
// (B) Design a function that consumes information about schools and their
//     tuition and produces a bar chart.
// (C) Design a function that consumes information about schools and produces
//     the school with the lowest international student tuition.

// Constants:
val FONTSIZE = 24
val FONTCOLOR = black
val YSCALE = 0.01
val BARWIDTH = 30
val BARCOLOR = blue

// Data definitions:
case class School(name: String, tuition: Double):
  require(0 <= tuition)
// interp. the school's name, international-students tuition in USD
val school1 = School("School1", 27797)
val school2 = School("School2", 23300)
val school3 = School("School3", 28500)

def fnForSchool(school: School) = ??? // school.name, school.tuition

// Template rules used:
//  - compound: (make-school String Natural)

// ListOfSchool is one of:
//  - empty
//  - (cons School ListOfSchool)
// interp. a list of schools
val schools1 = List[School]()
val schools2 = List(school1, school2, school3)

def fnForSchoolList(schools: List[School]) = schools match
  case head :: next => ??? // head, fnForSchoolList(next)
  case Nil          => ???

// Template rules used:
//  - one of: 2 cases
//  - atomic distinct: empty
//  - compound: (cons School ListOfSchool)
//  - reference: (first los) is School
//  - self-reference: (rest los) is ListOfSchool

// Functions:
// ListOfSchool -> Image
// produce bar chart showing names and tuitions of consumed schools
// def chart(schools: List[School]): Image = Image.empty // stub
def chart(schools: List[School]): Image = schools match
  case head :: next => makeBar(head) beside chart(next)
  case Nil          => Image.empty

// School -> Image
// produce the bar for a single school in the bar chart
// def makeBar(school: School): Image = Image.empty // stub
def makeBar(school: School): Image =
  val name = Text(school.name)
  val bar = Image
    .rectangle(BARWIDTH, school.tuition * YSCALE)
    .strokeColor(black)
    .fillColor(blue)
  name above bar

// ListOfSchool -> School
// produce the school with the lowest tuition
// ASSUME the list includes at least one school or else
//        the notion of cheapest doesn't make sense
cheapest(List(school1)) == school1
cheapest(schools2) == school2

// def cheapest(schools: List[School]): School = School("", 0.0) // stub
def cheapest(schools: List[School]): School = schools match
  // Change to base case test here, for this function
  // the base case is when the list has one element.
  case head :: Nil => head
  case head :: next =>
    val cheaper = cheapest(next)
    if head.tuition < cheaper.tuition
    then head
    else cheaper
  case Nil => School("", 0.0) // should not happen, only to satisfy type checker

// chart(schools2).draw()

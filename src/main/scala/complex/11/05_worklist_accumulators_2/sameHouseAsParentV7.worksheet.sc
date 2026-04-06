// PROBLEM:
// In the Harry Potter movies, it is very important which of the four houses a
// wizard is placed in when they are at Hogwarts. This is so important that in
// most families multiple generations of wizards are all placed in the same
// family. Design a representation of wizard family trees that includes, for
// each wizard, their name, the house they were placed in at Hogwarts and their
// children. We encourage you to get real information for wizard families from:
//    http://harrypotter.wikia.com/wiki/Main_Page
// The reason we do this is that designing programs often involves collection
// domain information from a variety of sources and representing it in the
// program as constants of some form. So this problem illustrates a fairly
// common scenario. That said, for reasons having to do entirely with making
// things fit on thescreen in later videos, we are going to use the following
// wizard family tree, in which wizards and houses both have 1 letter names.
// Data definitions:
enum House:
  case Slytherin, Griffindor, Hufflepuff, Ravenclaw
import House.*

case class Wizard(name: String, house: House, kids: List[Wizard])
// Wizard is (make-wiz String String (listof Wizard))
// interp. A wizard, with name, house and list of children.
val wizA = Wizard("A", Slytherin, Nil)
val wizB = Wizard("B", Griffindor, Nil)
val wizC = Wizard("C", Ravenclaw, Nil)
val wizD = Wizard("D", Hufflepuff, Nil)
val wizE = Wizard("E", Ravenclaw, Nil)
val wizF = Wizard("F", Ravenclaw, List(wizB))
val wizG = Wizard("G", Slytherin, List(wizA))
val wizH = Wizard("H", Slytherin, List(wizC, wizD))
val wizI = Wizard("I", Hufflepuff, Nil)
val wizJ = Wizard("J", Ravenclaw, List(wizE, wizF, wizG))
val wizK = Wizard("K", Griffindor, List(wizH, wizI, wizJ))

def fnForWizard(wizard: Wizard) =
  ??? // wizard.name, wizard.house, fnForWizardList(wizard.kids)

def fnForWizardList(kids: List[Wizard]) = kids match
  case head :: next => ??? // fnForWizard(head), fnForWizardList(next)
  case Nil          => ???

// Functions:
// PROBLEM:
// Design a function that consumes a wizard and produces the names of every
// wizard in the tree that was placed in the same house as their immediate
// parent.
// Wizard -> (listof String)
// Produce the names of every descendant in the same house as their parent.
sameHouseAsParent(wizA).isEmpty
sameHouseAsParent(wizH).isEmpty
sameHouseAsParent(wizG) == List("A")
sameHouseAsParent(wizK) == List("E", "F", "A")

// template from Wizard plus lost context accumulator
// def sameHouseAsParent(wizard: Wizard): List[Wizard] = Nil // stub
def sameHouseAsParent(wizard: Wizard): List[String] =
  def fnForWiz(wiz: Wizard, parentHouse: Option[House]): List[String] =
    if Some(wiz.house) == parentHouse
    then wiz.name :: fnForWizList(wiz.kids, Some(wiz.house))
    else fnForWizList(wiz.kids, Some(wiz.house))

  def fnForWizList(
      kids: List[Wizard],
      parentHouse: Option[House]
  ): List[String] =
    kids match
      case head :: next =>
        fnForWiz(head, parentHouse) ::: fnForWizList(next, parentHouse)
      case Nil => Nil

  fnForWiz(wizard, None)

// PROBLEM:
// Design a new function definition for same-house-as-parent that is tail
// recursive. You will need a context preserving accumulator.
sameHouseAsParentTr(wizA).isEmpty
sameHouseAsParentTr(wizH).isEmpty
sameHouseAsParentTr(wizG) == List("A")
sameHouseAsParentTr(wizK) == List("A", "F", "E")

def sameHouseAsParentTr(wizard: Wizard): List[String] =
  def fnForWiz(
      wiz: Wizard,
      parentHouse: Option[House],
      soFar: List[String],
      toDo: List[(Wizard, Option[House])]
  ): List[String] =
    fnForWizList(
      wiz.kids.map(kid => (kid, Some(wiz.house))) ::: toDo,
      Some(wiz.house),
      if Some(wiz.house) == parentHouse then wiz.name :: soFar else soFar
    )

  def fnForWizList(
      toDo: List[(Wizard, Option[House])],
      parentHouse: Option[House],
      soFar: List[String]
  ): List[String] =
    toDo match
      case (kid, parentHouse) :: next =>
        fnForWiz(kid, parentHouse, soFar, next)
      case Nil => soFar

  fnForWiz(wizard, None, Nil, Nil)

// PROBLEM:
// Design a function that consumes a wizard and produces the number of wizards
// in that tree (including the root). Your function should be tail recursive.
// Wizard -> Natural
// produces the number of wizards in that tree (including the root)
countWizards(wizA) == 1
countWizards(wizB) == 1
countWizards(wizC) == 1
countWizards(wizD) == 1
countWizards(wizE) == 1
countWizards(wizF) == 2
countWizards(wizG) == 2
countWizards(wizH) == 3
countWizards(wizI) == 1
countWizards(wizJ) == 6
countWizards(wizK) == 11
// def countWizards(wizard: Wizard): Int = 0 // stub
def countWizards(wizard: Wizard): Int =
  def fnForWiz(wizard: Wizard, toDo: List[Wizard], soFar: Int): Int =
    fnForWizList(wizard.kids ::: toDo, soFar + 1)

  def fnForWizList(toDo: List[Wizard], soFar: Int): Int = toDo match
    case head :: next => fnForWiz(head, next, soFar)
    case Nil          => soFar

  fnForWiz(wizard, Nil, 0)

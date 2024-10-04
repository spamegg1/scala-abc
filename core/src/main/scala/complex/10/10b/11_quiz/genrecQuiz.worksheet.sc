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
import scala.util.Random

//  PROBLEM 1:
//  In the lecture videos we designed a function to make a Sierpinski
//  triangle fractal.
//  Here is another geometric fractal that is made of circles rather than
//  triangles:
//  Design a function to create this circle fractal of size n and colour c.
val CUTOFF = 20

// Natural String -> Image
// produce a circle fractal of size n and colour c
def circleFractal(size: Int, color: Color): Image =
  if size <= CUTOFF then Image.circle(size).strokeColor(color)
  else
    val sub = circleFractal(size / 2, color)
    (sub beside sub) on Image.circle(size).strokeColor(color)

// circleFractal(800, purple).draw()
//  PROBLEM 2:
//  Below you will find some data definitions for a tic-tac-toe solver.
//  In this problem we want you to design a function that produces all
//  possible filled boards that are reachable from the current board.
//  In actual tic-tac-toe, O and X alternate playing. For this problem
//  you can disregard that. You can also assume that the players keep
//  placing Xs and Os after someone has won. This means that boards that
//  are completely filled with X, for example, are valid.
//  Note: As we are looking for all possible boards, rather than a winning
//  board, your function will look slightly different than the solve function
//  you saw for Sudoku in the videos, or the one for tic-tac-toe in the
//  lecture questions.
// Value is one of:
// - false
// - "X"
// - "O"
// interp. a square is either empty (represented by false)
// or has and "X" or an "O"
enum Value:
  case X, O, E
import Value.*

def fnForValue(value: Value) = value match
  case X => ???
  case O => ???
  case E => ???

// Board is (listof Value)
// a board is a list of 9 Values
type Board = List[Value]

val board0 = List(E, E, E, E, E, E, E, E, E)
val board1 = List(E, X, O, O, X, O, E, E, X)
val board2 = List(X, X, O, O, X, O, X, E, X)
val board3 = List(X, X, O, O, O, E, X, X, E)

def fnForBoard(board: Board) = board match
  case head :: next => ??? // fnForValue(head), fnForBoard(next)
  case Nil          => ???

// Board -> (listof Board)
// produce list of all possible filled boards reachable from given board
allBoards(board2) == List(
  List(X, X, O, O, X, O, X, X, X),
  List(X, X, O, O, X, O, X, O, X)
)
allBoards(board3) == List(
  List(X, X, O, O, O, X, X, X, X),
  List(X, X, O, O, O, X, X, X, O),
  List(X, X, O, O, O, O, X, X, X),
  List(X, X, O, O, O, O, X, X, O)
)

def allBoards(board: Board): List[Board] = board match
  case head :: next =>
    val rest = allBoards(next)
    if head == E
    then rest.map(bd => X :: bd) ::: rest.map(bd => O :: bd)
    else rest.map(bd => head :: bd)
  case Nil => List(Nil)

//  PROBLEM 3:
//  Now adapt your solution to filter out the boards that are impossible if
//  X and O are alternating turns. You can continue to assume that they keep
//  filling the board after someone has won though.
//  You can assume X plays first, so all valid boards will have 5 Xs and 4 Os.
//  NOTE: make sure you keep a copy of your solution from problem 2 to answer
//  the questions on edX.
filterBoards(allBoards(board2)) == List(
  List(X, X, O, O, X, O, X, O, X)
)
filterBoards(allBoards(board3)) == List(
  List(X, X, O, O, O, X, X, X, O),
  List(X, X, O, O, O, O, X, X, X)
)
def filterBoards(boards: List[Board]): List[Board] =
  boards.filter(board => board.count(_ == X) == 5 && board.count(_ == O) == 4)

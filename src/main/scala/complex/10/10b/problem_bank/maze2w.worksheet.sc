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

// Data definitions:
// Maze is (listof Boolean)
// interp. a square maze
//         each side length is (sqrt (length <maze>))
//         true  (aka #t) means open, can move through this
//         false (aka #f) means a wall, cannot move into or through a wall
// all of your mazes will be square
//    - the maze always starts in the upper left corner
//      and ends in the lower right corner
//    - at each move, you can only move down or right
enum Terrain:
  case Open, Wall
import Terrain.*

case class Pos(x: Int, y: Int):
  require(0 <= x && 0 <= y)

type Row = Vector[Terrain]
type Maze = Vector[Row]

def stringToMaze(mazeString: String): Maze =
  mazeString
    .split("\n")
    .zipWithIndex
    .map(lineToMaze)
    .toVector

def lineToMaze(line: String, lineNumber: Int): Row =
  (0 until line.size).toVector.map(x => charToTerrain(line(x)))

val charToTerrain: Char => Terrain = char =>
  require(char == 'O' || char == 'W')
  char match
    case 'O' => Open
    case 'W' => Wall

val mazeString1 =
  """OWWW
    |WWWW
    |WWWW
    |WWWW""".stripMargin

val mazeString2 =
  """OWWWW
    |OOWOO
    |WOWWW
    |OOWWW
    |OOOOO""".stripMargin

val mazeString3 =
  """OOOOO
    |OWWWO
    |OWWWO
    |OWWWO
    |OWWWO""".stripMargin

val mazeString4 =
  """OOOOO
    |OWWWW
    |OWWWW
    |OWWWW
    |OOOOO""".stripMargin

val mazeString5 =
  """OOOOO
    |OWWWO
    |OWOOO
    |OWOWW
    |WWOOO""".stripMargin

val maze1 = stringToMaze(mazeString1)
val maze2 = stringToMaze(mazeString2)
val maze3 = stringToMaze(mazeString3)
val maze4 = stringToMaze(mazeString4)
val maze5 = stringToMaze(mazeString5)

// Functions:
// Maze Pos -> Terrain
// produce contents of given square in given maze
lookup(maze1, Pos(0, 0)) == Open
lookup(maze2, Pos(3, 2)) == Wall
lookup(maze3, Pos(0, 4)) == Open
lookup(maze4, Pos(4, 0)) == Open
lookup(maze5, Pos(1, 4)) == Wall

// def lookup(maze: Maze, pos: Pos): Terrain = Wall // stub
def lookup(maze: Maze, pos: Pos): Terrain = maze(pos.y)(pos.x)

// Maze -> Boolean
// produce true if maze is solvable, false otherwise
// assume maze has a true at least in the upper left
// remember: starts top-left, ends bot-right, only move down or right.
!solvable(maze1)
solvable(maze2)
solvable(maze3)
solvable(maze4)
!solvable(maze5)

// <template as backtracking search over generated arbitrary-arity tree>
// def solvable(maze: Maze): Boolean = false // stub
def solvable(maze: Maze): Boolean =
  def helperOne(pos: Pos): Boolean =
    solved(pos, maze) || helperList(nextPos(maze, pos))
  def helperList(posList: List[Pos]): Boolean = posList match
    case head :: next => helperOne(head) || helperList(next)
    case Nil          => false
  helperOne(Pos(0, 0))

// Pos -> Boolean
// produce true if p represents the lower right corner of a maze
// <template from type>
def solved(pos: Pos, maze: Maze): Boolean =
  pos.x == maze.length - 1 && pos.y == maze.length - 1

// Maze Position -> (listof Position)
// Given maze and position, produce a list of up to two valid next positions
nextPos(maze1, Pos(0, 0)).isEmpty
nextPos(maze2, Pos(0, 0)) == List(Pos(0, 1))
nextPos(maze2, Pos(1, 0)) == List(Pos(1, 1))
nextPos(maze3, Pos(0, 0)) == List(Pos(1, 0), Pos(0, 1))
nextPos(maze3, Pos(4, 0)) == List(Pos(4, 1))

// <template as function composition>
def valid(pos: Pos, maze: Maze): Boolean =
  pos.x <= maze.length - 1 &&
    pos.y <= maze.length - 1 &&
    lookup(maze, pos) == Open

def nextPos(maze: Maze, pos: Pos): List[Pos] =
  List(Pos(pos.x + 1, pos.y), Pos(pos.x, pos.y + 1)).filter(valid(_, maze))

val CELLSIZE = 100
val BLOCK = Image.square(CELLSIZE)
val OPEN = BLOCK.fillColor(lightGray)
val WALL = BLOCK.fillColor(black)
val terrainToImage = (terrain: Terrain) =>
  terrain match
    case Open => OPEN
    case Wall => WALL

// Maze -> Image
// produce simple rendering of a square maze.
def render(maze: Maze): Image = maze
  .map(renderRow)
  .foldRight(Image.empty)((r1, r2) => r1 above r2)

def renderRow(row: Row): Image = row
  .map(terrainToImage)
  .foldRight(Image.empty)((i1, i2) => i1 beside i2)

// render(maze5).draw()

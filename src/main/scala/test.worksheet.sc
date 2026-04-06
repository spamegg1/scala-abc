import scala.io.Source.fromResource
import scala.util.Using

case class Point(x: Int, y: Int) extends Ordered[Point]:
  def compare(that: Point): Int =
    (this.x compare that.x, this.y compare that.y) match
      case (0, i) => i
      case (i, _) => i

case class Grid(
    start: Point,
    end: Point,
    width: Int,
    height: Int,
    points: List[Point]
):
  require(points contains start)
  require(points contains end)
  require(0 <= width)
  require(0 <= height)
  require(points.sorted == points)

  def containsBlock(block: Block): Boolean =
    (points contains block.p1) && (points contains block.p2)

  // top-left is (0, 0), x grows to the right, y grows downward
  // 00 10 20 ...
  // 01 11 21 ...
  // 02 12 22 ...
  // ...
  override def toString: String =
    def line(y: Int): String =
      (for
        x <- 0 until width
        point = Point(x, y)
      yield
        if point == start then "S"
        else if point == end then "E"
        else if points contains Point(x, y) then "o"
        else ".").mkString

    (for y <- 0 until height yield line(y)).mkString("\n")

case class Block(p1: Point, p2: Point):
  require(p1 <= p2)

  def isStanding: Boolean = p1 == p2
  def isHorizontal: Boolean = p1.y == p2.y
  def isVertical: Boolean = p1.x == p2.x

  def possibleMoves: List[Block] =
    if isStanding then
      List(
        Block(Point(p1.x, p1.y + 1), Point(p1.x, p1.y + 2)),
        Block(Point(p1.x, p1.y - 1), Point(p1.x, p1.y - 2)),
        Block(Point(p1.x + 1, p1.y), Point(p1.x + 2, p1.y)),
        Block(Point(p1.x - 1, p1.y), Point(p1.x - 2, p1.y))
      )
    else if isVertical then
      List(
        Block(Point(p1.x - 1, p1.y), Point(p2.x - 1, p2.y)),
        Block(Point(p1.x + 1, p1.y), Point(p2.x + 1, p2.y)),
        Block(Point(p1.x, p1.y - 1), Point(p1.x, p1.y - 1)),
        Block(Point(p2.x, p2.y + 1), Point(p2.x, p2.y + 1))
      )
    else // isHorizontal
      List(
        Block(Point(p1.x, p1.y - 1), Point(p2.x, p2.y - 1)),
        Block(Point(p1.x, p1.y + 1), Point(p2.x, p2.y + 1)),
        Block(Point(p1.x - 1, p1.y), Point(p1.x - 1, p1.y)),
        Block(Point(p2.x + 1, p2.y), Point(p2.x + 1, p2.y))
      )

case class Move(from: Block, to: Block)
type Moves = List[Move]

case class State(grid: Grid, block: Block):
  def isGameOver: Boolean = block.isStanding && block.p1 == grid.end

  def legalMoves: Moves =
    block.possibleMoves
      .filter(grid.containsBlock)
      .map(possible => Move(block, possible))

def stringToGrid(terrain: String): Grid =
  val lines = terrain.split("\n").toList // assume nonempty list
  val width = lines.head.length
  val height = lines.length

  val start: Point =
    (for
      x <- 0 until width
      y <- 0 until height
      if lines(y)(x) == 'S'
    yield Point(x, y)).head

  val end: Point =
    (for
      x <- 0 until width
      y <- 0 until height
      if lines(y)(x) == 'E'
    yield Point(x, y)).head

  def lineToPoints(line: String, wdth: Int, hght: Int): List[Point] =
    (for
      x <- 0 until wdth
      if line(x) != '.'
    yield Point(x, hght)).toList

  val allPoints =
    (for y <- 0 until height
    yield lineToPoints(lines(y), width, y)).toList.flatten

  Grid(start, end, width, height, allPoints.sorted)

val level =
  """ooo.......
    |oSoooo....
    |ooooooooo.
    |.ooooooooo
    |.....ooEoo
    |......ooo.""".stripMargin

// val grid = stringToGrid(level)
// val level2 = grid.toString
// level == level2

// Using.resource(fromResource("story.txt"))(file => file.mkString)

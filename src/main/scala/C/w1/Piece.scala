package C.w1

import util.Random.nextInt

type Point = (Int, Int)
type Points = Array[Point]
type Rotation = Points
type Rotations = Array[Rotation]

class Piece(pointArray: Points, board: Board):
  var allRotations: Rotations = Array(pointArray)
  var rotationIndex: Int = nextInt(allRotations.length)
  var basePosition: Point = (0, 5) // row, col
  var moved: Boolean = true
  val color = Piece.allColors(nextInt(Piece.allColors.length))

  def currentRotation: Rotation = allRotations(rotationIndex)

  // Takes the intended movement in x, y and rotation
  // and checks to see if the movement is possible.
  // If it is, makes this movement and returns true. Otherwise returns false.
  def move(dx: Int, dy: Int, dr: Int): Boolean =
    // Ensures that the rotation will always be a possible formation (as opposed to nil)
    // by altering the intended rotation so that it stays
    // within the bounds of the rotation array
    moved = true
    val potential: Rotation = allRotations((rotationIndex + dr) % allRotations.size)

    // for each individual block in the piece,
    // checks if the intended move will put this block in an occupied space
    potential.foreach: posns =>
      if !board.emptyAt(posns(0) + dx + basePosition(0), posns(1) + dy + basePosition(1))
      then moved = false

    if moved then
      basePosition = (basePosition(0) + dx, basePosition(1) + dy)
      rotationIndex = (rotationIndex + dr) % allRotations.size
    moved

object Piece:
  def rotations(pointArray: Points): Rotations =
    val rot1 = pointArray.map((x, y) => (-y, x))
    val rot2 = pointArray.map((x, y) => (-x, y))
    val rot3 = pointArray.map((x, y) => (y, -x))
    Array(pointArray, rot1, rot2, rot3)

  def move(board: Board): Piece = ???
  def nextPiece(board: Board) = Piece(allPieces(nextInt(allPieces.length)), board)

  val square = Array((0, 0), (1, 0), (0, 1), (1, 1))
  val T = Array((0, 0), (1, 0), (0, -1), (-1, 0))
  val L = Array((0, 0), (0, -1), (0, 1), (1, 1))
  val invertedL = Array((0, 0), (0, -1), (0, 1), (-1, 1))
  val S = Array((0, 0), (-1, 0), (0, -1), (1, -1))
  val Z = Array((0, 0), (1, 0), (0, -1), (-1, -1))
  val pieces = Array(square, T, L, invertedL, S, Z)

  val allPieces: Rotations = pieces flatMap rotations
  val allColors: List[String] = List(
    "DarkGreen",
    "dark blue",
    "dark red",
    "gold2",
    "Purple3",
    "OrangeRed2",
    "LightSkyBlue"
  )

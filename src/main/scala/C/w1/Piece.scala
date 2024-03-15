package C.w1

import util.Random.nextInt

class Piece(pointArray: Array[(Int, Int)]):
  var allRotations = pointArray
  var rotationIndex = nextInt(allRotations.length)
  var basePosition = (0, 5) // row, col
  var moved = true
  val color = Piece.allColors(nextInt(Piece.allColors.length))

  def currentRotation = allRotations(rotationIndex)
  def move(dx: Int, dy: Int, dr: Int) = ???
  def rotations = ???

object Piece:
  def move(board: Board): Piece = ???
  def nextPiece(board: Board) = Piece(allPieces(nextInt(allPieces.length)))

  val allPieces: Array[Array[(Int, Int)]] = ???

  val allColors: List[String] = List(
    "DarkGreen",
    "dark blue",
    "dark red",
    "gold2",
    "Purple3",
    "OrangeRed2",
    "LightSkyBlue"
  )

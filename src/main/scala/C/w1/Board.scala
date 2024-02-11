package C.w1

class Board(_game: Tetris):
  var score = 0
  var game = _game
  var delay = 500
  var grid = Array.fill(Block.rows, Block.cols)(0)
  var currentBlock = ???
  var currentPos: Option[(Int, Int)] = None

  def isGameOver: Boolean = grid(0).exists(_ != 0)

  def run: Unit = ???

  def move: Unit =
    if !isGameOver && game.isRunning then
      // currentBlock.move
      ???

  def rotate: Unit = ???

  def nextPiece: Piece =
    currentPos = None
    Piece.nextPiece(this)

  def storeCurrent: Unit = ???

  def emptyAt(row: Int, col: Int): Boolean = ???

  def removeFilledRows: Unit = ???

object Block:
  val blockSize = 15
  val cols = 10
  val rows = 27

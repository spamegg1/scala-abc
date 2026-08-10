package C.w1

import scalafx.scene.paint.Color

case class Board(pieces: List[Piece]):
  var score        = 0
  var delay        = 500
  var cheated      = false
  var currentBlock = StandardPieces.next
  var currentPos   = Seq[Option[Color]]()
  val grid = Array.fill(Constants.Rows)(Array.ofDim[Option[Color]](Constants.Cols))

  /** Takes a point and checks to see if it is in the bounds of the board and currently
    * empty.
    *
    * @param pos
    *   An (x, y) coordinate on the grid.
    * @return
    *   true if position is empty, false otherwise.
    */
  def emptyAt(pos: Pos) =
    if pos.x < 0 || pos.x >= Constants.Cols then false
    else if pos.y < 1 then true
    else if pos.y >= Constants.Rows then false
    else !grid(pos.y)(pos.x).isDefined

  /** Gets the information from the current piece about where it is and uses this to store
    * the piece on the board itself. Then calls `removeFilled``.
    */
  def storeCurrent: Unit =
    val locations    = currentBlock.currentRot
    val displacement = currentBlock.position
    (0 until locations.size).foreach: index =>
      val current = locations(index)
      grid(current.y + displacement.y)(current.x + displacement.x) = currentPos(index)
    removeFilled
    delay = math.max(80, delay - 2)

  /** Removes all filled rows and replaces them with empty ones, dropping all rows above
    * them down each time a row is removed and increasing the score.
    */
  def removeFilled =
    import Constants.{Rows, Cols}
    (2 until Rows).foreach: row =>
      if grid(row).forall(_.isDefined) then // see if this row is full (has no None)
        (0 until Cols).foreach: colIndex => // remove from canvas blocks in full row
          grid(row)(colIndex) = None
        (Rows - row + 1 until Rows).foreach: row2 => // move down all rows above
          grid(Rows - row2 + 1) = grid(Rows - row2)
        grid(0) = Array.fill[Option[Color]](Cols)(None) // insert new blank row at top
        score += 10                                     // adjust score for full flow

  def nextPiece: Unit =
    if cheated then
      currentBlock = StandardPieces.Cheat
      cheated = false
    else currentBlock = StandardPieces.next
    currentPos = Seq()

  def cheat: Unit =
    if score >= 100 && !cheated then
      score -= 100
      cheated = true

  def gameOver    = grid(1).exists(_.isDefined)
  def updateScore = ()
  def draw        = ()

  def run: Unit =
    val ran = currentBlock.dropByOne(this)
    if !ran then
      storeCurrent
      if !gameOver then nextPiece
    updateScore
    draw

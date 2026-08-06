package C.w1

import scalafx.scene.paint.Color

case class Board(pieces: List[Piece]):
  var score        = 0
  var delay        = 500
  var cheated      = false
  var currentBlock = StandardPieces.next
  var currentPos   = Seq[Option[Color]]()
  val grid = Array.fill(Constants.Rows)(Array.ofDim[Option[Color]](Constants.Cols))

  def emptyAt(pos: Pos) =
    if pos.x < 0 || pos.x >= Constants.Cols then false
    else if pos.y < 1 then true
    else if pos.y >= Constants.Rows then false
    else !grid(pos.y)(pos.x).isDefined

  def storeCurrent: Unit =
    val locations    = currentBlock.currentRot
    val displacement = currentBlock.position
    (0 until locations.size).foreach: index =>
      val current = locations(index)
      grid(current.y + displacement.y)(current.x + displacement.x) = currentPos(index)
    removeFilled
    delay = math.max(80, delay - 2)

  def removeFilled = ()

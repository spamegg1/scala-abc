package C.w1

case class Board(pieces: List[Piece]):
  var score        = 0
  var delay        = 500
  var cheated      = false
  var currentBlock = StandardPieces.next
  val grid         = ???

  def emptyAt(pos: Pos) = true

package C.w1

trait Pieces:
  val All: Seq[Piece]
  def next = All(util.Random.nextInt(All.size))

object StandardPieces extends Pieces:
  val Cheat = Piece(Seq((0, 0)))

  val Sq4 = Piece(Seq((0, 0), (1, 0), (0, 1), (1, 1)))
  val T   = Piece(Seq((0, 0), (-1, 0), (1, 0), (0, -1)))
  val I4  = Piece(Seq((0, 0), (-1, 0), (1, 0), (2, 0)))
  val L41 = Piece(Seq((0, 0), (0, -1), (0, 1), (1, 1)))
  val L42 = Piece(Seq((0, 0), (0, -1), (0, 1), (-1, 1)))
  val S   = Piece(Seq((0, 0), (-1, 0), (0, -1), (1, -1)))
  val Z   = Piece(Seq((0, 0), (1, 0), (0, -1), (-1, -1)))

  val All = Seq(Sq4, T, I4, L41, L42, S, Z)
end StandardPieces

/** Add your additional pieces and your new stuff here */
object MyPieces extends Pieces:
  val Sq5 = Piece(Seq((0, 0), (1, 0), (0, -1), (-1, -1), (-1, 0)))
  val I5  = Piece(Seq((0, 0), (-1, 0), (1, 0), (-2, 0), (2, 0)))
  val L3  = Piece(Seq((0, 0), (0, 1), (1, 1)))
  val All = StandardPieces.All ++ Seq(Sq5, I5, L3)

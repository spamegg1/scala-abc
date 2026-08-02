package C.w1

type Pos       = (x: Double, y: Double)
type Points    = Seq[Pos]
type Rotation  = Seq[Pos]
type Rotations = Seq[Rotation]

case class Piece(points: Points):
  val rot1: Rotation       = points.map((x, y) => (-y, x))
  val rot2: Rotation       = points.map((x, y) => (-x, -y))
  val rot3: Rotation       = points.map((x, y) => (y, -x))
  val rotations: Rotations = Seq(points, rot1, rot2, rot3).distinct

  var basePos: Pos = (x = 5.0, y = 0.0)
  var moved        = true
  var rotIndex     = scala.util.Random.nextInt(4)

object Piece:
  val Sq   = Piece(Seq((0, 0), (1, 0), (0, 1), (1, 1)))
  val T    = Piece(Seq((0, 0), (-1, 0), (1, 0), (0, -1)))
  val Long = Piece(Seq((0, 0), (-1, 0), (1, 0), (2, 0)))
  val L    = Piece(Seq((0, 0), (0, -1), (0, 1), (1, 1)))
  val LInv = Piece(Seq((0, 0), (0, -1), (0, 1), (-1, 1)))
  val S    = Piece(Seq((0, 0), (-1, 0), (0, -1), (1, -1)))
  val Z    = Piece(Seq((0, 0), (1, 0), (0, -1), (-1, -1)))

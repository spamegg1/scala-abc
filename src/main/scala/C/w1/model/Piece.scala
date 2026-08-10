package C.w1

/** A piece (tetromino) on the screen and its various methods to rotate, move, etc.
  *
  * @param points
  *   Sequence of positions on the Tetris screen that are occupied by this piece.
  */
case class Piece(points: Points):
  val color      = Constants.Colors.sample
  val rotations  = Piece.rotations(points)
  val numOfRots  = rotations.length
  var position   = (x = 5, y = 0)
  var moved      = true
  var rotIndex   = util.Random.nextInt(numOfRots)
  def currentRot = rotations(rotIndex)

  def move(dx: Int, dy: Int, dr: Int, board: Board): Boolean =
    moved = true
    val index     = (rotIndex + dr) % numOfRots
    val potential = rotations(index)
    potential.foreach: (x, y) =>
      val pos: Pos = (x = x + dx + position.x, y = y + dy + position.y)
      if !board.emptyAt(pos) then moved = false
    if moved then
      position = (position.x + dx, position.y + dy)
      rotIndex = (rotIndex + dr) % numOfRots
    moved
  end move

  def dropByOne(board: Board) =
    moved = move(0, 1, 0, board)
    moved
end Piece

/** Methods for pieces that are uniform and do not depend on a particular instance. */
object Piece:
  def rotations(points: Points): Rotations =
    val rot1 = points.map((x, y) => (-y, x))
    val rot2 = points.map((x, y) => (-x, -y))
    val rot3 = points.map((x, y) => (y, -x))
    Seq(points, rot1, rot2, rot3).distinct

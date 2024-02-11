import math.pow

case class Coordinate(x: Double, y: Double):
  def distance(other: Coordinate) =
    val xDiffSquared = pow(x - other.x, 2)
    val yDiffSquared = pow(y - other.y, 2)
    pow(xDiffSquared + yDiffSquared, 0.5)

  override def toString(): String = s"<${x}, ${y}>"

  def -(other: Coordinate) = Coordinate(x - other.x, y - other.y)

val c = Coordinate(3,4)
val origin = Coordinate(0,0)
c distance origin
origin - c

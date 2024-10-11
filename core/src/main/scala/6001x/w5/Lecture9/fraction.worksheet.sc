class Fraction(numer: Int, denom: Int):
  override def toString = s"${numer} / ${denom}"
  def getNumer = numer
  def getDenom = denom
  def +(other: Fraction) =
    val numerNew =
      other.getDenom * getNumer + other.getNumer * getDenom
    val denomNew = other.getDenom * getDenom
    Fraction(numerNew, denomNew)

  def -(other: Fraction) =
    val numerNew =
      other.getDenom * getNumer - other.getNumer * getDenom
    val denomNew = other.getDenom * getDenom
    Fraction(numerNew, denomNew)

  def convert = getNumer / getDenom

val x = Fraction(1, 2)
val y = Fraction(3, 4)
x + y
x - y
x.convert

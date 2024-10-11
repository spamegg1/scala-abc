package curriculum

class IntSet(vals: ArrayBuffer[Int] = ArrayBuffer()):
  def insert(e: Int) = if !vals.contains(e) then vals.addOne(e)
  def member(e: Int) = vals contains e
  def remove(e: Int) =
    try vals.remove(vals.indexOf(e))
    catch case _: IndexOutOfBoundsException => println(s"$e not found")
  override def toString(): String = vals.sortInPlace().mkString("{", ", ", "}")

@main
def intSetTest =
  val s = new IntSet()
  println(s)
  s.insert(3)
  s.insert(4)
  s.insert(3)
  println(s) // 3,4
  println(s.member(3)) // true
  println(s.member(5)) // false
  s.insert(6)
  println(s) // 3,4,6
  s.remove(3)
  println(s) // 4,6
  s.remove(3) // 3 not found

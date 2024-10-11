def avg(grades: List[Double]) =
  try grades.sum.toInt / grades.length
  catch
    case _: ArithmeticException =>
      println("no grades data")
      0.0

def getStats(classList: List[(List[String], List[Double])]) =
  for (names, grades) <- classList yield (names, grades, avg(grades))

val testGrades = List(
  (List("peter", "parker"), List(10.0, 5.0, 85.0)),
  (List("bruce", "wayne"), List(10.0, 8.0, 74.0)),
  (List("captain", "america"), List(8.0, 10.0, 96.0)),
  (List("deadpool"), List[Double]())
)

getStats(testGrades)

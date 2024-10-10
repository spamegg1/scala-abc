def polySum(sides: Int, sideLength: Double) =
  // Returns the sum of the area and the square of the perimeter of
  // a regular polygon.
  // n is a positive integer, denotes the number of sides of polygon
  // s is a positive float, denotes the length of one side of polygon
  // Area of a regular polygon is given by the below formula:
  val area = 0.25 * sides * math.pow(sideLength, 2) / math.tan(math.Pi / sides)

  // Perimeter of a regular polygon is: n * s
  val perimeterSquared = math.pow(sides * sideLength, 2)

  // Return the sum of area and perimeterSquared, rounded to 4 decimal places
  round(area + perimeterSquared, 4)

@main
def pset2PolySum = println(polySum(7, 2)) // 210.5356

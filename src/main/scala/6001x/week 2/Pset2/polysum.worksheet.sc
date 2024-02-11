import math.*
import BigDecimal.RoundingMode.HALF_EVEN

def round(double: Double, places: Int): Double =
  BigDecimal(double).setScale(places, HALF_EVEN).toDouble

//val mode = new MathContext(4, RoundingMode.HALF_EVEN)

def polySum(sides: Int, sideLength: Double) =
  // Returns the sum of the area and the square of the perimeter of
  // a regular polygon.
  // n is a positive integer, denotes the number of sides of polygon
  // s is a positive float, denotes the length of one side of polygon
  // Area of a regular polygon is given by the below formula:
  val area = 0.25 * sides * pow(sideLength, 2) / tan(Pi / sides)

  // Perimeter of a regular polygon is: n * s
  val perimeterSquared = pow(sides * sideLength, 2)

  // Return the sum of area and perimeterSquared, rounded to 4 decimal places
  round(area + perimeterSquared, 4)

polySum(7, 2)

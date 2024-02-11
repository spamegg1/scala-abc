/**
  * DESIGN a function called area that consumes the length of one
  * side of a square and produces the area of the square.
  * Remember, when we say DESIGN, we mean follow the recipe.
  * Leave behind commented out versions of the stub and template.
  */

// Tests: do these first!
area(0) == 0
area(2) == 4
area(5) == 25

// Template
// def area(side: Double): Double = side.???(???)

// def area(side: Double): Double = 0.0 // stub

/**
  * produce area of square with given side length
  *
  * @param side length of one side of square
  * @return area of square
  */
def area(side: Double): Double = side * side

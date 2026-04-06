package curriculum
package mit6001x

def fancyDivide1(numbers: Array[Int], index: Int) =
  try
    val denom = numbers(index)
    for i <- 0 until numbers.length do numbers(i) /= denom
  catch
    case _: IndexOutOfBoundsException => println("fancyDivide1: -1")
    case _ => println("fancyDivide1: 1")
  finally println("fancyDivide1: 0")

def fancyDivide2(numbers: Array[Int], index: Int): Unit =
  try
    val denom = numbers(index)
    for i <- 0 until numbers.length do numbers(i) /= denom
  catch
    case _: IndexOutOfBoundsException => fancyDivide2(numbers, numbers.length - 1)
    case _: ArithmeticException => println("fancyDivide2: -2")
    case _ => println("fancyDivide2: 1")
  finally println("fancyDivide2: 0")

def fancyDivide3(numbers: Array[Int], index: Int): Unit =
  try
    try
      val denom = numbers(index)
      for i <- 0 until numbers.length do numbers(i) /= denom
    catch
      case _: IndexOutOfBoundsException => fancyDivide3(numbers, numbers.length - 1)
      case _ => println("fancyDivide3: 1")
    finally println("fancyDivide3: 0")
  catch
    case _: ArithmeticException => println("fancyDivide3: -2")

def fancyDivide4(numbers: Array[Int], index: Int): Unit =
  try
    try
      throw new Exception("0")
    finally
      val denom = numbers(index)
      for i <- 0 until numbers.length do numbers(i) /= denom
  catch case ex => println(s"fancyDivide4: $ex")

def fancyDivide5(numbers: Array[Int], index: Int): Unit =
  try
    try
      val denom = numbers(index)
      for i <- 0 until numbers.length do numbers(i) /= denom
    finally throw new Exception("0")
  catch case ex => println(s"fancyDivide5: $ex")

@main
def fancyDivideTest =
  fancyDivide1(Array(0, 2, 4), 1) // 1, 0
  fancyDivide1(Array(0, 2, 4), 4) // -1, 0
  fancyDivide1(Array(0, 2, 4), 0) // 0, ZeroDivisionError
  fancyDivide2(Array(0, 2, 4), 1) // 1, 0
  fancyDivide2(Array(0, 2, 4), 4) // 1, 0, 0
  fancyDivide2(Array(0, 2, 4), 0) // -2, 0
  fancyDivide3(Array(0, 2, 4), 1) // 1. 0
  fancyDivide3(Array(0, 2, 4), 4) // 1, 0, 0
  fancyDivide3(Array(0, 2, 4), 0) // 0, -2
  fancyDivide4(Array(0, 2, 4), 0) // division by zero
  fancyDivide5(Array(0, 2, 4), 0) // 0

// fancyDivide1: 0
// fancyDivide1: -1
// fancyDivide1: 0
// fancyDivide1: 1
// fancyDivide1: 0
// fancyDivide2: 0
// fancyDivide2: 0
// fancyDivide2: 0
// fancyDivide2: -2
// fancyDivide2: 0
// fancyDivide3: 0
// fancyDivide3: 0
// fancyDivide3: 0
// fancyDivide3: 1
// fancyDivide3: 0
// fancyDivide4: java.lang.ArithmeticException: / by zero
// fancyDivide5: java.lang.Exception: 0

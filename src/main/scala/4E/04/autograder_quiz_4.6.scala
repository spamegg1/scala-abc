// 4.6 Write a program to prompt the user for hours and
// rate per hour using input to compute gross pay.
// Pay should be the normal rate for hours up to 40 and
// time-and-a-half for the hourly rate for all hours worked above 40 hours.
// Put the logic to do the computation of pay in a function
// called computepay() and use the function to do the computation.
// The function should return a value.
// Use 45 hours and a rate of 10.50 per hour to test the program
// (the pay should be 498.75).
// You should use input to read a string and float() to
// convert the string to a number.
// Do not worry about error checking the user input unless you want to
//  - you can assume the user types numbers properly.
// Do not name your variable sum or use the sum() function.

// Desired output:
// Pay 498.75
import scala.io.StdIn.readLine

def computepay(hour: Int, rate: Double) =
  if hour <= 40 then hour * rate
  else (hour - 40) * 1.5 * rate + 40 * rate

@main def timeAndAHalf =
  val hour = readLine("Enter Hours: ").toInt
  val rate = readLine("Enter rate per hour: ").toDouble
  println(f"Pay ${computepay(hour, rate)}")

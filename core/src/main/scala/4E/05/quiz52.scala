package curriculum

// 5.2 Write a program that repeatedly prompts a user
// for integer numbers until the user enters 'done'.
// Once 'done' is entered, print out the largest and smallest of the numbers.
// If the user enters anything other than a valid number
// catch it with a try/except
// and put out an appropriate message
// and ignore the number.
// Enter 7, 2, bob, 10, and 4 and match the output below:
// Invalid input
// Maximum is 10
// Minimum is 2

@main
def minMax =
  var largest: Option[Int] = None
  var smallest: Option[Int] = None

  boundary:
    while true do
      val num = readLine("Enter a number: ")
      if num == "done" then break()

      try
        val int = num.toInt
        if smallest.isDefined then
          if int < smallest.get then smallest = Some(int)
        else smallest = Some(int)
        if largest.isDefined then
          if int > largest.get then largest = Some(int)
        else largest = Some(int)

      catch case ex: NumberFormatException => println("Invalid input")

  println(f"Maximum is ${largest.get}")
  println(f"Minimum is ${smallest.get}")

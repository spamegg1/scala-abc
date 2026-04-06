package curriculum
package mit6001x

def guessingGame(lowerBound: Int, upperBound: Int) =
  // Play a number guessing game with the computer.

  var (low, high) = (lowerBound, upperBound)
  println(s"Please think of a number between ${low} and ${high}!")

  var guess = (low + high) / 2

  println(s"Is your secret number ${guess}?")
  var command = readLine(
    "Enter h to indicate the guess is too high. " +
      "Enter l to indicate the guess is too low. " +
      "Enter c to indicate I guessed correctly.\n"
  )

  while command != "c" do
    if !List("h", "l").contains(command) then
      println("Sorry, I did not understand your command. Enter h, l, or c.")
    else if command == "h" then
      high = guess
      guess = (low + high) / 2
    else if command == "l" then
      low = guess
      guess = (low + high) / 2

    println(s"Is your secret number ${guess}?")
    command = readLine(
      "Enter h to indicate the guess is too high. " +
        "Enter l to indicate the guess is too low. " +
        "Enter c to indicate I guessed correctly.\n"
    )

  println(s"Game over. Your secret number was: ${guess}")

@main def playGuessingGame = guessingGame(0, 100)

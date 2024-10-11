package curriculum
package ps4

// Computer chooses a word
def compChooseWord(hand: Hand, wordList: List[String], n: Int): Option[String] =
  // Given a hand and a wordList, find the word that gives
  // the maximum value score, and return it.
  // This word should be calculated by considering all the words
  // in the wordList.
  // If no words in the wordList can be made from the hand, return None.
  // hand: dictionary (string -> int)
  // wordList: list (string)
  // n: integer (HANDSIZE; i.e., hand size required for additional points)
  // returns: string or None
  // Create new variable to store the maximum score seen so far (initially 0)
  wordList
    .filter(word => isValidWordps4(word, hand, wordList))
    .maxByOption(word => getWordScoreps4(word, n))

// Computer plays a hand
def compPlayHand(hand: Hand, wordList: List[String], n: Int) =
  // Allows the computer to play the given hand, following the same procedure
  // as playHand, except instead of the user choosing a word, the computer
  // chooses it.
  // 1) The hand is displayed.
  // 2) The computer chooses a word.
  // 3) After every valid word: the word and the score for that word is
  // displayed, the remaining letters in the hand are displayed, and the
  // computer chooses another word.
  // 4)  The sum of the word scores is displayed when the hand finishes.
  // 5)  The hand finishes when the computer has exhausted its possible
  // choices (i.e. compChooseWord returns None).
  // hand: dictionary (string -> int)
  // wordList: list (string)
  // n: integer (HANDSIZE; i.e., hand size required for additional points)

  // Keep track of the total score
  var totalScore = 0
  var newHand = hand

  // As long as there are still letters left in the hand:
  boundary:
    while calculateHandlen(newHand) > 0 do
      // Display the hand
      displayHand(newHand)
      // computer"s word
      val wordOpt = compChooseWord(newHand, wordList, n)
      // If the input is a single period:
      if wordOpt == None then
        // End the game (break out of the loop)
        break()

      // Otherwise (the input is not a single period):
      else
        val word = wordOpt.get
        // If the word is not valid:
        if !isValidWordps4(word, hand, wordList) then
          println("This is a terrible error! I need to check my own code!")
          break()
        // Otherwise (the word is valid):
        else
          // Tell the user how many points the word earned,
          // and the updated total score
          val score = getWordScoreps4(word, n)
          totalScore += score
          println(s"${word} earned ${score} points. Total: ${totalScore} points")
          // Update hand and show the updated hand to the user
          newHand = updateHand(newHand, word)

  // Game is over (user entered a "." or ran out of letters), so tell user the total score
  println(s"Total score: ${totalScore} points.")

// Problem 6: Playing a game
def computerPlayGame(wordList: List[String]): Unit =
  // Allow the user to play an arbitrary nber of hands.
  // 1) Asks the user to input "n" or "r" or "e".
  //     * If the user inputs "e", immediately exit the game.
  //     * If the user inputs anything that"s not "n", "r", or "e",
  //       keep asking them again.
  // 2) Asks the user to input a "u" or a "c".
  //     * If the user inputs anything that"s not "c" or "u",
  //       keep asking them again.
  // 3) Switch functionality based on the above choices:
  //     * If the user inputted "n", play a new (random) hand.
  //     * Else, if the user inputted "r", play the last hand again.
  //     * If the user inputted "u", let the user play the game
  //       with the selected hand, using playHand.
  //     * If the user inputted "c", let the computer play the
  //       game with the selected hand, using compPlayHand.
  // 4) After the computer or user has played the hand, repeat from step 1
  // wordList: list (string)
  // Keep track of last hand played
  var lastHand = Map[Char, Int]()
  var command = ""

  while true do
    // Ask user to input "n" or "r" or "e"
    command = readLine(
      "Enter n to deal a new hand, r to replay the last hand, or e to end game: "
    )

    // If user inputs anything that"s not "n", "r", or "e",
    // keep asking them again
    while !List("n", "r", "e").contains(command) do
      println("Invalid command.")
      command = readLine(
        "Enter n to deal a new hand, r to replay the last hand, or e to end game: "
      )

    // If no hand has been played, impossible to replay last hand
    while command == "r" && lastHand.isEmpty do
      println("You have not played a hand yet.")
      println("Please play a new hand first!")
      command = readLine(
        "Enter n to deal a new hand, r to replay the last hand, or e to end game: "
      )

    // If user inputs "e", immediately exit game
    if command == "e" then return

    // Ask the user to input a "u" or a "c"
    var player = readLine("Enter u to have yourself play, c to have the computer play: ")

    // If the user inputs anything that"s not "c" or "u",
    // keep asking them again
    while !List("u", "c").contains(player) do
      println("Invalid command.")
      player = readLine("Enter u to have yourself play, c to have the computer play: ")

    var newHand = Map[Char, Int]()
    if command == "n" then
      // deal new random hand
      newHand = dealHand(HANDSIZ)

      // update lastHand
      lastHand = newHand
    else if command == "r" then newHand = lastHand

    if player == "u" then
      // call playHand
      playHand(newHand, wordList, HANDSIZ)
    else if player == "c" then
      // call compPlayHand
      compPlayHand(newHand, wordList, HANDSIZ)

@main def computerScrabble =
  val wordList = loadWords4a
  computerPlayGame(wordList)

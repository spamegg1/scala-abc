package curriculum
package mit6001x

def getAvailableLettersps3(lettersGuessed: List[Char]) =
  // lettersGuessed: list, what letters have been guessed so far
  // returns: string, comprised of letters that represents what letters have
  // not yet been guessed.
  var result = ""
  for letter <- LOWER do if !lettersGuessed.contains(letter) then result += letter
  result

def hangman(secretWord: String) =
  // secretWord: string, the secret word to guess.
  // Starts up an interactive game of hangman.
  // * At the start of the game, let the user know how many
  //   letters the secretWord contains.
  // * Ask the user to supply one guess (i.e. letter) per round.
  // * The user should receive feedback immediately after each guess
  //   about whether their guess appears in the computers word.
  // * After each round, you should also display to the user the
  //   partially guessed word so far, as well as letters that the
  //   user has not yet guessed.
  // Follows the other limitations detailed in the problem write-up.
  println("Welcome to the game, hangman!")
  println(s"I am thinking of a word that is ${secretWord.length} letters long.")

  var mistakesMade = 0
  var lettersGuessed = List[Char]()

  boundary:
    while mistakesMade < 8 do
      println("-------------")
      println(s"You have ${8 - mistakesMade} guesses left.")
      val availableLetters = getAvailableLettersps3(lettersGuessed)
      println(s"Available letters: ${availableLetters}")

      val guess = readLine("Please guess a letter: ")
      var soFar = getGuessedWord(secretWord, lettersGuessed)

      if lettersGuessed.contains(guess) then
        println(s"Oops! You've already guessed that letter: ${soFar}")
      else
        lettersGuessed = guess(0) :: lettersGuessed
        if secretWord.contains(guess) then
          soFar = getGuessedWord(secretWord, lettersGuessed)
          println(s"Good guess: ${soFar}")
          if isWordGuessed(secretWord, lettersGuessed) then
            println("-------------")
            println("Congratulations, you won!")
            break()
        else
          println(s"Oops! That letter is not in my word: ${soFar}")
          mistakesMade += 1
          if mistakesMade == 8 then
            println("-------------")
            println(
              s"Sorry, you ran out of guesses. The word was ${secretWord}."
            )

// When you"ve completed your hangman function, uncomment these two lines
// and run this file to test! (hint: you might want to pick your own
// SECRETWORD while you"re testing)
// Load the list of words into the variable wordlist
// so that it can be accessed from anywhere in the program
@main def runhangman =
  val WORDLIST = loadWords(WORDLISTFILENAME)
  val SECRETWORD = chooseWord(WORDLIST)
  hangman(SECRETWORD)

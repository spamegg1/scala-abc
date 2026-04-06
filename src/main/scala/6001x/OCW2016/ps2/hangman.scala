package curriculum
package mit6001x
package ocw2016

def hangman(secretWord: Word) =
  // secretWord: string, the secret word to guess.
  // Starts up an interactive game of Hangman.
  // * At the start of the game, let the user know how many
  //   letters the secretWord contains and how many guesses s/he starts with.
  // * The user should start with 6 guesses
  // * Before each round, you should display to the user how many guesses
  //   s/he has left and the letters that the user has not yet guessed.
  // * Ask the user to supply one guess per round. Remember to make
  //   sure that the user puts in a letter!
  // * The user should receive feedback immediately after each guess
  //   about whether their guess appears in the computer's word.
  // * After each guess, you should display to the user the
  //   partially guessed word so far.
  // Follows the other limitations detailed in the problem write-up.
  var guessesLeft = 6
  var warningsLeft = 3
  var score = 0
  var wonGame = false
  var lettersGuessed = List[Char]()
  var guessesSoFar = ""

  println("Welcome to the game Hangman!")
  println(s"I am thinking of a word that is ${secretWord.length} letters long.")
  println(s"You have ${warningsLeft} warnings left.")

  boundary:
    while guessesLeft > 0 do
      println("-------------")
      println(s"You have ${guessesLeft} guesses left.")
      println(s"Available letters: ${getAvailableLetters(lettersGuessed)}")

      val guess = readLine("Please guess a letter: ")(0)

      if !(LOWER + UPPER).contains(guess) then
        if warningsLeft > 0 then
          warningsLeft -= 1
          println("Oops! That is not a valid letter.")
          println(s"You have ${warningsLeft} warnings left: ${guessesSoFar}")
        else
          guessesLeft -= 1
          println("Oops! You've already guessed that letter.")
          println("You have no warnings left,")
          println(s"so you lose one guess: ${guessesSoFar}")
      else if lettersGuessed.contains(guess) then
        if warningsLeft > 0 then
          warningsLeft -= 1
          println(s"Oops! You've already guessed that letter.")
          println(s"You have ${warningsLeft} warnings left:")
          println(guessesSoFar)
        else
          guessesLeft -= 1
          println("Oops! You've already guessed that letter.")
          println(s"You have no warnings left")
          println(s"so you lose one guess: ${guessesSoFar}")
      else if !secretWord.contains(guess) then
        lettersGuessed = guess :: lettersGuessed
        guessesSoFar = getGuessedWord(secretWord, lettersGuessed)
        if VOWELS.contains(guess) then guessesLeft -= 2
        else guessesLeft -= 1
        println(s"Oops! That letter is not in my word: ${guessesSoFar}")
      else
        lettersGuessed = guess :: lettersGuessed
        guessesSoFar = getGuessedWord(secretWord, lettersGuessed)
        println(s"Good guess: ${guessesSoFar}")

      if isWordGuessed(secretWord, lettersGuessed) then
        wonGame = true
        score = guessesLeft * secretWord.distinct.size
        println("Congratulations, you won!")
        println(s"Your total score for this game is: ${score}")
        break()

  if !wonGame then println(s"Sorry, you ran out of guesses. The word was ${secretWord}.")

def matchWithGaps(myWord: Word, otherWord: Word) =
  // myWord: string with _ characters, current guess of secret word
  // otherWord: string, regular English word
  // returns: boolean, True if all the actual letters of myWord match the
  //     corresponding letters of otherWord, or the letter is the special symbol
  //     _ , and myWord and otherWord are of the same length;
  //     False otherwise:
  val word = myWord.split(" ").mkString
  val checkLength = word.length == otherWord.length
  val checkLetters = word
    .zip(otherWord)
    .forall((c1, c2) => c1 == c2 || c1 == '_' && !word.contains(c2))
  checkLength && checkLetters

def showPossibleMatches(myWord: Word, wordList: List[Word]) =
  // myWord: string with _ characters, current guess of secret word
  // returns: nothing, prints out every word in wordList that matches myWord
  //   Keep in mind that in hangman when a letter is guessed, all the positions
  //   at which that letter occurs in the secret word are revealed.
  //   Therefore, the hidden letter(_ ) cannot be one of the letters in the word
  //   that has already been revealed.
  val matches = wordList.filter(otherWord => matchWithGaps(myWord, otherWord))
  if matches.isEmpty then println("No matches found")
  else matches foreach println

def hangmanWithHints(secretWord: Word, wordList: List[Word]) =
  // secretWord: string, the secret word to guess.
  // Starts up an interactive game of Hangman.
  // * At the start of the game, let the user know how many
  //   letters the secretWord contains and how many guesses s/he starts with.
  // * The user should start with 6 guesses
  // * Before each round, you should display to the user how many guesses
  //   s/he has left and the letters that the user has not yet guessed.
  // * Ask the user to supply one guess per round.
  //   Make sure to check that the user guesses a letter
  // * The user should receive feedback immediately after each guess
  //   about whether their guess appears in the computer's word.
  // * After each guess, you should display to the user the
  //   partially guessed word so far.
  // * If the guess is the symbol *, println out all words in wordList that
  //   matches the current guessed word.
  // Follows the other limitations detailed in the problem write-up.
  var guessesLeft = 6
  var warningsLeft = 3
  var wonGame = false
  var score = 0
  var lettersGuessed = List[Char]()
  var guessesSoFar = getGuessedWord(secretWord, lettersGuessed)

  println("Welcome to the game Hangman!")
  println(s"I am thinking of a word that is ${secretWord.length} letters long.")
  println(s"You have ${warningsLeft} warnings left.")

  boundary:
    while guessesLeft > 0 do
      println("-------------")
      println(s"You have ${guessesLeft} guesses left.")
      println(s"Available letters: ${getAvailableLetters(lettersGuessed)}")

      val guess = readLine("Please guess a letter: ")(0)

      if !(LOWER + UPPER).contains(guess) then
        if guess == '*' then
          println("Possible word matches are:")
          showPossibleMatches(guessesSoFar, wordList)
        else if warningsLeft > 0 then
          warningsLeft -= 1
          println("Oops! That is not a valid letter.")
          println(s"You have ${warningsLeft} warnings left: ${guessesSoFar}")
        else
          guessesLeft -= 1
          println("Oops! You've already guessed that letter. You have no warnings left")
          println(s"so you lose one guess: ${guessesSoFar}")
      else if lettersGuessed.contains(guess) then
        if warningsLeft > 0 then
          warningsLeft -= 1
          println(s"Oops! You've already guessed that letter.")
          println(s"You have ${warningsLeft} warnings left:")
          println(guessesSoFar)
        else
          guessesLeft -= 1
          println("Oops! You've already guessed that letter. You have no warnings left")
          println(s"so you lose one guess: ${guessesSoFar}")
      else if !secretWord.contains(guess) then
        lettersGuessed = guess :: lettersGuessed
        guessesSoFar = getGuessedWord(secretWord, lettersGuessed)
        if VOWELS.contains(guess) then guessesLeft -= 2
        else guessesLeft -= 1
        println(s"Oops! That letter is not in my word: ${guessesSoFar}")
      else
        lettersGuessed = guess :: lettersGuessed
        guessesSoFar = getGuessedWord(secretWord, lettersGuessed)
        println(s"Good guess: ${guessesSoFar}")

      if isWordGuessed(secretWord, lettersGuessed) then
        wonGame = true
        score = guessesLeft * secretWord.distinct.size
        println("Congratulations, you won!")
        println(s"Your total score for this game is: ${score}")
        break()

  if !wonGame then println(s"Sorry, you ran out of guesses. The word was ${secretWord}.")

// When you've completed your hangman_with_hint function, comment the two similar
// lines above that were used to run the hangman function, and then uncomment
// these two lines and run this file to test!
// Hint: You might want to pick your own secretWord while you're testing.
def hangmanTests =
  assert(!matchWithGaps("te_ t", "tact"))
  assert(matchWithGaps("ta_ t", "tact"))
  assert(!matchWithGaps("a_ ple", "apple"))
  assert(matchWithGaps("a_ _ le", "apple"))
  assert(!matchWithGaps("a_ _ le", "banana"))
  println("Tests pass.")

@main
def playHangman =
  hangmanTests
  val wordList = loadWords(WORDLISTFILENAME)
  val secretWord = chooseWord(wordList)
  // hangman(secretWord)
  hangmanWithHints(secretWord, wordList)

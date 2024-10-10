type Word = String

val WORDLISTFILENAME1 = "words.txt"
val STORYFILENAME1 = "story.txt"
val LOWER1 = "abcdefghijklmnopqrstuvwxyz"
val UPPER1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
val VOWELS1 = "aeiou"

// Hangman Game
// -----------------------------------
// Helper code
// You don't need to understand this helper code,
// but you will have to know how to use the functions
// (so be sure to read the docstrings!)
def loadWords1(fileName: String) =
  println("Loading word list from file...")

  Using.resource(fromResource(fileName)): inFile =>
    val wordList = inFile.mkString.split(" ").toList
    println(s"${wordList.length} words loaded.")
    wordList

def chooseWord(wordList: List[Word]) =
  // wordList (list): list of words (strings)
  // Returns a word from wordList at random
  wordList(Random.between(0, wordList.length))

// end of helper code

def isWordGuessed(secretWord: Word, lettersGuessed: List[Char]) =
  // secretWord: string, the word the user is guessing;
  //   assumes all letters are lowercase
  // lettersGuessed: list (of letters),
  //   which letters have been guessed so far;
  //   assumes that all letters are lowercase
  // returns: boolean, True if all the letters of secretWord are in lettersGuessed;
  //   False otherwise
  secretWord.forall(lettersGuessed.contains)

def getGuessedWord(secretWord: Word, lettersGuessed: List[Char]) =
  // secretWord: string, the word the user is guessing
  // lettersGuessed: list (of letters), which letters have been guessed so far
  // returns: string, comprised of letters, underscores (_), and spaces that
  //   represents which letters in secretWord have been guessed so far.
  secretWord
    .map(char => if lettersGuessed.contains(char) then char else "_ ")
    .mkString

def getAvailableLetters(lettersGuessed: List[Char]) =
  // lettersGuessed: list (of letters), which letters have been guessed so far
  // returns: string (of letters), comprised of letters that represents which
  //   letters have not yet been guessed.
  LOWER1.filter(!lettersGuessed.contains(_)).mkString

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

      if !(LOWER1 + UPPER1).contains(guess) then
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
        if VOWELS1.contains(guess) then guessesLeft -= 2
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

      if !(LOWER1 + UPPER1).contains(guess) then
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
        if VOWELS1.contains(guess) then guessesLeft -= 2
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
def tests =
  assert(!matchWithGaps("te_ t", "tact"))
  assert(matchWithGaps("ta_ t", "tact"))
  assert(!matchWithGaps("a_ ple", "apple"))
  assert(matchWithGaps("a_ _ le", "apple"))
  assert(!matchWithGaps("a_ _ le", "banana"))
  println("Tests pass.")

@main
def playHangman =
  tests
  // val wordList = loadWords1(WORDLISTFILENAME1)
  // val secretWord = chooseWord(wordList)
  // hangman(secretWord)
  // hangmanWithHints(secretWord, wordList)

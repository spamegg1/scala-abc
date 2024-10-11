package curriculum

val WORDFILE = "words.txt"
val LOWERCASE = "abcdefghijklmnopqrstuvwxyz"
val UPPERCASE = LOWERCASE.toUpperCase

// Helper code
// You don"t need to understand this helper code,
// but you will have to know how to use the functions
// (so be sure to read the docstrings!)

def loadDict =
  // Returns a list of valid words. Words are strings of lowercase letters.
  // Depending on the size of the word list, this function may
  // take a while to finish.
  println("Loading word list from file...")
  Using.resource(fromResource(WORDFILE)): infile =>
    val lines = infile.mkString.split(" ").toList
    println(s"${lines.length} words loaded.")
    lines

def chooseWordps3(wordList: List[String]) =
  // wordlist (list): list of words (strings)
  // Returns a word from wordlist at random
  wordList(Random.between(0, wordList.length))

// end of helper code

def isWordGuessedps3(secretWord: String, lettersGuessed: List[Char]) =
  // SECRETWORD: string, the word the user is guessing
  // lettersGuessed: list, what letters have been guessed so far
  // returns: boolean, True if all letters of SECRETWORD are in lettersGuessed;
  //                   False otherwise
  secretWord.forall(lettersGuessed.contains)

def getGuessedWordps3(secretWord: String, lettersGuessed: List[Char]) =
  // secretWord: string, the word the user is guessing
  // lettersGuessed: list, what letters have been guessed so far
  // returns: string, comprised of letters and underscores that represents
  //   what letters in secretWord have been guessed so far.
  var result = ""
  for letter <- secretWord do
    if lettersGuessed.contains(letter) then result += letter else result += "_ "
  result

def getAvailableLettersps3(lettersGuessed: List[Char]) =
  // lettersGuessed: list, what letters have been guessed so far
  // returns: string, comprised of letters that represents what letters have
  // not yet been guessed.
  var result = ""
  for letter <- LOWERCASE
  do if !lettersGuessed.contains(letter) then result += letter
  result

def hangmanps3(secretWord: String) =
  // secretWord: string, the secret word to guess.
  // Starts up an interactive game of hangmanps3.
  // * At the start of the game, let the user know how many
  //   letters the secretWord contains.
  // * Ask the user to supply one guess (i.e. letter) per round.
  // * The user should receive feedback immediately after each guess
  //   about whether their guess appears in the computers word.
  // * After each round, you should also display to the user the
  //   partially guessed word so far, as well as letters that the
  //   user has not yet guessed.
  // Follows the other limitations detailed in the problem write-up.
  println("Welcome to the game, hangmanps3!")
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
      var soFar = getGuessedWordps3(secretWord, lettersGuessed)

      if lettersGuessed.contains(guess) then
        println(s"Oops! You've already guessed that letter: ${soFar}")
      else
        lettersGuessed = guess(0) :: lettersGuessed
        if secretWord.contains(guess) then
          soFar = getGuessedWordps3(secretWord, lettersGuessed)
          println(s"Good guess: ${soFar}")
          if isWordGuessedps3(secretWord, lettersGuessed) then
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

// When you"ve completed your hangmanps3 function, uncomment these two lines
// and run this file to test! (hint: you might want to pick your own
// SECRETWORD while you"re testing)
// Load the list of words into the variable wordlist
// so that it can be accessed from anywhere in the program
@main def runhangmanps3 =
  val WORDLIST = loadDict
  val SECRETWORD = chooseWordps3(WORDLIST)
  hangmanps3(SECRETWORD)

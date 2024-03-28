package MIT6001x.ps3

import scala.io.Source.fromResource
import util.Random.between
import scala.io.StdIn.readLine
import util.control.Breaks.*

val WORDLISTFILENAME = "words.txt"
val LOWER = "abcdefghijklmnopqrstuvwxyz"
val UPPER = LOWER.toUpperCase

// Helper code
// You don"t need to understand this helper code,
// but you will have to know how to use the functions
// (so be sure to read the docstrings!)

def loadWords =
  // Returns a list of valid words. Words are strings of lowercase letters.
  // Depending on the size of the word list, this function may
  // take a while to finish.
  println("Loading word list from file...")
  val infile = fromResource(WORDLISTFILENAME)
  val lines = infile.mkString.split(" ").toList
  println(s"${lines.length} words loaded.")
  lines

def chooseWord(wordList: List[String]) =
  // wordlist (list): list of words (strings)
  // Returns a word from wordlist at random
  wordList(between(0, wordList.length))

// end of helper code

def isWordGuessed(secretWord: String, lettersGuessed: List[Char]) =
  // SECRETWORD: string, the word the user is guessing
  // lettersGuessed: list, what letters have been guessed so far
  // returns: boolean, True if all letters of SECRETWORD are in lettersGuessed;
  //                   False otherwise
  secretWord.forall(char => lettersGuessed.contains(char))

def getGuessedWord(secretWord: String, lettersGuessed: List[Char]) =
  // secretWord: string, the word the user is guessing
  // lettersGuessed: list, what letters have been guessed so far
  // returns: string, comprised of letters and underscores that represents
  //   what letters in secretWord have been guessed so far.
  var result = ""
  for letter <- secretWord
  do
    if lettersGuessed.contains(letter) then result += letter
    else result += "_ "
  result

def getAvailableLetters(lettersGuessed: List[Char]) =
  // lettersGuessed: list, what letters have been guessed so far
  // returns: string, comprised of letters that represents what letters have
  // not yet been guessed.
  var result = ""
  for letter <- LOWER
  do if !lettersGuessed.contains(letter) then result += letter
  result

def hangman(secretWord: String) =
  // secretWord: string, the secret word to guess.
  // Starts up an interactive game of Hangman.
  // * At the start of the game, let the user know how many
  //   letters the secretWord contains.
  // * Ask the user to supply one guess (i.e. letter) per round.
  // * The user should receive feedback immediately after each guess
  //   about whether their guess appears in the computers word.
  // * After each round, you should also display to the user the
  //   partially guessed word so far, as well as letters that the
  //   user has not yet guessed.
  // Follows the other limitations detailed in the problem write-up.
  println("Welcome to the game, Hangman!")
  println(s"I am thinking of a word that is ${secretWord.length} letters long.")

  var mistakesMade = 0
  var lettersGuessed = List[Char]()

  breakable(
    while mistakesMade < 8
    do
      println("-------------")
      println(s"You have ${8 - mistakesMade} guesses left.")
      val availableLetters = getAvailableLetters(lettersGuessed)
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
            break
        else
          println(s"Oops! That letter is not in my word: ${soFar}")
          mistakesMade += 1
          if mistakesMade == 8 then
            println("-------------")
            println(
              s"Sorry, you ran out of guesses. The word was ${secretWord}."
            )
  )

// When you"ve completed your hangman function, uncomment these two lines
// and run this file to test! (hint: you might want to pick your own
// SECRETWORD while you"re testing)
// Load the list of words into the variable wordlist
// so that it can be accessed from anywhere in the program
@main def runHangman =
  val WORDLIST = loadWords
  val SECRETWORD = chooseWord(WORDLIST)
  hangman(SECRETWORD)

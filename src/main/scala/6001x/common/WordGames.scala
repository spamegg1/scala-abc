package curriculum
package mit6001x

// DO NOT MODIFY THIS FUNCTION
def loadWords(fileName: String) =
  /** fileName (string) = the name of the file containing the list of words to load
    * Returns: a list of valid words. Words are strings of lowercase letters. Depending on
    * the size of the word list, this function may take a while to finish.
    */
  println("Loading word list from file...")
  Using.resource(fromResource(fileName)): inFile =>
    val wordList = inFile.mkString.split(" ").toList
    println(s"${wordList.length.toString} words loaded.")
    wordList

// DO NOT MODIFY THIS FUNCTION
def isWord(wordList: List[Word], word: Word) =
  /** Determines if word is a valid word, ignoring capitalization and punctuation wordList
    * (list) = list of words in the dictionary. word (string) = a possible word. Returns:
    * True if word is in wordList, False otherwise Example: >>> isWord(wordList, 'bat')
    * True >>> isWord(wordList, 'asdf') False
    */
  val newWord = word.toLowerCase.replaceAll(PUNCTUATION, "")
  wordList contains newWord

def chooseWord(wordList: List[Word]) =
  // wordList (list): list of words (strings)
  // Returns a word from wordList at random
  wordList(Random.between(0, wordList.length))

// DO NOT MODIFY THIS FUNCTION
/** Returns: a joke in encrypted text. */
def getStoryString = Using.resource(fromResource(STORYFILENAME))(_.mkString)

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
  LOWER.filter(!lettersGuessed.contains(_)).mkString

def getFreqMap(word: String) =
  // Returns a dictionary where the keys are elements of the sequence
  // and the values are integer counts, for the number of times that
  // an element is repeated in the sequence.
  // sequence: string or list
  // return: dictionary
  word.toLowerCase
    .groupBy(identity)
    .view
    .mapValues(_.length)
    .toMap

// Problem 2: Make sure you understand how this function works and what it does!
def displayHand(hand: Hand) =
  // Displays the letters currently in the hand.
  // For example:
  // >>> displayHand({"a":1, "x":2, "l":3, "e":1})
  // Should println out something like:
  //     a x x l l l e
  // The order of the letters is unimportant.
  // hand: dictionary (string -> int)
  val letters = for (letter, count) <- hand yield s"${letter} " * count
  println(s"Current hand: ${letters.mkString}")

// Problem #5: Playing a hand
def calculateHandlen(hand: Hand) =
  // Returns the length (number of letters) in the current hand.
  // hand: dictionary (string-> int)
  // returns: integer
  hand.values.sum

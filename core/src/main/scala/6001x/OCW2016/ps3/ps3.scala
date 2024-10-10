type Hand = Map[Char, Int]
type Words = List[String]

val VOWELS3 = "aeiou"
val CONSONANTS = "bcdfghjklmnpqrstvwxyz"
val HANDSIZE = 10
val VOWELRATIO = 3
val WORDLISTFILENAME3 = "wordgame.txt"
val BONUS = 50

val SCRABBLE = Map(
  'a' -> 1,
  'b' -> 3,
  'c' -> 3,
  'd' -> 2,
  'e' -> 1,
  'f' -> 4,
  'g' -> 2,
  'h' -> 4,
  'i' -> 1,
  'j' -> 8,
  'k' -> 5,
  'l' -> 1,
  'm' -> 3,
  'n' -> 1,
  'o' -> 1,
  'p' -> 3,
  'q' -> 10,
  'r' -> 1,
  's' -> 1,
  't' -> 1,
  'u' -> 1,
  'v' -> 4,
  'w' -> 4,
  'x' -> 8,
  'y' -> 4,
  'z' -> 10,
  '*' -> 0
)

// Helper code
// (you don't need to understand this helper code)
def loadWords3(fileName: String) =
  /** fileName (string) = the name of the file containing the list of words to load
    * Returns: a list of valid words. Words are strings of lowercase letters. Depending on
    * the size of the word list, this function may take a while to finish.
    */
  println("Loading word list from file...")
  Using.resource(fromResource(fileName)): inFile =>
    val wordList = inFile.getLines.map(_.toLowerCase).toList
    println(s"${wordList.length} words loaded.")
    wordList

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

// (end of helper code)

// Problem #1: Scoring a word
def getWordScore(word: String, num: Int): Int =
  // Returns the score for a word. Assumes the word is a valid word.
  // You may assume that the input word is always either a string of letters,
  // or the empty string "". You may not assume that the string will only contain
  // lowercase letters, so you will have to handle uppercase and mixed case strings
  // appropriately.
  // The score for a word is the product of two components:
  // The first component is the sum of the points for letters in the word.
  // The second component is the larger of:
  //         1, or
  //         7*wordlen - 3*(n-wordlen), where wordlen is the length of the word
  //         and n is the hand length when the word was played
  // Letters are scored as in Scrabble; A is worth 1, B is
  // worth 3, C is worth 3, D is worth 2, E is worth 1, and so on.
  // word: string
  // n: int >= 0
  // returns: int >= 0
  val len = word.length
  val wordLower = word.toLowerCase
  val first = (for letter <- wordLower yield SCRABBLE(letter)).sum
  val second = math.max(1, 7 * len - 3 * (num - len))
  first * second

// Make sure you understand how this function works and what it does!
def displayHand(hand: Hand): Unit =
  // Displays the letters currently in the hand.
  // For example:
  //     display_hand({"a":1, "x":2, "l":3, "e":1})
  // Should print out something like:
  //     a x x l l l e
  // The order of the letters is unimportant.
  // hand: dictionary (string -> int)
  val letters: Iterable[String] = for (letter, count) <- hand yield s"$letter " * count
  println(s"Current hand: ${letters.mkString}")

// Make sure you understand how this function works and what it does!
// You will need to modify this for Problem #4.
def dealHand(handSize: Int, numVowels: Int): Hand =
  // Returns a random hand containing n lowercase letters.
  // ceil(n/3) letters in the hand should be VOWELS3 (note,
  // ceil(n/3) means the smallest integer not less than n/3).
  // Hands are represented as dictionaries. The keys are
  // letters and the values are the number of times the
  // particular letter is repeated in that hand.
  // n: int >= 0
  // returns: dictionary (string -> int)
  val vowels: Iterable[Char] =
    for _ <- 1 to numVowels yield VOWELS3(Random.between(0, VOWELS3.size))

  val consonants: Iterable[Char] =
    for _ <- numVowels + 1 to handSize
    yield CONSONANTS(Random.between(0, CONSONANTS.size))

  getFreqMap(vowels.mkString + consonants.mkString)

// Problem #2: Update a hand by removing letters
def updateHand(hand: Hand, word: String): Hand =
  // Does NOT assume that hand contains every letter in word at least as
  // many times as the letter appears in word. Letters in word that don"t
  // appear in hand should be ignored. Letters that appear in word more times
  // than in hand should never result in a negative count; instead, set the
  // count in the returned hand to 0 (or remove the letter from the
  // dictionary, depending on how your code is structured).
  // Updates the hand: uses up the letters in the given word
  // and returns the new hand, without those letters in it.
  // Has no side effects: does not modify hand.
  // word: string
  // hand: dictionary (string -> int)
  // returns: dictionary (string -> int)
  val wordLower = word.toLowerCase
  val handFromWord: Hand = getFreqMap(wordLower)
  hand.map: (letter, count) =>
    handFromWord.get(letter) match
      case Some(num) => (letter, count - num)
      case None      => (letter, count)

// Problem #3: Test word validity
def isValidWord(word: String, hand: Hand, wordList: Words): Boolean =
  // Returns True if word is in the word_list and is entirely
  // composed of letters in the hand. Otherwise, returns False.
  // Does not mutate hand or word_list.
  // word: string
  // hand: dictionary (string -> int)
  // word_list: list of lowercase strings
  // returns: boolean
  val wordLower = word.toLowerCase
  val wordHand: Hand = getFreqMap(wordLower)
  val wordIsInHand: Boolean = wordLower.forall: char =>
    hand.contains(char) && wordHand(char) <= hand(char)
  wordIsInHand && wordList.contains(wordLower)

// Problem #5: Playing a hand
def calculateHandlen(hand: Hand) =
  // Returns the length (number of letters) in the current hand.
  // hand: dictionary (string-> int)
  // returns: integer
  hand.values.sum

def playHand(hand: Hand, words: Words, num: Int): Unit =
  // Allows the user to play the given hand, as follows:
  // * The hand is displayed.
  // * The user may input a word.
  // * When any word is entered (valid or invalid), it uses up letters
  //   from the hand.
  // * An invalid word is rejected, and a message is displayed asking
  //   the user to choose another word.
  // * After every valid word: the score for that word is displayed,
  //   the remaining letters in the hand are displayed, and the user
  //   is asked to input another word.
  // * The sum of the word scores is displayed when the hand finishes.
  // * The hand finishes when there are no more unused letters.
  //   The user can also finish playing the hand by inputing two
  //   exclamation points (the string "!!") instead of a word.
  //   hand: dictionary (string -> int)
  //   word_list: list of lowercase strings
  //   returns: the total score for the hand
  // BEGIN PSEUDOCODE <-- Remove this comment when you implement this function
  // Keep track of the total score
  // As long as there are still letters left in the hand:
  // Display the hand
  // Ask user for input
  // If the input is two exclamation points:
  // End the game (break out of the loop)
  // Otherwise (the input is not two exclamation points):
  // If the word is valid:
  // Tell the user how many points the word earned,
  // and the updated total score
  // Otherwise (the word is not valid):
  // Reject invalid word (print a message)
  // update the users hand by removing the letters of their inputted word
  // Game is over (user entered "!!" or ran out of letters),
  // so tell user the total score
  // Return the total score as result of function

  var score: Int = 0
  var currentHand: Hand = hand
  boundary:
    while calculateHandlen(currentHand) > 0 do
      displayHand(currentHand)
      val word: String = readLine("Enter a word, or a . to indicate you are finished: ")

      if word == "." then break()
      else if !isValidWord(word, hand, words) then
        println("Invalid word, please try again.")
      else
        val wordScore: Int = getWordScore(word, num)
        score += wordScore
        println(s"$word earned $wordScore points. Total: $score points.")
        currentHand = updateHand(currentHand, word)

  if calculateHandlen(currentHand) == 0 then
    println(s"Ran out of letters. Total score: $score points.")
  else println(s"Goodbye! Total score: $score points.")

// Problem #6: Playing a game
// procedure you will use to substitute a letter in a hand
def substituteHand(hand: Hand, letter: Char) =
  // Allow the user to replace all copies of one letter in the hand (chosen by user)
  // with a new letter chosen from the VOWELS3 and CONSONANTS at random. The new letter
  // should be different from user"s choice, and should not be any of the letters
  // already in the hand.
  // If user provide a letter not in the hand, the hand should be the same.
  // Has no side effects: does not mutate hand.
  // For example:
  //     substituteHand({"h":1, "e":1, "l":2, "o":1}, "l")
  // might return:
  //     {"h":1, "e":1, "o":1, "x":2} -> if the new letter is "x"
  // The new letter should not be "h", "e", "l", or "o" since those letters were
  // already in the hand.
  // hand: dictionary (string -> int)
  // letter: string
  // returns: dictionary (string -> int)

  if !hand.contains(letter) then hand
  else
    val availableLetters = for l <- VOWELS3 + CONSONANTS if !hand.contains(l) yield l
    hand.updated(letter, availableLetters(Random.nextInt(availableLetters.length)))

def playGame(words: Words, handSize: Int, vowelRatio: Int): Unit =
  // Allow the user to play a series of hands
  // * Asks the user to input a total number of hands
  // * Accumulates the score for each hand into a total score for the
  //   entire series
  // * For each hand, before playing, ask the user if they want to substitute
  //   one letter for another. If the user inputs "yes", prompt them for their
  //   desired letter. This can only be done once during the game. Once the
  //   substitue option is used, the user should not be asked if they want to
  //   substitute letters in the future.
  // * For each hand, ask the user if they would like to replay the hand.
  //   If the user inputs "yes", they will replay the hand and keep
  //   the better of the two scores for that hand.  This can only be done once
  //   during the game. Once the replay option is used, the user should not
  //   be asked if they want to replay future hands. Replaying the hand does
  //   not count as one of the total number of hands the user initially
  //   wanted to play.
  //         * Note: if you replay a hand, you do not get the option to
  //            substitute a letter - you must play whatever hand you just had.
  // * Returns the total score for the series of hands
  // word_list: list of lowercase strings

  // Keep track of last hand played
  var lastHand: Hand = Map()
  var command: String = ""

  val msg = "Enter n to deal a new hand, r to replay last hand, or e to end the game: "

  boundary:
    while true do
      command = readLine(msg)
      while command == "r" && lastHand.isEmpty do
        println("You have not played a hand yet. Please play a new hand first!")
        command = readLine(msg)
      if command == "n" then
        val newHand = dealHand(handSize, handSize / vowelRatio)
        lastHand = newHand
        playHand(newHand, words, handSize)
      if command == "r" then playHand(lastHand, words, handSize)
      if command == "e" then break()
      if !List("e", "n", "r").contains(command) then println("Invalid command.")

// Build data structures used for entire session and play game
// Do not remove the "if __name__ == "__main__":" line - this code is executed
// when the program is run directly, instead of through an import statement
@main def playWordGame =
  val wordList = loadWords3(WORDLISTFILENAME3)
  playGame(wordList, HANDSIZE, VOWELRATIO)

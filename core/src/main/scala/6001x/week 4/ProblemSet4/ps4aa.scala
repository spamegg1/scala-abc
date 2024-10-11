type MyHand = Map[Char, Int]

val VOWELS = "aeiou"
val CONSON = "bcdfghjklmnpqrstvwxyz"
val HANDSIZ = 7
val FILE = "wordgame.txt"
val BONUSPT = 50
val SCRABBL = Map(
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
  'z' -> 10
)

def loadWords4a =
  // Returns a list of valid words. Words are strings of lowercase letters.
  // Depending on the size of the word list, this function may
  // take a while to finish.
  println("Loading word list from file...")
  val inFile = fromResource(FILE)
  val wordList = inFile.getLines
    .map(_.toLowerCase)
    .toList
  println(s"${wordList.length} words loaded.")
  wordList

def getFreqMapps4(word: String): MyHand =
  // Returns a dictionary where the keys are elements of the sequence
  // and the values are integer counts, for the number of times that
  // an element is repeated in the sequence.
  // word: string or list of chars
  // return: dictionary
  word
    .groupMapReduce(identity)(_ => 1)(_ + _)

// Problem 1: Scoring a word
def getWordScoreps4(word: String, n: Int) =
  // Returns the score for a word. Assumes the word is a valid word.
  // The score for a word is the sum of the points for letters in the
  // word, multiplied by the length of the word, PLUS 50 points if all num
  // letters are used on the first turn.
  // Letters are scored as in SCRABBL; A is worth 1, B is worth 3, C is
  // worth 3, D is worth 2, E is worth 1, and so on (see SCRABBL)
  // word: string (lowercase letters)
  // num: integer (HANDSIZ; i.e., hand size required for additional points)
  // returns: int >= 0
  val len = word.length
  val score1 = word.map(char => SCRABBL(char)).sum
  val score2 = score1 * len
  score2 + (if len == n then BONUSPT else 0)

// Problem 2: Make sure you understand how this function works and what it does!
def displayMyHand(hand: MyHand) =
  // Displays the letters currently in the hand.
  // For example:
  // >>> displayMyHand({"a":1, "x":2, "l":3, "e":1})
  // Should println out something like:
  //     a x x l l l e
  // The order of the letters is unimportant.
  // hand: dictionary (string -> int)
  val letters =
    for (letter, count) <- hand
    yield s"${letter} " * count
  println(s"Current hand: ${letters.mkString}")

//
// Problem 2: Make sure you understand how this function works and what it does!

def dealMyHand(num: Int): MyHand =
  // Returns a random hand containing num lowercase letters.
  // At least num/3 the letters in the hand should be VOWELS.
  // MyHands are represented as dictionaries. The keys are
  // letters and the values are the number of times the
  // particular letter is repeated in that hand.
  // num: int >= 0
  // returns: dictionary (string -> int)
  val numVowels = num / 3
  val vowels = for _ <- 0 until numVowels yield VOWELS(Random.between(0, VOWELS.length))
  val cons =
    for _ <- numVowels until num
    yield CONSON(Random.between(0, CONSON.length))

  getFreqMapps4(vowels.mkString + cons.mkString)

// Problem 2: Update a hand by removing letters
def updateMyHand(hand: MyHand, word: String): MyHand =
  // Assumes that "hand" has all the letters in word.
  // In other words, this assumes that however many times
  // a letter appears in "word", "hand" has at least as
  // many of that letter in it.
  // Updates the hand: uses up the letters in the given word
  // and returns the new hand, without those letters in it.
  // Has no side effects: does not modify hand.
  // word: string
  // hand: dictionary (string -> int)
  // returns: dictionary (string -> int)
  hand.map((char, count) => (char, count - word.count(_ == char)))

// Problem 3: Test word validity
def isValidWordps4(word: String, hand: MyHand, wordList: List[String]) =
  // Returns True if word is in the wordList and is entirely
  // composed of letters in the hand. Otherwise, returns False.
  // Does not mutate hand or wordList.
  // word: string
  // hand: dictionary (string -> int)
  // wordList: list of lowercase strings
  wordList.contains(word) &&
    word.forall(letter => word.count(_ == letter) <= hand.getOrElse(letter, 0))

// Problem 4: Playing a hand
def calculateMyHandlen(hand: MyHand) =
  // Returns the length (number of letters) in the current hand.
  // hand: dictionary (string-> int)
  // returns: integer
  hand.values.sum

def playMyHand(hand: MyHand, wordList: List[String], n: Int) =
  // Allows the user to play the given hand, as follows:
  // * The hand is displayed.
  // * The user may input a word or a single period (the string ".")
  //   to indicate they"re done playing
  // * Invalid words are rejected, and a message is displayed asking
  //   the user to choose another word until they enter a valid word or "."
  // * When a valid word is entered, it uses up letters from the hand.
  // * After every valid word: the score for that word is displayed,
  //   the remaining letters in the hand are displayed, and the user
  //   is asked to input another word.
  // * The sum of the word scores is displayed when the hand finishes.
  // * The hand finishes when there are no more unused letters or the user
  //   inputs a "."
  //   hand: dictionary (string -> int)
  //   wordList: list of lowercase strings
  //   n: integer (HANDSIZ; i.e., hand size required for additional points)
  // Keep track of the total score
  var score = 0
  var newMyHand = hand

  // As long as there are still letters left in the hand:
  boundary:
    while calculateMyHandlen(newMyHand) > 0 do
      // Display the hand
      displayMyHand(newMyHand)

      // Ask user for input
      val word = readLine("Enter word, or a . to indicate that you are finished: ")

      // If the input is a single period:
      if word == "." then
        // End the game (break out of the loop)
        break()

      // Otherwise (the input is not a single period):
      else
      // If the word is not valid:
      if !isValidWordps4(word, newMyHand, wordList) then
        // Reject invalid word (println message followed by a blank line)
        println("Invalid word, please try again.")
      // Otherwise (the word is valid):
      else
        // Tell the user how many points the word earned, and the
        // updated total score, in one line followed by a blank line
        val wordScore = getWordScoreps4(word, n)
        score += wordScore
        println(s"${word} earned ${wordScore} points.")
        println(s"Total: ${score} points")
        // Update the hand
        newMyHand = updateMyHand(newMyHand, word)

  // Game is over (user entered a "." or ran out of letters), so tell user
  // the total score
  if calculateMyHandlen(newMyHand) == 0 then
    println(s"Ran out of letters. Total score: ${score} points.")
  else println(s"Goodbye! Total score: ${score} points.")

// Problem 5: Playing a game
def playGameps4(wordList: List[String]): Unit =
  // Allow the user to play an arbitrary number of hands.
  // 1) Asks the user to input "n" or "r" or "e".
  //   * If the user inputs "n", let the user play a new (random) hand.
  //   * If the user inputs "r", let the user play the last hand again.
  //   * If the user inputs "e", exit the game.
  //   * If the user inputs anything else, tell them their input was invalid.
  // 2) When done playing the hand, repeat from step 1
  // Keep track of last hand played
  var lastMyHand = Map[Char, Int]()
  var command = ""
  var newMyHand = Map[Char, Int]()

  while true do
    // Ask user for input
    command = readLine(
      "Enter n to deal a new hand, r to replay the last hand, or e to end game: "
    )

    // If no hand has been played, impossible to replay last hand
    while command == "r" && lastMyHand.isEmpty do
      println("You have not played a hand yet.")
      println("Please play a new hand first!")
      command = readLine(
        "Enter n to deal a new hand, r to replay the last hand, or e to end game: "
      )

    // If user inputs "n", play a new random hand
    if command == "n" then
      newMyHand = dealMyHand(HANDSIZ)
      // update lastMyHand
      lastMyHand = newMyHand
      // play
      playMyHand(newMyHand, wordList, HANDSIZ)

    // If user inputs "r", play last hand again
    if command == "r" then playMyHand(lastMyHand, wordList, HANDSIZ)

    // If user inputs "e", exit the game
    if command == "e" then return

    // If user inputs anything other than n, r, e, ask again
    if !List("n", "r", "e").contains(command) then println("Invalid command.")

// Build data structures used for entire session and play game
@main def playScrabbleps4 =
  val wordList = loadWords4a
  playGameps4(wordList)

// package MIT6001x.ps6

import scala.io.Source.fromResource

type Words = List[String]
type Word = String

val WORDLISTFILENAME = "words.txt"
val STORYFILENAME = "story.txt"
val LOWER = "abcdefghijklmnopqrstuvwxyz"
val UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
val VOWELS = "aeiou"
val PUNCTUATION = raw"(\p{Punct})"

// DO NOT MODIFY THIS FUNCTION //////
def loadWords(fileName: String) =
  // fileName (string) = the name of the file containing
  // the list of words to load
  // Returns: a list of valid words. Words are strings of lowercase letters.
  // Depending on the size of the word list, this function may
  // take a while to finish.

  println("Loading word list from file...")
  val inFile = fromResource(fileName)
  val line = inFile.mkString // file has a single line of text in it
  val wordList = line.split(" ").toList
  println(s"${wordList.length} words loaded.")
  inFile.close()
  wordList

// DO NOT MODIFY THIS FUNCTION //////
def isWord(wordList: Words, word: Word) =
  // Determines if word is a valid word, ignoring
  // capitalization and punctuation
  // wordList (list) = list of words in the dictionary.
  // word (string) = a possible word.
  // Returns: true if word is in wordList, false otherwise
  // Example:
  // >>> isWord(wordList, "bat") returns
  // true
  // >>> isWord(wordList, "asdf") returns
  // false
  val newWord = word.replaceAll(PUNCTUATION, "").toLowerCase
  wordList.contains(newWord)

// DO NOT MODIFY THIS FUNCTION //////
def getStoryString =
  // Returns: a joke in encrypted text.
  val file = fromResource(STORYFILENAME)
  val story = file.mkString.toLowerCase
  file.close()
  story

class Message(messageText: String):
  val validWords = loadWords(WORDLISTFILENAME)

  // DO NOT MODIFY THIS METHOD //////
  def getMessageText =
    // Used to safely access messageText outside of the class
    // Returns: messageText
    messageText

  // DO NOT MODIFY THIS METHOD //////
  def getValidWords =
    // Used to safely access a copy of validWords outside of the class
    // Returns: a COPY of validWords
    validWords

  def buildShiftDict(shift: Int) =
    // Creates a dictionary that can be used to apply a cipher to a letter.
    // The dictionary maps every uppercase and lowercase letter to a
    // character shifted down the alphabet by the input shift. The dictionary
    // should have 52 keys of all the uppercase letters and all the lowercase
    // letters only.
    // shift (integer) = the amount by which to shift every letter of the
    // alphabet. 0 <= shift < 26
    // Returns: a dictionary mapping a letter (string) to another letter (string).
    val lowerDict =
      (for i <- 0 until 26
      yield LOWER(i) -> LOWER((i + shift) % 26)).toMap

    val upperDict =
      (for i <- 0 until 26
      yield LOWER(i) -> LOWER((i + shift) % 26)).toMap

    lowerDict ++ upperDict

  def applyShift(shift: Int) =
    // Applies the Caesar Cipher to messageText with the input shift.
    // Creates a new string that is messageText shifted down the
    // alphabet by some number of characters determined by the input shift
    // shift (integer) = the shift with which to encrypt the message.
    // 0 <= shift < 26
    // Returns: the message text (string) in which every character is shifted
    //       down the alphabet by the input shift
    val shiftDict = buildShiftDict(shift)
    messageText.map(letter =>
      if (LOWER + UPPER).contains(letter)
      then shiftDict(letter)
      else letter
    )

class PlaintextMessage(text: String, var shift: Int) extends Message(text):
  // Initializes a PlaintextMessage object
  // text (string) = the message text
  // shift (integer) = the shift associated with this message
  // A PlaintextMessage object inherits from Message, has five attributes:
  //     messageText (string, determined by input text)
  //     validWords (list, determined by helper function loadWords)
  //     shift (integer, determined by input shift)
  //     encryptingDict (dictionary, built using shift)
  //     messageTextEncrypted (string, created using shift)
  // Hint: consider using the parent class constructor so less
  // code is repeated
  var encryptingDict = buildShiftDict(shift)
  var messageTextEncrypted = applyShift(shift)

  def getShift =
    // Used to safely access shift outside of the class
    // Returns: shift
    shift

  def getEncryptingDict =
    // Used to safely access a copy encryptingDict outside of the class
    // Returns: a COPY of encryptingDict
    encryptingDict

  def getmessageTextEncrypted =
    // Used to safely access messageTextEncrypted outside of the class
    // Returns: messageTextEncrypted
    messageTextEncrypted

  def changeShift(newShift: Int) =
    // Changes shift of the PlaintextMessage and updates other
    // attributes determined by shift (ie. encryptingDict and
    // messageTextEncrypted).
    // shift (integer) = new shift that should be associated with this message.
    // 0 <= shift < 26
    // Returns: nothing
    shift = newShift
    encryptingDict = buildShiftDict(newShift)
    messageTextEncrypted = applyShift(newShift)

class CiphertextMessage(text: String) extends Message(text):
  // Initializes a CiphertextMessage object
  // text (string) = the message text
  // a CiphertextMessage object has two attributes:
  //     messageText (string, determined by input text)
  //     validWords (list, determined by helper function loadWords)

  def decryptMessage =
    // Decrypt messageText by trying every possible shift value
    // and find the "best" one. We will define "best" as the shift that
    // creates the maximum number of real words when we use applyShift(shift)
    // on the message text. If s is the original shift value used to encrypt
    // the message, then we would expect 26 - s to be the best shift value
    // for decrypting it.
    // Note: if multiple shifts are  equally good such that they all create
    // the maximum number of you may choose any of those shifts (and their
    // corresponding decrypted messages) to return
    // Returns: a tuple of the best shift value used to decrypt the message
    // and the decrypted message text using that shift value
    // Keep track of best decryption so far
    val words = getValidWords
    var bestShiftValue = 0
    var decryptedMessageText = ""
    var bestCount = 0

    for shift <- 0 until 26
    do
      val shiftedText = applyShift(shift)
      val validWordCount =
        shiftedText
          .split(" ")
          .filter(isWord(words, _))
          .length

      if validWordCount > bestCount then
        bestShiftValue = shift
        bestCount = validWordCount
        decryptedMessageText = shiftedText

    (bestShiftValue, decryptedMessageText)

// Example test case (PlaintextMessage)
val plaintext = PlaintextMessage("hello", 2)
println("Expected Output: jgnnq")
println(s"Actual Output: ${plaintext.getmessageTextEncrypted}")

// Example test case (CiphertextMessage)
val ciphertext = CiphertextMessage("jgnnq")
println(s"Expected Output: ${(24, "hello")}")
println(s"Actual Output: ${ciphertext.decryptMessage}")

def decryptedStory =
  val encryptedStory = CiphertextMessage(getStoryString)
  encryptedStory.decryptMessage

println(decryptedStory)

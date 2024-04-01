import scala.util.Using
import scala.io.Source.fromResource

val WORDLISTFILENAME = "words.txt"
val STORYFILENAME = "story.txt"
val LOWER = "abcdefghijklmnopqrstuvwxyz"
val UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
val PUNCTUATION = raw"(\p{Punct})"

// DO NOT MODIFY THIS FUNCTION
def loadWords(fileName: String) =
  /** fileName (string) = the name of the file containing the list of words to load
    * Returns: a list of valid words. Words are strings of lowercase letters. Depending on
    * the size of the word list, this function may take a while to finish.
    */
  println("Loading word list from file...")
  Using.resource(fromResource(fileName)): inFile =>
    val wordList = inFile.mkString.split(" ").toList
    println(s"${wordList.length} words loaded.")
    wordList

// DO NOT MODIFY THIS FUNCTION
def isWord(wordList: List[String], word: String) =
  /** Determines if word is a valid word, ignoring capitalization and punctuation wordList
    * (list) = list of words in the dictionary. word (string) = a possible word. Returns:
    * True if word is in wordList, False otherwise Example: >>> isWord(wordList, "bat")
    * True >>> isWord(wordList, "asdf") False
    */
  val newWord = word.toLowerCase.replaceAll(PUNCTUATION, "")
  wordList contains newWord

// DO NOT MODIFY THIS FUNCTION
/** Returns: a joke in encrypted text. */
def getStoryString = Using(fromResource(STORYFILENAME))(file => file.toString)

class Message(text: String):
  // DO NOT MODIFY THIS METHOD
  /** Initializes a Message object text (string) = the message"s text a Message object has
    * two attributes: self.messageText (string, determined by input text) self.validWords
    * (list, determined using helper function loadWords
    */
  val messageText = text
  val validWords = loadWords(WORDLISTFILENAME)

  // DO NOT MODIFY THIS METHOD
  def getMessageText =
    /** Used to safely access self.messageText outside of the class Returns:
      * self.messageText
      */
    messageText

  // DO NOT MODIFY THIS METHOD
  def getValidWords =
    /** Used to safely access a copy of self.validWords outside of the class Returns: a
      * COPY of self.validWords
      */
    validWords

  def buildShiftDict(shift: Int) =
    /** Creates a dictionary that can be used to apply a cipher to a letter. The
      * dictionary maps every uppercase and lowercase letter to a character shifted down
      * the alphabet by the input shift. The dictionary should have 52 keys of all the
      * uppercase letters and all the lowercase letters only. shift (integer) = the amount
      * by which to shift every letter of the alphabet. 0 <= shift < 26 Returns: a
      * dictionary mapping a letter (char) to another letter (char).
      */
    val lowerDict = (for i <- 0 until 26 yield LOWER(i) -> LOWER((i + shift) % 26)).toMap
    val upperDict = (for i <- 0 until 26 yield LOWER(i) -> LOWER((i + shift) % 26)).toMap
    lowerDict ++ upperDict

  def applyShift(shift: Int) =
    /** Applies the Caesar Cipher to self.messageText with the input shift. Creates a new
      * string that is self.messageText shifted down the alphabet by some number of
      * characters determined by the input shift shift (integer) = the shift with which to
      * encrypt the message. 0 <= shift < 26 Returns: the message text (string) in which
      * every character is shifted down the alphabet by the input shift
      */
    val shiftDict = buildShiftDict(shift)
    messageText.map(letter => shiftDict(letter))

class PlaintextMessage(text: String, shift: Int) extends Message(text):
  /** Initializes a PlaintextMessage object text (string) = the message"s text shift
    * (integer) = the shift associated with this message A PlaintextMessage object
    * inherits from Message and has five attributes: self.messageText (string, determined
    * by input text) self.validWords (list, determined using helper function loadWords)
    * self.shift (integer, determined by input shift) self.encryptingDict (dictionary,
    * built using shift) self.messageTextEncrypted (string, created using shift) Hint:
    * consider using the parent class constructor so less code is repeated
    */
  var thisShift = shift
  var encryptingDict = buildShiftDict(shift)
  var messageTextEncrypted = applyShift(shift)

  def getShift =
    /** Used to safely access self.shift outside of the class Returns: self.shift
      */
    thisShift

  def getEncryptingDict =
    /** Used to safely access a copy self.encryptingDict outside of the class Returns: a
      * COPY of self.encryptingDict
      */
    encryptingDict

  def getMessageTextEncrypted =
    /** Used to safely access self.messageTextEncrypted outside of the class Returns:
      * self.messageTextEncrypted
      */
    messageTextEncrypted

  def changeShift(newShift: Int) =
    /** Changes self.shift of the PlaintextMessage and updates other attributes determined
      * by shift (ie. self.encryptingDict and messageTextEncrypted). shift (integer) = the
      * new shift that should be associated with this message. 0 <= shift < 26 Returns:
      * nothing
      */
    thisShift = newShift
    encryptingDict = buildShiftDict(newShift)
    messageTextEncrypted = applyShift(newShift)

class CiphertextMessage(text: String) extends Message(text):
  /** Initializes a CiphertextMessage object text (string) = the message"s text a
    * CiphertextMessage object has two attributes: self.messageText (string, determined by
    * input text) self.validWords (list, determined using helper function loadWords)
    */

  def decryptMessage =
    /** Decrypt self.messageText by trying every possible shift value and find the "best"
      * one. We will define "best" as the shift that creates the maximum number of real
      * words when we use applyShift(shift) on the message text. If s is the original
      * shift value used to encrypt the message, then we would expect 26 - s to be the
      * best shift value for decrypting it. Note: if multiple shifts are equally good such
      * that they all create the maximum number of you may choose any of those shifts (and
      * their corresponding decrypted messages) to return Returns: a tuple of the best
      * shift value used to decrypt the message and the decrypted message text using that
      * shift value
      */
    val words = getValidWords
    var bestShiftValue = 0
    var decryptedMessageText = ""
    var bestCount = 0

    for shift <- 0 until 26 do
      val shiftedText = applyShift(shift)
      val validWordCount =
        shiftedText
          .split(" ")
          .filter(word => words.contains(word))
          .length

      if validWordCount > bestCount then
        bestShiftValue = shift
        bestCount = validWordCount
        decryptedMessageText = shiftedText

    (bestShiftValue, decryptedMessageText)

// Example test case (PlaintextMessage)
val plaintext = PlaintextMessage("hello", 2)
val expected1 = "Expected Output: jgnnq"
val actual1 = s"Actual Output: ${plaintext.getMessageTextEncrypted}"

// Example test case (CiphertextMessage)
val ciphertext = CiphertextMessage("jgnnq")
val expected2 = s"Expected Output: ${(24, "hello")})"
val actual2 = s"Actual Output: ${ciphertext.decryptMessage}"

// Example test case (PlaintextMessage)
val msg = Message("test")
val plaintext2 = PlaintextMessage("hello", 2)
val expected3 = "Expected Output: jgnnq"
val actual3 = s"Actual Output: ${plaintext.getMessageTextEncrypted}"

// Example test case (CiphertextMessage)
val ciphertext2 = CiphertextMessage("jgnnq")
val expected4 = s"Expected Output: ${(24, "hello")}"
val actual4 = s"Actual Output: ${ciphertext.decryptMessage}"

// Example test case (CiphertextMessage)
val ciphertext3 = CiphertextMessage("fdw")
val expected5 = s"Expected Output: ${(23, "cat")}"
val actual5 = s"Actual Output: ${ciphertext3.decryptMessage}"

// TODO: WRITE YOUR TEST CASES HERE

// TODO: best shift value and unencrypted story

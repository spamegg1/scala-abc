package curriculum

// you may find these constants helpful
val VOWELSLOWER4 = "aeiou"
val VOWELSUPPER4 = "AEIOU"
val CONSONANTSLOWER4 = "bcdfghjklmnpqrstvwxyz"
val CONSONANTSUPPER4 = "BCDFGHJKLMNPQRSTVWXYZ"
val WORDLISTFILENAME4 = "words.txt"
val STORYFILENAME4 = "story.txt"
val LOWER4 = "abcdefghijklmnopqrstuvwxyz"
val UPPER4 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
val PUNCTUATION4 = raw"(\p{Punct})"

def getPermutations4(sequence: String): List[String] =
  if sequence.length == 1 then List(sequence)
  else
    val first = sequence.take(1)
    val perms = getPermutations4(sequence.drop(1))
    for
      perm <- perms
      index <- 0 to perm.length
      (left, right) = (perm.take(index), perm.drop(index))
    yield left + first + right

def loadWords4(fileName: String) =
  println("Loading word list from file...")
  Using.resource(fromResource(fileName)): inFile =>
    val wordList = inFile.mkString.split(" ").toList
    println(s"${wordList.length} words loaded.")
    wordList

def isWord4(wordList: List[String], word: String) =
  val newWord = word.toLowerCase.replaceAll(PUNCTUATION4, "")
  wordList contains newWord

// END HELPER CODE
class SubMessage(text: String):
  // Initializes a SubMessage object
  // text (string): the message text
  // A SubMessage object has two attributes:
  //     self.messageText (string, determined by input text)
  //     self.valid_words (list, determined using helper function load_words)
  val messageText = text
  val validWords = loadWords4(WORDLISTFILENAME4)

  def getMessageText =
    /** Used to safely access self.messageText outside of the class Returns:
      * self.messageText
      */
    messageText

  def getValidWords =
    /** Used to safely access a copy of self.validWords outside of the class Returns: a
      * COPY of self.validWords
      */
    validWords

  def buildTransposeDict(vowelsPermutation: String): Map[Char, Char] =
    // vowelsPermutation (string): a string containing a permutation of vowels
    // (a, e, i, o, u)
    // Creates a dictionary that can be used to apply a cipher to a letter.
    // The dictionary maps every uppercase and lowercase letter to an
    // uppercase and lowercase letter, respectively. Vowels are shuffled
    // according to vowelsPermutation. The first letter in vowelsPermutation
    // corresponds to a, the second to e, and so on in the order a, e, i, o, u.
    // The consonants remain the same. The dictionary should have 52
    // keys of all the uppercase letters and all the lowercase letters.
    // Example: When input "eaiuo":
    // Mapping is a->e, e->a, i->i, o->u, u->o
    // and "Hello World!" maps to "Hallu Wurld!"

    // Returns: a dictionary mapping a letter (string) to
    //             another letter (string).
    val consonantsLower = for consonant <- CONSONANTSLOWER4 yield consonant -> consonant
    val consonantsUpper = for consonant <- CONSONANTSUPPER4 yield consonant -> consonant

    val vowelsLower =
      for i <- 0 until vowelsPermutation.length
      yield VOWELSLOWER4(i) -> vowelsPermutation(i)

    val vowelsUpper =
      for i <- 0 until vowelsPermutation.length
      yield VOWELSUPPER4(i) -> vowelsPermutation(i)

    (consonantsLower ++ consonantsUpper ++ vowelsLower ++ vowelsUpper).toMap

  def applyTranspose(transposeDict: Map[Char, Char]) =
    // transposeDict (dict): a transpose dictionary
    // Returns: an encrypted version of the message text, based
    // on the dictionary
    var encryptedMessage = ""

    for letter <- messageText do
      if letter.isLetter then encryptedMessage += transposeDict(letter)
      else encryptedMessage += letter

    encryptedMessage

class EncryptedSubMessage(text: String) extends SubMessage(text):
  def decryptMessage =
    // Attempt to decrypt the encrypted message
    // Idea is to go through each permutation of the vowels and test it
    // on the encrypted message. For each permutation, check how many
    // words in the decrypted text are valid English words, and return
    // the decrypted message with the most English words.
    // If no good permutations are found (i.e. no permutations result in
    // at least 1 valid word), return the original string. If there are
    // multiple permutations that yield the maximum number of words, return any
    // one of them.
    // Returns: the best decrypted message
    // Hint: use your function from Part 4A

    val perms = getPermutations4(VOWELSLOWER4)
    var maxWords = 0
    var decryptedMessage = ""

    for perm <- perms do
      val transposeDict = buildTransposeDict(perm)
      val transposedMessage = applyTranspose(transposeDict)
      val words = transposedMessage.split(" ")
      var wordCount = 0

      for word <- words do if isWord4(validWords, word) then wordCount += 1

      if wordCount > maxWords then
        maxWords = wordCount
        decryptedMessage = transposedMessage

    decryptedMessage

@main
def ps4c =
  // Example test case
  val message = SubMessage("Hello World!")
  val permutation = "eaiuo"
  val encDict = message.buildTransposeDict(permutation)

  println(s"Original message: ${message.getMessageText} Permutation: $permutation")
  println("Expect encryption: Hallu Wurld!")
  println(s"Actual encryption: ${message.applyTranspose(encDict)}")

  val encMessage = EncryptedSubMessage(message.applyTranspose(encDict))
  println(s"Decrypted message: ${encMessage.decryptMessage}")

  // TODO: WRITE YOUR TEST CASES HERE

import scala.util.Using
import scala.io.Source.fromResource

// you may find these constants helpful
val VOWELSLOWER = "aeiou"
val VOWELSUPPER = "AEIOU"
val CONSONANTSLOWER = "bcdfghjklmnpqrstvwxyz"
val CONSONANTSUPPER = "BCDFGHJKLMNPQRSTVWXYZ"
val WORDLISTFILENAME = "words.txt"
val STORYFILENAME = "story.txt"
val LOWER = "abcdefghijklmnopqrstuvwxyz"
val UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
val PUNCTUATION = raw"(\p{Punct})"

def getPermutations(sequence: String): List[String] =
  if sequence.length == 1 then List(sequence)
  else
    val first = sequence.take(1)
    val perms = getPermutations(sequence.drop(1))
    for
      perm <- perms
      index <- 0 to perm.length
      (left, right) = (perm.take(index), perm.drop(index))
    yield left + first + right

def loadWords(fileName: String) =
  println("Loading word list from file...")
  Using.resource(fromResource(fileName)): inFile =>
    val wordList = inFile.mkString.split(" ").toList
    println(s"${wordList.length} words loaded.")
    wordList

def isWord(wordList: List[String], word: String) =
  val newWord = word.toLowerCase.replaceAll(PUNCTUATION, "")
  wordList contains newWord

// END HELPER CODE
class SubMessage(text: String):
  // Initializes a SubMessage object
  // text (string): the message text
  // A SubMessage object has two attributes:
  //     self.messageText (string, determined by input text)
  //     self.valid_words (list, determined using helper function load_words)
  val messageText = text
  val validWords = loadWords(WORDLISTFILENAME)

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
    val consonantsLower = for consonant <- CONSONANTSLOWER yield consonant -> consonant
    val consonantsUpper = for consonant <- CONSONANTSUPPER yield consonant -> consonant

    val vowelsLower =
      for i <- 0 until vowelsPermutation.length
      yield VOWELSLOWER(i) -> vowelsPermutation(i)

    val vowelsUpper =
      for i <- 0 until vowelsPermutation.length
      yield VOWELSUPPER(i) -> vowelsPermutation(i)

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

    val perms = getPermutations(VOWELSLOWER)
    var maxWords = 0
    var decryptedMessage = ""

    for perm <- perms do
      val transposeDict = buildTransposeDict(perm)
      val transposedMessage = applyTranspose(transposeDict)
      val words = transposedMessage.split(" ")
      var wordCount = 0

      for word <- words do if isWord(validWords, word) then wordCount += 1

      if wordCount > maxWords then
        maxWords = wordCount
        decryptedMessage = transposedMessage

    decryptedMessage

// Example test case
val message = SubMessage("Hello World!")
val permutation = "eaiuo"
val encDict = message.buildTransposeDict(permutation)

s"Original message: ${message.getMessageText} Permutation: $permutation"
"Expected encryption: Hallu Wurld!"
s"Actual encryption: ${message.applyTranspose(encDict)}"

val encMessage = EncryptedSubMessage(message.applyTranspose(encDict))
println("Decrypted message: " + encMessage.decryptMessage)

// TODO: WRITE YOUR TEST CASES HERE

package curriculum
package mit6001x

@main
def ps6 =
  // Example test case (PlaintextMessage)
  val plaintext = PlaintextMessage("hello", 2)
  val expect1 = "Expect Output: jgnnq"
  val actual1 = s"Actual Output: ${plaintext.getMessageTextEncrypted}"
  println(expect1)
  println(actual1)

  // Example test case (CiphertextMessage)
  val ciphertext = CiphertextMessage("jgnnq")
  val expect2 = s"Expect Output: ${(24, "hello")}"
  val actual2 = s"Actual Output: ${ciphertext.decryptMessage}"
  println(expect2)
  println(actual2)

  val decryptedStory = CiphertextMessage(getStoryString).decryptMessage
  println(decryptedStory._2) // best shift is 16

  /** Jack Florey is a mythical character created on the spur of a moment to help cover an
    * insufficiently planned hack. He has been registered for classes at MIT twice before,
    * but has reportedly never passed a class. It has been the tradition of the residents
    * of East Campus to become Jack Florey for a few nights each year to educate incoming
    * students in the ways, means, and ethics of hacking.)
    */

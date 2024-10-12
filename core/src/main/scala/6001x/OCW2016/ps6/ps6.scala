package curriculum
package mit6001x
package ocw2016

@main
def ps6 =
  // Example test case (PlaintextMessage)
  val plaintext = PlaintextMessage("hello", 2)
  val expected1 = "Expect Output: jgnnq"
  val actual1 = s"Actual Output: ${plaintext.getMessageTextEncrypted}"
  println(expected1)
  println(actual1)

  // Example test case (CiphertextMessage)
  val ciphertext = CiphertextMessage("jgnnq")
  val expected2 = s"Expect Output: ${(24, "hello").toString}"
  val actual2 = s"Actual Output: ${ciphertext.decryptMessage.toString}"
  println(expected2)
  println(actual2)

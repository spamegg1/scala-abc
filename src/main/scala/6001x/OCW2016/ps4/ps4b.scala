package curriculum
package mit6001x
package ocw2016

@main
def ps4b =
  // Example test case (PlaintextMessage)
  val plaintext = PlaintextMessage("hello", 2)
  println("Expect Output: jgnnq")
  println(s"Actual Output: ${plaintext.getMessageTextEncrypted}")

  // Example test case (CiphertextMessage)
  val ciphertext = CiphertextMessage("jgnnq")
  println(s"Expect Output: ${(24, "hello")})")
  println(s"Actual Output: ${ciphertext.decryptMessage}")

  // Example test case (PlaintextMessage)
  val msg = Message("test")
  val plaintext2 = PlaintextMessage("hello", 2)
  println("Expect Output: jgnnq")
  println(s"Actual Output: ${plaintext.getMessageTextEncrypted}")

  // Example test case (CiphertextMessage)
  val ciphertext2 = CiphertextMessage("jgnnq")
  println(s"Expect Output: ${(24, "hello")}")
  println(s"Actual Output: ${ciphertext.decryptMessage}")

  // Example test case (CiphertextMessage)
  val ciphertext3 = CiphertextMessage("fdw")
  println(s"Expect Output: ${(23, "cat")}")
  println(s"Actual Output: ${ciphertext3.decryptMessage}")

  // TODO: WRITE YOUR TEST CASES HERE

  // TODO: best shift value and unencrypted story

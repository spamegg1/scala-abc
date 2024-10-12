package curriculum
package mit6001x
package w5
package lec10

class Professor(name: String, department: String) extends MITPerson(name):
  override def speak(utterance: String) =
    val newUtterance = s"In course ${department}  we say "
    super.speak(newUtterance + utterance)

  def lecture(topic: String) = speak(s"it is obvious that ${topic}")

object Professor:
  val faculty = Professor("Doctor Arrogant", "six")
  println(faculty.speak("hi there"))
  println(faculty.lecture("hi there"))

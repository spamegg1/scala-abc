package MIT6001x.person

object MITPerson:
  var nextIdNum: Int = 0 // next ID number to assign

class MITPerson(name: String) extends Person(name) with Ordered[MITPerson]:
  val idNum = MITPerson.nextIdNum // new MITPerson attribute: a unique ID number
  MITPerson.nextIdNum += 1
  def getIdNum = idNum

  // sorting MIT people uses their ID number, not name!
  def compare(other: MITPerson) = getIdNum - other.getIdNum
  def speak(utterance: String) = s"${name} says: ${utterance}"

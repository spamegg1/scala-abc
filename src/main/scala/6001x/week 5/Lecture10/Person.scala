package MIT6001x.person

import java.time.*

class Person(name: String):
  var birthday: Option[LocalDate] = None
  val lastName =
    val fullName = name.split(' ')
    if fullName.length == 1 then "" else fullName(1)

  def getName = name
  def getLastName = lastName

  def setBirthday(month: Int, day: Int, year: Int) =
    birthday = Some(LocalDate.of(year, month, day))

  def getAge =
    // returns 's current age in years
    val birth = birthday.getOrElse(throw new NoSuchElementException)
    Period.between(birth, LocalDate.now()).getYears()

  def compare(other: Person) =
    // True if this name is lexicographically
    // less than other's name, and False otherwise
    if   getLastName == other.getLastName
    then getName < other.getName
    else lastName < other.getLastName

  // other methods
  override def toString = name

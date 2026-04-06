class Animal(var age: Int, var name: Option[String] = None):
  def getAge = age
  def getName = name
  def setAge(newAge: Int) = age = newAge
  def setName(newName: String = "") = name = Some(newName)
  override def toString: String = s"animal: ${name.getOrElse("")}: ${age}"

class Cat(age: Int, name: Option[String] = None) extends Animal(age, name):
  def speak = println("meow")
  override def toString: String = s"cat: ${name.getOrElse("")}: ${age}"

class Rabbit(age: Int, name: Option[String] = None) extends Animal(age, name):
  def speak = println("meep")
  override def toString: String = s"rabbit: ${name.getOrElse("")}: ${age}"

class Person(age: Int, name: Option[String] = None) extends Animal(age, name):
  var friends = List[String]()
  def getFriends = friends
  def speak = println("hello")
  override def toString: String = s"person: ${name.getOrElse("")}: ${age}"

  def addFriend(friendName: String) =
    if !friends.contains(friendName) then friends = friendName :: friends

  def ageDiff(other: Person) =
    val diff = this.getAge - other.getAge
    val msg = if diff > 0 then "older" else "younger"
    val thisName = name.getOrElse("this")
    val otherName = other.name.getOrElse("other")
    println(s"${thisName} is ${math.abs(diff)} years ${msg} than ${otherName}")
end Person

class Student(age: Int, name: Option[String], var major: Option[String] = None)
    extends Person(age, name):

  def changeMajor(newMajor: Option[String]) = major = newMajor
  override def toString: String =
    s"student: ${name.getOrElse("")}: ${age}: ${major.getOrElse("")}"
  override def speak =
    val r = scala.util.Random.nextFloat()
    val msg =
      if r < 0.25 then "i have homework"
      else if r < 0.5 then "i need sleep"
      else if r < 0.75 then "i should eat"
      else "i am watching tv"
    println(msg)

val blob = Animal(1)
blob.getAge
blob.getName
blob.setAge(2)
blob.getAge
blob.setName("blob")
blob.getName

val jelly = Cat(1)
jelly.setName("Jelly")
println(jelly)
jelly.speak

val peter = Rabbit(3)
peter.speak

val eric = Person(45, Some("Eric"))
eric.speak

val john = Person(55, Some("John"))
john.getFriends
eric.ageDiff(john)

val fred = Student(18, Some("Fred"))
fred.addFriend("John")
fred.getFriends

val amy = Student(19, Some("Amy"), Some("Course VI"))

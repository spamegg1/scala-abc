class Animal(var age: Int, var name: Option[String] = None):
  def getAge = age
  def getName = name
  def setAge(newAge: Int) = age = newAge
  def setName(newName: Option[String] = None) = name = newName
  override def toString = s"animal: ${name}: ${age}"

class Cat(age: Int, name: Option[String] = None) extends Animal(age, name):
  def speak = println("meow")
  override def toString = s"cat: ${name}: ${age}"

class Person(age: Int, name: Option[String] = None) extends Animal(age, name):
  var friends = Set[String]()
  def getFriends = friends
  def addFriend(fName: String) = friends = friends + fName
  def speak = println("hello")
  def ageDiff(other: Person) =
    val diff = getAge - other.getAge
    val comp = if diff > 0 then "older" else "younger"
    println(s"${name} is ${diff} years ${comp} than ${other.name}")
  override def toString = s"person: ${name}: ${age}"

class Student(
    age: Int,
    name: Option[String] = None,
    var major: Option[String] = None
) extends Person(age, name):
  def changeMajor(newMajor: Option[String]) = major = newMajor
  override def speak =
    val r = util.Random.nextDouble
    val msg =
      if r < 0.25 then "i have homework"
      else if r < 0.5 then "i need sleep"
      else if r < 0.75 then "i should eat"
      else "i am watching tv"
    println(msg)
  override def toString = s"student: ${name}: ${age} :${major}"

class Rabbit(
    age: Int,
    name: Option[String] = None,
    parent1: Option[Rabbit] = None,
    parent2: Option[Rabbit] = None
) extends Animal(age, name):

  val id = Rabbit.tag
  Rabbit.tag += 1

object Rabbit:
  var tag: Int = 0
  val basic = Rabbit(0)

class Animal(age: Int, var name: Option[String] = None)
  // def getAge = age
  // def getName = name
  // def setAge(newAge: Int) = age = newAge
  // def setName(newName: String = "") = name = Some(newName)
  // override def toString = s"animal: ${name} : ${age}"

// class Cat(age: Int, name: Option[String] = None) extends Animal(age, name):
//   def speak = println("meow")
//   override def toString = s"cat: ${name} :${age}"

object Rabbit:
  val default = Rabbit(0)
  var tag: Int = 1

class Rabbit(
  age: Int,
  parent1: Option[Rabbit] = None,
  parent2: Option[Rabbit] = None
) extends Animal(age, None):

  val id = Rabbit.tag
  Rabbit.tag += 1

  def getId = id.toString
  // def getParent1 = parent1.getOrElse(Rabbit.default)
  // def getParent2 = parent2.getOrElse(Rabbit.default)
  // def add(other: Rabbit) = Rabbit(0, Some(this), Some(other))

  // def equals(other: Rabbit): Boolean =
  //   val sameParents =
  //     getParent1.getId == other.getParent1.getId &&
  //     getParent2.getId == other.getParent2.getId
  //   val oppositeParents =
  //     getParent2.getId == other.getParent1.getId &&
  //     getParent1.getId == other.getParent2.getId
  //   sameParents || oppositeParents

// val peter = Rabbit(2)
// peter.setName("Peter")
// println(peter.getId)

// val simon = Rabbit(3, Some(peter))
// println(simon.getId)

// val hopsy = Rabbit(3)
// hopsy.setName("Hopsy")

// val cotton = Rabbit(1, Some(peter), Some(hopsy))
// cotton.setName("Cottontail")

// val mopsy = peter add hopsy
//println(mopsy == cotton)

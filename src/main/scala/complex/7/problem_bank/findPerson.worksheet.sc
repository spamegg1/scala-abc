// The following program implements an arbitrary-arity descendant family
// tree in which each person can have any number of children.
// PROBLEM A:
// Decorate the type comments with reference arrows and establish a clear
// correspondence between template function calls in the templates and
// arrows in the type comments.

// Data definitions:
case class Person(name: String, age: Int, kids: List[Person])
// Person is (make-person String Natural ListOfPerson)
// interp. A person, with first name, age and their children
val person1 = Person("name1", 5, Nil)
val person2 = Person("name2", 25, List(person1))
val person3 = Person("name3", 15, Nil)
val person4 = Person("name4", 45, List(person3, person2))

def fnForPerson(person: Person) = ???
// person.name, person.age, fnForPersonList(person.kids)

def fnForPersonList(persons: List[Person]) = persons match
  case head :: next => ??? // fnForPerson(head), fnForPersonList(next)
  case Nil          => ???

// Functions:
// PROBLEM B:
// Design a function that consumes a Person and a String. The function
// should search the entire tree looking for a person with the given
// name. If found the function should produce the person's age. If not
// found the function should produce false.

// String Person -> Natural or false
// String ListOfPerson -> Natural or false
// search the given tree for a person with the given name,
// produce age if foundfalse otherwise
findPerson("name1", person1) == Some(5)
findPerson("name2", person2) == Some(25)
findPerson("name3", person3) == Some(15)
findPerson("name4", person4) == Some(45)
findPerson("name2", person1) == None
findPersonList("name1", Nil) == None
findPersonList("name3", List(person1, person2, person3)) == Some(15)
findPersonList("name4", List(person1, person2, person3)) == None

// <templates taken from Person and ListOfPerson>
def findPerson(searchName: String, person: Person): Option[Int] =
  if person.name == searchName
  then Some(person.age)
  else findPersonList(searchName, person.kids)

def findPersonList(searchName: String, persons: List[Person]): Option[Int] =
  persons match
    case head :: next =>
      findPerson(searchName, head) match
        case Some(age) => Some(age)
        case None      => findPersonList(searchName, next)
    case Nil => None

// alternate approach by folding
def findPerson2(searchName: String, person: Person): Option[Int] =
  if person.name == searchName
  then Some(person.age)
  else
    person.kids.foldLeft[Option[Int]](None)((opt, kid) =>
      if opt.isDefined then opt
      else findPerson2(searchName, kid)
    )

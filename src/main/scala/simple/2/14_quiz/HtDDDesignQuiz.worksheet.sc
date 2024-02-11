// Age is Natural
// interp. the age of a person in years
case class Age(n: Int):
  require(0 <= n)

val age0 = Age(18)
val age1 = Age(25)

// Template
def fnForAge(age: Age) = ???
// age.n ???

// Template rules used:
// - atomic non-distinct: Natural

// Problem 1:
// Consider the above data definition for the age of a person.
// Design a function called teenager? that determines whether a person
// of a particular age is a teenager (i.e., between the ages of 13 and 19).

// Age -> Boolean
// produces true if given age is between 13 and 19 (inclusive), false otherwise
!isTeenager(Age(12))
isTeenager(Age(13))
isTeenager(Age(16))
isTeenager(Age(19))
!isTeenager(Age(20))

// def isTeenager(age: Age): Boolean = false // stub

//  <used template from Age>
def isTeenager(age: Age) =
  13 <= age.n && age.n <= 19

// Problem 2:
// Design a data definition called MonthAge to represent a person's age
// in months.

// MonthAge is Natural
// interp. the age of a person in months
case class MonthAge(n: Int):
  require(0 <= n)

val monthAge1 = MonthAge(120)
val monthAge2 = MonthAge(300)

// Template
def fnForMonthAge(monthAge: MonthAge) = ???
// monthAge.n ???

// Template rules used:
// - atomic non-distinct: Natural

// Problem 3:
// Design a function called months-old that takes a person's age in years
// and yields that person's age in months.

// Age -> MonthAge
// takes a person's age in years and yields that person's age in months (accurate within 12 months)
monthsOld(Age(0)) == MonthAge(0)
monthsOld(Age(12)) == MonthAge(144)
monthsOld(Age(25)) == MonthAge(300)
monthsOld(Age(40)) == MonthAge(480)
monthsOld(Age(80)) == MonthAge(960)

// def monthsOld(age: Age): MonthAge = MonthAge(0) // stub

//  <used template from Age>
def monthsOld(age: Age): MonthAge =
  MonthAge(age.n * 12)

// Problem 4:
// Consider a video game where you need to represent the health of your
// character. The only thing that matters about their health is:
//   - if they are dead (which is shockingly poor health)
//   - if they are alive then they can have 0 or more extra lives
// Design a data definition called Health to represent the health of your
// character.
// Design a function called increase-health that allows you to increase the
// lives of a character.  The function should only increase the lives
// of the character if the character is not dead, otherwise the character
// remains dead.

// Health is one of:
//  - false
//  - Natural
// interp. false means dead, Natural means number of extra lives
enum Health:
  case Dead
  case Alive(extraLives: Int)
import Health.*
// sealed trait Health
// case object Dead extends Health
// case class Alive(extraLives: Int) extends Health:
//   require(0 <= extraLives)

val health1 = Dead
val health2 = Alive(0)
val health3 = Alive(5)

// Template
def fnForHealth(h: Health) = h match
  case Dead     => ???
  case Alive(n) => ???

// Template rules used:
//  - one of: 2 cases
//  - atomic distinct: false
//  - atomic non-distinct: Number

// Functions
// Health -> Health
// increases given health by 1 only if health is not false (otherwise leaves health unchanged)
increaseHealth(Dead) == Dead
increaseHealth(Alive(0)) == Alive(1)
increaseHealth(Alive(5)) == Alive(6)

// def increaseHealth(health: Health): Health = Dead // stub

//  <used template from Health>
def increaseHealth(h: Health) = h match
  case Dead     => Dead
  case Alive(n) => Alive(n + 1)

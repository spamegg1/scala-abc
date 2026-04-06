// PROBLEM A:
// You are designing a program to track a rocket's journey as it descends
// 100 kilometers to Earth. You are only interested in the descent from
// 100 kilometers to touchdown. Once the rocket has landed it is done.
// Design a data definition to represent the rocket's remaining descent.
// Call it RocketDescent.

// RocketDescent is one of:
// - Number(0, 100]
// - false
// Interp. false if rocket's descent has ended,
// otherwise number of kilometers left to Earth
enum RocketDescent:
  case Ended
  case KmsLeft(kms: Double)
import RocketDescent.*
// sealed trait RocketDescent
// case object Ended extends RocketDescent
// case class KmsLeft(kms: Double) extends RocketDescent:
//   require(0.0 < kms && kms <= 100.0)

//  Note - The type comment:
//  RocketDescent is one of:
//  - Number[0,100]
//  - false
//  is also a reasonable solution to the problem.

val rd1 = KmsLeft(100.0)
val rd2 = KmsLeft(40.0)
val rd3 = KmsLeft(0.5)
val rd4 = Ended

// Template
def fnForRocketDescent(rd: RocketDescent) = rd match
  case Ended        => ???
  case KmsLeft(kms) => ??? // kms

// Template Rules Used:
//  - one of: 2 cases
//  - atomic non-distinct: Number[100, 0)
//  - atomic distinct: false

// Functions:
// PROBLEM B:
// Design a function that will output the rocket's remaining descent distance
// in a short string that can be broadcast on Twitter.
// When the descent is over, the message should be "The rocket has landed!".
// Call your function rocket-descent-to-msg.

// RocketDescent -> String
// outputs a Twitter update on rocket's descent distance
rocketDescentToMsg(KmsLeft(100)) == "Altitude is 100.0 kms."
rocketDescentToMsg(KmsLeft(40)) == "Altitude is 40.0 kms."
rocketDescentToMsg(KmsLeft(0.5)) == "Altitude is 0.5 kms."
rocketDescentToMsg(Ended) == "The rocket has landed!"

// def rocketDescentToMsg(rd: RocketDescent): String = "" // stub

// <template from RocketDescent>
def rocketDescentToMsg(rd: RocketDescent) = rd match
  case Ended        => "The rocket has landed!"
  case KmsLeft(kms) => s"Altitude is ${kms} kms."

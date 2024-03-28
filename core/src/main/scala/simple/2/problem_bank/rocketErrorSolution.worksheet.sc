import doodle.image.Image
import doodle.image.syntax.all.ImageOps
import doodle.java2d.java2dRenderer
import cats.effect.unsafe.implicits.global

// Consider the following data definition from the Rocket practice problem.
// We have designed a function has-landed?, but there are errors in the function
// design. Uncomment the program below, and make the minimal changes possible to
// a) make this program work properly and b) make the function design
// consistent.
// Data Definitions:
// RocketDescent is one of:
// - Number
// - false
// interp. false if rocket's descent has ended, otherwise number of kilometers
//         left to Earth, restricted to (0, 100]
enum RocketDescent:
  case Ended
  case KmsLeft(kms: Double)
import RocketDescent.*
// sealed trait RocketDescent
// case object Ended extends RocketDescent
// case class KmsLeft(kms: Double) extends RocketDescent:
//   require(0 < kms && kms <= 100)

val rd1 = KmsLeft(100)
val rd2 = KmsLeft(40)
val rd3 = KmsLeft(0.5)
val rd4 = Ended

// (@dd-template-rules one-of              ;2 cases
//                     atomic-non-distinct ;Number
//                     atomic-distinct)    ;false
def fnForRocketDescent(rd: RocketDescent) = rd match
  case Ended        => ???
  case KmsLeft(kms) => ???

// Functions:
// (@signature RocketDescent -> Boolean)
// produce true if rocket's descent has ended; false if it's still descending
!hasLanded(KmsLeft(100))
!hasLanded(KmsLeft(23))
!hasLanded(KmsLeft(0.25))
hasLanded(Ended)

// def hasLanded(rd: RocketDescent): Boolean = false // stub

def hasLanded(rd: RocketDescent) = rd match
  case Ended        => true
  case KmsLeft(kms) => false

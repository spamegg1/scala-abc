import doodle.image.Image
import doodle.image.syntax.all.ImageOps
import doodle.java2d.java2dRenderer
import cats.effect.unsafe.implicits.global

// PROBLEM A:
// You are working on a system that will automate delivery for
// YesItCanFly! airlines catering service.
// There are three dinner options for each passenger, chicken, pasta
// or no dinner at all.
// Design a data definition to represent a dinner order. Call the type
// DinnerOrder.
// DinnerOrder is one of:
//  - No dinner
//  - Chicken
//  - Pasta
// interp. "No dinner" means the passenger does not want dinner,
//         the other values are dinner options
enum DinnerOrder:
  case NoDinner, Chicken, Pasta

import DinnerOrder.*
// <examples are redundant for enumerations>

def fnForDinnerOrder(order: DinnerOrder) = order match
  case NoDinner => ???
  case Chicken  => ???
  case Pasta    => ???

// Template rules used:
// - one-of: 3 cases
// - atomic distinct: "No dinner"
// - atomic distinct: "Chicken"
// - atomic distinct: "Pasta"

// PROBLEM B:
// Design the function dinner-order-to-msg that consumes a dinner order
// and produces a message for the flight attendants saying what the
// passenger ordered.
// For example, calling dinner-order-to-msg for a chicken dinner would
// produce "The passenger ordered chicken."

// DinnerOrder -> String
// produce message to describe what passenger ordered
dinnerOrderToMsg(NoDinner) == "The passenger did not order dinner."
dinnerOrderToMsg(Chicken)  == "The passenger ordered chicken."
dinnerOrderToMsg(Pasta)    == "The passenger ordered pasta."

// def dinnerOrderToMsg(order: DinnerOrder): String = "" // stub

// <template from DinnerOrder>
def dinnerOrderToMsg(order: DinnerOrder) = order match
  case NoDinner => "The passenger did not order dinner."
  case Chicken  => "The passenger ordered chicken."
  case Pasta    => "The passenger ordered pasta."

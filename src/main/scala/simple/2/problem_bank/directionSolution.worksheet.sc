import doodle.image.Image
import doodle.image.syntax.all.ImageOps
import doodle.java2d.java2dRenderer
import cats.effect.unsafe.implicits.global

// PROBLEM:
// Given the data definition below, design a function named left
// that consumes a compass direction and produces the direction
// that results from making a 90 degree left turn.
// Data definitions:
// Direction is one of:
//  - N
//  - S
//  - E
//  - W
// interp. a compass direction that a player can be facing
enum Direction:
  case North, South, East, West
import Direction.*
// <examples are redundant for enumerations>

def fnForDirection(direction: Direction) = direction match
  case North => ???
  case South => ???
  case East  => ???
  case West  => ???

// Template rules used:
// - one of: 4 cases
// - atomic distinct: "N"
// - atomic distinct: "S"
// - atomic distinct: "E"
// - atomic distinct: "W"


// Functions:
// Direction -> Direction
// direction resulting from facing d and turning 90 degrees left
left(North) == West
left(South) == East
left(East) == North
left(West) == South

// def left(direction: Direction): Direction = North // stub

// <template from Direction>

def left(direction: Direction): Direction = direction match
  case North => West
  case South => East
  case East  => North
  case West  => South

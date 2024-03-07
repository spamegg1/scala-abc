import doodle.core.*
import doodle.core.Color.*
import doodle.image.Image
import doodle.image.Image.Elements.*
import doodle.java2d.*
import doodle.reactor.Reactor
import cats.effect.unsafe.implicits.global
import concurrent.duration.FiniteDuration
import doodle.syntax.all.*
import doodle.image.syntax.all.*
import scala.util.Random

// REFACTOR TO ADD A STATIONARY STATE FOR THE TANK!

// Space Invaders
// Constants:
val WIDTH = 600
val HEIGHT = 1000
val BGCOLOR = skyBlue
val TICKRATE = FiniteDuration(50, "milliseconds")
val FRAME = Frame.default
  .withSize(WIDTH, HEIGHT)
  .withBackground(BGCOLOR)
  .withTitle("Space Invaders")
  .withCenterAtOrigin

val XSPEED = 5
val YSPEED = 5
val TANKSPEED = 10
val MISSILESPEED = 20
val HITRANGE = 10
val INVADERATE = 4 // percent
val SPEEDMIN = 2
val SPEEDMAX = 5

val FILLER = Image.rectangle(HITRANGE * 2, HITRANGE).strokeWidth(0)
val COCKPIT = Image.circle(HITRANGE).scale(1, 2).strokeColor(blue)
val SAUCER = Image.circle(HITRANGE).scale(3, 1).fillColor(gray)
val INVADER = COCKPIT on (FILLER above SAUCER)

val CENTER = Image.circle(4).scale(7, 2).fillColor(green)
val OUTLINE = Image.circle(HITRANGE).scale(3, 1).fillColor(black)
val GUN = Image.rectangle(3, HITRANGE).fillColor(green)
val BODY = Image.rectangle(HITRANGE * 2, HITRANGE).fillColor(green)
val FILLER2 = Image.rectangle(0, HITRANGE * 2).strokeWidth(0.0)
val TANK = (FILLER2 above (CENTER on OUTLINE)) on (GUN above BODY)

val MISSILE = Image.circle(HITRANGE).scale(1, 2).fillColor(red)

// Data Definitions:
enum Direction:
  case Left, Right
import Direction.*

// interp. the tank location is x, HEIGHT - TANK-HEIGHT/2 in screen coordinates
//         the tank moves TANK-SPEED pixels per clock tick
// left if dir -1, right if dir 1
case class Tank(x: Double, direction: Direction)

// interp. the invader is at (x, y) in screen coordinates
//         the invader along x by dx pixels per clock tick
// (define I1 (make-invader 150 100 1))           ;not landed, moving right
// (define I2 (make-invader 150 HEIGHT -1))       ;exactly landed, moving left
// (define I3 (make-invader 150 (+ HEIGHT 10) 1)) ;> landed, moving right
case class Invader(x: Double, y: Double, dx: Double, dy: Double)

// interp. the missile's location is x y in screen coordinates
case class Missile(x: Double, y: Double, dy: Double)

// interp. the current state of a space invaders game
//         with the current invaders, missiles and tank position
// GameState constants defined below Missile data definition
case class GameState(
    invaders: List[Invader],
    missiles: List[Missile],
    tank: Tank
)

def fnForGameState(gs: GameState) = ???
// fnForInvaders(gs.invaders), fnForMissiles(gs.missiles), fnForTank(gs.Tank)

// Functions:
// MAIN
// GameState -> GameState
// runs the Space Invaders game
// start the world with (main GS0)
// <no tests for main functions>
def main(gs: GameState): Unit =
  Reactor
    .init[GameState](gs)
    .withOnTick(nextGameState) // GameState -> GameState
    .withRender(renderGameState) // GameState -> Image
    .withOnMouseClick(fireMissile) // Point GameState -> GameState
    .withOnMouseMove(moveTank) // Point GameState => GameState
    .withStop(gameOver) // GameState => Boolean
    .withTickRate(TICKRATE)
    .run(FRAME)

// render invaders on top of missiles, on top of tank
def renderGameState(gs: GameState): Image =
  renderInvaders(gs.invaders) on
    renderMissiles(gs.missiles) on renderTank(gs.tank)

def renderInvaders(invaders: List[Invader]): Image = invaders match
  case head :: next =>
    INVADER.at(Point(head.x, head.y)) on renderInvaders(next)
  case Nil => Image.empty

def renderMissiles(missiles: List[Missile]): Image = missiles match
  case head :: next =>
    MISSILE.at(Point(head.x, head.y)) on renderMissiles(next)
  case Nil => Image.empty

def renderTank(tank: Tank): Image = TANK.at(tank.x, -HEIGHT / 2 + HITRANGE * 2)

// when mouse is clicked, create new missile at tank location
def fireMissile(point: Point, gs: GameState): GameState =
  val newMissile = Missile(gs.tank.x, -HEIGHT / 2 + HITRANGE * 3, MISSILESPEED)
  GameState(gs.invaders, newMissile :: gs.missiles, gs.tank)

// depending on location of mouse cursor, change tank movement direction
def moveTank(point: Point, gs: GameState): GameState =
  val newDirection = (gs.tank.direction, gs.tank.x < point.x) match
    case (Left, true)   => Right
    case (Left, false)  => Left
    case (Right, true)  => Right
    case (Right, false) => Left
  val newTank = Tank(gs.tank.x, newDirection)
  GameState(gs.invaders, gs.missiles, newTank)

// GameState => Boolean
// returns true if an invader reaches bottom of screen
def gameOver(gs: GameState): Boolean =
  gs.invaders.exists(invader => invader.y <= -HEIGHT / 2)

// GameState -> GameState
// update tank, invaders, missiles
def nextGameState(gs: GameState): GameState =
  val newInvaders =
    if Random.between(0, 100) < INVADERATE
    then randomInvader :: gs.invaders
    else gs.invaders

  GameState(
    nextInvaders(newInvaders, gs.missiles),
    nextMissiles(gs.invaders, gs.missiles),
    nextTank(gs.tank)
  )

// => Invader
// generate a random invader
def randomInvader = Invader(
  Random.between(-WIDTH / 2, WIDTH / 2), // random horizontal location
  HEIGHT / 2, // top of screen
  Random.between(SPEEDMIN, SPEEDMAX), // random horizontal speed (+/-)
  -Random.between(SPEEDMIN, SPEEDMAX) // random vertical speed (negative)
)

// ListOfInvader ListOfMissile -> ListOfInvader
// remove invaders that are hit by any missile
def nextInvaders(
    invaders: List[Invader],
    missiles: List[Missile]
): List[Invader] = invaders
  .filter(invader => !invaderCollides(invader, missiles))
  .map(advanceInvader)

def advanceInvader(invader: Invader): Invader =
  if invader.x + invader.dx >= WIDTH / 2
  then Invader(WIDTH / 2, invader.y + invader.dy, -invader.dx, invader.dy)
  else if invader.x + invader.dx <= -WIDTH / 2
  then Invader(-WIDTH / 2, invader.y + invader.dy, -invader.dx, invader.dy)
  else
    Invader(
      invader.x + invader.dx,
      invader.y + invader.dy,
      invader.dx,
      invader.dy
    )

// Invader ListOfMissile => Boolean
// check one invader against all missiles for collision
// return true if invader collides against at least one missile.
def invaderCollides(invader: Invader, missiles: List[Missile]): Boolean =
  missiles exists (missile => collision(invader, missile))

def nextMissiles(
    invaders: List[Invader],
    missiles: List[Missile]
): List[Missile] = missiles
  .filter(missile => !missileCollides(invaders, missile))
  .map(advanceMissile)

def advanceMissile(missile: Missile): Missile =
  Missile(missile.x, missile.y + missile.dy, missile.dy)

def missileCollides(invaders: List[Invader], missile: Missile): Boolean =
  missile.y >= HEIGHT / 2 ||
    invaders.exists(invader => collision(invader, missile))

// Invader Missile => Boolean
// return true if invader and missile are within hit range of each other.
def collision(invader: Invader, missile: Missile): Boolean =
  math.abs(invader.x - missile.x) <= HITRANGE * 2 &&
    math.abs(invader.y - missile.y) <= HITRANGE * 2

def nextTank(tank: Tank): Tank = tank.direction match
  case Left  => Tank(math.max(-WIDTH / 2, tank.x - TANKSPEED), Left)
  case Right => Tank(math.min(WIDTH / 2, tank.x + TANKSPEED), Right)

// un-comment to start game!
// main(GameState(Nil, Nil, Tank(0.0, Left)))
2 + 3

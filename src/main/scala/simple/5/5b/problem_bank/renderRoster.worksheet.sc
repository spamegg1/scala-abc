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

// Problem:
// You are running a dodgeball tournament and are given a list of all
// of the players in a particular game as well as their team numbers.
// You need to build a game roster like the one shown below. We've given
// you some constants and data definitions for Player, ListOfPlayer
// and ListOfString to work with.
// While you're working on these problems, make sure to keep your
// helper rules in mind and use helper functions when necessary.

// Constants
val CELLWIDTH = 200
val CELLHEIGHT = 30

// Data Definitions
enum Team:
  case Home, Away
import Team.*

case class Player(name: String, team: Team)
// Player is (make-player String Natural[1,2])
// interp. a dodgeball player.
//   (make-player s t) represents the player named s
//   who plays on team t
val player1 = Player("Samael", Home)
val player2 = Player("Georgia", Away)

def fnForPlayer(player: Player) = ??? // player.name, player.team

// ListOfPlayer is one of:
// - empty
// - (cons Player ListOfPlayer)
// interp.  A list of players.
val playerList0 = List[Player]()
val playerList1 = List(player1, player2)


def fnForPlayerList(players: List[Player]) = players match
  case head :: next => ??? // fnForPlayer(head), fnForPlayerList(next)
  case Nil => ???

// Functions
// PROBLEM 1:
// Design a function called select-players that consumes a list
// of players and a team t (Natural[1,2]) and produces a list of
// players that are on team t.
// ListOfPlayer Natural[1,2] -> ListOfPlayer
// produce the list of given players who are on a given team
selectPlayers(playerList0, Home) == Nil
selectPlayers(playerList0, Away) == Nil
selectPlayers(playerList1, Home) == List(player1)
selectPlayers(playerList1, Away) == List(player2)

// def selectPlayers(players: List[Player]): List[Player] = Nil // stub
def template(players: List[Player], team: Team): List[Player] =
  players match
    case head :: next => ??? // team, fnForPlayer(head), selectPlayers(next, team)
    case Nil => ???

def selectPlayers(players: List[Player], team: Team): List[Player] =
  players match
    case head :: next =>
      if onTeam(head, team)
      then head :: selectPlayers(next, team)
      else selectPlayers(next, team)
    case Nil => Nil

// Player Natural[1,2] -> Boolean
// produce true if the given player is on the given team, otherwise false.
onTeam(player1, Home)
!onTeam(player1, Away)
!onTeam(player2, Home)
onTeam(player2, Away)

// def onTeam(player: Player, team: Team): Boolean = false // stub
def onTeam(player: Player, team: Team): Boolean = team == player.team

// PROBLEM 2:
// Complete the design of render-roster. We've started you off with
// the signature, purpose, stub and examples. You'll need to use
// the function that you designed in Problem 1.
// Note that we've also given you a full function design for render-los
// and its helper, render-cell. You will need to use these functions
// when solving this problem.
// ListOfPlayer -> Image
def renderRoster(playerList: List[Player]): Image =
  renderTeam(selectPlayers(playerList, Home), Home)
    .beside(renderTeam(selectPlayers(playerList, Away), Away))

// ListOfPlayer Natural[1,2] -> Image
// given a list of players lop on team t, render an image of the roster.
// ASSUME: All the players in lop are on team t
// <template as function composition, extra atomic parameter>
def renderTeam(playerList: List[Player], team: Team): Image =
  val cellColor = team match
    case Home => blue
    case Away => red

  renderCell(s"Team: ${team.toString}", cellColor)
    .above(renderCells(playerNames(playerList), cellColor))

def playerNames(playerList: List[Player]): List[String] =
  // playerList match
  //   case head :: next => head.name :: playerNames(next)
  //   case Nil => Nil
  playerList.map(player => player.name)

def renderCells(strings: List[String], cellColor: Color): Image =
  strings match
    case head :: next =>
      renderCell(head, cellColor) above renderCells(next, cellColor)
    case Nil => Image.empty

def renderCell(string: String, cellColor: Color): Image =
  Text(string).on(Rectangle(CELLWIDTH, CELLHEIGHT).strokeColor(cellColor))

// renderRoster(playerList1).draw()

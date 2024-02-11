// championship-bracket-solution.rkt
// The weekend of October 17-20, 2013, USA Ultimate held its annual national
// championships tournament in Frisco, Texas.  In this problem, you will
// represent and operate on information about the results of the women's
// competition. Here is a diagram of the results:
// (Taken from http://scores.usaultimate.org/scores/#womens/tournament/13774)
// See http://en.wikipedia.org/wiki/Bracket_(tournament) for an explanation of
// tournament brackets.
// Here is a simple data definition for representing a completed game play
// bracket. For simplicity, it does not represent the scores of the games:
// it only includes the teams, their match-ups, and who won (in green) and
// lost (in white).
// To make sure you understand this data definition, we recommend that you
// copy the types comment and template into their own file, print it out,
// and draw the proper reference arrows.
type Team = String

enum Bracket:
  case FirstRound
  case Round(
      winner: Team,
      loser: Team,
      winnerBracket: Bracket,
      loserBracket: Bracket
  )
import Bracket.*

// Bracket is one of:
// - false
// - (make-bracket String String Bracket Bracket)
// interp.  A tournament competition bracket.
//    false indicates an empty bracket.
//    (make-bracket t1 t2 br1 br2) means that
//    - team t1 beat team t2, and
//    - br1 represents team t1's bracket leading up to this match.
//    - br2 represents team t2's bracket leading up to this match.

val bracket0 = FirstRound // an empty tournament bracket,

// PROBLEM 1:
// We have intentionally left out definitions for 3 of the brackets that make up
// bracket BN. Give definitions for those 3 brackets below. If you run the file
// you'll see which definitions are missing.
// Real bracket examples are named using the bracket letter from the figure.

// 1st round: Riot defeat Schwa
val riotSchwa1 = Round("Riot", "Schwa", FirstRound, FirstRound)

// 1st round: Nemesis defeat Ozone
val nemesisOzone1 = Round("Nemesis", "Ozone", FirstRound, FirstRound)

// 1st round: Scandal defeat Phoenix
val scandalPhoenix1 = Round("Scandal", "Phoenix", FirstRound, FirstRound)

// 1st round: Capitals defeat Traffic
val capitalsTraffic1 = Round("Capitals", "Traffic", FirstRound, FirstRound)

// 2nd Round: Riot defeat Nemesis
val riotNemesis2 = Round("Riot", "Nemesis", riotSchwa1, nemesisOzone1)

// 2nd Round: Scandal defeat Capitals
val scandalCapitals2 =
  Round("Scandal", "Capitals", scandalPhoenix1, capitalsTraffic1)

// 3rd Round: Scandal defeat Riot
val scandalRiot3 =
  Round("Scandal", "Riot", scandalCapitals2, riotNemesis2)

def fnForBracket(bracket: Bracket) = bracket match
  case FirstRound                                        => ???
  case Round(winner, loser, winnerBracket, loserBracket) => ???

// SIDE NOTE: we use false to represent an empty bracket so that you cannot
// confuse an empty bracket with an empty list of teams, which is represented
// using empty.

// PROBLEM 2:
// Once a tournament is over, it can be hard to remember even some of the teams
// that your favorite team defeated and the order in which your team played them
// Design a function that takes a bracket and a list of teams and checks whether
// or not the winner of the bracket beat those teams in reverse order on the way
// to winning the current round. Not all of the teams that the victor played
// need to be listed, but the victor must have played the teams listed, and
// those teams must be listed in order from the most recent (latest round)
// win to least recent win (earliest round of tournament play).
// For instance, if we just consider Fury's semifinal bracket, then we know that
// Fury beat Showdown in the semifinals and beat Nova in the first round, so
// (list "Showdown" "Nova") is a good list of ordered wins for the semifinals,
// and so is just (list "Showdown").  (list "Traffic" "Nova") is not a good list
// because Fury didn't play Traffic, and (list "Nova" "Brute Squad") is not good
// because the order of wins is wrong.
// You must include a properly formed cross-product of types comment table in
// your solution.You must render it as text in a comment box. It should come
// after the purpose.  You may find it helpful to draw your cross-product on
// paper for your design and then use a tool like http://www.asciiflow.com/#Draw
// to help you render it.  As part of the simplification, number each subclass
// that produces different answers.  For each cell in the cross-product table,
// label them with the appropriate subclass number. In your final function,
// number each case of your cond with a comment to show which subclass it
// corresponds to in the table.

// Bracket ListOfTeam -> Boolean
// determine if the lot teams were beaten by the bracket
// winner in most-recent order.
//    ╔═══════════════════════╦════════════════════╦═══════════════════════════╗
//    ║                       ║                    ║                           ║
//    ║                       ║                    ║                           ║
//    ║                    br ║                    ║                           ║
//    ║                       ║       false        ║         bracket           ║
//    ║                       ║                    ║                           ║
//    ║                       ║                    ║                           ║
//    ║  lot                  ║                    ║                           ║
//    ║                       ║                    ║                           ║
//    ╠═══════════════════════╬════════════════════╩═══════════════════════════╣
//    ║                       ║                                                ║
//    ║                       ║                                                ║
//    ║                       ║                                                ║
//    ║    empty              ║             true     (** 1 **)                 ║
//    ║                       ║                                                ║
//    ║                       ║                                                ║
//    ║                       ║                                                ║
//    ╠═══════════════════════╬════════════════════╦═══════════════════════════╣
//    ║                       ║                    ║       (** 3 **)           ║
//    ║                       ║                    ║                           ║
//    ║                       ║                    ║1) (string=?  (first lot)  ║
//    ║    cons               ║      false         ║    (bracket-team-lost br))║
//    ║                       ║                    ║recurse on rest lot, won br║
//    ║                       ║    (** 2 **)       ║2) (not string=? ...)      ║
//    ║                       ║                    ║ recurse on lot, won br    ║
//    ╚═══════════════════════╩════════════════════╩═══════════════════════════╝
orderedWins(FirstRound, Nil)
!orderedWins(FirstRound, List("Capitals"))
orderedWins(scandalRiot3, Nil)
orderedWins(scandalRiot3, List("Capitals"))
orderedWins(scandalRiot3, List("Riot", "Phoenix"))
!orderedWins(scandalRiot3, List("Phoenix", "Riot"))
orderedWins(scandalRiot3, List("Capitals", "Phoenix"))
!orderedWins(scandalRiot3, List("Riot", "Ozone"))
!orderedWins(scandalRiot3, List("Ozone", "Riot"))
orderedWins(scandalRiot3, List("Riot", "Capitals", "Phoenix"))

// template taken from cross product table
// 4 cases simplified to 3
def orderedWins(bracket: Bracket, teams: List[Team]): Boolean =
  (bracket, teams) match
    case (_, Nil)        => true
    case (FirstRound, _) => false
    case (Round(winner, loser, winnerBracket, loserBracket), head :: next) =>
      if head == loser
      then orderedWins(winnerBracket, next)
      else orderedWins(winnerBracket, teams)

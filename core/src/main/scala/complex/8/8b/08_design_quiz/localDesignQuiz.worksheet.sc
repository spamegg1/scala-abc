// Problem 1:
// Suppose you have rosters for players on two opposing tennis team, and each
// roster is ordered by team rank, with the best player listed first. When both
// teams play, the best players of each team play one another,
// and the second-best players play one another, and so on down the line. When
// one team has more players than the other, the lowest ranking players on
// the larger team do not play.
// Design a function that consumes two rosters, and produces true if all players
// on both teams will play if the teams play each other.
// No marks will be given to solution that do not use a cross product table.
type Player = String
type Roster = List[Player]

case class Match(p1: Player, p2: Player)
// interp.  a match between player p1 and player p2, with same team rank
val match0 = Match("Eugenie", "Maria")
val match1 = Match("Gabriela", "Nadia")

// Roster Roster -> Boolean
// produce true if all players on both rosters will play in a match
//(define (roster-match r1 r2) false) //stub
def rosterMatch(roster1: Roster, roster2: Roster): Boolean =
  (roster1, roster2) match
    case (Nil, Nil)           => true
    case (Nil, _)             => false
    case (_, Nil)             => false
    case (h1 :: n1, h2 :: n2) => rosterMatch(n1, n2)

// Cross Product of Type Comments Table
//-------------------------------------------
//         r2  |   empty      |   non-empty
// r1          |              |
//-------------------------------------------
//  empty      |   true       |   false
//             |              |
//-------------------------------------------
//  non-empty  |   false      |   ???
//             |              |
//-------------------------------------------

// Problem 2:
// Now write a function that, given two teams, produces the list of tennis
// matches that will be played.
// Assume that this function will only be called if the function you designed
// above produces true. In other words, you can assume the two teams have the
// same number of players.

// Roster Roster -> ListOfMatch
// produces list of matches that will be played
// ASSUMES both rosters have equal number of players
//(define (matches r1 r2) empty) //stub
def matches(roster1: Roster, roster2: Roster): List[Match] =
  (roster1, roster2) match
    case (Nil, Nil) => Nil
    case _ =>
      Match(roster1.head, roster2.head) :: matches(roster1.tail, roster2.tail)

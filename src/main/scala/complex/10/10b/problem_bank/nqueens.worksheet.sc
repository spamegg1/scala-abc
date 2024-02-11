// A simple solution to the generic nqueens problem.
//  - uses lambda to reduce and simplify the code

// Data definitions:
// Position is Natural
// interp. positions on the board
//         if    N is the number of queens
//         then  (sqr N) is the number of positions on the board
//         so    this number should be in [0, (- (sqr N) 1)]
type Pos = Int
// (define P1 0)        ;upper left corner of board
// (define P2 (- 16 1)) ;lower right corner of 4x4 board

// Board is (listof Position)  up to N elements long
type Board = List[Pos]
// interp. the positions of the queens that have been placed on the board
// (define BD1 empty)           ;no queens placed
// (define BD2 (list 0))        ;one queen in upper left corner
// (define BD3 (list 14 8 7 1)) ;a solution to 4x4 puzzle

// Functions:
// NOTE about following function. It could have been designed with N as a
// top-level constant and all the locally defined functions as top-level
// functions. But doing it the way we have done below, makes it easy to make
// the top-level nqueens function consume N which is kind of nice.  The
// trampoline starts the actual search out by calling fn-for-bd with an empty
// board.

// (@signature Natural -> Board or false)
// produce first found solution for n queens of size N; or false if none exists
nQueens(1) == Some(List(0))
nQueens(2) == None
nQueens(3) == None
nQueens(4) == Some(List(14, 8, 7, 1))
nQueens(5) == Some(List(22, 19, 11, 8, 0))
// nQueens(6) == Some(List(34, 26, 18, 17, 9, 1))
// nQueens(7) == Some(List(47, 38, 29, 27, 18, 9, 0))
// nQueens(8) == Some(List(59, 49, 46, 34, 29, 23, 12, 0))

// (@template encapsulated backtracking genrec arb-tree)
// def nQueens(n: Int): Option[Board] = None // stub
def nQueens(n: Int): Option[Board] =
//   Termination argument:
//   Trivial cases:
//     bd is solved or there are no valid next boards left to explore
//   Reduction step:
//     (fn-for-lobd (next-boards bd)) in other words go explore the
//     valid next boards of this board
//   Since board is finite, and each board is explored at most once,
//   search will definitely terminate. (But the search space does grow
//   really fast!)
  def fnForBoard(board: Board): Option[Board] =
    if board.length == n then Some(board) else fnForBoardList(nextBoards(board))

  def fnForBoardList(boards: List[Board]): Option[Board] = boards match
    case head :: next =>
      val attempt = fnForBoard(head)
      if attempt.isDefined then attempt
      else fnForBoardList(next)
    case Nil => None

  def attacksQueen(pos: Pos, queen: Pos): Boolean =
    val (x1, y1, x2, y2) = (pos % n, pos / n, queen % n, queen / n)
    x1 == x2 ||
    y1 == y2 ||
    (y2 - y1) / (x2 - x1) == 1 ||
    (y2 - y1) / (x2 - x1) == -1

  def avoidsQueens(pos: Pos, board: Board): Boolean =
    board.forall(queen => !attacksQueen(pos, queen))

  def nextBoards(board: Board): List[Board] =
    (0 until n * n)
      .filter(pos => avoidsQueens(pos, board))
      .map(pos => pos :: board)
      .toList

  fnForBoard(Nil)

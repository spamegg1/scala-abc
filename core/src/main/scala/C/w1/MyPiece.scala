package C.w1

class MyPiece(pointArray: Array[(Int, Int)], board: Board)
    extends Piece(pointArray, board):
  // your enhancements here

  // class method to choose the next piece
  def nextPiece(newBoard: Board) =
    // MyPiece.new(All_My_Pieces.sample, board)
    ???

object MyPiece:
  // The constant allMyPieces should be declared here
  val allMyPieces: Array[Array[(Int, Int)]] = Piece.allPieces ++
    Piece.rotations(Array((0, 0), (1, 0), (0, -1), (-1, -1), (-1, 0))) ++ // 5-sq
    Piece.rotations(Array((0, 0), (0, 1), (1, 1))) ++ // short-L
    Array(
      Array((0, 0), (-1, 0), (1, 0), (-2, 0), (2, 0)), // 5-long (only needs two)
      Array((0, 0), (0, -1), (0, 1), (0, -2), (0, 2))
    )

package C.w1

/** The Tetris screen is divided up into a grid of squares. The top-left corner is (0, 0),
  * the x coordinate grows to the right, the y coordinate grows downward.
  */
type Pos = (x: Int, y: Int)

/** A sequence of positions is used to define a piece (a.k.a. tetromino). */
type Points = Seq[Pos]

/** A rotation is the same as a piece (sequence of positions). */
type Rotation = Seq[Pos]

/** Each piece has a sequence of all of its possible rotations. */
type Rotations = Seq[Rotation]

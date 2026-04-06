// PROBLEM:
// Imagine that you are designing a program to manage ticket sales for a
// theatre. (Also imagine that the theatre is perfectly rectangular in shape!)
// Design a data definition to represent a seat number in a row, where each
// row has 32 seats. (Just the seat number, not the row number.)

// Data definitions:
// SeatNumber is Natural[1, 32]
// Interp. Seat numbers in a row, 1 and 32 are aisle seats
case class SeatNumber(number: Int):
  require(1 <= number && number <= 32)

val seatNumber1 = SeatNumber( 1) // aisle
val seatNumber2 = SeatNumber(12) // middle
val seatNumber3 = SeatNumber(32) // aisle

// template
def fnForSeatNumber(seatNumber: SeatNumber) = ???
  // seatNumber.number ???

// Template rules used:
//  - atomic non-distinct: Natural[1, 32]

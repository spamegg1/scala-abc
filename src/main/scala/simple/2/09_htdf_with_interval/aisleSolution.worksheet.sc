// PROBLEM:
// Using the SeatNum data definition below design a function
// that produces true if the given seat number is on the aisle.

// Data definitions:
// SeatNum is Natural[1, 32]
// Interp. Seat numbers in a row, 1 and 32 are aisle seats
case class SeatNumber(seat: Int):
  require(1 <= seat && seat <= 32)

val seat1 = SeatNumber(1 ) // aisle
val seat2 = SeatNumber(16) // middle
val seat3 = SeatNumber(32) // aisle

// Template
def fnForSeatNumber(seatNumber: SeatNumber) = seatNumber match
  case SeatNumber(_) => ???

// Template rules used:
//  - atomic non-distinct: Natural[1, 32]


// Functions:
// SeatNum -> Boolean
// produce true if the given seat number is on the aisle
aisle(seat1)
!aisle(seat2)
aisle(seat3)

// def aisle(seatNumber: SeatNumber) = false // stub

// <use template from SeatNum>
def aisle(seatNumber: SeatNumber) = seatNumber match
  case SeatNumber(seat) => 1 == seat || seat == 32

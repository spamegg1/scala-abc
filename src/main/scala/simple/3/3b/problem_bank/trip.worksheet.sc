// Data definitions:
// PROBLEM A:
// Design a data definition to help travellers plan their next trip.
// A trip should specify an origin, destination, mode of transport and
// duration (in days).
enum Transport:
  case Flight, Car, Ferry
import Transport.*

// a trip with origin, destination, mode of transport, and duration in days
case class Trip(
    origin: String,
    destination: String,
    mode: Transport,
    duration: Int
):
  require(0 <= duration)

val trip1 = Trip("Vancouver", "Cancun", Flight, 10)
val trip2 = Trip("Calgary", "Ottawa", Car, 14)
val trip3 = Trip("Montreal", "New York", Ferry, 5)

def fnForTrip(trip: Trip) = ???
// ???(trip.origin, trip.destination, trip.mode, trip.duration)

// ;; Template rules used:
// ;; - compound: 4 fields

// Functions:
// PROBLEM B:
// You have just found out that you have to use all your days off work
// on your next vacation before they expire at the end of the year.
// Comparing two options for a trip, you want to take the one that
// lasts the longest. Design a function that compares two trips and
// returns the trip with the longest duration.

// Note that the rule for templating a function that consumes two
// compound data parameters is for the template to include all
// the selectors for both parameters.
// Trip Trip -> Trip
// produce the trip with the longer duration,
// if durations are equal produce the second trip
longerTrip(trip1, trip2) == trip2
longerTrip(trip1, trip3) == trip1
longerTrip(trip3, trip2) == trip2

// def longerTrip(trip1: Trip, trip2: Trip): Trip = trip1 // stub
def longerTrip(trip1: Trip, trip2: Trip): Trip =
  if trip1.duration > trip2.duration
  then trip1
  else trip2

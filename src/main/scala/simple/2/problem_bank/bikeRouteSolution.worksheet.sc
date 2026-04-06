import doodle.image.Image
import doodle.image.syntax.all.ImageOps
import doodle.java2d.java2dRenderer
import cats.effect.unsafe.implicits.global

// PROBLEM a:
// Suppose you are developing a route planning tool for bicycling in Vancouver.
// There are four varieties of designated bike routes:
// 1) Separated Bikeway
// 2) Local Street Bikeway
// 3) Painted Bike Lane
// 4) Painted Shared-Use Lane
// Use the HtDD recipe to design a data definition for varieties of bike routes
// (call it BikeRoute)

// BikeRoute is one of:
// - "Separated Bikeway"
// - "Local Street Bikeway"
// - "Painted Bike Lane"
// - "Painted Shared-Use Lane"
// interp.  Varieties of bike routes in Vancouver
enum BikeRoute:
  case
    SeparatedBikeway,
    LocalStreetBikeway,
    PaintedBikeLane,
    PaintedSharedUseLane

import BikeRoute.*
// <examples are redundant for enumerations>

def fnForBikeRoute(route: BikeRoute) = route match
  case SeparatedBikeway     => ???
  case LocalStreetBikeway   => ???
  case PaintedBikeLane      => ???
  case PaintedSharedUseLane => ???

// Template Rules Used:
// - one of: 4 cases
// - atomic distinct: "Separated Bikeway"
// - atomic distinct: "Local Street Bikeway"
// - atomic distinct: "Painted Bike Lane"
// - atomic distinct: "Painted Shared-Use Lane"

// PROBLEM b:
// Separated bikeways and painted bike lanes are exclusively designated for bicycles, while
// local street bikeways and shared-use lanes must be shared with cars and/or pedestrians.
// Design a function called 'exclusive?' that takes a bike route and indicates whether it
// is exclusively designated for bicycles.

// BikeRoute -> Boolean
// indicate whether a bike route is exclusively designated for bicycles
exclusive(SeparatedBikeway)
!exclusive(LocalStreetBikeway)
exclusive(PaintedBikeLane)
!exclusive(PaintedSharedUseLane)

// def exclusive(route: BikeRoute): Boolean = false // stub

// <Template from BikeRoute>

def exclusive(route: BikeRoute) = route match
  case SeparatedBikeway     => true
  case LocalStreetBikeway   => false
  case PaintedBikeLane      => true
  case PaintedSharedUseLane => false
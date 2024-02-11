// PROBLEM A:
// You are assigned to develop a system that will classify
// buildings in downtown Vancouver based on how old they are.
// According to city guidelines, there are three different classification levels:
// new, old, and heritage.
// Design a data definition to represent these classification levels.
// Call it BuildingStatus.

// BuildingStatus is one of:
// - "new"
// - "old"
// - "heritage"
// interp. classification of a building based on age
enum BuildingStatus:
  case New, Old, Heritage
import BuildingStatus.*

// examples redundant in enumeration

// Template
def fnForBuildingStatus(status: BuildingStatus) = status match
  case New      => ???
  case Old      => ???
  case Heritage => ???

// Template Rules Used:
// - one of: 3 cases
// - atomic distinct: "new"
// - atomic distinct: "old"
// - atomic distinct: "heritage"

// Functions:
// PROBLEM B:
// The city wants to demolish all buildings classified as "old".
// You are hired to design a function called demolish?
// that determines whether a building should be torn down or not.

// BuildingStatus -> Boolean
// produces true if building is "old" and should be demolished
!demolish(New)
demolish(Old)
!demolish(Heritage)

// def demolish(status: BuildingStatus): Boolean = false // stub

// <template from BuildingStatus>
def demolish(status: BuildingStatus) = status match
  case New      => false
  case Old      => true
  case Heritage => false

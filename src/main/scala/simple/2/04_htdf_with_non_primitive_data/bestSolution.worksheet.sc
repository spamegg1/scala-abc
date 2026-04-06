// PROBLEM:
// Using the CityName data definition below design a function
// that produces true if the given city is the best in the world.
// (You are free to decide for yourself which is the best city
// in the world.)

// Data definitions:

// CityName is String
// interp. the name of a city
type CityName = String
val cityName1 = "Boston"
val cityName2 = "Vancouver"

// template
def fnForCityName(cityName: CityName) = ???
  // cityName.???

// Template rules used:              For the first part of the course
//   atomic non-distinct: String     we want you to list the template
//                                   rules used after each template.

// Functions:
// CityName -> Boolean
// produce true if cn is the best city
!best("Boston")
best("Hogsmeade")

// def best(cityName: CityName) = false // stub

// <use template from CityName>
def best(cityName: CityName): Boolean =
  cityName == "Hogsmeade"

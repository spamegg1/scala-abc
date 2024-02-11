// Data definitions:
// PROBLEM A:
// Design a data definition to represent a movie, including
// title, budget, and year released.
// To help you to create some examples, find some interesting movie facts below:
// "Titanic" - budget: 200000000 released: 1997
// "Avatar" - budget: 237000000 released: 2009
// "The Avengers" - budget: 220000000 released: 2012
// However, feel free to resarch more on your own!
case class Movie(title: String, budget: Double, year: Int)
// Movie is (make-movie String Natural Natural)
// interp. a movie with title, budget in USD, and year released
val m1 = Movie("Titanic", 200000000.0, 1997)
val m2 = Movie("Avatar", 237000000.0, 2009)
val m3 = Movie("The Avengers", 220000000.0, 2012)

def fnForMovie(movie: Movie) = ??? // ???(movie.title, movie.budget, movie.year)
// Template rules used:
// - compound: 3 fields

// Functions:
// PROBLEM B:
// You have a list of movies you want to watch, but you like to watch your
// rentals in chronological order. Design a function that consumes two movies
// and produces the title of the most recently released movie.
// Note that the rule for templating a function that consumes two compound data
// parameters is for the template to include all the selectors for both
// parameters.

// Movie Movie -> String
// determine which of two given movies was released most recently
chronologicalMovie(m1, m2) == "Avatar"
chronologicalMovie(m3, m2) == "The Avengers"

// def chronologicalMovie(movie1: Movie, movie2: Movie): String = "" // stub
def chronologicalMovie(movie1: Movie, movie2: Movie): String =
  if movie1.year > movie2.year
  then movie1.title
  else movie2.title

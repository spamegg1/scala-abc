// author: spamegg
// Consider the following sequence of expressions:
// animals = { 'a': ['aardvark'], 'b': ['baboon'], 'c': ['coati']}
// animals['d'] = ['donkey']
// animals['d'].append('dog')
// animals['d'].append('dingo')
// We want to write some simple procedures that work on dictionaries
// to return information.
// First, write a procedure, called howMany, which returns the sum of
// the number of values associated with a dictionary. For example:
// >>> print(howMany(animals))
// 6

def howMany[S, T](aDict: Map[S, List[T]]): Int =
  // aDict: A dictionary, where all the values are lists.
  // returns: int, how many values are in the dictionary.
  // Your Code Here
  var result = 0
  for thing <- aDict.values do result += thing.size
  result

// TESTING
val example = Map('B' -> List(15), 'u' -> List(10, 15, 5, 2, 6))
assert(howMany(example) == 6)
println("Tests passed!")

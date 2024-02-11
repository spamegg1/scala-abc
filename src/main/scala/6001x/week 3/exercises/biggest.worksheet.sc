import collection.mutable.Map

// Consider the following sequence of expressions:
// animals = { 'a': ['aardvark'], 'b': ['baboon'], 'c': ['coati']}
// animals['d'] = ['donkey']
// animals['d'].append('dog')
// animals['d'].append('dingo')
// We want to write some simple procedures that work on dictionaries to
// return information.
// This time, write a procedure, called biggest, which returns the key
// corresponding to the entry with the largest number of values associated
// with it. If there is more than one such entry, return any one of the
// matching keys.
// Example usage:
// >>> biggest(animals)
// 'd'

// If there are no values in the dictionary, biggest should return None.

def biggest[S, T](aDict: Map[S, Array[T]]): Option[S] =
  // aDict: A dictionary, where all the values are Arrays.
  // returns: The key with the largest number of values associated with it
  // Your Code Here
  var result: Option[S] = None
  var biggestSoFar = 0

  for
    (key -> value) <- aDict
  do
    if value.size > biggestSoFar then
      result = Some(key)
  result

// TESTING
val animals = Map(
  'a' -> Array("aardvark"), 'b' -> Array("baboon"), 'c' -> Array("coati")
)

animals += 'd' -> Array("donkey")
animals('d').appended("dog")
animals('d').appended("dingo")

assert(biggest(animals) == Some('d'))

println("Tests passed!")

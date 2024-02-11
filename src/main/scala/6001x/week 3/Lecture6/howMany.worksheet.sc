def howMany(aDict: Map[AnyVal, List[AnyVal]]) =
  // aDict: A dictionary, where all the values are lists.
  // returns: int, how many values are in the dictionary.
  var result = 0
  for
    value <- aDict.values
  do
    result += value.length
  result


def biggest(aDict: Map[AnyVal, List[AnyVal]]) =
  // aDict: A dictionary, where all the values are lists.
  // returns: The key with the largest number of values associated with it
  if aDict.values.isEmpty then None
  else
    val (largestKey, _) = aDict.maxBy((_, value) => value.length)
    Some(largestKey)

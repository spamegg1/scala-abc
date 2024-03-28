def getPermutations(sequence: String): List[String] =
  /**
   * Enumerate all permutations of a given string
   * sequence (string): an arbitrary string to permute. Assume that it is a
   * non-empty string.
   * You MUST use recursion for this part.
   * Non-recursive solutions will not be accepted.
   * Returns: a list of all permutations of sequence
   * Example:
   * scala> getPermutations("abc")
   * List("abc", "acb", "bac", "bca", "cab", "cba")
   * Note: depending on your implementation, you may return the permutations in
   * a different order than what is listed here.
   */

  if sequence.length == 1 then List(sequence)
  else
    val first = sequence.take(1)
    val perms = getPermutations(sequence.drop(1))
    for
      perm <- perms
      index <- 0 to perm.length
      (left, right) = (perm.take(index), perm.drop(index))
    yield
      left + first + right


// TESTING
val exampleInput  = "abc"
val exampleOutput = List("abc", "acb", "bac", "bca", "cab", "cba")

println("Input: " + exampleInput)
println("Expected Output: " + exampleOutput.toString)
println("Actual Output: " + getPermutations(exampleInput).toString)

// Put three example test cases here (for your sanity, limit your inputs
// to be three characters or fewer as you will have n! permutations for a
// sequence of length n)

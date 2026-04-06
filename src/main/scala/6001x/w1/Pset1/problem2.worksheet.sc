// Assume s is a string of lower case characters.
// Write a program that prints the number of times the string "bob" occurs in s.
// For example, if s = "azcbobobegghakl", then your program should print
// Number of times bob occurs is: 2

val s = "azcbobobegghaklbo"

var occurrences = 0
var start = 0

while start < s.length do
  val index = s.drop(start).indexOf("bob")
  if index == -1 then start = s.length // break
  else
    occurrences += 1
    start += index + 2

s"Number of times bob occurs is: $occurrences"

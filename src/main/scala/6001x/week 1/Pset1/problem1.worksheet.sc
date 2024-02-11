// Assume s is a string of lower case characters.
// Write a program that counts up the number of vowels contained in the string s.
// Valid vowels are: 'a', 'e', 'i', 'o', and 'u'.
// For example, if s = 'azcbobobegghakl', your program should print:
// Number of vowels: 5
val VOWELS = "aeiou"
val s = "azcbobobegghakl"

var vowels = 0
for
  letter <- s
do
  if VOWELS contains letter then
    vowels += 1
println("Number of vowels: " + vowels)

// better:
s.count(char => VOWELS contains char)

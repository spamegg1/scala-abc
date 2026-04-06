def printName(firstName: String, lastName: String, reverse: Boolean) =
  if reverse then println(s"$lastName, $firstName")
  else println(s"$firstName $lastName")

printName("spam", "egg", true)

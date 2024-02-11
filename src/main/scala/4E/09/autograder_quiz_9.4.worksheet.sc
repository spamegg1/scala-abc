// 9.4 Write a program to read through the mbox-short.txt and
// figure out who has sent the greatest number of mail messages.
// The program looks for 'From ' lines and takes the second word
// of those lines as the person who sent the mail.
// The program creates a Python dictionary that maps the sender's
// mail address to a count of the number of times they appear in the file.
// After the dictionary is produced, the program reads through the dictionary
// using a maximum loop to find the most prolific committer.
// Desired output:
// cwen@iupui.edu 5

import scala.io.Source.fromResource
import collection.mutable.Map

val fileName = "mbox-short.txt"
val file = fromResource(fileName)

val senders = Map[String, Int]()

for
  line <- file.getLines
  if line.startsWith("From ")
do
  val sender = line.split(" ")(1)
  senders(sender) = senders.getOrElse(sender, 0) + 1

var maximum = 0
var topSender = ""
for
  (sender, count) <- senders
  if count > maximum
do
  maximum = count
  topSender = sender

print(f"${topSender} ${maximum}")
file.close()

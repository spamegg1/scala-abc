package C.w1

import scala.util.CommandLineParser.FromString.given_FromString_String

given util.CommandLineParser.FromString[Array[String]] with
  def fromString(s: String): Array[String] = s.split(" ")

def mainLoop = ???
// Tk.mainloop

def exitProgram = ???
// Tk.exit

def runTetris = ???
// Tetris.new
// mainLoop

def runMyTetris = ???
//   MyTetris.new
//   mainLoop

@main
def run(args: Array[String]): Unit =
  if args.size == 0 then runMyTetris
  else if args.size != 1 then println("usage: hw6runner.rb [enhanced | original]")
  else if args.head == "enhanced" then runMyTetris
  else if args.head == "original" then runTetris
  else println("usage: hw6runner.rb [enhanced | original]")

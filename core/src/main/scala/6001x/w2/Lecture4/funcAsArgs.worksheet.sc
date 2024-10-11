def funA(): Unit = println("inside funA")

def funB(y: Int): Int =
  println("inside funB")
  y

def funC(z: () => Unit) =
  println("inside funC")
  z()

funA()
5 + funB(2)
funC(funA)

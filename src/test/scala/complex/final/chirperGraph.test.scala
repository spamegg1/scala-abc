package complex.finalExam

class ChirperSuite extends munit.FunSuite:

  // import scalax.collection.Graph
  // import scalax.collection.GraphPredef.*
  // import scalax.collection.GraphEdge.*

  /*  set-up some Chirper users to place in a graph  */
  val a = Chirper("A", true)
  val b = Chirper("B", false)
  val c = Chirper("C", true)
  val d = Chirper("D", true)
  val e = Chirper("E", false)
  val f = Chirper("F", true)

  /*  graph  */
  /*val graph = Graph(
    a ~> b,
    a ~> d, // A follows B and D
    b ~> c,
    b ~> e, // B follows C and E
    c ~> b, // C follows B
    d ~> e, // D follows E
    e ~> a,
    e ~> f // E follows A and F
    // F follows nobody
  )*/

  test("chirper test"):
    val inputs: List[Any] = Nil
    val expected: List[Any] = Nil
    assertEquals(inputs, expected)

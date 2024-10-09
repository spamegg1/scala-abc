// These are just like imports, but available everywhere in the project.
// They cut down on repeated imports in dozens of files.
// Does not work in worksheets (they are outside project scope).
// Think of it as an addition to scala.Prelude.
export scala.io.StdIn.readLine
export scala.io.Source.fromResource
export scala.util.boundary, boundary.break
export scala.util.Using
export collection.mutable.{Map => MMap}

package curriculum
package plc
package w7

class Var(s: String) extends GeoExp:
  def evalProg(env: Map[String, GeoVal]): GeoVal =
    env.get(s) match
      case Some(geoVal) => geoVal
      case None         => throw new NoSuchElementException("undefined variable")

  def preprocessProg: Geometry = this

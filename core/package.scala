package curriculum

// These are just like imports, but available everywhere in the project.
// They cut down on repeated imports in dozens of files.
// Does not work in worksheets (they are outside project scope).
// Think of it as an addition to scala.Prelude.
export java.io.{FileNotFoundException, IOException}
export java.nio.file.AccessDeniedException
export java.time.{LocalDate, Period}
export net.ruippeixotog.scalascraper.browser.JsoupBrowser
export net.ruippeixotog.scalascraper.dsl.DSL.{deepFunctorOps, Extract, RichHtmlExtractor}
export scala.collection.mutable.{Map => MMap, ArrayBuffer}
export scala.io.StdIn.readLine
export scala.io.Source.fromResource
export scala.util.{boundary, Using, Random}, boundary.break
export scala.xml.XML
export spray.json.{DefaultJsonProtocol, enrichString, JsObject}, DefaultJsonProtocol.*
export sttp.client4.{DefaultSyncBackend, basicRequest, UriContext}

// some commonly repeated utility functions.
extension (double: Double)
  def round(places: Int): Double =
    BigDecimal(double).setScale(places, BigDecimal.RoundingMode.HALF_EVEN).toDouble

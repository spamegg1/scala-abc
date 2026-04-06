package curriculum

// some commonly repeated utility functions.
extension (double: Double)
  def round(places: Int): Double =
    BigDecimal(double).setScale(places, BigDecimal.RoundingMode.HALF_EVEN).toDouble

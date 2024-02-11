package MIT6001x.mortgage

import math.*
import BigDecimal.RoundingMode.HALF_EVEN

def round(x: Double, n: Int): Double =
  BigDecimal(x).setScale(n, HALF_EVEN).toDouble

class Fixed(loan: Double, r: Double, months: Int)
  extends Mortgage(loan, r, months):
  legend = Some(s"Fixed, ${round(r * 100, 2)}%")

class FixedWithPts(loan: Double, r: Double, months: Int, pts: Double)
  extends Mortgage(loan, r, months):
  paid = List(loan * pts / 100)
  legend = Some(s"Fixed, ${round(r * 100, 2)} %, ${pts} points")

class TwoRate(
  loan: Double, r: Double, months: Int, teaserRate: Double, teaserMonths: Int
) extends Mortgage(loan, r, months):

  val nextRate = r / 12
  legend = Some(
    s"${teaserRate * 100} % for ${teaserMonths} months, then ${round(r * 100, 2)} %"
  )

  override def makePayment =
    if paid.length == teaserMonths + 1 then
      rate = nextRate
      val lastPlace = outstanding.length - 1
      val lastOutstanding = outstanding(lastPlace)
      payment = findPayment(
        lastOutstanding,
        rate,
        months - teaserMonths
      )
    super.makePayment

def compareMortgages(
  amt: Double,
  years: Int,
  fixedRate: Double,
  pts: Double,
  ptsRate: Double,
  varRate1: Double,
  varRate2: Double,
  varMonths: Int
) =
  val totMonths = years * 12
  val fixed1 = Fixed(amt, fixedRate, totMonths)
  val fixed2 = FixedWithPts(amt, ptsRate, totMonths, pts)
  val twoRate = TwoRate(amt, varRate2, totMonths, varRate1, varMonths)
  val morts = List(fixed1, fixed2, twoRate)
  for
    m <- 0 until totMonths
  do
    for
      mort <- morts
    do
      mort.makePayment
  for
    m <- morts
  do
    println(m)
    println(s"Total payments = $$${m.getTotalPaid}")

object CompareMortgages:
  val x = compareMortgages(
    amt=200000,
    years=30,
    fixedRate=0.07,
    pts = 3.25,
    ptsRate=0.05,
    varRate1=0.045,
    varRate2=0.095,
    varMonths=48
  )

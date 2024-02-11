package MIT6001x.mortgage

import math.abs

def findMaxPoints(
  amt: Double,
  years: Int,
  fixedRate: Double,
  ptsRate: Double,
  minPts: Int,
  maxPts: Int
) =
  val totMonths = years * 12
  val fixed1 = Fixed(amt, fixedRate, totMonths)
  for
    m <- 0 until totMonths
  do
    fixed1.makePayment
  val baseAmount = fixed1.getTotalPaid
  println(s"base amount ${baseAmount}")

  // want to find out how many points you can tolerate
  val span = (maxPts - minPts) * 10

  for
    incr <- 0 until span
  do
    val pts = minPts + incr * 0.1
    val fixed2 = FixedWithPts(amt, ptsRate, totMonths, pts)
    for
      m <- 0 until totMonths
    do
      fixed2.makePayment

    val ptsAmount = fixed2.getTotalPaid
    if ptsAmount - baseAmount > 0 then
      println(s"maximum points affordable ${pts}")
    pts

def findMaxPointsBisection(
  amt: Double,
  years: Int,
  fixedRate: Double,
  ptsRate: Double,
  minPts: Int,
  maxPts: Int
) =
  val totMonths = years * 12
  val fixed1 = Fixed(amt, fixedRate, totMonths)
  for
    m <- 0 until totMonths
  do
    fixed1.makePayment
  val baseAmount = fixed1.getTotalPaid
  println(s"base amount ${baseAmount}")

  // want to find out how many points you can tolerate
  var eps = 1000
  var high = maxPts.toDouble
  var low = minPts.toDouble
  var closeEnuf = false
  var pts = 0.0

  while
    !closeEnuf
  do
    pts = (high - low) / 2
    println(s"current points ${pts}")
    // input("next")
    val fixed2 = FixedWithPts(amt, ptsRate, totMonths, pts)
    for
      m <- 0 until totMonths
    do
      fixed2.makePayment

    val ptsAmount = fixed2.getTotalPaid
    println(s"pts amount ${ptsAmount}")

    if abs(ptsAmount - baseAmount) < eps then
      closeEnuf = true
    else if ptsAmount < baseAmount then
      high = pts
    else
      low = pts

  println(s"maximum points affordable ${pts}")
  pts

object MaxPoints:
  findMaxPoints(200000, 30, 0.07, 0.06875, 0, 10)
  findMaxPointsBisection(200000, 30, 0.07, 0.6875, 0, 10)

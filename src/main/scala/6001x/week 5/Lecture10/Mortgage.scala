package MIT6001x.mortgage

import math.pow

def findPayment(loan: Double, r: Double, m: Int) =
  // Assumes: loan and r are floats, m an int
  // Returns the monthly payment for a mortgage of size
  // loan at a monthly rate of r for m months
  loan * r * pow(1 + r, m) / (pow(1 + r, m) - 1)


class Mortgage(loan: Double, annRate: Double, months: Int):
  // Abstract class for building different kinds of mortgages
  // Assumes: loan and annRate are floats, months an int
  // Creates a new mortgage of size loan, duration months, and
  // annual rate annRate
  var rate = annRate / 12
  var paid = List(0.0)
  var outstanding = List(loan)
  var payment = findPayment(loan, rate, months)
  var legend: Option[String] = None // description of mortgage

  def makePayment =
    // Make a payment
    paid = payment :: paid
    val lastPlace = outstanding.length - 1
    val lastOutstanding = outstanding(lastPlace)
    val reduction = payment - lastOutstanding * rate
    val reducedVal = lastOutstanding - reduction
    outstanding = reducedVal :: outstanding

  def getTotalPaid = paid.sum // the total amount paid so far

  override def toString = legend.getOrElse("")

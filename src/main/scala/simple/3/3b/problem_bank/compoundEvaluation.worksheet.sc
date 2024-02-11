// PROBLEM:
// Given the following definitions:
case class CensusData(city: String, population: Int)
def countNewborn(data: CensusData): CensusData =
  CensusData(data.city, data.population + 1)
// Write down the evaluation steps for the following expression.
countNewborn(CensusData("Vancouver", 603502))

CensusData(
  CensusData("Vancouver", 603502).city,
  CensusData("Vancouver", 603502).population + 1
)
CensusData(
  "Vancouver",
  CensusData("Vancouver", 603502).population + 1
)
CensusData("Vancouver", 603502 + 1)
CensusData("Vancouver", 603503)

val cube = 29
for guess <- 0 to cube do
  if math.pow(guess, 3) == cube then println(s"Cube root of ${cube} is ${guess}")

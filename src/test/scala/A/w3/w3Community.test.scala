package curriculum
package pla
package w3

class Week2CommunitySuite extends munit.FunSuite:
  test("01. composeOpt"):
    val firstArgs: List[Int => Option[Int]] = List(x => Some(x * x)
    // add more functions here!
    )
    val secondArgs: List[Int => Option[Int]] = List(y => Some(y * 2)
    // add more functions here!
    )
    val funs: List[Int => Option[Int]] =
      for
        first <- firstArgs
        second <- secondArgs
      yield composeOpt(first)(second)
    val inputs: List[Int] = List(
      5,
      2
      // add more test cases here!
    )
    val applied: List[List[Option[Int]]] = funs map (fun => inputs map fun)
    val expected: List[List[Option[Int]]] = List(
      List(
        Some(100),
        Some(16) // add more cases here (for each input)
      )
      // add more test cases here (for each function pair)
    )
    assertEquals(applied, expected)

  test("02. doUntil"):
    val firstArgs: List[Int => Int] = List(x => x / 2
    // add more functions here!
    )
    val secondArgs: List[Int => Boolean] = List(y => y % 2 != 1
    // add more functions here!
    )
    val funs: List[Int => Int] =
      for
        first <- firstArgs
        second <- secondArgs
      yield doUntil(first)(second)
    val inputs: List[Int] = List(
      1024
      // add more test cases here!
    )
    val applied: List[List[Int]] = funs map (fun => inputs map fun)
    val expected: List[List[Int]] = List(
      List(
        1 // add more cases here (for each input)
      )
      // add more test cases here (for each function pair)
    )
    assertEquals(applied, expected)

  test("03. factorial"):
    val inputs: List[Int] = List(
      5
      // add more test cases here!
    )
    val expected: List[Int] = List(
      120
      // add more test cases here!
    )
    assertEquals(inputs map factorial, expected)

  test("04. fixedPoint"):
    val firstArgs: List[Int => Int] = List(x => x / 2
    // add more functions here!
    )
    val funs: List[Int => Int] = firstArgs map fixedPoint
    val inputs: List[Int] = List(
      1024
      // add more test cases here!
    )
    val applied: List[List[Int]] = funs map (fun => inputs map fun)
    val expected: List[List[Int]] = List(
      List(
        0 // add more test cases here (for each input)
      )
      // add more test cases here (for each function)
    )
    assertEquals(applied, expected)

  test("05. map2"):
    val firstArgs: List[Int => Boolean] = List(x => x % 2 == 0
    // add more functions here!
    )
    val funs: List[(Int, Int) => (Boolean, Boolean)] = firstArgs map map2
    val inputs: List[(Int, Int)] = List(
      (1, 2)
      // add more test cases here!
    )
    val applied: List[List[(Boolean, Boolean)]] =
      funs map (fun => inputs map fun.tupled)
    val expected: List[List[(Boolean, Boolean)]] = List(
      List(
        (false, true) // add more cases here (for each input)
      )
      // add more test cases here (for each function)
    )
    assertEquals(applied, expected)

  test("06. appAll"):
    val firstArgs: List[Int => List[Double]] = List(x => List(x / 2, x / 4)
    // add more functions here!
    )
    val secondArgs: List[String => List[Int]] = List(x => List(x.length)
    // add more functions here!
    )
    val funs: List[String => List[Double]] =
      for
        first <- firstArgs
        second <- secondArgs
      yield appAll(first)(second)
    val inputs: List[String] = List(
      "spam"
      // add more test cases here!
    )
    val applied: List[List[List[Double]]] = funs map (fun => inputs map fun)
    val expected: List[List[List[Double]]] = List(
      List(
        List(2.0, 1.0) // add more cases here (for each input)
      )
      // add more test cases here (for each function pair)
    )
    assertEquals(applied, expected)

  test("07. myFoldRight"):
    val funArgs: List[(Int, Boolean) => Boolean] =
      List((x, y) => (x % 2 == 0) && y
      // add more functions here!
      )
    val funs: List[List[Int] => Boolean] = funArgs map myFoldRight(true)
    val inputs: List[List[Int]] = List(
      List(2, 4, 6),
      List(2, 4, 5)
      // add more test cases here!
    )
    val applied: List[List[Boolean]] = funs map (fun => inputs map fun)
    val expected: List[List[Boolean]] = List(
      List(true, false)
      // add more test cases here (for each function)
    )
    assertEquals(applied, expected)

  // test("01. "):
  //   val inputs: List[] = List(
  //     ,
  //     // add more test cases here!
  //   )
  //   val expected: List[] = List(
  //     ,
  //     // add more test cases here!
  //   )
  //   assertEquals(inputs map , expected)
  // }

  // test("01. "):
  //   val inputs: List[] = List(
  //     ,
  //     // add more test cases here!
  //   )
  //   val expected: List[] = List(
  //     ,
  //     // add more test cases here!
  //   )
  //   assertEquals(inputs map , expected)
  // }

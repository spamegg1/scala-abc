package A.w3

class HW3ChallengeSuite extends munit.FunSuite:
  import Pattern.*, Value.*, Typ.*

  /* test input data for typecheckPatterns() */
  val inp01: (List[Triple], List[Pattern]) = (Nil, Nil)
  val inp02: (List[Triple], List[Pattern]) = (List(("a", "a", Anything)), Nil)
  val inp03: (List[Triple], List[Pattern]) = (Nil, List(Wildcard))
  val inp04: (List[Triple], List[Pattern]) =
    (Nil, List(ConstP(10), Variable("a")))
  val inp05: (List[Triple], List[Pattern]) = (
    Nil,
    List(
      TupleP(List(Variable("a"), ConstP(10), Wildcard)),
      TupleP(List(Variable("b"), Wildcard, ConstP(11))),
      Wildcard
    )
  )
  val inp06: (List[Triple], List[Pattern]) = (
    Nil,
    List(
      TupleP(List(Wildcard, Wildcard)),
      TupleP(List(Wildcard, TupleP(List(Wildcard, Wildcard))))
    )
  )
  val inp07: (List[Triple], List[Pattern]) = (
    Nil,
    List(
      TupleP(
        List(
          TupleP(List(TupleP(List(Wildcard)))),
          TupleP(List(TupleP(List(Wildcard)), Wildcard))
        )
      ),
      TupleP(
        List(
          TupleP(List(Wildcard)),
          TupleP(List(Wildcard, TupleP(List(Wildcard))))
        )
      )
    )
  )
  /* if Constructor is given wrong argument, we return None */
  val inp08a: (List[Triple], List[Pattern]) = (
    List(
      ("Empty", "list", UnitT),
      ("List", "list", TupleT(List(Anything, DataType("list"))))
    ),
    List(
      ConstructorP("empty", UnitP),
      ConstructorP(
        "List",
        TupleP(List(ConstP(10), ConstructorP("Empty", UnitP)))
      ),
      Wildcard
    )
  )
  /* here UnitT does not match ConstP(10) so we return None. */
  val inp08b: (List[Triple], List[Pattern]) = (
    List(
      ("Empty", "list", UnitT),
      ("List", "list", TupleT(List(UnitT, DataType("list"))))
    ),
    List(
      ConstructorP("Empty", UnitP),
      ConstructorP(
        "List",
        TupleP(List(ConstP(10), ConstructorP("Empty", UnitP)))
      ),
      Wildcard
    )
  )
  val inp09a: (List[Triple], List[Pattern]) = (
    List(
      ("Red", "color", UnitT),
      ("Green", "color", UnitT),
      ("Blue", "color", UnitT)
    ),
    List(ConstructorP("Red", UnitP), Wildcard)
  )
  val inp09b: (List[Triple], List[Pattern]) = (
    List(
      ("Red", "color", UnitT),
      ("Green", "color", UnitT),
      ("Blue", "color", UnitT)
    ),
    List(ConstructorP("Red", Variable("x")))
  )
  val inp09c: (List[Triple], List[Pattern]) = (
    List(
      ("Red", "color", UnitT),
      ("Green", "color", UnitT),
      ("Blue", "color", UnitT)
    ),
    List(ConstructorP("Red", Wildcard))
  )
  val inp09d: (List[Triple], List[Pattern]) = (
    List(
      ("Red", "color", UnitT),
      ("Green", "color", UnitT),
      ("Blue", "color", UnitT)
    ),
    List(ConstructorP("Red", ConstP(10)))
  )
  val inp10a: (List[Triple], List[Pattern]) = (
    List(
      ("Sedan", "auto", DataType("color")),
      ("Truck", "auto", TupleT(List(IntT, DataType("color")))),
      ("SUV", "auto", UnitT)
    ),
    List(
      ConstructorP("Sedan", Variable("a")),
      ConstructorP("Truck", TupleP(List(Variable("b"), Wildcard))),
      Wildcard
    )
  )
  /* Here IntT does not match DataType("color") so we return None */
  val inp10b: (List[Triple], List[Pattern]) = (
    List(
      ("Sedan", "auto", DataType("color")),
      ("Truck", "auto", TupleT(List(IntT, DataType("color")))),
      ("SUV", "auto", UnitT)
    ),
    List(
      ConstructorP("Sedan", ConstP(10)),
      ConstructorP("Truck", TupleP(List(Variable("b"), Wildcard))),
      Wildcard
    )
  )
  val inp10c: (List[Triple], List[Pattern]) = (
    List(
      ("Sedan", "auto", DataType("color")),
      ("Truck", "auto", TupleT(List(IntT, DataType("color")))),
      ("SUV", "auto", UnitT)
    ),
    List(
      ConstructorP("Sedan", Variable("a")),
      ConstructorP("Truck", TupleP(List(Variable("b"), ConstP(10)))),
      Wildcard
    )
  )
  val inp10d: (List[Triple], List[Pattern]) = (
    List(
      ("Sedan", "auto", DataType("color")),
      ("Truck", "auto", TupleT(List(IntT, DataType("color")))),
      ("SUV", "auto", UnitT)
    ),
    List(
      ConstructorP("Sedan", Variable("a")),
      ConstructorP("Truck", TupleP(List(ConstP(10), UnitP))),
      Wildcard
    )
  )
  val inp11: (List[Triple], List[Pattern]) = (
    List(
      ("Empty", "list", UnitT),
      ("List", "list", TupleT(List(Anything, DataType("list"))))
    ),
    List(
      ConstructorP("Empty", UnitP),
      ConstructorP(
        "List",
        TupleP(List(ConstP(10), ConstructorP("Empty", UnitP)))
      ),
      Wildcard
    )
  )
  val inp12: (List[Triple], List[Pattern]) = (
    List(
      ("Empty", "list", UnitT),
      ("List", "list", TupleT(List(Anything, DataType("list"))))
    ),
    List(
      ConstructorP("Empty", UnitP),
      ConstructorP("List", TupleP(List(Variable("k"), Wildcard)))
    )
  )
  val inp13: (List[Triple], List[Pattern]) = (
    List(
      ("Empty", "list", UnitT),
      ("List", "list", TupleT(List(Anything, DataType("list"))))
    ),
    List(
      ConstructorP("Empty", UnitP),
      ConstructorP(
        "List",
        TupleP(List(ConstructorP("Sedan", Variable("c")), Wildcard))
      )
    )
  )
  val inp14: (List[Triple], List[Pattern]) = (
    List(
      ("Empty", "list", UnitT),
      ("List", "list", TupleT(List(Anything, DataType("list"))))
    ),
    List(
      ConstructorP("Empty", UnitP),
      ConstructorP("List", TupleP(List(ConstructorP("Sedan", Variable("c")))))
    )
  )
  val inp15: (List[Triple], List[Pattern]) = (
    Nil,
    List(
      TupleP(List(Variable("x"), Variable("y"))),
      TupleP(List(Wildcard, Wildcard))
    )
  )

  val inputs: List[(List[Triple], List[Pattern])] = List(
    inp01,
    inp02,
    inp03,
    inp04,
    inp05,
    inp06,
    inp07,
    inp08a,
    inp08b,
    inp09a,
    inp09b,
    inp09c,
    inp09d,
    inp10a,
    inp10b,
    inp10c,
    inp10d,
    inp11,
    inp12,
    inp13,
    inp14,
    inp15
  )

  /* test results for typecheckPatterns */
  val expected: List[Option[Typ]] = List(
    None,
    None,
    Some(Anything),
    Some(IntT),
    Some(TupleT(List(Anything, IntT, IntT))),
    Some(TupleT(List(Anything, TupleT(List(Anything, Anything))))),
    Some(
      TupleT(
        List(
          TupleT(List(TupleT(List(Anything)))),
          TupleT(List(TupleT(List(Anything)), TupleT(List(Anything))))
        )
      )
    ),
    None,
    None,
    Some(DataType("color")),
    Some(DataType("color")),
    Some(DataType("color")),
    None,
    Some(DataType("auto")),
    None,
    None,
    None,
    Some(DataType("list")),
    Some(DataType("list")),
    None,
    None,
    Some(TupleT(List(Anything, Anything)))
  )

  test("01-15. typecheckPatterns") {
    assertEquals(inputs map typecheckPatterns, expected)
  }

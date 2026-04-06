package curriculum
package plb
package w5

/*   MUPL expressions: */
enum MUPL:
  case Var(varName: String)
  case Integer(int: Int) /* also a MUPL value */
  case Add(e1: MUPL, e2: MUPL)
  case IfGreater(e1: MUPL, e2: MUPL, e3: MUPL, e4: MUPL)
  case Fun(nameOpt: Option[String], arg: String, body: MUPL)
  case Call(closure: MUPL, arg: MUPL)
  case Mlet(varName: String, exp: MUPL, body: MUPL)
  case Apair(e1: MUPL, e2: MUPL) /* also a MUPL value (if e1, e2 are values) */
  case First(exp: MUPL)
  case Second(exp: MUPL)
  case Aunit /* also a MUPL value */
  case IsAunit(exp: MUPL)

  /* not used directly in MUPL source code */
  case Closure(env: Map[String, MUPL], arg: MUPL) /* also a MUPL value */
export MUPL.*

/*  Companion object of the enum */
object MUPL:
  /*
      Scala list -> MUPL list
      produces analogous MUPL list with same elts in same order
   */
  def toMUPLlist(lst: List[MUPL]): MUPL =
    if lst.isEmpty then Aunit else Apair(lst.head, toMUPLlist(lst.tail))

  /*
      MUPL list -> Scala list
      produces analogous Racket list with same elts in same order
   */
  def toScalaList(lst: MUPL): List[MUPL] = lst match
    case Aunit         => Nil
    case Apair(e1, e2) => e1 :: toScalaList(e2)
    case _ =>
      throw new IllegalArgumentException(
        "${lst} is not Aunit or Apair, can't be a MUPL list"
      )

  /*  Problem 2 */
  def envLookup(env: Map[String, MUPL], varName: String): MUPL =
    env.get(varName) match
      case Some(muplVal) => muplVal
      case None => throw new NoSuchElementException("unbound variable during evaluation")

  def evalUnderEnv(exp: MUPL, env: Map[String, MUPL]): MUPL = exp match
    case Fun(_, _, _) => Closure(env, exp)
    case Var(varName) => envLookup(env, varName)
    case Add(e1, e2) =>
      val (v1, v2) = (evalUnderEnv(e1, env), evalUnderEnv(e2, env))
      (v1, v2) match
        case (Integer(i1), Integer(i2)) => Integer(i1 + i2)
        case _ =>
          throw new IllegalArgumentException("MUPL addition applied to non-number")
    case IfGreater(e1, e2, e3, e4) =>
      val (v1, v2) = (evalUnderEnv(e1, env), evalUnderEnv(e2, env))
      (v1, v2) match
        case (Integer(i1), Integer(i2)) =>
          if i1 > i2 then evalUnderEnv(e3, env) else evalUnderEnv(e4, env)
        case _ =>
          throw new IllegalArgumentException("MUPL IfGreater applied to non-number")
    case Mlet(varName, e, body) =>
      val v = evalUnderEnv(e, env)
      val newEnv = env + (varName -> v)
      evalUnderEnv(body, newEnv)
    case Apair(e1, e2) => Apair(evalUnderEnv(e1, env), evalUnderEnv(e2, env))
    case First(e) =>
      evalUnderEnv(e, env) match
        case Apair(e1, e2) => e1
        case _ => throw new IllegalArgumentException("MUPL First applied to non-pair")
    case Second(e) =>
      evalUnderEnv(e, env) match
        case Apair(e1, e2) => e2
        case _ => throw new IllegalArgumentException("MUPL Second applied to non-pair")
    case IsAunit(e) =>
      evalUnderEnv(e, env) match
        case Aunit => Integer(1)
        case _     => Integer(0)
    case Call(cl, ar) =>
      val (closure, argument) = (evalUnderEnv(cl, env), evalUnderEnv(ar, env))
      closure match
        case Closure(env1, Fun(nameOpt, arg, body)) =>
          val env2 = env1 + (arg -> argument)
          val newEnv = nameOpt match
            case Some(funName) => env2 + (funName -> closure)
            case None          => env2
          evalUnderEnv(body, newEnv)
        case _ =>
          throw new IllegalArgumentException(
            "MUPL Call's first argument is not a Closure"
          )
    /* Aunit, Integer, Closure are already MUPL values by themselves */
    case _ => exp

  def evalExp(exp: MUPL): MUPL = evalUnderEnv(exp, Map[String, MUPL]())

  /*  Problem 3 */
  def ifAunit(e1: MUPL, e2: MUPL, e3: MUPL): MUPL =
    IfGreater(IsAunit(e1), Integer(0), e2, e3)

  def mLet(lst: List[(String, MUPL)], exp: MUPL): MUPL = lst match
    case Nil                    => exp
    case (str, muplVal) :: tail => Mlet(str, muplVal, mLet(tail, exp))

  /* assume none of the arguments use MUPL var names "spam" or "egg" */
  def ifEq(e1: MUPL, e2: MUPL, e3: MUPL, e4: MUPL): MUPL =
    Mlet(
      "spam",
      e1,
      Mlet(
        "egg",
        e2,
        IfGreater(
          Var("egg"),
          Var("spam"),
          e4,
          IfGreater(Var("spam"), Var("egg"), e4, e3)
        )
      )
    )

  /*  Problem 4 */
  val muplMap: MUPL = Fun(
    Some("muplMap"),
    "muplFun",
    Fun(
      Some("helper"),
      "muplList",
      ifAunit(
        Var("muplList"),
        Aunit,
        Apair(
          Call(Var("muplFun"), First(Var("muplList"))),
          Call(Var("helper"), Second(Var("muplList")))
        )
      )
    )
  )

  val muplMapAddN: MUPL = Mlet(
    "map",
    muplMap,
    Fun(
      Some("muplMapAddN"),
      "i",
      Fun(
        None,
        "muplList",
        Call(
          Call(Var("map"), Fun(None, "x", Add(Var("x"), Var("i")))),
          Var("muplList")
        )
      )
    )
  )

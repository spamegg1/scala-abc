package curriculum
package pla
package w3

class NoAnswer extends Exception

enum Pattern:
  case Wildcard
  case Variable(s: String)
  case UnitP
  case ConstP(i: Int)
  case TupleP(pl: List[Pattern])
  case ConstructorP(s: String, p: Pattern)
export Pattern.*

enum Value:
  case Const(i: Int)
  case Unit
  case Tuple(vl: List[Value])
  case Constructor(s: String, v: Value)
export Value.*

def g(f1: Unit => Int)(f2: String => Int)(p: Pattern): Int =
  val r: Pattern => Int = g(f1)(f2)
  p match
    case Wildcard           => f1(())
    case Variable(s)        => f2(s)
    case TupleP(ps)         => ps.foldLeft(0)((i, p) => r(p) + i)
    case ConstructorP(_, p) => r(p)
    case _                  => 0

/*  returns list of strings that start with an uppercase letter
 *  assumes all strings in list have at least 1 character  */
val onlyCapitals: List[String] => List[String] = _ filter (s => s(0).isUpper)

/*  returns longest string in list, "" if list is empty,
 *  earliest in case of tie  */
val longestString1: List[String] => String =
  _.foldLeft("")((x, y) => if x.size > y.size then x else y)

/* returns longest string in list, "" if list is empty, latest in case of tie */
val longestString2: List[String] => String =
  _.foldLeft("")((x, y) => if y.size < x.size then x else y)

/*  if function passed in behaves like >, this acts like longest_string1  */
def longestStringHelper(f: (Int, Int) => Boolean): List[String] => String =
  _.foldLeft("")((x, y) => if f(x.size, y.size) then x else y)

/*  returns longest string in list, "" if empty, earliest in case of tie  */
val longestString3: List[String] => String = longestStringHelper((x, y) => x > y)

/* returns longest string in list, "" if list is empty, latest in case of tie */
val longestString4: List[String] => String = longestStringHelper((x, y) => y < x)

/*  returns longest string in list that begins with uppercase,
 *  "" if there are no such strings
 *  returns earliest longest string in case of tie
 *  assumes all strings in list have at least 1 character  */
val longestCapitalized: List[String] => String = longestString1 compose onlyCapitals

/*  returns string in reverse  */
val revString: String => String = _.reverse

/*  applies f to list elts until a Some(v) results. Else throws NoAnswer. */
def firstAnswer[T, S](f: T => Option[S])(lst: List[T]): S = lst match
  case Nil => throw new NoAnswer
  case t :: ts =>
    f(t) match
      case None    => firstAnswer(f)(ts)
      case Some(s) => s

def allAnswers[T, S](f: T => Option[List[S]])(lst: List[T]): Option[List[S]] =
  lst match
    case Nil => Some(Nil)
    case t :: ts =>
      (f(t), allAnswers(f)(ts)) match
        case (_, None)            => None
        case (None, _)            => None
        case (Some(s1), Some(s2)) => Some(s1 ::: s2)

val countWildcards: Pattern => Int = g(_ => 1)(_ => 0)

val countWildAndVariableLengths: Pattern => Int = g(_ => 1)(_.size)

def countSomeVar(s: String, p: Pattern): Int = g(_ => 0)(x => if x == s then 1 else 0)(p)

def checkPat(p: Pattern): Boolean =
  /* returns list of strings occurring in variables in pattern */
  def getVarStrings(q: Pattern): List[String] = q match
    case Wildcard      => Nil
    case Variable(str) => List(str)
    case UnitP         => Nil
    case ConstP(i)     => Nil
    case TupleP(plist) =>
      plist.foldLeft(List[String]())((vs, pat) => getVarStrings(pat) ::: vs)
    case ConstructorP(str, pat) => getVarStrings(pat)

  /* returns true if there are reps, false if no reps */
  def checkStringReps(sl: List[String]): Boolean = sl match
    case Nil       => false
    case s :: rest => rest exists (s == _)

  !checkStringReps(getVarStrings(p))

def patMatch(vp: (Value, Pattern)): Option[List[(String, Value)]] =
  val (v, p) = vp
  (v, p) match
    case (_, Wildcard)         => Some(Nil)
    case (_, Variable(str))    => Some(List((str, v)))
    case (Unit, UnitP)         => Some(Nil)
    case (Const(i), ConstP(j)) => if i == j then Some(Nil) else None
    case (Tuple(vs), TupleP(ps)) =>
      if vs.length == ps.length then allAnswers(patMatch)(vs.zip(ps)) else None
    case (Constructor(s1, valu), ConstructorP(s2, pat)) =>
      if s1 == s2 then patMatch(valu, pat) else None
    case (_, _) => None

def firstMatch(v: Value)(ps: List[Pattern]): Option[List[(String, Value)]] =
  try Some(firstAnswer((p: Pattern) => patMatch((v, p)))(ps))
  catch case _: NoAnswer => None

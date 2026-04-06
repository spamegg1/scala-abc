package curriculum
package pla
package w3

// enum Pattern:
//   case Wildcard
//   case Variable(s: String)
//   case UnitP
//   case ConstP(i: Int)
//   case TupleP(pl: List[Pattern])
//   case ConstructorP(s: String, p: Pattern)

// enum Value:
//   case Const(i: Int)
//   case Unit
//   case Tuple(vl: List[Value])
//   case Constructor(s: String, v: Value)

enum Typ:
  case Anything
  case UnitT
  case IntT
  case TupleT(tl: List[Typ])
  case DataType(s: String)
export Typ.*

type Triple = (String, String, Typ)

/*  Checks if two Typs have a common Type.  */
def compatible(s: Typ, t: Typ): Boolean = (s, t) match
  case (Anything, _)                => true
  case (_, Anything)                => true
  case (UnitT, UnitT)               => true
  case (IntT, IntT)                 => true
  case (DataType(s1), DataType(s2)) => s1 == s2
  case (TupleT(lst1), TupleT(lst2)) =>
    lst1.length == lst2.length && ((lst1 zip lst2) forall compatible)
  case _ => false

/*  Converts a single pattern to its Typ. Uses compatible. */
def p2t(triples: List[Triple])(p: Pattern): Option[Typ] = p match
  case Wildcard    => Some(Anything)
  case Variable(_) => Some(Anything)
  case UnitP       => Some(UnitT)
  case ConstP(_)   => Some(IntT)
  case TupleP(lst) =>
    val converted = lst map p2t(triples)
    if converted forall (_.isDefined) then Some(TupleT(converted map (_.get))) else None
  case ConstructorP(str, pat) =>
    val lookup = triples find (_._1 == str)
    (lookup, p2t(triples)(pat)) match
      case (None, _) => None // could not find str in metadata
      case (_, None) => None // pat fails to convert
      case (Some(_, s2, t1), Some(t2)) =>
        if compatible(t1, t2) then Some(DataType(s2))
        else None // pat incompatible with metadata

/* Merges two Typs to their common Typ. Assumes the Typs are compatible. */
def coalesce(s: Typ, t: Typ): Typ = (s, t) match
  case (Anything, x)                => x
  case (x, Anything)                => x
  case (UnitT, UnitT)               => UnitT
  case (IntT, IntT)                 => IntT
  case (DataType(s1), DataType(s2)) => DataType(s1) // assume s1 = s2
  case (TupleT(lst1), TupleT(lst2)) => TupleT((lst1 zip lst2) map coalesce)
  case _ => s // to satisfy exhaustivity check. This case shouldn't happen

/* Merges two Typ Options. Checks if they are compatible. */
def merge(s: Option[Typ], t: Option[Typ]): Option[Typ] =
  (s, t) match
    case (None, _) => None
    case (_, None) => None
    case (Some(s1), Some(t1)) =>
      if compatible(s1, t1) then Some(coalesce(s1, t1)) else None

/* Assumes the list of patterns is not empty. */
def typecheckPatterns(ts: List[Triple], pats: List[Pattern]): Option[Typ] =
  pats map p2t(ts) match // convert all pats to Typs
    case t :: Nil => t
    case t :: ts  => ts.foldLeft(t)(merge)
    case _        => None // to satisfy exhaustivity. This case should not happen

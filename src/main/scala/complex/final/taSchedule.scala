package complex.finalExam

case class Slot(i: Int)
type Slots = List[Slot]

case class TA(name: String, maxHours: Int, slots: Slots)
type TAs = List[TA]

type Schedule = Map[Slot, TA]
type Schedules = List[Schedule]

def taGoodForSlot(schedule: Schedule, slot: Slot, ta: TA): Boolean =
  ta.slots.contains(slot) && schedule.values.count(_ == ta) < ta.maxHours

@annotation.tailrec
def solver(tas: TAs, slots: Slots, schedules: Schedules): Schedules =
  slots match
    case Nil => schedules
    case slot :: rest =>
      val newSchedules =
        for
          schedule <- schedules
          ta <- tas
          if taGoodForSlot(schedule, slot, ta)
        yield schedule + (slot -> ta)
      solver(tas, rest, newSchedules)

def solve(tas: TAs, slots: Slots): Option[Schedule] =
  if tas.isEmpty || slots.isEmpty
  then None
  else solver(tas, slots, List(Map())).headOption

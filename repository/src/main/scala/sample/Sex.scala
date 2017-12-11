package sample

import domala.Holder

@Holder
sealed abstract class Sex(val value: String)
object Sex {
  case object Male extends Sex("M")
  case object Female extends Sex("F")
  case object Other extends Sex("O")
}

package sample

import domala.Holder

@Holder
sealed abstract class Sex(val value: String) 
object Sex {
  object Male extends Sex("M")
  object Female extends Sex("F")
  object Other extends Sex("O")
}

package sample

case class Age(value: Int) extends AnyVal {
  def grow: Age = Age(value + 1)
}

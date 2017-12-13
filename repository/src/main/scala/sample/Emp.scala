package sample

import domala._

@Entity
case class Emp(
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(sequence = "emp_id_seq")
  id: ID[Emp],
  name: Name,
  age: Age,
  @Version
  version: Int) {
  def growOld: Emp =
    this.copy(age = age.grow)
}

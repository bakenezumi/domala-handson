package sample

import domala._
import domala.jdbc.Result

@Dao
trait EmpDao {

  def selectById(id: ID[Emp]): Option[Emp] = selectByIds(Seq(id)) {
    _.toStream.headOption
  }

  @Select("select * from emp")
  def selectAll: Seq[Emp] // scala.Seqが使える

  @Insert
  def insert(emp: Emp): Result[Emp]

  @Update
  def update(emp: Emp): Result[Emp]

  @Delete
  def delete(emp: Emp): Result[Emp]

  @Script("""
  create table emp(
      id int not null primary key,
      name varchar(20),
      age int,
      sex char(1),
      version int not null
  );

  create sequence emp_id_seq start with 1;
  """)
  def create(): Unit

  @Select(
    "select * from emp where id in /* id */()",
    strategy = SelectType.ITERATOR
  )
  def selectByIds[R](id: Iterable[ID[Emp]])(mapper: Iterator[Emp] => R): R

}

package sample

import domala._
import domala.jdbc.Config

object SampleApp extends App {
  private implicit lazy val config: Config = AppConfig
  private lazy val dao: EmpDao = EmpDao.impl

  Required {
    // create table & insert
    dao.create()
    val inserted = Seq(
      Emp(ID(-1), "scott", 10, -1),
      Emp(ID(-1), "allen", 20, -1)
    ).map(dao.insert)
    println(inserted)

    // idが2のEmpのageを +1 にupdate
    val updated = // Optionなので型推論が働く
      dao
        .selectById(ID(2))
        .map(_.growOld)
        .map(dao.update)
    println(updated)

    dao.selectAll.foreach(println)
    // =>
    //   Emp(ID(1),scott,10,1)
    //   Emp(ID(2),allen,21,2)
  }
}
package io.getquill.mytest

import MirrorType._

object ContextUse {
  def main(args: Array[String]): Unit = {
    val ctx = new MyContext();
    import ctx._
    val tup = ("foo", 1)
    println( tup.mirrorType )
  }
}


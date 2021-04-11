package io.getquill.mytest

import scala.quoted._

object PrintMac {
    inline def apply[T](inline any: T): T = ${ printMacImpl('any) }
    def printMacImpl[T: Type](anyRaw: Expr[T])(using Quotes): Expr[T] = {
      import quotes.reflect._
      val any = anyRaw.asTerm.underlyingArgument.asExpr
      println(Printer.TreeShortCode.show(any.asTerm))
      anyRaw
    }
}

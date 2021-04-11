package io.getquill.mytest

import scala.quoted._
import scala.deriving._
import scala.compiletime.{erasedValue, constValue, summonFrom, summonInline}

class MyContext {
  implicit inline def autoMirrorType[T]: MirrorType[T] = MirrorType.generic
}

trait MirrorType[T] {
  def mirrorType: String
}

object MirrorType {
  class Container[T]

  inline def decode[T]: String =
    //PrintMac { // uncomment to have a look at the code
    summonFrom {
      case ev: Mirror.ProductOf[T] =>
        s"Product-${new Container[ev.MirroredElemLabels]}"
      case m: Mirror.SumOf[T] =>
        "Sum"
    }
    //}

  inline def generic[T]: MirrorType[T] = 
    new MirrorType[T] {
      def mirrorType: String = decode[T]
    }

  extension[T](inline value: T)
    inline def mirrorType = summonFrom {
      case mt: MirrorType[T] => mt.mirrorType
      case _ => "mirror not found"
    }
}

package com.weather.sun

import io.protoless._
import io.protoless.tag._
import io.protoless.messages._
import io.protoless.generic.auto._
import io.protoless.messages.decoders.AutoDecoder
import io.protoless.messages.encoders.AutoEncoder

package object grpc {


//
//  val p = SampleCaseClass("John", "Doe", 28.asInstanceOf[Int @@ Unsigned] , Seq("Paris", "London", "New York"))
//  // p: Person = Right(Person(John, Doe, Some(28), Seq(Paris, London, New York)
//
//  val bytes:Array[Byte] = AutoEncoder[SampleCaseClass].encodeAsBytes(p) // or p.asProtobufBytes
//  // bytes: Array[Byte] = Array(10, 4, 74, 111, 104, 110, 18, ...)
//
//  lazy val decoded = AutoDecoder[SampleCaseClass].decode(bytes) // or bytes.as[Person]
//  // res1: Either[DecodingFailure, Person] = Right(Person(John, Doe, Some(28), Seq(Paris, London, New York)))
}

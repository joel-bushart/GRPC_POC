package com.weather.sun.grpc

import io.protoless.tag._


case class SampleCaseClass(firstname: String, lastname: String, age: Int @@ Unsigned, locations: Seq[String]) {

}


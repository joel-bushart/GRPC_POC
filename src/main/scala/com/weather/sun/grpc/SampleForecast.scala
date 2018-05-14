package com.weather.sun.grpc

import io.protoless.tag._


case class SampleForecast(temprature: Float, humidity: Int @@ Unsigned, phrase: String) {

}


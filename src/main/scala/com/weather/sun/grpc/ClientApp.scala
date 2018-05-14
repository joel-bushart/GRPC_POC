package com.weather.sun.grpc


import SampleProtocol.{SampleReply, SampleRequest, SampleServiceGrpc}
import SampleProtocol.SampleServiceGrpc.SampleService
import io.grpc.ManagedChannelBuilder
import io.protoless.tag.{@@, Unsigned}

import scala.concurrent.Future


object ClientApp extends App{
    val channel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext(true).build
    val request = new SampleRequest("John", "Doe", 28.asInstanceOf[Int @@ Unsigned] , Seq("Paris", "London", "New York"))

    val blockingStub = SampleServiceGrpc.blockingStub(channel)
    val reply: SampleReply = blockingStub.sampleServer(request)
    println(reply)

}

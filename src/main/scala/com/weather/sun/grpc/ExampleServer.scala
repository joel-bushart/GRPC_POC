package com.weather.sun.grpc

import java.util.logging.Logger

import SampleProtocol.SampleServiceGrpc
import io.grpc.{Server, ServerBuilder}

import scala.concurrent.{ExecutionContext, Future}

/**
  * [[https://github.com/grpc/grpc-java/blob/v0.15.0/examples/src/main/java/io/grpc/examples/helloworld/HelloWorldServer.java]]
  */
object ExampleServer {
  private val logger = Logger.getLogger(classOf[ExampleServer].getName)

  def main(args: Array[String]): Unit = {
    val server = new ExampleServer(ExecutionContext.global)
    server.start()
    server.blockUntilShutdown()
  }

  private val port = 50051
}

class ExampleServer(executionContext: ExecutionContext) extends {
  self =>
  private[this] var server: Server = null

  private def start(): Unit = {
    server = ServerBuilder.forPort(ExampleServer.port)
      .addService(SampleServiceGrpc.bindService(new SampleServiceImpl(executionContext), executionContext)).build.start
    ExampleServer.logger.info("Server started, listening on " +  ExampleServer.port)
    sys.addShutdownHook {
      System.err.println("*** shutting down gRPC server since JVM is shutting down")
      self.stop()
      System.err.println("*** server shut down")
    }
  }

  private def stop(): Unit = {
    if (server != null) {
      server.shutdown()
    }
  }

  private def blockUntilShutdown(): Unit = {
    if (server != null) {
      server.awaitTermination()
    }
  }

  private class SampleServiceImpl(executionContext: ExecutionContext) extends SampleServiceGrpc.SampleService {
    private implicit val exContext = executionContext
    override def serviceCompanion = SampleServiceGrpc.SampleService

    override def sampleServer(request: SampleProtocol.SampleRequest): scala.concurrent.Future[SampleProtocol.SampleReply] = {
      Future(new SampleProtocol.SampleReply(request.firstname+"1", request.lastname+"1", request.age+1, request.locations.map(_+"2")))
    }
  }

}
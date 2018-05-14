name := "GRPC_POC"

version := "0.1"

scalaVersion := "2.12.4-bin-typelevel-4"

inThisBuild(Seq(
  scalaOrganization := "org.typelevel",
  scalaVersion := "2.12.4-bin-typelevel-4"
))

PB.targets in Compile := Seq(
  scalapb.gen() -> (sourceManaged in Compile).value
)

credentials += Credentials(Path.userHome / ".sbt" / ".credentials")

scalacOptions += "-Ypartial-unification"

resolvers := Seq(
  "Artifactory Realm " at "https://repo.artifacts.weather.com/sun-release-virtual",
  Resolver.typesafeRepo("releases"),
  Resolver.sonatypeRepo("releases"),
  Resolver.mavenCentral,
  Resolver.bintrayRepo("julien-lafont", "maven"),
  Resolver.defaultLocal
)

libraryDependencies ++= Seq(
  "com.google.protobuf" % "protobuf-java" % "3.5.1" ,
  "com.google.protobuf" % "protobuf-java-util" % "3.5.1" ,
  "io.protoless" %% "protoless-core" % "0.0.8-M1",
  "io.protoless" %% "protoless-generic" % "0.0.8-M1",
  "com.chuusai" %% "shapeless" % "2.3.3",
  "org.typelevel" %% "cats-core" % "1.0.1",

  "io.grpc" % "grpc-netty" % scalapb.compiler.Version.grpcJavaVersion,
  "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % scalapb.compiler.Version.scalapbVersion,

  //testing
  "org.typelevel" %% "cats-testkit" % "1.0.1"
).map(_.withSources().withJavadoc())
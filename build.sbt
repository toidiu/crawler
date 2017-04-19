name := "web-scrape"

version := "0.0.1"

lazy val root = (project in file("."))

scalaVersion := "2.12.2"

resolvers ++= Seq(
  "Typesafe Releases" at "https://repo.typesafe.com/typesafe/releases/"
)

libraryDependencies ++= {
  Seq("com.typesafe.play" %% "play-ahc-ws-standalone" % "1.0.0-M6",
    "org.jsoup" % "jsoup" % "1.10.2",
    "commons-validator" % "commons-validator" % "1.5+")
}


package PetStore

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class PetStoreSimulation extends Simulation {
  // loading test dat
  val feeder = csv("pets.csv").random // 1, 2

  object Delete {
    val delete = exec(http("Home")
      .get("/"))
      .feed(feeder)
      .pause(1)
      .exec(http("PostPet")
        .post("/")
        .formParam("name", "${name}")
        .formParam("status", "${status}")
        .check(
          jsonPath("$.id").saveAs("myresponseId")))
      .exec(http("DeletePet")
        .delete("/${myresponseId}"))
  }

  object Get {

    val get = exec(http("GetPets")
      .get("/"))
  }

  object Post {


    val post = exec(http("Home")
      .get("/"))
      .pause(1)
      .feed(feeder)
      .exec(http("PostPet")
        .post("/")
        .formParam("name", "${name}")
        .formParam("status", "${status}"))
  }

  val httpProtocol = http
    .baseUrl("http://localhost:3000/api/pets")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")

  // set scenarios
  val scn = scenario("Scenario Name").exec(Post.post, Get.get, Delete.delete)
  // rampup 5 users over 5 seconds
  setUp(scn.inject(rampUsers(5) during (5 seconds)).protocols(httpProtocol))
  // uncomment below if you would like to have 5 users at once
  //setUp(scn.inject(atOnceUsers(5)).protocols(httpProtocol))
}
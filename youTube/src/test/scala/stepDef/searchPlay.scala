package stepDef


import support.support


class searchPlay extends support {


  feature("The user can search on youtube and select the first video to watch") {

    info("As a youtube user")
    info("I want to be able to search by phrase")
    info("So that I can find videos related to provided phrase")
    info("Once list of video is shown I want to choose the first video")
    info("I want to watch the video and quit after the video finishes")



    scenario("YouTube search and play") {

      Given("I am on the youtube homepage")

      When("I enter search phrase google co uk")
      //HomePage.enterPhrase("simon d comfortable")
      // HomePage.enterPhrase("TOASTING WITH GLASS")
      HomePage.enterPhrase("the fastest wifi i have tested")

      And("I click search")
      HomePage.clickSearch()
      ResultsPage.waitForResult(driver)

      And("I click on the first video")
      ResultsPage.clickFirstVideo()
      VideoPlayerPage.waitForVideo(driver)

      And("I want to check that the video clicked is the one that is playing")
      VideoPlayerPage.checkVideoTitle(ResultsPage)

      And("I turn off the auto play")
      VideoPlayerPage.turnOffAutoPlay()

      Then("I want to quit after the video finishes")
      VideoPlayerPage.finishQuit(driver)

    }
  }
}


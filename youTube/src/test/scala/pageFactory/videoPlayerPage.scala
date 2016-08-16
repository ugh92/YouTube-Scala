package pageFactory

import org.openqa.selenium.{By, WebDriver, WebElement}
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.{ExpectedConditions, WebDriverWait}
import org.scalatest.selenium.WebBrowser.click


class videoPlayerPage {

  @FindBy(xpath = ".//*[@id='autoplay-checkbox-label']")
  var Checkbox: WebElement = _
  @FindBy(xpath = ".//*[@id='autoplay-checkbox-label']/span[1]")
  var Checked: WebElement = _

  @FindBy(className = "ytp-time-current")
  var CurrentTime: WebElement = _
  @FindBy(xpath = ".//*[@class='ytp-time-display notranslate']/span[3]")
  var DurationTime: WebElement = _

  @FindBy(xpath = ".//*[@class='video-ads']/div[1]")
  var AdCheck: WebElement = _

  @FindBy(className = "videoAdUiSkipButton")
  var skipButton: WebElement = _

  /////////
  @FindBy(xpath = ".//*[@class='videoAdUiVisitAdvertiserLinkText']")
  var AdWithoutSkip: WebElement = _

  @FindBy(xpath = ".//*[@id='eow-title']")
  var VideoTitle: WebElement = _

  def waitForVideo(driver: WebDriver): Unit = {
    val wait: WebDriverWait = new WebDriverWait(driver, 10)
    wait.until(ExpectedConditions.visibilityOf(Checkbox))
  }

  def checkVideoTitle(ResultsPage: resultsPage) = {
    val videoTitle = VideoTitle.getText
    println(videoTitle)
    assert(ResultsPage.firstVideoTitle == videoTitle, "Video title doesn't equal searched video title.")
    println("Title of the First Video  : " + ResultsPage.firstVideoTitle + "\n" + "Title of the Video playing: " + videoTitle + "\n")
  }


  def turnOffAutoPlay() = {
    if (Checked.isDisplayed)
      click on Checkbox
    else
      println("Turn off autoplay: Autoplay already off" + "\n")
  }


  def adPlaying(driver: WebDriver) = {
    val wait: WebDriverWait = new WebDriverWait(driver, 10)
    wait.until(ExpectedConditions.visibilityOf(skipButton))
    if (skipButton.isDisplayed){
    click on skipButton
    println("Ad skipped" + "\n")
    waitForVideoStart(driver)
    }
      else wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@class='videoAdUiVisitAdvertiserLinkText']")))
  }


  def adNotPlaying(driver: WebDriver) = {
    val adClass = AdCheck.getAttribute("className")
    if (adClass == "video-ad-status-bar" )
      println("Ad is not playing" + "\n")
    waitForVideoStart(driver)
  }

  def waitForVideoStart(driver: WebDriver)={
    val wait: WebDriverWait = new WebDriverWait(driver, 10)
    wait.until(ExpectedConditions.textToBe(By.className("ytp-time-current"), "0:02"))
    println("Video is playing!!!" + "\n")
  }

  def waitTillFinish(CurrentTime: WebElement): Unit = {

    val duration = DurationTime.getText
    while (CurrentTime.getText != duration) {
      //println(CurrentTime.getText + "/" + duration) debug
    }

    println("Video has finished playing!!!")
    println("Current Time  : " + CurrentTime.getText + "\n" + "Duration Time : " + duration + "\n")

  }

  def finishQuit(driver: WebDriver) = {
    val adClass = AdCheck.getAttribute("className")
    if (adClass != "video-ad-status-bar") {
      println("Ad is Playing")
      adPlaying(driver)
      waitTillFinish(CurrentTime)
    }
    else
      adNotPlaying(driver)
    waitTillFinish(CurrentTime)


  }


}

package pageFactory



import org.openqa.selenium.{WebDriver, WebElement}
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.{ExpectedConditions, WebDriverWait}
import org.scalatest.selenium.WebBrowser.click


class resultsPage {

  @FindBy(xpath = ".//*[starts-with(@id,'item-section-')]/li[1]/div/div/div[1]/a/div/span/img")
  var LocateFirstVideo: WebElement = _

  @FindBy(xpath = ".//*[starts-with(@id,'item-section-')]/li[2]/div/div/div[1]/a/div/span/img")
  var LFVwithAd: WebElement = _

  @FindBy(xpath = ".//[starts-with(@id,'item-section-')]/li[1]/div/div[1]/div/div[1]/a/div/span/img")
  var forAd: WebElement = _

  @FindBy(xpath = ".//*[starts-with(@id,'item-section-')]/li[1]/div/div/div[2]/h3/a")
  var FirstVideoTitle: WebElement = _


  @FindBy(xpath = ".//*[starts-with(@id,'item-section-')]/li[2]/div/div/div[2]/h3/a")
  var FVTwithAd: WebElement = _

  var firstVideoTitle = ""

  def waitForResult(driver: WebDriver): Unit = {
    val wait: WebDriverWait = new WebDriverWait(driver, 10)
    wait.until(ExpectedConditions.or(
      ExpectedConditions.visibilityOf(LocateFirstVideo),
      ExpectedConditions.visibilityOf(LFVwithAd)))
  }

  /*def clickFirstVideo() = {
    if (LocateFirstVideo.isDisplayed)
      click on LocateFirstVideo

    else click on LFVwithAd
    */

  //////check keeps producing different result .isdisplayed???????
  def clickFirstVideo(): Unit = {
    if (LocateFirstVideo.isDisplayed) {
      firstVideoTitle = FirstVideoTitle.getText
      println(firstVideoTitle)
      click on LocateFirstVideo
    }
    else { firstVideoTitle = FVTwithAd.getText
      println(firstVideoTitle)
      click on LFVwithAd
    }

  }
}

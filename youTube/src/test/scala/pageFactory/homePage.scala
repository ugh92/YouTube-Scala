package pageFactory

import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.scalatest.selenium.WebBrowser.click


class homePage {


  @FindBy(xpath = ".//*[@id='masthead-search-term']")
  var SearchField: WebElement = _

  @FindBy(id = "search-btn")
  var SearchButton: WebElement = _


  def enterPhrase(searchVideo: String) {
    SearchField.sendKeys(searchVideo)

  }

  def clickSearch(): Unit = {
    click on SearchButton
  }

}

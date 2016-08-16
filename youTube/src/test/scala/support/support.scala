package support

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.safari.SafariDriver
import org.openqa.selenium.support.PageFactory
import org.scalatest._
import org.scalatest.selenium.WebBrowser
import pageFactory.{homePage, resultsPage, videoPlayerPage}



trait support extends FeatureSpec with GivenWhenThen with Matchers with WebBrowser with BeforeAndAfterAll with BeforeAndAfterEach {

  /* Firefox
  System.setProperty("webdriver.gecko.driver", "/Users/umargurung/Downloads/geckodriver")
  var capabilities: DesiredCapabilities = DesiredCapabilities.firefox()
  capabilities.setCapability("marionette", true)
  implicit lazy val driver: WebDriver = new FirefoxDriver(capabilities)
  */

  /* Chrome
  System.setProperty("webdriver.chrome.driver", "/Users/umargurung/Downloads/chromedriver")
  implicit  val driver: WebDriver = new ChromeDriver()
  */

  implicit lazy val driver: WebDriver = new SafariDriver()
  driver.manage().window().maximize()
  lazy val HomePage = PageFactory.initElements(driver,classOf[homePage])
  lazy val ResultsPage = PageFactory.initElements(driver,classOf[resultsPage])
  lazy val VideoPlayerPage = PageFactory.initElements(driver,classOf[videoPlayerPage])



  /////////Base Page Url

  def baseURL(driver: WebDriver): String = {
    driver.manage.deleteAllCookies()
    val URL = "https://www.youtube.com/"
    URL
  }
  /////////

  override def beforeEach() = {
    go to baseURL(driver)
  }

  override def afterAll() = {
    //quit()
  }
}
package amazon.pages;

import config.common.WebTestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import static amazon.pageElements.HomePageElements.*;
import static config.common.GlobalReUsableMethods.*;


public class HomePage extends WebTestBase {
    // Action Methods class for all type of business logic/ function/ actions purpose: Page Object class

    // Constructor of the Page Object class and pass driver from WebTestBase class
    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        //  WebTestBase.driver = driver;
    }


    //By searchBox = By.xpath(searchBoxWebElement);
    public void searchProductUsingValidProductName1() {
        //  driver.findElement(By.xpath(searchBoxWebElement)).sendKeys("Hand Sanitizer");
        //   driver.findElement(By.xpath(searchButtonWebElement)).click();
        //  driver.findElement(searchBox).sendKeys("Hand Sanitizer");

    }

    // Find by Annotation with How: Modern Approach
//    @FindBy(how = How.XPATH, using = searchBoxWebElement)
//    public WebElement searchBox1;
//    @FindBy(how = How.XPATH, using = searchButtonWebElement)
//    public WebElement searchButton1;

//    @FindBy(how = How.ID, using = searchButtonWebElement) public WebElement searchButton;
//    @FindBy(how = How.NAME, using = searchButtonWebElement) public WebElement searchButton;
//    @FindBy(how = How.CSS, using = searchButtonWebElement) public WebElement searchButton;
//    @FindBy(how = How.TAG_NAME, using = searchButtonWebElement) public WebElement searchButton;
//    @FindBy(how = How.LINK_TEXT, using = searchButtonWebElement) public WebElement searchButton;
//    @FindBy(how = How.PARTIAL_LINK_TEXT, using = searchButtonWebElement) public WebElement searchButton;
//    @FindBy(how = How.CLASS_NAME, using = searchButtonWebElement) public WebElement searchButton;


    // Find by Annotation with WithHow : Modern Approach
//    @FindBy(xpath = searchBoxWebElement)
//    public WebElement searchBox;
//    @FindBy(xpath = searchButtonWebElement)
//    public WebElement searchButton;


    /**
     * Account login
     */
    @FindBy(xpath = accountLogin)
    public WebElement accountLogIn;
    @FindBy(xpath= emailBox)
    public WebElement email;
    @FindBy(xpath = clickOnContinue)
    public WebElement clickContinue;
    @FindBy(xpath = passwordValue)
    public WebElement password;
    @FindBy(xpath=singInButton)
    public WebElement singIn;
    @FindBy(xpath=verifyLoginValid)
    public WebElement validLogin;

    /**
     * Amazon Logo link
     */
    @FindBy(xpath=amazonLogoHref)
    public WebElement amazonLogo;

//    // Action Method:
//    public void searchProductUsingValidProductName() {
//        searchBox1.sendKeys("Hand Sanitizer");
//        searchButton1.click();
//    }

    public void validAmazonUserShouldLogin() throws InterruptedException {
        clickOnWebElement(accountLogIn);
        enterValueOnWebElement(email, "bibirabuqa@gmail.com");
        clickOnWebElement(clickContinue);
        //waitFor(2);
        enterValueOnWebElement(password,"QaTest@2023");
        clickOnWebElement(singIn);
    }

    public void verifyAmazonLoginValid(String expectedProduct) {
       String actualProduct = validLogin.getText();
        Assert.assertEquals(actualProduct, expectedProduct, "==== Not Valid Login ==== ");
    }

    public void amazonLogoLinkVaild(){
        clickOnWebElement(amazonLogo);
    }






}
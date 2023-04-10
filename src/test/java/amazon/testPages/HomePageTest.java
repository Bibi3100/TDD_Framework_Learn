package amazon.testPages;

import amazon.pages.HomePage;
import amazon.pages.RegistrationPage;
import config.common.WebTestBase;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class HomePageTest extends WebTestBase {

    public HomePage homePage;
    public RegistrationPage registrationPage;

    // @BeforeMethod
    public void getInItElements() {
        PageFactory.initElements(driver, HomePage.class);
        homePage = new HomePage(driver);
        registrationPage = new RegistrationPage(driver);
    }

    //Bibi Rabu Test case ++++

@Test()
public void amazonLoginWithVaildEmailAndPassword() throws InterruptedException {
      //homePage = new HomePage(driver);
    getInItElements();
    homePage.validAmazonUserShouldLogin();
    waitFor(2);
    homePage.verifyAmazonLoginValid("Hello, bibi");
}

    @Test
    public void amazonLogoValidation(){
        getInItElements();
        homePage.amazonLogoLinkVaild();
    }




}
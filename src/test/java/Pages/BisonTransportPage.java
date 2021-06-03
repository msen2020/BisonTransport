package Pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Drivers;

public class BisonTransportPage {

    public BisonTransportPage() {

        PageFactory.initElements(Drivers.getDriver(), this);
    }

    @FindBy(xpath = "//a[contains(text(),'About')]")
    public WebElement About;

    @FindBy(xpath = "//a[contains(text(),'Services')]")
    public WebElement Services;

    @FindBy(xpath = "//a[contains(text(),'Careers')]")
    public WebElement Careers;

    @FindBy(xpath = "//a[contains(text(),'News')]")
    public WebElement News;

    @FindBy(xpath = "//a[contains(text(),'Contact')]")
    public WebElement Contact;

    @FindBy(xpath = "//a[contains(text(),'Blog')]")
    public WebElement Blog;

    @FindBy(xpath = "//a[contains(text(),'Login')]")
    public WebElement Login;

    @FindBy(xpath = "//a[contains(text(),'READ HERE')]")
    public WebElement ReadHere;


    //Verifying Web elements visibility.
    public void verification(String expected) {

        if (expected.equals(About.getText())) {
            String actual = About.getText();
            Assert.assertEquals(expected, actual);

        } else if (expected.equals(Services.getText())) {
            String actual = Services.getText();
            Assert.assertEquals(expected, actual);

        } else if (expected.equals(Careers.getText())) {
            String actual = Careers.getText();
            Assert.assertEquals(expected, actual);

        } else if (expected.equals(News.getText())) {
            String actual = News.getText();
            Assert.assertEquals(expected, actual);

        } else if (expected.equals(Blog.getText())) {
            String actual = Blog.getText();
            Assert.assertEquals(expected, actual);

        } else {
            String actual = Login.getText();
            Assert.assertEquals(expected, actual);

        }
    }
    //Verifying READ HERE is visible.
    public void verifyReadHere(String read) {

        Assert.assertTrue(ReadHere.isDisplayed());
    }
}

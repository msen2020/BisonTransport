package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Drivers;

import java.util.concurrent.TimeUnit;

public class ShippingPage extends BasePage {



    //
    public ShippingPage() {

        PageFactory.initElements(Drivers.getDriver(), this);
    }

    @FindBy(xpath = "//body/article[1]/div[1]/div[1]/div[2]/div[1]/a[1]/img[1]")
    public WebElement clickServicesLink;


    @FindBy(xpath = "//a[contains(text(),'Request a Quote')]")
    public WebElement clickRequest_a_Quote;

    @FindBy(xpath = "//input[@id='select_a_service0-d46e6e98-5fd4-471d-8bba-40c15e9e2e66']")
    public WebElement Full_Truckload;

    @FindBy(xpath = "//input[@id='live_intermodal0-d46e6e98-5fd4-471d-8bba-40c15e9e2e66']")
    public WebElement Live_Load;

    @FindBy(xpath = "//input[@id='live_intermodal1-d46e6e98-5fd4-471d-8bba-40c15e9e2e66']")
    public WebElement Live_Unload;

    @FindBy(xpath = "//select[@id='shipment_weight_metric-d46e6e98-5fd4-471d-8bba-40c15e9e2e66']")
    public WebElement LBS_KGS;

    @FindBy(xpath = "//input[@id='ft_weight-d46e6e98-5fd4-471d-8bba-40c15e9e2e66']")
    public WebElement Shipment_Weight;

    @FindBy(xpath = "//input[@id='this_shipment2-d46e6e98-5fd4-471d-8bba-40c15e9e2e66']")
    public WebElement Dedicated;

    @FindBy(css = "#this_shipment_requires0-d46e6e98-5fd4-471d-8bba-40c15e9e2e66")
    public WebElement A_Dry_Trailer;

    @FindBy(css = "#where_are_you_shipping_from_-d46e6e98-5fd4-471d-8bba-40c15e9e2e66")
    public WebElement Country;

    @FindBy(css = "#pickup_state_province-d46e6e98-5fd4-471d-8bba-40c15e9e2e66")
    public WebElement PickUp_State_Province;

    @FindBy(css = "#postal_code_zip-d46e6e98-5fd4-471d-8bba-40c15e9e2e66")
    public WebElement PickUp_Postal_Code_ZIP;

    @FindBy(css = "#pick_up_address-d46e6e98-5fd4-471d-8bba-40c15e9e2e66")
    public WebElement Pick_Up_City;

    @FindBy(css = "#where_are_you_shipping_to_-d46e6e98-5fd4-471d-8bba-40c15e9e2e66")
    public WebElement Shipping_To_Country;

    @FindBy(css = "#dropoff_state_province-d46e6e98-5fd4-471d-8bba-40c15e9e2e66")
    public WebElement Shipping_To_State;

    @FindBy(css = "#drop_off_postal_code_zip-d46e6e98-5fd4-471d-8bba-40c15e9e2e66")
    public WebElement Shipping_To_PostalCode;

    @FindBy(css = "#delivery_address-d46e6e98-5fd4-471d-8bba-40c15e9e2e66")
    public WebElement Delivery_City;

    @FindBy(css = "#freight_product_type-d46e6e98-5fd4-471d-8bba-40c15e9e2e66")
    public WebElement Freight_Product_Type;

    @FindBy(css = "#number_of_loads-d46e6e98-5fd4-471d-8bba-40c15e9e2e66")
    public WebElement Number_Of_Loads;

    @FindBy(css = "#frequency-d46e6e98-5fd4-471d-8bba-40c15e9e2e66")
    public WebElement Frequency;

    @FindBy(css = "#delivery_date-d46e6e98-5fd4-471d-8bba-40c15e9e2e66")
    public WebElement DeliveryDate;

    @FindBy(xpath = "//table/tbody/tr[1]/td[3]")
    public WebElement June_1;

    @FindBy(css = "#pick_up_deliver_date-d46e6e98-5fd4-471d-8bba-40c15e9e2e66")
    public WebElement Pick_Up_Date;

    @FindBy(xpath = "(//table/tbody/tr[5]/td[4])[2]")
    public WebElement June_30;

    @FindBy(css = "#cargovalue-d46e6e98-5fd4-471d-8bba-40c15e9e2e66")
    public WebElement Cargo_Value;

    @FindBy(css = "#firstname-d46e6e98-5fd4-471d-8bba-40c15e9e2e66")
    public WebElement FirstName;

    @FindBy(css = "#lastname-d46e6e98-5fd4-471d-8bba-40c15e9e2e66")
    public WebElement LastName;

    @FindBy(css = "#phone-d46e6e98-5fd4-471d-8bba-40c15e9e2e66")
    public WebElement PhoneNumber;

    @FindBy(css = "#company-d46e6e98-5fd4-471d-8bba-40c15e9e2e66")
    public WebElement CompanyName;

    @FindBy(css = "#email-d46e6e98-5fd4-471d-8bba-40c15e9e2e66")
    public WebElement Email;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/form[1]/div[1]/div[2]/input[1]")
    public WebElement Submit;

    //iframe locator
    @FindBy(xpath = "//iframe[@id='hs-form-iframe-0']")
    public WebElement iframe1;

    //* Scenario 2 starting.

    //Clicking the Services link.
    public void ClickServicesLink() {

        // Trying to make sure page loaded and all the elements visible.
        Drivers.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //to see the process putting 3 second wait.
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clickServicesLink.click();

    }
        // Clicking the ClickRequest_a_Quote
    public void ClickRequest_a_Quote() {

        Drivers.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clickRequest_a_Quote.click();

    }

    //Verifying the title that we are in Shipping Page
    public void VerifyUsersInShippingPage() {
        System.out.println("You are on the shipping Page");

    }

    //User selects "Full Truckload" under "1. SELECT FREIGHT TYPE"
    public void selectCheckBoxes(String a) {
        try {
            switchiframe();
        }catch (Exception e){

        }


        if(a.equals("Full Truckload")) {
            Full_Truckload.click();
        }
        if(a.equals("Live Unload")) {
            Live_Unload.click();
        }
        if (a.equals("Live Load")) {
            Live_Load.click();
        }

    }

    private void selectCheckBoxes(WebElement full_truckload, WebElement live_unload, WebElement live_load) {
    }

    public void selectADryTrailer() {
        scrollDown(A_Dry_Trailer);
        A_Dry_Trailer.click();
    }

    //Verifying the user is on the Result page.
    public void verifyUserOnTheResultPage(){

    }

    /*
    Verifying the user sees the message on the Result page.
    "Thank You for contacting us. One of our Account Managers will get back to you shortly.
    */
    public void verification(){

    }
    //iframe oldugundan switchiframe olusturuyoruz
    public void switchiframe(){
        Drivers.getDriver().switchTo().frame(iframe1);

    }

    }
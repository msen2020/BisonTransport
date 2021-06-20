package StepDefinitions;

import Pages.BisonTransportPage;
import Pages.ShippingPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.support.ui.Select;

public class ShippingStepDefs {


        ShippingPage shippingPage = new ShippingPage();


    @Given("User clicks Services link on the home page")
        public void user_clicks_services_link_on_the_home_page() {
        shippingPage.clickServicesLink.click();
        System.out.println("You are in the Services Page");
        }

        @Then("User clicks on request a quote button")
        public void user_clicks_on_request_a_quote_button() {
        shippingPage.clickRequest_a_Quote.click();
        System.out.println("You are on the Request a Quote page");
        }

        @Then("User selects {string} under {string}")
        public void user_selects_under(String string, String string2)  {

        shippingPage.selectCheckBoxes(string);
        //Thread.sleep(1000);

        }

        @Then("User selects {string} under LBS_KGS")
        public void user_selects_kgs_under_lbs_kgs(String KGS) {
        Select LBS_KGS = new Select(shippingPage.LBS_KGS);
        LBS_KGS.selectByVisibleText(KGS);
        }

        @Then("User enters {string} in the Shipment Weight text box")
        public void user_enters_in_the_shipment_weight_text_box(String weight) {
        shippingPage.Shipment_Weight.sendKeys(weight);
        }

        @Then("User selects {string} under the This Shipment is")
        public void user_selects_dedicated_under_the_this_shipment_is(String type) {
               shippingPage.Dedicated.click();
        }

        @Then("User selects {string} under the This Shipment Requires")
        public void user_selects_a_dry_trailer_under_the_this_shipment_requires(String str) {
                shippingPage.A_Dry_Trailer.click();
        }

        @Then("User selects {string} under the Shipping from Country")
        public void user_selects_united_states_under_the_shipping_from_country(String s) {

            // dropdowns
            Select Country = new Select(shippingPage.Country);
            Country.selectByVisibleText("United States");
        }

        @Then("User enters {string} under the Shipping from States Province")
        public void user_selects_pennsylvania_under_the_shipping_from_states_province(String State) {
                shippingPage.PickUp_State_Province.sendKeys(State);
        }

        @Then("User enters {string} under the Shipping from Postal Code")
        public void user_enters_under_the_shipping_from_postal_code(String code) {
                shippingPage.PickUp_Postal_Code_ZIP.sendKeys(code);
        }

        @Then("User enters {string} under the Pick Up City")
        public void user_selects_pennsylvania_under_the_pick_up_city(String city) {
                shippingPage.Pick_Up_City.sendKeys(city);
        }

        @Then("User selects {string} under the Shipping to Country")
        public void user_selects_other_under_the_shipping_to_country(String country) {

            /* dropdowns
            We are creating an object of *Select* and the corresponding class that we created
            then we select the text by using selectByVisibleText("text") method
            */
            Select Shipping_To_Country = new Select(shippingPage.Shipping_To_Country);
            Shipping_To_Country.selectByVisibleText(country);

        }

        @Then("User enters {string} under the Shipping to States Province")
        public void user_selects_spain_under_the_shipping_to_states_province(String ShippingCountry) {
                shippingPage.Shipping_To_State.sendKeys(ShippingCountry);
        }

        @Then("User enters {string} under the Shipping to Postal Code")
        public void user_enters_under_the_shipping_to_postal_code(String shippingtoCode) {
                shippingPage.Shipping_To_PostalCode.sendKeys(shippingtoCode);
        }

        @Then("User enters {string} under the Delivery City")
        public void user_selects_sevilla_under_the_delivery_city(String shippngToCity) {
                shippingPage.Delivery_City.sendKeys(shippngToCity);
        }

        @Then("User enters {string} in the Freight_Product Type text box")
        public void user_enters_in_the_freight_product_type_text_box(String productType) {
                shippingPage.Freight_Product_Type.sendKeys(productType);
        }

        @Then("User selects {string} under the Number Of Loads")
        public void user_selects_one_time_under_the_number_of_loads(String number) {

            //dropdowns

            Select numberOfLoads = new Select(shippingPage.Number_Of_Loads);
            numberOfLoads.selectByVisibleText(number);
        }

        @Then("User selects {string} under the Frequency")
        public void user_selects_per_year_under_the_frequency(String frequency) {
            //dropdowns
            Select Frequency = new Select(shippingPage.Frequency);
            Frequency.selectByVisibleText(frequency);
        }
        // DATE
        // Select a Date from DatePicker

        @Then("User selects date {int}-{int}-{int} under the Delivery Date")
        public void user_selects_date_under_the_delivery_date(Integer int1, Integer int2, Integer int3) {
        shippingPage.DeliveryDate.click();
        shippingPage.June_1.click();

        }

        //Select a Date from DatePicker

        @Then("User selects date {int}-{int}-{int} under the Pick Up Date")
        public void user_selects_date_under_the_pick_up_date(Integer int1, Integer int2, Integer int3) {
            shippingPage.Pick_Up_Date.click();
            shippingPage.June_30.click();
        }

        @Then("User enters {string} Euros under the Cargo Value")
        public void user_enters_euros_under_the_cargo_value(String value) {

            shippingPage.Cargo_Value.sendKeys( value + " Euros");
        }

        @Then("User enters {string} under the First Name")
        public void user_enters_under_the_first_name(String name) {
            shippingPage.FirstName.sendKeys(name);

        }

        @Then("User enters {string} under the Last Name")
        public void user_enters_under_the_last_name(String lastName) {
            shippingPage.LastName.sendKeys(lastName);

        }

        @Then("User enters {string} under the Phone Number")
        public void user_enters_under_the_phone_number(String phone) {
            shippingPage.PhoneNumber.sendKeys(phone);
        }

       @Then("User enters {string} under the Company Name")
       public void user_enters_under_the_company_name(String companyName) {
       shippingPage.CompanyName.sendKeys(companyName);

       }

        @Then("User enters {string} under the Email")
        public void user_enters_under_the_email(String email) {
            shippingPage.Email.sendKeys(email);
        }

        @Then("User clicks Submit button")
        public void user_clicks_submit_button() {
            shippingPage.Submit.click();


        }

        @Then("User verifies that he is in result page")
        public void user_verifies_that_he_is_in_result_page() {

        shippingPage.verifyUserOnTheResultPage();
            System.out.println("You are on the result page.");
        }

        @Then("User verifies that he sees {string}")
        public void user_verifies_that_he_sees(String message) {

        shippingPage.verification();
        System.out.println(message);

        }



}

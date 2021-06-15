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

        @Then("User selects Full Truckload under {string}")
        public void user_selects_full_truckload_under(String string) throws InterruptedException {
        Thread.sleep(2000);
        shippingPage.selectFull_Truckload();
}

        @Then("User selects Live load under {string}")
        public void user_selects_live_load_under(String string) {
        shippingPage.Live_Load.click();
}

        @Then("User selects Live Unload under {string}")
        public void user_selects_live_unload_under(String string) {
        shippingPage.Live_Unload.click();
}

        @Then("User selects KGS under LBS_KGS")
        public void user_selects_kgs_under_lbs_kgs() {
        Select LBS_KGS = new Select(shippingPage.LBS_KGS);
        LBS_KGS.selectByVisibleText("KGS");
        }

        @Then("User enters {int} in the Shipment Weight text box")
        public void user_enters_in_the_shipment_weight_text_box(Integer int1) {
        shippingPage.Shipment_Weight.sendKeys("10000");
        }

        @Then("User selects Dedicated under the This Shipment is")
        public void user_selects_dedicated_under_the_this_shipment_is() {
               shippingPage.Dedicated.click();
        }

        @Then("User selects A Dry Trailer under the This Shipment Requires")
        public void user_selects_a_dry_trailer_under_the_this_shipment_requires() {
                shippingPage.A_Dry_Trailer.click();
        }

        @Then("User selects United States under the Shipping from Country")
        public void user_selects_united_states_under_the_shipping_from_country() {

            // dropdowns
            Select Country = new Select(shippingPage.Country);
            Country.selectByVisibleText("United States");
        }

        @Then("User enters Pennsylvania under the Shipping from States Province")
        public void user_selects_pennsylvania_under_the_shipping_from_states_province() {
                shippingPage.PickUp_State_Province.sendKeys("Pennsylvania");
        }

        @Then("User enters {string} under the Shipping from Postal Code")
        public void user_enters_under_the_shipping_from_postal_code(String string) {
                shippingPage.PickUp_Postal_Code_ZIP.sendKeys("19019");
        }

        @Then("User enters Pennsylvania under the Pick Up City")
        public void user_selects_pennsylvania_under_the_pick_up_city() {
                shippingPage.Pick_Up_City.sendKeys("Philadelphia");
        }

        @Then("User selects Other under the Shipping to Country")
        public void user_selects_other_under_the_shipping_to_country() {

            // dropdowns
            // We are creating an object of *Select* and the corresponding
            // class that we created
            //then we select the text by using selectByVisibleText("text") method

            Select Shipping_To_Country = new Select(shippingPage.Shipping_To_Country);
                Shipping_To_Country.selectByVisibleText("Other");

        }

        @Then("User enters Spain under the Shipping to States Province")
        public void user_selects_spain_under_the_shipping_to_states_province() {
                shippingPage.Shipping_To_State.sendKeys("Spain");
        }

        @Then("User enters {string} under the Shipping to Postal Code")
        public void user_enters_under_the_shipping_to_postal_code(String string) {
                shippingPage.Shipping_To_PostalCode.sendKeys("41007");
        }

        @Then("User enters Sevilla under the Delivery City")
        public void user_selects_sevilla_under_the_delivery_city() {
                shippingPage.Delivery_City.sendKeys("Sevilla");
        }

        @Then("User enters {string} in the Freight_Product Type text box")
        public void user_enters_in_the_freight_product_type_text_box(String string) {
                shippingPage.Freight_Product_Type.sendKeys("Timber");
        }

        @Then("User selects One Time under the Number Of Loads")
        public void user_selects_one_time_under_the_number_of_loads() {

            //dropdowns

            Select numberOfLoads = new Select(shippingPage.Number_Of_Loads);
            numberOfLoads.selectByVisibleText("One Time");
        }

        @Then("User selects Per Year under the Frequency")
        public void user_selects_per_year_under_the_frequency() {
            //dropdowns
            Select Frequency = new Select(shippingPage.Frequency);
            Frequency.selectByVisibleText("Per Year");
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

        @Then("User enters {int} Euros under the {string}")
        public void user_enters_euros_under_the(Integer int1, String string) {

            shippingPage.Cargo_Value.sendKeys("100000 Euros");
        }

        @Then("User enters Mehmet under the First Name")
        public void user_enters_mehmet_under_the_first_name() {
            shippingPage.FirstName.sendKeys("MEHMET");

        }

        @Then("User enters Sen\" under the \"Last Name\"")
        public void user_enters_sen_under_the_last_name() {
            shippingPage.LastName.sendKeys("Sen");

        }

        @Then("User enters {string} under the Phone Number")
        public void user_enters_under_the_phone_number(String string) {
            shippingPage.PhoneNumber.sendKeys("+43671379791");
        }

       @Then("User enters {string} under the Company Name")
       public void user_enters_under_the_company_name(String string) {
       shippingPage.CompanyName.sendKeys("Shen");

       }

        @Then("User enters msen2020@hotmail.com under the Email")
        public void user_enters_msen2020_hotmail_com_under_the_email() {
            shippingPage.Email.sendKeys("msen2020@hotmail.com");
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
        public void user_verifies_that_he_sees(String string) {

        shippingPage.verification();
        System.out.println("Thank You for contacting us. One of our Account Managers will get back to you shortly.");

        }



}

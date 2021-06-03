
Feature: BisonTransport Page

  @bisonTransport
  Scenario: TC01_User goes to BisonTransport Page
    Given User goes to BisonTransport page
    Then Verify that "About" are present
    Then Verify that "Services" are present
    Then Verify that "Careers" are present
    Then Verify that "News" are present
    Then Verify that "Contact" are present
    Then Verify that "Blog" are present
    Then Verify that "Login" are present
    Then Verify that "READ HERE" is visible

    @shippingDetails
    Scenario: TC02_User goes to BisonTransport Page
    Given User clicks Services link on the home page
      Then User clicks on request a quote button
      Then User selects "Full Truckload" and "Live load" and "Live Unload" under "1. SELECT FREIGHT TYPE"
      And User selects "KGS" under "LBS/KGS"
      And User enters "10000" in the Shipment Weight text box
      And User selects "Dedicated" under the "This Shipment is"
      Then User selects "A Dry Trailer" under the "This Shipment Requires"
      Then User selects "United States" under the "Country"
      Then User selects "Pennsylvania" under the "States/Province"
      Then User selects "Postal Code/ZIP" under the "19019"
      Then User selects "Pennsylvania" under the "Pick Up City"
      Then User selects "Other" under the "Country"
      Then User selects "Spain" under the "States/Province"
      Then User selects "Postal Code/ZIP" under the "41007"
      Then User selects "Sevilla" under the "Delivery City"
      Then User enters "Timber" in the "Freight/Product Type" text box
      Then User selects "One Time" under the "Number Of Loads"
      Then User selects "Per Year" under the "Frequency"
      Then User selects date "2021-06-01" under the "Delivery Date"
      Then User selects date "2021-06-30" under the "Pick Up Date"
      Then User enters "100000 Euros" under the "Cargo Value"
      Then User enters "Mehmet" under the "First Name"
      Then User enters "Sen" under the "Last Name"
      Then User enters "+43671379791" under the "Phone Number"
      Then User enters "Shen" under the "Company Name"
      Then User enters "msen2020@hotmail.com" under the "Email"
      Then User clicks Submit button
      Then User verifies that he is in result page
      Then User verifies that he sees "Thank You for contacting us. One of our Account Managers will get back to you shortly."




















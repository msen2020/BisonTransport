
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
      Then User selects "Full Truckload" under "SELECT FREIGHT TYPE"
      Then User selects "Live load" under "SELECT FREIGHT TYPE"
      Then User selects "Live Unload" under "SELECT FREIGHT TYPE"
      And User selects "KGS" under LBS_KGS
      And User enters "10000" in the Shipment Weight text box
      And User selects "Dedicated" under the This Shipment is
      And User selects "A Dry Trailer" under the This Shipment Requires
      And User selects "United States" under the Shipping from Country
      And User enters "Pennsylvania" under the Shipping from States Province
      And User enters "19019" under the Shipping from Postal Code
      And User enters "Philadelphia" under the Pick Up City
      And User selects "Other" under the Shipping to Country
      And User enters "Spain" under the Shipping to States Province
      And User enters "41007" under the Shipping to Postal Code
      And User enters "Sevilla" under the Delivery City
      And User enters "Timber" in the Freight_Product Type text box
      And User selects "One Time" under the Number Of Loads
      And User selects "Per Year" under the Frequency
      And User selects date 2021-06-01 under the Delivery Date
      And User selects date 2021-06-30 under the Pick Up Date
      And User enters "100000" Euros under the Cargo Value
      And User enters "Mehmet" under the First Name
      And User enters "Sen" under the Last Name
      And User enters "+43671379791" under the Phone Number
      And User enters "Shen" under the Company Name
      And User enters "msen2020@hotmail.com" under the Email
      Then User clicks Submit button
      Then User verifies that he is in result page
      Then User verifies that he sees "Thank You for contacting us. One of our Account Managers will get back to you shortly."




















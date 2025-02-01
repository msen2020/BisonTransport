Feature: BisonTransport Page

  @shippingDetails
  Scenario: TC02_user goes to BisonTransport Page
    Given user clicks Services link on the home page
    Then user clicks on request a quote button
    Then user selects "Full Truckload" under "SELECT FREIGHT TYPE"
    Then user selects "Live load" under "SELECT FREIGHT TYPE"
    Then user selects "Live Unload" under "SELECT FREIGHT TYPE"
    And user selects "KGS" under LBS_KGS
    And user enters "10000" in the Shipment Weight text box
    And user selects "Dedicated" under the This Shipment is
    And user selects "A Dry Trailer" under the This Shipment Requires
    And user selects "United States" under the Shipping from Country
    And user enters "Pennsylvania" under the Shipping from States Province
    And user enters "19019" under the Shipping from Postal Code
    And user enters "Philadelphia" under the Pick Up City
    And user selects "Other" under the Shipping to Country
    And user enters "Spain" under the Shipping to States Province
    And user enters "41007" under the Shipping to Postal Code
    And user enters "Sevilla" under the Delivery City
    And user enters "Timber" in the Freight_Product Type text box
    And user selects "One Time" under the Number Of Loads
    And user selects "Per Year" under the Frequency
    And user selects date 2021-06-01 under the Delivery Date
    And user selects date 2021-06-30 under the Pick Up Date
    And user enters "100000" Euros under the Cargo Value
    And user enters "Mehmet" under the First Name
    And user enters "Sen" under the Last Name
    And user enters "+43671379791" under the Phone Number
    And user enters "Shen" under the Company Name
    And user enters "msen2020@hotmail.com" under the Email
    Then user clicks Submit button
    Then user verifies that he is in result page
    Then user verifies that he sees "Thank You for contacting us. One of our Account Managers will get back to you shortly."
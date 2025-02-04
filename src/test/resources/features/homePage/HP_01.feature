@msen
Feature: Home Page Navigation Bar

  @HP_01 @homepage @smoke
  Scenario: TC_01 Verify navbar titles are visible and functional
    Given user goes to Bison Transport page
    Then user verifies the titles are displayed & functional in the navbar
      | About             |
      | Careers           |
      | Shippers          |
      | Carriers          |
      | Locations         |
      | Contact           |
      | Talk to an Expert |
      | Request Quote     |

  @HP_01 @homepage @smoke
  Scenario: TC_02 Verify About menu items and page content
    Given user goes to Bison Transport page
    Then verify About menu items and their content
      | menuItem                           | url                             | pageTitle                                            | heading                              |
      | Who We Are                         | about                           | About - Bison Transport                              | ABOUT US                             |
      | Leadership                         | leadership                      | Leadership - Bison Transport                         | OUR LEADERSHIP                       |
      | Our Culture                        | our-culture                     | Culture - Bison Transport                            | OUR CULTURE                          |
      | History                            | history                         | History - Bison Transport                            | BISON'S HISTORY                      |
      | Environmental, Social & Governance | environmental-social-governance | Environmental, Social & Governance - Bison Transport | ENVIRONMENTAL, SOCIAL AND GOVERNANCE |
      | Blog                               | blog                            | Blog - Bison Transport                               | BISON BLOG                           |


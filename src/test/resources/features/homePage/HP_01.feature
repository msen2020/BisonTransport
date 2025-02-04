@HP_01 @msen
Feature: HP_01 Verify Home Page Navigation Bar

  @HP_01_TC_01 @homepage @smoke
  Scenario: HP_01_TC_01 Verify navbar titles are visible and functional
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

  @HP_01_TC_02 @homepage @smoke
  Scenario: HP_01_TC_02 Verify About menu items and page content
    Given user goes to Bison Transport page
    Then verify About menu items and their content
      | menuItem                           | url                             | pageTitle                                            | heading                              |
      | Who We Are                         | about                           | About - Bison Transport                              | ABOUT US                             |
      | Leadership                         | leadership                      | Leadership - Bison Transport                         | OUR LEADERSHIP                       |
      | Our Culture                        | our-culture                     | Culture - Bison Transport                            | OUR CULTURE                          |
      | History                            | history                         | History - Bison Transport                            | BISON'S HISTORY                      |
      | Environmental, Social & Governance | environmental-social-governance | Environmental, Social & Governance - Bison Transport | ENVIRONMENTAL, SOCIAL AND GOVERNANCE |
      | Blog                               | blog                            | Blog - Bison Transport                               | BISON BLOG                           |

  @HP_01_TC_03 @homepage @smoke
  Scenario: HP_01_TC_03 Verify Careers menu items and page content
    Given user goes to Bison Transport page
    Then verify Careers menu items and their content
      | menuItem             | url                                                                     | pageTitle                                        | heading                |
      | Driving Jobs         | https://bisontransport.phenompro.com/ca/en                              | Join Bison Transport Herd - Jobs for Job Seekers | Bison Transport Jobs   |
      | Non-Driving Jobs     | https://bisontransport.phenompro.com/ca/en                              | Join Bison Transport Herd - Jobs for Job Seekers | Bison Transport Jobs   |
      | Students & Graduates | https://bisontransport.phenompro.com/ca/en/bison-students-and-graduates | Explore Bison Transport Jobs                     | Students and Graduates |

  @HP_01_TC_04 @homepage @smoke
  Scenario: HP_01_TC_04 Verify Shippers menu items and page content
    Given user goes to Bison Transport page
    Then verify Shippers menu items and their content
      | menuItem      | url                                                | pageTitle                    | heading       |
      | Truckload     | https://www.bisontransport.com/shippers/truckload  | Truckload - Bison Transport  | TRUCKLOAD     |
      | Intermodal    | https://www.bisontransport.com/shippers/intermodal | Intermodal - Bison Transport | INTERMODAL    |
      | Logistics     | https://www.bisontransport.com/shippers/logistics  | Logistics - Bison Transport  | LOGISTICS     |
      | LTL           | https://www.bisontransport.com/shippers/ltl        | LTL - Bison Transport        | LTL           |
      | Mexico        | https://www.bisontransport.com/shippers/mexico     | Mexico - Bison Transport     | MEXICO        |
      | i-Tools Login | https://itools.bisontransport.com/                 | i-Tools - Bison Transport    | i-Tools Login |


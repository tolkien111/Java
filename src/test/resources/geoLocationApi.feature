Feature: Geo location API
  I want to know coordinates after put an address
  Also I what to know when something goes wrong with searching a location

  Scenario: Successfully retrieving location data for a known place
    Given the client has location query for "Champ de Mars, 75007 Paris"
    When the client requests the location data
    Then the client should receives coordinates "48.8556475" and "2.2986304"

  Scenario: Retrieve location data for a non-existent location
    Given the client has location query for "Lorem ipsum"
    When the client requests the location data
    Then the client should receives an error indicating a problem with Google API

  Scenario: Retrieve location data for location query with forbidden characters
    Given the client has location query for "Champ de Mars %23, 75007 Paris"
    When the client requests the location data
    Then the client should receives an error indicating a problem with location query string


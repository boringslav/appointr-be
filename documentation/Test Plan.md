# Test Plan

## Introduction
This test plan specifies the testing approach, strategy, quality objective and test environment for the development of my Individual Project.
The plan identifies the approaches that will be taken, types of testing that will be done. Majority of the tests can be automated which results in less time-consuming development. However, some tests, such as User Acceptance Testing have to be tested by a human, which might be more time consuming, The mixture of both automated and humen tests give the greatest results.

## Testing Strategy
I intend to use a mixture of tests, including unit tests, integration tests and user acceptance tests. Below you will find an overview of the test types, the purpose of the test and the tester (person or a program)

| Test                    | Purpose                                                                                                                                                                                                  | Tester                                                                                  |
| ----------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------- |
| Unit Tests              | Testing specific pieces of code on a low level, ensuring that it works according to the requirements and that the expected exceptions are being handled                                                  | GitLab CI/CD Runner                                                                     |
| Integration Testing     | After making sure all the different pieces of code work accurately alone, combine them an test them as a group to see if they will be working accordingly together                                       | Selenium                                                                                |
| User Acceptance Testing | Testing the interaction between users and the system, observing how user friendly the application is and comparing the expected output against the real output when the user performs a specific action. | User Acceptance Tests will be carried out by human testers, mainly ny me and my friends | 

| ID  | Test Description                                                         | Expected Results                                                                                            |
| --- | ------------------------------------------------------------------------ | ----------------------------------------------------------------------------------------------------------- |
| 1   | Testing if a created user (Customer, Company) can login into the website | Every user (Customer, Company) has his own accoint and should be able to log in after making a registration |
| 2   | Testing if a Company can upload timeslot for Customer to book            | The available time slot (booking) gets uploaded and appears on the home page                                |
| 3   | Testing if a Company can update or delete a time slot                    | The created time slot has an update button, which opens the edit page                                       |
| 4   | Testing if the site displays all the Booked time slots (Bookings)        | The Company can see all customer bookings                                                                   |


## Quality Objective
The tests should focus on testing project activities such as creating, editing and choosing an appointment/booking to ensure that all these operations can perform normally.

## Test Environment
I intend to test my code using CI/CD pipelines on the unit tests.
For running integration tests, I intend to use Selenium to automatically perform the tests.
For the acceptance testing I will test the code on different browsers and mobile device to ensure that the application is working properly on all machines.
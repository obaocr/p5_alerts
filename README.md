# SafetyNet Alerts
The main purpose of the SafetyNet Alerts application is to send information to the emergency services.
If, for example, a fire breaks out, SafetyNet Alerts must provide information about people in the burning building, such as their telephone numbers. Another example: In the event of a hurricane alert, we want SafetyNet Alerts to be able to notify all residents in the area by SMS. To do this, SafetyNet Alerts needs to collect the phone numbers of people living in houses near the hurricane area. As a final example, in the event of flooding, we want to provide the emergency services with specific information about the people in the area. We need to know the potential victims, their ages and their medical history (treatments, allergies, etc.).
The application exposes web services for querying and updating information

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them

- Java 1.8
- Maven 3.6.3
- Springboot 2.2.5


### Installing

A step by step series of examples that tell you how to get a development env running:

1.Install Java:

https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html

2.Install Maven:

https://maven.apache.org/install.html

3.Springboot

https://spring.io/projects/spring-boot

### Running App

Import the code into an IDE of your choice and run the P5AlertsApplication.java to launch the application.

### Testing

The app has unit tests and integration tests written. The existing tests need to be triggered from maven-surefire plugin while we try to generate the final executable jar file.
The code coverage can be improved by adding tests for exceptions.

To run the tests from maven, go to the folder that contains the pom.xml file and execute the below command.

`mvn test`

### Other consideration
JAVADOC has been initialized and needs to be completed.

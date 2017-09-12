#Note
Deploy this application on Tomcat 8 with port 8084


# Programming Exercise: Procedure scheduling Web App
Implement a study scheduling application in which procedures for treatment of patients performed
by doctors are planned. The data is modelled via domain objects.
The application provides an interface for the following operations:
- Adding patients
-  Scheduling procedures
-  Updating status of procedure
##Domain objects:
### Patient
1. Id (mandatory)
1. Name (mandatory)
1. Sex (optional)
1. Day of Birth (optional)
### Study
1. Id (mandatory)
1. Description (mandatory)
1. Status [Planned, In Progress, Finished] (mandatory)
1. Planned Start time (mandatory)
1. Estimated End time (optional)
### Doctor
1. Id (mandatory)
1. Name (mandatory)
### Room
1. Id (mandatory)
1. Name (mandatory)

##Tasks:
1. Implement database model using hibernate
2. Implement simple web GUI using Spring MVC
### Technology constraints:
1. Oracle JDK 8 or OpenJDK
1. Spring Framework
1. Hibernate
## Hints:
- Readability and structure of source code is important, consider clean code best practices
- Provide sufficient amount of unit tests for implementation
- Additional documentation is not required but be prepared for questions regarding design
decision during review

- It is not required that data is persisted when application is shut down, but it is expected that
rooms and doctors are already available after start of the application.
- Rooms and doctors can be implemented within the code base. No need for exposing
creation, update or deletion of rooms and doctors via interface.
Hand over:
Bundle complete source (no compiled classes or jar) and additional files like build scripts into a zip
archive and send it per mail to Oliver.Welter@s-cape.com . The content must compile with the
chosen build tool and Oracle JDK 8.

# Supplementary Specification (FURPS+)

## Functionality

Localization:

- The program is capable of changing between 2 different languages, english and portuguese.

Security:

- "All those who wish to use the application must be authenticated with a password holding seven alphanumeric characters, including three capital letters and two digits."
- The interface changes based on what type of user is utilizing the program(user, nurse, administrator, etc.) and has different functionalities.


## Usability 

_Evaluates the user interface. It has several subcategories,
among them: error prevention; interface aesthetics and design; help and
documentation; consistency and standards._
- The aesthetic and design part of the system has not been developed yet.
- The program has documentations on how it is being developed.
- There are some files that can help any type of user utilizing and understanding the program such as the glossary.



## Reliability
_Refers to the integrity, compliance and interoperability of the software. The requirements to be considered are: frequency and severity of failure, possibility of recovery, possibility of prediction, accuracy, average time between failures._

  Reporting:

- The program is capable of generating reports by using the JaCoCo plugin.



## Performance
_Evaluates the performance requirements of the software, namely: response time, start-up time, recovery time, memory consumption, CPU usage, load capacity and application availability._
- There is no information regarding this topic.



## Supportability
_The supportability requirements gathers several characteristics, such as:
testability, adaptability, maintainability, compatibility,
configurability, installability, scalability and more._ 

Localization:

- The program is capable of displaying 2 different languages, such as english and portuguese.

Testability:

- "The development team must implement unit tests for all methods, except for methods that implement Input/Output operations. The unit tests should be implemented using the JUnit 5 framework."

Adaptability: 
- "As Coronavirus might not be the last pandemic in our lifetime, the application should be designed to easily support managing other future pandemic events requiring a massive vaccination of the population."


Compatibility: 
- The software application should also be conceived having in mind that it can be further commercialized to other companies and/or organizations and/or healthcare systems besides DGS. 

Configurability: 
- An Administrator is responsible for properly configuring and managing the core information.


## +

### Design Constraints

_Specifies or constraints the system design process. Examples may include: programming languages, software process, mandatory standards/patterns, use of development tools, class library, etc._

- The application must be developed in Java language using the IntelliJ IDE or NetBeans.



### Implementation Constraints

_Specifies or constraints the code or construction of a system 
such as: mandatory standards/patterns, implementation languages,
database integrity, resource limits, operating system._
- Adopt recognized java coding standards (e.g., CamelCase).





### Interface Constraints
_Specifies or constraints the features inherent to the interaction of the
system being developed with other external systems._

- The application graphical interface is to be developed in JavaFX 11


### Physical Constraints

_Specifies a limitation or physical requirement regarding the hardware used to house the system, as for example: material, shape, size or weight._
- There is no information regarding this topic.
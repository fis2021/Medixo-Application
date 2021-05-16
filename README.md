# Medixo-Application
A JavaFX Application that represents a medical application using the following technologies:
* [Java 15 or 16](https://www.oracle.com/java/technologies/javase-downloads.html)
* [JavaFX](https://openjfx.io/openjfx-docs/) (as GUI)
* [Gradle](https://gradle.org/) (as build tools)
* [Nitrite Java](https://www.dizitart.org/nitrite-database.html) (as Database)

## Prerequisites
To be able to install and run this project, please make sure you have installed Java 11 or higher. Otherwise, the setup will note work!
To check your java version, please run `java -version` in the command line.

To install a newer version of Java, you can go to [Oracle](https://www.oracle.com/java/technologies/javase-downloads.html) or [OpenJDK](https://jdk.java.net/).

It would be good if you also installed Maven and / or Gradle to your system. To check if you have Maven or Gradle installed run `mvn -version` or `gradle -version`.

If you need to install any of them, please refer to this [official Gradle docs](https://docs.gradle.org/current/userguide/installation.html).

Make sure you install JavaFX SDK on your machine, using the instructions provided in the [Official Documentation](https://openjfx.io/openjfx-docs/#install-javafx). Make sure to export the `PATH_TO_FX` environment variable, or to replace it in every command you will find in this documentation from now on, with the `path/to/javafx-sdk-15.0.1/lib`.

Note: you can download version 15 of the javafx-sdk, by replacing in the download link for version 16 the `16` with `15`._

## Setup & Run
To set up and run the project locally on your machine, please follow the next steps.

### Clone the repository
Clone the repository using:
```git
git clone https://github.com/fis2021/SimpleRegistrationExample
```

### Verify that the project Builds locally
Open a command line session and `cd SimpleRegistrationExample`.
If you have installed all the prerequisites, you should be able to run any of the following commands:
```
gradle clean build
```
If you prefer to run using the wrappers, you could also build the project using
```
./gradlew clean build (for Linux or MacOS)
or 
gradlew.bat clean build (for Windows)
```

### Open in IntelliJ IDEA
To open the project in IntelliJ idea, you have to import it as a Gradle project, depending on what you prefer.
After you import it, in order to be able to run it, you need to set up your IDE according to the [official documentation](https://openjfx.io/openjfx-docs/). Please read the section for `Non-Modular Projects from IDE`.
If you managed to follow all the steps from the tutorial, you should also be able to start the application by pressing the run key to the left of the Main class.

### Run the project with Gradle
The project has already been setup for Gradle according to the above link.
To start and run the project use one of the following commands:
* `gradle run` or `./gradlew run` (to start the `run` task of the `org.openjfx.javafxplugin` plugin)

To understand better how to set up a project using JavaFX 11+ and [Gradle](https://openjfx.io/openjfx-docs/#gradle), please check the [official OpenJFX documentation](https://openjfx.io/).

## Registration
The user needs to first register into the application by selecting one of the 2 roles:
* patient
* doctor
  These roles require a unique username, a password and the basic information like full name, age, email adress, phone number and, for the doctor role, specialization.

## Patient
An agent can make an appointment, see all his appointments and cancel an existing appointment.
After logging in he will see a menu from where he can choose an option. 
An appointment should contain a date, a hour, a doctor and the username of the patient.

## Doctor
A Doctor can add, edit and delete an appointment type. Also, he can see his appointments and set a holiday, when no appointments can be done.
After logging in he will see a menu from where he can choose an option.
An appointment type should contain a name, a description and a price.

##Manager
After he logs in, the manager can add a new specialization, which will be available at the registration process for doctors.

## Resources
To understand and learn more about **JavaFX**, you can take a look at some of the following links:
* [Introduction to FXML](https://openjfx.io/javadoc/16/javafx.fxml/javafx/fxml/doc-files/introduction_to_fxml.html)
* [Getting Started with JavaFX](https://openjfx.io/openjfx-docs/)
* [JavaFX Tutorial](https://code.makery.ch/library/javafx-tutorial/)
* [JavaFX Java GUI Design Tutorials](https://www.youtube.com/playlist?list=PL6gx4Cwl9DGBzfXLWLSYVy8EbTdpGbUIG)

To better understand how to use **Nitrite Java**, use the following links:
* [Nitrite Java Github Repository](https://github.com/nitrite/nitrite-java)
* [Nitrite Java Project Page](https://www.dizitart.org/nitrite-database.html)
* [Nitrite Java Documentation Page](https://www.dizitart.org/nitrite-database/)
* [Nitrite Java: Filters](https://www.dizitart.org/nitrite-database/#filter)
* [Nitrite: How to Create an Embedded Database for Java and Android](https://dzone.com/articles/nitrite-how-to-create-an-embedded-database-for-jav)
* [Nitrite: An Embedded NoSQL Database for Java and Android](https://medium.com/@anidotnet/nitrite-an-embedded-nosql-database-for-java-and-android-318bf48c7758)
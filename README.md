# JavaPhone
JavaPhone is a project about a telephone company which provides custom contracts for its users. The users are free to choose the details of their contract with no limitations. This project is responsible for creating, deleting and printing these contracts. As well as creating and managing users.

## Installation
In order to run this program on a terminal you will need to have maven installed. In order to install maven on your computer you will need to enter the following command on your terminal as a root user.
```bash
sudo apt update
sudo apt install maven
```
Verify the installation was completed successfully by entering:
```bash
mvn - version
```
## Execution
In order to execute the program enter the following:
```bash
mvn clean install package
java -cp target/JavaPhone-1.0-SNAPSHOT.jar org.hua.javaphone.it21958
```

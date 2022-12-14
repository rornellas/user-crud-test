# About this project
This project aims to enabling the execution of CRUD operations for the user entity. It's main goal is to attend to a test proposal.

## Stack
- Java
- Spring boot 2
- Gradle
- Reactive Proggraming
- Mockito
- OpenAPI/Swagger

### Tests
I am using mockito to write unit tests. Such tests covers the main aspects and behaviours of the application. It's useful because we know that while those tests run fine the application is, potentially, behaving as expected

## Getting Started
To get started you will need a java 17+ environment and a JAVA_HOME pointing to such installation. If you're used to run java programs, you probably can just run the command bellow that suits to your operational system. 
And this is it, if you're ready, just run:

Linux:
```bash
./run.sh
```

Windows:
```cmd
./run.bat
```

Once the application is started, click [here](http://localhost:8080/swagger-ui.html) to open the swagger API explorer and have access to the CRUD endpoints for creating, updating, listing, deleting and finding users.

To access the database you can always access the h2 console by clicking [here](http://localhost:8080/h2-console). If you need, you can also understand a bit more of how h2 console works by checking the [h2 console documentation](https://developers.redhat.com/quickstarts/eap-archive/h2-console).

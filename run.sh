./gradlew bootJar
java -Dserver.port=8080 -jar ./build/libs/crudusr-0.0.1-SNAPSHOT.jar

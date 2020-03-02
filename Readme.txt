How to generate allure report

Execute below commands -
$ ./gradlew clean build ----- Gradle wrapper cmd has been used to execute the test

Once the execution completed then -
$ ./gradlew allureServe ----- This will generate allure report in web app

Test files are present under this location -
/src/test/resources
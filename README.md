---- How to generate allure report

---- Execute below commands -
$ ./gradlew clean build ----- Gradle wrapper cmd has been used to execute the test

---- Once the execution completed then -
$ ./gradlew allureServe ----- This will generate allure report in web app

---- Test files are present under this location -
src/test/resources
Both the test files are containing 1025 lines having urls

---- In testng.xml thread count = 30 has been considered based on withig what time the execution of complete program got over

---- Output of the execution is getting stored in csv file named as Result.csv which is present under this location -
gojek-qa-growth-assignment/Result.csv




To run a single test or method under the test class
./gradlew test --tests *AppTest.testPrint

After changing build.gradle to show print, run clean
./gradlew clean test

Run gradle clean after modifying build.gradle

Add task to enable printing from unit tests
test {
    testLogging {
        //showStandardStreams = true
        testLogging.showStandardStreams = true
        testLogging.exceptionFormat = 'full'
        // Or we use events method:
        // events 'standard_out', 'standard_error'

        // Or set property events:
        // events = ['standard_out', 'standard_error']

        // Instead of string values we can
        // use enum values:
        // events org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_OU$
        //        org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_ER$
    }
}

Format for task/estting properties. First define a test task. Then 
set the property of the test task to enable system print output
test{
  testTaskName{
    testTaskName.showStandardStreams=true
  }
}



From TestGuice level:
./gradlew :Test2:test

If running while you are in Test2 dir:
./gradlew test



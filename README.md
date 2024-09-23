## Steps to Run Astronuts Code Quality Checks on your Java Project

**for gradle projects**

Apply the **JaCoCo Plugin** to capture and visualize code coverage testing result.

```groovy
apply plugin: 'jacoco'
```



Add Repositories
Ensure your project uses mavenCentral for resolving dependencies:

```groovy
repositories {
    mavenCentral()
}
```


Add the necessary dependencies for your project and testing:
```groovy
dependencies {
    implementation 'com.google.guava:guava:31.0.1-jre'
    testImplementation 'org.junit.jupiter:junit-jupiter:5.8.1'
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher:1.8.1'
}
```
Configure Java Toolchain
Specify the Java toolchain for the project:
```groovy
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}
```

Configure Test Task
Ensure the test task is configured to generate JaCoCo reports after tests run:
```groovy
test {
    finalizedBy jacocoTestReport // report is always generated after tests run
}

tasks.named('test') {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}
```

Configure JaCoCo
Add configuration for generating JaCoCo reports:
```groovy
jacocoTestReport {
    dependsOn test // tests are required to run before generating the report

    reports {
        xml.required = true
    }
}

```

Ensure the build task depends on the JaCoCo report generation:
```groovy
tasks.build {
    dependsOn jacocoTestReport
}
```
## Integration with CI/CD Workflow

You can integrate Astronuts Code Quality action into your build scripts   , which is especially useful in continuous integration (CI) environments where tests are run automatically.

Add this to your workflow file to run Astronuts Code Quality Checks on your Java project:

```yaml
      - name: Run Astronuts Code Quality Checks
        uses: astronuts-app/astronuts-code-quality-action@v4
        with:
          sourceLanguage: 'java'
          buildSystem: 'gradle'
```
For more info you can check the [Astronuts Code Quality Action](https://github.com/marketplace/actions/astronuts-code-quality-action).

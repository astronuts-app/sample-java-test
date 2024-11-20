
---

## Steps to Run Astronuts Code Quality Checks on Your Java Project

### For Gradle Projects

Follow these steps to set up Astronuts code quality checks on your Gradle-based Java project.

### 1. Apply the JaCoCo Plugin
The JaCoCo plugin is required to capture and visualize code coverage during testing.

```groovy
apply plugin: 'jacoco'
```

### 2. Configure Repositories
Ensure your project resolves dependencies from Maven Central.

```groovy
repositories {
    mavenCentral()
}
```

### 3. Add Dependencies
Include the necessary dependencies for your project and testing.

```groovy
dependencies {
    implementation 'com.google.guava:guava:31.0.1-jre'
    testImplementation 'org.junit.jupiter:junit-jupiter:5.8.1'
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher:1.8.1'
}
```

### 4. Configure Java Toolchain
Specify the Java toolchain version for your project.

```groovy
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}
```

### 5. Configure Test Task for JaCoCo Reporting
Ensure the test task generates JaCoCo reports after tests are executed.

```groovy
test {
    finalizedBy jacocoTestReport // Generate the report after tests run
}

tasks.named('test') {
    useJUnitPlatform() // Use JUnit Platform for unit tests
}
```

### 6. Configure JaCoCo Report Generation
Set up JaCoCo to generate XML reports.

```groovy
jacocoTestReport {
    dependsOn test // Ensure tests run before generating the report

    reports {
        xml.required = true // Generate the XML report
    }
}
```

### 7. Ensure JaCoCo Report Runs with Build
Ensure the build task depends on the JaCoCo report generation.

```groovy
tasks.build {
    dependsOn jacocoTestReport
}
```

---

## Integration with CI/CD Workflow

You can integrate Astronuts Code Quality checks into your continuous integration (CI) workflow to run tests and code quality checks automatically.

Add the following to your workflow file to run Astronuts Code Quality Checks on your Gradle-based Java project:

```yaml
      - name: Run Astronuts Code Quality Checks
        uses: astronuts-app/astronuts-code-quality-action@v4
        with:
          sourceLanguage: 'java'
          buildSystem: 'gradle'
```

For more information, refer to the [Astronuts Code Quality Action documentation](https://github.com/marketplace/actions/astronuts-code-quality-action).

---


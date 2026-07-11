# PizzaCostCalculator

## Description

Handy Android app which helps to make an optimal decision when choosing pizza size and shape.
Project focused on high code quality and proper coding and Android architecture practices. Much code
written with TDD, high coverage levels. Unit tests, integration tests, e2e tests. CI/CD made with
Bitrise. SonarQube and CodeCov for metrics. I wanted to make it the best way possible, taking no
half measures and being a contrary to commercial projects limited by deadlines and other business
factors. One might say it's trivial in functionality, but it's not the point as the real effort has
been put predominantly in its codebase.

## Features

- Calculating pizza price/area ratio
- Sorting by ratio ascending/descending
- Saving/deleting provided data

## Technologies

- Kotlin
- Room
- JUnit
- Mockito
- Espresso
- Gradle

and AndroidX stack elements such as Navigation, Lifecycle, Fragment, etc.

## Code Quality

### Sonar

![quality-gate](sca/sonar/img/quality-gate.svg)\
![maintainability](sca/sonar/img/maintainability.svg)
![reliability](sca/sonar/img/reliability.svg)
![security](sca/sonar/img/security.svg)\
![bugs](sca/sonar/img/bugs.svg)
![technical-debt](sca/sonar/img/technical-debt.svg)
![vulnerabilities](sca/sonar/img/vulnerabilities.svg)

### Code Coverage

![coverage](sca/sonar/img/coverage.svg)\
![lines-of-code](sca/sonar/img/lines-of-code.svg)\
![duplicated-lines](sca/sonar/img/duplicated-lines.svg)

Detailed results can be found in [jaCoCo Report][jaCoCo].

## Screenshots

Will appear in the far future, as frontend side is not the point of this app.

[jaCoCo]: sca/app/build/reports/jacoco/testDebugUnitTestCoverage/html/index.html

# Effective software testing

![example workflow](https://github.com/effective-software-testing/code/actions/workflows/tests.yml/badge.svg)

This repository contains the code examples of the _Effective Software Testing: A Guide to Engineering High-Quality Tests_ book, by [Maurício Aniche](https://www.mauricioaniche.com).

Each folder contains the code examples of their respective chapter:

* Chapter 1: Effective and systematic software testing
* Chapter 2: Specification-based testing
* Chapter 3: Structural testing and code coverage
* Chapter 4: Design by Contracts
* Chapter 5: Property-based testing
* Chapter 6: Test doubles and mocks
* Chapter 7: Designing for testability
* Chapter 8: Test-Driven Development
* Chapter 9: Larger tests
* Chapter 10: Test code quality

Each folder is an independent maven project. You should be able to import the project directly in your favorite IDE (e.g., InteliiJ, Eclipse). You can also run all the tests via `mvn test`.

To run code coverage in chapter 3, go to the ch3 folder and type `mvn clean test jacoco:report`. Then, open the `target/site/jacoco/index.html` file to see the report. If you want to run the mutation coverage, type `mvn clean compile test-compile pitest:mutationCoverage`. The report will be generated in the `target/pit-reports/**/index.html`, where `**` is a string that represents the date time that you ran the report. For Linux or Mac users, I provide bash scripts `coverage.sh` and `mutation.sh` that run the commands above for you.

To run the web tests of chapter 9, you first should run the [Spring PetClinic](https://github.com/spring-projects/spring-petclinic) application. For convenience, we provide a compiled jar here. To run the web app, just go to the ch9 folder and type `java -jar *.jar`.

## License and reuse

You are free to reuse and modify the code provided in this repository, for personal or business purposes, as long as the book is always explicitly mentioned as reference. For example, if you are providing training or workshops, you are required to have a dedicated slide with the picture of the book in each of the slide decks that make use of examples from here.

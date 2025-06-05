
---

## Automated API Testing with Java & JUnit 5

Automated testing for an application built with Java, JUnit 5, and Allure reporting. This project incorporates environment variable management and a GitHub Actions CI/CD workflow following industry best practices.

---

## 📑 Table of Contents

* [Overview](#overview)
* [Reports](#reports)
* [Features](#features)
* [Project Structure](#project-structure)
* [Technology Stack](#technology-stack)
* [Requirements](#requirements)
* [Installation](#installation)
* [Configuration](#configuration)
* [Running Tests](#running-tests)
* [CI/CD Pipeline](#cicd-pipeline)
* [Author](#author)
* [License](#license)

---

## 📊 Reports

After your test run, Allure generates a report under `build/allure-report/`. You can:

1. **Generate the report**:

   ```bash
   ./gradlew allureReport
   ```

2. **Serve the report** locally:

   ```bash
   ./gradlew allureServe
   ```

3. **View reports on GitHub Pages**: Visit the <a href="https://josinaldogjunior.github.io/java-automation/" target="_blank">GitHub Pages site</a> for the latest CI/CD pipeline reports.

---

## 🏆 Features

* **Full API Test Coverage**: Authentication, accounts, transactions
* **Payload Models**: Clean separation of data classes
* **Helpers and Utilities**: Streamlined test data generation and environment management
* **Allure Reporting**: Beautiful HTML reports with logs and screenshots (if needed)
* **Environment Variables**: Secure `.env` support for credentials & base URL
* **Parallel Execution**: Gradle parallel test execution

---

## 🗂️ Project Structure

```
java-junit5-financial/
├── java/
│   ├── api/
│   │   └── test/
│   │       ├── AccountsTests.java          # API tests for accounts
│   │       ├── AuthTests.java              # API tests for authentication
│   │       └── TransactionTests.java       # API tests for transactions
│   ├── core/
│   │   ├── BaseTest.java                   # Base test class for setup/teardown
│   │   └── Config.java                     # Configuration (env variables, etc.)
│   ├── models/
│   │   ├── AccountPayload.java             # Account payload model
│   │   └── TransactionPayload.java         # Transaction payload model
│   └── utils/
│       ├── Helpers.java                    # Utility methods
│       └── PayloadGenerator.java           # Dynamic test data generator
├── allure-results/                         # Allure raw results
├── build.gradle                            # Gradle build file
├── .env                                    # API keys, credentials, base URL (ignored by Git)
├── .gitignore
├── settings.gradle
└── README.md
```

---

## 🛠️ Technology Stack

* **Java 17+**: Modern Java features
* **JUnit 5**: Unit & integration testing
* **Gradle**: Build tool & dependency management
* **Allure**: Reporting
* **GitHub Actions**: CI/CD pipeline

---

## 📋 Requirements

* Java 17+
* Gradle 7+
* Allure CLI (optional, for local report generation)
* A modern OS (Windows, macOS, Linux)
* Git

---

## 📥 Installation

1. **Clone** the repository:

   ```bash
   git clone <your-repo-url>
   cd java-junit5-financial
   ```

2. **Configure** your `.env` file in the project root.

---

## ⚙️ Configuration

1. **Create a `.env`** file:

   ```env
   EMAIL=your-user@example.com
   PASSWORD=yourPassword
   BASE_URL=https://barrigareact.wcaquino.me/
   ```

2. Ensure your `build.gradle` is configured for Allure and environment variables.

---

## 🚀 Running Tests

* **Run all tests**:

  ```bash
  ./gradlew clean test
  ```

* **Generate Allure report**:

  ```bash
  ./gradlew allureReport
  ```

* **Serve Allure report**:

  ```bash
  ./gradlew allureServe
  ```

---

## 🔄 CI/CD Pipeline

We use **GitHub Actions**. Key steps:

1. **Checkout** the repository and set up Java
2. **Run** tests with Gradle
3. **Upload** Allure results as an artifact
4. **Publish** a static HTML report via GitHub Pages

```yaml
# .github/workflows/test.yml
name: Java CI

on:
  push:
    branches: [ main ]
  pull_request:

jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - name: Set up JDK
        uses: actions/setup-java@v3
        with:
          distribution: 'temurin'
          java-version: '17'
      - name: Build with Gradle
        run: ./gradlew clean test
      - name: Generate Allure Report
        run: ./gradlew allureReport
      - name: Upload Allure Report
        uses: actions/upload-artifact@v3
        with:
          name: allure-report
          path: build/allure-report
```

---

## 👤 Author

[Josinaldo Junior](https://github.com/josinaldogjunior)

---

## 📄 License

MIT © [Josinaldo Junior](https://github.com/josinaldogjunior)

---
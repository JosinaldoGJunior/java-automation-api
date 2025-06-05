# API Test Automation Framework

A robust test automation framework for a financial management API, built with Java, JUnit 5, and RestAssured. This project demonstrates a professional, scalable, and maintainable approach to API testing, featuring clear design patterns, a full CI/CD pipeline with GitHub Actions, and live reporting with Allure.

<p align="center">
  <a href="https://josinaldogjunior.github.io/java-automation-api/">
    <img src="https://img.shields.io/badge/Allure%20Report-View%20Live-brightgreen.svg" alt="View Live Report">
  </a>
</p>

---

## 📑 Table of Contents

* [Overview](#-overview)
* [Key Features & Design Principles](#-key-features--design-principles)
* [Technology Stack](#-technology-stack)
* [Project Structure](#️-project-structure)
* [Getting Started](#-getting-started)
* [Running Tests & Viewing Reports](#-running-tests--viewing-reports)
* [CI/CD Pipeline](#-cicd-pipeline)


---

## 💭 Overview

This framework is designed to provide comprehensive, reliable, and maintainable automated tests for a RESTful API. The architecture emphasizes a clear separation of concerns, ensuring that the code is easy to read, debug, and scale as the application grows. It serves as a practical demonstration of modern API test automation techniques.
<p align="center">
  <img src="https://img.shields.io/badge/Java-17-blue.svg?logo=java&logoColor=white" alt="Java 17">
  <img src="https://img.shields.io/badge/Gradle-8-blue.svg?logo=gradle" alt="Gradle">
  <img src="https://img.shields.io/badge/JUnit-5-blue.svg?logo=junit5&logoColor=white" alt="JUnit 5">
  <img src="https://img.shields.io/badge/RestAssured-5-orange.svg" alt="Rest Assured">
  <img src="https://img.shields.io/badge/Allure-Report-orange.svg?logo=allure&logoColor=white" alt="Allure Report">
  <a href="https://github.com/josinaldogjunior/java-automation-api/actions/workflows/pipeline.yml">
    <img src="https://github.com/josinaldogjunior/java-automation-api/actions/workflows/pipeline.yml/badge.svg" alt="CI/CD Status">
  </a>
</p>
---

## 🌟 Key Features & Design Principles

This project was built following industry-standard best practices and design patterns to ensure high quality and maintainability.

* **API Client Pattern:** All direct API communication is encapsulated within dedicated `Client` classes (e.g., `AccountsClient`). This abstracts away RestAssured implementation details from the tests, making them cleaner and easier to maintain.
* **Separation of Concerns (SoC):** The framework is organized into distinct layers, each with a single responsibility:
    * `clients`: API communication.
    * `models`: Data structures (Payloads/POJOs).
    * `utils`: Reusable tools for test data (`PayloadGenerator`) and test setup (`TestDataSetup`).
    * `core`: Base framework logic (BaseTest, Configuration, Auth).
    * `test`: The tests themselves, focused purely on validation logic.
* **Arrange-Act-Assert (AAA) Pattern:** Every test strictly follows the AAA structure, making them universally readable and easy to understand. Minimalist, intentional comments (`// Arrange`, `// Act`, `// Assert`) guide the reader.
* **Deterministic Test Data:** The `PayloadGenerator` was refactored to produce predictable and specific data (e.g., `generateNewIncomePayload`), eliminating randomness and creating reliable, deterministic tests.
* **Test Precondition Management:** A dedicated `TestDataSetup` utility provides high-level methods to create necessary preconditions (e.g., `createValidAccountAndGetId()`), keeping the `Arrange` block of tests clean and focused on intent.
* **Centralized Configuration:** All environment-specific details (URLs, credentials) are managed in a `Config` class that reads from environment variables, making the framework portable across local and CI environments.
* **Integrated CI/CD Pipeline:** Automated test execution, reporting, and deployment via GitHub Actions.

---

## 🛠️ Technology Stack

* **Language:** Java 17
* **Build Tool:** Gradle (with Kotlin DSL `build.gradle.kts`)
* **Testing Framework:** JUnit 5
* **API Client & Validation:** RestAssured
* **Reporting:** Allure Framework
* **CI/CD:** GitHub Actions
* **Allure Reporting:** Reports with logs and screenshots (if needed)
* **Environment Variables:** Secure .env support for credentials & base URL
---

## 🗂️ Project Structure

The framework is organized into a clean, scalable structure:

```
java-automation-api/
├── .github/workflows/
│   └── pipeline.yml                    # CI/CD workflow
├── src/test/java/
│   ├── api/test/
│   │   ├── AccountTests.java           # Tests Account
│   │   └── TransactionTests.java       # Tests Transaction
│   ├── clients/
│   │   ├── AccountsClient.java         # API client for the Account
│   │   └── TransactionsClient.java     # API client for the Transaction
│   ├── core/
│   │   ├── BaseTest.java               # Base class with global setup (RestAssured, Clients)
│   │   ├── Config.java                 # Centralized configuration management
│   │   └── TokenManager.java           # Singleton for handling auth tokens
│   ├── models/
│   │   ├── AccountPayload.java         # POJO for Account data
│   │   └── TransactionPayload.java     # POJO for Transaction data
│   └── utils/
│       ├── PayloadGenerator.java       # Deterministic test data factory
│       └── TestDataSetup.java          # Helper for creating test preconditions
├── .env.example                        # Example environment file
├── build.gradle.kts                    # Gradle build script (Kotlin DSL)
└── README.md
```

---

## 🚀 Getting Started

### Prerequisites

* Java JDK 17 or higher.
* Git.

### Installation & Configuration

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/josinaldogjunior/java-automation-api.git](https://github.com/josinaldogjunior/java-automation-api.git)
    cd java-automation-api
    ```
2.  **Set up environment variables:**
    * Create a file named `.env` in the root directory of the project.
    * Copy the contents of `.env.example` (if it exists) or use the template below.
    * Replace the placeholder values with your actual credentials for local execution.

    **`.env` file template:**
    ```env
    # Base URL for the target API
    BASE_URL=[https://barrigarest.wcaquino.me](https://barrigarest.wcaquino.me)

    # Credentials for authentication
    API_USER_EMAIL=your-user@example.com
    API_USER_PASSWORD=your_secret_password
    ```

---

## ⚡ Running Tests & Viewing Reports

All commands should be run from the root directory of the project. The Gradle Wrapper (`./gradlew`) will handle all dependencies.

1.  **Run all tests:**
    ```bash
    ./gradlew clean test
    ```

2.  **Generate and open the Allure report locally:**
    This command will generate the report and open it in your default web browser.
    ```bash
    ./gradlew allureServe
    ```

---

## 🔄 CI/CD Pipeline

This project uses **GitHub Actions** for continuous integration. The workflow is defined in `.github/workflows/pipeline.yml`.

The pipeline automatically performs the following steps on every push or pull request to the `main` branch:
1.  Checks out the code.
2.  Sets up the Java 17 environment.
3.  Runs all tests using Gradle, injecting credentials as GitHub Secrets.
4.  Generates the Allure test report.
5.  **Deploys the report** as a static website to GitHub Pages.

**The latest report is always available live at: [https://josinaldogjunior.github.io/java-automation-api/](https://josinaldogjunior.github.io/java-automation-api/)**

---

## 👤 Author

**Josinaldo Junior**

* **GitHub:** [@josinaldogjunior](https://github.com/josinaldogjunior)

## 📄 License

This project is licensed under the MIT License.
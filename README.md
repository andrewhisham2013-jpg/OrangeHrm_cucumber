# OrangeHrm Cucumber BDD Automation Framework

A robust, enterprise-grade Behavior-Driven Development (BDD) test automation framework built for the **OrangeHRM** web application. This framework leverages **Java**, **Selenium WebDriver**, **Cucumber JVM**, and **TestNG** while following a highly optimized **Page Object Model (POM)** design pattern with centralized data management and state tracking.

---

## 🚀 Key Framework Features

*   **Centralized Data Management:** Integrated global `JsonFileManager` and `CSVFileManager` configurations inside the centralized hook handler. This structure eliminates redundant, local file initializations across separate step definition packages.
*   **Unified State Tracking:** Utilizes a global page context tracking pointer (`HooksHandler.verificationPage`) to seamlessly synchronize UI state transitions across complex multi-step scenarios.
*   **Robust Multi-Browser Support:** Structured with a dedicated wrapper factory (`WebDriverFactory`) capable of launching cross-browser test executions dynamically (Chrome, Firefox, Edge) via external configuration keys.
*   **Automated Allure Report Generation:** Configured with the Maven Surefire plugin and TestNG listener profiles to automatically aggregate test metrics, application logs, and interactive steps upon execution completion.
*   **Execution Video Recording:** Integrated automated screen recording to capture video recordings of test runs. This provides complete end-to-end visual playback for debugging flakiness or reviewing failed scenarios in your CI/CD pipelines.
*   **Smart Diagnostics & Stream Safety:** Implemented automatic driver screenshot captures upon test failure scenarios directly attached to Allure, wrapped in auto-closing try-with-resources blocks to prevent localized directory lockups during CI runs.

---

## 📋 Test Automation Coverage Matrix

The suite delivers complete functional end-to-end BDD coverage across the following platform modules:

### 1. Admin User Management
*   **Scope:** System Administration navigation and platform security controls.
*   **Capabilities:** Validates Admin HR authentication, secure portal routing, system user database filtering, and multi-layered credential checking.

### 2. User Account Editing
*   **Scope:** Dynamic operational modifications for system accounts.
*   **Capabilities:** Automation for searching out specific records within the employee list and executing clean, saved administrative edits or profile role alterations.

### 3. Employee Dependents Validation
*   **Scope:** Verifies the integrity of employee relationship data.
*   **Capabilities:** Automates form fields for creating new emergency contacts, appending dependent entries, updating mobile records, and verifying data tables save accurately after profile re-logging.

### 4. Employee Profile Management
*   **Scope:** Covers core Personal Information Management (PIM) features.
*   **Capabilities:** Maps complete positive and negative regression flows modifying employee nationalities, marital status options, and custom street address profiles.

### 5. Timesheet Log & Validation Matrix
*   **Scope:** Validates the Time tracking modules of the platform.
*   **Capabilities:** Automates hours logging against designated project activity rows, cross-checks daily mathematical time accumulation accuracy, and executes admin filtering and submission approval cycles.

---

## 🛠️ Tech Stack & Core Architecture

*   **Language:** Java
*   **Automation Engine:** Selenium WebDriver
*   **BDD Framework:** Cucumber JVM
*   **Test Runner Engine:** TestNG
*   **Reporting Utility:** Allure Reports & Log4j2
*   **Media Capture:** Automated Video Recorder / Screen Capture Utility
*   **Build Tool:** Maven


🏃 Steps to Run Tests and Generate Allure Reports
Prerequisites
Before running, make sure your local workspace environment is ready:

Ensure Java JDK (v17 or higher) is installed and configured.

Ensure Maven is installed and mapped to your system environment variables.

Install the Allure Command Line Tool to host and serve visual analytics locally.

Step 1: Clean and Execute the Test Suite
Open your preferred terminal profile at the root of the project directory and run the following Maven command to trigger test execution:
Bash
mvn clean test
This command cleans previous build targets, launches the background browsers, runs your feature scenarios through TestNG, records execution videos, captures failure screenshots automatically, and outputs raw metrics into a local allure-results/ folder.

Step 2: Access Video Recordings & Artifacts
Screenshots: Captured dynamically on step failure and attached directly to the Allure Report step node.
Video Playbacks: Saved locally in the configured recordings target folder (e.g., target/recordings/ or videos/), mapping precisely to your Cucumber scenario execution name for deep-dive playback analysis.

Step 3: Generate and Open the Interactive Allure Report
Once the execution completes, run the following command to process the raw output logs into a locally hosted web dashboard:
Bash
allure serve allure-results


Step 4: Optional - Build a Hardened HTML Static Directory
If you want to save a standalone, shareable dashboard folder (ideal for uploading to CI systems, GitHub Actions, or sending to teams), compile it statically:
Bash
allure generate allure-results --clean -o allure-report
To view a previously generated static directory, execute: allure open allure-report
---

## 📂 Framework Directory Structure

```text
OrangeHrm_cucumber/
├── src/
│   ├── main/
│   │   ├── java/org/example/
│   │   │   ├── admin/         # Admin & User Editing Page Objects
│   │   │   ├── myinfo/        # Personal Details & Dependents Page Objects
│   │   │   ├── pages/         # Login, Dashboard, & Core Base Pages
│   │   │   ├── pim/           # Employee List & PIM Search Page Objects
│   │   │   └── time/          # Timesheet Management Page Objects
│   │   └── resources/
│   │       └── config.json    # Global Configuration Properties
│   └── test/
│       ├── java/tests/
│       │   ├── base/          # BaseTest Configuration
│       │   ├── hooks/         # Centralized HooksHandler & State Managers
│       │   ├── reuse/         # WebDriverFactory, JSON/CSV/Screenshot/Video Utilities
│       │   └── [modules]/     # Decoupled Step Definition Packages
│       └── resources/
│           ├── features/      # Cucumber .feature files 
│           └── testData/      # Data-Driven CSV profiles
├── pom.xml                    # Maven Configuration File
└── testng.xml                 # TestNG Runner Suite Configuration







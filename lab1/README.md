# CS489 Lab 1 — Applied Software Development

This folder contains the work for **Lab 1** (April 2026): environment setup and a small Java CLI for product management.

## What was completed

### Task 1 — Java JDK

Installed and configured the Java Development Kit (JDK 21+). Verified the compiler with `javac -version`. Evidence is in `screenshots/Installment Screenshot - JAVA and Git.png`.

### Task 2 — IDE

Set up **IntelliJ IDEA** (recommended in the lab) for Java development. The project metadata is under `.idea/`.

### Task 3 — Git

Installed and configured **Git** (2.53.0+). Verified with `git --version`. The same screenshot as Task 1 shows the Git version alongside Java.

### Task 4 — Products CLI application

Built a command-line application that loads the sample products from the lab and prints them in **JSON**, **XML**, and **CSV** formats. Products are sorted by **name** (ascending), then by **unit price** (descending).

- **Model:** `src/edu/miu/cs/cs489appsd/lab1/productmgmtapp/model/Product.java` — product fields, three constructors, getters and setters.
- **Entry point:** `src/edu/miu/cs/cs489appsd/lab1/productmgmtapp/ProductMgmtApp.java` — `main` and `printProducts(...)`.

Console output screenshots: `screenshots/Output Screenshot - JSON.png`, `Output Screenshot - XML.png`, and `Output Screenshot - CSV.png`.

## How to run (from this `lab1` folder)

Simply open the project in IntelliJ and run `ProductMgmtApp`.

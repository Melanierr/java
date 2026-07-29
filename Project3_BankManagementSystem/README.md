# 🏦 Project 3 — Bank Management System

A console-based **Bank Management System** written in **Java** as part of my Java learning progression.

This project focuses on managing bank accounts, handling transactions, and persisting account data between program runs. Compared to my earlier CRUD projects, this one introduced **inheritance, account management logic, and file-based persistence**.

---

## Features

* Create new bank accounts
* Automatically generate unique account numbers
* Support multiple account types (`CheckingAccount`, `SavingsAccount`)
* Deposit money
* Withdraw money with balance validation
* Transfer money between accounts
* Search accounts by account number
* View all registered accounts
* Save and load account data from a text file

---

## Project Structure

```text
src/
├── BankAccount.java
├── CheckingAccount.java
├── SavingsAccount.java
├── BankManager.java
├── AccountsSaver.java
└── Main.java
```

---

## Example Usage

```text
=== Bank Menu ===
1. Create account
2. Deposit
3. Withdraw
4. Transfer
5. Search account
6. View all accounts
7. Save
8. Load
0. Exit
```

---

## Example Save File

```text
Name:Musc
Account number:001
Balance:1500.0

Name:Alex
Account number:002
Balance:820.5

ID:3
```

The final `ID` value is used to continue generating unique account numbers after loading saved data.

---

## What I implemented lol

This project helped me practice:

* **Inheritance** and subclassing
* **Object-oriented design**
* Managing collections with `ArrayList`
* Searching and updating objects in memory
* Validating user input and account balances
* File I/O using `BufferedReader` and `FileWriter`
* Keeping application state persistent across program restarts

---

## How to Run

1. Open the project in **IntelliJ IDEA** or another Java IDE.
2. Run `Main.java`.
3. Use the console menu to create accounts and perform transactions.

---

## Notes

This is a **learning project**, so my focus was on implementing the core banking workflow rather than security, encryption, or database storage. The save system uses a simple text-file format to make the persistence logic easier to understand and debug while learning Java.

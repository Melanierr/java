# 📚 Project 4 — Library Management System

A console-based Library Management System written in **Java** as part of my Java learning journey.

This project started as a simple CRUD application, but it gradually evolved into a small system that manages **books, members, and active loans** while keeping their relationships synchronized in memory and after loading from a save file.

---

## Features

* Add, view, search, and delete books
* Add, view, search, and delete members
* Borrow and return books
* Prevent borrowing unavailable books
* Limit the number of books a member can borrow
* Save and load the entire library database from a text file
* Restore **book availability**, **member loan history**, and **active loans** correctly after restarting the program

---

## Project Structure

```text
src/
├── Class/
│   ├── Book.java
│   ├── Member.java
│   ├── Loan.java
│   ├── LibraryManager.java
│   ├── DataSaver.java
│   ├── ISBNGenerator.java
│   └── Main.java
```

---

## What I basically have implemented ngl

This project was mainly focused on **object relationships and state management**, including:

* OOP design
* Composition
* Managing many-to-many relationships through a `Loan` object
* `ArrayList` operations
* File I/O with `BufferedReader` and `FileWriter`
* Reconstructing object references when loading saved data
* Keeping multiple parts of the system synchronized (`Book ↔ Loan ↔ Member`)

---

## Example Save File

```text
==BOOKS==
Dune,Frank Herbert,9780441172719,1965,false

==MEMBERS==
Musc,18,2026-07-29

==LOANS==
Musc,9780441172719,2026-07-29
```

---

## How to Run

1. Open the project in **IntelliJ IDEA** (or any Java IDE).
2. Compile and run `Main.java`.
3. Use the console menu to manage books, members, and loans.

---

## Notes

This is a **learning project**, so the focus was not on GUI design or databases. My goal was to build a working console application while improving my understanding of **Java, OOP, and persistence logic** through multiple iterations and bug fixes.

One of the biggest challenges was correctly restoring **loan relationships and book availability after loading saved data**, which required reconnecting `Book`, `Member`, and `Loan` objects instead of treating them as independent records.


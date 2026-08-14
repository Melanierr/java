# Contact Book

A console-based Contact Book application written in Java. This project is part of my Java OOP project series and focuses on using a `HashMap` for efficient contact lookup, CRUD operations, sorting, and file persistence.

## features

- Add new contacts
- Search contacts by name
- Search contacts by phone number
- Delete contacts by phone number
- Edit existing contacts
- View all contacts
- View contacts alphabetically by name
- Save contacts to a text file
- Load contacts from a text file
- Preserve each contact's creation date

## Data Structure

The main contact collection uses:

```java
HashMap<String, Contact>
```

The phone number is used as the key:

```text
Phone Number -> Contact
```

This allows direct phone-number lookup and provides a simple way to enforce unique phone numbers.

When alphabetical ordering is needed, the values from the `HashMap` are copied into a temporary `ArrayList` and sorted by contact name. The original `HashMap` is not modified.

## Project Structure

```text
src/
├── Contact.java
├── ContactBookManager.java
├── Saver.java
└── Main.java
```

### `Contact`
Represents an individual contact.

Stores:
- Name
- Email
- Phone number
- Creation date

### `ContactBookManager`
Handles the contact book's main operations:

- Add
- Search
- Delete
- Update
- View contacts

### `Saver`
Handles saving and loading contacts from `savefile.txt`.

### `Main`
Provides the console menu and handles user input.

## Persistence

Contacts are stored in a simple comma-separated text format:

```text
name,email,phoneNumber,creationDate
```

Example:

```text
John Doe,john@example.com,0123456789,2026-08-14
```

The file is recreated when saving, and contacts are reconstructed as `Contact` objects when loading.

## concepts

- Object-Oriented Programming
- Encapsulation
- Classes and objects
- `HashMap`
- CRUD operations
- Searching and filtering
- Sorting collections
- `ArrayList`
- `LocalDate`
- Varargs
- File I/O
- Exception handling
- Serialization/deserialization logic
- Separation between UI and business logic
- Maintaining consistency between a `HashMap` key and an object's identifier

## flaws lowk

This is a V1 console project, so it intentionally keeps the design simple.

Current limitations include:

- Names are not unique, so searching by name returns one matching contact.
- The save format uses commas as separators, so commas inside contact fields are not supported.
- The application has no database or GUI.
- Input validation is basic.

## i make this because erm

The goal of this project was not to build a production-ready contact application, but to practice designing a small Java application around a `HashMap` and to build the CRUD and persistence logic without relying on tutorial code.

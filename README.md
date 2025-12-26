# 🧾 User Management System

A robust, console-based Java application for managing user records with full CRUD (Create, Read, Update, Delete) operations, data validation, sorting, and file export capabilities.

---

## 📌 Project Description

The **User Management System** is a command-line application built in Java that enables administrators to maintain a dynamic list of user profiles. Each user is uniquely identified by an ID and associated with a name and email address. The system enforces data integrity by preventing duplicate IDs and email addresses, supports real-time sorting by name or ID, and allows exporting the current user list to a CSV file for external use.

Designed with modularity and clean code principles, this project demonstrates core object-oriented programming (OOP) concepts, exception handling, file I/O, and separation of concerns—making it an excellent reference for Java best practices in small-scale applications.

---

## ✨ Features

| Feature | Description |
|--------|-------------|
| **Add User** | Register a new user with a unique ID, name, and email. System validates for duplicate IDs and emails before insertion. |
| **View All Users** | Display the complete list of users in a numbered, human-readable format. |
| **Search User by ID** | Retrieve and display a specific user record using their unique ID. Throws `UserNotFoundException` if not found. |
| **Update User** | Modify the name and email of an existing user by ID. Ensures the new email is not already in use by another user. |
| **Delete User** | Remove a user from the system by ID with confirmation of successful deletion. |
| **Sort by Name** | Reorder the internal user list alphabetically by name (case-insensitive). |
| **Sort by ID** | Reorder the internal user list numerically by user ID (ascending). |
| **Export to CSV** | Manually export the current list of users (in its current order) to a user-specified CSV file with headers (`ID,Name,Email`). |
| **Input Validation** | Robust input handling with proper consumption of newline characters to prevent scanner anomalies. |
| **Error Handling** | Custom checked exception (`UserNotFoundException`) for missing records; I/O errors are caught and reported during file export. |

---

## 💡 Java Concepts & Technologies Used

This project leverages a wide range of fundamental and intermediate Java concepts, demonstrating industry-aligned coding practices:

### 1. **Object-Oriented Programming (OOP)**
- **Encapsulation**: All fields in the `User` class are `private` with public getter/setter methods.
- **Abstraction**: Business logic is abstracted into the `UserService` layer; the `Main` class acts only as a controller.
- **Class Design**: The `User` class includes proper `equals()`, `hashCode()`, and `toString()` overrides for correct behavior in collections and debugging.

### 2. **Exception Handling**
- **Custom Checked Exception**: `UserNotFoundException` extends `Exception`, forcing callers to handle or declare it—ensuring robust error propagation.
- **Try-with-Resources**: Used in file export (`PrintWriter`) to guarantee automatic resource cleanup and prevent file handle leaks.
- **Graceful Degradation**: Errors (e.g., missing user, I/O failure) are caught and displayed to the user without crashing the application.

### 3. **Collections Framework**
- **`ArrayList`**: Used internally in `UserService` to store user records dynamically.
- **Enhanced For-Loops & Iteration**: Used for searching, validation, and printing.
- **Sorting with `Comparator`**: Utilizes `Comparator.comparing()` and `Comparator.comparingInt()` for clean, functional-style sorting by name (case-insensitive) or ID.

### 4. **File I/O (Input/Output)**
- **Text File Writing**: Export functionality uses `PrintWriter` wrapped around `FileWriter` for formatted, readable output.
- **CSV Format**: Standard comma-separated values with headers for interoperability with spreadsheets and databases.
- **Charset Awareness**: While not explicitly specified, the system uses the platform default charset—can be extended to enforce UTF-8 if needed.

### 5. **Java Standard Library Utilization**
- **`java.util.Scanner`**: For interactive console input with proper handling of mixed data types (`int` + `String`).
- **`java.util.Objects`**: Used in `hashCode()` for safe, null-tolerant hashing.
- **`java.lang.String` Methods**: `equalsIgnoreCase()` ensures email uniqueness is case-insensitive (e.g., `JOHN@EXAMPLE.COM` ≡ `john@example.com`).

### 6. **Code Quality & Maintainability**
- **Separation of Concerns**: 
  - `Main`: Handles user interaction and flow control.
  - `UserService`: Encapsulates all business logic.
  - `User`: Pure data model with no logic leakage.
  - `UserNotFoundException`: Dedicated error type.
- **DRY Principle**: Reuses `getUserById()` in `updateUser()` and `deleteUser()` to avoid redundant search logic.
- **Meaningful Naming**: Clear method and variable names improve readability (e.g., `sortUsersByName`, `exportUsersToFile`).

### 7. **User Experience (UX) Considerations**
- Clear menu-driven interface with numbered options.
- Informative success/error messages.
- Default filename (`users.csv`) if user provides empty input.
- Automatic newlines and formatting in output for readability.

---

## 🚀 Getting Started

1. Clone or download the project.
2. Compile all Java files (ensure package structure: `com/app/model`, `com/app/service`, `com/app/exception`).
3. Run the `Main` class:
   ```bash
   java Main
   ```
4. Follow on-screen prompts to manage users.

> **Note**: The application runs indefinitely until the user selects "Exit".

---

## 📐 System Architecture

The following class diagram shows the relationship between the core components of the system:
```
+----------------------------+
|         <<Exception>>      |
|   UserNotFoundException    |
+----------------------------+
| + UserNotFoundException(    |
|     message: String)       |
+----------------------------+

+----------------------------+
|          User              |
+----------------------------+
| - id: int                  |
| - name: String             |
| - email: String            |
+----------------------------+
| + User(id, name, email)    |
| + getId(): int             |
| + getName(): String        |
| + getEmail(): String       |
| + setName(name): void      |
| + setEmail(email): void    |
| + equals(obj): boolean     |
| + hashCode(): int          |
| + toString(): String       |
+----------------------------+

+----------------------------+
|        UserService         |
+----------------------------+
| - users: List<User>        |
+----------------------------+
| + addUser(user): void      |
| + getAllUsers(): void      |
| + getUserById(id): User    |
| + updateUser(id, name,     |
|     email): void           |
| + deleteUser(id): void     |
| + sortUsersByName(): void  |
| + sortUsersById(): void    |
| + exportUsersToFile(       |
|     filename): void        |
+----------------------------+
          ▲
          │ uses
          │
+----------------------------+
|          Main              |
+----------------------------+
| + main(args: String[]): void|
+----------------------------+

The following textual class diagram represents the core structure of the application and shows how the main components interact with each other.

```

## 📁 Project Structure

```
src/
├── Main.java
├── com/
│   ├── app/
│   │   ├── model/
│   │   │   └── User.java
│   │   ├── service/
│   │   │   └── UserService.java
│   │   └── exception/
│   │       └── UserNotFoundException.java
```

---

## 📝 License

This project is open-source and available for educational and non-commercial use.

---

> ✨ **Built with Java | Clean Code | OOP Principles | Real-World Practices**  
> Ideal for students, junior developers, or as a foundation for more advanced user management systems.

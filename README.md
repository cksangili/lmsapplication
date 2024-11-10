
# Library Management System

This is a command-line Library Management System built using Java. The system supports typical library operations such as registering admins and users, logging in, borrowing and returning books, and viewing library inventory. 

## Table of Contents
- [Project Description](#project-description)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Setup and Installation](#setup-and-installation)
- [Usage](#usage)
- [Command Reference](#command-reference)
- [Testing](#testing)
- [Future Enhancements](#future-enhancements)

---

## Project Description

The Library Management System is designed to manage books and users in a library. It allows admins to add and remove books, manage user accounts, and view library inventory. Users can search for books, borrow them, and return them once they are finished. All operations are managed through a command-line interface.

## Features

- **Admin and User Registration**: Register new admins and users.
- **User Login**: Log in as an admin or a user.
- **Book Management**:
  - Admins can add or remove books by title, author, and ISBN.
  - Users can view available books.
- **Borrow and Return Books**: Users can borrow available books and return borrowed books.
- **Command-Line Interface**: The system operates entirely from the command line.

## Technologies Used

- **Java**: Core language for application logic.
- **JUnit**: For testing application functionality.
- **SystemLambda**: For capturing and testing `System.out` output during tests (optional).

---

## Setup and Installation

### Prerequisites
- **Java JDK** (version 8 or higher)
- **Maven** (for JUnit testing suite dependency management and build automation)

### Installation

1. **Clone the Repository**:
   ```bash
   git clone -b master https://github.com/cksangili/lmsapplication.git
   cd lmsapplication
   ```

2. **Build the Project**:
   Use Maven to compile the project:
   ```bash
   mvn clean compile
   ```

3. **Run the Program**:
   After building, run the program by passing commands directly:
   ```bash
   java -cp target/classes com.lms.app.entry.LibraryManagementSystem <command>
   ```
3. **Run the JAR Program**:
   Download LMSApplication.jar from the root path of GitHub repository and run the JAR by passing commands directly:
   ```bash
   java -jar LMSApplication.jar <commands>
   Eg. commands: register user John password1 or login John password1
   ```
---

## Usage

### Run Commands
Run the program using:
```bash
java -cp target/classes com.lms.app.entry.LibraryManagementSystem <command> <arguments>
```

Replace `<command>` and `<arguments>` with specific commands and arguments as outlined in the [Command Reference](#command-reference).

### Example Commands

1. **Register an Admin**:
   ```bash
   java -cp target/classes com.lms.app.entry.LibraryManagementSystem register admin Alice password1
   ```

2. **Login as a User**:
   ```bash
   java -cp target/classes com.lms.app.entry.LibraryManagementSystem login user Bob password123
   ```

3. **Borrow a Book**:
   ```bash
   java -cp target/classes com.lms.app.entry.LibraryManagementSystem borrow Bob 1234567890
   ```

---

## Command Reference

### User and Admin Management
| Command                             | Description                                  |
|-------------------------------------|----------------------------------------------|
| `register admin <name> <password>`  | Registers a new admin.                       |
| `register user <name> <password>`   | Registers a new user.                        |
| `login admin <name> <password>`     | Logs in an admin.                            |
| `login user <name> <password>`      | Logs in a user.                              |

### Book Management
| Command                             | Description                                  |
|-------------------------------------|----------------------------------------------|
| `addBook <title> <author> <ISBN>`   | Adds a new book to the library (admin only). |
| `removeBook <ISBN>`                 | Removes a book from the library (admin only).|
| `borrow <user> <ISBN>`              | User borrows a book by ISBN.                 |
| `return <user> <ISBN>`              | User returns a borrowed book by ISBN.        |

### Inventory
| Command           | Description                         |
|-------------------|-------------------------------------|
| `viewBooks`       | View all available books in library.|

---

## Testing

The project includes unit tests to verify the functionality of core features. Tests are located in the `src/test/java` directory.

### Running Tests

1. **Compile and Run All Tests**:
   ```bash
   mvn test
   ```

2. **Test Output Capture**:
   The application uses `System.out` for displaying command results. Tests capture `System.out` output and verify expected outputs using `SystemLambda` for ease of testing.

### Example Test

Here's a sample JUnit test for the `register` command:
```java
@Test
public void testRegisterAdmin() throws Exception {
    String[] args = {"register", "user", "John", "password1"};
    String output = tapSystemOut(() -> {
        LibraryManagementSystem.main(args);
    });
    assertTrue(output.contains("Admin Alice registered successfully"));
}
```

---

## Future Enhancements

- **Persistent Storage**: Implement a database for storing users and books, allowing data to persist between program sessions.
- **User Permissions**: Enhance permission checks for more secure role management.
- **Graphical User Interface (GUI)**: Add a GUI to make the system more user-friendly.

---

## License

NA

---



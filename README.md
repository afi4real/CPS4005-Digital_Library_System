*** St Mary’s Digital Library System ***
---This is a simple Java desktop application I built for my BSc Computer Science Object Oriented Programming module.
   The project was to create a digital library management system where someone can manage books, members, and borrowing records.
   The project's focus was mainly on understanding Java, Swing, and SQLite.---

### Features ###
---Books---
   Add, update, delete books
   Search by title, author or category
   Shows availability status

---Members---
   Add, update, delete members
   Search by name

---Borrow Records---
   Add new borrow entries
   Update or delete records
   Search by book ID, member ID, or dates
   Return the book

---Technologies Used---
   Java (JDK 17)
   Java Swing for the GUI
   SQLite as the database
   JDBC for database connection
   VS Code as the IDE

### Project Structure ###
---Code---
LIBRARYSYSTEM/
   src/
    ├── gui/                → All GUI code (LibraryAppGUI)
    ├── dao/                → Data Access Objects (BookDAO, MemberDAO, BorrowRecordDAO)
    ├── model/              → Model classes (Book, Member, BorrowRecord)
    ├── db/                 → DatabaseConnection.java
    └── Main.java           → Entry point
    db/
    └── library.db          → SQLite database
    lib/
    └── sqlite-jdbc.jar     → JDBC driver(sqlite-jdbc-3.51.3.0.jar)
    README.md               → Project Brief


### How to Run the Project ###
    Make sure you have Java 17 or above installed.
    Open the project folder in VS Code.
    Make sure the SQLite JDBC driver is inside the lib/ folder.
    Run the Main.java file. (Code Runner might show some error, it is better to use the vscode internal run and debug)
    The GUI should open automatically.

### Future Improvements (Can be done later) ####
    Add user login (admin/staff)
    Add validation for empty fields and overdue book highlighting
    Improve the UI layout

### UML Class Diagram ###
    (docs/library_uml_diagram.png)
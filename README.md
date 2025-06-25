# StudyPlannerApp

A Java **console-based** application that allows users to manage and organize their study tasks.  
This project was developed as a learning exercise to practice key **Object-Oriented Programming (OOP)** principles in Java, including:
- Class hierarchy and design
- Inheritance and abstract classes
- Polymorphism via method overriding and interface usage
- Encapsulation through mutators/accessors
- Comparator-based sorting and grouping
- Console-based interaction using loops and conditionals

---

## 📜 Project Purpose

The goal of this project was to build a functional task planner to reinforce OOP concepts while learning how to structure a multi-class Java application.  
The program supports dynamic interaction through the terminal and showcases the use of abstract classes, interfaces, and comparators for extensibility and maintainability.

---

## ✏️ Features

- Add different types of study tasks:
  - **Revision** (e.g., textbook or lecture material)
  - **Quiz** (with question types like multiple-choice or written)
  - **Assignment** (with prerequisite task information)
- View tasks:
  - Group by type, status, or week
- Update or delete existing tasks
- Console-based menu navigation
- Sorting and grouping via `Comparator` and interface polymorphism

---

## 🏛 Key Class Roles & Architecture

### ✅ `StudyPlanner` (Main entry point)
- Contains the `main()` method
- Initializes the system and delegates interaction to `Menu`
- Acts as the **controller** to start the application

### ✅ `Menu` (User interaction)
- Provides the menu-driven user interface
- Accepts user input and triggers task creation, update, view, and delete operations

### ✅ `Task` (Abstract Parent Class)
- Defines core task attributes: `taskName`, `status`, `priority`, `week`
- Contains abstract method(s) like `toString()` to be implemented by subclasses
- Promotes **code reuse** and **enforces a common structure** for all tasks

### ✅ `Revision`, `Quiz`, `Assignment` (Concrete Subclasses)
- Extend `Task` and add specific attributes:
  - `Revision` → `materialType`
  - `Quiz` → `questionType`
  - `Assignment` → `requiredTask`
- Override `toString()` to return detailed descriptions
- Demonstrates **inheritance** and **polymorphism**

### ✅ `ListManager` (Task management logic)
- Uses `ArrayList<Task>` to store and manage all tasks
- Contains methods for adding, updating, removing, and listing tasks
- Applies **interface-based strategies** and **Comparator** implementations to support:
  - `groupByType()`, `groupByStatus()`, `groupByWeek()`
- Delegates sorting responsibility via **custom comparators**

### ✅ Interfaces

#### `Groupable`
- Defines a contract for task grouping strategy
- Implemented by different classes to define how tasks are grouped (e.g., by week, status)

#### `Viewable`
- Defines a display contract for how tasks should be listed
- Promotes **loose coupling** between UI and logic layers

---

## 🧠 Abstract Class & Interface Design

This project helped explore the **difference and collaboration between abstract classes and interfaces**:

- `Task` is an **abstract class** → it provides partial implementation and enforces consistent structure for all task types.
- `Groupable` and `Viewable` are **interfaces** → they allow ListManager to use polymorphic behavior when grouping or viewing tasks.
- These were used together to build a flexible and extensible task management system that supports new task types or grouping strategies with minimal changes.

---

## 🔄 Program Flow

1. App starts in `StudyPlanner` and initializes the menu
2. User is presented with choices: Add, View, Update, or Delete tasks
3. Upon selection:
   - If **Add**, user selects task type and provides necessary details
   - If **View**, user chooses how to group/sort tasks
   - If **Update/Delete**, user selects task from list
4. Menu continues in a loop until user chooses to exit

---

## 🌳 Inheritance Hierarchy

Task (abstract) <br />
├── Revision <br />
├── Quiz <br />
└── Assignment

---

## 💻 How to Run

1. Compile Java files:
   ```bash
   javac src/*.java
   ```
2. Run the main program:
   ```bash
   java -cp src StudyPlanner
   ```
    >☝️ Ensure the working directory is set to your project folder containing src/.
   

## 🌟 Learning Outcomes
- Through this project, the following Java concepts were practiced and applied:
- Designing and using abstract classes
- Applying inheritance and polymorphism
Implementing and using interfaces for flexibility and modularity
- Creating custom Comparator classes to group and sort data dynamically
- Managing objects with ArrayList
- Building a console-based menu-driven user interface
- Applying control flow with if, switch, while, and method abstraction


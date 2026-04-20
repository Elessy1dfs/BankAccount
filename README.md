# Bank Account Management System

A Java-based banking application demonstrating OOP principles (Inheritance, Abstraction, Interfaces, and Encapsulation).

Features
-Abstract Design:** Uses a base `BankAccount` class to manage shared attributes like name, PIN, and balance.
-Transactional Interface:** Implements a `Transactional` interface to standardize deposit and withdrawal behaviors.
-Account Variety: Supports multiple account types, including `SavingsAccount` and `CheckingAccount`, each with specific logic.
-Security: Includes a PIN validation mechanism for secure withdrawals.
Handles input errors via try-catch.


- Language:Java
- Concepts: OOP (Abstraction, Polymorphism, Inheritance, Encapsulation)

📂 Project Structure
Transactional.java: The interface contract.
BankAccount.java: The abstract template.
SavingsAccount.java / CheckingAccount.java: Specific logic.
Main.java: The interactive console menu.

💻 How to Run
1. Clone the repository.
2. Compile the Java files:
   ```bash
   javac *.java

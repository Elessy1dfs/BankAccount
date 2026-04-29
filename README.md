# Bank Account System

A Java-based application for managing Savings and Checking accounts with secure transaction handling and history tracking. It works like a basic ATM where users can create an account, deposit money, and withdraw funds when needed. We built it to show how a real bank handles different types of accounts while keeping your money safe.


## Features

**Dual Account Support**  
Supports both Savings and Checking accounts.

**Secure Transactions**  
All withdrawals require PIN verification to ensure transactions are authorized.

**History Tracking**  
Every deposit and withdrawal is recorded so users can review their transaction history any time.

**Input Validation**  
Uses try-catch blocks to prevent crashes and handle invalid input properly.

## Project Structure

- **Main.java**  
Handles the main program flow and provides the menu interface for the user.

- **BankSystem.java**  
Manages all accounts, including storing and searching for accounts.

- **BankAccount**  
An abstract class that contains shared attributes and methods for all account types.

- **SavingsAccount.java / CheckingAccount.java**  
Classes that define specific behavior for each account type.

- **TransactionHistory.java**  
Responsible for storing and retrieving transaction records.

- **Transactional.java**  
An interface that defines the basic banking operations.

## Diagrams

**Class Diagram**   
![Class Diagram](Class%20Diagram.drawio.png)

**Use Case Diagram**  


## Installation & Setup

**Clone the repository:**

git clone https://github.com/Elessy1dfs/BankAccount.git


**Navigate to the project folder:**

cd BankAccount/src/bank


## Usage

**Compile the program:**

javac *.java


**Run the program:**

java Main


**Run the system:**  
Follow the instructions shown in the console to create an account and perform transactions.

   

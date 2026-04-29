# Bank Account System

A Java-based application for managing Savings and Checking accounts with secure transaction handling and history tracking.


## Features

**Dual Account Support**  
Supports both Savings and Checking accounts.

**Secure Transactions**  
All withdrawals require PIN verification to make sure transactions are authorized.

**History Tracking**  
Every deposit and withdrawal is recorded so users can review their transaction history anytime.

**Input Validation**  
Uses try-catch blocks to prevent crashes and handle invalid inputs properly.

## Project Structure

- **Main.java**  
Handles the main program flow and provides the menu interface for theuser.

- **BankSystem.java**  
Manages all accounts, including storing and searching for them.

- **BankAccount**  
An abstract class that contains shared attributes and methods for all account types.

- **SavinsgAccount.java / CheckingAccount.jva**  
Classes that define specific behavior for each account type

- **TransactionHistory.java**  
Responsible for storing and retrieving transaction records.

- **Transactional.java**  
An interface that defines the basic bankin operations.

## Diagrams

**Class Diagram**   
![Class Diagram](Class%20Diagram.drawio.png)



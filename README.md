# Bank Account System

A Java-based banking application demonstrating OOP principles (Inheritance, Abstraction, Interfaces, and Encapsulation).

   Features
-Abstract Design: Uses a base `BankAccount` class to manage shared attributes like name, PIN, and balance.
-Transactional Interface: Implements a `Transactional` interface to standardize deposit and withdrawal behaviors.
-Account Variety: Supports multiple account types, including `SavingsAccount` and `CheckingAccount`, each with specific logic.
-Security: Includes a PIN validation mechanism for secure withdrawals.
-Handles input errors via try-catch.

- Language:Java
- Concepts: OOP (Abstraction, Polymorphism, Inheritance, Encapsulation)

   📂 Project Structure
Transactional.java: The interface contract.
BankAccount.java: The abstract template.
SavingsAccount.java / CheckingAccount.java: Specific logic.
Main.java: The interactive console menu.


               'Class Diagram'
1. Interface: Transactional
Methods:
+deposit(amount: double) : void
+withdraw(amount: double, inputPin: String) : void

2. Abstract Class: BankAccount
Implements: Transactional
                
Fields:                                                   Methods:
-name: String (Private)                                   +BankAccount(name: String, balance: double, pin: String) (Constructor)
-pin: String (Private)                                    +getName() : String
#balance: double (Protected)                              +getBalance() : double
                                                          +validatePin(inputPin: String) : boolean
                                                          +displayAccountType() : void

3.Subclass: SavingsAccount
Inherits from: BankAccount
   Methods:
+SavingsAccount(name: String, balance: double, pin: String) (Constructor)
+deposit(amount: double) : void
+withdraw(amount: double, inputPin: String) : void
+displayAccountType() : void


4.Subclass: CheckingAccount
Inherits from: BankAccount

   Methods:
+CheckingAccount(name: String, balance: double, pin: String) (Constructor)
+deposit(amount: double) : void
+withdraw(amount: double, inputPin: String) : void
+displayAccountType() : void

5. Class: Main
   Methods:
+main(args: String[]) : void

         'UseCase Diagram'
The diagram identifies five primary actions the User can perform:

Create Account
Relationship: <<include>> → Select Account Type
Meaning: Every time a user creates an account, they must go through the step of selecting whether it is a Savings or Checking account.

Deposit Money
A direct action initiated by the User.
Withdraw Money
Relationship: <<include>> → Validate PIN

Meaning: 
The system must check the user's PIN before the withdrawal can be completed. You cannot have a withdrawal without validation.
Check Balance
A direct action initiated by the User to view their current funds.
Exit
The action to close or log out of the system.

💻 How to Run
1. Clone the repository.
2. Compile the Java files:
   ```bash
   javac *.java

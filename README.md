# Trade-account-management-system
A simple Java program to manage Cash and Margin accounts and apply transactions from text files. This project demonstrates object-oriented principles and a service/repository architecture for account management.
Structure

Main.java – Entry point of the application. Loads accounts, applies transactions, and displays results.

pojo/ – Contains Java classes for CashAccount and MarginAccount.

repository/ – Contains TradeAccountRepository for storing and managing accounts.

service/ – Contains CashAccountService and MarginAccountService for account logic.

data/ – Contains text files:

accounts.txt – List of accounts with initial balances.

transactions.txt – List of transactions (DEPOSIT or WITHDRAWAL).

Features

Load Accounts

Reads accounts from accounts.txt and creates CashAccount or MarginAccount objects.

Apply Transactions

Reads transactions from transactions.txt and executes DEPOSIT or WITHDRAWAL on the corresponding accounts.

Display Results

Prints the final balances of selected accounts in the console.

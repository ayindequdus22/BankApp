This document functions as a manual for the Java GUI Bank Application, detailing its functionalities, components, and design principles.

The application emulates fundamental banking operations and offers a user-friendly interface for account management.

Target Audience:

This documentation is aimed at individuals possessing a basic comprehension of computer software and graphical user interfaces (GUIs).

Purpose of the Document:

The purpose of this guide is to empower users with the necessary information to efficiently navigate the application. It elucidates the various features and provides guidance on interaction.

Documentation

Components:

1.Login Window:

The login window serves as the entry point for users to access the bank application. It consists of the following components:

i. Username Field: Allows users to input their username.

ii. Password Field: Allows users to input their password securely.

iii. Login Button: Initiates the login process.

2. Main Window (after successful login):

Once users successfully login, they are presented with the main window, which houses the core functionalities of the application. It includes:

- Buttons for Core Functionalities:

1. Deposit Funds

2. Withdraw Funds

3. Transfer Funds

4. View Transaction History

5. Logout Button

Functionality:

User Authentication:

Users can securely log in using their username and password.

Core Functionalities:

After successful authentication, users can access various features:

Deposit Funds : Allows users to add funds to their account by inputting the desired amount.

Withdraw Funds: Enables users to withdraw funds from their account after validating the available balance.

Transfer Funds (Optional): Facilitates transferring funds to another account by providing recipient account information and the amount to transfer.

View Transaction History: Allows users to view their transaction history.

Logout Button: Safely terminates the session and logs the user out of the application.

Design Considerations:

User Interface:

The graphical user interface (GUI) is designed to be intuitive and user-friendly, featuring clear labels and instructions to guide users through the application.

Input Validation:

Input validation is implemented for both deposit and withdrawal functionalities to ensure that only non-negative numbers are accepted.

Error Handling:

The application includes robust error handling mechanisms to manage scenarios such as invalid login attempts or insufficient funds during withdrawal.

This documentation provides an overview of the components, functionality, and design considerations of the Java GUI Bank Application, ensuring users have a seamless and secure banking experience.

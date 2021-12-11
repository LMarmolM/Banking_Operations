# Welcome to the banking console application project
> This project is about creating a bank application that has users, customers, and employees. 
> The main technologies are SQL and Java, with JDBC taking care of the communication between them



## Requirements
USER
* Users must be able to register
* Users must be able to login and request a banking account
* From the banking account they must be able to request general information about their account as well as have basic deposit, withdraw, and transfer management capacities. 
* Users can accept or refund a transfer from another user. 

EMPLOYEE
* Employees must be able to login
* Employees must be able to see the user information
* Employees must be able to approve or reject banking account requests from customers
* Employees must be able to search by user

DATABASE
* The database must keep the information safely
* The database must be consistent with withdraws, deposits and transfers, so as to alter the total or new total amount of funds

CONSOLE APP
* The console has a navigation menu that does not allow for inputs that break the program or cause unintended functionality.
* The console must be readable for a user and provide several functional menus
* The console must receive exclusively Scanner input. 

## Usage
> Once the app is downloaded and the corresponding packages imported for JDBC, the program runs from main.java and it ends whenever manually stopped or when the exit option is selected.
> Essentially, one must make a username and password, then request a banking account. After an employee logs in and accepts the request, you are able to withdraw, deposit, transfer or check the account information.
> You must know the username of another user to transfer, as customers may not view any of the information of another customer. 

# CustomerManagement

A Web App to manage and perform crud operation on customerlist 

Repository Tree Structure

Here's a general structure based on typical Java web applications with JSP, Servlets, and DAO patterns:








Detailed Documentation

Repository Overview

The CustomerManagement repository is a Java web application that uses JSP, Servlets, and the DAO pattern to manage customer information. It includes functionalities such as user login, customer list management, and CRUD operations for customers.


Project Structure

src/main/java/com/yourpackage/dao


CustomerDAO.java: Defines the interface for CRUD operations on customer entities.

CustomerDAOImp.java: Implements the CustomerDAO interface with actual database operations.



src/main/java/com/yourpackage/model


Customer.java: Defines the Customer entity with fields like uuid, firstName, lastName, address, etc.


src/main/java/com/yourpackage/service



CustomerService.java: Provides the service layer interface for customer operations.

CustomerServiceImpl.java: Implements the CustomerService interface.

src/main/java/com/yourpackage/servlet



CustomerServlet.java: Handles HTTP requests related to customer operations (list, create, update, delete).


AuthServlet.java: Handles user login requests.

src/main/webapp/WEB-INF


lib/: Directory for external libraries (JAR files).

src/main/webapp/


 
updateCustomer.jsp:  editing customer details

customerList.jsp: JSP page for displaying the list of customers with search and pagination functionality.

customerForm.jsp: JSP page for adding details.

index.jsp: JSP page for user login.


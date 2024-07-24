<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@page import="com.tap.model.Customer"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Customer</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <div class="container">
        <h1>Edit Customer</h1>
        
<%	request.getAttribute("customer");	 %>
        <form action="customer?action=update" method="post">
            <input type="hidden" name="uuid" value="<%= request.getParameter("uuid") %>">
            <label for="first_name">First Name:</label>
            <input type="text" id="first_name" name="first_name" value="${customer.firstName}" required>
            <br>
            <label for="last_name">Last Name:</label>
            <input type="text" id="last_name" name="last_name" value="${customer.lastName}" required>
            <br>
            <label for="street">Street:</label>
            <input type="text" id="street" name="street" value="<%= request.getParameter("street") %>" required>
            <br>
            <label for="address">Address:</label>
            <input type="text" id="address" name="address" value="<%= request.getParameter("address") %>" required>
            <br>
            <label for="city">City:</label>
            <input type="text" id="city" name="city" value="<%= request.getParameter("city") %>" required>
            <br>
            <label for="state">State:</label>
            <input type="text" id="state" name="state" value="<%= request.getParameter("state") %>" required>
            <br>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="<%= request.getParameter("email") %>" required>
            <br>
            <label for="phone">Phone:</label>
            <input type="text" id="phone" name="phone" value="<%= request.getParameter("phone") %>" required>
            <br>
            <input type="submit" value="Update">
        </form>
    </div>
</body>
</html> --%><%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@page import="com.tap.model.Customer"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Customer</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <style>
        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #f9f9f9;
        }
        h1 {
            text-align: center;
        }
        label {
            display: block;
            margin-top: 10px;
        }
        input[type="text"],
        input[type="email"] {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Edit Customer</h1>
        
        <form action="customer?action=update" method="post">
            <input type="hidden" name="uuid" value="${customer.uuid}">
            <label for="first_name">First Name:</label>
            <input type="text" id="first_name" name="first_name" value="${customer.firstName}" required>
            <br>
            <label for="last_name">Last Name:</label>
            <input type="text" id="last_name" name="last_name" value="${customer.lastName}" required>
            <br>
            <label for="street">Street:</label>
            <input type="text" id="street" name="street" value="${customer.street}" required>
            <br>
            <label for="address">Address:</label>
            <input type="text" id="address" name="address" value="${customer.address}" required>
            <br>
            <label for="city">City:</label>
            <input type="text" id="city" name="city" value="${customer.city}" required>
            <br>
            <label for="state">State:</label>
            <input type="text" id="state" name="state" value="${customer.state}" required>
            <br>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="${customer.email}" required>
            <br>
            <label for="phone">Phone:</label>
            <input type="text" id="phone" name="phone" value="${customer.phone}" required>
            <br>
            <input type="submit" value="Update">
        </form>
    </div>
</body>
</html>
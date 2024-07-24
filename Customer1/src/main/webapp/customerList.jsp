<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.tap.model.Customer"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
<head>
    <title>Customer List</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <!-- <script>
        let searchBy = 'firstName'; // Default search criteria

        // Set the search criteria
        function setSearchBy(criteria) {
            searchBy = criteria;
            document.getElementById('searchByButton').innerText = criteria.charAt(0).toUpperCase() + criteria.slice(1); // Update button text
        }

        // Function to filter table rows based on search input
        function searchCustomer() {
            const input = document.getElementById('searchQuery').value.toLowerCase();
            const table = document.getElementById('customerTableBody');
            const rows = table.getElementsByTagName('tr');

            let columnIndex;
            // Determine the column index based on search criteria
            switch (searchBy) {
                case 'firstName':
                    columnIndex = 0;
                    break;
                case 'city':
                    columnIndex = 4;
                    break;
                case 'email':
                    columnIndex = 6;
                    break;
                case 'phone':
                    columnIndex = 7;
                    break;
                default:
                    columnIndex = 0;
            }

            // Iterate through rows and hide those that don't match the search query
            for (let row of rows) {
                let cell = row.getElementsByTagName('td')[columnIndex];
                if (cell) {
                    let textValue = cell.textContent || cell.innerText;
                    if (textValue.toLowerCase().indexOf(input) > -1) {
                        row.style.display = '';
                    } else {
                        row.style.display = 'none';
                    }
                }
            }
        }
    </script> --> <script>
        let searchBy = 'firstName'; // Default search criteria

        // Set the search criteria
        function setSearchBy(criteria) {
            searchBy = criteria;
            document.getElementById('searchByButton').innerText = criteria.charAt(0).toUpperCase() + criteria.slice(1); // Update button text
        }

        // Function to filter table rows based on search input
        function searchCustomer() {
            const input = document.getElementById('searchQuery').value.toLowerCase();
            const table = document.getElementById('customerTableBody');
            const rows = table.getElementsByTagName('tr');

            let columnIndex;
            // Determine the column index based on search criteria
            switch (searchBy) {
                case 'firstName':
                    columnIndex = 0; // Adjusted to match column index in table
                    break;
                case 'city':
                    columnIndex = 4; // Adjusted to match column index in table
                    break;
                case 'email':
                    columnIndex = 7; // Adjusted to match column index in table
                    break;
                case 'phone':
                    columnIndex = 8; // Adjusted to match column index in table
                    break;
                default:
                    columnIndex = 1; // Default to 'firstName' column index
            }

            // Iterate through rows and hide those that don't match the search query
            for (let row of rows) {
                let cell = row.getElementsByTagName('td')[columnIndex];
                if (cell) {
                    let textValue = cell.textContent || cell.innerText;
                    if (textValue.toLowerCase().indexOf(input) > -1) {
                        row.style.display = '';
                    } else {
                        row.style.display = 'none';
                    }
                }
            }
        }
    </script>
   
    <style type="text/css">/* General Styles */
body {
    font-family: Arial, sans-serif;
    background-color: #f4f4f4;
    margin: 0;
    padding: 0;
}

.container {
    width: 100%;
    max-width: 1500px;
    margin: 20px auto;
    padding: 20px;
    background-color: #fff;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h1 {
    text-align: center;
    color: #333;
}

/* Form Styles */
form {
    margin-bottom: 20px;
}

form label {
    display: block;
    margin-bottom: 8px;
    font-weight: bold;
}

form input[type="text"],
form input[type="email"],
form select {
    width: calc(100% - 22px);
    padding: 10px;
    margin-bottom: 10px;
    border: 1px solid #ddd;
    border-radius: 4px;
}

form button {
    padding: 10px 15px;
    color: #fff;
    background-color: #007bff;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

form button:hover {
    background-color: #0056b3;
}

/* Table Styles */
table {
    width: 100%;
    border-collapse: collapse;
    margin-bottom: 20px;
}

table th,
table td {
    padding: 12px;
    text-align: left;
    border-bottom: 1px solid #ddd;
}

table th {
    background-color: #007bff;
    color: #fff;
}

table tr:nth-child(even) {
    background-color: #f9f9f9;
}

table tr:hover {
    background-color: #f1f1f1;
}

/* Action Buttons */
.actions a,
.actions button {
    color: #007bff;
    border: none;
    background: none;
    cursor: pointer;
    padding: 0;
}

.actions a:hover,
.actions button:hover {
    text-decoration: underline;
}

.actions button {
    background-color: transparent;
    font-size: 14px;
}

.actions button:hover {
    color: #dc3545;
}

/* Pagination Styles */
.pagination {
    text-align: center;
    margin: 20px 0;
}

.pagination a,
.pagination span {
    display: inline-block;
    padding: 10px 15px;
    margin: 0 5px;
    border-radius: 4px;
    text-decoration: none;
    color: #007bff;
}

.pagination a:hover {
    background-color: #e9ecef;
}

.pagination .current {
    background-color: #007bff;
    color: #fff;
    cursor: default;
}
    </style>
</head>
<body>
    <!-- <div class="container">
        <h1>Customer List</h1>
        <form action="customer?action=get" method="get">
            <input type="hidden" name="action" value="list">
            <label for="searchQuery">Search:</label>
            <input type="text" id="searchQuery" name="searchQuery">
            <button type="button" onclick="searchCustomer()">Search</button>
            
            Dropdown menu for search criteria
            <div class="dropdown">
                <button class="dropbtn">Search by <span id="searchByButton">First Name</span></button>
                <div class="dropdown-content">
                    <a href="#" onclick="setSearchBy('firstName')">First Name</a>
                    <a href="#" onclick="setSearchBy('city')">City</a>
                    <a href="#" onclick="setSearchBy('email')">Email</a>
                    <a href="#" onclick="setSearchBy('phone')">Phone</a>
                </div>
            </div>
                        <label for="sortField">Sort By:</label>
            <select id="sortField" name="sortField">
                <option value="first_name">First Name</option>
                <option value="last_name">Last Name</option>
                <option value="city">City</option>
                <option value="email">Email</option>
            </select>
            <br>
            <label for="sortDirection">Sort Direction:</label>
            <select id="sortDirection" name="sortDirection">
                <option value="asc">Ascending</option>
                <option value="desc">Descending</option>
            </select>
            <br>
            <input type="submit" value="Search">
        </form> -->
       <form action="customer?action=get" method="get">
            <input type="hidden" name="action" value="list">
            <label for="searchQuery">Search:</label>
            <input type="text" id="searchQuery" name="searchQuery">
            <button type="button" onclick="searchCustomer()">Search</button>


            <label for="sortField">Sort By:</label>
            <select id="sortField" name="sortField">
                <option value="first_name">First Name</option>
                <option value="last_name">Last Name</option>
                <option value="city">City</option>
                <option value="email">Email</option>
            </select>
            <br>
            <label for="sortDirection">Sort Direction:</label>
            <select id="sortDirection" name="sortDirection">
                <option value="asc">Ascending</option>
                <option value="desc">Descending</option>
            </select>
            <br>
            <input type="submit" value="Search">
        </form>

    <table border="1">
        <thead>
            <tr>
                <!-- <th>UUID</th> -->
                <th>First Name</th>
                <th>Last Name</th>
                <th>Street</th>
                <th>Address</th>
                <th>City</th>
                <th>State</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody id="customerTableBody">
            <c:choose>
                <c:when test="${not empty customers}">
                    <c:forEach var="customer" items="${customers}">
                        <tr><%-- 
                            <td>${customer.uuid}</td> --%>
                            <td>${customer.firstName}</td>
                            <td>${customer.lastName}</td>
                            <td>${customer.street}</td>
                            <td>${customer.address}</td>
                            <td>${customer.city}</td>
                            <td>${customer.state}</td>
                            <td>${customer.email}</td>
                            <td>${customer.phone}</td>
                            <td class="actions">
                                <a href="customer?action=get&uuid=${customer.uuid}">Edit</a>
                                <form action="customer" method="post" style="display:inline;">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="uuid" value="${customer.uuid}">
                                    <button type="submit">Delete</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td colspan="10">No customers found.</td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>
    <!-- Pagination -->
 

<%
    // Fetch attributes and handle null values
    Integer currentPageAttr = (Integer) request.getAttribute("currentPage");
    Integer pageSizeAttr = (Integer) request.getAttribute("pageSize");
    Integer noOfPagesAttr = (Integer) request.getAttribute("noOfPages");
    String sortFieldAttr = (String) request.getAttribute("sortField");
    String sortOrderAttr = (String) request.getAttribute("sortOrder");

    int currentPage = (currentPageAttr != null) ? currentPageAttr : 1;
    int pageSize = (pageSizeAttr != null) ? pageSizeAttr : 10;
    int noOfPages = (noOfPagesAttr != null) ? noOfPagesAttr : 1;
    String sortField = (sortFieldAttr != null) ? sortFieldAttr : "firstName";
    String sortOrder = (sortOrderAttr != null) ? sortOrderAttr : "asc";
%>
<c:if test="${noOfPages > 1}">
    <div class="pagination">
        <%
            if (currentPage > 1) {
        %>
            <a href="?page=<%= currentPage - 1 %>&pageSize=<%= pageSize %>&sortField=<%= sortField %>&sortOrder=<%= sortOrder %>">Previous</a>
        <%
            }
            for (int pageNumber = 1; pageNumber <= noOfPages; pageNumber++) {
                if (pageNumber == currentPage) {
        %>
            <span class="current"><%= pageNumber %></span>
        <%
                } else {
        %>
            <a href="?page=<%= pageNumber %>&pageSize=<%= pageSize %>&sortField=<%= sortField %>&sortOrder=<%= sortOrder %>"><%= pageNumber %></a>
        <%
                }
            }
            if (currentPage < noOfPages) {
        %>
            <a href="?page=<%= currentPage + 1 %>&pageSize=<%= pageSize %>&sortField=<%= sortField %>&sortOrder=<%= sortOrder %>">Next</a>
        <%
            }
        %>
    </div>
</c:if>
 
        <button onclick="location.href='customerForm.jsp'">Add New Customer</button>
        <button onclick="location.href='customer?action=sync'">Sync</button>
    </div>
</body>
</html>
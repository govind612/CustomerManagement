package com.tap.Servlet;
////

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.tap.DAO.CustomerDAOImp;
import com.tap.Service.CustomerService;
import com.tap.Service.CustomerServiceImp;
import com.tap.model.Customer;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CustomerService customerService;
    

    @Override
    public void init() throws ServletException {
        customerService = new CustomerServiceImp(); // Initialize the service
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        String loginId = "test@sunbasedata.com";
        String password = "Test@123";
        String authUrl = "https://qa.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp";
        String CUSTOMER_LIST_URL = "https://qa.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=get_customer_list";

        try {
            // Set up the connection to the authentication API
            String token = authenticateAndGetToken(loginId, password);
         // Set the token in session
            request.getSession().setAttribute("bearerToken", token);
//           request.setAttribute("token", token);

            // Use the token for further API calls
            if (token != null) {
                // Store the token in the session or use it for subsequent requests
                request.getSession().setAttribute("bearerToken", token);
                response.getWriter().write("Authentication successful. Token: " + token);
//                response.sendRedirect("customerList.jsp");    if (token != null) {
                JSONArray customerList = getCustomerList(token);
                	request.setAttribute("customerList", customerList);
                if (customerList == null || customerList.isEmpty()) {
                    response.getWriter().println("No customers found or failed to retrieve customer list.");
                    return;
                }
                	CustomerDAOImp customerDAO=new CustomerDAOImp();
                ArrayList<Customer> al = new ArrayList<>();
                for (Object customerObj : customerList) {
                    JSONObject customerJson = (JSONObject) customerObj;
                    Customer customer = new Customer();
                    customer.setUuid(customerJson.getString("uuid"));
                    customer.setFirstName(customerJson.getString("first_name"));
                    customer.setLastName(customerJson.getString("last_name"));
                    customer.setStreet(customerJson.getString("street"));
                    customer.setAddress(customerJson.getString("address"));
                    customer.setCity(customerJson.getString("city"));
                    customer.setState(customerJson.getString("state"));
                    customer.setEmail(customerJson.getString("email"));
                    customer.setPhone(customerJson.getString("phone"));
                    //customers.add(customer);
                    al.add(customer);
//                    System.out.println(customer);
                    
                    
                   
                    Customer existingCustomer= new CustomerDAOImp().getCustomerById("uuid");   //   
                    if(existingCustomer==null)
                    {
                    	
                     customerDAO.createCustomer(customer);
                    }else
                    {    
//                    	System.out.println(al);

                    	existingCustomer.setFirstName("firstName");
                        existingCustomer.setLastName("lastName");
                        existingCustomer.setStreet("street");
                        existingCustomer.setAddress("address");
                        existingCustomer.setCity("city");
                        existingCustomer.setState("state");
                        existingCustomer.setPhone("phone");
                        customerDAO.updateCustomer(existingCustomer);}
                    }

            	List<Customer> customers=customerService.getAllCustomers();

                	request.setAttribute("customers", customers);
            	request.getRequestDispatcher("customerList.jsp").forward(request, response);
//                // Store the customer list in the session and forward to the JSP page
//                // Get pagination and sorting parameters from the request
//                String pageStr = request.getParameter("page");
//                String pageSizeStr = request.getParameter("pageSize");
//                String sortField = request.getParameter("sortField");
//                String sortOrder = request.getParameter("sortOrder");
//                String searchQuery=request.getParameter("searchQuery");
//                int noOfrecords = customerDAO.getNoOfRecords();
//                
//                // Set default values if parameters are not provided
//                int page = (pageStr == null || pageStr.isEmpty()) ? 1 : Integer.parseInt(pageStr);
//                int pageSize = (pageSizeStr == null || pageSizeStr.isEmpty()) ? 10 : Integer.parseInt(pageSizeStr);
//                sortField = (sortField == null || sortField.isEmpty()) ? "firstName" : sortField;
//                sortOrder = (sortOrder == null || sortOrder.isEmpty()) ? "asc" : sortOrder;
////                searchQuery = (searchQuery == null || searchQuery.isEmpty()) ? "firstName" : searchQuery;
//
//                // Retrieve the list of customers based on the given pagination and sorting parameters
//                List<Customer>customer=customerDAO.getCustomers(page, pageSize, sortField, sortOrder);
////                List<Customer>customer=customerDAO.getAllCustomers();
//                
//                
////                                request.getSession().setAttribute("customerList", customer);
//              	int noOfPages=   (int) Math.ceil(noOfrecords*1.0/pageSize);
//
//                // Set attributes to be accessed in the JSP
//                request.setAttribute("customers", al);System.out.println(al);
//                request.setAttribute("currentPage", page);
//                request.setAttribute("pageSize", pageSize);
//                request.setAttribute("sortField", sortField);
//                request.setAttribute("sortOrder", sortOrder);
//				request.setAttribute("noOfPages", noOfPages);
//               
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/customerList.jsp");
			    dispatcher.forward(request, response);
				
//                response.sendRedirect("customerList.jsp");
            } else {
                response.getWriter().write("Authentication failed. Token not found in response.");
            }
        } catch (Exception e) {
            response.getWriter().write("Exception: " + e.getMessage());
        }   
    }
    private String authenticateAndGetToken(String loginId, String password) throws Exception {
    	String authUrl = "https://qa.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp";
        	URL url = new URL(authUrl);
           HttpURLConnection conn = (HttpURLConnection) url.openConnection();
           conn.setRequestMethod("POST");
           conn.setRequestProperty("Content-Type", "application/json; utf-8");
           conn.setRequestProperty("Accept", "application/json");
           conn.setDoOutput(true);

           // Create the request body
           String jsonInputString = "{\"login_id\": \"" + loginId + "\", \"password\": \"" + password + "\"}";

           // Write the request body to the connection
           try(OutputStream os = conn.getOutputStream()) {
               byte[] input = jsonInputString.getBytes("utf-8");
               os.write(input, 0, input.length);
           }

           // Read the response
           StringBuilder responseStrBuilder = new StringBuilder();
           try(BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
               String responseLine = null;
               while ((responseLine = br.readLine()) != null) {
                   responseStrBuilder.append(responseLine.trim());
               }
           }

           // Extract the token from the response
           String responseBody = responseStrBuilder.toString();
           // Assuming the token is in the JSON response body as {"token": "your_token_here"}
           return  extractTokenFromResponse(responseBody);
    }

    private String extractTokenFromResponse(String responseBody) {
        // Implement a method to parse the token from the JSON response body
        // This is a placeholder implementation; you may need to use a JSON library such as org.json or Jackson
        String token = null;
        if (responseBody.contains("token")) {
            int startIndex = responseBody.indexOf("token") + 8;
            int endIndex = responseBody.indexOf("\"", startIndex);
            token = responseBody.substring(startIndex, endIndex);
        }
        return token;
    }
    private JSONArray getCustomerList(String token) throws Exception {
        String CUSTOMER_LIST_URL = "https://qa.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=get_customer_list";

        URL url = new URL(CUSTOMER_LIST_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/json; utf-8");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("Authorization", "Bearer " + token);

        // Read the response
        StringBuilder responseStrBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                responseStrBuilder.append(responseLine.trim());
            }
        }

        // Print the full response body for debugging
//        System.out.println("Customer List Response: " + responseStrBuilder.toString());

//    	List<Customer> customer=customerService.getAllCustomers();
      
    	
    	
    	
//    	System.out.println(customer);
        return new JSONArray(responseStrBuilder.toString());
    }
}
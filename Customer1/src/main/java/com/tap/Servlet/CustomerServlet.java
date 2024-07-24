package com.tap.Servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import com.tap.DAO.CustomerDAOImp;
import com.tap.Service.CustomerService;
import com.tap.Service.CustomerServiceImp;
import com.tap.model.Customer;

@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private CustomerService customerService;
    String CUSTOMER_LIST_URL = "https://qa.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=get_customer_list";
    String loginId = "test@sunbasedata.com";
    String password = "Test@123";
    @Override
    public void init() throws ServletException {
        customerService = new CustomerServiceImp(); // Initialize your service
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action parameter is missing");
            return;
        }

        switch (action) {
            case "list":
                listCustomers(request, response);
                break;
            case "get":
                getCustomer(request, response);
                break;
            case "sync":
                syncCustomers(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action parameter");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action parameter is missing");
            return;
        }

        switch (action) {
            case "create":
                createCustomer(request, response);
                break;
            case "update":
                updateCustomer(request, response);
                break;
            case "delete":
                deleteCustomer(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action parameter");
                break;
        }
    }

    private void createCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Customer customer = new Customer();
//            customer.setUuid(UUID.randomUUID().toString());
//            customer.setUuid(request.getParameter("uuid"));
           String uuid = UUID.randomUUID().toString();
            HttpSession session= request.getSession();
            	session.setAttribute("uuid", uuid);
            customer.setUuid("uuid");
            customer.setFirstName(request.getParameter("first_name"));
            customer.setLastName(request.getParameter("last_name"));
            customer.setStreet(request.getParameter("street"));
            customer.setAddress(request.getParameter("address"));
            customer.setCity(request.getParameter("city"));
            customer.setState(request.getParameter("state"));
            customer.setEmail(request.getParameter("email"));
            customer.setPhone(request.getParameter("phone"));

            customerService.createCustomer(customer); 	
        	List<Customer> customers=customerService.getAllCustomers();
        	
        	request.setAttribute("customers", customers);
        	request.getRequestDispatcher("customerList.jsp").forward(request, response);
//            response.sendRedirect("customerList.jsp"); // Redirect to the customer list page
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error creating customer");
        }
    }

    private void updateCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Customer customer = new Customer();
            customer.setUuid(request.getParameter("uuid"));
            customer.setFirstName(request.getParameter("first_name"));
            customer.setLastName(request.getParameter("last_name"));
            customer.setStreet(request.getParameter("street"));
            customer.setAddress(request.getParameter("address"));
            customer.setCity(request.getParameter("city"));
            customer.setState(request.getParameter("state"));
            customer.setEmail(request.getParameter("email"));
            customer.setPhone(request.getParameter("phone"));

            customerService.updateCustomer(customer);
//            System.out.println("updatedone");   
         	
        	List<Customer> customers=customerService.getAllCustomers();
        	
        	request.setAttribute("customers", customers);
        	request.getRequestDispatcher("customerList.jsp").forward(request, response);
//            response.sendRedirect("customer?action=list"); // Redirect to the customer list page

//            response.sendRedirect("customerList.jsp"); // Redirect to the customer list page
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error updating customer");
        }
    }

    private void listCustomers(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
//            int page = Integer.parseInt(request.getParameter("page"));
//            int size = Integer.parseInt(request.getParameter("size"));
//            String sortField = request.getParameter("sortField");
//            String sortDirection = request.getParameter("sortDirection");
////            String searchQuery = request.getParameter("searchQuery");
//
//            List<Customer> customers = customerService.getCustomers(page, size, sortField, sortDirection);
//
//            JSONArray jsonArray = new JSONArray();
//            for (Customer customer : customers) {
//                JSONObject jsonObject = new JSONObject();
//                jsonObject.put("uuid", customer.getUuid());
//                jsonObject.put("first_name", customer.getFirstName());
//                jsonObject.put("last_name", customer.getLastName());
//                jsonObject.put("street", customer.getStreet());
//                jsonObject.put("address", customer.getAddress());
//                jsonObject.put("city", customer.getCity());
//                jsonObject.put("state", customer.getState());
//                jsonObject.put("email", customer.getEmail());
//                jsonObject.put("phone", customer.getPhone());
//                jsonArray.put(jsonObject);
//            }

//            response.setContentType("application/json");
//            response.getWriter().write(jsonArray.toString());
        	
        	List<Customer> customers=customerService.getAllCustomers();
        	
        	request.setAttribute("customers", customers);
        	request.getRequestDispatcher("customerList.jsp").forward(request, response);
//        	System.out.println(customers);
        	
        	
        	
        	
        	
        	
        	
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid number format in parameters");
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error listing customers");
        }
    }

    private void getCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        {        	
        	// Set customer object in request attribute to forward to JSP for editing
        	
          String uuid = request.getParameter("uuid");  
          Customer customer = customerService.getCustomerById(uuid);
System.out.println("getcustomer "+customer);
            request.setAttribute("customer", customer);
            try {
				request.getRequestDispatcher("/updateCustomer.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}  
            //Customer customer = null;
////            String uuid = request.getParameter("uuid");    
////            customer.setUuid(UUID.randomUUID().toString()); // Generate UUID
////            	customer.setUuid(uuid);
//            if (uuid == null || uuid.trim().isEmpty()) {
//                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "UUID parameter is missing");
//                return;
//            }
////           Customer
//
//            if (customer != null) {
//                JSONObject jsonObject = new JSONObject();
//                jsonObject.put("uuid", customer.getUuid());
//                jsonObject.put("first_name", customer.getFirstName());
//                jsonObject.put("last_name", customer.getLastName());
//                jsonObject.put("street", customer.getStreet());
//                jsonObject.put("address", customer.getAddress());
//                jsonObject.put("city", customer.getCity());
//                jsonObject.put("state", customer.getState());
//                jsonObject.put("email", customer.getEmail());
//                jsonObject.put("phone", customer.getPhone());
//
//                response.setContentType("application/json");
//                response.getWriter().write(jsonObject.toString());
//          } else {
//                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Customer not found");
//            }
//        } catch (Exception e) {
//            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving customer");
//        }
    }

    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String uuid = request.getParameter("uuid");
            customerService.deleteCustomer(uuid);
            System.out.println("deleteddone");
//           customer = request.getAttribute("customers");
//            List<Customer> customers = (List<Customer>) request.getAttribute("customers");
// System.out.println(customers);
         	
        	List<Customer> customers=customerService.getAllCustomers();
        	
        	request.setAttribute("customers", customers);
        	request.getRequestDispatcher("customerList.jsp").forward(request, response);
//           response.sendRedirect("customerList.jsp"); // Redirect to the customer list page
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error deleting customer");
        }
    }

    private void syncCustomers(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Logic to sync customers from remote API
        // This method is left unimplemented for simplicity
        String token = (String) request.getSession().getAttribute("bearerToken");
//        String token = AuthServlet.authenticateAndGetToken(loginId, password);


        if (token != null) {
            try {
                JSONArray customerList = getCustomerList(token);

                if (customerList == null || customerList.isEmpty()) {
                    response.getWriter().println("No customers found or failed to retrieve customer list.");
                    return;
                }

                CustomerDAOImp customerDAO = new CustomerDAOImp();
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

                    Customer existingCustomer = customerDAO.getCustomerById(customer.getUuid());
                    if (existingCustomer == null) {
                        customerDAO.createCustomer(customer);
                    } else {
                        existingCustomer.setFirstName(customer.getFirstName());
                        existingCustomer.setLastName(customer.getLastName());
                        existingCustomer.setStreet(customer.getStreet());
                        existingCustomer.setAddress(customer.getAddress());
                        existingCustomer.setCity(customer.getCity());
                        existingCustomer.setState(customer.getState());
                        existingCustomer.setEmail(customer.getEmail());
                        existingCustomer.setPhone(customer.getPhone());
                        customerDAO.updateCustomer(existingCustomer);
                    }
                }
                List<Customer> customers = customerDAO.getAllCustomers();
                request.setAttribute("customers", customers);
                request.getRequestDispatcher("customerList.jsp").forward(request, response);
            } catch (Exception e) {
                response.getWriter().println("Exception: " + e.getMessage());
            }
        } else {
            response.getWriter().println("Authentication required. Please log in.");
        }
    }

    private JSONArray getCustomerList(String token) throws Exception {
        URL url = new URL(CUSTOMER_LIST_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/json; utf-8");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("Authorization", "Bearer " + token);

        StringBuilder responseStrBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                responseStrBuilder.append(responseLine.trim());
            }
        }
        return new JSONArray(responseStrBuilder.toString());
    }
}

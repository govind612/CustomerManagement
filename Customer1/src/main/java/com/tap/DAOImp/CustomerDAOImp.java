//package com.tap.DAOImp;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.tap.DAO.CustomerDAO;
//import com.tap.model.Customer;
//
//public class CustomerDAOImp implements CustomerDAO {
//	String url="jdbc:mysql://localhost:3306/cust";
//	String UserName="root";
//	String password="root";
//	Connection con=null;
//    public CustomerDAOImp() {
//    	
//    		try {
//    			Class.forName("com.mysql.cj.jdbc.Driver");
//    			 con = DriverManager.getConnection(url, UserName, password);
//    			
//    		} catch (Exception e) {
//    			
//    			e.printStackTrace();
//    		}
//    }
//
//    public void createCustomer(Customer customer) {
//        String sql = "INSERT INTO customers (uuid, first_name, last_name, street, address, city, state, email, phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
//        try {PreparedStatement stmt = con.prepareStatement(sql);
//            stmt.setString(1, customer.getUuid());
//            stmt.setString(2, customer.getFirstName());
//            stmt.setString(3, customer.getLastName());
//            stmt.setString(4, customer.getStreet());
//            stmt.setString(5, customer.getAddress());
//            stmt.setString(6, customer.getCity());
//            stmt.setString(7, customer.getState());
//            stmt.setString(8, customer.getEmail());
//            stmt.setString(9, customer.getPhone());
//            stmt.executeUpdate();
//        }
//        catch (Exception e) {
//			// TODO: handle exception
//		}
//    }
//
//    public void updateCustomer(Customer customer) {
//        String sql = "UPDATE customers SET first_name = ?, last_name = ?, street = ?, address = ?, city = ?, state = ?, email = ?, phone = ? WHERE uuid = ?";
//        try {PreparedStatement stmt = con.prepareStatement(sql);
//            stmt.setString(1, customer.getFirstName());
//            stmt.setString(2, customer.getLastName());
//            stmt.setString(3, customer.getStreet());
//            stmt.setString(4, customer.getAddress());
//            stmt.setString(5, customer.getCity());
//            stmt.setString(6, customer.getState());
//            stmt.setString(7, customer.getEmail());
//            stmt.setString(8, customer.getPhone());
//            stmt.setString(9, customer.getUuid());
//            stmt.executeUpdate();
//        }
//        catch (Exception e) {
//			// TODO: handle exception
//		}
//    }
//
//    public List<Customer> getCustomers(int page, int size, String sortField, String sortDirection) {
//        List<Customer> customers = new ArrayList<>();
//        String sql = "SELECT * FROM customers WHERE first_name LIKE ? ORDER BY " + sortField + " " + sortDirection + " LIMIT ? OFFSET ?";
//        try {PreparedStatement stmt = con.prepareStatement(sql);
//            stmt.setString(1, "%" + sortField + "%");
//            stmt.setInt(2, size);
//            stmt.setInt(3, page * size);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                Customer customer = new Customer();
//                customer.setUuid(rs.getString("uuid"));
//                customer.setFirstName(rs.getString("first_name"));
//                customer.setLastName(rs.getString("last_name"));
//                customer.setStreet(rs.getString("street"));
//                customer.setAddress(rs.getString("address"));
//                customer.setCity(rs.getString("city"));
//                customer.setState(rs.getString("state"));
//                customer.setEmail(rs.getString("email"));
//                customer.setPhone(rs.getString("phone"));
//                customers.add(customer);
//            }
//            
//        }catch (Exception e) {
//			// TODO: handle exception
//		}
//        return customers;
//    }
//    
//    
//    
//    public List<Customer> getAllCustomers() {
//        List<Customer> customers = new ArrayList<>();
//        String sql = "SELECT * FROM customers";
//        try {
//            PreparedStatement stmt = con.prepareStatement(sql);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                Customer customer = new Customer();
//                customer.setUuid(rs.getString("uuid"));
//                customer.setFirstName(rs.getString("first_name"));
//                customer.setLastName(rs.getString("last_name"));
//                customer.setStreet(rs.getString("street"));
//                customer.setAddress(rs.getString("address"));
//                customer.setCity(rs.getString("city"));
//                customer.setState(rs.getString("state"));
//                customer.setEmail(rs.getString("email"));
//                customer.setPhone(rs.getString("phone"));
//                customers.add(customer);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            // Log and handle exception as needed
//        }
//        return customers;
//    }
//
//    
//    
//
//    public Customer getCustomerById(String uuid) {
//        String sql = "SELECT * FROM customers WHERE uuid = ?";
//        try {PreparedStatement stmt = con.prepareStatement(sql);
//            stmt.setString(1, uuid);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                Customer customer = new Customer();
//                customer.setUuid(rs.getString("uuid"));
//                customer.setFirstName(rs.getString("first_name"));
//                customer.setLastName(rs.getString("last_name"));
//                customer.setStreet(rs.getString("street"));
//                customer.setAddress(rs.getString("address"));
//                customer.setCity(rs.getString("city"));
//                customer.setState(rs.getString("state"));
//                customer.setEmail(rs.getString("email"));
//                customer.setPhone(rs.getString("phone"));
//                return customer;
//            }
//        }catch (Exception e) {
//			// TODO: handle exception
//		}
//        return null;
//    }
//
//    public void deleteCustomer(String uuid) {
//        String sql = "DELETE FROM customers WHERE uuid = ?";
//        try {PreparedStatement stmt = con.prepareStatement(sql);
//            stmt.setString(1, uuid);
//            stmt.executeUpdate();
//        }catch (Exception e) {
//			// TODO: handle exception
//		}
//    }
//    public int getNoOfRecords() {
//		ResultSet a ;						 int count = 0;
//
//    			String SQL="Select count(*) from customers";
//    			Statement statement;
//				try {
//					statement = con.createStatement();   
//					 a = statement.executeQuery(SQL);
//					 while(a.next()) {
//						count++;
//					 }
//					
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//    	return count;
//	}
//}

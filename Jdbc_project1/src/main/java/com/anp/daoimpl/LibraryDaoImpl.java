package com.anp.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.anp.dao.LibraryDao;
import com.anp.entities.Library;

@SuppressWarnings("serial")
class BookAlreadyExistsException extends Exception {
    public static void BookAlreadyExistsExceptions() {
        System.out.println("Welcome to the library");
    }

	
}
	
public class LibraryDaoImpl implements LibraryDao {
		String jdbcUrl="jdbc:mysql://localhost:3306/librarymanagementsystem";
		String username="root";
		String password="Admin123";
		
		public void create() {
			String sql="create table if not exists Books ("
						+"book_id int primary key,"
						+"book_name varchar(255),"
						+"assured_date date,"
						+"renewal_date date,"
						+"return_date date)";
				try {
					Connection con = DriverManager.getConnection(jdbcUrl, username, password);
					PreparedStatement pts=con.prepareStatement(sql);	
					pts.execute();
					System.out.println("Table is created successfully");
					System.out.println();
				}catch(Exception e) {
					BookAlreadyExistsException.BookAlreadyExistsExceptions();
				}
		}
		@Override
		public void addBook(Library library) {
			String insertQuery ="insert into Books(book_id,book_name,assured_date,renewal_date,return_date) values(?,?,?,?,?)";
			
			try {
				Connection con = DriverManager.getConnection(jdbcUrl,username,password);
				PreparedStatement pts=con.prepareStatement(insertQuery);
				pts.setInt(1,library.getBook_id());
				pts.setString(2,library.getBook_name());
				pts.setDate(3, java.sql.Date.valueOf(library.getAssured_date()));
		        pts.setDate(4, java.sql.Date.valueOf(library.getRenewal_date()));
		        pts.setDate(5, java.sql.Date.valueOf(library.getReturn_date()));
				
				pts.executeUpdate();
				System.out.println("Book is inserted successfully");
				
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
		public void display() {
			 String displayQuery = "SELECT * FROM Books";
			try {
				Connection con = DriverManager.getConnection(jdbcUrl,username,password);
				PreparedStatement pts=con.prepareStatement(displayQuery);
				ResultSet rs = pts.executeQuery();
		            
		            System.out.println("Books in the library:");
		            while (rs.next()) {
		                int bookId = rs.getInt("book_id");
		                String bookName = rs.getString("book_name");
		                java.sql.Date assuredDate = rs.getDate("assured_date");
		                java.sql.Date renewalDate = rs.getDate("renewal_date");
		                java.sql.Date returnDate = rs.getDate("return_date");
		                
		                System.out.println("Book ID: " + bookId);
		                System.out.println("Book Name: " + bookName);
		                System.out.println("Assured Date: " + assuredDate);
		                System.out.println("Renewal Date: " + renewalDate);
		                System.out.println("Return Date: " + returnDate);
		                System.out.println();
		            }
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
			
		}
		
		public void displaybook(int book_id, String book_name) {
	        String selectQuery = "SELECT * FROM Books WHERE book_id = ?";
	        
	        try (Connection con = DriverManager.getConnection(jdbcUrl, username, password);
	             PreparedStatement pts = con.prepareStatement(selectQuery)) {
	            
	            pts.setInt(1, book_id);
	            ResultSet rs = pts.executeQuery();
	            
	            if (rs.next()) {
	                int bookId = rs.getInt("book_id");
	                String bookName = rs.getString("book_name");
	                java.sql.Date assuredDate = rs.getDate("assured_date");
	                java.sql.Date renewalDate = rs.getDate("renewal_date");
	                java.sql.Date returnDate = rs.getDate("return_date");
	                
	                System.out.println("Book ID: " + bookId);
	                System.out.println("Book Name: " + bookName);
	                System.out.println("Assured Date: " + assuredDate);
	                System.out.println("Renewal Date: " + renewalDate);
	                System.out.println("Return Date: " + returnDate);
	            } else {
	                System.out.println("Book not found.");
	            }
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	
		@Override
		public void update(Library library, int book_id, String book_name) {
		    String updateQuery = "UPDATE Books SET book_name = ? WHERE book_id = ?";
		    
		    try (Connection con = DriverManager.getConnection(jdbcUrl, username, password);
		         PreparedStatement pts = con.prepareStatement(updateQuery)) {
		        
		        pts.setString(1, book_name);
		        pts.setInt(2, book_id);
		        
		        // Execute the update query
		        pts.executeUpdate(); // Use executeUpdate() for update queries
		        
		        // If no exception is thrown, the update was successful
		        System.out.println("Book details updated successfully.");
		        
		    } catch (SQLException e) {
		        // Handle the SQL exception (e.g., print error message and stack trace)
		        System.out.println("Failed to update book details. Reason: " + e.getMessage());
		        e.printStackTrace();
		    }
		}


	
	public void bookDetails(int book_id) {
        String selectQuery = "SELECT * FROM Books WHERE book_id = ?";
        
        try (Connection con = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement pstmt = con.prepareStatement(selectQuery)) {
            
            pstmt.setInt(1, book_id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                int bookId = rs.getInt("book_id");
                String bookName = rs.getString("book_name");
                java.sql.Date assuredDate = rs.getDate("assured_date");
                java.sql.Date renewalDate = rs.getDate("renewal_date");
                java.sql.Date returnDate = rs.getDate("return_date");
                
                System.out.println("Book ID: " + bookId);
                System.out.println("Book Name: " + bookName);
                System.out.println("Assured Date: " + assuredDate);
                System.out.println("Renewal Date: " + renewalDate);
                System.out.println("Return Date: " + returnDate);
            } else {
                System.out.println("Book not found.");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletebook(Library library, int book_id) {
        String deleteQuery = "DELETE FROM Books WHERE book_id = ?";
        
        try (Connection con = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement pstmt = con.prepareStatement(deleteQuery)) {
            
            pstmt.setInt(1, book_id);
            
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Book deleted successfully.");
            } else {
                System.out.println("Failed to delete book. Book may not exist.");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

	

	


	
			
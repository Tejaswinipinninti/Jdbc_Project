package com.anp.main;

import java.time.LocalDate;
import java.util.Scanner;

import com.anp.daoimpl.LibraryDaoImpl;
import com.anp.entities.Library;



public class Test {

	public static void main(String[] args) {
		
		LibraryDaoImpl dao = new LibraryDaoImpl();
        Scanner scanner = new Scanner(System.in);

        // Create the Books table if it doesn't exist
        dao.create();

        int choice;
        do {
            System.out.println("Library Management System Menu:");
            System.out.println("1. Add a book");
            System.out.println("2. Display all books");
            System.out.println("3. Display book details");
            System.out.println("4. Update book details");
            System.out.println("5. Delete a book");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    addBook(scanner, dao);
                    break;
                case 2:
                    dao.display();
                    break;
                case 3:
                    displayBookDetails(scanner, dao);
                    break;
                case 4:
                    updateBook(scanner, dao);
                    break;
                case 5:
                    deleteBook(scanner, dao);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        } while (choice != 6);
        
        scanner.close();
    }

    private static void addBook(Scanner scanner, LibraryDaoImpl dao) {
        System.out.print("Enter book ID: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        
        System.out.print("Enter book name: ");
        String bookName = scanner.nextLine();
        
        System.out.print("Enter assured date (YYYY-MM-DD): ");
        String assuredDateStr = scanner.nextLine();
        LocalDate assuredDate = LocalDate.parse(assuredDateStr);
        
        System.out.print("Enter renewal date (YYYY-MM-DD): ");
        String renewalDateStr = scanner.nextLine();
        LocalDate renewalDate = LocalDate.parse(renewalDateStr);
        
        System.out.print("Enter return date (YYYY-MM-DD): ");
        String returnDateStr = scanner.nextLine();
        LocalDate returnDate = LocalDate.parse(returnDateStr);

        Library book = new Library(bookId, bookName, assuredDate, renewalDate, returnDate);
        dao.addBook(book);
    }

    private static void displayBookDetails(Scanner scanner, LibraryDaoImpl dao) {
        System.out.print("Enter book ID: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        
        dao.displaybook(bookId,null); // We only need the book_id for displaying details
    }

    private static void updateBook(Scanner scanner, LibraryDaoImpl dao) {
        System.out.print("Enter book ID: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        
        System.out.print("Enter new book name: ");
        String newBookName = scanner.nextLine();
        
        dao.update(new Library(bookId, newBookName, null, null, null), bookId, newBookName);
    }

    private static void deleteBook(Scanner scanner, LibraryDaoImpl dao) {
        System.out.print("Enter book ID: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        
        dao.deletebook(new Library(bookId, null, null, null, null), bookId);
    }
}
		
		
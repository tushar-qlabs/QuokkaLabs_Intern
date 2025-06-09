package dev.tushar;

import dev.tushar.model.Book;
import dev.tushar.service.LibraryService;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String FILE_PATH = "E:\\books.csv";
    private static final Scanner sc = new Scanner(System.in);
    private static final LibraryService library = new LibraryService();

    public static void main(String[] args) {
        library.loadBooksFromFile(FILE_PATH);

        // I will keep checking the running variable to exit the loop
        boolean running = true;
        while (running) {
            System.out.println("\nLibrary Menu");
            System.out.println("1. Add Book");
            System.out.println("2. Show All Books");
            System.out.println("3. Search Books");
            System.out.println("4. Save & Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addBookDetails(); // If choice is 1, we will call addBookDetails
                case 2 -> library.getAllBooks().forEach(System.out::println); // If choice is 2, we will call getAllBooks
                case 3 -> { // If choice is 3, then we will search for the keyword in title or author
                    System.out.print("Enter keyword: ");
                    String keyword = sc.nextLine();
                    List<Book> found = library.searchBooks(keyword);
                    if (found.isEmpty()) System.out.println("No books found.");
                    else found.forEach(System.out::println);
                }
                case 4 -> {
                    library.saveBooksToFile(FILE_PATH); // If choice is 4, we will save the books to file (E:\\books.csv) and set running to false
                    System.out.println("Books saved to file.");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addBookDetails() {
        try {
            System.out.print("ID: ");
            int id = Integer.parseInt(sc.nextLine());
            System.out.print("Title: ");
            String title = sc.nextLine();
            System.out.print("Author: ");
            String author = sc.nextLine();
            System.out.print("Price: ");
            double price = Double.parseDouble(sc.nextLine());

            Book book = new Book(id, title, author, price);
            library.addBook(book); // We will add the book to the library
        } catch (Exception e) {
            System.out.println("Invalid input. Try again.");
        }
    }
}


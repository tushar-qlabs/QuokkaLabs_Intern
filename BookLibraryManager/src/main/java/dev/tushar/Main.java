package dev.tushar;

import dev.tushar.model.Book;
import dev.tushar.service.LibraryService;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String FILE_PATH = "books.csv";
    private static final Scanner sc = new Scanner(System.in);
    private static final LibraryService library = new LibraryService();

    public static void main(String[] args) {
        library.loadBooksFromFile(FILE_PATH);

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
                case 1 -> addBookFlow();
                case 2 -> library.getAllBooks().forEach(System.out::println);
                case 3 -> {
                    System.out.print("Enter keyword: ");
                    String keyword = sc.nextLine();
                    List<Book> found = library.searchBooks(keyword);
                    if (found.isEmpty()) System.out.println("No books found.");
                    else found.forEach(System.out::println);
                }
                case 4 -> {
                    library.saveBooksToFile(FILE_PATH);
                    System.out.println("Books saved to file.");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addBookFlow() {
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
            library.addBook(book);
        } catch (Exception e) {
            System.out.println("Invalid input. Try again.");
        }
    }
}


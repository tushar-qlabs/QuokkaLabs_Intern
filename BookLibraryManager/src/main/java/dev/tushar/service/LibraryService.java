package dev.tushar.service;

import dev.tushar.model.Book;
import dev.tushar.util.FileUtil;

import java.util.*;
import java.util.stream.Collectors;

public class LibraryService {
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        if (books.contains(book)) {
            System.out.println("Book with this ID already exists.");
            return;
        }
        books.add(book);
        System.out.println("Book added!");
    }

    public List<Book> getAllBooks() {
        return books.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<Book> searchBooks(String keyword) {
        return books.stream()
                .filter(b -> b.getTitle().toLowerCase().contains(keyword.toLowerCase())
                        || b.getAuthor().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void loadBooksFromFile(String filePath) {
        List<Book> loadedBooks = FileUtil.readBooksFromFile(filePath);
        books.addAll(loadedBooks);
    }

    public void saveBooksToFile(String filePath) {
        FileUtil.writeBooksToFile(filePath, books);
    }
}


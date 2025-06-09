package dev.tushar.util;

import dev.tushar.model.Book;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class FileUtil {

    public static List<Book> readBooksFromFile(String filePath) {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) return Collections.emptyList();

        try {
            return Files.readAllLines(path, StandardCharsets.UTF_8).stream()
                    .map(line -> line.split(","))
                    .filter(tokens -> tokens.length == 4)
                    .map(tokens -> {
                        try {
                            int id = Integer.parseInt(tokens[0].trim());
                            String title = tokens[1].trim();
                            String author = tokens[2].trim();
                            double price = Double.parseDouble(tokens[3].trim());
                            return new Book(id, title, author, price);
                        } catch (NumberFormatException e) {
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public static void writeBooksToFile(String filePath, List<Book> books) {
        Path path = Paths.get(filePath);

        List<String> lines = books.stream()
                .map(book -> String.format("%d,%s,%s,%.2f",
                        book.getId(), book.getTitle(), book.getAuthor(), book.getPrice()))
                .collect(Collectors.toList());

        try {
            Files.write(path, lines);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}

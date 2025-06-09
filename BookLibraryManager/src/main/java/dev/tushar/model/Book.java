package dev.tushar.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Comparable<Book> {
    private int id;
    private String title;
    private String author;
    private double price;

    @Override
    public int compareTo(Book other) {
        return this.title.compareToIgnoreCase(other.title);
    }

    // We're not updating title and author right now
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }
}

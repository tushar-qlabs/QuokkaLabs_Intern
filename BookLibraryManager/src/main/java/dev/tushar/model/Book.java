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
        // This compares two book titles ignoring case,
        // allowing us to sort books lexicographically by title.
        // We override compareTo because Comparable requires it,
        // and we want to define the natural order based on the title.
    }

    // We're not updating title and author right now (...umm, maybe later)
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim(); // Say, no to trailing or leading whitespace
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim(); // Same as above!
    }
}

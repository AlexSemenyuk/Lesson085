package org.itstep.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "publisher")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 100, nullable = false, unique = true)
    private String publisher;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "publisher_book",
            joinColumns = @JoinColumn(name="publisher_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name="book_id", nullable = false ))
    private List<Book> books;

    public Publisher() {
    }

    public Publisher(String publisher) {
        this.publisher = publisher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
    public void addPublisher(Book book) {
        if (books.stream().noneMatch(b -> Objects.equals(b, book))) {
            books.add(book);
        }
    }
    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", publisher='" + publisher + '\'' +
//                ", books=" + books +
                '}';
    }


}








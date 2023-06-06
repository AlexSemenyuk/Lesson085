package org.itstep.entity;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 100, nullable = false, unique = true)
    private String title;
    @Column(nullable = false, name = "pages")
    private int pages;
    @ManyToMany(mappedBy = "books", fetch = FetchType.EAGER)

    private List<Author> authors;
    @ManyToMany(mappedBy = "books", fetch = FetchType.EAGER)
    private List<Publisher> publishers;

    public Book() {
    }

    public Book(String title, int pages, List<Author> authors, List<Publisher> publishers) {
        this.title = title;
        this.pages = pages;
        this.authors = authors;
        this.publishers = publishers;
        authors.stream().forEach(author -> author.addBook(this));
        publishers.stream().forEach(publisher -> publisher.addPublisher(this));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Publisher> getPublishers() {
        return publishers;
    }

    public void setPublishers(List<Publisher> publishers) {
        this.publishers = publishers;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", pages=" + pages +
                ", authors=" + authors +
                ", publishers=" + publishers +
                '}';
    }

//    @PreRemove
//    public void beforeRemoveHandler() {
//        authors = null;
//        publishers = null;
//    }

}

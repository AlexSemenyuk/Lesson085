package org.itstep.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class AuthorBook {
    @Id
    @Column(name = "author_id")
    private int authorId;
    @Column(name = "book_id")
    private int bookId;

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "AuthorBook{" +
                "authorId=" + authorId +
                ", bookId=" + bookId +
                '}';
    }
}

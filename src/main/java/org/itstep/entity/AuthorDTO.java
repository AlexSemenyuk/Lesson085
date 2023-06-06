package org.itstep.entity;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class AuthorDTO {

    private int id;

    private String author;


    public AuthorDTO() {
    }

    public AuthorDTO(String author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "AuthorDTO{" +
                "id=" + id +
                ", author='" + author + '\'' +
                '}';
    }
}

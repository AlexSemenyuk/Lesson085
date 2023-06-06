package org.itstep.entity;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
@Entity
@Table(name = "publisherdto")
public class PublisherDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 100, nullable = false)
    private String publisher;

    public PublisherDTO() {
    }

    public PublisherDTO(String publisher) {
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

    @Override
    public String toString() {
        return "PublisherDTO{" +
                "id=" + id +
                ", publisher='" + publisher + '\'' +
                '}';
    }
}


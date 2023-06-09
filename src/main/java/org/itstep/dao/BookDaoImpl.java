package org.itstep.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.h2.engine.Session;
import org.hibernate.SessionFactory;
import org.itstep.entity.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookDaoImpl {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Book> findAllBooks() {

        TypedQuery<Book> query = entityManager.createQuery("select с from Book с", Book.class);
        List<Book> books = query.getResultList();
        if (books != null) {
            System.out.println("BookList from Impl");
            books.stream().forEach(System.out::println);
        }
        return books;
    }

    public List<Author> findAllAuthors() {
        TypedQuery<Author> query = entityManager.createQuery("select с from Author с", Author.class);
        List<Author> authors = query.getResultList();
        if (authors != null) {
            authors.forEach(System.out::println);
        }
        return authors;
    }

    public List<Publisher> findAllPublisers() {
        TypedQuery<Publisher> query = entityManager.createQuery("select с from Publisher с", Publisher.class);
        List<Publisher> publishers = query.getResultList();
        if (publishers != null) {
            publishers.forEach(System.out::println);
        }
        return publishers;
    }

    public Book findBookById(int id) {
        Book book = entityManager.find(Book.class, id);
        return book;
    }

    public Author findAuthorById(int id) {
        Author author = entityManager.find(Author.class, id);
        return author;
    }

    public Publisher findPublisherById(int id) {
        Publisher publisher = entityManager.find(Publisher.class, id);
        return publisher;
    }

    @Transactional
    public void addBook(Book book) {
        entityManager.persist(book);
        book.getAuthors().forEach(a -> entityManager.merge(a));
        book.getPublishers().forEach(p -> entityManager.merge(p));
        System.out.println("Persist Book is successful");
    }

    @Transactional
    public void addAuthor(Author newAuthor) {
        entityManager.persist(newAuthor);
        System.out.println("Persist Author is successful");
    }

    @Transactional
    public void addPublisher(Publisher newPublisher) {
        entityManager.persist(newPublisher);
        System.out.println("Persist Publisher is successful");
    }

    @Transactional
    public void deleteBook(int id) {
        Book book = entityManager.find(Book.class, id);
        if (book != null) {
            System.out.println("book before DELETE" + book.toString());
            entityManager.remove(book);
        } else {
            System.out.println("Delete Book impl: id = null");
        }
    }

    @Transactional
    public void deleteBookInAuthor(int id) {
        List<Author> authors = findAllAuthors();
        for (Author a : authors) {
            List<Book> booksOfAuthor = a.getBooks();
            if (booksOfAuthor != null) {
                for (Book b : booksOfAuthor) {
                    if (b.getId() == id) {
                        booksOfAuthor.remove(b);
                        break;
                    }
                }
            }
        }
    }

    @Transactional
    public void deleteBookInPublisher(int id) {
        List<Publisher> publishers = findAllPublisers();
        for (Publisher p : publishers) {
            List<Book> booksOfPublisher = p.getBooks();
            for (Book b : booksOfPublisher) {
                if (b.getId() == id) {
                    booksOfPublisher.remove(b);
                    break;
                }

            }
        }
    }

    @Transactional
    public void deleteAuthor(int id) {
        Author author = entityManager.find(Author.class, id);
        if (author != null) {
            System.out.println("Delete author.toString() = " + author.toString());
            entityManager.remove(author);
        } else {
            System.out.println("Delete Author impl: id = null");
        }
    }

    @Transactional
    public void deletePublisher(int id) {
        Publisher publisher = entityManager.find(Publisher.class, id);
        if (publisher != null) {
            System.out.println("Delete publisher.toString() = " + publisher.toString());
            entityManager.remove(publisher);
        } else {
            System.out.println("Delete Publisher impl: id = null");
        }
    }


}

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
//            book.setAuthors(new ArrayList<>());
//            book.setPublishers(new ArrayList<>());
//            book.getAuthors().forEach(a -> entityManager.merge(a));
//            book.getPublishers().forEach(p -> entityManager.merge(p));
//            entityManager.merge(book.getAuthors());
//            entityManager.merge(book.getPublishers());
//            entityManager.merge(book);        // не работает
//            System.out.println("Delete book.toString() = " + book.toString());
//            String delete = "DELETE FROM Book b WHERE b.BOOK_ID  = %s;".formatted(id);
//            System.out.println("delete = " + delete);
//            entityManager.createQuery(delete);
            entityManager.remove(id);
        } else {
            System.out.println("Delete Book impl: id = null");
        }
    }

    @Transactional
    public void deleteDataInAuthorBookTable(int id) {
        String delete = "DELETE FROM AuthorBook ab WHERE ab.BOOK_ID  = %s;".formatted(id);
        System.out.println("delete = " + delete);
//        Query nativeQuery =
//            entityManager.createNativeQuery(delete);
        entityManager.createQuery(delete);
//          entityManager.createNativeQuery();
//        List<Category> resultList = nativeQuery.getResultList();
//        resultList.stream().forEach(System.out::println);
    }

    @Transactional
    public void deleteDataInPublisherBookTable(int id) {
        String delete = "DELETE FROM PUBLISHER_BOOK pb WHERE pb.BOOK_ID  = %s;".formatted(id);
        System.out.println("delete = " + delete);
        entityManager.createQuery(delete);
//        Query nativeQuery =
//        entityManager.createNativeQuery(delete);
//        List<Category> resultList = nativeQuery.getResultList();
//        resultList.stream().forEach(System.out::println);
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

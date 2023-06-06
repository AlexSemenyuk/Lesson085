package org.itstep.service;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.itstep.dao.BookDaoImpl;
//import org.itstep.entity.AuthorDTO;
import org.itstep.entity.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
//import org.itstep.entity.BookDTO;


import java.lang.annotation.Repeatable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;


@Service
@RequiredArgsConstructor
public class BookService {

    protected final BookDaoImpl bookDaoImpl;

    public List<Book> findAllBooks() {
        List<Book> books = bookDaoImpl.findAllBooks();
        return books;
    }

    public List<Author> findAllAuthors() {
        List<Author> authors = bookDaoImpl.findAllAuthors();
        return authors;
    }

    public List<Publisher> findAllPublishers() {
        List<Publisher> publishers = bookDaoImpl.findAllPublisers();
        return publishers;
    }

    public void addBook(BookDTO bookDTO, AuthorDTO authorDTO, PublisherDTO publisherDTO) {
        List<Integer> authorsId = getAuthorsId(authorDTO);
        List<Integer> publishersId = getPublishersId(publisherDTO);

        List<Author> authorsList = new ArrayList<>();
        for (Integer id : authorsId) {
            authorsList.add(bookDaoImpl.findAuthorById(id));
        }
        List<Publisher> publishersList = new ArrayList<>();
        for (Integer id : publishersId) {
            publishersList.add(bookDaoImpl.findPublisherById(id));
        }

        publishersList.forEach(System.out::println);
        authorsList.forEach(System.out::println);

        int bookId = getBookId(bookDTO);
        if (bookId == 0) {
            Book newBook = new Book(bookDTO.getTitle(), bookDTO.getPages(), authorsList, publishersList);
            if (newBook != null) {
                System.out.println("ADD Book (Service): " + newBook);
                bookDaoImpl.addBook(newBook);
            }
        }
    }

    private int getBookId(BookDTO bookDTO) {
        int id = 0;
        List<Book> books = bookDaoImpl.findAllBooks();
        for (Book book : books) {
            if (book.getTitle().equals(bookDTO.getTitle())) {
                id = book.getId();
                break;
            }
        }
        return id;
    }

    private List<Integer> getAuthorsId(AuthorDTO authorDTO) {
        List<Integer> authorsId = new CopyOnWriteArrayList<>();
        String[] authorsTMP = authorDTO.getAuthor().split(",");

        for (int i = 0; i < authorsTMP.length; i++) {
            if (!authorsTMP[i].equals("")) {
                int idTmp = 0;
                idTmp = getAuthorsIdTmp(authorsTMP[i]);
                // add unique author
                if (idTmp == 0) {
                    Author newAuthor = new Author(authorsTMP[i]);
                    if (newAuthor != null) {
                        System.out.println("ADD Author (Service): " + newAuthor);
                        bookDaoImpl.addAuthor(newAuthor);
                    }
                }
                idTmp = getAuthorsIdTmp(authorsTMP[i]);

                if (idTmp != 0) {
                    Author authorTmp = bookDaoImpl.findAuthorById(idTmp);
                    if (authorTmp != null) {
                        authorsId.add(idTmp);
                    }
                }
            }
        }
        return authorsId;
    }

    private int getAuthorsIdTmp(String author) {
        int idTmp = 0;
        List<Author> authors = bookDaoImpl.findAllAuthors();
        for (Author a : authors) {
            if (a.getAuthor().equals(author)) {
                idTmp = a.getId();
            }
        }
        return idTmp;
    }

    public void addAuthor(String author) {
        AuthorDTO authorDTO = new AuthorDTO(author);
        getAuthorsId(authorDTO);
    }


    private List<Integer> getPublishersId(PublisherDTO publisherDTO) {
        List<Integer> publishersId = new CopyOnWriteArrayList<>();
        String[] publishersTMP = publisherDTO.getPublisher().split(",");

        for (int i = 0; i < publishersTMP.length; i++) {
            if (!publishersTMP[i].equals("")) {
                int idTmp = 0;
                idTmp = getPublishersIdTmp(publishersTMP[i]);
                // add unique author
                if (idTmp == 0) {
                    Publisher newPublisher = new Publisher(publishersTMP[i]);
                    if (newPublisher != null) {
                        System.out.println("ADD Publisher (Service): " + newPublisher);
                        bookDaoImpl.addPublisher(newPublisher);
                    }
                }
                idTmp = getPublishersIdTmp(publishersTMP[i]);

                if (idTmp != 0) {
                    Publisher publisherTmp = bookDaoImpl.findPublisherById(idTmp);
                    if (publisherTmp != null) {
                        publishersId.add(idTmp);
                    }
                }
            }
        }
        return publishersId;
    }


    private int getPublishersIdTmp(String publisher) {
        int idTmp = 0;
        List<Publisher> publishers = bookDaoImpl.findAllPublisers();
        for (Publisher p : publishers) {
            if (p.getPublisher().equals(publisher)) {
                idTmp = p.getId();
            }
        }
        return idTmp;
    }

    public void addPublisher(String publisher) {
        PublisherDTO publisherDTO = new PublisherDTO(publisher);
        getPublishersId(publisherDTO);
    }

    public void deleteBook(int id) {
//        bookDaoImpl.deleteDataInAuthorBookTable(id);
//        bookDaoImpl.deleteDataInPublisherBookTable(id);
        bookDaoImpl.deleteBook(id);
        System.out.println("After Book delete");
    }

    public void deleteAuthor(int id) {
        bookDaoImpl.deleteAuthor(id);
    }

    public void deletePublisher(int id) {
        bookDaoImpl.deletePublisher(id);
    }

    public List<Book> addFindBy(String findType, String data) {
        List<Book> booksRezult = new CopyOnWriteArrayList<>();
        List<Book> books = bookDaoImpl.findAllBooks();
        Book bookTmp = null;
        int id = 0;
        switch (findType) {
            case "title" -> {
                for (Book b : books) {
                    if (data.equals(b.getTitle().trim())) {
                        bookTmp = bookDaoImpl.findBookById(b.getId());
                        if (bookTmp != null) {
                            booksRezult.add(bookTmp);
                        }
                        break;
                    }
                }
            }
            case "author" -> {
                for (Book b : books) {
                    label:
                    for (Author a : b.getAuthors()) {
                        if (data.equals(a.getAuthor().trim())) {
                            bookTmp = bookDaoImpl.findBookById(b.getId());
                            if (bookTmp != null) {
                                booksRezult.add(bookTmp);
                            }
                            break label;
                        }
                    }
                }
            }
            case "publisher" -> {
                for (Book b : books) {
                    label:
                    for (Publisher p : b.getPublishers()) {
                        if (data.equals(p.getPublisher().trim())) {
                            bookTmp = bookDaoImpl.findBookById(b.getId());
                            if (bookTmp != null) {
                                booksRezult.add(bookTmp);
                            }
                            break label;
                        }
                    }
                }
            }
            default -> System.out.println("unknown type");
        }
        if (booksRezult != null) {
            System.out.println("List<Book> findBy isn't null");
            booksRezult.stream().forEach(System.out::println);
        } else {
            System.out.println("List<Book> findBy is null");
        }
        return booksRezult;
    }


//    public void addBook(BookDTO bookDTO, List<AuthorDTO> authorDTO) {
//        Set<Author> authors = getAuthorsSet(authorDTO);
//        if (authors != null){
//            Book newBook = new Book(bookDTO.getTitle(), bookDTO.getPages(), authors);
//            if (newBook != null){
//                bookDaoImpl.addBook(newBook);
//            }
//        }
//    }

//    private Set<Author> getAuthorsSet(List<AuthorDTO> authorsDTO) {
//        List<Author> authorsList = bookDaoImpl.findAllAuthors();
//        Set<Author> authorsSet = new HashSet<>();
//        for (AuthorDTO authorDTO : authorsDTO) {
//            int authorId = getAuthorId(authorsList, authorDTO);
//            if (authorId == 0) {
//                Author newAuthor = new Author(authorDTO.getAuthor());
//                if (newAuthor != null) {
//                    bookDaoImpl.addAuthor(newAuthor);
//                }
//            }
//            authorsList = bookDaoImpl.findAllAuthors();
//            authorId = getAuthorId(authorsList, authorDTO);
//            if (authorId != 0) {
//                authorsSet.add(bookDaoImpl.findAuthorById(authorId));
//            } else {
//                System.out.println("Problem with authors");
//            }
//        }
//        return authorsSet;
//    }
//
//    private static int getAuthorId(List<Author> authorsList, AuthorDTO authorDTO) {
//        int authorId = 0;
//        for (Author author : authorsList) {
//            if (author.getAuthor().equals(authorDTO.getAuthor())) {
//                authorId = author.getId();
//                break;
//            }
//        }
//        return authorId;
//    }


//        public void add(Book book){
//
//        }

//    public List<Author> getAllAuthors() {
//        TypedQuery<Author> query = entityManager.createQuery("select с from Author с", Author.class);
//        List<Author> authors = query.getResultList();
//        if (authors != null) {
//            authors.forEach(System.out::println);
//        }
//        return authors;
//    }
//
//    public List<Genre> getAllGenres() {
//        TypedQuery<Genre> query = entityManager.createQuery("select с from Genre с", Genre.class);
//        List<Genre> genres = query.getResultList();
//        if (genres != null) {
//            genres.forEach(System.out::println);
//        }
//        return genres;
//    }
//
//    public void addAuthor(String authorName) {
//        Author author = new Author(authorName);
//        entityManager.persist(author);
//    }
//
//    public void addGenre(String genreName) {
//        Genre genre = new Genre(genreName);
//        entityManager.persist(genre);
//    }

//    public void addBook(String name, String authorDb, String releaseYearDb, String genreDb, String amountOfPageDb, String description) {
//        int releaseYear = Integer.parseInt(releaseYearDb);
//        int amountOfPage = Integer.parseInt(amountOfPageDb);
//
//        try {
//            Book book = new Book(name, authorDb, releaseYear, genreDb, amountOfPage, description);
//            if (book != null) {
//                System.out.println(" addBook " + book.toString());
//            } else {
//                System.out.println(" addBook - null ");
//            }
//            entityManager.getTransaction().begin();
//            entityManager.persist(book);
//            entityManager.getTransaction().commit();
//        } catch (Throwable ex) {
//            ex.printStackTrace();
//            entityManager.getTransaction().rollback();
//        }
//    }
//
//    public void remove(String idDb) {
//        int id = Integer.parseInt(idDb);
//        try {
//            Book book = findBookById(id);
//            if (book != null) {
//                System.out.println(" removeBook " + book.toString());
//            } else {
//                System.out.println(" addBook - null ");
//            }
//            entityManager.getTransaction().begin();
//            entityManager.remove(book);
//            entityManager.getTransaction().commit();
//        } catch (Throwable ex) {
//            ex.printStackTrace();
//            entityManager.getTransaction().rollback();
//        }
//    }
//
//
//    public void updateBook(String editType, String editNewData, String editId) {
//        int id = Integer.parseInt(editId);
//        Book book = findBookById(id);
//        switch (editType) {
//            case "name" -> book.setName(editNewData);
//            case "author" -> book.setAuthor(editNewData);
//            case "year" -> {
//                if (isDigit(editNewData)) {
//                    int releaseYear = Integer.parseInt(editNewData);
//                    book.setReleaseYear(releaseYear);
//                }
//            }
//            case "genre" -> book.setGenre(editNewData);
//
//            case "amount" -> {
//                if (isDigit(editNewData)) {
//                    int amountOfPage = Integer.parseInt(editNewData);
//                    book.setAmountOfPage(amountOfPage);
//                }
//            }
//            case "description" -> book.setDescription(editNewData);
//            default -> System.out.println("unknown type");
//        }
//
//        try {
//            entityManager.getTransaction().begin();
//            entityManager.merge(book);		// внесли изменения в таблицу БД
//            entityManager.getTransaction().commit();
//        } catch (Throwable ex){
//            ex.printStackTrace();
//            entityManager.getTransaction().rollback();
//        }
//
//    }
//
//    private boolean isDigit(String s) throws NumberFormatException {
//        try {
//            Integer.parseInt(s);
//            return true;
//        } catch (NumberFormatException e) {
//            return false;
//        }
//    }
//
//    public Book findBy(String findType, String data) {
//        List <Book> books = getAllBooks();
//        int id = 0;
//        for (Book b:books){
//            switch (findType) {
//                case "name" -> {
//                    if (b.getName().equals(data)){
//                        id = b.getId();
//                        break;
//                    }
//                }
//                case "author" -> {
//                    if (b.getAuthor().equals(data)){
//                        id = b.getId();
//                        break;
//                    }
//                }
//                case "year" -> {
//                    if (isDigit(data)) {
//                        int releaseYear = Integer.parseInt(data);
//                        if (b.getReleaseYear() == releaseYear){
//                            id = b.getId();
//                            break;
//                        }
//                    }
//                }
//                case "genre" -> {
//                    if (b.getGenre().equals(data)){
//                        id = b.getId();
//                        break;
//                    }
//                }
//                case "amount" -> {
//                    if (isDigit(data)) {
//                        int amountOfPage = Integer.parseInt(data);
//                        if (b.getAmountOfPage() == amountOfPage){
//                            id = b.getId();
//                            break;
//                        }
//                    }
//                }
//                case "description" -> {
//                    if (b.getDescription().equals(data)){
//                        id = b.getId();
//                        break;
//                    }
//                }
//                default -> System.out.println("unknown type");
//            }
//        }
//        return findBookById(id);
//    }

}




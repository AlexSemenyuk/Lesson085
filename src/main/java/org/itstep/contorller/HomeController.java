package org.itstep.contorller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.itstep.dao.BookDaoImpl;
//import org.itstep.entity.Author;
//import org.itstep.entity.AuthorDTO;
import org.itstep.entity.*;
//import org.itstep.entity.BookDTO;
import org.itstep.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

//@RequiredArgsConstructor
@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {
//    private final BookDaoImpl bookDaoImpl;
    //    private final BookDaoJdbc bookDaoJdbc;
    private final BookService bookService;


    @GetMapping("/")
    public String home(Model model) {
        List<Book> books = bookService.findAllBooks();
        if (books != null){
            log.info("books {}", books);
            model.addAttribute("books", books);
        }
        List<Author> authors = bookService.findAllAuthors();
        if (authors != null){
            model.addAttribute("authors", authors);
        }
        List<Publisher> publishers = bookService.findAllPublishers();
        if (publishers != null){
            model.addAttribute("publishers", publishers);
        }
        return "home";
    }

    @PostMapping("/")
    public String create(BookDTO bookDTO, AuthorDTO authorDTO, PublisherDTO publisherDTO,
                         String addauthor, String addpublisher, String find, String finddata,
                         Model model, HttpSession session) {
        if (bookDTO.getTitle() != null &&
                authorDTO.getAuthor() != null &&
                publisherDTO.getPublisher() != null) {
            log.info("bookDTO {}", bookDTO);
            log.info("authorDTO {}", authorDTO);
            log.info("publisherDTO {}", publisherDTO);
            System.out.println("bookDTO.toString() = " + bookDTO.toString());
            System.out.println("authorDTO.toString() = " + authorDTO.toString());
            System.out.println("publisherDTO.toString() = " + publisherDTO.toString());
            bookService.addBook(bookDTO, authorDTO, publisherDTO);
        } else {
            System.out.println("Create book - null");
        }
        if (addauthor != null && !addauthor.isBlank()) {
            System.out.println("addauthor.toString() = " + addauthor);
            bookService.addAuthor(addauthor);
        } else {
            System.out.println("Create author - null");
        }
        if (addpublisher != null && addpublisher != null) {
            System.out.println("addauthor.toString() = " + addauthor);
            bookService.addPublisher(addpublisher);
        } else {
            System.out.println("Create publisher - null");
        }
        if (find != null && !find.isBlank() &&
                finddata != null && !finddata.isBlank()) {
            System.out.println("find = " + find);
            System.out.println("finddata = " + finddata);
            List <Book> booksFind = bookService.addFindBy(find, finddata);
            if (booksFind != null){
                session.setAttribute("find", booksFind);
            }
        } else {
            System.out.println("Find bookList - null");
        }
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String deleteBook(@PathVariable int id) {
        log.info("delete book by id {}", id);
        bookService.deleteBook(id);
//        bookDaoImpl.delete(id);
        return "redirect:/";
    }

    @GetMapping("author/{id}")
    public String deleteAuthor(@PathVariable int id) {
        log.info("delete book by id {}", id);
        bookService.deleteAuthor(id);
        return "redirect:/";
    }

    @GetMapping("publisher/{id}")
    public String deletePublisher(@PathVariable int id) {
        log.info("delete book by id {}", id);
        bookService.deletePublisher(id);
        return "redirect:/";
    }
//
//    @GetMapping("/post/{id}")
//    public String postDetails(@PathVariable int id, Model model) {
//        log.info("find post by id {}", id);
//        model.addAttribute("post", postDao.findById(id));
//        return "details";
//    }
//
//    @GetMapping("/post/delete/{id}")
//    public String deletePost(@PathVariable int id) {
//        log.info("delete post by id {}", id);
//        postDao.delete(id);
//        return "redirect:/post";
//    }


}

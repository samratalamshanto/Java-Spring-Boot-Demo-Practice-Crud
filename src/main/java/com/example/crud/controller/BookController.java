package com.example.crud.controller;

import com.example.crud.entity.Books;
import com.example.crud.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    BookService bookService;

    //getmapping
    @GetMapping("/book")
    private List<Books> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/book/{bookid}")
    private Books getBook(@PathVariable("bookid") int bookid) {
        return bookService.getById(bookid);
    }

    @GetMapping("/book/price")
    public List<Books> getBookPriceDecs()
    {
        return bookService.showLowToHighPrice();
    }

    @GetMapping("/book/maxprice")
    public Books getHighestPrice()
    {
        //max price
        return bookService.getHighPrice();
    }

    //post
    @PostMapping("/book")
    private List<Books> postBook(@RequestBody Books book) {
        bookService.saveBook(book);
        return bookService.getAllBooks();
    }

    @PostMapping("/book/all")
    private List<Books> postManyBooks(@RequestBody List<Books> manyBooks){
        bookService.saveManyBook(manyBooks);
        return bookService.getAllBooks();

    }

    //delete mapping
    @DeleteMapping("/book/{bookid}")
    private String deleteBook(@PathVariable("bookid") int bookid) {
        Books book = bookService.getById(bookid);
        if(bookService.exist(bookid))
        {
            bookService.delete(bookid);
            return "Successfully Deleted";
        }
        else {
            return "Book not found!!!";
        }
    }

    @PutMapping("/book/{id}")
    private List<Books> updateBook(@RequestBody Books book, @PathVariable("id") int id)
    {
        bookService.update(book,id);
        return bookService.getAllBooks();
    }

}

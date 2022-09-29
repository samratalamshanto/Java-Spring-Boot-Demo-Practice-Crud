package com.example.crud.service;

import com.example.crud.entity.Books;
import com.example.crud.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.ArrayList;
import java.util.List;

//logic
@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public boolean exist(int id){
        if(bookRepository.findById(id).isPresent())
        {
            return true;
        }else return false;

    }

    //get
    public List<Books> getAllBooks() {
        List<Books> book = new ArrayList<Books>();
        bookRepository.findAll().forEach((singleBook) -> book.add(singleBook));
        return book;
    }

//    public void showNameById(int id) {
//        bookRepository.showNameById(id);
//    }

    public List<Books> showLowToHighPrice() {
        List<Books> books = new ArrayList<>();
        bookRepository.findAll(Sort.by(Sort.Direction.ASC, "price")).forEach(singleBook -> books.add(singleBook));

        return books;
    }

    public Books getById(int id) {
        Books book = new Books();
        book = bookRepository.findById(id).get();
        return book;
    }

   // @Query(value = "select max(price) from Books")
    public Books getHighPrice(){
        List<Books> book= new ArrayList<>();

        book = bookRepository.findAll(Sort.by(Sort.Direction.DESC,"price"));

        return book.get(0);
    }

    //put or save
    public void saveBook(Books book) {
        // System.out.println(book);
        bookRepository.save(book);
    }

    public void saveManyBook(List<Books> manyBooks)
    {
      manyBooks.forEach(singleBook->bookRepository.save(singleBook));
    }

    //put
    public void update(Books book, int id) {
        Books updateBook = bookRepository.findById(id).get();

        //use getter setter to update
        updateBook.setId(id);
        updateBook.setAuthor(book.getAuthor());
        updateBook.setName(book.getName());
        updateBook.setPrice(book.getPrice());
        updateBook.setQty(book.getQty());

        bookRepository.save(updateBook);
    }

    //delete
    public void delete(int id) {
        bookRepository.deleteById(id);
    }



}

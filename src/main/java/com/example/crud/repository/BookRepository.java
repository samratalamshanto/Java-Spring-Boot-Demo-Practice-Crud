package com.example.crud.repository;

import com.example.crud.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Books,Integer> {
  //  public void showNameById(int id);
   //  List<Books> showLowToHighPrice();

}

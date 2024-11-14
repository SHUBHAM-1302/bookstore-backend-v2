package com.rith.id.controller;

import com.rith.id.dto.BookDetailDto;
import com.rith.id.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("v1/md/book/")
    public ResponseEntity<BookDetailDto> postBook(@RequestBody BookDetailDto bookDetailDto){
        return ResponseEntity.ok(bookService.postBookDetail(bookDetailDto));
    }

    @GetMapping("v1/md/book/")
    public ResponseEntity<List<BookDetailDto>> getBooks(){
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("v1/md/book/{id}/")
    public ResponseEntity<BookDetailDto> getBookById(@PathVariable(value = "id",required = true) Long id){
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PutMapping("v1/md/book/{id}/")
    public ResponseEntity<BookDetailDto> putBookById(@PathVariable(value = "id",required = true) Long id,@RequestBody BookDetailDto bookDetailDto){
        return ResponseEntity.ok(bookService.updateBookDetail(id,bookDetailDto));
    }

}

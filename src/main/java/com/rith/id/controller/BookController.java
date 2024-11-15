package com.rith.id.controller;

import com.rith.id.dto.BookDetailDto;
import com.rith.id.dto.BookDto;
import com.rith.id.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("api/books")
    public ResponseEntity<BookDetailDto> postBook(@RequestBody BookDetailDto bookDetailDto){
        return ResponseEntity.ok(bookService.postBookDetail(bookDetailDto));
    }

    @GetMapping("api/books")
    public ResponseEntity<List<BookDto>> getBooks(@RequestParam(name = "status",required = false,defaultValue = "available") String status){
        return ResponseEntity.ok(bookService.getAllBooks(status));
    }

    @GetMapping("api/books/{id}")
    public ResponseEntity<BookDetailDto> getBookById(@PathVariable(value = "id",required = true) Long id){
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PutMapping("api/books/{id}")
    public ResponseEntity<BookDetailDto> putBookById(@PathVariable(value = "id",required = true) Long id,@RequestBody BookDetailDto bookDetailDto){
        return ResponseEntity.ok(bookService.updateBookDetail(id,bookDetailDto));
    }

    @PutMapping("api/books/{id}/purchase")
    public ResponseEntity<BookDetailDto> purchaseBook(@PathVariable(value = "id",required = true) Long id){
        return ResponseEntity.ok(bookService.updateBookToPurchased(id));
    }
}

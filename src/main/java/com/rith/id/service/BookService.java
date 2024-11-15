package com.rith.id.service;

import com.rith.id.dto.BookDetailDto;
import com.rith.id.dto.BookDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookService {
    List<BookDto> getAllBooks(String status);

    @Transactional(readOnly = true)
    BookDetailDto getBookById(Long id);

    BookDetailDto postBookDetail(BookDetailDto bookDetailDto);

    BookDetailDto updateBookDetail(Long id, BookDetailDto bookDetailDto);

    BookDetailDto updateBookToPurchased(Long id);
}

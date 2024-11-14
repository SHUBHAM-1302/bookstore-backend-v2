package com.rith.id.service;

import com.rith.id.dto.BookDetailDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookService {
    List<BookDetailDto> getAllBooks();

    @Transactional(readOnly = true)
    BookDetailDto getBookById(Long id);

    BookDetailDto postBookDetail(BookDetailDto bookDetailDto);

    BookDetailDto updateBookDetail(Long id, BookDetailDto bookDetailDto);
}

package com.rith.id.service;

import com.rith.id.constants.Status;
import com.rith.id.dto.BookDetailDto;
import com.rith.id.dto.BookDto;
import com.rith.id.entity.Books;
import com.rith.id.exception.RecordNotFoundException;
import com.rith.id.repository.BookRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.rith.id.util.CurrencyUtil.fromUSD;
import static com.rith.id.util.CurrencyUtil.toUSD;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;

    @Override
    @Transactional(readOnly = true)
    public List<BookDto> getAllBooks(String status){
        List<Books> books;
        if("all".equalsIgnoreCase(status)){
            books = bookRepository.findAll();
        }
        else {
            books = bookRepository.findAllByStatus(status);
        }
        return transformBookToBookDto(books);
    }

    @Override
    @Transactional(readOnly = true)
    public BookDetailDto getBookById(Long id){
        Optional<Books> book = bookRepository.findById(id);
        if(book.isEmpty()){
            throw new RecordNotFoundException("Book with "+id+" not found","Use book with valid id");
        }
        return transformBookToBookDetailDto(book.get());
    }

    @Override
    @Transactional
    public BookDetailDto postBookDetail(BookDetailDto bookDetailDto){
        Books books = transformBokDetailDtoToBook(bookDetailDto);
        //
        books = bookRepository.save(books);
        bookDetailDto.setBookId(books.getBookId());
        return bookDetailDto;
    }

    @Override
    @Transactional
    public BookDetailDto updateBookDetail(Long id, BookDetailDto bookDetailDto){
        Books books;
        Optional<Books> optionalBook = bookRepository.getByIdWithPessimisticLock(id);
        if(optionalBook.isEmpty()){
            throw new RecordNotFoundException("Book with "+id+" not found","Use book with valid id");
        }
        books = optionalBook.get();
        //
        exchangeValues(books,bookDetailDto);
        //
        books = bookRepository.save(books);
        bookDetailDto.setBookId(books.getBookId());
        return bookDetailDto;
    }

    @Override
    @Transactional
    public BookDetailDto updateBookToPurchased(Long id) {
        Books books;
        Optional<Books> optionalBook = bookRepository.getByIdWithPessimisticLock(id);
        if(optionalBook.isEmpty()){
            throw new RecordNotFoundException("Book with "+id+" not found","Use book with valid id");
        }
        books = optionalBook.get();
        if(Status.SOLD.toString().equalsIgnoreCase(books.getStatus())){
            throw new RecordNotFoundException("Book with "+id+" not found","Use book with valid id");
        }
        books.setStatus(Status.SOLD.toString());
        bookRepository.save(books);

        return transformBookToBookDetailDto(books);
    }

    private void exchangeValues(Books books, BookDetailDto bookDetailDto) {
        books.setAuthor(bookDetailDto.getAuthor());
        books.setTitle(bookDetailDto.getTitle());
        books.setSummary(bookDetailDto.getSummary());
        books.setPrice(fromUSD(bookDetailDto.getPrice()));
        books.setStatus(bookDetailDto.getStatus());
    }

    public BookDetailDto transformBookToBookDetailDto(Books books){
        BookDetailDto bookDetailDto = new BookDetailDto();
        bookDetailDto.setSummary(books.getSummary());
        bookDetailDto.setPrice(toUSD(books.getPrice()));
        bookDetailDto.setStatus(books.getStatus());
        bookDetailDto.setAuthor(books.getAuthor());
        bookDetailDto.setTitle(books.getTitle());
        bookDetailDto.setBookId(books.getBookId());
        return bookDetailDto;
    }

    public BookDto transformBookToBookDto(Books books){
        BookDto bookDto = new BookDto();
        bookDto.setPrice(toUSD(books.getPrice()));
        bookDto.setAuthor(books.getAuthor());
        bookDto.setTitle(books.getTitle());
        bookDto.setBookId(books.getBookId());
        return bookDto;
    }

    public List<BookDto> transformBookToBookDto(List<Books> books){
        List<BookDto> bookDetailDtos = new ArrayList<>();
        for (Books book: books){
            bookDetailDtos.add(transformBookToBookDto(book));
        }
        return bookDetailDtos;
    }

    public Books transformBokDetailDtoToBook(BookDetailDto bookDetailDto){
        Books books = new Books();
        books.setAuthor(bookDetailDto.getAuthor());
        books.setBookId(bookDetailDto.getBookId());
        books.setSummary(bookDetailDto.getSummary());
        books.setTitle(bookDetailDto.getTitle());
        books.setPrice(fromUSD(bookDetailDto.getPrice()));
        books.setStatus(bookDetailDto.getStatus());
        return books;
    }


    @PostConstruct
    public void onInit(){
        if(bookRepository.count() == 0) {
            List<Books> books = new ArrayList<>();
            books.add(Books.builder()
                    .title("Book 1")
                    .author("Ram Ghor")
                    .summary("consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et ")
                    .price(BigDecimal.valueOf(29.99))
                    .status(Status.AVAILABLE.toString())
                    .build());
            books.add(Books.builder()
                    .title("Book 2")
                    .author("Mar rohg")
                    .summary(" veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem")
                    .price(BigDecimal.valueOf(11111119.95))
                    .status(Status.AVAILABLE.toString())
                    .build());
            books.add(Books.builder()
                    .title("Book 3")
                    .author("Sham Sundar")
                    .summary(" veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem")
                    .price(BigDecimal.valueOf(59.99))
                    .status(Status.AVAILABLE.toString())
                    .build());
            books.add(Books.builder()
                    .title("Book 4")
                    .author("Mash Radnus")
                    .summary(" veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem")
                    .price(BigDecimal.valueOf(14.99))
                    .status(Status.SOLD.toString())
                    .build());
            bookRepository.saveAll(books);
        }
    }
}

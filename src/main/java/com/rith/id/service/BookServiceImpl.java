package com.rith.id.service;

import com.rith.id.dto.BookDetailDto;
import com.rith.id.entity.Book;
import com.rith.id.repository.BookRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<BookDetailDto> getAllBooks(){
        return transformBookToBookDetailDto(bookRepository.findAll());
    }

    @Override
    public BookDetailDto getBookById(Long id){
        Optional<Book> book = bookRepository.findById(id);
        if(book.isEmpty()){
            return null;
        }
        return transformBookToBookDetailDto(book.get());
    }

    @Override
    public BookDetailDto postBookDetail(BookDetailDto bookDetailDto){
        Book book = transformBokDetailDtoToBook(bookDetailDto);
        //
        book = bookRepository.save(book);
        bookDetailDto.setBookId(book.getBookId());
        return bookDetailDto;
    }

    @Override
    public BookDetailDto updateBookDetail(Long id, BookDetailDto bookDetailDto){
        Book book;
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isEmpty()){
            throw new IllegalArgumentException();
        }
        book = optionalBook.get();
        //
        exchangeValues(book,bookDetailDto);
        //
        book = bookRepository.save(book);
        bookDetailDto.setBookId(book.getBookId());
        return bookDetailDto;
    }

    private void exchangeValues(Book book, BookDetailDto bookDetailDto) {
        book.setAuthor(bookDetailDto.getAuther());
        book.setTitle(bookDetailDto.getTitle());
        book.setSummary(bookDetailDto.getDescription());
        book.setPrice(bookDetailDto.getRate());
        book.setIsSold(bookDetailDto.getIsSold());
    }

    public BookDetailDto transformBookToBookDetailDto(Book book){
        BookDetailDto bookDetailDto = new BookDetailDto();
        bookDetailDto.setDescription(book.getSummary());
        bookDetailDto.setRate(book.getPrice());
        bookDetailDto.setIsSold(book.getIsSold());
        bookDetailDto.setAuther(book.getAuthor());
        bookDetailDto.setTitle(book.getTitle());
        bookDetailDto.setBookId(book.getBookId());
        return bookDetailDto;
    }

    public List<BookDetailDto> transformBookToBookDetailDto(List<Book> books){
        List<BookDetailDto> bookDetailDtos = new ArrayList<>();
        for (Book book: books){
            bookDetailDtos.add(transformBookToBookDetailDto(book));
        }
        return bookDetailDtos;
    }

    public Book transformBokDetailDtoToBook(BookDetailDto bookDetailDto){
        Book book = new Book();
        book.setAuthor(bookDetailDto.getAuther());
        book.setBookId(bookDetailDto.getBookId());
        book.setSummary(bookDetailDto.getDescription());
        book.setTitle(bookDetailDto.getTitle());
        book.setPrice(bookDetailDto.getRate());
        book.setIsSold(bookDetailDto.getIsSold());
        return book;
    }


    @PostConstruct
    public void onInit(){
        if(bookRepository.count() == 0) {
            List<Book> books = new ArrayList<>();
            books.add(Book.builder()
                    .title("Book 1")
                    .author("Ram Ghor")
                    .summary("consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et ")
                    .price(29.99)
                    .isSold(false)
                    .build());
            books.add(Book.builder()
                    .title("Book 2")
                    .author("Mar rohg")
                    .summary(" veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem")
                    .price(19.95)
                    .isSold(false)
                    .build());
            books.add(Book.builder()
                    .title("Book 3")
                    .author("Sham Sundar")
                    .summary(" veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem")
                    .price(59.99)
                    .isSold(false)
                    .build());
            books.add(Book.builder()
                    .title("Book 4")
                    .author("Mash Radnus")
                    .summary(" veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem")
                    .price(14.99)
                    .isSold(false)
                    .build());
            bookRepository.saveAll(books);
        }
    }
}

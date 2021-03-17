package com.inconcept.task.service;


import com.inconcept.task.config.BookCSVConfig;
import com.inconcept.task.persistence.entity.AuthorEntity;
import com.inconcept.task.persistence.entity.BookEntity;
import com.inconcept.task.persistence.repository.BookRepository;
import com.inconcept.task.service.criteria.SearchCriteria;
import com.inconcept.task.service.dto.BookDto;
import com.inconcept.task.service.model.ContentQuery;
import com.inconcept.task.service.model.UploadImgThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookCSVConfig bookCSVConfig;

    @Value("${image.upload.bookUrl}")
    private String bookImgUrl;

    @Autowired
    public BookService(BookRepository bookRepository, BookCSVConfig bookCSVConfig) {
        this.bookRepository = bookRepository;
        this.bookCSVConfig = bookCSVConfig;
    }

    public ContentQuery<BookDto> getBooks(SearchCriteria searchCriteria) {
        Page<BookEntity> bookDtoPage = bookRepository.findAllWithPage(searchCriteria.composePageRequest());
        return new ContentQuery<BookDto>(bookDtoPage.getTotalPages(), BookDto.castEntityToDo(bookDtoPage.getContent()));
    }

    public void saveCsvToDataBase(MultipartFile file) {
        try {
            List<BookDto> dtoList = bookCSVConfig.csvToTutorials(file.getInputStream());

            UploadImgThread uploadImgThread = new UploadImgThread(bookImgUrl, dtoList.stream().map(BookDto::getPicUrl).collect(Collectors.toList()));
            uploadImgThread.start();

            bookRepository.saveAll(BookDto.castDtoToEntity(dtoList));
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public BookDto addBook(BookEntity bookEntity) {

        bookRepository.save(bookEntity);
        return BookDto.castEntityToDo(bookEntity);
    }

    public Double getBookRate(Long bookId) {
        BookEntity bookEntity = bookRepository.findOneById(bookId);
        BookDto bookDto = BookDto.castEntityToDo(bookEntity);
        return bookDto.getAvgRating();
    }

    public BookDto getBook(Long id) throws Exception {
        BookEntity bookEntity = bookRepository.findById(id)
                .orElseThrow(() -> new Exception(String.format("Book which id %d not found", id)));
        ;

        return BookDto.castEntityToDo(bookEntity);
    }

    public void deleteBookById(Long id) throws Exception {
        bookRepository.findById(id)
                .orElseThrow(() -> new Exception(String.format("Book which id %d not found", id)));
        bookRepository.deleteById(id);
    }

    public BookDto updateBook(Long id, BookDto bookDto) throws Exception {
        BookEntity bookEntity = bookRepository.findById(id)
                .orElseThrow(() -> new Exception(String.format("Book which id %d not found", id)));


        if (bookDto.getTitle() != null) {
            bookEntity.setTitle(bookDto.getTitle());
        }
//        if (bookDto.getDesc() != null) {
//            bookEntity.setDesc(bookDto.getDesc());
//        }
        if (bookDto.getPublishDate() != null) {
            bookEntity.setPublishDate(bookDto.getPublishDate());
        }
//        if (bookDto.getPrice() != null) {
//            bookEntity.setPrice(bookDto.getPrice());
//        }
        bookEntity = bookRepository.save(bookEntity);
        return BookDto.castEntityToDo(bookEntity);
    }
}

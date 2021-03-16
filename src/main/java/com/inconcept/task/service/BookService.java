package com.inconcept.task.service;


import com.inconcept.task.persistence.entity.BookEntity;
import com.inconcept.task.persistence.repository.BookRepository;
import com.inconcept.task.service.criteria.SearchCriteria;
import com.inconcept.task.service.dto.BookDto;
import com.inconcept.task.service.model.ContentQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public ContentQuery<BookDto> getBooks(SearchCriteria searchCriteria) {

        Page<BookEntity> bookDtoPage = bookRepository.findAllWithPage(searchCriteria.composePageRequest());
        return new ContentQuery<BookDto>(bookDtoPage.getTotalPages(), BookDto.castEntityToDo(bookDtoPage.getContent()));
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

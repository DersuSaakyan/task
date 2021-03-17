package com.inconcept.task.controller;

import com.inconcept.task.config.BookCSVConfig;
import com.inconcept.task.persistence.entity.BookEntity;
import com.inconcept.task.service.BookService;
import com.inconcept.task.service.criteria.SearchCriteria;
import com.inconcept.task.service.dto.BookDto;
import com.inconcept.task.service.model.ContentQuery;
import com.inconcept.task.service.model.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping()
    public ContentQuery<BookDto> getBooks(SearchCriteria searchCriteria) {
        return bookService.getBooks(searchCriteria);
    }

    @PostMapping()
    public ResponseEntity<BookDto> addBook(@RequestBody BookEntity bookEntity) throws Exception {
        if (bookEntity.getTitle() == null) {
            throw new Exception("Fill required fields");
        }
        BookDto bookDto = bookService.addBook(bookEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookDto);
    }

    @PostMapping("/uploadCsv")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (BookCSVConfig.hasCSVFormat(file)) {
            try {
                bookService.saveCsvToDataBase(file);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                System.out.println(e.toString());
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message, ""));
            }
        }

        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message, ""));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable() Long id) throws Exception {
        return ResponseEntity.ok(bookService.getBook(id));
    }


    @GetMapping("/rate")
    public Double getBookRating(@RequestParam("bookId") Long id) {
        return bookService.getBookRate(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable() Long id, @RequestBody BookDto bookDto) throws Exception {
        BookDto dto = bookService.updateBook(id, bookDto);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }


    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable("id") Long id) throws Exception {
        bookService.deleteBookById(id);
    }
}

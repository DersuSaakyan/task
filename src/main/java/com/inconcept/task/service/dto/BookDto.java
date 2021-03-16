package com.inconcept.task.service.dto;


import com.inconcept.task.persistence.entity.AuthorEntity;
import com.inconcept.task.persistence.entity.BookEntity;
import com.inconcept.task.persistence.entity.RateEntity;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class BookDto {
    private String title;
    private String publishDate;
    private String publisher;
    private Double avgRating;
    private Set<String> bookAuthors;


    public BookDto(String title, String publishDate,String publisher, Double avgRating, Set<String> bookAuthors) {
        this.title = title;
        this.publishDate = publishDate;
        this.publisher = publisher;
        this.avgRating = avgRating;
        this.bookAuthors = bookAuthors;
    }

    public BookDto() {

    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public Double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Double avgRating) {
        this.avgRating = avgRating;
    }

    public Set<String> getBookAuthors() {
        return bookAuthors;
    }

    public void setBookAuthors(Set<String> bookAuthors) {
        this.bookAuthors = bookAuthors;
    }

    public static Double getAvgRatingBook(List<RateEntity> rateEntityList) {
        if (!CollectionUtils.isEmpty(rateEntityList)) {
            double temp = rateEntityList.stream().map(RateEntity::getRate).mapToDouble(Double::doubleValue).sum();
            return temp / rateEntityList.size();
        }
        return null;
    }

    public static List<BookDto> castEntityToDo(List<BookEntity> bookDtoList) {
        return bookDtoList.stream().map(b -> castEntityToDo(b)).collect(Collectors.toList());
    }

    public static BookDto castEntityToDo(BookEntity bookEntity) {
        if (bookEntity == null) {
            return null;
        }
        BookDto bookDto = new BookDto();
        bookDto.setTitle(bookEntity.getTitle());
        bookDto.setPublishDate(bookEntity.getPublishDate());
        bookDto.setPublisher(bookEntity.getPublisher());
        bookDto.setAvgRating(BookDto.getAvgRatingBook(bookEntity.getListBookRates()));

        Set<AuthorEntity> authorEntitySet = bookEntity.getBookAuthors();
        if (!CollectionUtils.isEmpty(authorEntitySet)) {
            bookDto.setBookAuthors(bookEntity.getBookAuthors().stream().map(AuthorEntity::getFullName).collect(Collectors.toSet()));
        }
        return bookDto;
    }
}

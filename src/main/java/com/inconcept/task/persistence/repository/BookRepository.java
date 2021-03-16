package com.inconcept.task.persistence.repository;


import com.inconcept.task.persistence.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
    BookEntity findOneById(Long id);

//    @Query("SELECT new com.inconcept.demo.service.model.BookWrapper(b.title,b.desc,b.publishDate,b.price) from BookEntity b")
//    Page<BookWrapper> findAllWithPage(Pageable pageable);

//    @Query("SELECT new com.inconcept.demo.service.dto.BookDto(b) from BookEntity b")
//    Page<BookDto> findAllWithPage(Pageable pageable);

    @Query("SELECT b from BookEntity b")
    Page<BookEntity> findAllWithPage(Pageable pageable);

}


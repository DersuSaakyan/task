package com.inconcept.task.persistence.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "book")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "publish_date")
    private String publishDate;

    @Column
    private String publisher;

    @Column(name = "pic_url")
    private String picUrl;

    @OneToMany(mappedBy = "book", targetEntity = RateEntity.class, fetch = FetchType.LAZY)
    private List<RateEntity> listBookRates;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "authorbook",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<AuthorEntity> bookAuthors = new HashSet<>();


    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<RateEntity> getListBookRates() {
        return listBookRates;
    }

    public void setListBookRates(List<RateEntity> listBookRates) {
        this.listBookRates = listBookRates;
    }

    public Set<AuthorEntity> getBookAuthors() {
        return bookAuthors;
    }

    public void setBookAuthors(Set<AuthorEntity> bookAuthors) {
        this.bookAuthors = bookAuthors;
    }
}

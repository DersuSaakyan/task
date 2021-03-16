package com.inconcept.task.persistence.entity;


import javax.persistence.*;

@Entity
@Table(name = "author")
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String country;

    @Column(name = "date_of_birth", nullable = false)
    private String dateOfBirth;

    @Column(name = "date_of_died", nullable = false)
    private String dateOfDied;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfDied() {
        return dateOfDied;
    }

    public void setDateOfDied(String dateOfDied) {
        this.dateOfDied = dateOfDied;
    }
}

package com.inconcept.task.persistence.entity;



import com.inconcept.task.persistence.entity.enums.UserEnum;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String firstName;

    @Column(name = "surname", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "hash_pass", nullable = false)
    private String password;

    @Column
    private String status;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserEnum userEnum;

    @OneToMany(mappedBy = "user", targetEntity = RateEntity.class, fetch = FetchType.LAZY)
    private List<RateEntity> listUserRates;

    @OneToMany(mappedBy = "user", targetEntity = CollectionsEntity.class, fetch = FetchType.LAZY)
    private List<CollectionsEntity> collectionsEntityList;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserEnum getUserEnum() {
        return userEnum;
    }

    public void setUserEnum(UserEnum userEnum) {
        this.userEnum = userEnum;
    }

    public List<RateEntity> getListUserRates() {
        return listUserRates;
    }

    public void setListUserRates(List<RateEntity> listUserRates) {
        this.listUserRates = listUserRates;
    }

    public List<CollectionsEntity> getCollectionsEntityList() {
        return collectionsEntityList;
    }

    public void setCollectionsEntityList(List<CollectionsEntity> collectionsEntityList) {
        this.collectionsEntityList = collectionsEntityList;
    }
}

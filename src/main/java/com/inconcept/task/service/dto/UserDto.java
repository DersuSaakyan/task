package com.inconcept.task.service.dto;


import com.inconcept.task.persistence.entity.BookEntity;
import com.inconcept.task.persistence.entity.RateEntity;
import com.inconcept.task.persistence.entity.UserEntity;
import com.inconcept.task.persistence.entity.enums.UserEnum;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;


public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String status;
    private UserEnum userEnum;
    private List<String> ratesBooks;

    public UserDto(String firstName, String lastName, String email, String status, UserEnum userEnum, List<String> ratesBooks) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.status = status;
        this.userEnum = userEnum;
        this.ratesBooks = ratesBooks;
    }

    public UserDto() {

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

    public List<String> getRatesBooks() {
        return ratesBooks;
    }

    public void setRatesBooks(List<String> ratesBooks) {
        this.ratesBooks = ratesBooks;
    }

    public static List<UserDto> castEntityToDo(List<UserEntity> userEntities) {
        return userEntities.stream().map(u -> castEntityToDo(u)).collect(Collectors.toList());
    }

    public static UserDto castEntityToDo(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        UserDto userDto = new UserDto();
        userDto.setFirstName(userEntity.getFirstName());
        userDto.setLastName(userEntity.getLastName());
        userDto.setEmail(userEntity.getEmail());
        userDto.setStatus(userEntity.getStatus());
        userDto.setUserEnum(userEntity.getUserEnum());

        List<RateEntity> rateEntities = userEntity.getListUserRates();
        if (!CollectionUtils.isEmpty(rateEntities)) {
            userDto.setRatesBooks(rateEntities.stream().map(RateEntity::getBook).map(BookEntity::getTitle).collect(Collectors.toList()));
        }
        return userDto;
    }
}

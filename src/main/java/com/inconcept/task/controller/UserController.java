package com.inconcept.task.controller;

import com.inconcept.task.persistence.entity.UserEntity;
import com.inconcept.task.service.UserService;
import com.inconcept.task.service.criteria.SearchCriteria;
import com.inconcept.task.service.dto.UserDto;
import com.inconcept.task.service.model.ContentQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ContentQuery<UserDto> getUsers(SearchCriteria searchCriteria) {
        return userService.getUsers(searchCriteria);
    }


    @PostMapping()
    public ResponseEntity<UserDto> registerUser(@RequestBody UserEntity userEntity) throws Exception {
        if (userEntity.getFirstName() == null || userEntity.getLastName() == null || userEntity.getEmail() == null || userEntity.getPassword() == null) {
            throw new Exception("Fill required fields");
        }
        UserDto userDto = userService.registerUser(userEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable() Long id) throws Exception {
        UserDto userDto = userService.getUser(id);
        return ResponseEntity.ok(userDto);
    }
}

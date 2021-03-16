package com.inconcept.task.service;



import com.inconcept.task.persistence.entity.UserEntity;
import com.inconcept.task.persistence.entity.enums.UserEnum;
import com.inconcept.task.persistence.repository.UserRepository;
import com.inconcept.task.service.criteria.SearchCriteria;
import com.inconcept.task.service.dto.UserDto;
import com.inconcept.task.service.model.ContentQuery;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ContentQuery<UserDto> getUsers(SearchCriteria searchCriteria) {

        Page<UserEntity> userDtoPage = userRepository.findAllWithPage(searchCriteria.composePageRequest());
        return new ContentQuery<UserDto>(userDtoPage.getTotalPages(), UserDto.castEntityToDo(userDtoPage.getContent()));

    }


    public UserDto registerUser(UserEntity userEntity) throws Exception {
        if (userRepository.existsUserByEmail(userEntity.getEmail())) {
            throw new Exception("Email is busy");
        }
        userEntity.setPassword(new BCryptPasswordEncoder().encode(userEntity.getPassword()));
        userEntity.setStatus("ACTIVE");
        userEntity.setUserEnum(UserEnum.USER);
        userRepository.save(userEntity);
        return UserDto.castEntityToDo(userEntity);
    }

    public UserDto getUser(Long id) throws Exception {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new Exception(String.format("User which id %d not found", id)));
        return UserDto.castEntityToDo(userEntity);
    }
}

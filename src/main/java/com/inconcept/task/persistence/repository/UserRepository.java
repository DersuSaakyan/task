package com.inconcept.task.persistence.repository;

import com.inconcept.task.persistence.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsUserByEmail(String str);

//    @Query("SELECT new com.inconcept.demo.service.model.UserWrapper(u.firstName,u.lastName,u.email,u.status,u.userEnum)  from UserEntity u")
//    Page<UserWrapper> findAllWithPage(Pageable pageable);

    @Query("SELECT u from UserEntity u")
    Page<UserEntity> findAllWithPage(Pageable pageable);
}

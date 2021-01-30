package com.yorisapp.notaria.model.repository;

import com.yorisapp.notaria.model.entity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Qualifier("UserRepository")
public interface UserRepository extends CrudRepository<User, Long> {
    @Query(value = "select u from User u where u.username=?1 and u.deletedDate is null and u.state <> 'BLOQUEADO'")
    Optional<User> getUserByUsername(String pUserName);
}

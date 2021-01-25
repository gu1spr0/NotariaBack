package com.yorisapp.notaria.model.repository;

import com.yorisapp.notaria.model.entity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("UserRepository")
public interface UserRepository extends CrudRepository<User, Long> {
}

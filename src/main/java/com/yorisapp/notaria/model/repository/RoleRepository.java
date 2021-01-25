package com.yorisapp.notaria.model.repository;

import com.yorisapp.notaria.model.entity.Role;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("RoleRepository")
public interface RoleRepository extends CrudRepository<Role, Long> {
    @Query(value = "select r from Role r where r.state=?1")
    Optional<List<Role>> getRoleByState(String pState);
}

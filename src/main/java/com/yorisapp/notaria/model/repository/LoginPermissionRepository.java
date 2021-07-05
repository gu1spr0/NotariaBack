package com.yorisapp.notaria.model.repository;

import com.yorisapp.notaria.model.entity.LoginPermission;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("LoginPermissionRepository")
public interface LoginPermissionRepository extends CrudRepository<LoginPermission, Long> {
    @Query(value = "select d from LoginPermission d")
    List<LoginPermission> getPermissions();
}

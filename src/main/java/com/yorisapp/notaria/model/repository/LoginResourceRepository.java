package com.yorisapp.notaria.model.repository;

import com.yorisapp.notaria.model.entity.LoginResource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("LoginResourceRepository")
public interface LoginResourceRepository extends CrudRepository<LoginResource, Long> {
    @Query(value = "select d from LoginResource d where d.resourceId in ?1")
    List<LoginResource> getResourcesById(Long[] pResourceIdList);

    @Query(value = "select d from LoginResource d")
    List<LoginResource> getResources();
}

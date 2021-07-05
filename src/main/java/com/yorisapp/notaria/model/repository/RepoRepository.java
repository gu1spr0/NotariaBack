package com.yorisapp.notaria.model.repository;

import com.yorisapp.notaria.model.entity.Repo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("RepoRepository")
public interface RepoRepository extends CrudRepository<Repo, Long> {
    @Query(value = "select r from Repo r where r.state=?1")
    List<Repo> getRepoByState(String pState);
}

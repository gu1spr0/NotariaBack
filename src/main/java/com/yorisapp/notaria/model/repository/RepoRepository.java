package com.yorisapp.notaria.model.repository;

import com.yorisapp.notaria.model.entity.Repo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("RepoRepository")
public interface RepoRepository extends CrudRepository<Repo, Long> {

}

package com.yorisapp.notaria.model.repository;

import com.yorisapp.notaria.model.entity.DocumentClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("DocumentClientRepository")
public interface DocumentClientRepository extends CrudRepository<DocumentClient, Long> {
}

package com.yorisapp.notaria.model.repository;

import com.yorisapp.notaria.model.entity.Client;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("ClientRepository")
public interface ClientRepository extends CrudRepository<Client, Long> {
}

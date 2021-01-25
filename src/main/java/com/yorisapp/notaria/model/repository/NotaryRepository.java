package com.yorisapp.notaria.model.repository;

import com.yorisapp.notaria.model.entity.Notary;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("NotaryRepository")
public interface NotaryRepository extends CrudRepository<Notary, Long> {
}

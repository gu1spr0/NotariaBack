package com.yorisapp.notaria.model.repository;

import com.yorisapp.notaria.model.entity.Document;
import com.yorisapp.notaria.model.entity.DomainValue;
import com.yorisapp.notaria.service.dto.domainValue.DomainValueQueryDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("DocumentRepository")
public interface DocumentRepository extends CrudRepository<Document, Long> {
    /*@Query(value = "select case when (d.correlative is null) then 1 else d.correlative+1 end from Document d")
    long getCorrelative();*/
}

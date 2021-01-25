package com.yorisapp.notaria.model.repository;

import com.yorisapp.notaria.model.entity.Word;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("WordRepository")
public interface WordRepository extends CrudRepository<Word, Long> {
}

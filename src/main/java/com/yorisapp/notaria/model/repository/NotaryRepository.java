package com.yorisapp.notaria.model.repository;

import com.yorisapp.notaria.model.entity.Domain;
import com.yorisapp.notaria.model.entity.Notary;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("NotaryRepository")
public interface NotaryRepository extends CrudRepository<Notary, Long> {
    @Query(value = "select n from Notary n where n.state=?1")
    List<Notary> getNotaryByState(String pState);

    @Query(value = "select n from Notary n where n.id = ?1 and n.state=?2")
    Optional<Notary> getNotaryByIdAndState(long pNotaryId, String pState);

}

package com.yorisapp.notaria.model.repository;

import com.yorisapp.notaria.model.entity.DomainValue;
import com.yorisapp.notaria.model.entity.Role;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("DomainValueRepository")
public interface DomainValueRepository extends CrudRepository<DomainValue, Long> {
    @Query(value = "select d from DomainValue d where d.state=?1")
    Optional<List<DomainValue>> getDomainValueByState(String pState);

    @Query(value = "select d from DomainValue d where d.id=?1 and d.state=?2")
    Optional<DomainValue> getDomainValueByIdAndState(long pDomainValueId, String pState);

    @Query(value = "select d from DomainValue d where d.code=?1 and d.state=?2")
    Optional<DomainValue> getDomainValueByCodeAndState(String pCode, String pState);

    @Query(value = "select d from DomainValue d where d.domain.name=?1 and d.code=?2 and d.state=?3")
    Optional<DomainValue> getDomainValueByDomainAndCodeAndState(String pDomainName, String pCode, String pState);
}

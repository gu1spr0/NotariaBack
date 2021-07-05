package com.yorisapp.notaria.model.repository;

import com.yorisapp.notaria.model.entity.LogConnection;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Qualifier("LogConnectionRepository")
public interface LogConnectionRepository extends CrudRepository<LogConnection, Long> {
    @Query(value = "select d from LogConnection d where d.idUsuario=?1 and d.logoutDate is null")
    Optional<LogConnection> getConnectionLogByUserName(Long pUsernameId);
}

package com.yorisapp.notaria.model.repository;

import com.yorisapp.notaria.model.entity.Role;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("RoleRepository")
public interface RoleRepository extends CrudRepository<Role, Long> {
    @Query(value = "select count(r) from Role r where r.state=?1")
    Long getCountRolesByState(String pState);

    @Query(value = "select r from Role r where r.state = ?1 order by r.role")
    List<Role> getRolesPageableByState(String pState, Pageable pPageable);

    @Query(value = "select r from Role r where r.state = ?1 order by r.role")
    List<Role> getRolesByState(String pState);

    @Query(value = "select r from Role r where r.id = ?1")
    Optional<Role> getRoleById(long pRoleId);

    @Query(value = "select r from Role r where r.role = ?1 and r.deletedDate is null")
    Optional<Role> getRoleByRoleName(String pRoleName);

    @Query(value = "select case when count(r) > 0 then true else false end from Role r where r.role=?1 and r.deletedDate is null")
    Boolean isRoleNameRepeated(String pRoleName);

    @Query(value = "select d from Role d inner join d.userList u where u.id = ?1 order by d.role")
    List<Role> getUserRolesByUsername(long pUserId);

    @Query(value = "select d from Role d where d.id in ?1 order by d.role")
    List<Role> getRolesByIds(Long[] pRoleIds);
}

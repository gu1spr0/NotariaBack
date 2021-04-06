package com.yorisapp.notaria.model.repository;

import com.yorisapp.notaria.model.entity.Resource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("ResourceRepository")
public interface ResourceRepository extends CrudRepository<Resource, Long> {
    @Query(value = "select count(r) from Resource r where r.groupResource.id=?1 AND r.state=?2")
    Long getCountResourcesValuesByIdAndState(Long pResourceGroupId, String pState);

    @Query(value = "select r from Resource r where r.groupResource.id=?1 AND r.state=?2")
    List<Resource> getResourcesPageableByIdAndState(Long pResourceGroupId, String pState, Pageable pPageable);

    @Query(value = "select r from Resource r where r.id = ?1")
    Optional<Resource> getResourceById(long pResourceId);

    @Query(value = "select d from Resource d inner join d.roleList r where r.role in ?1")
    List<Resource> getResourcesByRoles(String[] pRoles);

    @Query(value = "select d from Resource d inner join d.roleList r where r.id = ?1")
    List<Resource> getResourcesByRoleId(long pRoleId);

    @Query(value = "select d from Resource d where d.id in ?1")
    List<Resource> getResourcesByIds(Long[] pIds);
}

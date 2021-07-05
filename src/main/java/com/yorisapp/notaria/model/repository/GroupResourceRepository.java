package com.yorisapp.notaria.model.repository;

import com.yorisapp.notaria.model.entity.GroupResource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("GroupResourceRepository")
public interface GroupResourceRepository extends CrudRepository<GroupResource, Long> {
    @Query(value = "select count(g) from GroupResource g where g.state=?1")
    Long getCountGroupResourceByState(String pState);

    @Query(value = "select g from GroupResource g where g.state=?1")
    List<GroupResource> getGroupResourceByPageableByState(String pState, Pageable pPageable);

    @Query(value = "select g from GroupResource g where g.id = ?1")
    Optional<GroupResource> getGroupResourceById(long pResourcesGroupId);
}

package com.yorisapp.notaria.model.repository;

import com.yorisapp.notaria.model.entity.GroupPermission;
import com.yorisapp.notaria.model.entity.GroupResource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("GroupPermissionRepository")
public interface GroupPermissionRepository extends CrudRepository<GroupPermission, Long> {
}

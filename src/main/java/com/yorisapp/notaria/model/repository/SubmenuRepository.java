package com.yorisapp.notaria.model.repository;

import com.yorisapp.notaria.model.entity.Submenu;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("SubmenuRepository")
public interface SubmenuRepository extends CrudRepository<Submenu, Long> {
}

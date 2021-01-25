package com.yorisapp.notaria.model.repository;

import com.yorisapp.notaria.model.entity.Menu;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("MenuRepository")
public interface MenuRepository extends CrudRepository<Menu, Long> {
}

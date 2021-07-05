package com.yorisapp.notaria.model.repository;

import com.yorisapp.notaria.model.entity.Route;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("RouteRepository")
public interface RouteRepository extends CrudRepository<Route, Long> {
    @Query(value = "select count(r) from Route r")
    Long getCountRoutes();

    @Query(value = "select r from Route r")
    List<Route> getRoutesPageable(Pageable pPageable);

    @Query(value = "select r from Route r where r.id = ?1")
    Optional<Route> getRouteById(long pRouteId);

    @Query(value = "select r from Route r")
    List<Route> getRoutes();
}

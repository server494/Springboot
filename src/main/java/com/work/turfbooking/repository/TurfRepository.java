package com.work.turfbooking.repository;

import com.work.turfbooking.entity.Turf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurfRepository extends JpaRepository<Turf,Integer> {

    List<Turf> findByName(String name);

    @Query("SELECT t FROM Turf t  WHERE t.name=:n")//jpql
    public List<Turf> getTurfsByName(@Param("n")String name);

}

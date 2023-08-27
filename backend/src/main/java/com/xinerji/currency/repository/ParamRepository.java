package com.xinerji.currency.repository;

import com.xinerji.currency.model.entity.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ParamRepository extends JpaRepository<Param, Long> {
    @Query("select p from Param p where p.key = :key")
    Param findByKey(String key);
}

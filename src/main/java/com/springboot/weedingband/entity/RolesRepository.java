package com.springboot.weedingband.entity;

import org.springframework.data.repository.CrudRepository;

public interface RolesRepository extends CrudRepository<Roles, Integer> {
    Roles findByUserId(long id);
}

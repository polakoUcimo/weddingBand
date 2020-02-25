package com.springboot.weedingband.dao;

import org.springframework.data.repository.CrudRepository;

import com.springboot.weedingband.entity.ConfirmationToken;

public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {
    ConfirmationToken findByConfirmationToken(String confirmationToken);
}

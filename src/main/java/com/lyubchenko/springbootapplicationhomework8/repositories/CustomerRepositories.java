package com.lyubchenko.springbootapplicationhomework8.repositories;

import com.lyubchenko.springbootapplicationhomework8.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerRepositories extends JpaRepository<Customer, UUID> {
}

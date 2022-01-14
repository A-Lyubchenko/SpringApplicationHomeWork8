package com.lyubchenko.springbootapplicationhomework8.repositories;

import com.lyubchenko.springbootapplicationhomework8.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepositories extends JpaRepository<Product, UUID> {
}

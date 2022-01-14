package com.lyubchenko.springbootapplicationhomework8.repositories;

import com.lyubchenko.springbootapplicationhomework8.domain.forSecurity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthorityRepositories extends JpaRepository<Authority, UUID> {

}

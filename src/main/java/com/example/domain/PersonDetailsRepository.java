package com.example.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonDetailsRepository extends JpaRepository<PersonDetails, Long> {
}

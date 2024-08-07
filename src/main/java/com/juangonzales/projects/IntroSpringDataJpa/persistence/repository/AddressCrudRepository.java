package com.juangonzales.projects.IntroSpringDataJpa.persistence.repository;

import com.juangonzales.projects.IntroSpringDataJpa.persistence.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressCrudRepository extends JpaRepository<Address, Long> {
}

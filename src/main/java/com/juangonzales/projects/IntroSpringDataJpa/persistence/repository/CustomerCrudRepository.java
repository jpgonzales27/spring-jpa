package com.juangonzales.projects.IntroSpringDataJpa.persistence.repository;

import com.juangonzales.projects.IntroSpringDataJpa.persistence.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CustomerCrudRepository extends CrudRepository<Customer,Long> {
}

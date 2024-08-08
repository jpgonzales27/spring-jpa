package com.juangonzales.projects.IntroSpringDataJpa.persistence.repository;

import com.juangonzales.projects.IntroSpringDataJpa.persistence.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerCrudRepository extends JpaRepository<Customer,Long> {

    Customer searchByUsername(String username);
    Optional<Customer> findByUsername(String username);

    // SELECT c.* FROM customers c WHERE c.name like %?%
    List<Customer> findByNameContaining(String name);

    // SELECT c.* FROM customers c WHERE c.name like ?%
    List<Customer> queryByNameStartsWith(String name);

    // SELECT c.* FROM customers c WHERE c.name like %?
    List<Customer> readByNameIsEndingWith(String name);

    // SELECT c.* FROM customer c where c.name like %?1% and c.id >= ?2 order by id desc
    List<Customer> findByNameContainingAndIdGreaterThanEqualOrderByIdDesc(String name, Long id);

    /**
     * Si no queremos detallar el nombre como el metodo anterior
     * podemos darle un nombre cualquiera, pero debemos declarar el @Query
     * y dentro escribir el JPQL necesario para realizar nuestra consulta
     *
     * JPQL funciona en relacion con nuestros atributos de la clase que declaramos
     */
    @Query("select c from Customer c where c.name like %?1% and c.id >= ?2 order by c.id desc " )
    List<Customer> findAllByNameAndIdGreaterThan(String name, Long id);

    /**
     * Si queremos usar sql nativo debemos declarar dentro de @Query
     * el sql que queremos ejecutar y tambien el parametro nativeQuery a true
     *
     * SQL funciona en relacion a la tabla en la base de datos
     */
    @Query(value = "select c.* from clientes c where c.nombre like %?1% and c.id >= ?2 order by c.username desc ", nativeQuery = true )
    List<Customer> findAllByNameAndIdGreaterThanUsingNativeSQL(String name, Long id);
}

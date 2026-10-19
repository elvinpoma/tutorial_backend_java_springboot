package com.ccsw.tutorial.client.repository;

import com.ccsw.tutorial.client.model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author elportil
 *
 */
@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
    boolean existsByNameIgnoreCase(String name);

    Client getById(Long id);
}

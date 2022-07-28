package org.omn1vor.epicpartnerstesttask.repository;

import org.omn1vor.epicpartnerstesttask.models.Counter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CounterRepository extends CrudRepository<Counter, String> {
    List<Counter> findTop50ByOrderById();
}

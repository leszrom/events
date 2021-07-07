package com.crud.events.repository;

import com.crud.events.domain.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
    @Override
    List<Event> findAll();

    Optional<Event> findById(Long id);
}

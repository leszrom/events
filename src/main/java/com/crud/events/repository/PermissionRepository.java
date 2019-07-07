package com.crud.events.repository;

import com.crud.events.domain.Permission;
import com.crud.events.domain.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PermissionRepository extends CrudRepository<Permission, Long> {
    Optional<Permission> findByRole(Role role);

    List<Permission> findAll();
}

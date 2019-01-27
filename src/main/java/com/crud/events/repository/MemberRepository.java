package com.crud.events.repository;

import com.crud.events.domain.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {
    @Override
    List<Member> findAll();

    Optional<Member> findById(Long id);

    void deleteById(Long id);
}

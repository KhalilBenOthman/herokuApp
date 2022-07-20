package com.TeamSeven.CConge.repositories;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;

import com.TeamSeven.CConge.domain.DmdConge;

public interface DmdCongeRepository extends CrudRepository<DmdConge, Long>{
		DmdConge findBydateDebutC(Date dateDeb);
		DmdConge findByid(Long id);

		Iterable<DmdConge> findAllByusername(String username);
}

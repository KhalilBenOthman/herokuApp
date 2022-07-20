package com.TeamSeven.CConge.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.TeamSeven.CConge.domain.TA_Conges;

@Repository
public interface TA_CongesRepository extends CrudRepository<TA_Conges, Long>{

	TA_Conges findBycode(String code);

	@Override
	Iterable<TA_Conges> findAll();
	
}

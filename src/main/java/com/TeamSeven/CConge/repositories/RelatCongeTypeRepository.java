package com.TeamSeven.CConge.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.TeamSeven.CConge.domain.RelatCongeType;
@Repository
public interface RelatCongeTypeRepository extends CrudRepository<RelatCongeType, Long>{
	
	RelatCongeType findByTACongeCode(String code);

}

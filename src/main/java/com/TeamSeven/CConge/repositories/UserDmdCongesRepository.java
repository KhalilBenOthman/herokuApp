package com.TeamSeven.CConge.repositories;

import org.springframework.data.repository.CrudRepository;

import com.TeamSeven.CConge.domain.UserDmdConges;

public interface UserDmdCongesRepository extends CrudRepository<UserDmdConges, Long>{
	UserDmdConges findByuserName(String username);
}

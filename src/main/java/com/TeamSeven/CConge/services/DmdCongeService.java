package com.TeamSeven.CConge.services;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TeamSeven.CConge.domain.Conges;
import com.TeamSeven.CConge.domain.DmdConge;
import com.TeamSeven.CConge.domain.User;
import com.TeamSeven.CConge.domain.UserDmdConges;
import com.TeamSeven.CConge.exceptions.TA_CongesNotFoundException;
import com.TeamSeven.CConge.repositories.CongesRepository;
import com.TeamSeven.CConge.repositories.DmdCongeRepository;
import com.TeamSeven.CConge.repositories.UserDmdCongesRepository;
import com.TeamSeven.CConge.repositories.UserRepository;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Date;
@Service
public class DmdCongeService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserDmdCongesRepository userDmdCongesRepository;
	@Autowired
	private CongesRepository congesRepository ;
	@Autowired
	private DmdCongeRepository dmdCongesRepository;

	
	
	public DmdConge addDemandeConge(DmdConge dmdConge, String username) {
		try {
			UserDmdConges userDmdConges = userDmdCongesRepository.findByuserName(username);
			Conges conge = congesRepository.findBycode(dmdConge.getCongesCode());
			if (userDmdConges==null || conge == null) {
				throw new TA_CongesNotFoundException("Erreur lors de la creation de la demande .");
			}
			 
			User user = userRepository.findByUsername(username);
			if (user.getSoldeRestant() == 0) {
				throw new TA_CongesNotFoundException("la solde de congé restant est zéro .");
			}
			 
			if (user.getSoldeRestant() < dmdConge.getPeriodeOnJours()) {
				throw new TA_CongesNotFoundException("la solde de congé restant est zéro .");
			}
			
			dmdConge.setUserDmdConges(userDmdConges);
			dmdConge.setUsername(username);
			user.setSoldeRestant(user.getSoldeRestant() - dmdConge.getPeriodeOnJours());
			userRepository.save(user);
			return dmdCongesRepository.save(dmdConge);
		} catch (Exception e) {
			throw new TA_CongesNotFoundException("Erreur lors de la creation .");
		}
		
	}
	
	public Iterable<DmdConge> findAllDmdConge(String username){
		return dmdCongesRepository.findAllByusername(username);
	}
	
	public void deleteCongeByDateDebut(DmdConge dmdConge, String username ) {
		UserDmdConges userDmdConges = userDmdCongesRepository.findByuserName(username);
		List<DmdConge> udmdC = userDmdConges.getDmdConges();
		udmdC.remove(dmdConge);
		userDmdConges.setDmdConges(udmdC);
		userDmdCongesRepository.save(userDmdConges);
		
		dmdCongesRepository.delete(dmdConge);
	}
	
	
	
}

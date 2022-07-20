package com.TeamSeven.CConge.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.TeamSeven.CConge.domain.User;
import com.TeamSeven.CConge.domain.UserDmdConges;
import com.TeamSeven.CConge.exceptions.UsernameExistException;
import com.TeamSeven.CConge.repositories.UserDmdCongesRepository;
import com.TeamSeven.CConge.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserDmdCongesRepository userDmdCongesRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	
	public User saveUser(User newUser) {
		
		try {
			//SetUp BackLog des demandes 
			UserDmdConges userDmdConges = userDmdCongesRepository.findByuserName(newUser.getUsername());
			if(userDmdConges == null) {
				UserDmdConges userDmdConges1 = new UserDmdConges();
				newUser.setUserDmdConges(userDmdConges1);
				userDmdConges1.setUserDmd(newUser);
				userDmdConges1.setUserName(newUser.getUsername());
			}
			
			
			newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));	
			newUser.setConfirmPassword(null);
			return userRepository.save(newUser);
		} catch (Exception e) {
			throw new UsernameExistException("Username "+newUser.getUsername()+" déja exist");
		}
		
	
	}
	public void deleteUser(User user, String username) {
		
		
		if(user.getIsAdmin()==true) {
			userRepository.delete(user);
			return;
		}
		throw new UsernameExistException("Username "+username+" n'est pas autorisé a supprimer des utilisateur");
			
	}
	public void deleteUserByUsername(String userADelete, String username) {
		User user = userRepository.findByUsername(username);
		if(user.getIsAdmin()==true) {
			try {
				User user1 = userRepository.findByUsername(userADelete);
				userRepository.delete(user1);
				return;
			} catch (Exception e) {
				throw new UsernameExistException("Username "+userADelete+" n'existe pas");
			}
		}
		throw new UsernameExistException("Username "+username+" n'est pas autorisé a supprimer des utilisateur");
			
	}
	
	
	
	public Iterable<User> findAllUsers(){
		return userRepository.findAll();
	}
	public User findUserByUserName(String userName) {
		try {
			return userRepository.findByUsername(userName);
		} catch (Exception e) {
			throw new UsernameExistException("Username "+userName+" n'existe pas");
		}

	}
	
	
	
}

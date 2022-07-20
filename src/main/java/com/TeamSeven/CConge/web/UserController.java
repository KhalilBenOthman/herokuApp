package com.TeamSeven.CConge.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TeamSeven.CConge.Validator.UserValidator;
import com.TeamSeven.CConge.domain.User;
import com.TeamSeven.CConge.payload.JWTLoginSuccessResponse;
import com.TeamSeven.CConge.payload.LoginRequest;
import com.TeamSeven.CConge.security.JwtTokenProvider;
import com.TeamSeven.CConge.services.MapValidationErrorService;
import com.TeamSeven.CConge.services.UserService;
import static com.TeamSeven.CConge.security.SecurityConstants.TOKEN_PREFIX;

import java.security.Principal;
@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserValidator userValidator;
	@Autowired
	private JwtTokenProvider tokenProvider;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/Login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){
		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationError(result);
		if (errorMap != null) return errorMap;
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
				);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = TOKEN_PREFIX + tokenProvider.generateToken(authentication);
		
		return ResponseEntity.ok(new JWTLoginSuccessResponse(true, jwt));
		
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result){
		//Validate password
		userValidator.validate(user, result);
		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationError(result);
		if (errorMap != null) return errorMap;
		
		User newUser = userService.saveUser(user);
		return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public Iterable<User> getAllUsers(){
		return userService.findAllUsers();
	}
	@GetMapping("/{username}")
	public ResponseEntity<User> getAllUsers(@PathVariable String username){
		User user = userService.findUserByUserName(username);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@DeleteMapping("/")
	public ResponseEntity<String> deleteUser(@Valid @RequestBody User user, BindingResult result, Principal principal){
		
		userService.deleteUser(user, principal.getName());
		return new ResponseEntity<String>("user has been deleted", HttpStatus.OK);
		
	}
	@DeleteMapping("/{username}")
	public ResponseEntity<String> deleteUser(@PathVariable String username, Principal principal){
		
		userService.deleteUserByUsername(username, principal.getName());
		return new ResponseEntity<String>("user has been deleted", HttpStatus.OK);
		
	}
	
}

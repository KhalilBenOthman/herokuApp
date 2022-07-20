package com.TeamSeven.CConge.web;

import java.security.Principal;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TeamSeven.CConge.domain.DmdConge;
import com.TeamSeven.CConge.domain.TA_Conges;
import com.TeamSeven.CConge.services.DmdCongeService;
import com.TeamSeven.CConge.services.MapValidationErrorService;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/api/Dmd")
@CrossOrigin
public class CongesDemandesController {
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	@Autowired
	private DmdCongeService dmdCongeService;
	
	@PostMapping("")
	public ResponseEntity<?> CreateNewDemandeConge(@Valid @RequestBody DmdConge dmdConge, BindingResult result, Principal principal){	
		
		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationError(result);
		if (errorMap != null) return errorMap;
		
		DmdConge dmdConge1 = dmdCongeService.addDemandeConge(dmdConge, principal.getName());
		return new ResponseEntity<DmdConge>(dmdConge1, HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public Iterable<DmdConge> getAllDmdConges(Principal principal){
		return dmdCongeService.findAllDmdConge(principal.getName());
	}
	
	@DeleteMapping("/")
	public ResponseEntity<?> deleteTAConge(@Valid @RequestBody DmdConge dmdConge, BindingResult result,Principal principal) {
		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationError(result);
		if (errorMap != null) return errorMap;
		dmdCongeService.deleteCongeByDateDebut(dmdConge, principal.getName());
		return new ResponseEntity<String>("La conge a été supprimer.", HttpStatus.OK);
	}
	
	
}

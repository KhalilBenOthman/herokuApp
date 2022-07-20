package com.TeamSeven.CConge.web;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TeamSeven.CConge.domain.Conges;
import com.TeamSeven.CConge.services.CongesService;
import com.TeamSeven.CConge.services.MapValidationErrorService;

@RestController
@RequestMapping("/api/Conge")
@CrossOrigin
public class CongesController {
	@Autowired
	private CongesService congesService;
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	
	@PostMapping("/{RelatCongesType_id}")
	public ResponseEntity<?> addCongesToRelatCongeType (@Valid @RequestBody Conges conge,
														BindingResult result, @PathVariable String RelatCongesType_id,Principal principal)	{															
		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationError(result);
		if (errorMap != null) return errorMap;
		
		Conges conge1 = congesService.addConges(RelatCongesType_id, conge,principal.getName());
		return new ResponseEntity<Conges>(conge1,HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public Iterable<Conges> getAllConges(){
		return congesService.findAllConges();
	}
	
	@GetMapping("/{RelatCongesType_id}")
	public Iterable<Conges> getConges(@PathVariable String RelatCongesType_id){
		return congesService.findRelatCongeTypeById(RelatCongesType_id);
	}
	@GetMapping("/{RelatCongesType_id}/{Conges_id}")
	public ResponseEntity<?> getConge(@PathVariable String RelatCongesType_id, @PathVariable String Conges_id){
			Conges conge = congesService.findByCode(RelatCongesType_id,Conges_id);
			return new ResponseEntity<Conges>(conge,HttpStatus.OK);
		}
	
	@PatchMapping("/{RelatCongesType_id}/{Conges_id}")
	public ResponseEntity<?> updateConge(@Valid @RequestBody Conges conge,BindingResult result,
										@PathVariable String RelatCongesType_id, @PathVariable String Conges_id){
		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationError(result);
		if (errorMap != null) return errorMap;
		
		Conges updatedConge = congesService.updateByCode(conge, RelatCongesType_id, Conges_id);
		return new ResponseEntity<Conges>(updatedConge,HttpStatus.OK);
		
	}
	
	
	@DeleteMapping("/{RelatCongesType_id}/{Conges_id}")
	public ResponseEntity<?> deleteConge(@PathVariable String RelatCongesType_id, @PathVariable String Conges_id){
		congesService.deleteCongeByCode(RelatCongesType_id, Conges_id);
		return new ResponseEntity<String>("Congé avec le code "+Conges_id+" a été supprimer",HttpStatus.OK);

	}
	
}

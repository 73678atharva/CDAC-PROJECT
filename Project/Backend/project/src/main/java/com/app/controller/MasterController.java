package com.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Master;
import com.app.repositry.MasterRepository;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/")
public class MasterController {

	@Autowired
	MasterRepository masterRepository;
	
	@PostMapping("/master")
	public String createNewEntry(@RequestBody Master master) {
		masterRepository.save(master);
		return "Entry Created successfully";
	}
	
	@GetMapping("/master")
	public ResponseEntity<List<Master>> getAllEntries(){
		List<Master> masterlist = new ArrayList<>();
		masterRepository.findAll().forEach(masterlist::add);
		return new ResponseEntity<List<Master>>(masterlist, HttpStatusCode.valueOf(200));
	}
	
	@GetMapping("master/{id}")
	public ResponseEntity<Master> getEntryById(@PathVariable  int id) {
		Optional<Master> mast = masterRepository.findById(id);
		if(mast.isPresent()) {
			return new ResponseEntity<Master>(mast.get(),HttpStatus.FOUND);
		}
		else {
			return new ResponseEntity<Master>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("master/{id}")
	public String updateEntry(@PathVariable int id, @RequestBody Master master) {
		
		Optional<Master> mast = masterRepository.findById(id);
		if(mast.isPresent()) {
			Master existmaster = mast.get();
			existmaster.setName(master.getName());
			existmaster.setRole(master.getRole());
			masterRepository.save(existmaster);
			return "Details of id: "+ id + " Updated";
		}
		return "Details of id: "+ id + " not found";
	}
	
  @DeleteMapping("/master/{id}")
  public String deleteById(@PathVariable int id) {
	  masterRepository.deleteById(id);
	  return "Entry deleted Successfully";
  }
  
  @DeleteMapping("/master")
  public String deleteByAll(@PathVariable int id) {
	  masterRepository.deleteAll();
	  return "All Entries deleted Successfully";
  }
}

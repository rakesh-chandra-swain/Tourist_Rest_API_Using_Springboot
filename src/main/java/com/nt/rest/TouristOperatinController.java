package com.nt.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.entity.Tourist;
import com.nt.service.ITouristMgmtservice;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/tourist-API")
@Tag(name="Tourist INFO",description = "perform CRUD operation on Tourist")
public class TouristOperatinController {
	
	@Autowired
	private ITouristMgmtservice service;
	//end Point devlopment
	@PostMapping("/register")
	@Operation(summary ="save the product",description = "perform save Object Opration")
	public ResponseEntity<String> enrollTourist(@RequestBody Tourist tourist){
		try {
			//use service
			String resultMsg=service.registerTourist(tourist);
			return new ResponseEntity<String>(resultMsg,HttpStatus.CREATED);
		}
		catch(Exception e) {
			return new ResponseEntity<String>("problem is Tourist Enrollment",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/all")
	public ResponseEntity<?> showAllTourist(){
		try {
			//use service
			List<Tourist> list=service.showAlltraveller();
			return new ResponseEntity<List<Tourist>>(list,HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
@GetMapping("/find/{id}")
	public ResponseEntity<?> showTouristById(@PathVariable int id){
		try {
			//use service
			Tourist tourist=service.fetchTouristById(id);
			return new ResponseEntity<Tourist>(tourist,HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
@PutMapping("/update")
public ResponseEntity<?> modifyTourist(@RequestBody Tourist tourist){
	try {
		//use service
				String msg=service.updateTourist(tourist);
				return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	catch(Exception e) {
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

@PatchMapping("/update/{id}/{addrs}")
public ResponseEntity<?> modifyTouristAddress(@PathVariable int id,@PathVariable String addrs){
	try {
		//use service
				String msg=service.updateTouristAddress(id, addrs);
				return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	catch(Exception e) {
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
}

package com.sportyshoes.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sportyshoes.service.SportyShoesService;

@RestController
public class AdministratorController {
	@Autowired
	SportyShoesService sportyShoesService;

	@RequestMapping(value = "/adminLoginValidation", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> adminValidation(@RequestParam("adminUserId") String adminUserId,
			@RequestParam("password") String password) {
		JSONObject adminValidation = sportyShoesService.adminValidation(adminUserId, password);
		return new ResponseEntity<Object>(adminValidation.toString(), HttpStatus.OK);
	}
	@RequestMapping(value = "/changeAdminPassword", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Object> changeAdminPassword( @RequestParam("adminId") int adminId,@RequestParam("oldPassword") String oldPassword,
			@RequestParam("newPassword") String newPassword) {
		String passwordChangeStatus = sportyShoesService.changeAdminPassword(adminId,oldPassword, newPassword);
		return new ResponseEntity<Object>(passwordChangeStatus, HttpStatus.OK);
	}
}

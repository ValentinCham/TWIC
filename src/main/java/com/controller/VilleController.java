package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.blo.VilleBLO;

@RestController
//@RequestMapping("/path")
class VilleController {

	//@Autowired
	//VilleBLO villeService;

	// Methode GET
	@RequestMapping(value = "/ville", method = RequestMethod.GET)
	@ResponseBody
	public String appelGet() {
		System.out.println("Appel GET");
		
		//TODO
		
		return "JSON";
	}
}

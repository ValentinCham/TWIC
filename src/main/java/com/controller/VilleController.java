package com.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.blo.VilleBLO;
import com.config.JDBCConfiguration;
import com.dao.VilleDAO;
import com.dto.Ville;

@RestController
class VilleController {

	VilleDAO villeService;

	// Methode GET
	@RequestMapping(value = "/ville", method = RequestMethod.GET)
	@ResponseBody
	public List<Ville> appelGet(@RequestParam(required = false, value = "codePostal") String codePostal) {
		System.out.println("Appel GET");
		JDBCConfiguration jdbc = JDBCConfiguration.getInstance();
		villeService = jdbc.getVilleDao();
		List<Ville> listeVille = new ArrayList<Ville>();
		if(codePostal!= null)
			listeVille= villeService.afficherVilles(codePostal);
		else 
			listeVille = villeService.afficherVilles();
		
		return listeVille;
	}

	// Methode PUT (insert)
	@RequestMapping(value = "/villePut", method = RequestMethod.PUT)
	@ResponseBody
	public String appelPUT(@RequestParam( value = "codeInsee") String codeInsee,
			@RequestParam( value = "nomCommune") String nomCommune,
			@RequestParam( value = "codePostal") String codePostal,
			@RequestParam( value = "libelle") String libelle,
			@RequestParam(value = "ligne5") String ligne5,
			@RequestParam( value = "latitude") String latitude,
			@RequestParam( value = "longitude") String longitude) {
		System.out.println("Appel Put");
		JDBCConfiguration jdbc = JDBCConfiguration.getInstance();
		villeService = jdbc.getVilleDao();
		String resp =villeService.insertVille(codeInsee, nomCommune, codePostal, libelle, ligne5, latitude, longitude);
		return resp;

	}

	// Methode POST (update)
	@RequestMapping(value = "/villePost", method = RequestMethod.POST)
	@ResponseBody
	public String appelPOST(@RequestParam( value = "codeInsee") String codeInsee,
			@RequestParam( value = "nomCommune") String nomCommune,
			@RequestParam( value = "codePostal") String codePostal,
			@RequestParam( value = "libelle") String libelle,
			@RequestParam( value = "ligne5") String ligne5,
			@RequestParam( value = "latitude") String latitude,
			@RequestParam( value = "longitude") String longitude) {
		System.out.println("Appel Post");
		JDBCConfiguration jdbc = JDBCConfiguration.getInstance();
		villeService = jdbc.getVilleDao();
		String resp =villeService.updateVille(codeInsee, nomCommune, codePostal, libelle, ligne5, latitude, longitude);
		return resp;

		
	}

	// Methode DELETE
	@RequestMapping(value = "/villeDelete", method = RequestMethod.DELETE)
	@ResponseBody
	public String appelDELETE(@RequestParam(value = "codeInsee") String codeInsee){
		System.out.println("Appel Delete");
		
		JDBCConfiguration jdbc = JDBCConfiguration.getInstance();
		villeService = jdbc.getVilleDao();
		String resp =villeService.deleteVille(codeInsee);
		return resp;
	}

}

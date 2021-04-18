package com.dao;

import java.util.List;

import com.dto.Ville;

public interface VilleDAO {

	public List<Ville> afficherVilles();
	
	public List<Ville> afficherVilles(String code);
	
	public String insertVille(String codeInsee, String nomCommune, String codePostal, String libelle, String ligne, String latitude, String longitude);
	
	public String deleteVille(String codeInsee);
	
	public String updateVille(String codeInsee, String nomCommune, String codePostal, String libelle, String ligne, String latitude, String longitude);
	
}

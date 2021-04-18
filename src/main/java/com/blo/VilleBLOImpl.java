package com.blo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.config.JDBCConfiguration;
import com.dao.VilleDAO;
import com.dto.Ville;

@Service
public class VilleBLOImpl {
	

	private VilleDAO villeDAO;
	
	//Marche jamais donc je passe directement par la DAO
	public List<Ville> getInfoVille(String code) {
		JDBCConfiguration jdbc = JDBCConfiguration.getInstance();
		villeDAO = jdbc.getVilleDao();
		List<Ville> ville = new ArrayList<Ville>();
		if (code != null) {
			ville = villeDAO.afficherVilles(code);
		} else {
			ville = villeDAO.afficherVilles();
		}
		return ville;
	}




}

package com.blo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.VilleDAO;
import com.dto.Ville;

@Service
public class VilleBLOImpl implements VilleBLO {

	@Autowired
	private VilleDAO villeDAO;

	@Override
	public ArrayList<Ville> getInfoVille() throws VilleException {
		ArrayList<Ville> listVille;

		listVille = villeDAO.findAllTrains();
		System.out.println("Nombre de ville récupérée(s) : " + listVille.size());

		return listVille;
	}

}

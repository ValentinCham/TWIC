package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.config.JDBCConfiguration;
import com.dto.Ville;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class VilleDAOImpl implements VilleDAO {

	private JDBCConfiguration jdbcConfiguration;

	public VilleDAOImpl(JDBCConfiguration jdbcConfiguration) {
		this.jdbcConfiguration = jdbcConfiguration;
	}

	@Override
	public List<Ville> afficherVilles() {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		List<Ville> listeVilles = new ArrayList<Ville>();
		try {
			connexion = jdbcConfiguration.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT * FROM ville_france;");

			while (resultat.next()) {
				Ville ville = new Ville(resultat.getString("code_commune_insee"), resultat.getString("Nom_commune"),
						resultat.getString("code_postal"), resultat.getString("libelle_acheminement"),
						resultat.getString("ligne_5"), resultat.getString("longitude"), resultat.getString("latitude"));

				listeVilles.add(ville);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // Multiple streams were opened. Only the last is closed.
			finally {
				try {
					if (resultat != null)
						resultat.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		}
		return listeVilles;
	}

	public List<Ville> afficherVilles(String codePostal) {

		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultat = null;
		List<Ville> listeVilles = new ArrayList<>();

		try {
			connexion = jdbcConfiguration.getConnection();
			statement = connexion.prepareStatement("SELECT * FROM ville_france WHERE Code_postal = ?;");
			statement.setString(1, codePostal);
			resultat = statement.executeQuery();

			while (resultat.next()) {
				Ville ville = new Ville(resultat.getString("code_commune_insee"), resultat.getString("Nom_commune"),
						resultat.getString("code_postal"), resultat.getString("libelle_acheminement"),
						resultat.getString("ligne_5"), resultat.getString("longitude"), resultat.getString("latitude"));

				listeVilles.add(ville);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			finally {
				try {
					if (resultat != null)
						resultat.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		}

		return listeVilles;

	}

	@Override
	public String insertVille(String codeInsee, String nomCommune, String codePostal, String libelle, String ligne,
			String latitude, String longitude) {
		Connection connexion = null;
		PreparedStatement statement = null;
		try {
			connexion = jdbcConfiguration.getConnection();
			statement = connexion.prepareStatement("INSERT INTO ville_france(Code_commune_INSEE, Nom_commune,"
					+ " Code_postal, Libelle_acheminement, Ligne_5, Latitude, Longitude) " + "VALUES (?,?,?,?,?,?,?);");
			statement.setString(1, codeInsee);
			statement.setString(2, nomCommune);
			statement.setString(3, codePostal);
			statement.setString(4, libelle);
			statement.setString(5, ligne);
			statement.setString(6, latitude);
			statement.setString(7, longitude);

			return "La ville : " + nomCommune + " a été ajoutée !";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "echec";
		} finally {

			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // Multiple streams were opened. Only the last is closed.
		}
	}

	@Override
	public String deleteVille(String codeInsee) {
		Connection connexion = null;
		PreparedStatement statement = null;
		try {
			connexion = jdbcConfiguration.getConnection();
			statement = connexion.prepareStatement("DELETE FROM ville_france WHERE Code_commune_INSEE =?;");

			statement.setString(1, codeInsee);
			return "La ville " + codeInsee + " a été supprimé !";

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "echec";

		} finally {

			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // Multiple streams were opened. Only the last is closed.
		}
	}

	@Override
	public String updateVille(String codeInsee, String nomCommune, String codePostal, String libelle, String ligne,
			String latitude, String longitude) {
		Connection connexion = null;
		PreparedStatement statement = null;
		try {
			connexion = jdbcConfiguration.getConnection();
			statement = connexion.prepareStatement("UPDATE ville_france SET Nom_commune=?,Code_postal=?,"
					+ "Libelle_acheminement=?,Ligne_5=?," + "Latitude=?,Longitude=? WHERE Code_commune_INSEE=?;");
			statement.setString(1, nomCommune);
			statement.setString(2, codePostal);
			statement.setString(3, libelle);
			statement.setString(4, ligne);
			statement.setString(5, latitude);
			statement.setString(6, longitude);
			statement.setString(7, codeInsee);
			return "La ville " + nomCommune + " a été modifié !";

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "echec";

		} finally {

			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // Multiple streams were opened. Only the last is closed.
		}

	}

}

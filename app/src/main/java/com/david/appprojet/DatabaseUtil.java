/* DatabaseUtil.java
 * Cette classe permet la connection à la base de données et à envoyer des
 * requêtes
 * Auteur: David Osorio Laverde
 * Date: 25 mars 2023*/
package com.david.appprojet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseUtil {
    private static final String DB_HOST = "equipe500.tch099.ovh";
    private static final String DB_NAME = "equipe500";
    private static final String DB_USER = "equipe500";
    private static final String DB_PASSWORD = "+Sdum3RzzBJGQYvo";

    //Méthode pour se connecter à la base de données
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://" + DB_HOST + "/" + DB_NAME;
            return DriverManager.getConnection(url, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new SQLException("Database connection failed.");
        }
    }

    //Methode pour ajouter une information a la base de donnees
    public static void ajoutUsager(String prenom, String nom, String telephone, String courriel, String typeCompte, String motDePasse) {
        try {
            Connection connection = getConnection();
            String query = "INSERT INTO eq2utilisateur (adresse_courriel, mot_de_passe, nom,prenom, telephone, type_compte) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, courriel);
            statement.setString(2, motDePasse);
            statement.setString(3, nom);
            statement.setString(4, prenom);
            statement.setString(5, telephone);
            statement.setString(6, typeCompte);

            statement.executeUpdate();

            // Fermez la connexion et le statement après utilisation
            statement.close();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

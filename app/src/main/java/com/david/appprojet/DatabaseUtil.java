/* DatabaseUtil.java
 * Cette classe permet la connection à la base de données et à envoyer des
 * requêtes
 * Auteur: David Osorio Laverde
 * Date: 25 mars 2023*/
package com.david.appprojet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseUtil {
    //Attributs de la base de données
    private static Connection conn;

    private static String username = "equipe500";
    private static String password = "+Sdum3RzzBJGQYvo";
    private static String host = "localhost";
    private static int port = 3306;
    private static String database = "equipe500";

    //Constructeur de la classe - elle ne doit pas être instanciée
    private DatabaseUtil() {
    }

    public static ResultSet executeQuery(String sql, Object... params){
        //Le resultSet permettra de stocker les résultats de la requête
        ResultSet result = null;
        try {
            //On fait la connection à la base de données
            //Si elle existe pas ou si elle est fermée on la recrée
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
            }

            //On prepare la requete
            PreparedStatement stmt = conn.prepareStatement(sql);

            //On ajoute les paramètres à la requête
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            //On execute la requête
            result = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //On retourne le résultat de la requête
        return result;
    }
}

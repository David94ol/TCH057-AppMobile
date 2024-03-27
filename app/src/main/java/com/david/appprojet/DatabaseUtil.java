/* DatabaseUtil.java
 * Cette classe permet la connection à la base de données et à envoyer des
 * requêtes
 * Auteur: David Osorio Laverde
 * Date: 25 mars 2023*/
package com.david.appprojet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class DatabaseUtil {

    private static final OkHttpClient client = new OkHttpClient();

    //Méthode pour se connecter à la base de données
    public void getConnection() throws Exception{

        Request request = new Request.Builder()
                .url("https://equipe500.tch099.ovh/projet2/api/getutilisateur")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);

                    System.out.println(responseBody.string());
                }
            }
        });

    }
}





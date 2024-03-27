/* DatabaseUtil.java
 * Cette classe permet la connection à la base de données et à envoyer des
 * requêtes
 * Auteur: David Osorio Laverde
 * Date: 25 mars 2023*/
package com.david.appprojet;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.*;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DatabaseUtil {

    private static final String URL = "https://pma.tch099.ovh/index.php";
    private static final String BD = "equipe500";
    private OkHttpClient client;
    private ExecutorService executorService;

    public DatabaseUtil() {
        client = new OkHttpClient();
        executorService = Executors.newSingleThreadExecutor();
    }

    public Future<Boolean> publierInformation(String jsonData, String table) {
        return executorService.submit(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                try {
                    // Construction de l'URL avec la base de données et la table spécifiées
                    HttpUrl url = HttpUrl.parse(URL)
                            .newBuilder()
                            .addQueryParameter("route", "/sql")
                            .addQueryParameter("pos", "0")
                            .addQueryParameter("db", BD)
                            .addQueryParameter("table", table)
                            .build();

                    // Création de la requête POST avec les données JSON
                    RequestBody body = RequestBody.create(jsonData, MediaType.parse("application/json"));
                    Request request = new Request.Builder()
                            .url(url)
                            .post(body)
                            .build();

                    // Envoi de la requête et gestion de la réponse
                    Response response = client.newCall(request).execute();
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    } else {
                        return true;
                    }
                } catch (Exception e) {
                    Log.e("DatabaseUtil", "Error publishing information", e);
                    return false;
                }
            }
        });
    }
}

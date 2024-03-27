/* DatabaseUtil.java
 * Cette classe permet la connection à la base de données et à envoyer des
 * requêtes
 * Auteur: David Osorio Laverde
 * Date: 25 mars 2023*/
package com.david.appprojet;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
public class DatabaseUtil {
    private OkHttpClient client = new OkHttpClient();
    private String url = "https://pma.tch099.ovh/index.php";

    public void connecterBD(final ApiCallback callback) {
        Request requete = new Request.Builder()
                .url(url)
                .build();
        client.newCall(requete).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onError(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body().string());
                } else {
                    callback.onError("Erreur : " + response.code());
                }
            }
        });
    }
}



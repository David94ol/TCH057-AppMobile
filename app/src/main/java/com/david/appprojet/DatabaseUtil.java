/* DatabaseUtil.java
 * Cette classe permet la connection à la base de données et à envoyer des
 * requêtes
 * Auteur: David Osorio Laverde et Zakaria Ben
 * Date: 25 mars 2023*/
package com.david.appprojet;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
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

    public void affichageInfosProprietes(String id, Callback callback) throws Exception{

        Request request = new Request.Builder()
                .url("/projet2/api/getpropriete/"+id)
                .build();

        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);

                    // Convertir la réponse en chaîne de caractères
                    String reponseString = responseBody.string();

                    // Créer un objet JSONObject à partir de la chaîne de caractères
                    JSONObject reponseJson = new JSONObject(reponseString);

                    // Appeler la méthode de rappel avec l'objet JSONObject
                    callback.onResponse(call, response);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });



    }

    //Methode pour ajouter un utilisateur dans la base de donnees
    public Boolean ajoutUtilisateur(String email, String password, String nom, String prenom, String telephone, String type) throws Exception{

        //La reponse permettra de confirmer si l'utilisateur a ete ajoute
        final Boolean[] reponse = {false};
            // Créez un objet JSON avec les informations de l'utilisateur
            JSONObject nouvelUtilisateur = new JSONObject();
            nouvelUtilisateur.put("adresse_courriel", email);
            nouvelUtilisateur.put("mot_de_passe", password);
            nouvelUtilisateur.put("nom", nom);
            nouvelUtilisateur.put("prenom", prenom);
            nouvelUtilisateur.put("telephone", telephone);
            nouvelUtilisateur.put("type_compte", type);

            // Créez une requête POST avec l'objet JSON en tant que corps de la requête
            RequestBody body = RequestBody.create(nouvelUtilisateur.toString(), MediaType.parse("application/json; charset=utf-8"));
            Request request = new Request.Builder()
                    .url("https://equipe500.tch099.ovh/projet2/api/ajouterUtilisateur")
                    .post(body)
                    .build();

            //Envoi de la requete
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

                    }
                }
            });
            //Si l'utilisateur a ete ajoute, la reponse sera true
            reponse[0] = true;
            return reponse[0];
        }
}





/* *
 * PagePrincipaleVisualisation.java
 * Cette classe permet de visualiser la page principale de l'application.
 * Elle permet de visualiser les différents locations disponibles.
 * Auteur: David Osorio Laverde
 * Date: 15 mars 2023
* */

package com.david.appprojet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;

public class PageAfficheAppartment extends AppCompatActivity{

    //Composants de la vue
    Button annuler, contacter;
    TextView prix, adresse, arrondisement, chambres, superficie, animaux, fumeur, stationnement, description;
    String courrielProprietaire;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pageafficheappartement);

        //Initialisation des composants de la vue
        annuler = findViewById(R.id.boutonAnnuler);
        contacter = findViewById(R.id.buttonContacter);
        prix = findViewById(R.id.prix);
        adresse = findViewById(R.id.adresse);
        arrondisement = findViewById(R.id.arrondisement);
        chambres = findViewById(R.id.nombreChambres);
        superficie = findViewById(R.id.superficie);
        animaux = findViewById(R.id.animaux);
        fumeur = findViewById(R.id.fumeur);
        stationnement = findViewById(R.id.stationnement);
        description = findViewById(R.id.description);

        //Récupération des données de la base de donnees
        affichageLocations();

        //Poour retourner a la page de visualisation
        annuler.setOnClickListener(v -> {
            //TODO: A CHANGER
            Intent intent = new Intent(PageAfficheAppartment.this, PageLogIn.class);
            startActivity(intent);
            finish();
        });

        //Pour le bouton contacter
        contacter.setOnClickListener(v -> {
            try {
                composeEmail(new String[]{"david@hotmail.com"}, "Un locataire est intéressé par votre propriété - LocAppart");
            } catch (android.content.ActivityNotFoundException e) {
                Toast.makeText(this, "Aucune application de courriel n'est installée sur votre appareil", Toast.LENGTH_SHORT).show();
            }
        });
        }
    public void composeEmail(String[] addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void affichageLocations(){
        try {
            DatabaseUtil db = new DatabaseUtil();
            try {
                db.affichageInfosProprietes("1", new okhttp3.Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }
                    @Override
                    public void onResponse(Call call, okhttp3.Response response) throws IOException {
                        try (okhttp3.ResponseBody responseBody = response.body()) {
                            if (!response.isSuccessful())
                                throw new IOException("Unexpected code " + response);

                            JSONObject jsonObject = new JSONObject(responseBody.string());
                            runOnUiThread(() -> {
                                try {
                                    prix.setText(jsonObject.getString("prix"));
                                    adresse.setText(jsonObject.getString("adresse"));
                                    arrondisement.setText(jsonObject.getString("arrondissement"));
                                    chambres.setText(jsonObject.getString("chambres"));
                                    superficie.setText(jsonObject.getString("superficie"));
                                    animaux.setText(jsonObject.getString("animaux"));
                                    fumeur.setText(jsonObject.getString("fumeur"));
                                    stationnement.setText(jsonObject.getString("stationnement"));
                                    description.setText(jsonObject.getString("description"));
                                    courrielProprietaire = jsonObject.getString("courriel");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }


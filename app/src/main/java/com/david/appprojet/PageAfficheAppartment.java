/* *
 * PagePrincipaleVisualisation.java
 * Cette classe permet de visualiser la page principale de l'application.
 * Elle permet de visualiser les différents locations disponibles.
 * Auteur: David Osorio Laverde
 * Date: 15 mars 2023
* */

package com.david.appprojet;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

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

        //On doit aller chercher dans la base de données les informations de l'appartement
        DatabaseUtil httpclient = new DatabaseUtil();


        annuler.setOnClickListener(v -> {
            //TODO: A FAIRE
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
    }


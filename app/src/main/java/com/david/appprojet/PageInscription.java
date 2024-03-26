/* *
 * PageInscription.java
 * Cette classe permet de faire un nouveau compte pour l'utilisateur en tant que propriétaire ou locataire.
 * Auteur: David Osorio Laverde
 * Date: 15 mars 2023
 * */
package com.david.appprojet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PageInscription extends AppCompatActivity{

    //Attributs de la page d'inscription
    private TextView prenomUsager;
    private TextView nomUsager;
    private TextView telephoneUsager;
    private TextView courrielUsager;
    private TextView motPasse;
    private TextView confirmationMotPasse;
    private Spinner typeCompte;
    private Button soumettreInscription;
    private Button annulerInscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pageinscription);

        //On cherche les composants de la vue de PageInscription
        prenomUsager = findViewById(R.id.prenomFormulaire);
        nomUsager = findViewById(R.id.nomFormulaire);
        telephoneUsager = findViewById(R.id.telephoneFormulaire);
        courrielUsager = findViewById(R.id.emailFormulaire);
        typeCompte = (Spinner) findViewById(R.id.selectionFormulaire);
        soumettreInscription = findViewById(R.id.soumettreFormulaire);
        annulerInscription = findViewById(R.id.annulerInscription);
        motPasse = findViewById(R.id.motDePasse);
        confirmationMotPasse = findViewById(R.id.motDePasseConfirm);

        //Pour mettre les options du spinner
        String[] typeDeCompte = {"Veillez choisir le type de compte","Propriétaire", "Locataire"};
        //L'adapteur permet de mettre les options dans le spinner
        ArrayAdapter adapteur = new ArrayAdapter(this, android.R.layout.simple_spinner_item, typeDeCompte);
        //Specification du layout a utiliser pour afficher ce qu'il y a dans l'adapteur
        adapteur.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //On met l'adapteur dans le spinner
        typeCompte.setAdapter(adapteur);

        //Si on click sur prenom ou nom, on efface le texte
        prenomUsager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prenomUsager.setText("");
                nomUsager.setText("");
            }
        });
        nomUsager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nomUsager.setText("");
            }
        });

        //Si on veut annuler l'inscription, on retourne a l'activité PageLogIn
        annulerInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PageInscription.this, PageLogIn.class);
                startActivity(intent);
                finish();
            }
        });

        //Si on veut soumettre l'inscription
        soumettreInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //On recupere les valeurs des champs
                String prenom = prenomUsager.getText().toString();
                String nom = nomUsager.getText().toString();
                String telephone = telephoneUsager.getText().toString();
                String courriel = courrielUsager.getText().toString();
                String type = typeCompte.getSelectedItem().toString();
                String motDePasse = motPasse.getText().toString();
                String confirmationMotDePasse = confirmationMotPasse.getText().toString();


                //On verifie que les champs ne sont pas vides
                if(prenom.isEmpty() || nom.isEmpty() || telephone.isEmpty() || courriel.isEmpty() || type.equals("Veillez choisir le type de compte") || motDePasse.isEmpty() || confirmationMotDePasse.isEmpty()){
                    //Si un champ est vide, on affiche un message d'erreur
                    Toast.makeText(getApplicationContext(), "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                }
                //Si tout est bien rempli, on fai une requete a la base de données
                else{
                    //Confirmation du mot de passe
                    if(!motDePasse.equals(confirmationMotDePasse)){
                        //Si les mots de passe ne correspondent pas, on affiche un message d'erreur
                        Toast.makeText(getApplicationContext(), "Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        //On envoie la requete pour ajouter un utilisateur
                        DatabaseUtil.executeQuery("INSERT INTO eq2utilisateur (adresse_courriel, mot_de_passe, nom, prenom, telephone, type_compte) " +
                                        "VALUES (?, ?, ?, ?, ?, ?)", courriel, motDePasse, nom, prenom, telephone, type);
                        //On affiche un message de succès
                        Toast.makeText(getApplicationContext(), "Inscription réussie", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

}
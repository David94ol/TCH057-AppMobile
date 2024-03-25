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

public class PageInscription extends AppCompatActivity{

    //Attributs de la page d'inscription
    TextView prenomUsager;
    TextView nomUsager;
    TextView telephoneUsager;
    TextView courrielUsager;
    Spinner typeCompte;
    Button soumettreInscription;
    Button annulerInscription;

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

        //Pour mettre les options du spinner
        String[] typeDeCompte = {"Veillez choisir le type de compte","Propriétaire", "Locataire"};
        //L'adapteur permet de mettre les options dans le spinner
        ArrayAdapter adapteur = new ArrayAdapter(this, android.R.layout.simple_spinner_item, typeDeCompte);
        //Specification du layout a utiliser pour afficher ce qu'il y a dans l'adapteur
        adapteur.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //On met l'adapteur dans le spinner
        typeCompte.setAdapter(adapteur);

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

                //On verifie que les champs ne sont pas vides
                if(prenom.isEmpty() || nom.isEmpty() || telephone.isEmpty() || courriel.isEmpty() || type.equals("Veillez choisir le type de compte")){
                    //Si un champ est vide, on affiche un message d'erreur
                    Toast.makeText(getApplicationContext(), "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                }
                //Si tout est bien rempli, on fai une requete a la base de données
                else{
                    //On crée un objet JSON pour envoyer les données

                }
            }
        });

    }

}
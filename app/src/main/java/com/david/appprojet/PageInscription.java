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

    }

}
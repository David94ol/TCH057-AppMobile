/* *
 * PagePrincipaleVisualisation.java
 * Cette classe permet de visualiser la page principale de l'application.
 * Elle permet de visualiser les différents locations disponibles.
 * Auteur: David Osorio Laverde
 * Date: 15 mars 2023
* */

package com.david.appprojet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PageAfficheAppartment extends AppCompatActivity{

    // Attributs et importantion de vues de la page principale visualisation
    ImageButton btnFiltre; //Bouton pour filtrer la recherche
    ImageButton btnInscription; //Bouton pour s'inscrire
    TextView txtResultat; //Texte pour afficher le nombre d'appartements trouvés dans la base de données

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pageafficheappartement);

    }

}

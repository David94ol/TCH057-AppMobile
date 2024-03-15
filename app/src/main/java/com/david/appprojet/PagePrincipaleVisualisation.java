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
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PagePrincipaleVisualisation extends AppCompatActivity implements View.OnClickListener{

    // Attributs et importantion de vues de la page principale visualisation
    ImageButton btnFiltre; //Bouton pour filtrer la recherche
    ImageButton btnInscription; //Bouton pour s'inscrire
    TextView txtResultat; //Texte pour afficher le nombre d'appartements trouvés dans la base de données

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pageprincipalevisualisation);

        //On cherche les composants de la vue de PagePrincipaleVisualisation
        btnFiltre = findViewById(R.id.buttonFiltre);
        btnInscription = findViewById(R.id.inscriptionButton);
        txtResultat = findViewById(R.id.texteResultat);

        //Lorsqu'on click pour l'inscription
        btnInscription.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.inscriptionButton) {
            //On doit envoyer les informations de l'utilisateur à InscriptionActivity
            Intent inscriptionActivityIntent = new Intent(this, PageInscription.class);
            startActivity(inscriptionActivityIntent);
        }
    }
}

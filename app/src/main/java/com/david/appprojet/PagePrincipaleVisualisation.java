/* *
 * PagePrincipaleVisualisation.java
 * Cette classe permet de visualiser la page principale de l'application.
 * Elle permet de visualiser les différents locations disponibles.
 * Auteur: David Osorio Laverde
 * Date: 15 mars 2023
* */

package com.david.appprojet;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PagePrincipaleVisualisation extends AppCompatActivity {

    // Attributs et importantion de vues de la page principale visualisation
    ImageButton btnFiltre = findViewById(R.id.buttonFiltre); //Bouton pour filtrer la recherche
    TextView txtResultat = findViewById(R.id.texteResultat); //Texte pour afficher le nombre d'appartements trouvés dans la base de données

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.pageprincipalevisualisation);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
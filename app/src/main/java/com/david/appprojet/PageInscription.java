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

import com.david.appprojet.DatabaseUtil; // Import de la classe DatabaseUtil

import java.util.concurrent.ExecutionException;

public class PageInscription extends AppCompatActivity {

    // Attributs de la page d'inscription
    private TextView prenomUsager;
    private TextView nomUsager;
    private TextView telephoneUsager;
    private TextView courrielUsager;
    private TextView motPasse;
    private TextView confirmationMotPasse;
    private Spinner typeCompte;
    private Button soumettreInscription;
    private Button annulerInscription;
    private Boolean reponseRequete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pageinscription);

        // Initialisation des composants de la vue de PageInscription
        prenomUsager = findViewById(R.id.nomFormulaire);
        nomUsager = findViewById(R.id.prenomFormulaire);
        telephoneUsager = findViewById(R.id.telephoneFormulaire);
        courrielUsager = findViewById(R.id.emailFormulaire);
        typeCompte = findViewById(R.id.selectionFormulaire);
        soumettreInscription = findViewById(R.id.soumettreFormulaire);
        annulerInscription = findViewById(R.id.annulerInscription);
        motPasse = findViewById(R.id.motDePasse);
        confirmationMotPasse = findViewById(R.id.motDePasseConfirm);

        // Pour mettre les options du spinner
        String[] typeDeCompte = {"Veuillez choisir le type de compte", "Propriétaire", "Locataire"};
        // L'adapteur permet de mettre les options dans le spinner
        ArrayAdapter<String> adapteur = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, typeDeCompte);
        // Spécification du layout à utiliser pour afficher ce qu'il y a dans l'adapteur
        adapteur.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // On met l'adapteur dans le spinner
        typeCompte.setAdapter(adapteur);

        // Si on clique sur prénom , on efface le texte de nom et prenom
        prenomUsager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prenomUsager.setText("");
                nomUsager.setText("");
            }
        });

        // Si on veut annuler l'inscription, on retourne à l'activité PageLogIn
        annulerInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PageInscription.this, PageLogIn.class);
                startActivity(intent);
                finish();
            }
        });

        // Si on veut soumettre l'inscription
        soumettreInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // On récupère les valeurs des champs
                String prenom = prenomUsager.getText().toString();
                String nom = nomUsager.getText().toString();
                String telephone = telephoneUsager.getText().toString();
                String courriel = courrielUsager.getText().toString();
                String type = typeCompte.getSelectedItem().toString();
                String motDePasse = motPasse.getText().toString();
                String confirmationMotDePasse = confirmationMotPasse.getText().toString();

                // On vérifie que les champs ne sont pas vides
                if (prenom.isEmpty() || nom.isEmpty() || telephone.isEmpty() || courriel.isEmpty() || type.equals("Veuillez choisir le type de compte") || motDePasse.isEmpty() || confirmationMotDePasse.isEmpty()) {
                    // Si un champ est vide, on affiche un message d'erreur
                    Toast.makeText(getApplicationContext(), "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                } else {
                    // Confirmation du mot de passe
                    if (!motDePasse.equals(confirmationMotDePasse)) {
                        // Si les mots de passe ne correspondent pas, on affiche un message d'erreur
                        Toast.makeText(getApplicationContext(), "Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show();
                    } else {
                        // On crée un objet DatabaseUtil pour faire la requête
                        DatabaseUtil db = new DatabaseUtil();
                        try {
                            // On fait la requête pour ajouter un utilisateur
                            reponseRequete = db.ajoutUtilisateur(courriel, motDePasse, nom, prenom, telephone, type);
                            // Si la requête a réussi, on affiche un message de succès et on retourne a la page de Log In
                            if (reponseRequete == true) {
                                Toast.makeText(getApplicationContext(), "Inscription réussie", Toast.LENGTH_SHORT).show();
                                // On retourne à l'activité PageLogIn
                                Intent intent = new Intent(PageInscription.this, PageLogIn.class);
                                startActivity(intent);
                                finish();
                            } else {
                                // Si la requête a échoué, on affiche un message d'erreur
                                Toast.makeText(getApplicationContext(), "Erreur lors de l'inscription", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }
}


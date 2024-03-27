/* Page LogIn Loccappart
* Cette page permet à l'utilisateur de se connecter à l'application ainsi que de s'inscrire
* Création de la page : 25/03/2021 par David et Zakaria */

package com.david.appprojet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLException;

public class PageLogIn extends AppCompatActivity{

    //Composants de la page qui sont manipulés
    private EditText usernameId, passwordId;
    private Button inscriptionButton, connexionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_log_in);

        //Initialisation des composants
        usernameId = findViewById(R.id.username_id);
        passwordId = findViewById(R.id.password_id);
        inscriptionButton = findViewById(R.id.buttonInscription);
        connexionButton = findViewById(R.id.connexionButton);

        //Lorsqu'on click sur inscriptio
        inscriptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //On commence l'activité d'inscription
                Intent inscription = new Intent(PageLogIn.this, PageInscription.class);
                startActivity(inscription);
                finish();
            }
        });
        //Lorsqu'on click sur connexion
        connexionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseUtil db = new DatabaseUtil();
                db.connecterBD(new ApiCallback() {
                    @Override
                    public void onSuccess(String response) {
                        // Si la réponse est "true", les identifiants sont valides
                        if (response.equals("true")) {
                            // Redirection vers une autre activité ou traitement après la connexion réussie
                            Toast.makeText(PageLogIn.this, "Connexion réussie", Toast.LENGTH_SHORT).show();
                            // Ici, vous pouvez démarrer une nouvelle activité ou effectuer d'autres actions
                        } else {
                            // Sinon, les identifiants sont invalides
                            Toast.makeText(PageLogIn.this, "Identifiants invalides", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(String message) {
                        // En cas d'erreur lors de la connexion à la base de données
                        Toast.makeText(PageLogIn.this, "Erreur : " + message, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
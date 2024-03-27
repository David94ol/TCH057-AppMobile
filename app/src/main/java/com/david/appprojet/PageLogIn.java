/* Page LogIn Loccappart
* Cette page permet à l'utilisateur de se connecter à l'application ainsi que de s'inscrire
* Création de la page : 25/03/2021 par David et Zakaria */

package com.david.appprojet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.sql.SQLException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

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
            //TODO comme exemple mais faut le changer
            public void run () throws Exception {
                DatabaseUtil databaseUtil = new DatabaseUtil();
                databaseUtil.getConnection();
            }
            @Override
            public void onClick(View v) {
                try {
                    run();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }


}
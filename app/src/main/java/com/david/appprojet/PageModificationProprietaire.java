package com.david.appprojet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


/**
 * PageModificationProprietaire.java
 *
 * Cette classe représente l'activité qui gère la page de modification pour les propriétaires
 * dans l'application LocAppart. Elle offre aux propriétaires la possibilité d'ajouter une nouvelle
 * propriété, de modifier ou de supprimer une propriété existante, et de retourner à la page principale.
 *
 * L'interface utilisateur est définie dans le fichier XML activity_page_modification_proprietaire.xml.
 *
 * Fonctionnalités :
 * - Ajout d'un nouvel appartement.
 * - Modification ou suppression d'un appartement existant.
 * - Retour à la page principale de l'application.
 *
 * @author Monica Delao
 * @version 1
 * @since 25 mars 2024
 */

public class PageModificationProprietaire extends AppCompatActivity {

private ImageView imgV_ajouterProp;
private ImageView imgV_modifierProp;
private ImageView imgV_retourProp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_page_modification_proprietaire);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Lier les elements de l'interface
        imgV_ajouterProp = findViewById(R.id.img_addprop);
        imgV_modifierProp = findViewById(R.id.imgv_modifprp);
        imgV_retourProp = findViewById(R.id.imgV_sortieprp);

        //comportement si click sur ajouter Propriete

        imgV_ajouterProp.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(PageModificationProprietaire.this, AjouterNouvelleProp.class);
                startActivity(intent1);
            }
        });
        //comportement si click sur modifier Propriete
      /*  imgV_modifierProp.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(PageModificationProprietaire.this, ModifierProp.class);
                startActivity(intent2);
            }
        });

        //comportement si click sur RetourPagePrincipale
        imgV_retourProp.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
            }
        });*/



        }




}
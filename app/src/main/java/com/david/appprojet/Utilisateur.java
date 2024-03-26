/* Classe utilisateur
 * Cette classe permet d'instancier un utilisateur afin de pouvoir envoyer les donnees de l'utilisateur a la base de donnees
 * Création de la page : 25/03/2021 par David*/
package com.david.appprojet;

public class Utilisateur {
    //Attributs de l'utilisateur
    private String prenom;
    private String nom;
    private String telephone;
    private String courriel;
    private String typeCompte;
    private String motDePasse;

    private String confirmationMotDePasse;

    //Constructeur de l'utilisateur
    public Utilisateur(String prenom, String nom, String telephone, String courriel, String typeCompte, String motDePasse, String confirmationMotDePasse){
        this.prenom = prenom;
        this.nom = nom;
        this.telephone = telephone;
        this.courriel = courriel;
        this.typeCompte = typeCompte;
        this.motDePasse = motDePasse;
        this.confirmationMotDePasse = confirmationMotDePasse;
    }
    //Getters
    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getCourriel() {
        return courriel;
    }

    public String getTypeCompte() {
        return typeCompte;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public String getConfirmationMotDePasse() {
        return confirmationMotDePasse;
    }

}

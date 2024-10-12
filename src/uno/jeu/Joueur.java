package uno.jeu;

import uno.CoupIncorrect;
import uno.cartes.Carte;
import uno.cartes.PaquetDeCartes;

public abstract class Joueur {

    protected int typeDeJoueur;
    private String nom;
    protected PaquetDeCartes main;
    protected Uno uno;

    Joueur(Uno u)
    {
        this.uno = u;
        this.main = new PaquetDeCartes();
    }

    public abstract void jouer(String type) throws CoupIncorrect;

    public void pioche(Carte carte)
    {
        main.ajouter(carte);
    }

    public int getTypeDeJoueur()
    {
        return typeDeJoueur;
    }

    public PaquetDeCartes getHand()
    {
        return main;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public String getNom()
    {
        return nom;
    }

    @Override
    public String toString()
    {
        return "Type de joueur: " + this.getTypeDeJoueur() + " | Nom du joueur: " + this.getNom();
    }
}



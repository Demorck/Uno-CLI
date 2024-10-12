package uno.cartes;

import uno.jeu.Uno;

import java.util.Random;

/**
 * Singleton qui permet de fabriquer un paquet de cartes.
 */
public final class FabriqueCartes
{
    private static final FabriqueCartes instance = new FabriqueCartes();
    private PaquetDeCartes deck;

    private FabriqueCartes(){}

    public static FabriqueCartes getInstance()
    {
        return instance;
    }

    /**
     * Créer un paquet de 32 cartes aléatoires
     * @return PaquetDeCartes qui est aléatoire
     */
    public PaquetDeCartes getPaquet32()
    {
        deck = new PaquetDeCartes();
        Random r = new Random();
        for (int i = 0; i < 32; i++)
        {
            Chiffre c = new Chiffre(r.nextInt(10), Couleur.values()[r.nextInt(Couleur.values().length)]);
            deck.ajouter(c);
        }
        return deck;
    }

    public PaquetDeCartes get1Vert()
    {
        deck = new PaquetDeCartes();
        Chiffre c = new Chiffre(1, Couleur.VERT);
        c.setValeur(1);
        deck.ajouter(c);
        return deck;
    }

    /**
     * Crée un paquet complet de uno. 19 cartes chiffres de chaque couleur (2 entre 1-9 et 1 zéro -> (9*2+1)*4 = 76
     * 2 cartes couleur de Plus2, ChangementSens et PasseTour -> 2*4 = 8
     * 4 cartes noires -> 2*4 = 8
     *
     * @return Un paquet de uno complet
     */
    public PaquetDeCartes createUnoDeck(Uno uno)
    {
        deck = new PaquetDeCartes();
        /*
          On parcourt 10 fois chaque couleur MAIS si c'est la première fois, on le parcourt qu'une fois.
          Ce qui fait 9*2*4 + 1*4 = 4*(9*2+1) ce qui est égal à au dessus
         */
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < (i == 0 ? 1 : 2); j++)
                for (Couleur couleur : Couleur.values())
                    deck.ajouter(new Chiffre(uno, i, couleur));

        /*
         * Pour chaque couleur, on ajoute 2 Plus2, changement de sens et passe ton tour. J'ai préféré faire une seconde
         * boucle que ajouter deux fois dans le deck, c'est plus lisible je trouve
         */
        for (Couleur couleur : Couleur.values())
            for (int i = 0; i < 2; i++)
                deck.ajouter(new Plus2(uno, couleur), new ChangementDeSens(uno, couleur), new PasseTonTour(uno, couleur));

        /*
         * On ajoute 4 fois les deux cartes noires
         */
        for (int i = 0; i < 4; i++)
            deck.ajouter(new Plus4(uno), new Joker(uno));

        return deck;
    }

    /**
     * @return un paquet vide
     */
    public PaquetDeCartes getPaquetVide()
    {
        return new PaquetDeCartes();
    }
}

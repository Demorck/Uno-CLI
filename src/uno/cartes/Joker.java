package uno.cartes;

import uno.jeu.Uno;

/**
 * La carte Joker permet de jouer à la place de n'importe quelle carte et de choisir une couleur.
 * Elle a une valeur de 50 par défaut.
 *
 * @see Carte
 */
public class Joker extends Carte
{

    /**
     * Initialise le joker avec une valeur de 50 et une couleur {@code null}.
     */
    public Joker()
    {
        super(50, null);
    }

    public Joker(Uno u)
    {
        super(u, 50, null);
    }

    @Override
    public boolean peutEtreRecouvertePar(Carte c) {
        return c.peutEtrePoseeSur(this);
    }
    @Override
    public boolean peutEtrePoseeSur(Chiffre c)
    {
        return true;
    }

    @Override
    public boolean peutEtrePoseeSur(Plus2 c)
    {
        return true;
    }

    @Override
    public boolean peutEtrePoseeSur(ChangementDeSens c)
    {
        return true;
    }

    @Override
    public boolean peutEtrePoseeSur(PasseTonTour c)
    {
        return true;
    }

    @Override
    public boolean peutEtrePoseeSur(Plus4 c)
    {
        return true;
    }

    @Override
    public boolean peutEtrePoseeSur(Joker c)
    {
        return true;
    }

    @Override
    public void appliquerEffet() {}
}

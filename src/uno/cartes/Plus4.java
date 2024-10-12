package uno.cartes;

import uno.jeu.Uno;

/**
 * La carte Plus 4 permet de jouer à la place de n'importe quelle carte, de choisir une couleur, de rajouter 4 cartes au joueur suivant et de lui faire passer son tour.
 * Elle a une valeur de 50 par défaut.
 *
 * @see Carte
 */
public class Plus4 extends Carte
{

    /**
     * Initialise le Plus 4 avec une valeur de 50 et une couleur {@code null}.
     */
    public Plus4()
    {
        super(50, null);
    }

    public Plus4(Uno uno) { super(uno, 50, null); }

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
    public void appliquerEffet()
    {
        if (this.uno != null)
        {
            this.uno.nextPlayer();
            for (int i = 0; i < 4; i++)
            {
                this.uno.draw();
            }
        }
    }
}

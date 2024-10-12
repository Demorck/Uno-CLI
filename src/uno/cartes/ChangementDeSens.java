package uno.cartes;


import uno.jeu.Uno;

/**
 * La carte Changement de sens change le sens du jeu.
 * Elle a une valeur de 20 par défaut et peut prendre une couleur.
 *
 * @see Carte
 */
public class ChangementDeSens extends Carte
{

    /**
     * La carte changement de sens est initialisée avec une valeur de 20.
     * @param couleur La couleur initiale de la carte
     */
    public ChangementDeSens(Couleur couleur)
    {
        super(20, couleur);
    }

    public ChangementDeSens(Uno uno, Couleur couleur) { super(uno, 20, couleur); }

    @Override
    public boolean peutEtreRecouvertePar(Carte c) {
        return c.peutEtrePoseeSur(this);
    }

    @Override
    public boolean peutEtrePoseeSur(Chiffre c)
    {
        return this.estDeCouleurCompatibleAvec(c);
    }

    @Override
    public boolean peutEtrePoseeSur(Plus2 c)
    {
        return this.estDeCouleurCompatibleAvec(c);
    }

    @Override
    public boolean peutEtrePoseeSur(ChangementDeSens c)
    {
        return true;
    }

    @Override
    public boolean peutEtrePoseeSur(PasseTonTour c)
    {
        return this.estDeCouleurCompatibleAvec(c);
    }

    @Override
    public boolean peutEtrePoseeSur(Plus4 c)
    {
        return this.estDeCouleurCompatibleAvec(c);
    }

    @Override
    public boolean peutEtrePoseeSur(Joker c)
    {
        return this.estDeCouleurCompatibleAvec(c);
    }

    @Override
    public void appliquerEffet()
    {
        if (this.uno != null)
        {
            this.uno.inverserSens();
        }

    }
}

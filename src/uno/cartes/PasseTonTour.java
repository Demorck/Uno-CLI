package uno.cartes;


import uno.jeu.Uno;

/**
 * La carte Passe ton tour fait passer le tour du prochain joueur.
 * Elle a une valeur de 20 par défaut et peut prendre une couleur.
 *
 * @see Carte
 */
public class PasseTonTour extends Carte
{

    /**
     * La carte Passe ton tour est initialisée avec une valeur de 20.
     * @param couleur La couleur initiale de la carte
     */
    public PasseTonTour(Couleur couleur)
    {
        super(20, couleur);
    }

    public PasseTonTour(Uno uno,Couleur couleur) { super(uno, 20, couleur); }

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
        return this.estDeCouleurCompatibleAvec(c);
    }

    @Override
    public boolean peutEtrePoseeSur(PasseTonTour c)
    {
        return true;
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
            this.uno.nextPlayer();
    }
}

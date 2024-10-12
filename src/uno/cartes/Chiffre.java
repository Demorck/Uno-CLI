package uno.cartes;


import uno.jeu.Uno;

public class  Chiffre extends Carte
{

    public Chiffre(int valeur, Couleur couleur)
    {
        super(valeur, couleur);
    }

    public Chiffre(Uno uno, int valeur, Couleur couleur) { super(uno, valeur, couleur); }

    @Override
    public boolean peutEtreRecouvertePar(Carte c) {
        return c.peutEtrePoseeSur(this);
    }

    @Override
    public boolean peutEtrePoseeSur(Chiffre c)
    {
        return this.estDeCouleurCompatibleAvec(c) || this.getValeur() == c.getValeur();
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
    {}

    @Override
    public String toString()
    {
        return this.getValeur() + " " +
                this.getCouleur().getNom();
    }
}

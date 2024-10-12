package uno.cartes;

import uno.jeu.Uno;

/**
 * La class Carte est une class abstraite afin d'implémenter les différents types de cartes
 * <li>{@link Chiffre}</li>
 * <li>{@link Plus2}</li>
 * <li>{@link ChangementDeSens}</li>
 * <li>{@link PasseTonTour}</li>
 * <li>{@link Plus4}</li>
 * <li>{@link Joker}</li>
 *
 * Chaque carte a une valeur qui lui sert à compter le score final à la fin et une couleur.
 * @see Couleur
 * @author Maximilien ANTOINE
 */
public abstract class Carte
{
    /** La valeur est comprise entre 0 et 9 inclus pour les chiffres
     * <p> 20 pour les Plus 2, Changement de sens et Passe Ton Tour
     * <p> 50 pour les Plus 4 et Joker
     */
    private int valeur;
    /**
     * La couleur est soit {@code Rouge}, {@code Vert}, {@code Bleu} ou {@code Jaune}
     * <p> Pour les cartes {@code Joker} ou {@code Plus4}, la couleur est {@code null}.
     * @see Couleur
     */
    private Couleur couleur;

    /**
     * Utilisé pour les effets de carte
     */
    protected final Uno uno;

    /**
     * Créer une carte avec une valeur et une couleur.
     *
     * @param valeur La valeur initiale de la carte
     * @param couleur La couleur initiale de la carte
     */
    public Carte(int valeur, Couleur couleur)
    {
        this.valeur = valeur;
        this.couleur = couleur;
        this.uno = null;
    }

    /**
     * Créer une carte sans valeur réelle mais avec une couleur ({@code Plus2, Changement de Sens, Passe ton Tour})
     * @param uno L'instance de Uno
     * @param couleur La couleur initiale de la carte
     */
    public Carte(Uno uno, Couleur couleur) {
        this.uno = uno;
        this.couleur = couleur;
    }

    /**
     * Créer une carte sans valeur ni couleur
     * @param uno L'instance de Uno
     */
    public Carte(Uno uno) {
        this.uno = uno;
    }

    /**
     * Créer une carte avec une instance de Uno, une valeur et une couleur
     * @param uno l'instance de Uno
     * @param valeur La valeur de la carte
     * @param couleur La couleur initiale de la carte
     * @see Couleur
     */
    public Carte(Uno uno, int valeur, Couleur couleur)
    {
        this.uno = uno;
        this.valeur = valeur;
        this.couleur = couleur;
    }

    /**
     * @return La valeur de la carte
     */
    public int getValeur() {
        return this.valeur;
    }

    /**
     * Modifie la valeur de la carte ; utilisé pour des fins de débogages
     * @param valeur La valeur à modifier
     */
    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    /**
     * @return La couleur actuelle de la carte. Si la carte est noire ({@link Joker} ou {@link Plus4}), retourne {@code null}
     * @see Couleur
     */
    public Couleur getCouleur() {
        return this.couleur;
    }

    /**
     * Modifie la couleur de la carte ; utilisé pour les cartes {@link Joker} et {@link Plus4}.
     * @see Couleur
     * @param couleur
     */
    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    /**
     * Retourne la carte comme suit :
     * [NOM DE LA CARTE] [VALEUR SI CHIFFRE] [COULEUR SI EXISTE]
     * @return La carte en chaîne de caractère
     */
    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.getClass().getSimpleName()).append(" ");
        if (this.valeur < 10) stringBuilder.append(this.valeur).append(" ");
        if (!this.estSansCouleur()) stringBuilder.append(this.couleur.getNom()).append(" ");

        return stringBuilder.toString();
    }

    /**
     * Teste si {@code this} peut être recouverte par la carte {@code c}
     * @param c La carte à recouvrir
     * @return true si la carte peut être recouverte ; false sinon
     */
    public abstract boolean peutEtreRecouvertePar(Carte c);

    /**
     * Teste si la carte a une couleur
     * @see Couleur
     * @return true si la couleur est null, false sinon
     */
    public boolean estSansCouleur()
    {
        return this.couleur == null;
    }

    /**
     * Teste si {@code this} est de couleur compatible avec {@code c}
     * @see Couleur
     * @param c La carte à tester
     * @return true si les deux couleurs sont compatibles ; false sinon.
     */
    public boolean estDeCouleurCompatibleAvec(Carte c)
    {
        return this.estSansCouleur() || c.estSansCouleur() || c.getCouleur() == this.getCouleur();
    }

    /**
     * Teste si {@code this} peut être posée sur un {@link Chiffre}
     * @param c Une carte Chiffre
     * @return true si la carte peut être posée dessus ; false sinon.
     * @see Chiffre
     */
    public abstract boolean peutEtrePoseeSur(Chiffre c);

    /**
     * Teste si {@code this} peut être posée sur un {@link Plus2}
     * @param c Une carte Plus2
     * @return true si la carte peut être posée dessus ; false sinon.
     * @see Plus2
     */
    public abstract boolean peutEtrePoseeSur(Plus2 c);

    /**
     * Teste si {@code this} peut être posée sur un {@link ChangementDeSens}
     * @param c Une carte ChangementDeSens
     * @return true si la carte peut être posée dessus ; false sinon.
     * @see ChangementDeSens
     */
    public abstract boolean peutEtrePoseeSur(ChangementDeSens c);

    /**
     * Teste si {@code this} peut être posée sur un {@link PasseTonTour}
     * @param c Une carte PasseTonTour
     * @return true si la carte peut être posée dessus ; false sinon.
     * @see PasseTonTour
     */
    public abstract boolean peutEtrePoseeSur(PasseTonTour c);

    /**
     * Teste si {@code this} peut être posée sur un {@link Plus4}
     * @param c Une carte Plus4
     * @return true si la carte peut être posée dessus ; false sinon.
     * @see Plus4
     */
    public abstract boolean peutEtrePoseeSur(Plus4 c);

    /**
     * Teste si {@code this} peut être posée sur un {@link Joker}
     * @param c Une carte Joker
     * @return true si la carte peut être posée dessus ; false sinon.
     * @see Joker
     */
    public abstract boolean peutEtrePoseeSur(Joker c);

    /**
     * Permet d'appliquer les effets de cartes, voir les cartes en question
     */
    public abstract void appliquerEffet();

}
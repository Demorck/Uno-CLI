package uno.cartes;


import java.util.Random;

/**
 * {@code Couleur} est un enum décrivant les 4 couleurs possibles dans un Uno: Bleu, Rouge, Jaune et Vert.
 * Ils sont utilisés pour les différentes {@link Carte}.
 * Une carte {@link Joker} ou {@link Plus4} n'a pas de couleur pas défaut, donc sa couleur est {@code null}.
 */
public enum Couleur
{
    BLEU("Bleu"),
    ROUGE("Rouge"),
    JAUNE("Jaune"),
    VERT("Vert");

    final String fr;
    Couleur(String fr)
    {
        this.fr = fr;
    }

    public String getNom()
    {
        return this.fr;
    }

    /**
     * Les couleurs en paramètre peuvent être: bleu, rouge, jaune et vert.
     * <p>La casse n'a pas besoin d'être respectée
     * @param nom Le nom à mettre parmi les couleurs existantes
     * @return La couleur correspondante ; si ce n'est pas parmi la liste, retourne {@code null}
     */
    public static Couleur getCouleurParNom(String nom) {
        switch (nom.toLowerCase())
        {
            case "b":
                return Couleur.BLEU;
            case "r":
                return Couleur.ROUGE;
            case "j":
                return Couleur.JAUNE;
            case "v":
                return Couleur.VERT;
            default:
                return null;
        }
    }

    /**
     * @return L'enum en chaîne
     */
    public String toString()
    {
        return this.fr;
    }

    /**
     * Donne une couleur aléatoire parmi celles proposées.
     * @return Une couleur
     */
    public static Couleur getRandom()
    {
        Random r = new Random();
        return Couleur.values()[r.nextInt(Couleur.values().length)];
    }

}

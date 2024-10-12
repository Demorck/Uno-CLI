package uno.jeu;

import uno.CoupIncorrect;
import uno.cartes.Carte;
import uno.cartes.Couleur;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * La class JoueurHumain est un enfant de la class {@link Joueur}.
 * <p> Pour que l'utilisateur joue, il doit choisir une carte via son paquet
 *
 * @see Joueur
 * @author Maximilien ANTOINE
 */
public class JoueurHumain extends Joueur{
    JoueurHumain(Uno u)
    {
        super(u);
        this.typeDeJoueur = 1;
    }

    /**
     * Permet d'identifier les cartes et permet de piocher.
     * @param type Le type de coup joué. Si le type ne respecte pas, ça passe le tour
     * @throws CoupIncorrect Si le coup ne respecte pas les consignes (p pour piocher, [nombre][rvbj] pour une carte)
     *
     */
    @Override
    public void jouer(String type) throws CoupIncorrect
    {
        if (type.equals("p"))
        {
            jouerPioche();
        }
        else
        {
            try
            {
                jouerCarte(type);
            }
            catch (CoupIncorrect e)
            {
                System.out.println(e.getMessage());
            }
        }

        this.uno.nextPlayer();
        this.uno.getDlc().react();
    }

    /**
     * Si le joueur veut piocher
     * <br>Joue automatiquement la carte piochée si possible
     */
    private void jouerPioche()
    {
        uno.draw();
        Carte carte = this.getHand().getSommet();
        if (peutJouerCarte(carte))
        {
            jouerCarteSurTalon(carte);
        }
    }

    /**
     * Joue la carte sur le talon : l'ajoute, applique l'effet puis enlève la carte de la main
     * @param c la carte à jouer
     */
    private void jouerCarteSurTalon(Carte c)
    {
        this.uno.ajouterTalon(c);
        c.appliquerEffet();
        main.enlever(c);
    }

    /**
     * @param c La carte à jouer
     * @return vrai si la carte peut être jouée ; false sinon
     */
    private boolean peutJouerCarte(Carte c)
    {
        return uno.getTopCard().peutEtreRecouvertePar(c);
    }

    /**
     * Joue le coup
     * @param type Le coup joué
     * @throws CoupIncorrect si le coup est incorrect
     */
    private void jouerCarte(String type) throws CoupIncorrect
    {
        Pattern patternCarte = Pattern.compile("(\\d+)([rvbj])?");
        Matcher matcher = patternCarte.matcher(type);
        
        if (matcher.matches()) {
            try {
                Carte carte = obtenirCarteSelonPattern(matcher);

                validerEtJouerCarte(carte);

            } catch (CoupIncorrect e) {
                System.out.println(e.getMessage());
            }
        } else {
            throw new CoupIncorrect("Coup incorrect, le tour passe.");
        }
    }

    /**
     * @param carte La carte à jouer
     * @throws CoupIncorrect Si la carte ne peut être jouée sur le paquet
     */
    private void validerEtJouerCarte(Carte carte) throws CoupIncorrect
    {
        if (!peutJouerCarte(carte)) {
            throw new CoupIncorrect("Cette carte ne peut pas être jouée sur le paquet.");
        }

        jouerCarteSurTalon(carte);
    }

    /**
     * @param matcher Le regex pour trouver la carte
     * @return Trouve la carte dans la main. Retourne null si aucune carte n'est trouvée
     */
    private Carte obtenirCarteSelonPattern(Matcher matcher)
    {
        if (matcher.group(1) == null)
            return null;

        int numeroCarte = Integer.parseInt(matcher.group(1));
        Carte carte = main.getCarte(numeroCarte - 1);

        if (matcher.group(2) != null && carte.estSansCouleur()) {
            Couleur couleur = Couleur.getCouleurParNom(matcher.group(2));
            carte.setCouleur(couleur);
        }

        return carte;
    }
}

package uno.jeu;

import uno.CoupIncorrect;
import uno.cartes.Carte;
import uno.cartes.Couleur;

/**
 * La class Bot est enfant de la class {@link Joueur}. Elle permet de faire les coups des ordinateurs au Uno.
 * <p>Il pose la première carte qu'il trouve, sinon il pioche.
 * <p>Si cette nouvelle carte est posable, il la joue, sinon il passe.
 *
 * @see Joueur
 * @author Maximilien ANTOINE
 */
public class Bot extends Joueur
{
    Bot(Uno u)
    {
        super(u);
        this.typeDeJoueur = 0;
    }

    /**
     * Joue la première carte jouable. Sinon, il pioche.
     * <p>Si cette dernière peut être posée, il la joue sinon il passe son tour.
     * @param type est {@code null} pour un bot
     */
    @Override
    public void jouer(String type) throws CoupIncorrect
    {
        // Initialise la carte à null
        Carte cardToPlay = null;

        // Cherche la première carte qui peut être recouverte par le talon.
        for (Carte carte : main)
        {
            if (carte.peutEtreRecouvertePar(uno.getTopCard()))
            {
                cardToPlay = carte;
                main.enlever(carte);
                break;
            }
        }

        // Si aucune carte n'a été trouvée, le bot pioche
        if (cardToPlay == null)
        {
            uno.draw();
            // La carte au sommet, c'est la carte piochée
            Carte carteDrew = main.getSommet();

            // Si la carte piochée fonctionne, PAF c'est la carte à jouer
            if (uno.getTopCard().peutEtreRecouvertePar(carteDrew))
                cardToPlay = carteDrew;
        }

        // Si après avoir pioché ou toutes les cartes, on a une carte disponible, on va la jouer, sinon on fait rien
        if (cardToPlay != null)
        {
            // Si la carte est sans couleur, on en choisit une au hasard
            if (cardToPlay.estSansCouleur())
            {
                Couleur newCouleur = Couleur.getRandom();
                cardToPlay.setCouleur(newCouleur);
            }

            // On joue la carte et on l'enlève de la main.
            uno.ajouterTalon(cardToPlay);
            cardToPlay.appliquerEffet();
            main.enlever(cardToPlay);
        }

        this.uno.nextPlayer();
        this.uno.getDlc().react();

    }
}

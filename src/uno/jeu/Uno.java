package uno.jeu;

import uno.CoupIncorrect;
import uno.cartes.Carte;
import uno.cartes.FabriqueCartes;
import uno.cartes.PaquetDeCartes;

import java.util.ArrayList;
import java.util.Random;

/**
 * La class Uno permet de gérer tout le jeu en dualité avec {@link DialogueLigneDeCommande}.
 * <p>C'est un peu comme un modèle MVC où Uno est le controllet et DLC la view.
 * @author Maximilien
 */
public class Uno
{
    /**
     * Compte le nombre de tours (même passée)
     * <p>Utilisé à des fins de débogages, voir {@link DialogueLigneDeCommande#debug}
     */
    public int nbTour = 1;

    /**
     * L'instance de DialogueLigneDeCommande
     * @see DialogueLigneDeCommande
     */
    private DialogueLigneDeCommande dlc;

    /**
     * L'état actuel du jeu
     * @see State
     */
    private State state;

    /**
     * La pioche du Uno.
     */
    private PaquetDeCartes pioche;

    /**
     * Le talon, là où on pose les cartes.
     */
    private PaquetDeCartes talon;

    /**
     * Les différents joueurs
     * @see Joueur
     */
    private final ArrayList<Joueur> joueurs;

    /**
     * Le sens de jeu. Peut prendre {@code 1} ou {@code -1} en fonction du sens.
     */
    private int sens = 1;

    /**
     * L'indice du joueur actuel
     */
    private int actualPlayer;

    /**
     * Crée une instance de Uno, initialise les variables et créer {@code DialogueLigneDeCommande}.
     */
    public Uno() {
        state = State.Idle;
        joueurs = new ArrayList<>();
        talon = new PaquetDeCartes();
        pioche = new PaquetDeCartes();
        dlc = new DialogueLigneDeCommande(this);
    }

    /**
     * Crée une instance de Uno, initialise les variables et créer {@code DialogueLigneDeCommande}.
     * <br>Permet d'activer le mode debug.
     */
    public Uno(boolean b)
    {
        state = State.Idle;
        joueurs = new ArrayList<>();
        talon = new PaquetDeCartes();
        pioche = new PaquetDeCartes();
        dlc = new DialogueLigneDeCommande(this, b);
    }

    /**
     *
     * @param dlc DialogueLigneDeCommande object
     */
    public void setDLC(DialogueLigneDeCommande dlc)
    {
        this.dlc = dlc;
    }

    /**
     * @return l'état du jeu
     * @see State
     */
    public State getState()
    {
        return this.state;
    }

    /**
     * Change l'état du jeu
     * @param state L'état à modifier
     * @see State
     */
    public void setState(State state)
    {
        this.state = state;
    }

    /**
     * Permet de jouer le coup
     * @param coup {@code null} si joué par un bot. Sinon voir {@link JoueurHumain#jouer(String)}.
     * @throws CoupIncorrect Si le coup ne peut pas être joué à cause de l'utilisateur.
     */
    public void play(String coup) throws CoupIncorrect
    {
        joueurs.get(actualPlayer).jouer(coup);
    }

    /**
     * Ajoute une carte au talon. (Joue une carte quoi).
     * @param card La carte jouée.
     */
    public void ajouterTalon(Carte card)
    {
        talon.ajouter(card);
    }

    /**
     * Fait piocher le joueur actuel d'une carte
     */
    public void draw()
    {
        if (pioche.estVide())
        {
            nouvellePioche();
        }

        joueurs.get(actualPlayer).pioche(pioche.piocher());
    }

    /**
     * Permet de retourner la carte du dessus.
     * @return La carte au sommet du talon
     */
    public Carte getTopCard()
    {
        return talon.getSommet();
    }

    /**
     * @return le sens du jeu
     * @see #sens
     */
    public int getSens()
    {
        return this.sens;
    }

    /**
     * Initialise le jeu en créant les joueurs, le paquet de Uno, la distribution, etc.
     * @param numberOfBots Le nombre de bots à ajouter.
     */
    public void initialize(int numberOfBots)
    {
        this.createPlayers(numberOfBots, 1);

        // Fabrique le jeu
        pioche = FabriqueCartes.getInstance().createUnoDeck(this);
        pioche.melanger();

        // Choisi le joueur de base / donneur
        Random random = new Random();
        actualPlayer = random.nextInt(joueurs.size());

        if (getActualPlayer().getTypeDeJoueur() == 0)
            this.state = State.Bot;
        else
            this.state = State.Player;

        // Créer la main de chaque joueur
        deal();

        // Créer le talon
        talon.ajouter(pioche.piocher());
    }

    /**
     * @return Le joueur actuel
     */
    public Joueur getActualPlayer()
    {
        return joueurs.get(actualPlayer);
    }

    /**
     * @return vrai si la main du joueur actuel est vide ; false sinon.
     */
    public boolean isGameOver()
    {
        return getActualPlayer().main.estVide();
    }

    /**
     * @return L'instance de DialogueLigneDeCommande.
     */
    public DialogueLigneDeCommande getDlc()
    {
        return dlc;
    }

    /**
     * Inverse le sens du jeu
     */
    public void inverserSens()
    {
        sens *= -1;
    }

    /**
     * Change le joueur actuel en fonction du sens. (-1 ou +1)
     * <br>Change également le type de joueur qui joue ; si personne ne joue, on arrête la partie
     * <br>On ne modifie par le joueur si la main est vide
     * @see #actualPlayer
     * @see #sens
     * @see State
     */
    public void nextPlayer()
    {
        if (isGameOver())
        {
            state = State.GameOver;
            dlc.react();
            return;
        }

        actualPlayer = (actualPlayer + sens + joueurs.size()) % joueurs.size();
        switch (this.getActualPlayer().getTypeDeJoueur())
        {
            case 0:
                state = State.Bot;
                break;
            case 1:
                state = State.Player;
                break;
            default:
                state = State.GameOver;
                break;
        }
        nbTour++;
    }

    /**
     * Distribue 7 cartes par joueur
     */
    public void deal()
    {
        for (Joueur joueur : joueurs)
        {
            for (int i = 0; i < 7; i++)
            {
                joueur.pioche(pioche.piocher());
            }
        }
    }

    /**
     * Créer les joueurs et les bots et leur nom
     * @param nbBots Le nombre de bots à créer
     * @param nbHumains Le nombre d'humains à créer
     */
    public void createPlayers(int nbBots, int nbHumains)
    {
        for (int i = 0; i < nbBots; i++)
        {
            joueurs.add(new Bot(this));
            joueurs.get(i).setNom("Bot " + (i + 1));
        }

        for (int i = 0; i < nbHumains; i++)
        {
            joueurs.add(new JoueurHumain(this));
            String name = this.dlc.promptName();
            joueurs.get(joueurs.size() - 1).setNom(name);
        }
    }

    /**
     * @return la pioche
     */
    public PaquetDeCartes getPioche()
    {
        return pioche;
    }

    /**
     * Créer une nouvelle pioche si elle est vide
     */
    public void nouvellePioche()
    {
        Carte topTalon = talon.piocher();
        pioche.ajouter(talon);
        talon = new PaquetDeCartes();
        talon.ajouter(topTalon);
    }
}



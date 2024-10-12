package uno.jeu;

import java.util.Objects;
import java.util.Scanner;

import uno.CoupIncorrect;
import uno.cartes.Carte;
import uno.cartes.PaquetDeCartes;

/**
 * La class permet de faire toutes les entrées et sorties du jeu.
 * <br>Beaucoup de messages sont fait avec des constantes de la class {@link Message}
 * @author Maximilien
 */
public class DialogueLigneDeCommande {
    /**
     * L'instance de Uno
     */
    private final Uno uno;
    /**
     * Le scanner pour les entrées
     */
    private final Scanner sc;
    /**
     * Utilisé à des fins de déboages. Pour l'activer, lancer l'application avec les paramètres {@code --debug}
     */
    private boolean debug = false;

    /**
     * Créer la classe avec l'instance de uno puis initialise le uno.
     * @param u L'instance de uno
     */
    DialogueLigneDeCommande(Uno u)
    {
        this.uno = u;
        this.uno.setDLC(this);
        this.sc = new Scanner(System.in);
    }

    /**
     * Créer la class avec l'instance de uno puis l'initialise
     * @param u L'instance de uno
     * @param debug Utilisé à des fins de débogage, voir {@link #debug}
     * @see #debug
     */
    DialogueLigneDeCommande(Uno u, boolean debug)
    {
        this.uno = u;
        this.uno.setDLC(this);
        this.sc = new Scanner(System.in);
        this.debug = debug;

    }

    /**
     * Un peu "l'update" de tout le jeu. Affiche les choses, demande à l'utilisateur, etc.
     */
    public void react()
    {
        State state = this.uno.getState();

        if (state == State.Idle)
            return;

        if (state == State.GameOver)
        {
            printGameOver(this.uno.getActualPlayer());
            this.uno.setState(State.Idle);
            return;
        }

        // Affiche toutes les infos importantes
        printTopCard();
        printWhoPlay();
        System.out.println();
        printPlayerHand();
        System.out.println();
        if (debug)
            printDebugInfos();

        if (state == State.Bot)
        {
            try
            {
                this.uno.play(null);
            } catch (CoupIncorrect ignored) {}
        }
        else if (state == State.Player)
        {
            String typeOfCard = getPlayerInput(Message.PROMPT_CARD);
            try
            {
                this.uno.play(typeOfCard);
            } catch (CoupIncorrect e)
            {
                System.out.println(e.getMessage());

            }
        }

    }

    /**
     * Utilisé à des fins de débogages
     * @see #debug
     */
    private void printDebugInfos()
    {
        System.out.print("DEBUG INFOS: Sens: ");
        System.out.print(this.uno.getSens() + " | Tour numéro: " + this.uno.nbTour + " | State : " + this.uno.getState());
        System.out.println();
    }

    /**
     * Affiche qui joue puis affiche le nombre de cartes du joueur.
     * <br>"C'est au tour de [NOM JOUEUR] de jouer."
     */
    private void printWhoPlay()
    {
        Joueur currentPlayer = this.uno.getActualPlayer();

        System.out.println(Message.NEW_TURN_1 + currentPlayer.getNom() + Message.NEW_TURN_2);
        System.out.println(currentPlayer.getNom() + " a " + currentPlayer.getHand().getNombreDeCartes() + Message.NUMBER_CARD_IN_HAND);
    }

    /**
     * Affiche la carte du talon.
     * <br>"La carte du dessus est: "
     */
    public void printTopCard()
    {
        System.out.println(Message.TOP_CARD + uno.getTopCard().toString());
    }

    /**
     * Affiche que le jeu est terminé
     * @param joueur Le joueur qui a gagné
     */
    public void printGameOver(Joueur joueur)
    {
        System.out.println(Message.GAME_OVER);
        System.out.println(joueur.getNom() + Message.GAME_WON);
    }

    /**
     * Demande le nombre d'ordinateurs ; minimum 1
     * @return Le nombre d'ordinateurs
     */
    public int promptNumberOfBots()
    {
        String userInput;
        int nbOfPlayer = 0;
        do
        {
            userInput = getPlayerInput(Message.PROMPT_BOTS);
            try
            {
                nbOfPlayer = Integer.parseInt(userInput);
                if (nbOfPlayer < 1)
                {
                    System.out.println(Message.ERROR_NB_BOTS);
                }
            }
            catch (NumberFormatException e)
            {
                System.out.println(Message.ERROR_INPUT_NOT_VALID);
            }
        } while (nbOfPlayer < 1);
        return nbOfPlayer;
    }

    /**
     * Ferme le scanner. A utiliser en fin de programme.
     */
    public void closeScanner()
    {
        if (sc != null)
        {
            sc.close();
        }
    }

    /**
     * Demande le nom du joueur
     * @return le nom du joueur
     */
    public String promptName()
    {
        String s;
        do
        {
            s = getPlayerInput(Message.PROMPT_NAME);
            if (Objects.equals(s, ""))
            {
                System.out.println(Message.ERROR_INVALID_NAME);
            }
        } while (Objects.equals(s, ""));

        return s;
    }

    /**
     * Affiche la main du joueur.
     * <p>Si {@link #debug} est activé, affiche toutes les mains.
     */
    public void printPlayerHand()
    {
        Joueur currentPlayer = this.uno.getActualPlayer();
        if (debug || currentPlayer.getTypeDeJoueur() == 1)
        {
            System.out.println(Message.HAND);
            PaquetDeCartes currentHand = currentPlayer.getHand();

            int i = 1;
            for (Carte carte : currentHand)
            {
                System.out.println(i + " : " + carte.toString());
                i++;
            }
        }
    }

    /**
     * Demande à l'utilisateur de rentrer quelque chose
     * @param message Le message à afficher
     * @return La chose rentrée par l'utilisateur
     */
    private String getPlayerInput(String message)
    {
        System.out.println(message);
        while (!sc.hasNextLine()) {}
        return sc.nextLine();
    }

}

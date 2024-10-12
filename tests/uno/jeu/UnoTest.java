package uno.jeu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uno.cartes.*;

import static org.junit.jupiter.api.Assertions.*;

class UnoTest
{
    Uno u;

    @BeforeEach
    void init()
    {
        u = new Uno();
        u.setDLC(new DialogueLigneDeCommande(u));
    }


    @Test
    void setDLC()
    {
        assertNotNull(u.getDlc());
    }
    @Test
    void createPlayers()
    {
        u.createPlayers(3, 0);
        Joueur j = u.getActualPlayer();
        u.nextPlayer();
        u.nextPlayer();
        u.nextPlayer();
        Joueur j2 = u.getActualPlayer();
        assertEquals(j, j2);

    }

    @Test
    void ajouterTalon()
    {
        Carte c = new Chiffre(6, Couleur.BLEU);
        u.ajouterTalon(c);
        Carte a = u.getTopCard();
        assertEquals(c, a);
    }

    @Test
    void draw()
    {
        PaquetDeCartes pioche = FabriqueCartes.getInstance().createUnoDeck(u);
        pioche.melanger();
        u.getPioche().ajouter(pioche);
        u.createPlayers(3, 0);
        u.deal();
        assertEquals(u.getActualPlayer().getHand().getNombreDeCartes(), 7);
    }

    @Test
    void isGameOver()
    {
        u.createPlayers(3, 0);
        Carte c = new Chiffre(u, 4, Couleur.ROUGE);
        u.getActualPlayer().getHand().ajouter(c);
        assertFalse(u.isGameOver());
        u.nextPlayer();
        assertTrue(u.isGameOver());
    }

    @Test
    void inverserSens()
    {
        assertEquals(u.getSens(), 1);
        u.inverserSens();
        assertEquals(u.getSens(), -1);
    }
}
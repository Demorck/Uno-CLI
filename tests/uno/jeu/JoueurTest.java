package uno.jeu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uno.cartes.Chiffre;
import uno.cartes.Couleur;

import static org.junit.jupiter.api.Assertions.*;

class JoueurTest
{
    Joueur bot;
    Joueur humain;
    Uno u;
    @BeforeEach
    void setUp()
    {
        u = new Uno();
        bot = new Bot(u);
        humain = new JoueurHumain(u);
        Chiffre c = new Chiffre(u, 4, Couleur.ROUGE);
        for (int i = 0; i < 6; i++)
            humain.pioche(c);
    }

    @Test
    void pioche()
    {
        Chiffre c = new Chiffre(u, 6, Couleur.BLEU);
        bot.pioche(c);
        assertEquals(bot.getHand().getNombreDeCartes(), 1);
    }

    @Test
    void getTypeDeJoueur()
    {
        assertEquals(bot.getTypeDeJoueur(), 0);
        assertEquals(humain.getTypeDeJoueur(), 1);
    }
}
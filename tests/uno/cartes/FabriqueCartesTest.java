package uno.cartes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uno.jeu.Uno;

import static org.junit.jupiter.api.Assertions.*;

class FabriqueCartesTest
{
    FabriqueCartes fc;
    PaquetDeCartes pdc;

    @BeforeEach
    void setUp()
    {
        fc = FabriqueCartes.getInstance();
        pdc = new PaquetDeCartes();
    }

    @Test
    @DisplayName("Génère un paquet de 32 cartes aléatoires")
    void getPaquet32()
    {
        pdc = fc.getPaquet32();
        assertEquals(pdc.getNombreDeCartes(), 32);
    }

    @Test
    @DisplayName("Génère un paquet de 1 carte : un 1 vert")
    void get1Vert()
    {
        pdc = fc.get1Vert();
        assertFalse(pdc.estVide());
        Chiffre c = new Chiffre(1, Couleur.VERT);
        pdc.enlever(c);
        assertTrue(pdc.estVide());
    }

    @Test
    @DisplayName("Génère un paquet complet de Uno")
    void createUnoDeck()
    {
        pdc = fc.createUnoDeck(new Uno());
        assertEquals(pdc.getNombreDeCartes(), 108);
    }

    @Test
    @DisplayName("Génère un paquet vide")
    void getPaquetVide()
    {
        pdc = fc.getPaquetVide();
        assertTrue(pdc.estVide());
    }
}
package uno.cartes;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import uno.jeu.Uno;

import static org.junit.jupiter.api.Assertions.*;

/**
 * J'aimerais bien créer un paquet de le mélanger et tester les cartes aléatoirements mais on ne peut pas deviner à l'avance l'issue des tests. Donc c'est embêtant.
 */
class CarteTest
{
    Chiffre chiffreTemoin;
    Chiffre chiffreNumero;
    Chiffre chiffreCouleur;
    Chiffre chiffreVert;
    Chiffre chiffreJaune;
    Plus2 plus2;
    Plus4 plus4;
    ChangementDeSens inversion;
    PasseTonTour passe;
    Joker joker;
    Uno uno;

    @BeforeEach
    void setUp()
    {
        uno = new Uno();

        chiffreTemoin = new Chiffre(4, Couleur.BLEU);
        chiffreNumero = new Chiffre(4, Couleur.ROUGE);
        chiffreCouleur = new Chiffre(9, Couleur.BLEU);
        chiffreVert = new Chiffre(9, Couleur.VERT);
        chiffreJaune = new Chiffre(9, Couleur.JAUNE);

        plus2 = new Plus2(Couleur.VERT);

        plus4 = new Plus4();

        inversion = new ChangementDeSens(Couleur.ROUGE);

        joker = new Joker();

        passe = new PasseTonTour(Couleur.JAUNE);
    }

    @Test
    @DisplayName("Test de pleins de possibilités")
    void peutEtreRecouvertePar()
    {
        assertTrue(chiffreTemoin.peutEtreRecouvertePar(chiffreNumero));
        assertTrue(chiffreTemoin.peutEtreRecouvertePar(chiffreCouleur));
        assertFalse(chiffreTemoin.peutEtreRecouvertePar(plus2));
        assertTrue(chiffreTemoin.peutEtreRecouvertePar(new Plus2(Couleur.BLEU)));
        assertFalse(chiffreTemoin.peutEtreRecouvertePar(inversion));
        assertTrue(chiffreTemoin.peutEtreRecouvertePar(new ChangementDeSens(Couleur.BLEU)));
        assertFalse(chiffreTemoin.peutEtreRecouvertePar(passe));
        assertTrue(chiffreTemoin.peutEtreRecouvertePar(new PasseTonTour(Couleur.BLEU)));
        assertTrue(chiffreTemoin.peutEtreRecouvertePar(plus4));
        assertTrue(chiffreTemoin.peutEtreRecouvertePar(joker));

        assertTrue(chiffreTemoin.peutEtreRecouvertePar(chiffreCouleur));
        assertTrue(chiffreTemoin.peutEtreRecouvertePar(chiffreCouleur));
        assertTrue(chiffreTemoin.peutEtreRecouvertePar(chiffreCouleur));
        assertFalse(chiffreTemoin.peutEtreRecouvertePar(new Chiffre(6, Couleur.ROUGE)));

        assertTrue(plus2.peutEtreRecouvertePar(chiffreVert));
        assertFalse(plus2.peutEtreRecouvertePar(chiffreJaune));
        assertTrue(plus2.peutEtreRecouvertePar(new Plus2(Couleur.BLEU)));
        assertFalse(plus2.peutEtreRecouvertePar(inversion));
        assertTrue(plus2.peutEtreRecouvertePar(new ChangementDeSens(Couleur.VERT)));
        assertFalse(plus2.peutEtreRecouvertePar(passe));
        assertTrue(plus2.peutEtreRecouvertePar(new PasseTonTour(Couleur.VERT)));

        assertFalse(inversion.peutEtreRecouvertePar(chiffreVert));
        assertTrue(inversion.peutEtreRecouvertePar(chiffreNumero));
        assertTrue(inversion.peutEtreRecouvertePar(new Plus2(Couleur.ROUGE)));
        assertTrue(inversion.peutEtreRecouvertePar(new ChangementDeSens(Couleur.ROUGE)));
        assertFalse(inversion.peutEtreRecouvertePar(passe));
        assertTrue(inversion.peutEtreRecouvertePar(new PasseTonTour(Couleur.ROUGE)));

        assertFalse(passe.peutEtreRecouvertePar(chiffreVert));
        assertTrue(passe.peutEtreRecouvertePar(chiffreJaune));
        assertTrue(passe.peutEtreRecouvertePar(new Plus2(Couleur.JAUNE)));
        assertFalse(passe.peutEtreRecouvertePar(inversion));
        assertTrue(passe.peutEtreRecouvertePar(new ChangementDeSens(Couleur.JAUNE)));
        assertTrue(passe.peutEtreRecouvertePar(new PasseTonTour(Couleur.JAUNE)));


        assertTrue(plus4.peutEtreRecouvertePar(chiffreJaune));
        plus4.setCouleur(Couleur.ROUGE);
        assertFalse(plus4.peutEtreRecouvertePar(chiffreJaune));

        assertTrue(joker.peutEtreRecouvertePar(chiffreJaune));
        joker.setCouleur(Couleur.ROUGE);
        assertFalse(joker.peutEtreRecouvertePar(chiffreJaune));


        plus2.getCouleur();
    }

    @Test
    @DisplayName("Test si les cartes ont une couleur ou non")
    void estSansCouleur()
    {
        assertFalse(chiffreJaune.estSansCouleur());
        assertFalse(plus2.estSansCouleur());
        assertTrue(plus4.estSansCouleur());
        assertTrue(joker.estSansCouleur());
    }

    @Test
    @DisplayName("Test si les couleurs sont les mêmes ou avec une sans couleur")
    void estDeCouleurCompatibleAvec()
    {
        assertFalse(chiffreTemoin.estDeCouleurCompatibleAvec(plus2));
        assertFalse(chiffreTemoin.estDeCouleurCompatibleAvec(inversion));
        assertFalse(chiffreTemoin.estDeCouleurCompatibleAvec(passe));
        assertTrue(chiffreTemoin.estDeCouleurCompatibleAvec(plus4));
        assertTrue(chiffreTemoin.estDeCouleurCompatibleAvec(joker));
    }

    @Test
    @DisplayName("Peut être posée sur un chiffre")
    void peutEtrePoseeSur()
    {
        assertTrue(joker.peutEtrePoseeSur(chiffreTemoin));
        assertTrue(plus4.peutEtrePoseeSur(chiffreTemoin));

        assertTrue(plus2.peutEtrePoseeSur(chiffreVert));
        assertFalse(plus2.peutEtrePoseeSur(chiffreTemoin));

        assertFalse(inversion.peutEtrePoseeSur(chiffreTemoin));
        assertTrue(inversion.peutEtrePoseeSur(chiffreNumero));

        assertFalse(passe.peutEtrePoseeSur(chiffreTemoin));
        assertTrue(passe.peutEtrePoseeSur(chiffreJaune));

        assertTrue(chiffreTemoin.peutEtrePoseeSur(chiffreCouleur));
        assertTrue(chiffreTemoin.peutEtrePoseeSur(chiffreNumero));
        assertFalse(chiffreTemoin.peutEtrePoseeSur(chiffreVert));
    }

    @Test
    @DisplayName("Peut être posée sur un Plus2")
    void testPeutEtrePoseeSur()
    {
        assertTrue(joker.peutEtrePoseeSur(plus2));
        assertTrue(plus4.peutEtrePoseeSur(plus2));

        assertTrue(plus2.peutEtrePoseeSur(plus2));

        assertFalse(inversion.peutEtrePoseeSur(plus2));
        inversion.setCouleur(plus2.getCouleur());
        assertTrue(inversion.peutEtrePoseeSur(plus2));

        assertFalse(passe.peutEtrePoseeSur(plus2));
        passe.setCouleur(plus2.getCouleur());
        assertTrue(passe.peutEtrePoseeSur(plus2));

        assertFalse(chiffreTemoin.peutEtrePoseeSur(plus2));
        assertTrue(chiffreVert.peutEtrePoseeSur(plus2));
    }

    @Test
    @DisplayName("Peut être posée sur un Changement de Sens")
    void testPeutEtrePoseeSur1()
    {
        assertTrue(joker.peutEtrePoseeSur(inversion));
        assertTrue(plus4.peutEtrePoseeSur(inversion));

        assertFalse(plus2.peutEtrePoseeSur(inversion));
        plus2.setCouleur(inversion.getCouleur());
        assertTrue(plus2.peutEtrePoseeSur(inversion));

        assertTrue(inversion.peutEtrePoseeSur(inversion));

        assertFalse(passe.peutEtrePoseeSur(inversion));
        passe.setCouleur(inversion.getCouleur());
        assertTrue(passe.peutEtrePoseeSur(inversion));

        assertFalse(chiffreTemoin.peutEtrePoseeSur(inversion));
        assertTrue(chiffreNumero.peutEtrePoseeSur(inversion));
    }

    @Test
    @DisplayName("Peut être posée sur un Passe ton tour")
    void testPeutEtrePoseeSur2()
    {
        assertTrue(joker.peutEtrePoseeSur(passe));
        assertTrue(plus4.peutEtrePoseeSur(passe));

        assertFalse(plus2.peutEtrePoseeSur(passe));
        plus2.setCouleur(passe.getCouleur());
        assertTrue(plus2.peutEtrePoseeSur(passe));

        assertFalse(inversion.peutEtrePoseeSur(passe));
        inversion.setCouleur(plus2.getCouleur());
        assertTrue(inversion.peutEtrePoseeSur(passe));

        assertTrue(passe.peutEtrePoseeSur(passe));

        assertFalse(chiffreTemoin.peutEtrePoseeSur(passe));
        assertTrue(chiffreJaune.peutEtrePoseeSur(passe));
    }

    @Test
    @DisplayName("Peut être posée sur un Joker")
    void testPeutEtrePoseeSur3()
    {
        assertTrue(joker.peutEtrePoseeSur(joker));
        assertTrue(plus4.peutEtrePoseeSur(joker));
        assertTrue(plus2.peutEtrePoseeSur(joker));
        assertTrue(inversion.peutEtrePoseeSur(joker));
        assertTrue(passe.peutEtrePoseeSur(joker));
        assertTrue(chiffreTemoin.peutEtrePoseeSur(joker));
        assertTrue(chiffreJaune.peutEtrePoseeSur(joker));
    }

    @Test
    @DisplayName("Peut être posée sur un Plus4")
    void testPeutEtrePoseeSur4()
    {
        assertTrue(joker.peutEtrePoseeSur(plus4));
        assertTrue(plus4.peutEtrePoseeSur(plus4));
        assertTrue(plus2.peutEtrePoseeSur(plus4));
        assertTrue(inversion.peutEtrePoseeSur(plus4));
        assertTrue(passe.peutEtrePoseeSur(plus4));
        assertTrue(chiffreTemoin.peutEtrePoseeSur(plus4));
        assertTrue(chiffreJaune.peutEtrePoseeSur(plus4));
    }
}
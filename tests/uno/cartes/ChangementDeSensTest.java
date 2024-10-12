package uno.cartes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ChangementDeSensTest {
    Chiffre chiffreMemeCouleur, chiffreCouleurDiff;
    ChangementDeSens carteTemoin, changementCouleurDiff;
    PasseTonTour passeTonTourMemeCouleur, passeTonTourCouleurDiff;
    Plus2 plus2MemeCouleur, plus2CouleurDiff;
    Plus4 plus4;
    Joker joker;

    @BeforeEach
    void setUp() {
        chiffreMemeCouleur = new Chiffre(7, Couleur.BLEU);
        chiffreCouleurDiff = new Chiffre(7, Couleur.JAUNE);

        carteTemoin = new ChangementDeSens(Couleur.BLEU);
        changementCouleurDiff = new ChangementDeSens(Couleur.ROUGE);

        passeTonTourMemeCouleur  = new PasseTonTour(Couleur.BLEU);
        passeTonTourCouleurDiff = new PasseTonTour(Couleur.VERT);

        plus2MemeCouleur = new Plus2(Couleur.BLEU);
        plus2CouleurDiff = new Plus2(Couleur.ROUGE);

        plus4 = new Plus4();
        joker = new Joker();

    }

    @Test
    void peutEtreRecouvertePar() {
        assertTrue(carteTemoin.peutEtreRecouvertePar(chiffreMemeCouleur));
        assertTrue(carteTemoin.peutEtreRecouvertePar(carteTemoin));
        assertTrue(carteTemoin.peutEtreRecouvertePar(passeTonTourMemeCouleur));
        assertTrue(carteTemoin.peutEtreRecouvertePar(plus2MemeCouleur));
        assertTrue(carteTemoin.peutEtreRecouvertePar(plus4));
        assertTrue(carteTemoin.peutEtreRecouvertePar(joker));
        assertTrue(carteTemoin.peutEtreRecouvertePar(changementCouleurDiff));


        assertFalse(carteTemoin.peutEtreRecouvertePar(chiffreCouleurDiff));
        assertFalse(carteTemoin.peutEtreRecouvertePar(plus2CouleurDiff));
        assertFalse(carteTemoin.peutEtreRecouvertePar(passeTonTourCouleurDiff));

    }

    @Test
    @DisplayName("Peut être posée sur un joker")
    void peutEtrePoseeSurJoker() {
        assertTrue(carteTemoin.peutEtrePoseeSur(joker));
    }

    @Test
    @DisplayName("Peut être posée sur un plus 2")
    void peutEtrePoseeSurPlus2() {
        assertTrue(carteTemoin.peutEtrePoseeSur(plus2MemeCouleur));
        assertFalse(carteTemoin.peutEtrePoseeSur(plus2CouleurDiff));
    }

    @Test
    @DisplayName("Peut être posée sur un Changement de Sens")
    void peutEtrePoseeSurChangement() {
        assertTrue(carteTemoin.peutEtrePoseeSur(carteTemoin));
        assertTrue(carteTemoin.peutEtrePoseeSur(changementCouleurDiff));
    }

    @Test
    @DisplayName("Peut être posée sur un Passe ton Tour")
    void peutEtrePoseeSurPasseTour() {
        assertTrue(carteTemoin.peutEtrePoseeSur(passeTonTourMemeCouleur));
        assertFalse(carteTemoin.peutEtrePoseeSur(passeTonTourCouleurDiff));
    }

    @Test
    @DisplayName("Peut être posée sur un chiffre")
    void peutEtrePoseeSurChiffre() {
        assertTrue(carteTemoin.peutEtrePoseeSur(chiffreMemeCouleur));
        assertFalse(carteTemoin.peutEtrePoseeSur(chiffreCouleurDiff));
    }

    @Test
    @DisplayName("Peut être posée sur un plus4")
    void peutEtrePoseeSurPlus4() {
        assertTrue(carteTemoin.peutEtrePoseeSur(plus4));
    }
}
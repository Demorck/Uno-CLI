package uno.cartes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class JokerTest {
    Chiffre chiffreMemeCouleur, chiffreCouleurDiff;
    Joker carteTemoin;
    Plus2 plus2MemeCouleur, plus2CouleurDiff;
    ChangementDeSens changementMemeCouleur, changementCouleurDiff;
    PasseTonTour passeTonTourMemeCouleur, passeTonTourCouleurDiff;
    Plus4 plus4;

    @BeforeEach
    void setUp() {
        chiffreMemeCouleur = new Chiffre(7, Couleur.BLEU);
        chiffreCouleurDiff = new Chiffre(7, Couleur.JAUNE);

        carteTemoin = new Joker();
        passeTonTourCouleurDiff = new PasseTonTour(Couleur.ROUGE);

        plus2MemeCouleur = new Plus2(Couleur.BLEU);
        plus2CouleurDiff = new Plus2(Couleur.VERT);

        changementMemeCouleur = new ChangementDeSens(Couleur.BLEU);
        changementCouleurDiff = new ChangementDeSens(Couleur.ROUGE);

        passeTonTourMemeCouleur = new PasseTonTour(Couleur.BLEU);
        plus4 = new Plus4();

    }

    @Test
    void peutEtreRecouvertePar() {
        assertTrue(carteTemoin.peutEtreRecouvertePar(chiffreMemeCouleur));
        assertTrue(carteTemoin.peutEtreRecouvertePar(carteTemoin));
        assertTrue(carteTemoin.peutEtreRecouvertePar(plus2MemeCouleur));
        assertTrue(carteTemoin.peutEtreRecouvertePar(changementMemeCouleur));
        assertTrue(carteTemoin.peutEtreRecouvertePar(passeTonTourMemeCouleur));
        assertTrue(carteTemoin.peutEtreRecouvertePar(plus4));
        assertTrue(carteTemoin.peutEtreRecouvertePar(passeTonTourCouleurDiff));


        assertTrue(carteTemoin.peutEtreRecouvertePar(chiffreCouleurDiff));
        assertTrue(carteTemoin.peutEtreRecouvertePar(changementCouleurDiff));
        assertTrue(carteTemoin.peutEtreRecouvertePar(plus2CouleurDiff));

    }

    @Test
    @DisplayName("Peut être posée sur un joker")
    void peutEtrePoseeSurJoker() {
        assertTrue(carteTemoin.peutEtrePoseeSur(carteTemoin));
    }

    @Test
    @DisplayName("Peut être posée sur un Plus 2")
    void peutEtrePoseeSurPlus2() {
        assertTrue(carteTemoin.peutEtrePoseeSur(plus2MemeCouleur));
        assertTrue(carteTemoin.peutEtrePoseeSur(plus2CouleurDiff));
    }

    @Test
    @DisplayName("Peut être posée sur un Changement de Sens")
    void peutEtrePoseeSurChangement() {
        assertTrue(carteTemoin.peutEtrePoseeSur(changementCouleurDiff));
        assertTrue(carteTemoin.peutEtrePoseeSur(changementMemeCouleur));
    }

    @Test
    @DisplayName("Peut être posée sur un Passe ton Tour")
    void peutEtrePoseeSurPasseTour() {
        assertTrue(carteTemoin.peutEtrePoseeSur(passeTonTourMemeCouleur));
        assertTrue(carteTemoin.peutEtrePoseeSur(passeTonTourCouleurDiff));
    }

    @Test
    @DisplayName("Peut être posée sur un chiffre")
    void peutEtrePoseeSurChiffre() {
        assertTrue(carteTemoin.peutEtrePoseeSur(chiffreMemeCouleur));
        assertTrue(carteTemoin.peutEtrePoseeSur(chiffreCouleurDiff));
    }

    @Test
    @DisplayName("Peut être posée sur un plus4")
    void peutEtrePoseeSurPlus4() {
        assertTrue(carteTemoin.peutEtrePoseeSur(plus4));
    }
}
package uno.cartes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class Plus4Test {
    Chiffre chiffreMemeCouleur, chiffreCouleurDiff;
    Plus4 carteTemoin;
    Plus2 plus2MemeCouleur, plus2CouleurDiff;
    ChangementDeSens changementMemeCouleur, changementCouleurDiff;
    PasseTonTour passeTonTourMemeCouleur, passeTonTourCouleurDiff;
    Joker joker;

    @BeforeEach
    void setUp() {
        chiffreMemeCouleur = new Chiffre(7, Couleur.BLEU);
        chiffreCouleurDiff = new Chiffre(7, Couleur.JAUNE);

        carteTemoin = new Plus4();
        passeTonTourCouleurDiff = new PasseTonTour(Couleur.ROUGE);

        plus2MemeCouleur = new Plus2(Couleur.BLEU);
        plus2CouleurDiff = new Plus2(Couleur.VERT);

        changementMemeCouleur = new ChangementDeSens(Couleur.BLEU);
        changementCouleurDiff = new ChangementDeSens(Couleur.ROUGE);

        passeTonTourMemeCouleur = new PasseTonTour(Couleur.BLEU);
        joker = new Joker();

    }

    @Test
    void peutEtreRecouvertePar() {
        assertTrue(carteTemoin.peutEtreRecouvertePar(chiffreMemeCouleur));
        assertTrue(carteTemoin.peutEtreRecouvertePar(carteTemoin));
        assertTrue(carteTemoin.peutEtreRecouvertePar(plus2MemeCouleur));
        assertTrue(carteTemoin.peutEtreRecouvertePar(changementMemeCouleur));
        assertTrue(carteTemoin.peutEtreRecouvertePar(passeTonTourMemeCouleur));
        assertTrue(carteTemoin.peutEtreRecouvertePar(joker));
        assertTrue(carteTemoin.peutEtreRecouvertePar(passeTonTourCouleurDiff));


        assertTrue(carteTemoin.peutEtreRecouvertePar(chiffreCouleurDiff));
        assertTrue(carteTemoin.peutEtreRecouvertePar(changementCouleurDiff));
        assertTrue(carteTemoin.peutEtreRecouvertePar(plus2CouleurDiff));

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
        assertTrue(carteTemoin.peutEtrePoseeSur(plus2CouleurDiff));
    }

    @Test
    @DisplayName("Peut être posée sur un Changement de Sens")
    void peutEtrePoseeSurChangement() {
        assertTrue(carteTemoin.peutEtrePoseeSur(changementMemeCouleur));
        assertTrue(carteTemoin.peutEtrePoseeSur(changementCouleurDiff));
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
        assertTrue(carteTemoin.peutEtrePoseeSur(carteTemoin));
    }
}
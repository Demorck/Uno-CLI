package uno.cartes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uno.ErreurFichier;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

//@TestMethodOrder(OrderAnnotation.class)
class PaquetDeCartesTest {

    PaquetDeCartes pdc;
    PaquetDeCartes pdc2;
    @BeforeEach
    void setUp() {
        pdc = new PaquetDeCartes();
        Chiffre c1 = new Chiffre(6, Couleur.JAUNE);
        Chiffre c2 = new Chiffre(8, Couleur.ROUGE);
        Chiffre c3 = new Chiffre(3, Couleur.BLEU);
        Chiffre c4 = new Chiffre(1, Couleur.VERT);
        //Chiffre c4 = new Chiffre(1, Couleur.VERT);

        pdc.ajouter(c1, c2, c3, c4);
        pdc2 = new PaquetDeCartes();
        pdc2.ajouter(pdc);
    }

    @Test
    @DisplayName("Ajoute 4 cartes")
    void ajouterCartes() {
        Chiffre c1 = new Chiffre(9, Couleur.JAUNE);
        Chiffre c2 = new Chiffre(6, Couleur.ROUGE);
        Chiffre c3 = new Chiffre(1, Couleur.BLEU);

        pdc.ajouter(c1, c2, c3);
        assertEquals(pdc.getNombreDeCartes(), 7);
    }

    @Test
    @DisplayName("Ajoute un paquet de carte")
    void testAjouter() {
        pdc2 = new PaquetDeCartes();
        pdc2.ajouter(pdc);
        assertEquals(pdc.getSommet().getValeur(), 1);
    }

    @Test
    @DisplayName("Vérification de deux paquets égaux")
    void isEqual() {
        assertTrue(pdc.isEqual(pdc2));
    }

    @Test
    @DisplayName("Vérifie le nombre de carte dans un paquet")
    void getNombreDeCartes() {
        assertEquals(pdc.getNombreDeCartes(), 4);
    }

    @Test
    @DisplayName("Vérifie un paquet vide et non vide")
    void estVide() {
        assertFalse(pdc.estVide());
        PaquetDeCartes tmp = new PaquetDeCartes();
        assertTrue(tmp.estVide());
    }

    @Test
    @DisplayName("Mélange un paquet")
    void melanger() {
        pdc2.melanger();
        assertFalse(pdc.isEqual(pdc2));
    }

    @Test
    @DisplayName("Retourne le paquet")
    void retourner() {
        pdc.retourner();
        assertEquals(pdc.getSommet().getValeur(), 6);
    }

    @Test
    @DisplayName("Essaie d'enlever une carte inexistante et existante")
    void enlever() {
        Chiffre c = new Chiffre(6, Couleur.BLEU);
        pdc.enlever(c);
        assertEquals(pdc.getNombreDeCartes(), 4);

        c.setCouleur(Couleur.JAUNE);
        pdc.enlever(c);
        assertEquals(pdc.getNombreDeCartes(), 3);

    }

    @Test
    @DisplayName("Pioche une carte (et vérifie si elle l'a enlevée)")
    void piocher() {
        Carte c = pdc.piocher();
        assertEquals(c.getValeur(), 1);
        assertEquals(pdc.getNombreDeCartes(), 3);
    }

    @Test
    @DisplayName("Vérifie la somme de toutes les cartes")
    void getValeur(){
        pdc.ajouter(new Plus2(Couleur.JAUNE));
        int s = pdc.getValeur();

        assertEquals(s, 38);
    }

    @Test
    @DisplayName("Vérifie si l'écriture du fichier fonctionne")
    void ecrire() throws ErreurFichier {
        Joker joker = new Joker();
        Plus4 p4 = new Plus4();
        Plus2 p2 = new Plus2(Couleur.JAUNE);
        pdc.ajouter(joker, p4, p2);
        pdc.ecrire("pdc.txt");
    }

    @Test
    @DisplayName("Vérifie si la lecture du fichier fonctionne")
    void lire() throws ErreurFichier {
        pdc.lire("pdc.txt");
        System.out.println(pdc.toString());
    }

    @Test
    @DisplayName("Vérifie que la classe soit bien iterable")
    void testIterable()
    {
        Chiffre c1 = new Chiffre(6, Couleur.JAUNE);
        Chiffre c2 = new Chiffre(8, Couleur.ROUGE);
        Chiffre c3 = new Chiffre(3, Couleur.BLEU);
        Chiffre c4 = new Chiffre(1, Couleur.VERT);
        Iterator<Carte> iterator = pdc.iterator();

        assertTrue(iterator.hasNext());
        assertEquals(c1.toString(), iterator.next().toString());


        assertTrue(iterator.hasNext());
        assertEquals(c2.toString(), iterator.next().toString());


        assertTrue(iterator.hasNext());
        assertEquals(c3.toString(), iterator.next().toString());


        assertTrue(iterator.hasNext());
        assertEquals(c4.toString(), iterator.next().toString());


        assertFalse(iterator.hasNext());
    }
}
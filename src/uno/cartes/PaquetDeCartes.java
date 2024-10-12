package uno.cartes;

import uno.ErreurFichier;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * La class Paquet de Cartes est un deck contenant une Arraylist de Carte
 * La class est {@code Iterable}
 * @see Carte
 */
public class PaquetDeCartes implements Iterable<Carte>
{
    private final ArrayList<Carte> deck;

    public PaquetDeCartes()
    {
        this.deck = new ArrayList<>();
    }

    /**
     * Ajoute toutes les cartes en paramètres au paquet de cartes
     * @param cartes Les cartes à ajouter
     */
    public void ajouter(Carte... cartes)
    {
        this.deck.addAll(List.of(cartes));
    }

    /**
     * Ajoute un paquet de carte à deck
     * @param pdc paquet de carte à ajouter
     */
    public void ajouter(PaquetDeCartes pdc)
    {
        this.deck.addAll(pdc.getDeck());
    }

    /**
     * Teste en profondeur si le paquet de carte est égal à un autre.
     * Les deux paquets doivent être mélangés de la même façon
     * @param pdc L'autre paquet de cartes à tester
     * @return true si les deux paquets sont égaux ; false sinon
     */
    public boolean isEqual(PaquetDeCartes pdc)
    {
        if (this.deck.size() == pdc.getDeck().size())
        {
            for (int i = 0; i < this.deck.size(); i++)
                if (this.deck.get(i).getCouleur() != pdc.getDeck().get(i).getCouleur() || this.deck.get(i).getValeur() != pdc.getDeck().get(i).getValeur())
                    return false;
        }
        else
            return false;
        return true;
    }

    /**
     * @return le paquet de carte actuel
     */
    public ArrayList<Carte> getDeck()
    {
        return this.deck;
    }

    /**
     * @return Le nombre de cartes du paquet
     */
    public int getNombreDeCartes()
    {
        return this.deck.size();
    }

    /**
     * @return vrai si le paquet de cartes est vide ; faux sinon.
     */
    public boolean estVide()
    {
        return this.deck.isEmpty();
    }

    /**
     * Mélange le paquet
     */
    public void melanger()
    {
        Collections.shuffle(this.deck);
    }

    /**
     * Retourne le paquet
     */
    public void retourner()
    {
        Collections.reverse(this.deck);
    }

    /**
     * Enlève la carte en paramètre dans le paquet
     * @param carte La carte à enlever du paquet
     */
    public void enlever(Carte carte)
    {
        for (Carte c : deck)
        {
            if (c.getValeur() == carte.getValeur() && c.getCouleur() == carte.getCouleur())
            {
                this.deck.remove(c);
                break; // Sinon, on enlève toutes les occurrences de carte, ça serait bête.
            }
        }
    }

    /**
     * Voir {@link Carte} pour comprendre comment fonctionne les valeurs
     * @see Carte
     * @return la somme de toutes les valeurs des cartes
     */
    public int getValeur()
    {
        int s = 0;
        for (Carte carte : this.deck)
            s += carte.getValeur();

        return s;
    }

    /**
     * Retourne le sommet (sans l'enlever) ; permet de consulter la première carte
     * @return Retourne le sommet (sans l'enlever) ; renvoie {@code null} si le paquet est vide
     */
    public Carte getSommet()
    {
        return this.estVide() ? null : this.deck.get(this.deck.size() - 1); // Si c'est vide, on return null
    }

    /**
     * Retourne le sommet en l'enlevant ; permet de piocher la première carte
     * @return Retourne le sommet (en l'enlevant) ; renvoie {@code null} si le paquet est vide
     */
    public Carte piocher()
    {
        if (this.estVide()) return null;
        Carte tmpCarte = this.getSommet();
        this.deck.remove(this.deck.size() - 1);
        return tmpCarte;
    }

    /**
     * @param i l'index de la carte à retourner
     * @return La carte à l'index i
     */
    public Carte getCarte(int i)
    {
        if (i < 0 || i >= deck.size())
            return null;
        return this.deck.get(i);
    }

    /**
     * Écrit dans un fichier tout le paquet de carte pour sauvegarder le paquet.
     * <p>Chaque ligne est une carte écrit comme ce qui suit:
     * <p>[TYPE DE CARTE][VALEUR] [COULEUR]
     * @param nomDeFichier Le nom du fichier pour enregistrer le paquet de cartes
     * @throws ErreurFichier si le fichier est inexistant
     */
    public void ecrire(String nomDeFichier) throws ErreurFichier
    {
        PrintWriter filtre;
        FileWriter file;
        try
        {
            file = new FileWriter(nomDeFichier);
            filtre = new PrintWriter(new BufferedWriter(file));

            for (Carte c : deck)
            {
                if (c.estSansCouleur())
                    filtre.println(c.getClass().getSimpleName() + c.getValeur() + " Noir");
                else
                    filtre.println(c.getClass().getSimpleName() + c.getValeur() + " " + c.getCouleur().getNom());
            }
            filtre.close();
        }
        catch (IOException e)
        {
            throw new ErreurFichier(e.getMessage());
        }
    }

    /**
     * Lis un fichier écrit avec {@link #ecrire(String) ecrire} afin de créer un Paquet de Cartes
     * @param nomDeFichier Le nom du fichier à lire pour enregistrer le deck
     * @throws ErreurFichier Si le fichier est introuvable
     */
    public void lire(String nomDeFichier) throws ErreurFichier
    {
        BufferedReader buffer;
        FileReader file;

        try
        {
            file = new FileReader(nomDeFichier);
            buffer = new BufferedReader(file);
            String line = buffer.readLine();

            String regex = "(Plus2|Plus4|Chiffre|ChangementDeSens|PasseTonTour|Joker)(\\d+)\\s(\\w+)";
            Pattern pattern = Pattern.compile(regex);

            while (line != null)
            {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    String classe = matcher.group(1);
                    String valeur = matcher.group(2);
                    String couleur = matcher.group(3);

                    int value = Integer.parseInt(valeur);
                    Couleur coul = Couleur.getCouleurParNom(couleur);

                    switch (classe)
                    {
                        case "Chiffre":
                            this.ajouter(new Chiffre(value, coul));
                            break;
                        case "Plus2":
                            this.ajouter(new Plus2(coul));
                            break;
                        case "ChangementDeSens":
                            this.ajouter(new ChangementDeSens(coul));
                            break;
                        case "PasseTonTour":
                            this.ajouter(new PasseTonTour(coul));
                            break;
                        case "Plus4":
                            this.ajouter(new Plus4());
                            break;
                        case "Joker":
                            this.ajouter(new Joker());
                            break;
                    }
                }
                line = buffer.readLine();
            }
        }
        catch (IOException e)
        {
            throw  new ErreurFichier(e.getMessage());
        }
    }

    public String toString()
    {
        StringBuilder s = new StringBuilder("PaquetDeCarte={");
        for (Carte carte : this.deck)
        {
            s.append(carte.toString());
        }
        s.append("}");
        return s.toString();
    }

    @Override
    public Iterator<Carte> iterator() {
        return new PaquetDeCartesIterator();
    }


    private class PaquetDeCartesIterator implements Iterator<Carte>
    {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < deck.size();
        }

        @Override
        public Carte next() {
            return deck.get(currentIndex++);
        }
    }
}

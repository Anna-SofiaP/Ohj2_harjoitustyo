/**
 * 
 */
package unipaivakirja;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Omistaja
 * @version 2.3.2022
 *
 */
public class Kayttajat implements Iterable<Kayttaja>{
    private static final int MAX_KAYTTAJIA = 5;
    private int lkm = 0;
    private String tiedostonPerusNimi = "kayttajat";
    private Kayttaja[] alkiot = new Kayttaja[MAX_KAYTTAJIA];
    private String kokoNimi = "";
    private boolean muutettu = false;
    
    
    /**
     * Oletusmuodostaja
     */
    public Kayttajat() {
        //Attribuuttien oma alustus riittää
    }
    
    
    /**
     * Lisää uuden käyttäjän tietorakenteeseen.
     * @param kayttaja lisättävän käyttäjän viite
     * @example
     * <pre name="test">
     * Kayttajat kayttajat = new Kayttajat();
     * Kayttaja nea = new Kayttaja("Nea"), ansku = new Kayttaja("Ansku");
     * kayttajat.getLkm() === 0;
     * kayttajat.lisaa(nea); kayttajat.getLkm() === 1;
     * kayttajat.lisaa(ansku); kayttajat.getLkm() === 2;
     * kayttajat.lisaa(nea); kayttajat.getLkm() === 3;
     * kayttajat.anna(0) === nea;
     * kayttajat.anna(1) === ansku;
     * kayttajat.anna(2) === nea;
     * kayttajat.anna(1) == nea === false;
     * kayttajat.anna(1) == ansku === true;
     * kayttajat.anna(3) === nea; #THROWS IndexOutOfBoundsException
     * kayttajat.lisaa(nea); kayttajat.getLkm() === 4;
     * kayttajat.lisaa(nea); kayttajat.getLkm() === 5;
     * kayttajat.lisaa(nea); kayttajat.getLkm() === 6;
     * </pre>
     */
    public void lisaa(Kayttaja kayttaja) {
        if (lkm >= alkiot.length) 
            kasvataTaulukkoa();
        alkiot[lkm] = kayttaja;
        lkm++;
    }
    
    
    /**
     * Kasvatetaan merkinnät-taulukon kokoa
     * @example
     * <pre name="test">
     *  Kayttajat kayttajat = new Kayttajat();
     *  Kayttaja nea = new Kayttaja("Nea"), ansku = new Kayttaja("Ansku"), liisa = new Kayttaja("Liisa");
     *  kayttajat.lisaa(nea); kayttajat.getLkm() === 1;
     *  kayttajat.lisaa(ansku); kayttajat.getLkm() === 2;
     *  kayttajat.lisaa(liisa); kayttajat.getLkm() === 3;
     *  kayttajat.lisaa(nea); kayttajat.getLkm() === 4;
     *  kayttajat.lisaa(ansku); kayttajat.getLkm() === 5; 
     * </pre>
     */
    public void kasvataTaulukkoa() {
        int uusiKoko = alkiot.length + 10;
        Kayttaja alkiot2[] = new Kayttaja[uusiKoko];
        for (int i = 0; i < alkiot.length; i++) {
            alkiot2[i] = alkiot[i];
        }
        alkiot = alkiot2;
    }
    
    
    public List<Kayttaja> annaKayttajat() {
        List<Kayttaja> kayttajat = new ArrayList<>();
        for (int i = 0; i < getLkm(); i++) {
            kayttajat.add(alkiot[i]);
        }
        return kayttajat;
    }
    
    
    /**
     * Palauttaa viitteen i:nteen käyttäjään
     * @param i monennenko käyttäjän viite halutaan
     * @return viite käyttäjään, jonka indeksi listassa alkiot on i
     * @throws IndexOutOfBoundsException jos indeksi i ei ole sallittu 
     */
    public Kayttaja anna(int i) throws IndexOutOfBoundsException {
        if (i < 0 || lkm <= i )
            throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        return alkiot[i];
    }
    
    
    /**
     * Lukee käyttäjän tiedostosta
     * @param tiedosto tiedoston perusnimi
     * @throws SailoException jos lukeminen epäonnistuu
     */
    public void lueTiedostosta(String tiedosto) throws SailoException {
        setTiedostonPerusNimi(tiedosto);
        try ( BufferedReader fi = new BufferedReader(new FileReader(getTiedostonNimi())) ) {
            kokoNimi = fi.readLine();
            if ( kokoNimi == null ) throw new SailoException("Käyttäjän nimi puuttuu");
            String rivi = fi.readLine();
            if ( rivi == null ) throw new SailoException("Maksimikoko puuttuu");
            // int maxKoko = Mjonot.erotaInt(rivi,10); // tehdään jotakin

            while ( (rivi = fi.readLine()) != null ) {
                rivi = rivi.trim();
                if ( "".equals(rivi) || rivi.charAt(0) == ';' ) continue;
                Kayttaja kayttaja = new Kayttaja();
                kayttaja.parse(rivi); // voisi olla virhekäsittely
                lisaa(kayttaja);
            }
            muutettu = false;
        } catch ( FileNotFoundException e ) {
            throw new SailoException("Tiedosto " + getTiedostonNimi() + " ei aukea");
        } catch ( IOException e ) {
            throw new SailoException("Ongelmia tiedoston kanssa: " + e.getMessage());
        }
    }
    
    
   /**
    * Luetaan aikaisemmin annetun nimisestä tiedostosta
    * @throws SailoException jos tulee poikkeus
    */
   public void lueTiedostosta() throws SailoException {
       lueTiedostosta(getTiedostonPerusNimi());
   }

    
    
    /**
     * Asettaa tiedoston perusnimen ilman tarkenninta
     * @param tiedosto tallennustiedoston perusnimi
     */
    public void setTiedostonPerusNimi(String tiedosto) {
        tiedostonPerusNimi = tiedosto;
    }


    /**
     * Palauttaa tiedoston nimen, jota käytetään tallennukseen
     * @return tallennustiedoston nimi
     */
    public String getTiedostonPerusNimi() {
        return tiedostonPerusNimi;
    }

    
    /**
     * Palauttaa tiedoston nimen, jota käytetään tallennukseen
     * @return tallennustiedoston nimi
     */
    public String getTiedostonNimi() {
        return getTiedostonPerusNimi() + ".dat";
    }
    
    
    /**
     * Palauttaa unipäiväkirjan käyttäjien lukumäärän
     * @return käyttäjien lukumäärä
     */
    public int getLkm() {
        return lkm;
    }

    

    /**
     * Testiohjelma
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Kayttajat kayttajat = new Kayttajat();
        
        Kayttaja nea = new Kayttaja("nea"), ansku = new Kayttaja("ansku");
        nea.rekisteroi();
        nea.taytaNeaTiedoilla();
        ansku.rekisteroi();
        ansku.taytaAnskuTiedoilla();
        
        kayttajat.lisaa(nea);
        kayttajat.lisaa(ansku);
        
        System.out.println("Käyttäjät testi");
        
        for (int i = 0; i < kayttajat.getLkm(); i++) {
            Kayttaja kayttaja = kayttajat.anna(i);
            System.out.println("Käyttäjä nro: " + i);
            kayttaja.tulosta(System.out);
        }
    
    }


    public Kayttaja aseta(String selectedText) {
        Kayttaja uusi = new Kayttaja(selectedText);
        return uusi;
    }


    /**
     * Tallentaa käyttäjän unitiedot tiedostoon
     * @throws SailoException jos talletus ei onnistu
     */
    public void talleta() throws SailoException {
        if ( !muutettu ) return;

        File fbak = new File(getBakNimi());
        File ftiedosto = new File(getTiedostonNimi());
        fbak.delete(); // if .. System.err.println("Ei voi tuhota");
        ftiedosto.renameTo(fbak); // if .. System.err.println("Ei voi nimetä");

        try ( PrintWriter fo = new PrintWriter(new FileWriter(ftiedosto.getCanonicalPath())) ) {
            fo.println(getKokoNimi());
            fo.println(alkiot.length);
            for (Kayttaja kayttaja : this) {
                fo.println(kayttaja.toString());
            }
            //} catch ( IOException e ) { // ei heitä poikkeusta
            //  throw new SailoException("Tallettamisessa ongelmia: " + e.getMessage());
        } catch ( FileNotFoundException ex ) {
            throw new SailoException("Tiedosto " + ftiedosto.getName() + " ei aukea");
        } catch ( IOException ex ) {
            throw new SailoException("Tiedoston " + ftiedosto.getName() + " kirjoittamisessa ongelmia");
        }

        muutettu = false;
    }
    
    
    /**
     * Palauttaa varakopiotiedoston nimen
     * @return varakopiotiedoston nimi
     */
    public String getBakNimi() {
        return tiedostonPerusNimi + ".bak";
    }

    
    /**
     * Palauttaa käyttäjän unipäiväkirjan koko nimen
     * @return käyttäjän unipäiväkirjan koko nimi merkkijononna
     */
    public String getKokoNimi() {
        return kokoNimi;
    }
    
    
    /**
     * @author Omistaja
     * @version 15.4.2022
     *
     */
    public class KayttajatIterator implements Iterator<Kayttaja> {
        private int kohdalla = 0;

        
        /**
         * Onko olemassa vielä seuraavaa käyttäjää
         * @see java.util.Iterator#hasNext()
         * @return true jos on vielä käyttäjiä
         */
        @Override
        public boolean hasNext() {
            return kohdalla < getLkm();
        }


        /**
         * Annetaan seuraava käyttäjä
         * @return seuraava käyttäjä
         * @throws NoSuchElementException jos seuraavaa alkiota ei enää ole
         * @see java.util.Iterator#next()
         */
        @Override
        public Kayttaja next() throws NoSuchElementException {
            if ( !hasNext() ) throw new NoSuchElementException("Ei ole");
            return anna(kohdalla++);
        }
        
    }
              
        @Override
        public Iterator<Kayttaja> iterator() {
            return new KayttajatIterator();
        }

}


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
        muutettu = true;
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
    
    
    /**
     * Luodaan kayttajat-lista ja lisätään sinne kaikki unipäiväkirjan käyttäjät
     * @return listan käyttäjistä
     */
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
     * 
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #import java.io.File;
     * #import java.util.Iterator;
     * 
     *  Kayttajat kayttajat = new Kayttajat();
     *  Kayttaja nea = new Kayttaja(), ansku = new Kayttaja();
     *  nea.taytaNeaTiedoilla();
     *  ansku.taytaAnskuTiedoilla();
     *  String hakemisto = "testiKayttajat";
     *  String tiedNimi = hakemisto+"/tkayttajat";
     *  File ftied = new File(tiedNimi+".dat");
     *  File dir = new File(hakemisto);
     *  dir.mkdir();
     *  ftied.delete();
     *  kayttajat.lueTiedostosta(tiedNimi); #THROWS SailoException
     *  kayttajat.lisaa(nea);
     *  kayttajat.lisaa(ansku);
     *  kayttajat.talleta();
     *  kayttajat = new Kayttajat();            // Poistetaan vanhat luomalla uusi
     *  kayttajat.lueTiedostosta(tiedNimi);     // johon ladataan tiedot tiedostosta.
     *  Iterator<Kayttaja> i = kayttajat.iterator();
     *  i.next().toString() === nea.toString();
     *  i.next().toString() === ansku.toString();
     *  i.hasNext() === false;
     *  kayttajat.lisaa(nea);
     *  kayttajat.talleta();
     *  ftied.delete() === true;
     *  File fbak = new File(tiedNimi+".bak");
     *  fbak.delete() === true;
     *  dir.delete() === true;
     * </pre>
     */
    public void lueTiedostosta(String tiedosto) throws SailoException {
        setTiedostonPerusNimi(tiedosto);
        try ( BufferedReader fi = new BufferedReader(new FileReader(getTiedostonNimi())) ) {
            kokoNimi = fi.readLine();
            if ( kokoNimi == null ) throw new SailoException("Käyttäjän nimi puuttuu");
            String rivi = fi.readLine();
            if ( rivi == null ) throw new SailoException("Maksimikoko puuttuu");

            while ( (rivi = fi.readLine()) != null ) {
                rivi = rivi.trim();
                if ( "".equals(rivi) || rivi.charAt(0) == ';' ) continue;
                Kayttaja kayttaja = new Kayttaja();
                kayttaja.parse(rivi);
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
     * Asettaan käyttäjän tiedot sen perusteella, mikä käyttäjä on valittu käyttäjävalikosta.
     * @param selectedText käyttäjä, joka on valittu käyttäjävalikosta
     * @return se käyttäjä, joka valittiin käyttäjävalikosta
     */
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
     * luokka käyttäjien iteroimiseksi
     * @author Omistaja
     * @version 15.4.2022
     * 
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #PACKAGEIMPORT
     * #import java.util.*;
     * 
     * Kayttajat kayttajat = new Kayttajat();
     * Kayttaja nea = new Kayttaja(), ansku = new Kayttaja();
     * nea.rekisteroi(); ansku.rekisteroi();
     *
     * kayttajat.lisaa(nea); 
     * kayttajat.lisaa(ansku); 
     * kayttajat.lisaa(nea); 
     * 
     * StringBuffer ids = new StringBuffer(30);
     * for (Kayttaja kayttaja:kayttajat)   // Kokeillaan for-silmukan toimintaa
     *   ids.append(" "+kayttaja.getKayttajaId());           
     * 
     * String tulos = " " + nea.getKayttajaId() + " " + ansku.getKayttajaId() + " " + nea.getKayttajaId();
     * 
     * ids.toString() === tulos; 
     * 
     * ids = new StringBuffer(30);
     * for (Iterator<Kayttaja>  i=kayttajat.iterator(); i.hasNext(); ) { // ja iteraattorin toimintaa
     *   Kayttaja kayttaja = i.next();
     *   ids.append(" "+kayttaja.getKayttajaId());           
     * }
     * 
     * ids.toString() === tulos;
     * 
     * Iterator<Kayttaja>  i=kayttajat.iterator();
     * i.next() == nea  === true;
     * i.next() == ansku  === true;
     * i.next() == nea  === true;
     * 
     * i.next();  #THROWS NoSuchElementException
     *  
     * </pre> 
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


        /** 
         * Poistaa käyttäjän jolla on valittu tunnusnumero  
         * @param id poistettavan käyttäjän tunnusnumero 
         * @return 1 jos poistettiin, 0 jos ei löydy 
         * @example 
         * <pre name="test"> 
         * #THROWS SailoException  
         * Kayttajat kayttajat = new Kayttajat(); 
         * Kayttaja nea = new Kayttaja(), ansku = new Kayttaja(), liisa = new Kayttaja(); 
         * nea.rekisteroi(); ansku.rekisteroi(); liisa.rekisteroi(); 
         * int id1 = nea.getKayttajaId(); 
         * kayttajat.lisaa(nea); kayttajat.lisaa(ansku); kayttajat.lisaa(liisa); 
         * kayttajat.poista(id1+1) === 1; kayttajat.getLkm() === 2; 
         * kayttajat.poista(id1) === 1; kayttajat.getLkm() === 1; 
         * kayttajat.poista(id1+3) === 0; kayttajat.getLkm() === 1; 
         * </pre> 
         *  
         */ 
        public int poista(int id) { 
            int ind = etsiId(id); 
            if (ind < 0) return 0; 
            lkm--; 
            for (int i = ind; i < lkm; i++) 
                alkiot[i] = alkiot[i + 1]; 
            alkiot[lkm] = null; 
            muutettu = true; 
            return 1; 
        } 


        /** 
         * Etsii käyttäjän id:n perusteella 
         * @param id tunnusnumero, jonka mukaan etsitään 
         * @return löytyneen käyttäjän indeksi tai -1 jos ei löydy 
         * <pre name="test"> 
         * #THROWS SailoException  
         * Kayttajat kayttajat = new Kayttajat(); 
         * Kayttaja nea = new Kayttaja(), ansku = new Kayttaja(), liisa = new Kayttaja(); 
         * nea.rekisteroi(); ansku.rekisteroi(); liisa.rekisteroi(); 
         * int id1 = nea.getKayttajaId(); 
         * kayttajat.lisaa(nea); kayttajat.lisaa(ansku); kayttajat.lisaa(liisa); 
         * kayttajat.etsiId(id1+1) === 1; 
         * kayttajat.etsiId(id1+2) === 2; 
         * </pre> 
         */ 
        public int etsiId(int id) { 
            for (int i = 0; i < lkm; i++) 
                if (id == alkiot[i].getKayttajaId()) return i; 
            return -1; 
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

}


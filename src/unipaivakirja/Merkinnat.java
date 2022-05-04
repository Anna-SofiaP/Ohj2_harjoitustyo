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
 * Luokka käyttäjän unipäiväkirjan merkinnöille. Osaa lisätä uuden merkinnän.
 * @author Omistaja
 * @version 8.3.2022
 *
 */
public class Merkinnat implements Iterable<Merkinta>{
    private static final int MAX_MERKINTOJA = 5;
    private int lkm = 0;
    private Merkinta alkiot[] = new Merkinta[MAX_MERKINTOJA];
    private String kokoNimi = "";
    
    private boolean muutettu = false;
    private String tiedostonPerusNimi = "";
    
    
    /**
     * Oletusmuodostaja
     */
    public Merkinnat() {
        //Alustettu attribuuteissa
    }
    
    
    /**
     * Lisää uuden merkinnän tietorakenteeseen.  Ottaa merkinnän omistukseensa.
     * @param merkinta lisättävän merkinnän viite.  Huom tietorakenne muuttuu omistajaksi
     * @example
     * <pre name="test">
     * Merkinnat merkinnat = new Merkinnat();
     * Merkinta pvm1 = new Merkinta(), pvm2 = new Merkinta();
     * merkinnat.getLkm() === 0;
     * merkinnat.lisaa(pvm1); merkinnat.getLkm() === 1;
     * merkinnat.lisaa(pvm2); merkinnat.getLkm() === 2;
     * merkinnat.lisaa(pvm1); merkinnat.getLkm() === 3;
     * merkinnat.anna(0) === pvm1;
     * merkinnat.anna(1) === pvm2;
     * merkinnat.anna(2) === pvm1;
     * merkinnat.anna(1) == pvm1 === false;
     * merkinnat.anna(1) == pvm2 === true;
     * merkinnat.anna(3) === pvm1; #THROWS IndexOutOfBoundsException 
     * merkinnat.lisaa(pvm1); merkinnat.getLkm() === 4;
     * merkinnat.lisaa(pvm1); merkinnat.getLkm() === 5;
     * merkinnat.lisaa(pvm1); merkinnat.getLkm() === 6;
     * </pre>
     */
    public void lisaa(Merkinta merkinta) {
        if (lkm >= alkiot.length) kasvataTaulukkoa();
            //throw new SailoException("Liikaa alkioita");
        alkiot[lkm] = merkinta;
        lkm++;
        muutettu = true;
    }
    
    
    /*public void korvaaTaiLisaa(Merkinta merkinta) throws SailoException {
        int id = merkinta.getMerkintaid();
        for (int i = 0; i < lkm; i++) {
            if ( alkiot[i].getMerkintaid() == id ) {
                alkiot[i] = merkinta;
                muutettu = true;
                return;
            }
        }
        lisaa(merkinta);
    }*/

    
    
    /**
     * Kasvatetaan merkinnät-taulukon kokoa
     * @example
     * <pre name="test">
     *  Merkinnat merkinnat = new Merkinnat();
     *  Merkinta pvm1 = new Merkinta(), pvm2 = new Merkinta(), pvm3 = new Merkinta();
     *  merkinnat.lisaa(pvm1); merkinnat.getLkm() === 1;
     *  merkinnat.lisaa(pvm2); merkinnat.getLkm() === 2;
     *  merkinnat.lisaa(pvm3); merkinnat.getLkm() === 3;
     *  merkinnat.lisaa(pvm1); merkinnat.getLkm() === 4;
     *  merkinnat.lisaa(pvm3); merkinnat.getLkm() === 5; 
     * </pre>
     */
    public void kasvataTaulukkoa() {
        int uusiKoko = alkiot.length + 10;
        Merkinta alkiot2[] = new Merkinta[uusiKoko];
        for (int i = 0; i < alkiot.length; i++) {
            alkiot2[i] = alkiot[i];
        }
        alkiot = alkiot2;
    }
    
    
    /**
     * Palauttaa viitteen i:teen merkintään.
     * @param i monennenko merkinnän viite halutaan
     * @return viite merkintään, jonka indeksi on i
     * @throws IndexOutOfBoundsException jos i ei ole sallitulla alueella  
     */
    public Merkinta anna(int i) throws IndexOutOfBoundsException {
        if (i < 0 || lkm <= i)
            throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        return alkiot[i];
    }
    
    
    /**
     * Lukee merkinnät tiedostosta käyttäjäId:n perusteella. 
     * @param tiedosto tiedoston nimi
     * @throws SailoException jos lukeminen epäonnistuu
     * <pre name="test">
     * #THROWS SailoException 
     * #import java.io.File;
     * #import java.util.Iterator;
     * 
     *  Merkinnat merkinnat = new Merkinnat();
     *  Merkinta merk1 = new Merkinta(), merk2 = new Merkinta();
     *  merk1.taytaM1Tiedoilla();
     *  merk2.taytaM2Tiedoilla();
     *  String hakemisto = "testiKayttajat";
     *  String tiedNimi = hakemisto+"/tmerkinnat";
     *  File ftied = new File(tiedNimi+".dat");
     *  File dir = new File(hakemisto);
     *  dir.mkdir();
     *  ftied.delete();
     *  merkinnat.lueTiedostosta(tiedNimi); #THROWS SailoException
     *  merkinnat.lisaa(merk1);
     *  merkinnat.lisaa(merk2);
     *  merkinnat.talleta();
     *  merkinnat = new Merkinnat();            // Poistetaan vanhat luomalla uusi
     *  merkinnat.lueTiedostosta(tiedNimi);     // johon ladataan tiedot tiedostosta.
     *  Iterator<Merkinta> i = merkinnat.iterator();
     *  i.next().toString() === merk1.toString();
     *  i.next().toString() === merk2.toString();
     *  i.hasNext() === false;
     *  merkinnat.lisaa(merk1);
     *  merkinnat.talleta();
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
                Merkinta merkinta = new Merkinta();
                merkinta.parse(rivi); // voisi olla virhekäsittely
                lisaa(merkinta);
            }
            muutettu = false;

        } catch ( FileNotFoundException e ) {
            throw new SailoException("Tiedosto " + getTiedostonNimi() + " ei aukea");
        } catch ( IOException e ) {
            throw new SailoException("Ongelmia tiedoston kanssa: " + e.getMessage());
        }

    }
    
    
    /** Luetaan aikaisemmin annetun nimisestä tiedostosta
    * @throws SailoException jos tulee poikkeus
    */
   public void lueTiedostosta() throws SailoException {
       lueTiedostosta(getTiedostonPerusNimi());
   }
   
   
   /**
    * Asettaa tiedoston perusnimen ilan tarkenninta
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
       return tiedostonPerusNimi + ".dat";
   }

    
    
    /**
     * Tallentaa merkinnät tiedostoon.  Kesken.
     * @throws SailoException jos talletus epäonnistuu
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
            for (Merkinta merkinta : this) {
                fo.println(merkinta.toString());
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
     * Palauttaa merkintöjen koko nimen
     * @return merkintöjen koko nimi merkkijononna
     */
    public String getKokoNimi() {
        return kokoNimi;
    }



    /**
     * Palauttaa käyttäjän unipäiväkirjamerkintöjen lukumäärän
     * @return merkintöjen lukumäärä
     */
    public int getLkm() {
        return lkm;
    }


    /**
     * @param kayttajaId sen käyttäjän id, jonka merkinnät haetaan alkiot-taulukosta
     * @return tiettyyn käyttäjään kytketyt merkinnät listana
     */
    public List<Merkinta> annaKayttajanMerkinnat(int kayttajaId) {
        List<Merkinta> kayttajanMerkinnat = new ArrayList<Merkinta>();
        //Alustetaan apulista johon tallennetaan kaikki löytyneet merkinnät
        //Forissa käydään kaikki merkinnät läpi ja lisätään listaan kaikki täsmäävät id:t
        for (int i = 0; i < this.getLkm(); i++) {
            if (alkiot[i].getKayttajaId() == kayttajaId)
                kayttajanMerkinnat.add(alkiot[i]);
        }
        //palautetaan lista
        return kayttajanMerkinnat;
    }
    
    
    /**
     * @author Omistaja
     * @version 15.4.2022
     * 
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #PACKAGEIMPORT
     * #import java.util.*;
     * 
     * Merkinnat merkinnat = new Merkinnat();
     * Merkinta merk1 = new Merkinta(), merk2 = new Merkinta();
     * merk1.merkinnanLisays(); merk2.merkinnanLisays();
     *
     * merkinnat.lisaa(merk1); 
     * merkinnat.lisaa(merk2); 
     * merkinnat.lisaa(merk1); 
     * 
     * StringBuffer ids = new StringBuffer(30);
     * for (Merkinta merkinta:merkinnat)   // Kokeillaan for-silmukan toimintaa
     *   ids.append(" "+merkinta.getMerkintaid());           
     * 
     * String tulos = " " + merk1.getMerkintaid() + " " + merk2.getMerkintaid() + " " + merk1.getMerkintaid();
     * 
     * ids.toString() === tulos; 
     * 
     * ids = new StringBuffer(30);
     * for (Iterator<Merkinta>  i=merkinnat.iterator(); i.hasNext(); ) { // ja iteraattorin toimintaa
     *   Merkinta merkinta = i.next();
     *   ids.append(" "+merkinta.getMerkintaid());           
     * }
     * 
     * ids.toString() === tulos;
     * 
     * Iterator<Merkinta>  i=merkinnat.iterator();
     * i.next() == merk1  === true;
     * i.next() == merk2  === true;
     * i.next() == merk1  === true;
     * 
     * i.next();  #THROWS NoSuchElementException
     *  
     * </pre>
     *
     */
    public class MerkinnatIterator implements Iterator<Merkinta> {
        private int kohdalla = 0;


        /**
         * Onko olemassa vielä seuraavaa merkintää
         * @see java.util.Iterator#hasNext()
         * @return true jos on vielä merkintöjä
         */
        @Override
        public boolean hasNext() {
            return kohdalla < getLkm();
        }


        /**
         * Annetaan seuraava merkintä
         * @return seuraava merkintä
         * @throws NoSuchElementException jos seuraava alkiota ei enää ole
         * @see java.util.Iterator#next()
         */
        @Override
        public Merkinta next() throws NoSuchElementException {
            if ( !hasNext() ) throw new NoSuchElementException("Ei oo");
            return anna(kohdalla++);
        }
    }
    
    
    /**
     * Palautetaan iteraattori merkinnöistä.
     * @return merkintä iteraattori
     */
    @Override
    public Iterator<Merkinta> iterator() {
        return new MerkinnatIterator();
    } 
    
    
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Merkinnat merkinnat = new Merkinnat();

        Merkinta pvm1 = new Merkinta(), pvm2 = new Merkinta();
        pvm1.merkinnanLisays();
        //pvm1.taytaM1Tiedoilla(5);
        pvm2.merkinnanLisays();
        pvm2.taytaM2Tiedoilla();

        merkinnat.lisaa(pvm1);
        merkinnat.lisaa(pvm2);

        System.out.println("============= Merkinnät testi =================");

        for (int i = 0; i < merkinnat.getLkm(); i++) {
            Merkinta merkinta = merkinnat.anna(i);
            System.out.println("Merkinta nro: " + i);
            merkinta.tulosta(System.out);
        }
    
    }


    /** 
     * Poistaa merkinnän jolla on valittu id-numero  
     * @param id poistettavan merkinnän id-numero 
     * @return 1 jos poistettiin, 0 jos ei löydy 
     * @example 
     * <pre name="test"> 
     * #THROWS SailoException  
     * Merkinnat merkinnat = new Merkinnat(); 
     * Merkinta pvm1 = new Merkinta(), pvm2 = new Merkinta(), pvm3 = new Merkinta(); 
     * pvm1.merkinnanLisays(); pvm2.merkinnanLisays(); pvm3.merkinnanLisays(); 
     * int id1 = pvm1.getMerkintaid(); 
     * merkinnat.lisaa(pvm1); merkinnat.lisaa(pvm2); merkinnat.lisaa(pvm3); 
     * merkinnat.poista(id1+1) === 1; merkinnat.getLkm() === 2; 
     * merkinnat.poista(id1) === 1; merkinnat.getLkm() === 1; 
     * merkinnat.poista(id1+3) === 0; merkinnat.getLkm() === 1; 
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
     * Etsii merkinnän id:n perusteella 
     * @param id tunnusnumero, jonka mukaan etsitään 
     * @return löytyneen merkinnän indeksi tai -1 jos ei löydy 
     * <pre name="test"> 
     * #THROWS SailoException  
     * Merkinnat merkinnat = new Merkinnat(); 
     * Merkinta pvm1 = new Merkinta(), pvm2 = new Merkinta(), pvm3 = new Merkinta(); 
     * pvm1.merkinnanLisays(); pvm2.merkinnanLisays(); pvm3.merkinnanLisays(); 
     * int id1 = pvm1.getMerkintaid(); 
     * merkinnat.lisaa(pvm1); merkinnat.lisaa(pvm2); merkinnat.lisaa(pvm3); 
     * merkinnat.etsiId(id1+1) === 1; 
     * merkinnat.etsiId(id1+2) === 2; 
     * </pre> 
     */ 
    public int etsiId(int id) { 
        for (int i = 0; i < lkm; i++) 
            if (id == alkiot[i].getMerkintaid()) return i; 
        return -1; 
    }


    /**
     * Poistaa merkinnät siltä käyttäjältä, jonka id-numero on annettu parametrina
     * @param id sen käyttäjän id-numero, jonka merkinnät poistetaan
     * @return 0 jos parametrina tuotu käyttäjä-id on 0, ja 1 jos merkintöjen poistaminen onnistuu
     * <pre name="test">
     * #THROWS SailoException
     * Merkinnat merkinnat = new Merkinnat(); 
     * Merkinta pvm1 = new Merkinta(1), pvm2 = new Merkinta(1), pvm3 = new Merkinta(1); 
     * pvm1.merkinnanLisays(); pvm2.merkinnanLisays(); pvm3.merkinnanLisays(); 
     * int id1 = -1;
     * merkinnat.lisaa(pvm1); merkinnat.lisaa(pvm2); merkinnat.lisaa(pvm3); 
     * merkinnat.poistaKayttajanMerkinnat(id1) === 0; 
     * merkinnat.getLkm() === 3;
     * id1 = pvm1.getKayttajaId(); 
     * merkinnat.poistaKayttajanMerkinnat(id1) === 1;
     * merkinnat.getLkm() === 0;
     * </pre>
     */
    public int poistaKayttajanMerkinnat(int id) {
        int ind = etsiKayttajaId(id); 
        if (ind < 0) return 0;  
        for (int i = ind; i < lkm; i++) {
            if (i == ind)
                poistaMerkinta(i);
        }
        alkiot[lkm] = null; 
        muutettu = true; 
        return 1; 
    }
    
    
    /**
     * Poistaa yhden merkinnän käyttäjän id:n perusteella
     * @param id sen käyttäjän id, jonka merkintä poistetaan
     */
    public void poistaMerkinta(int id) {
        int ind = etsiKayttajaId(id); 
        if (ind < 0) return; 
        lkm--; 
        for (int i = ind; i < lkm; i++) 
            alkiot[i] = alkiot[i + 1]; 
        alkiot[lkm] = null; 
    }


    /** 
     * Etsii käyttäjän id:n perusteella 
     * @param id tunnusnumero, jonka mukaan etsitään 
     * @return löytyneen käyttäjän indeksi tai -1 jos ei löydy 
     * <pre name="test"> 
     * #THROWS SailoException  
     * Merkinnat merkinnat = new Merkinnat(); 
     * Merkinta pvm1 = new Merkinta(1), pvm2 = new Merkinta(2), pvm3 = new Merkinta(2); 
     * pvm1.merkinnanLisays(); pvm2.merkinnanLisays(); pvm3.merkinnanLisays();
     * int id1 = pvm1.getKayttajaId(); 
     * merkinnat.lisaa(pvm1); merkinnat.lisaa(pvm2); merkinnat.lisaa(pvm3);
     * merkinnat.etsiKayttajaId(id1) === 0; 
     * merkinnat.etsiKayttajaId(id1+1) === 1; 
     * merkinnat.etsiKayttajaId(id1+2) === -1;
     * merkinnat.etsiKayttajaId(id1+5) === -1;
     * </pre> 
     */ 
    public int etsiKayttajaId(int id) {
        for (int i = 0; i < lkm; i++) 
            if (id == alkiot[i].getKayttajaId()) return i; 
        return -1; 
    }

}

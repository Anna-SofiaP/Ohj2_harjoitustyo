/**
 * 
 */
package unipaivakirja;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Luokka käyttäjän unipäiväkirjan merkinnöille. Osaa lisätä uuden merkinnän.
 * @author Omistaja
 * @version 8.3.2022
 *
 */
public class Merkinnat {
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
    }
    
    
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
        throw new SailoException("Ei osata vielä tallettaa tiedostoa " + tiedostonPerusNimi);
    }


    /**
     * Palauttaa käyttäjän unipäiväkirjamerkintöjen lukumäärän
     * @return merkintöjen lukumäärä
     */
    public int getLkm() {
        return lkm;
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
    
    
    

}

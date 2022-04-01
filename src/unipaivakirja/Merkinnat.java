/**
 * 
 */
package unipaivakirja;

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
    private String tiedostonimi = "";
    private Merkinta alkiot[] = new Merkinta[MAX_MERKINTOJA];
    
    
    /**
     * Oletusmuodostaja
     */
    public Merkinnat() {
        //Alustettu attribuuteissa
    }
    
    
    /**
     * Lisää uuden merkinnän tietorakenteeseen.  Ottaa merkinnän omistukseensa.
     * @param merkinta lisättävän merkinnän viite.  Huom tietorakenne muuttuu omistajaksi
     * @throws SailoException jos tietorakenne on jo täynnä
     * @example
     * <pre name="test">
     * #THROWS SailoException 
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
     * merkinnat.lisaa(pvm1);  #THROWS SailoException
     * </pre>
     */
    public void lisaa(Merkinta merkinta) throws SailoException {
        if (lkm >= alkiot.length) kasvataTaulukkoa();
            //throw new SailoException("Liikaa alkioita");
        alkiot[lkm] = merkinta;
        lkm++;
    }
    
    
    /**
     * Kasvatetaan merkinnät-taulukon kokoa
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
     * Lukee merkinnät tiedostosta.  Kesken.
     * @param hakemisto tiedoston hakemisto
     * @throws SailoException jos lukeminen epäonnistuu
     */
    public void lueTiedostosta(String hakemisto) throws SailoException {
        tiedostonimi = hakemisto + "/nimet.dat";
        throw new SailoException("Ei osata vielä lukea tiedostoa " + tiedostonimi);
    }
    
    
    /**
     * Tallentaa merkinnät tiedostoon.  Kesken.
     * @throws SailoException jos talletus epäonnistuu
     */
    public void talleta() throws SailoException {
        throw new SailoException("Ei osata vielä tallettaa tiedostoa " + tiedostonimi);
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
        pvm1.taytaM1Tiedoilla();
        pvm2.merkinnanLisays();
        pvm2.taytaM2Tiedoilla();

        try {
            merkinnat.lisaa(pvm1);
            merkinnat.lisaa(pvm2);

            System.out.println("============= Merkinnät testi =================");

            for (int i = 0; i < merkinnat.getLkm(); i++) {
                Merkinta merkinta = merkinnat.anna(i);
                System.out.println("Merkinta nro: " + i);
                merkinta.tulosta(System.out);
            }

        } catch (SailoException ex) {
            System.out.println(ex.getMessage());
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

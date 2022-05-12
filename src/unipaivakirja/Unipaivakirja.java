/**
 * 
 */
package unipaivakirja;

import java.io.File;
import java.util.Collection;
import java.util.List;

/**
 * Unipaivakirja-luokka, joka huolehtii käyttäjistä.
 * @author Omistaja
 * @version 8.3.2022
 *
 */
public class Unipaivakirja {
    private Kayttajat kayttajat = new Kayttajat();
    private Merkinnat merkinnat = new Merkinnat();
    
    
    /**
     * Palautaa unipäiväkirjan käyttäjien määrän
     * @return jäsenmäärä
     */
    public int getKayttajia() {
        return kayttajat.getLkm();
    }
    
    
    /**
     * Lisää unipäiväkirjaan uuden käyttäjän
     * @param kayttaja lisättävä käyttäjä
     * @example
     * <pre name="test">
     * Unipaivakirja unipaivakirja = new Unipaivakirja();
     * Kayttaja nea = new Kayttaja("Nea"), ansku = new Kayttaja("Ansku");
     * nea.rekisteroi(); ansku.rekisteroi();
     * unipaivakirja.getKayttajia() === 0;
     * unipaivakirja.lisaa(nea); unipaivakirja.getKayttajia() === 1;
     * unipaivakirja.lisaa(ansku); unipaivakirja.getKayttajia() === 2;
     * unipaivakirja.lisaa(nea); unipaivakirja.getKayttajia() === 3;
     * unipaivakirja.getKayttajia() === 3;
     * unipaivakirja.annaKayttaja(0) === nea;
     * unipaivakirja.annaKayttaja(1) === ansku;
     * unipaivakirja.annaKayttaja(2) === nea;
     * unipaivakirja.annaKayttaja(3) === nea; #THROWS IndexOutOfBoundsException 
     * unipaivakirja.lisaa(nea); unipaivakirja.getKayttajia() === 4;
     * unipaivakirja.lisaa(nea); unipaivakirja.getKayttajia() === 5;
     * unipaivakirja.lisaa(nea);
     * </pre>
     */
    public void lisaa(Kayttaja kayttaja) {
        kayttajat.lisaa(kayttaja);
    }
    
    
    /**
     * Palauttaa i:n käyttäjän
     * @param i monesko käyttäjä palautetaan
     * @return viite i:teen käyttäjään
     * @throws IndexOutOfBoundsException jos i väärin
     * @exmaple
     * <pre name="test">
     *  Unipaivakirja unipaivakirja = new Unipaivakirja();
     *  Kayttaja nea = new Kayttaja("Nea"), ansku = new Kayttaja("Ansku");
     *  nea.rekisteroi();
     *  nea.rekisteroi();
     *  unipaivakirja.lisaa(nea);
     *  unipaivakirja.lisaa(ansku);
     *  unipaivakirja.annaKayttaja(0) === nea;
     *  unipaivakirja.annaKayttaja(1) === ansku;
     *  unipaivakirja.annaKayttaja(5); #THROWS IndexOutOfBoundsException
     * </pre>
     */
    public Kayttaja annaKayttaja(int i) throws IndexOutOfBoundsException {
        if (i < 0 || kayttajat.getLkm() <= i)
            throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        return kayttajat.anna(i);
    }
    
    
    /**
     * Palauttaa listan käyttäjistä
     * @return lista käyttäjistä
     */
    public List<Kayttaja> annaKayttajat() {
        return kayttajat.annaKayttajat();
    }
    
    
    /**
     * Lukee unipäiväkirjan tiedot tiedostosta
     * @param tiedosto jota käyteään lukemisessa
     * @throws SailoException jos lukeminen epäonnistuu
     */
    public void lueTiedostosta(String tiedosto) throws SailoException {
        kayttajat = new Kayttajat();
        merkinnat = new Merkinnat();
        
        setTiedosto(tiedosto);
        kayttajat.lueTiedostosta();
        merkinnat.lueTiedostosta();
    }
    
    
    /**
     * Asettaa tiedostojen perusnimet
     * @param nimi uusi nimi
     */
    public void setTiedosto(String nimi) {
        File dir = new File(nimi);
        dir.mkdirs();
        String hakemistonNimi = "data";
        if ( !nimi.isEmpty() ) hakemistonNimi = hakemistonNimi +"/";
        kayttajat.setTiedostonPerusNimi(hakemistonNimi + "kayttajat");
        merkinnat.setTiedostonPerusNimi(hakemistonNimi + "merkinnat");
    }
    
    
    
    /**
     * Tallettaa unipäiväkirjan tiedot tiedostoon
     * @throws SailoException jos tallettamisessa ongelmia
     */
    public void talleta() throws SailoException {
        kayttajat.talleta();
        merkinnat.talleta();
    }
    

    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Unipaivakirja unipaivakirja = new Unipaivakirja();

        Kayttaja nea = new Kayttaja("nea"), ansku = new Kayttaja("ansku");
        nea.rekisteroi();
        nea.taytaNeaTiedoilla();
        ansku.rekisteroi();
        ansku.taytaAnskuTiedoilla();

        unipaivakirja.lisaa(nea);
        unipaivakirja.lisaa(ansku);

        System.out.println("============= Kerhon testi =================");

        for (int i = 0; i < unipaivakirja.getKayttajia(); i++) {
            Kayttaja kayttaja = unipaivakirja.annaKayttaja(i);
            System.out.println("Käyttäjä paikassa: " + i);
            kayttaja.tulosta(System.out);
        }
    }


    /**
     * Asettaa käyttäjän comboboxchooserista valitun käyttäjänimen perusteella
     * @param selectedText comboboxchooserista valittu käyttäjä
     * @return valittu käyttäjä
     */
    public Kayttaja asetaKayttaja(String selectedText) {
        return kayttajat.aseta(selectedText);
    }
    
    
    /**
     * @param kayttajaId sen käyttäjän id, jonka merkinnät haetaan alkiot-taulukosta
     * @return tiettyyn käyttäjään kytketyt merkinnät listana
     */
    public List<Merkinta> annaKayttajanMerkinnat(int kayttajaId) {
        return merkinnat.annaKayttajanMerkinnat(kayttajaId);
    }


    /**
     * Lisää merkinnän tietorakenteeseen
     * @param merkinta lisättävä merkintä
     */
    public void lisaa(Merkinta merkinta) {
        merkinnat.lisaa(merkinta);
        
    }


    /**
     * Poistaa valitun käyttäjän tietorakenteesta
     * @param kayttaja poistettava käyttäjä
     * @return jokin luku ??
     */
    public int poista(Kayttaja kayttaja) {
        if ( kayttaja == null ) return 0;
        int ret = kayttajat.poista(kayttaja.getKayttajaId()); 
        merkinnat.poistaKayttajanMerkinnat(kayttaja.getKayttajaId()); 
        return ret; 
    }
    
    
    /** 
     * Poistaa tämän merkinnän 
     * @param merkinta poistettava merkintä
     */ 
    public void poista(Merkinta merkinta) { 
        merkinnat.poista(merkinta.getMerkintaid()); 
    }


    /** 
     * Palauttaa "taulukossa" hakuehtoon vastaavien merkintöjen viitteet 
     * @param hakuehto hakuehto 
     * @param kayttajaid sen käyttäjän id-numero, jonka merkinnät haetaan hakuehdon mukaan 
     * @return tietorakenteen löytyneistä merkinnöistä
     * @example 
     * <pre name="test">
     *   #import java.util.Collection;
     *   #import java.util.Iterator;
     *   Unipaivakirja unip = new Unipaivakirja();
     *   Merkinta merk1 = new Merkinta(1); merk1.merkinnanLisays();
     *   merk1.setPvmDate("2022-03-12");
     *   unip.lisaa(merk1);
     *   Collection<Merkinta> loytyneet = unip.etsi("2022-03-12", 1);
     *   loytyneet.size() === 1;
     *   Iterator<Merkinta> it = loytyneet.iterator();
     *   it.next() == merk1 === true; 
     * </pre>
     */ 
    public Collection<Merkinta> etsi(String hakuehto, int kayttajaid) { 
        return merkinnat.etsi(hakuehto, kayttajaid); 
    } 
    
    
    /**
     * @param merkinta merkintä jota muokataan
     * @throws SailoException jos muokkaus ei onnistu
     * @example
     * <pre name="test">
     * #THROWS SailoException  
     *  Unipaivakirja unip = new Unipaivakirja();
     *  Kayttaja uusi = new Kayttaja("Nea");
     *  uusi.rekisteroi();
     *  unip.lisaa(uusi);
     *  Merkinta merk1 = new Merkinta(1);
     *  merk1.merkinnanLisays();
     *  merk1.setPvmDate("2022-03-12");
     *  unip.lisaa(merk1);
     *  unip.etsi("12", 1).size() === 1;
     *  unip.muokkaa(merk1);
     *  unip.etsi("12", 1).size() === 1;
     * </pre>
     */
    public void muokkaa(Merkinta merkinta) throws SailoException{
        merkinnat.muokkaa(merkinta);
    }
}

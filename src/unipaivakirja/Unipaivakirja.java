/**
 * 
 */
package unipaivakirja;

import java.io.File;
import java.util.ArrayList;
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
     * Poistaa käyttäjistä ne joilla on nro. Kesken.
     * @param poistettava se käyttäjä, joka poistetaan
     * @return montako jäsentä poistettiin
     */
    public int poista(Merkinta poistettava) {
        if ( poistettava == null ) return 0;
        int ret = merkinnat.poista(poistettava.getMerkintaid());
        return ret;
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
     * 
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #import java.io.*;
     * #import java.util.*;
     * 
     *  Unipaivakirja unip = new Unipaivakirja();
     *  
     *  Kayttaja nea = new Kayttaja(); nea.taytaNeaTiedoilla(); nea.rekisteroi();
     *  Kayttaja ansku = new Kayttaja(); ansku.taytaAnskuTiedoilla(); ansku.rekisteroi();
     *  Merkinta merk1 = new Merkinta(); merk1.taytaM1Tiedoilla();
     *  Merkinta merk2 = new Merkinta(); merk2.taytaM2Tiedoilla();
     *  Merkinta merk3 = new Merkinta(); merk3.taytaM1Tiedoilla(); 
     *  Merkinta merk4 = new Merkinta(); merk4.taytaM2Tiedoilla(); 
     *  Merkinta merk5 = new Merkinta(); merk5.taytaM1Tiedoilla();
     *   
     *  String hakemisto = "testiKayttajat";
     *  File dir = new File(hakemisto);
     *  File ftied  = new File(hakemisto+"/tkayttajat.dat");
     *  File fhtied = new File(hakemisto+"/tmerkinnat.dat");
     *  dir.mkdir();  
     *  ftied.delete();
     *  fhtied.delete();
     *  unip.lueTiedostosta(hakemisto); #THROWS SailoException
     *  unip.lisaa(nea);
     *  unip.lisaa(ansku);
     *  unip.lisaa(merk1);
     *  unip.lisaa(merk2);
     *  unip.lisaa(merk3);
     *  unip.lisaa(merk4);
     *  unip.lisaa(merk5);
     *  unip.talleta();
     *  unip = new Unipaivakirja();
     *  unip.lueTiedostosta(hakemisto);
     *  Collection<Kayttaja> kaikki = unip.annaKayttajat(); 
     *  Iterator<Kayttaja> it = kaikki.iterator();
     *  it.next() === nea;
     *  it.next() === ansku;
     *  it.hasNext() === false;
     *  List<Merkinta> loytyneet = unip.annaKayttajanMerkinnat(1);
     *  Iterator<Merkinta> ih = loytyneet.iterator();
     *  ih.next() === merk1;
     *  ih.next() === merk2;
     *  ih.hasNext() === false;
     *  loytyneet = unip.annaKayttajanMerkinnat(2);
     *  ih = loytyneet.iterator();
     *  ih.next() === merk3;
     *  ih.next() === merk4;
     *  ih.next() === merk5;
     *  ih.hasNext() === false;
     *  unip.lisaa(ansku);
     *  unip.lisaa(merk3);
     *  unip.talleta();
     *  ftied.delete()  === true;
     *  fhtied.delete() === true;
     *  File fbak = new File(hakemisto+"/tkayttajat.bak");
     *  File fhbak = new File(hakemisto+"/tmerkinnat.bak");
     *  fbak.delete() === true;
     *  fhbak.delete() === true;
     *  dir.delete() === true;
     *  
     * </pre>
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
        // TODO: yritä tallettaa toinen vaikka toinen epäonnistuisi
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


    public void lisaa(Merkinta merkinta) {
        merkinnat.lisaa(merkinta);
        
    }
    
    
    /** 
     * Korvaa merkinnän tietorakenteessa.  Ottaa merkinnän omistukseensa. 
     * Etsitään samalla tunnusnumerolla oleva merkintä.  Jos ei löydy, 
     * niin lisätään uutena merkintänä. 
     * @param merkinta viite lisättävään merkintään
     * @throws SailoException jos tietorakenne on jo täynnä 
     */ 
    public void korvaaTaiLisaa(Merkinta merkinta) throws SailoException { 
        merkinnat.korvaaTaiLisaa(merkinta); 
    }


    public int poista(Kayttaja poistettava) {
        if (poistettava == null) return 0;
        int ret = kayttajat.poista(poistettava.getKayttajaId());
        //merkinnat.poistaKayttajanMerkinnat(poistettava.getKayttajaId());
        return ret;
    } 

    
    
    

}

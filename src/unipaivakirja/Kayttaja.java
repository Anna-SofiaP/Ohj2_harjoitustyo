package unipaivakirja;

import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * Unipäiväkirjan käyttäjä
 * @author Omistaja
 * @version 2.3.2022
 *
 */
public class Kayttaja {
    private int kayttajaid = 0;
    private String kayttajanimi = "";
    
    private static int seuraavaNro = 1;
    
    
    
    /**
     * Oletusmuodostaja
     */
    public Kayttaja() {
        //oletusmuodostaja
    }
    
    
    /**
     * @param kayttajanimi käyttäjän käyttäjänimi
     */
    public Kayttaja(String kayttajanimi) {
        this.kayttajanimi = kayttajanimi;
    }
    
    /**
     * @return käyttäjän nimi
     * @example
     * <pre name="test">
     *   Kayttaja nea = new Kayttaja("Nea");
     *   nea.taytaNeaTiedoilla();
     *   nea.getNimi() =R= "Nea";
     * </pre>
     */
    public String getNimi() {
        return kayttajanimi;
    }
    
    
    /**
     * Apumetodi, jolla täytetään testiarvot käyttäjille.
     */
    public void taytaNeaTiedoilla() {
        kayttajaid = 1;
        kayttajanimi = "Nea";
    }
    
    
    /**
     * Apumetodi, jolla täytetään testiarvot käyttäjille.
     */
    public void taytaAnskuTiedoilla() {
        kayttajaid = 2;
        kayttajanimi = "Ansku";
    }
    

    /**
     * Antaa käyttäjälle oman rekisterinumeron.
     * @return käyttäjän uusi kayttajaid
     * @example
     * <pre name="test">
     *   Kayttaja nea = new Kayttaja("Nea");
     *   nea.getKayttajaId() === 0;
     *   nea.rekisteroi();
     *   Kayttaja ansku = new Kayttaja("Ansku");
     *   ansku.rekisteroi();
     *   int n1 = nea.getKayttajaId();
     *   int n2 = ansku.getKayttajaId();
     *   n1 === n2-1;
     * </pre>
     */
    public int rekisteroi() {
        kayttajaid = seuraavaNro;
        seuraavaNro++;
        return kayttajaid;
    }
    
    
    /**
     * Palauttaa käyttäjän käyttäjäid:n.
     * @return käyttäjän käyttäjäid
     * @example
     * <pre name="test">
     *   Kayttaja nea = new Kayttaja("Nea");
     *   nea.taytaNeaTiedoilla();
     *   nea.getKayttajaId() === 1;
     * </pre>
     */
    public int getKayttajaId() {
        return kayttajaid;
    }
    

    /**
     * Tulostaa käyttäjän tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println("Käyttäjä: " + kayttajanimi);
    }
    
    
    /**
     * Palauttaa käyttäjän tiedot merkkijonona jonka voi tallentaa tiedostoon.
     * @return käyttäjä tolppaeroteltuna merkkijonona 
     * @example
     * <pre name="test">
     *   Kayttaja kayttaja = new Kayttaja("Liisa");
     *   kayttaja.parse("3|Liisa|");
     *   kayttaja.toString().startsWith("3|Liisa|") === true;
     * </pre>  
     */
    @Override
    public String toString() {
        return "" + getKayttajaId() + "|" + kayttajanimi + "|";
    }

    
    
    /**
     * Pilkotaan kayttajat.dat tiedostosta saadun rivin tiedot
     * @param rivi tiedostosta luettu rivi, joka pilkotaan
     * @example
     * <pre name="test">
     *   Kayttaja kayttaja = new Kayttaja("Liisa");
     *   kayttaja.parse("3|Liisa|");
     *   kayttaja.getKayttajaId() === 3;
     *   kayttaja.toString().startsWith("3|Liisa|") === true;
     *
     *   kayttaja.rekisteroi();
     *   int n = kayttaja.getKayttajaId();
     *   kayttaja.parse(""+(n+20));       // Otetaan merkkijonosta vain tunnusnumero
     *   kayttaja.rekisteroi();           // ja tarkistetaan että seuraavalla kertaa tulee yhtä isompi
     *   kayttaja.getKayttajaId() === n+20+1;   
     * </pre>

     */
    public void parse(String rivi) {
        StringBuffer sb = new StringBuffer(rivi);
        setKayttajaId(Mjonot.erota(sb, '|', getKayttajaId()));
        kayttajanimi = Mjonot.erota(sb, '|', kayttajanimi);
    }

    
    /**
     * Asettaa käyttäjälle id-numeron
     * @param id tunnusnumero joka asetetaan käyttäjälle
     */
    private void setKayttajaId(int id) {
        this.kayttajaid = id; 
        if (kayttajaid >= seuraavaNro) seuraavaNro = kayttajaid + 1;
    }


    /**
     * Testiohjelma käyttäjälle.
     * @param args ei käytössä
     */
    public static void main(String args[]) {
        Kayttaja nea = new Kayttaja("nea"), ansku = new Kayttaja("ansku");
        nea.rekisteroi();
        ansku.rekisteroi();
        
        nea.tulosta(System.out);
        nea.taytaNeaTiedoilla();
        nea.tulosta(System.out);

        ansku.taytaAnskuTiedoilla();
        ansku.tulosta(System.out);
    }

}

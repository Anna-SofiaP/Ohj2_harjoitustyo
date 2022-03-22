package unipaivakirja;

import java.io.PrintStream;

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
     * @return käyttäjän nimi
     * @example
     * <pre name="test">
     *   Kayttaja nea = new Kayttaja();
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
     *   Kayttaja nea = new Kayttaja();
     *   nea.getKayttajaId() === 1;
     *   nea.rekisteroi();
     *   Kayttaja ansku = new Kayttaja();
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
     */
    public int getKayttajaId() {
        return kayttajaid;
    }
    
    
    /**
     * Testiohjelma käyttäjälle.
     * @param args ei käytössä
     */
    public static void main(String args[]) {
        Kayttaja nea = new Kayttaja(), ansku = new Kayttaja();
        nea.rekisteroi();
        ansku.rekisteroi();
        
        nea.tulosta(System.out);
        nea.taytaNeaTiedoilla();
        nea.tulosta(System.out);

        ansku.taytaAnskuTiedoilla();
        ansku.tulosta(System.out);
    }


    /**
     * Tulostaa käyttäjän tiedot
     * @param out ??
     */
    public void tulosta(PrintStream out) {
        out.println("Käyttäjä: " + kayttajanimi);
    }

}

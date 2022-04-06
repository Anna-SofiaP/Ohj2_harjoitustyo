/**
 * 
 */
package unipaivakirja;

import java.io.*;

/**
 * Unenlaatu-luokka. Osaa huolehtia id-numerostaan.
 * @author Omistaja
 * @version 18.3.2022
 *
 */
public class Unenlaatu {
    
    private int unenlaatuid;
    private int merkintaid;
    private String unenlaatu;
    
    
    /**
     * Alustetaan unenlaatu
     * @param unenlaatu uusi unenlaatuvaihtoehto
     */
    public Unenlaatu(String unenlaatu) {
        this.unenlaatu = unenlaatu;
    }
    
    
    /**
     * Alustetaan tietyn unipäiväkirjamerkinnän unenlaatu.  
     * @param unenlaatuid merkinnän viitenumero 
     */
    public Unenlaatu(int unenlaatuid) {
        this.unenlaatuid = unenlaatuid;
    }
    
    
    /**
     * @param unenlaatu unenlaatuvaihtoehto
     * @param unenlaatuid tietyn unenlaadun id-numero
     */
    public Unenlaatu(String unenlaatu, int unenlaatuid)  {
        this.unenlaatu = unenlaatu;
        this.unenlaatuid = unenlaatuid;
    }
    
    /**
     * Apumetodi, jolla saadaan täytettyä testiarvot Unenlaadulle.
     * @param nro viite merkintään, jonka unenlaadusta on kyse
     */
    public void taytaUnenlaatuTiedoilla(int nro) {
        merkintaid = nro;
        unenlaatu = "kohtalainen";
    }
    
    
    /**
     * Tulostetaan unenlaadun tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println("Merkinnän " + merkintaid + " unenlaatu: " + unenlaatu);
    }
    
    
    /**
     * Tulostetaan merkinnän tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    
    /**
     * Palautetaan unenlaadun oma id
     * @return unenlaadun id
     */
    public int getUnenlaatuID() {
        return unenlaatuid;
    }
    
    
    /**
     * Palautetaan mille merkinnälle tietty unenlaatu on valittu
     * @return merkinnän id
     */
    public int getMerkintaID() {
        return merkintaid;
    }


    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Unenlaatu valittu = new Unenlaatu("erittäin hyvä");
        valittu.taytaUnenlaatuTiedoilla(2);
        valittu.tulosta(System.out);
    }

}

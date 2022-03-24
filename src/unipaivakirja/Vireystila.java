/**
 * 
 */
package unipaivakirja;

import java.io.*;

/**
 * Vireystila-luokka. Osaa huolehtia id-numerostaan.
 * @author Omistaja
 * @version 18.3.2022
 *
 */
public class Vireystila {
    
    private int vireystilaid;
    private int merkintaid;
    private String vireystila;
    
    
    /**
     * Alustetaan vireystila
     * @param vireystila uusi vireystilavaihtoehto
     */
    public Vireystila(String vireystila) {
        this.vireystila = vireystila;
    }
    
    
    /**
     * Alustetaan tietyn unipäiväkirjamerkinnän vireystila.  
     * @param merkintaid merkinnän viitenumero 
     */
    public Vireystila(int merkintaid) {
        this.merkintaid = merkintaid;
    }
    
    
    /**
     * Apumetodi, jolla saadaan täytettyä testiarvot Vireystila.
     * @param nro viite merkintään, jonka vireystila on kyse
     */
    public void taytaVireystilaTiedoilla(int nro) {
        merkintaid = nro;
        vireystila = "kohtalainen";
    }
    
    
    /**
     * Tulostetaan vireystila tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println("Merkinnän " + merkintaid + " vireystila: " + vireystila);
    }
    
    
    /**
     * Tulostetaan merkinnän tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    
    /**
     * Palautetaan vireystila oma id
     * @return vireystila id
     */
    public int getVireystilaID() {
        return vireystilaid;
    }
    
    
    /**
     * Palautetaan mille merkinnälle tietty vireystila on valittu
     * @return merkinnän id
     */
    public int getMerkintaID() {
        return merkintaid;
    }


    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Vireystila valittu = new Vireystila("erittäin hyvä");
        valittu.taytaVireystilaTiedoilla(2);
        valittu.tulosta(System.out);
    }

}

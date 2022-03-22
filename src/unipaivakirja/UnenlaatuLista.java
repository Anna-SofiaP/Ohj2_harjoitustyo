/**
 * 
 */
package unipaivakirja;

import java.util.*;

/**
 * @author Omistaja
 * @version 18.3.2022
 *
 */
public class UnenlaatuLista {
    
    private String tiedostonimi = "";
    private final int lkm = 5;
    private final Collection<Unenlaatu> alkiot = new ArrayList<Unenlaatu>(lkm);
    
    
    /**
     * Unenlaatulistan alustaminen
     */
    public UnenlaatuLista() {
        //Ei tarvitse mitään
    }
    
    
    /**
     * Lisää uuden unenlaatuvaihtoehdon tietorakenteeseen.  Ottaa unenlaadun omistukseensa.
     * @param vaihtoehto lisättävä unenlaatuvaihtoehto.
     */
    public void lisaa(Unenlaatu vaihtoehto) {
        alkiot.add(vaihtoehto);
    }
    
    
    //Ei toimi vielä!!!
    /**
     * Lukee merkinnät tiedostosta.  
     * TODO Kesken.
     * @param hakemisto tiedoston hakemisto
     * @throws SailoException jos lukeminen epäonnistuu
     */
    public void lueTiedostosta(String hakemisto) throws SailoException {
        tiedostonimi = hakemisto + ".har";         //Mitä tuohon ".har" -kohtaan???
        throw new SailoException("Ei osata vielä lukea tiedostoa " + tiedostonimi);
    }
    
    
    //Ei toimi vielä!!!
    /**
     * Tallentaa merkinnät tiedostoon.  
     * TODO Kesken.
     * @throws SailoException jos talletus epäonnistuu
     */
    public void talleta() throws SailoException {
        throw new SailoException("Ei osata vielä tallettaa tiedostoa " + tiedostonimi);
    }
    
    
    /**
     * Palauttaa unenlaatuvaihtoehtojen lukumäärän
     * @return unenlaatuvaihtoehtojen lukumäärä
     */
    public int getLkm() {
        return alkiot.size();
    }


    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        // jee jee
    
    }

}

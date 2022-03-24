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
public class VireystilaLista {
    
    private String tiedostonimi = "";
    private final int lkm = 5;
    private final Collection<Vireystila> alkiot = new ArrayList<Vireystila>(lkm);
    
    
    /**
     * Unenlaatulistan alustaminen
     */
    public VireystilaLista() {
        //Ei tarvitse mitään
    }
    
    
    /**
     * Lisää uuden vireystilavaihtoehdon tietorakenteeseen.  Ottaa vireystilan omistukseensa.
     * @param vaihtoehto lisättävä vireystilavaihtoehto.
     */
    public void lisaa(Vireystila vaihtoehto) {
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
     * Palauttaa vireystilavaihtoehtojen lukumäärän
     * @return vireystilavaihtoehtojen lukumäärä
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
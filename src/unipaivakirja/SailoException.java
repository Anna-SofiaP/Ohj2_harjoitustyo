/**
 * 
 */
package unipaivakirja;

/**
 * Poikkeusluokka tietorakenteesta aiheutuville poikkeuksille.
 * @author Omistaja
 * @version 2.3.2022
 *
 */
public class SailoException extends Exception{
    private static final long serialVersionUID = 1L;
    
    
    /**
     * Poikkeuksien muodostaja jolle tuodaan poikkeustilanteessa 
     * käytettävä viesti
     * @param viesti poikkeustilanteen viesti
     */
    public SailoException(String viesti) {
        super(viesti);
    }

}

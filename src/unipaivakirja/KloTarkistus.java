/**
 * 
 */
package unipaivakirja;

/**
 * @author Omistaja
 * @version 8.3.2022
 *
 */
public class KloTarkistus {
    
    /**
     * Tarkistetaan, onko syötetty kellonaika hyväksyttävää muotoa: "hh:mm".
     * TODO: mistä tätä funktiota kutsutaan
     * @param aika kellonaika, joka tarkistetaan
     * @example
     * <pre name="test">
     *  kellonajanTarkistus("15:05") === true;
     *  kellonajanTarkistus("09:00") === true;
     *  kellonajanTarkistus("23:46") === true;
     *  kellonajanTarkistus("26:40") === false;
     *  kellonajanTarkistus("00:67") === false;
     *  kellonajanTarkistus("6:30") === false;
     * </pre>
     */
    public static void kellonajanTarkistus(String aika) {
        String tunnit = aika.substring(0, 1);
        String minuutit = aika.substring(3, 4);
        
        if (aika.charAt(2) != ':'); /*TODO: tee niin, että tämä tilanne
                                            antaa virheilmoituksen*/
        
        int tunti = 0;
        int minuutti = 0;
        
        if (tunnit.charAt(0) == '0') {
            tunnit = tunnit.substring(1);
            tunti = Integer.parseInt(tunnit);
        }
        tunti = Integer.parseInt(tunnit);
            
        if (minuutit.charAt(0) == '0') {
            minuutit = minuutit.substring(1);
            minuutti = Integer.parseInt(minuutit);
        }
        minuutti = Integer.parseInt(minuutit);
        
        if (tunti < 0 || tunti > 23); //TODO: virheilmoitus!!!
        if (minuutti < 0 || minuutti > 59); //TODO: virheilmoitus!!!
    }
    
}

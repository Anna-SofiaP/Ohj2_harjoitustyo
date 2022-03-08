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
     */
    public static void kellonajanTarkistus(String aika) {
        String tunnit = aika.substring(0, 1);
        String minuutit = aika.substring(3, 4);
        
        if (aika.charAt(2) != ':'); //virhe!!! 
        
        int tunti = 0;
        int minuutti = 0;
        
        if (tunnit.charAt(0) == '0') {
            tunnit = tunnit.substring(1);
            tunti = Integer.parseInt(tunnit);
        }
        tunti = Integer.parseInt(tunnit);
            
        if (minuutit.charAt(0) == '0') {
            minuutit = minuutit.substring(4);
            minuutti = Integer.parseInt(minuutit);
        }
        minuutti = Integer.parseInt(minuutit);
        
        if (tunti < 0 || tunti > 23); //virhe!!!
        if (minuutti < 0 || minuutti > 59); //virhe!!!
    }
    
}

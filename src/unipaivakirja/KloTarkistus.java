/**
 * 
 */
package unipaivakirja;

/**
 * @author Omistaja
 * @version 8.3.2022
 */
public class KloTarkistus {
    
    /**
     * Tarkistetaan, onko syötetty kellonaika hyväksyttävää muotoa: "hh:mm" tai "h:mm".
     * TODO: mistä tätä funktiota kutsutaan
     * @param aika kellonaika, joka tarkistetaan
     * @return palauttaa null jos kellonaika on oikeanlainen, jos ei niin palautetaan virheilmoitus
     * @example
     * <pre name="test">
     *  kellonajanTarkistus("15:05") === null;
     *  kellonajanTarkistus("09:00") === null;
     *  kellonajanTarkistus("23:46") === null;
     *  kellonajanTarkistus("26:40") === "Tunnit liian isot tai pienet";
     *  kellonajanTarkistus("00:67") === "Minuutit liian isot tai pienet";
     *  kellonajanTarkistus("6:30") === null;
     *  kellonajanTarkistus("440") === "Kaksoispiste puuttuu";
     *  kellonajanTarkistus("9") === "Kaksoispiste puuttuu";
     *  kellonajanTarkistus("3:2") === "Minuuteissa liian vähän merkkejä";
     *  kellonajanTarkistus("oo:l0") === "Kellonajassa virheellisiä merkkejä";
     *  kellonajanTarkistus("-4:30") === "Tunnit liian isot tai pienet";
     * </pre>
     */
    public static String kellonajanTarkistus(String aika) {
        if (aika == null) return "Anna kellonaika";
        
        String[] kellonaika = aika.split(":");
        
        if (kellonaika.length == 1) 
            return "Kaksoispiste puuttuu";
        
        if (kellonaika[1].length() == 1) return "Minuuteissa liian vähän merkkejä";
        
        int tunnit = 0;
        int minuutti = 0;
        
        if (kellonaika[0].charAt(0) == '0') {
            kellonaika[0] = kellonaika[0].substring(1);
            try {
                tunnit = Integer.parseInt(kellonaika[0]);
            } catch(NumberFormatException e) {
                return "Kellonajassa virheellisiä merkkejä";
            }
        }
        try {
            tunnit = Integer.parseInt(kellonaika[0]);
        } catch(NumberFormatException e) {
            return "Kellonajassa virheellisiä merkkejä";
        }
            
        if (kellonaika[1].charAt(0) == '0') {
            kellonaika[1] = kellonaika[1].substring(1);
            try {
                minuutti = Integer.parseInt(kellonaika[1]);
            } catch(NumberFormatException e) {
                return "Kellonajassa virheellisiä merkkejä";
            }
        }
        try {
            minuutti = Integer.parseInt(kellonaika[1]);
        } catch(NumberFormatException e) {
            return "Kellonajassa virheellisiä merkkejä";
        }
        
        if (tunnit < 0 || tunnit > 23) return "Tunnit liian isot tai pienet";
        if (minuutti < 0 || minuutti > 59) return "Minuutit liian isot tai pienet";
        
        return null;
    }
    
}

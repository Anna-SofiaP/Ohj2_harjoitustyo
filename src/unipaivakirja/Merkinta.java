/**
 * 
 */
package unipaivakirja;

import java.io.OutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Omistaja
 * @version 8.3.2022
 *
 */
public class Merkinta {
    private int merkintaid;
    private String pvm = "";
    private String nukkumaanKlo = "";
    private String heratysKlo = "";
    private String unenMaara = "";
    private String lisatiedot = "";
    //private Unenlaatu unenlaatu = new Unenlaatu();
    //private Vireystila vireystila = new Vireystila();
    
    private static int seuraavaNro = 1;
    
    
    /**
     * @return merkinnän päivämäärä
     * @example
     * <pre name="test">
     *   Merkinta pvm1 = new Merkinta();
     *   pvm1.taytaM1Tiedoilla();
     *   pvm1.getPvm() =R= "16.3.2022 .*";
     * </pre>
     */
    public String getPvm() {
        return pvm;
    }

    
    /**
     * Apumetodi, jolla saadaan täytettyä testiarvot merkinnälle.
     * @param pvm1 merkinnän päivämäärä
     * @param unenmaara laskettuna nukkumaanmnoajasta ja heräämisajasta.
     */
    public void taytaM1Tiedoilla(String pvm1, String unenmaara) {
        this.pvm = pvm1;
        nukkumaanKlo = "21:00";
        heratysKlo = "6:00";
        unenMaara = unenmaara;
        lisatiedot = "Heräilin muutaman kerran yön aikana.";
    }
    
    
    /**
     * Apumetodi, jolla saadaan täytettyä testiarvot merkinnälle.
     * @param pvm2 merkinnän päivämäärä
     * @param unenmaara laskettuna nukkumaanmnoajasta ja heräämisajasta.
     */
    public void taytaM2Tiedoilla(String pvm2, String unenmaara) {
        this.pvm = pvm2;
        nukkumaanKlo = "21:30";
        heratysKlo = "6:00";
        unenMaara = unenmaara;
        lisatiedot = "Nukuin kuin tukki.";
    }
    
    
    /**
     * Apumetodi, jolla saadaan täytettyä testiarvot merkinnälle.
     * Unenmäärä ja päivämäärä saadaan laskettua/tuotua erillisistä metodeista.
     */
    public void taytaM1Tiedoilla() {
        String pvm1 = pvmKalenterista();
        String unenmaara = laskeUnenmaara();
        taytaM1Tiedoilla(pvm1, unenmaara);
    }
    
    
    /**
     * Apumetodi, jolla saadaan täytettyä testiarvot merkinnälle.
     * Unenmäärä ja päivämäärä saadaan laskettua/tuotua erillisistä metodeista.
     */
    public void taytaM2Tiedoilla() {
        String pvm2 = pvmKalenterista();
        String unenmaara = laskeUnenmaara();
        taytaM2Tiedoilla(pvm2, unenmaara);
    }
    
    
    /**
     * Tulostetaan merkinnän tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%03d", merkintaid, 3) + "  " + pvm);
        out.println("Nukkumaanmenoaika: " + nukkumaanKlo + " Heräämisaika: " + heratysKlo + " Unen määrä: " + unenMaara);
        out.println("Lisätiedot: " + lisatiedot);
        //out.print("Unenlaatu: " + unenlaatu + " Vireystila: " + vireystila);
    }
    
    
    /**
     * Tulostetaan merkinnän tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    
    /**
     * Antaa merkinnälle seuraavan id-numeron.
     * @return merkinnän uusi merkintaid
     * @example
     * <pre name="test">
     *   Merkinta pvm1 = new Merkinta();
     *   pvm1.getMerkintaid() === 0;
     *   pvm1.merkinnanLisays();
     *   Merkinta pvm2 = new Merkinta();
     *   pvm2.merkinnanLisays();
     *   int n1 = pvm1.getMerkintaid();
     *   int n2 = pvm2.getMerkintaid();
     *   n1 === n2-1;
     * </pre>
     */
    public int merkinnanLisays() {
        merkintaid = seuraavaNro;
        seuraavaNro++;
        return merkintaid;
    }
    
    
    /**
     * Palauttaa merkinnän id-numeron.
     * @return merkinnän id-numero
     */
    public int getMerkintaid() {
        return merkintaid;
    }
    
    
    /**
     * laskee unen määrän
     * @return unen määrä
     * @throws Exception jos laskeminen ei onnistu
     * @example
     * <pre name="test">
     *  String nukkumaanKlo = "21:00";
     *  String heratysKlo = "6:00";
     *  SimpleDateFormat unenmaara = new SimpleDateFormat("HH:mm");
     *  Date nukkumaan = unenmaara.parse(nukkumaanKlo);
     *  Date heratys = unenmaara.parse(heratysKlo);
     *  laskeUnenmaara() === "9 h 0 min";
     *  nukkumaanKlo = "1:45";
     *  heratysKlo = "9:00";
     *  unenmaara = new SimpleDateFormat("HH:mm");
     *  nukkumaan = unenmaara.parse(nukkumaanKlo);
     *  heratys = unenmaara.parse(heratysKlo);
     *  laskeUnenmaara() === "7 h 15 min";
     * </pre>
     */
    public String laskeUnenmaara() throws Exception{
        SimpleDateFormat unenmaara = new SimpleDateFormat("HH:mm");
        
        Date nukkumaan = unenmaara.parse(nukkumaanKlo);
        Date heratys = unenmaara.parse(heratysKlo);
        
        long eroMilliSek = Math.abs(heratys.getTime() - nukkumaan.getTime());
        
        long eroMin = (eroMilliSek / (60 * 1000)) % 60;
        
        long eroH = (eroMilliSek / (60 * 60 * 1000)) % 24;
        
        this.unenMaara = eroH + " h " + eroMin + " min";
        return unenMaara;
    }
    

    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Merkinta pvm1 = new Merkinta(), pvm2 = new Merkinta();
        pvm1.merkinnanLisays();
        pvm2.merkinnanLisays();
        pvm1.tulosta(System.out);
        pvm1.taytaM1Tiedoilla();
        pvm1.tulosta(System.out);

        pvm2.taytaM2Tiedoilla();
        pvm2.tulosta(System.out);
    }

}

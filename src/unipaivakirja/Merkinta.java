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
    private int kayttajaid;
    private int merkintaid;
    private String pvm = "";
    private String nukkumaanKlo = "";
    private String heratysKlo = "";
    private String unenMaara = "";
    private String lisatiedot = "";
    //private Unenlaatu unenlaatu;
    //private Vireystila vireystila;
    
    private static int seuraavaNro = 1;
    
    
    /**
     * Oletusmuodostaja
     */
    public Merkinta() {
        //oletusmuodostaja
    }
    
    
    /**
     * Muodostaja
     * @param nukkumaanKlo merkinnän nukkumaanmenoaika
     * @param heratysKlo merkinnän heräämisaika
     */
    public Merkinta(String nukkumaanKlo, String heratysKlo) {
        this.nukkumaanKlo = nukkumaanKlo;
        this.heratysKlo = heratysKlo;
    }
    
    
    /**
     * @return merkinnän päivämäärä
     * @example
     * <pre name="test">
     *   Merkinta pvm1 = new Merkinta();
     *   pvm1.taytaM1Tiedoilla();
     *   pvm1.getPvm() === "12.3.2022";
     * </pre>
     */
    public String getPvm() {
        return pvm;
    }

    
    /**
     * Apumetodi, jolla saadaan täytettyä testiarvot merkinnälle.
     * @param pvm1 merkinnän päivämäärä
     */
    public void taytaM1Tiedoilla(String pvm1) {
        this.pvm = pvm1;
        nukkumaanKlo = "21:00";
        heratysKlo = "6:00";
        unenMaara = "9 h";                    //laskeUnenmaara();
        lisatiedot = "Heräilin muutaman kerran yön aikana.";
        //unenlaatu = new Unenlaatu("Erinomainen");
        //vireystila = new Vireystila("Energinen");
    }
    
    
    /**
     * Apumetodi, jolla saadaan täytettyä testiarvot merkinnälle.
     * @param pvm2 merkinnän päivämäärä
     */
    public void taytaM2Tiedoilla(String pvm2) {
        this.pvm = pvm2;
        nukkumaanKlo = "21:30";
        heratysKlo = "6:00";
        unenMaara = "8 h 30 min";                    //laskeUnenmaara();
        lisatiedot = "Nukuin kuin tukki.";
        //unenlaatu = new Unenlaatu("Kohtalainen");
        //vireystila = new Vireystila("Ihan jees");
    }
    
    
    /**
     * Apumetodi, jolla saadaan täytettyä testiarvot merkinnälle.
     * Unenmäärä ja päivämäärä saadaan laskettua/tuotua erillisistä metodeista.
     */
    public void taytaM1Tiedoilla() {
        String pvm1 = "12.3.2022";          //TODO: pvmKalenterista();
        taytaM1Tiedoilla(pvm1);
    }
    
    
    /**
     * Apumetodi, jolla saadaan täytettyä testiarvot merkinnälle.
     * Unenmäärä ja päivämäärä saadaan laskettua/tuotua erillisistä metodeista.
     */
    public void taytaM2Tiedoilla() {
        String pvm2 = "13.2.2022";         //TODO: pvmKalenterista();
        taytaM2Tiedoilla(pvm2);
    }
    
    
    /**
     * Tulostetaan merkinnän tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%03d", merkintaid, 3) + "  " + pvm);
        out.println("Nukkumaanmenoaika: " + nukkumaanKlo + " Heräämisaika: " + heratysKlo + " Unen määrä: " + unenMaara);
        out.println("Lisätiedot: " + lisatiedot);
        //out.print("Unenlaatu: " + unenlaatu.toString() + " Vireystila: " + vireystila.toString());
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
     * @return käyttäjän id-numeron, johon merkintä on kytketty
     */
    public int getKayttajaId() {
        return kayttajaid;
    }
    
    
    /**
     * Palauttaa merkinnän id-numeron.
     * @return merkinnän id-numero
     * @example
     * <pre name="test">
     *   Merkinta pvm1 = new Merkinta();
     *   pvm1.merkinnanLisays();
     *   pvm1.getMerkintaid() === 1;
     * </pre>
     */
    public int getMerkintaid() {
        return merkintaid;
    }
    
    
    /**
     * @return merkinnän nukkumaanmenoaika
     */
    public String getNukkumaanKlo() {
        return this.nukkumaanKlo;
    }
    
    
    /**
     * @return merkinnän heräämisaika
     */
    public String getHeratysKlo() {
        return this.heratysKlo;
    }
    
    
    /**
     * laskee unen määrän
     * @return unen määrä
     * @throws Exception jos laskeminen ei onnistu
     * @example
     * <pre name="test">
     * #import java.text.SimpleDateFormat;
     * #import java.util.Date;
     *  Merkinta merkinta = new Merkinta("21:00", "5:30");
     *  SimpleDateFormat unenmaara = new SimpleDateFormat("HH:mm");
     *  try {
     *      unenmaara.parse(merkinta.getNukkumaanKlo());
     *      unenmaara.parse(merkinta.getHeratysKlo());
     *      merkinta.laskeUnenmaara() === "8 h 30 min";
     *      Merkinta merkinta2 = new Merkinta("1:00", "9:15");
     *      unenmaara = new SimpleDateFormat("HH:mm");
     *      unenmaara.parse(merkinta2.getNukkumaanKlo());
     *      unenmaara.parse(merkinta2.getHeratysKlo());
     *      merkinta2.laskeUnenmaara() === "8 h 15 min";
     *  } catch(Exception e) {
     *      1 === 0;
     *  }
     * </pre>
     */
    public String laskeUnenmaara() throws Exception{                    //TODO: tee tämä ohjelma niin, että se toimii oikein, ja että
        SimpleDateFormat unenmaara = new SimpleDateFormat("HH:mm");     //      testit menee läpi!!!
        
        Date nukkumaan = unenmaara.parse(nukkumaanKlo);
        Date heratys = unenmaara.parse(heratysKlo);
        
        long eroMilliSek = Math.abs(heratys.getTime() - nukkumaan.getTime());
        
        long eroMin = (eroMilliSek / (60 * 1000)) % 60;
        
        long eroH = (eroMilliSek / (60 * 60 * 1000)) % 24;
        
        this.unenMaara = eroH + " h " + eroMin + " min";
        return "" + unenMaara.toString();
    }
    

    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Merkinta merkinta1 = new Merkinta(), pvm2 = new Merkinta();
        merkinta1.merkinnanLisays();
        pvm2.merkinnanLisays();
        merkinta1.tulosta(System.out);
        merkinta1.taytaM1Tiedoilla();
        merkinta1.tulosta(System.out);

        pvm2.taytaM2Tiedoilla();
        pvm2.tulosta(System.out);
    }

}

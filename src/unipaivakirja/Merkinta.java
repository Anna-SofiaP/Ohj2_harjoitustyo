/**
 * 
 */
package unipaivakirja;

import java.io.OutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import fi.jyu.mit.ohj2.Mjonot;

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
    private String unenlaatu = "";
    private String vireystila = "";
    
    private static int seuraavaNro = 1;
    
    
    /**
     * Oletusmuodostaja
     */
    public Merkinta() {
        //oletusmuodostaja
    }
    
    
    /**
     * Muodostaja
     * @param kayttajaid sen käyttäjän käyttäjä-id, jonka merkintä on kyseessä
     */
    public Merkinta(int kayttajaid) {
        this.kayttajaid = kayttajaid;
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
     * @return päivämäärä LocalDate tyyppinä
     */
    public LocalDate getPvmDate() {
        if (pvm == "" || pvm == null) return LocalDate.now();
        return LocalDate.parse(pvm);
    }

    
    /**
     * Apumetodi, jolla saadaan täytettyä testiarvot merkinnälle.
     * @param pvm1 merkinnän päivämäärä
     */
    public void taytaM1Tiedoilla(String pvm1) {
        //this.merkintaid = merkintanro;
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
        out.print("Unenlaatu: " + unenlaatu + " Vireystila: " + vireystila);
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
     * Pilkkoo merkinnän tiedot "|"-merkin kohdalta
     * @param rivi merkintä, jonka tiedot pilkotaan
     * @example
     * <pre name="test">
     *   Merkinta merkinta = new Merkinta(3);
     *   merkinta.parse("3|5|12.3.2015");
     *   merkinta.getMerkintaid() === 5;
     *   merkinta.toString().startsWith("3|5|12.3.2015|") === true; // on enemmäkin kuin 3 kenttää, siksi loppu | 
     * </pre>
     */
    public void parse(String rivi) {
        StringBuffer sb = new StringBuffer(rivi);
        kayttajaid = Mjonot.erota(sb, '|', kayttajaid);
        setMerkintaId(Mjonot.erota(sb, '|', getMerkintaid()));
        pvm = Mjonot.erota(sb, '|', pvm);
        nukkumaanKlo = (Mjonot.erota(sb, '|', nukkumaanKlo));
        heratysKlo = (Mjonot.erota(sb, '|', heratysKlo));
        unenMaara = (Mjonot.erota(sb, '|', unenMaara));
        lisatiedot = (Mjonot.erota(sb, '|', lisatiedot));
        unenlaatu = (Mjonot.erota(sb, '|', unenlaatu));
        vireystila = (Mjonot.erota(sb, '|', vireystila));
    }
    
    
    /**
     * Palauttaa merkinnän tiedot merkkijonona jonka voi tallentaa tiedostoon.
     * @return merkintä eroteltuna merkkijonona "|"-merkin kohdalta
     * @example
     * <pre name="test">
     *   Merkinta merkinta = new Merkinta();
     *   merkinta.parse("3|5|12.3.2015");
     *   merkinta.toString().startsWith("3|5|12.3.2015|") === true; // on enemmäkin kuin 3 kenttää, siksi loppu |
     * </pre>  
     */   
    @Override
    public String toString() {
        return "" +
                getKayttajaId() + "|" +
                getMerkintaid() + "|" +
                pvm + "|" +
                nukkumaanKlo + "|" +
                heratysKlo + "|" +
                unenMaara + "|" +
                lisatiedot + "|" +
                unenlaatu + "|" +
                vireystila;
    }

    
    
    /**
     * @param merkintanro merkinnän id-numero
     */
    public void setMerkintaId(int merkintanro) {
        merkintaid = merkintanro;
        if ( merkintaid >= seuraavaNro ) seuraavaNro = merkintaid + 1;

    }


    /**
     * laskee unen määrän
     * @param nukkumaan nukkumaanmenoaika textfieldistä
     * @param heratys heräämisaika textfieldistä
     * @return unen määrä
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
    public String laskeUnenmaara(String nukkumaan, String heratys) {                    //TODO: tee tämä ohjelma niin, että se toimii oikein, ja että
        String[] nukkumaanKlo = nukkumaan.split(":");
        String[] heratysKlo = heratys.split(":");
        
        int tunti1 = 0;
        int tunti2 = 0;
        
        if (nukkumaanKlo[0].length() == 1) tunti1 = Integer.parseInt(nukkumaanKlo[0]);
        else
            if (nukkumaanKlo[0].charAt(0) == '0') {
                String uusi = "" + nukkumaanKlo[0].charAt(1);
                tunti1 = Integer.parseInt(uusi);
            }
        tunti1 = Integer.parseInt(nukkumaanKlo[0]);
        
        if (heratysKlo[0].length() == 1) tunti2 = Integer.parseInt(heratysKlo[0]);
        else
            if (heratysKlo[0].charAt(0) == '0') {
                String uusi = "" + heratysKlo[0].charAt(1);
                tunti2 = Integer.parseInt(uusi);
            }
        tunti2 = Integer.parseInt(heratysKlo[0]);
        
        int minuutit1 = 0;
        int minuutit2 = 0;
        
        if (nukkumaanKlo[1].charAt(0) == '0') {
            String uusi = "" + nukkumaanKlo[1].charAt(1);
            minuutit1 = Integer.parseInt(uusi);
        }
        minuutit1 = Integer.parseInt(nukkumaanKlo[1]);
        
        if (heratysKlo[1].charAt(0) == '0') {
            String uusi = "" + heratysKlo[1].charAt(1);
            minuutit2 = Integer.parseInt(uusi);
        }
        minuutit2 = Integer.parseInt(heratysKlo[1]);
        
        int tunnit = 0;
        int minuutit = 0;
        
        if (minuutit1 == 0) {                    //Jos menee tasatunteina nukkumaan
            if (tunti1 > tunti2) {                  //Jos menee nukkumaan ennen puoltayötä
                minuutit = minuutit2;
                tunnit = 24 - tunti1 + tunti2;
            }
            else if (tunti1 < tunti2) {               //Jos menee nukkumaan puolen yön jälkeen
                minuutit = minuutit2;
                tunnit = tunti2 - tunti1;
            }
        }
        else if (minuutit1 > 0) {                     //Jos EI mene tasatunteina nukkumaan
            if (tunti1 > tunti2) {                  //Jos menee nukkumaan ennen puoltayötä
                minuutit = 60 - minuutit1;
                tunnit = 24 - tunti1 + tunti2 - 1;
                if (minuutit2 > 0) {                     //Jos EI herää tasatunteina
                    minuutit += minuutit2;
                    if (minuutit > 60) {                      //Jos minuutit menee yli 60
                        tunnit++;
                        minuutit -= 60;
                    }
                }
            }
            else if (tunti1 < tunti2) {             //Jos menee nukkumaan puolen yön jälkeen
                if (minuutit1 < minuutit2) {
                    tunnit = tunti2 - tunti1;
                    minuutit = minuutit2 - minuutit1;
                }
                else if (minuutit2 < minuutit1) {
                    minuutit = minuutit1 + minuutit2 - 60;
                    tunnit = tunti2 - tunti1 + 1;
                }
            }
        }

        return "" + tunnit + " h " + minuutit + " min" ;
        
    }
    
    
    /**
     * Tehdään identtinen klooni merkinnästä
     * @return Object kloonattu merkintä
     * @example
     * <pre name="test">
     * #THROWS CloneNotSupportedException 
     *   Jasen jasen = new Jasen();
     *   jasen.parse("   3  |  Ankka Aku   | 123");
     *   Jasen kopio = jasen.clone();
     *   kopio.toString() === jasen.toString();
     *   jasen.parse("   4  |  Ankka Tupu   | 123");
     *   kopio.toString().equals(jasen.toString()) === false;
     * </pre>
     */
    @Override
    public Merkinta clone() throws CloneNotSupportedException {
        Merkinta uusi;
        uusi = (Merkinta) super.clone();
        return uusi;
    }

    

    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Merkinta merkinta1 = new Merkinta(), pvm2 = new Merkinta();
        merkinta1.merkinnanLisays();
        pvm2.merkinnanLisays();
        merkinta1.tulosta(System.out);
        //merkinta1.taytaM1Tiedoilla(5);
        merkinta1.tulosta(System.out);

        pvm2.taytaM2Tiedoilla();
        pvm2.tulosta(System.out);
        
        /*System.out.println(laskeUnenmaara("21:20", "07:15"));   //9 h 55 min
        System.out.println(laskeUnenmaara("1:00", "10:45"));    //9 h 45 min
        System.out.println(laskeUnenmaara("22:00", "2:00"));    //4 h 0 min
        System.out.println(laskeUnenmaara("5:10", "09:55"));    //4 h 45 min*/
    }


    /**
     * @return unipäiväkirjamerkinnän lisätiedot
     */
    public String getLisatiedot() {
        return this.lisatiedot;
    }


    public String setNukkumaanKlo(String s) {
        nukkumaanKlo = s;
        return null;
    }


    public String setHeratysKlo(String s) {
        heratysKlo = s;
        return null;
    }


    public String setUnenmaara(String s) {
        unenMaara = s;
        return null;
    }


    public String setLisatiedot(String s) {
        lisatiedot = s;
        return null;
    }


    public String setPvmDate(String s) {
        pvm = s;
        return null;
    }


    public String setUnenlaatu(String s) {
        unenlaatu = s;
        return null;
    }


    public String setVireystila(String s) {
        vireystila = s;
        return null;
    }


    public int getKenttia() {
        return 3;
    }
    
    
    /**
     * Eka kenttä joka on mielekäs kysyttäväksi
     * @return ekan kentän indeksi
     */
    public int ekaKentta() {
        return 1;
    }


    public String getKysymys(int k) {
        switch ( k ) {
        case 0: return "Nukkumaanmenoaika";
        case 1: return "Heräämisaika";
        case 2: return "Unen määrä";
        default: return "Äääliö";
        }
    }


    public String getUnenmaara() {
        return this.unenMaara;
    }


    public String getUnenlaatu() {
        return this.unenlaatu;
    }


    public String getVireystila() {
        return this.vireystila;
    }
    

}

/**
 * 
 */
package unipaivakirja;

/**
 * Unipaivakirja-luokka, joka huolehtii käyttäjistä.
 * @author Omistaja
 * @version 8.3.2022
 *
 */
public class Unipaivakirja {
    private final Kayttajat kayttajat = new Kayttajat();
    private final Merkinnat merkinnat = new Merkinnat();
    
    
    /**
     * Palautaa unipäiväkirjan käyttäjien määrän
     * @return jäsenmäärä
     */
    public int getKayttajia() {
        return kayttajat.getLkm();
    }
    
    
    /**
     * Poistaa käyttäjistä ne joilla on nro. Kesken.
     * @param nro sen käyttäjän id-numero, joka poistetaan
     * @return montako jäsentä poistettiin
     */
    public int poista(@SuppressWarnings("unused") int nro) {
        return 0;
    }
    
    
    /**
     * Lisää unipäiväkirjaan uuden käyttäjän
     * @param kayttaja lisättävä käyttäjä
     * @example
     * <pre name="test">
     * Unipaivakirja unipaivakirja = new Unipaivakirja();
     * Kayttaja nea = new Kayttaja("Nea"), ansku = new Kayttaja("Ansku");
     * nea.rekisteroi(); ansku.rekisteroi();
     * unipaivakirja.getKayttajia() === 0;
     * unipaivakirja.lisaa(nea); unipaivakirja.getKayttajia() === 1;
     * unipaivakirja.lisaa(ansku); unipaivakirja.getKayttajia() === 2;
     * unipaivakirja.lisaa(nea); unipaivakirja.getKayttajia() === 3;
     * unipaivakirja.getKayttajia() === 3;
     * unipaivakirja.annaKayttaja(0) === nea;
     * unipaivakirja.annaKayttaja(1) === ansku;
     * unipaivakirja.annaKayttaja(2) === nea;
     * unipaivakirja.annaKayttaja(3) === nea; #THROWS IndexOutOfBoundsException 
     * unipaivakirja.lisaa(nea); unipaivakirja.getKayttajia() === 4;
     * unipaivakirja.lisaa(nea); unipaivakirja.getKayttajia() === 5;
     * unipaivakirja.lisaa(nea);
     * </pre>
     */
    public void lisaa(Kayttaja kayttaja) {
        kayttajat.lisaa(kayttaja);
    }
    
    
    /**
     * Palauttaa i:n käyttäjän
     * @param i monesko käyttäjä palautetaan
     * @return viite i:teen käyttäjään
     * @throws IndexOutOfBoundsException jos i väärin
     * @exmaple
     * <pre name="test">
     *  Unipaivakirja unipaivakirja = new Unipaivakirja();
     *  Kayttaja nea = new Kayttaja("Nea"), ansku = new Kayttaja("Ansku");
     *  nea.rekisteroi();
     *  nea.rekisteroi();
     *  unipaivakirja.lisaa(nea);
     *  unipaivakirja.lisaa(ansku);
     *  unipaivakirja.annaKayttaja(0) === nea;
     *  unipaivakirja.annaKayttaja(1) === ansku;
     *  unipaivakirja.annaKayttaja(5); #THROWS IndexOutOfBoundsException
     * </pre>
     */
    public Kayttaja annaKayttaja(int i) throws IndexOutOfBoundsException {
        if (i < 0 || kayttajat.getLkm() <= i)
            throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        return kayttajat.anna(i);
    }
    
    
    /**
     * Lukee unipäiväkirjan tiedot tiedostosta
     * @param kayttaja jota käyteään lukemisessa
     * @throws SailoException jos lukeminen epäonnistuu
     */
    public void lueTiedostosta(String kayttaja) throws SailoException {
        kayttajat.lueTiedostosta(kayttaja);
    }
    
    
    /**
     * Tallettaa unipäiväkirjan tiedot tiedostoon
     * @throws SailoException jos tallettamisessa ongelmia
     */
    public void talleta() throws SailoException {
        kayttajat.talleta();
        // TODO: yritä tallettaa toinen vaikka toinen epäonnistuisi
    }
    

    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Unipaivakirja unipaivakirja = new Unipaivakirja();

        Kayttaja nea = new Kayttaja("nea"), ansku = new Kayttaja("ansku");
        nea.rekisteroi();
        nea.taytaNeaTiedoilla();
        ansku.rekisteroi();
        ansku.taytaAnskuTiedoilla();

        unipaivakirja.lisaa(nea);
        unipaivakirja.lisaa(ansku);

        System.out.println("============= Kerhon testi =================");

        for (int i = 0; i < unipaivakirja.getKayttajia(); i++) {
            Kayttaja kayttaja = unipaivakirja.annaKayttaja(i);
            System.out.println("Käyttäjä paikassa: " + i);
            kayttaja.tulosta(System.out);
        }
    }


    public Kayttaja asetaKayttaja(String selectedText) {
        return kayttajat.aseta(selectedText);
    }
    
    
    

}

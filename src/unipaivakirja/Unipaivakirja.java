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
     * @throws SailoException jos lisäystä ei voida tehdä
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * Unipaivakirja unipaivakirja = new Unipaivakirja();
     * Kayttaja nea = new Kayttaja(), anksu = new Kayttaja();
     * nea.rekisteroi(); ansku.rekisteroi();
     * unipaivakirja.getKayttajia() === 0;
     * unipaivakirja.lisaa(nea); unipaivakirja.getKayttajia() === 1;
     * unipaivakirja.lisaa(anksu); unipaivakirja.getKayttajia() === 2;
     * unipaivakirja.lisaa(nea); unipaivakirja.getKayttajia() === 3;
     * unipaivakirja.getKayttajia() === 3;
     * unipaivakirja.annaKayttaja(0) === nea;
     * unipaivakirja.annaKayttaja(1) === anksu;
     * unipaivakirja.annaKayttaja(2) === nea;
     * unipaivakirja.annaKayttaja(3) === nea; #THROWS IndexOutOfBoundsException 
     * unipaivakirja.lisaa(nea); kerho.getKayttajia() === 4;
     * unipaivakirja.lisaa(nea); kerho.getKayttajia() === 5;
     * unipaivakirja.lisaa(nea);            #THROWS SailoException
     * </pre>
     */
    public void lisaa(Kayttaja kayttaja) throws SailoException {
        kayttajat.lisaa(kayttaja);
    }
    
    
    /**
     * Palauttaa i:n käyttäjän
     * @param i monesko käyttäjä palautetaan
     * @return viite i:teen käyttäjään
     * @throws IndexOutOfBoundsException jos i väärin
     */
    public Kayttaja annaKayttaja(int i) throws IndexOutOfBoundsException {
        return kayttajat.anna(i);
    }
    
    
    /**
     * Lukee unipäiväkirjan tiedot tiedostosta
     * @param nimi jota käyteään lukemisessa
     * @throws SailoException jos lukeminen epäonnistuu
     */
    public void lueTiedostosta(String nimi) throws SailoException {
        kayttajat.lueTiedostosta(nimi);
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

        try {
            // kerho.lueTiedostosta("kelmit");

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

        } catch (SailoException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public void asetaKayttaja(String selectedText) {
        // TODO Auto-generated method stub
        kayttajat.aseta(selectedText);
    }
    
    
    

}

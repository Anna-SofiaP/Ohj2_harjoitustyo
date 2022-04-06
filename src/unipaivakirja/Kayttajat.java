/**
 * 
 */
package unipaivakirja;

/**
 * @author Omistaja
 * @version 2.3.2022
 *
 */
public class Kayttajat {
    private static final int MAX_KAYTTAJIA = 5;
    private int lkm = 3;
    private String tiedostonimi = "";
    private Kayttaja alkiot[] = new Kayttaja[MAX_KAYTTAJIA];
    
    
    /**
     * Oletusmuodostaja
     */
    public Kayttajat() {
        //Attribuuttien oma alustus riittää
    }
    
    
    /**
     * Lisää uuden käyttäjän tietorakenteeseen.
     * @param kayttaja lisättävän käyttäjän viite
     * @throws SailoException jos tietorakenne on jo täynnä
     * @example
     * #THROWS SailoException
     * <pre name="test">
     * Kayttajat kayttajat = new Kayttajat();
     * Kayttaja nea = new Kayttaja("Nea"), ansku = new Kayttaja("Ansku");
     * kayttajat.getLkm() === 3;
     * kayttajat.lisaa(nea); kayttajat.getLkm() === 1;
     * kayttajat.lisaa(ansku); kayttajat.getLkm() === 2;
     * kayttajat.lisaa(nea); kayttajat.getLkm() === 3;
     * kayttajat.anna(0) === nea;
     * kayttajat.anna(1) === ansku;
     * kayttajat.anna(2) === nea;
     * kayttajat.anna(1) == nea === false;
     * kayttajat.anna(1) == ansku === true;
     * kayttajat.anna(3) === nea; #THROWS IndexOutOfBoundsException
     * kayttajat.lisaa(nea); kayttajat.getLkm() === 4;
     * kayttajat.lisaa(nea); kayttajat.getLkm() === 5;
     * kayttajat.lisaa(nea); #THROWS SailoException
     * </pre>
     */
    public void lisaa(Kayttaja kayttaja) throws SailoException {
        if (lkm >= alkiot.length) throw new SailoException("Liikaa alkioita");
        alkiot[lkm] = kayttaja;
        lkm++;
    }
    
    
    /**
     * Palauttaa viitteen i:nteen käyttäjään
     * @param i monennenko käyttäjän viite halutaan
     * @return viite käyttäjään, jonka indeksi listassa alkiot on i
     * @throws IndexOutOfBoundsException jos indeksi i ei ole sallittu 
     */
    public Kayttaja anna(int i) throws IndexOutOfBoundsException {
        if (i < 0 || lkm <= i )
            throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        return alkiot[i];
    }
    
    
    /**
     * Lukee käyttäjän tiedostosta
     * @param hakemisto tiedoston hakemisto
     * @throws SailoException jos lukeminen epäonnistuu
     */
    public void lueTiedostosta(String hakemisto) throws SailoException {
        tiedostonimi = hakemisto + "/kayttajat.dat";
        throw new SailoException("Ei osata vielä lukea tiedostoa " + tiedostonimi);
    }
    
    
    /**
     * Tallentaa käyttäjät tiedostoon
     * @throws SailoException jos talletus epäonnistuu
     */
    public void talleta() throws SailoException {
        throw new SailoException("Ei osata vielä lukea tiedostoa " + tiedostonimi);
    }
    
    
    /**
     * Palauttaa unipäiväkirjan käyttäjien lukumäärän
     * @return käyttäjien lukumäärä
     */
    public int getLkm() {
        return lkm;
    }
    

    /**
     * Testiohjelma
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Kayttajat kayttajat = new Kayttajat();
        
        Kayttaja nea = new Kayttaja("nea"), ansku = new Kayttaja("ansku");
        nea.rekisteroi();
        nea.taytaNeaTiedoilla();
        ansku.rekisteroi();
        ansku.taytaAnskuTiedoilla();
        
        try {
            kayttajat.lisaa(nea);
            kayttajat.lisaa(ansku);
            
            System.out.println("Käyttäjät testi");
            
            for (int i = 0; i < kayttajat.getLkm(); i++) {
                Kayttaja kayttaja = kayttajat.anna(i);
                System.out.println("Käyttäjä nro: " + i);
                kayttaja.tulosta(System.out);
            }
            
        } catch (SailoException ex) {
            System.out.println(ex.getMessage());
        }
    
    }


    /*public void aseta(String selectedText) {
        // TODO Auto-generated method stub
        Kayttaja uusi = new Kayttaja(selectedText);
    }*/

}

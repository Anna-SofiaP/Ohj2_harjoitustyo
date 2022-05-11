package unipaivakirja.test;
// Generated by ComTest BEGIN
import java.io.File;
import java.util.Iterator;
import unipaivakirja.*;
import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2022.05.11 12:39:29 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class MerkinnatTest {



  // Generated by ComTest BEGIN
  /** testLisaa49 */
  @Test
  public void testLisaa49() {    // Merkinnat: 49
    Merkinnat merkinnat = new Merkinnat(); 
    Merkinta pvm1 = new Merkinta(), pvm2 = new Merkinta(); 
    assertEquals("From: Merkinnat line: 52", 0, merkinnat.getLkm()); 
    merkinnat.lisaa(pvm1); assertEquals("From: Merkinnat line: 53", 1, merkinnat.getLkm()); 
    merkinnat.lisaa(pvm2); assertEquals("From: Merkinnat line: 54", 2, merkinnat.getLkm()); 
    merkinnat.lisaa(pvm1); assertEquals("From: Merkinnat line: 55", 3, merkinnat.getLkm()); 
    assertEquals("From: Merkinnat line: 56", pvm1, merkinnat.anna(0)); 
    assertEquals("From: Merkinnat line: 57", pvm2, merkinnat.anna(1)); 
    assertEquals("From: Merkinnat line: 58", pvm1, merkinnat.anna(2)); 
    assertEquals("From: Merkinnat line: 59", false, merkinnat.anna(1) == pvm1); 
    assertEquals("From: Merkinnat line: 60", true, merkinnat.anna(1) == pvm2); 
    try {
    assertEquals("From: Merkinnat line: 61", pvm1, merkinnat.anna(3)); 
    fail("Merkinnat: 61 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
    merkinnat.lisaa(pvm1); assertEquals("From: Merkinnat line: 62", 4, merkinnat.getLkm()); 
    merkinnat.lisaa(pvm1); assertEquals("From: Merkinnat line: 63", 5, merkinnat.getLkm()); 
    merkinnat.lisaa(pvm1); assertEquals("From: Merkinnat line: 64", 6, merkinnat.getLkm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testKasvataTaulukkoa93 */
  @Test
  public void testKasvataTaulukkoa93() {    // Merkinnat: 93
    Merkinnat merkinnat = new Merkinnat(); 
    Merkinta pvm1 = new Merkinta(), pvm2 = new Merkinta(), pvm3 = new Merkinta(); 
    merkinnat.lisaa(pvm1); assertEquals("From: Merkinnat line: 96", 1, merkinnat.getLkm()); 
    merkinnat.lisaa(pvm2); assertEquals("From: Merkinnat line: 97", 2, merkinnat.getLkm()); 
    merkinnat.lisaa(pvm3); assertEquals("From: Merkinnat line: 98", 3, merkinnat.getLkm()); 
    merkinnat.lisaa(pvm1); assertEquals("From: Merkinnat line: 99", 4, merkinnat.getLkm()); 
    merkinnat.lisaa(pvm3); assertEquals("From: Merkinnat line: 100", 5, merkinnat.getLkm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testLueTiedostosta130 
   * @throws SailoException when error
   */
  @Test
  public void testLueTiedostosta130() throws SailoException {    // Merkinnat: 130
    Merkinnat merkinnat = new Merkinnat(); 
    Merkinta merk1 = new Merkinta(), merk2 = new Merkinta(); 
    merk1.taytaM1Tiedoilla(); 
    merk2.taytaM2Tiedoilla(); 
    String hakemisto = "testiKayttajat"; 
    String tiedNimi = hakemisto+"/tmerkinnat"; 
    File ftied = new File(tiedNimi+".dat"); 
    File dir = new File(hakemisto); 
    dir.mkdir(); 
    ftied.delete(); 
    try {
    merkinnat.lueTiedostosta(tiedNimi); 
    fail("Merkinnat: 145 Did not throw SailoException");
    } catch(SailoException _e_){ _e_.getMessage(); }
    merkinnat.lisaa(merk1); 
    merkinnat.lisaa(merk2); 
    merkinnat.talleta(); 
    merkinnat = new Merkinnat();  // Poistetaan vanhat luomalla uusi
    merkinnat.lueTiedostosta(tiedNimi);  // johon ladataan tiedot tiedostosta.
    Iterator<Merkinta> i = merkinnat.iterator(); 
    assertEquals("From: Merkinnat line: 152", merk1.toString(), i.next().toString()); 
    assertEquals("From: Merkinnat line: 153", merk2.toString(), i.next().toString()); 
    assertEquals("From: Merkinnat line: 154", false, i.hasNext()); 
    merkinnat.lisaa(merk1); 
    merkinnat.talleta(); 
    assertEquals("From: Merkinnat line: 157", true, ftied.delete()); 
    File fbak = new File(tiedNimi+".bak"); 
    assertEquals("From: Merkinnat line: 159", true, fbak.delete()); 
    assertEquals("From: Merkinnat line: 160", true, dir.delete()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testMerkinnatIterator305 
   * @throws SailoException when error
   */
  @Test
  public void testMerkinnatIterator305() throws SailoException {    // Merkinnat: 305
    Merkinnat merkinnat = new Merkinnat(); 
    Merkinta merk1 = new Merkinta(), merk2 = new Merkinta(); 
    merk1.merkinnanLisays(); merk2.merkinnanLisays(); 
    merkinnat.lisaa(merk1); 
    merkinnat.lisaa(merk2); 
    merkinnat.lisaa(merk1); 
    StringBuffer ids = new StringBuffer(30); 
    for (Merkinta merkinta:merkinnat) // Kokeillaan for-silmukan toimintaa
    ids.append(" "+merkinta.getMerkintaid()); 
    String tulos = " " + merk1.getMerkintaid() + " " + merk2.getMerkintaid() + " " + merk1.getMerkintaid(); 
    assertEquals("From: Merkinnat line: 324", tulos, ids.toString()); 
    ids = new StringBuffer(30); 
    for (Iterator<Merkinta>  i=merkinnat.iterator(); i.hasNext(); ) { // ja iteraattorin toimintaa
    Merkinta merkinta = i.next(); 
    ids.append(" "+merkinta.getMerkintaid()); 
    }
    assertEquals("From: Merkinnat line: 332", tulos, ids.toString()); 
    Iterator<Merkinta>  i=merkinnat.iterator(); 
    assertEquals("From: Merkinnat line: 335", true, i.next() == merk1); 
    assertEquals("From: Merkinnat line: 336", true, i.next() == merk2); 
    assertEquals("From: Merkinnat line: 337", true, i.next() == merk1); 
    try {
    i.next(); 
    fail("Merkinnat: 339 Did not throw NoSuchElementException");
    } catch(NoSuchElementException _e_){ _e_.getMessage(); }
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testPoista414 
   * @throws SailoException when error
   */
  @Test
  public void testPoista414() throws SailoException {    // Merkinnat: 414
    Merkinnat merkinnat = new Merkinnat(); 
    Merkinta pvm1 = new Merkinta(), pvm2 = new Merkinta(), pvm3 = new Merkinta(); 
    pvm1.merkinnanLisays(); pvm2.merkinnanLisays(); pvm3.merkinnanLisays(); 
    int id1 = pvm1.getMerkintaid(); 
    merkinnat.lisaa(pvm1); merkinnat.lisaa(pvm2); merkinnat.lisaa(pvm3); 
    assertEquals("From: Merkinnat line: 421", 1, merkinnat.poista(id1+1)); assertEquals("From: Merkinnat line: 421", 2, merkinnat.getLkm()); 
    assertEquals("From: Merkinnat line: 422", 1, merkinnat.poista(id1)); assertEquals("From: Merkinnat line: 422", 1, merkinnat.getLkm()); 
    assertEquals("From: Merkinnat line: 423", 0, merkinnat.poista(id1+3)); assertEquals("From: Merkinnat line: 423", 1, merkinnat.getLkm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testEtsiId443 
   * @throws SailoException when error
   */
  @Test
  public void testEtsiId443() throws SailoException {    // Merkinnat: 443
    Merkinnat merkinnat = new Merkinnat(); 
    Merkinta pvm1 = new Merkinta(), pvm2 = new Merkinta(), pvm3 = new Merkinta(); 
    pvm1.merkinnanLisays(); pvm2.merkinnanLisays(); pvm3.merkinnanLisays(); 
    int id1 = pvm1.getMerkintaid(); 
    merkinnat.lisaa(pvm1); merkinnat.lisaa(pvm2); merkinnat.lisaa(pvm3); 
    assertEquals("From: Merkinnat line: 450", 1, merkinnat.etsiId(id1+1)); 
    assertEquals("From: Merkinnat line: 451", 2, merkinnat.etsiId(id1+2)); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testPoistaKayttajanMerkinnat465 
   * @throws SailoException when error
   */
  @Test
  public void testPoistaKayttajanMerkinnat465() throws SailoException {    // Merkinnat: 465
    Merkinnat merkinnat = new Merkinnat(); 
    Merkinta pvm1 = new Merkinta(1), pvm2 = new Merkinta(1), pvm3 = new Merkinta(1); 
    pvm1.merkinnanLisays(); pvm2.merkinnanLisays(); pvm3.merkinnanLisays(); 
    int id1 = -1; 
    merkinnat.lisaa(pvm1); merkinnat.lisaa(pvm2); merkinnat.lisaa(pvm3); 
    assertEquals("From: Merkinnat line: 472", 0, merkinnat.poistaKayttajanMerkinnat(id1)); 
    assertEquals("From: Merkinnat line: 473", 3, merkinnat.getLkm()); 
    id1 = pvm1.getKayttajaId(); 
    assertEquals("From: Merkinnat line: 475", 1, merkinnat.poistaKayttajanMerkinnat(id1)); 
    assertEquals("From: Merkinnat line: 476", 0, merkinnat.getLkm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testEtsiKayttajaId513 
   * @throws SailoException when error
   */
  @Test
  public void testEtsiKayttajaId513() throws SailoException {    // Merkinnat: 513
    Merkinnat merkinnat = new Merkinnat(); 
    Merkinta pvm1 = new Merkinta(1), pvm2 = new Merkinta(2), pvm3 = new Merkinta(2); 
    pvm1.merkinnanLisays(); pvm2.merkinnanLisays(); pvm3.merkinnanLisays(); 
    int id1 = pvm1.getKayttajaId(); 
    merkinnat.lisaa(pvm1); merkinnat.lisaa(pvm2); merkinnat.lisaa(pvm3); 
    assertEquals("From: Merkinnat line: 520", 0, merkinnat.etsiKayttajaId(id1)); 
    assertEquals("From: Merkinnat line: 521", 1, merkinnat.etsiKayttajaId(id1+1)); 
    assertEquals("From: Merkinnat line: 522", -1, merkinnat.etsiKayttajaId(id1+2)); 
    assertEquals("From: Merkinnat line: 523", -1, merkinnat.etsiKayttajaId(id1+5)); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testEtsi539 
   * @throws SailoException when error
   */
  @Test
  public void testEtsi539() throws SailoException {    // Merkinnat: 539
    Merkinnat merkinnat = new Merkinnat(); 
    Merkinta merk1 = new Merkinta(); merk1.parse("1|1|2022-03-08|"); 
    Merkinta merk2 = new Merkinta(); merk2.parse("1|2|2022-03-11|"); 
    Merkinta merk3 = new Merkinta(); merk3.parse("2|3|2022-03-12|"); 
    Merkinta merk4 = new Merkinta(); merk4.parse("1|4|2022-03-03|"); 
    Merkinta merk5 = new Merkinta(); merk5.parse("1|5|2022-03-15|"); 
    merkinnat.lisaa(merk1); merkinnat.lisaa(merk2); merkinnat.lisaa(merk3); merkinnat.lisaa(merk4); merkinnat.lisaa(merk5); 
    List<Merkinta> loytyneet; 
    loytyneet = (List<Merkinta>)merkinnat.etsi("12", 2); 
    assertEquals("From: Merkinnat line: 550", 1, loytyneet.size()); 
    assertEquals("From: Merkinnat line: 551", true, loytyneet.get(0) == merk3); 
    loytyneet = (List<Merkinta>)merkinnat.etsi("1", 1); 
    assertEquals("From: Merkinnat line: 554", 2, loytyneet.size()); 
    assertEquals("From: Merkinnat line: 555", true, loytyneet.get(0) == merk2); 
    assertEquals("From: Merkinnat line: 556", true, loytyneet.get(1) == merk5); 
    loytyneet = (List<Merkinta>)merkinnat.etsi("2022-03-03", 1); 
    assertEquals("From: Merkinnat line: 559", 1, loytyneet.size()); 
    assertEquals("From: Merkinnat line: 560", true, loytyneet.get(0) == merk4); 
    loytyneet = (List<Merkinta>)merkinnat.etsi(null, 1); 
    assertEquals("From: Merkinnat line: 563", 4, loytyneet.size()); 
  } // Generated by ComTest END
}
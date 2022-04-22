package unipaivakirja.test;
// Generated by ComTest BEGIN
import java.io.*;
import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;
import unipaivakirja.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2022.04.20 14:51:18 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class UnipaivakirjaTest {


  // Generated by ComTest BEGIN
  /** testLisaa44 */
  @Test
  public void testLisaa44() {    // Unipaivakirja: 44
    Unipaivakirja unipaivakirja = new Unipaivakirja(); 
    Kayttaja nea = new Kayttaja("Nea"), ansku = new Kayttaja("Ansku"); 
    nea.rekisteroi(); ansku.rekisteroi(); 
    assertEquals("From: Unipaivakirja line: 48", 0, unipaivakirja.getKayttajia()); 
    unipaivakirja.lisaa(nea); assertEquals("From: Unipaivakirja line: 49", 1, unipaivakirja.getKayttajia()); 
    unipaivakirja.lisaa(ansku); assertEquals("From: Unipaivakirja line: 50", 2, unipaivakirja.getKayttajia()); 
    unipaivakirja.lisaa(nea); assertEquals("From: Unipaivakirja line: 51", 3, unipaivakirja.getKayttajia()); 
    assertEquals("From: Unipaivakirja line: 52", 3, unipaivakirja.getKayttajia()); 
    assertEquals("From: Unipaivakirja line: 53", nea, unipaivakirja.annaKayttaja(0)); 
    assertEquals("From: Unipaivakirja line: 54", ansku, unipaivakirja.annaKayttaja(1)); 
    assertEquals("From: Unipaivakirja line: 55", nea, unipaivakirja.annaKayttaja(2)); 
    try {
    assertEquals("From: Unipaivakirja line: 56", nea, unipaivakirja.annaKayttaja(3)); 
    fail("Unipaivakirja: 56 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
    unipaivakirja.lisaa(nea); assertEquals("From: Unipaivakirja line: 57", 4, unipaivakirja.getKayttajia()); 
    unipaivakirja.lisaa(nea); assertEquals("From: Unipaivakirja line: 58", 5, unipaivakirja.getKayttajia()); 
    unipaivakirja.lisaa(nea); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAnnaKayttaja73 */
  @Test
  public void testAnnaKayttaja73() {    // Unipaivakirja: 73
    Unipaivakirja unipaivakirja = new Unipaivakirja(); 
    Kayttaja nea = new Kayttaja("Nea"), ansku = new Kayttaja("Ansku"); 
    nea.rekisteroi(); 
    nea.rekisteroi(); 
    unipaivakirja.lisaa(nea); 
    unipaivakirja.lisaa(ansku); 
    assertEquals("From: Unipaivakirja line: 80", nea, unipaivakirja.annaKayttaja(0)); 
    assertEquals("From: Unipaivakirja line: 81", ansku, unipaivakirja.annaKayttaja(1)); 
    try {
    unipaivakirja.annaKayttaja(5); 
    fail("Unipaivakirja: 82 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testLueTiedostosta107 
   * @throws SailoException when error
   */
  @Test
  public void testLueTiedostosta107() throws SailoException {    // Unipaivakirja: 107
    Unipaivakirja unip = new Unipaivakirja(); 
    Kayttaja nea = new Kayttaja(); nea.taytaNeaTiedoilla(); nea.rekisteroi(); 
    Kayttaja ansku = new Kayttaja(); ansku.taytaAnskuTiedoilla(); ansku.rekisteroi(); 
    Merkinta merk1 = new Merkinta(); merk1.taytaM1Tiedoilla(); 
    Merkinta merk2 = new Merkinta(); merk2.taytaM2Tiedoilla(); 
    Merkinta merk3 = new Merkinta(); merk3.taytaM1Tiedoilla(); 
    Merkinta merk4 = new Merkinta(); merk4.taytaM2Tiedoilla(); 
    Merkinta merk5 = new Merkinta(); merk5.taytaM1Tiedoilla(); 
    String hakemisto = "testiKayttajat"; 
    File dir = new File(hakemisto); 
    File ftied  = new File(hakemisto+"/tkayttajat.dat"); 
    File fhtied = new File(hakemisto+"/tmerkinnat.dat"); 
    dir.mkdir(); 
    ftied.delete(); 
    fhtied.delete(); 
    try {
    unip.lueTiedostosta(hakemisto); 
    fail("Unipaivakirja: 129 Did not throw SailoException");
    } catch(SailoException _e_){ _e_.getMessage(); }
    unip.lisaa(nea); 
    unip.lisaa(ansku); 
    unip.lisaa(merk1); 
    unip.lisaa(merk2); 
    unip.lisaa(merk3); 
    unip.lisaa(merk4); 
    unip.lisaa(merk5); 
    unip.talleta(); 
    unip = new Unipaivakirja(); 
    unip.lueTiedostosta(hakemisto); 
    Collection<Kayttaja> kaikki = unip.annaKayttajat(); 
    Iterator<Kayttaja> it = kaikki.iterator(); 
    assertEquals("From: Unipaivakirja line: 142", nea, it.next()); 
    assertEquals("From: Unipaivakirja line: 143", ansku, it.next()); 
    assertEquals("From: Unipaivakirja line: 144", false, it.hasNext()); 
    List<Merkinta> loytyneet = unip.annaKayttajanMerkinnat(1); 
    Iterator<Merkinta> ih = loytyneet.iterator(); 
    assertEquals("From: Unipaivakirja line: 147", merk1, ih.next()); 
    assertEquals("From: Unipaivakirja line: 148", merk2, ih.next()); 
    assertEquals("From: Unipaivakirja line: 149", false, ih.hasNext()); 
    loytyneet = unip.annaKayttajanMerkinnat(2); 
    ih = loytyneet.iterator(); 
    assertEquals("From: Unipaivakirja line: 152", merk3, ih.next()); 
    assertEquals("From: Unipaivakirja line: 153", merk4, ih.next()); 
    assertEquals("From: Unipaivakirja line: 154", merk5, ih.next()); 
    assertEquals("From: Unipaivakirja line: 155", false, ih.hasNext()); 
    unip.lisaa(ansku); 
    unip.lisaa(merk3); 
    unip.talleta(); 
    assertEquals("From: Unipaivakirja line: 159", true, ftied.delete()); 
    assertEquals("From: Unipaivakirja line: 160", true, fhtied.delete()); 
    File fbak = new File(hakemisto+"/tkayttajat.bak"); 
    File fhbak = new File(hakemisto+"/tmerkinnat.bak"); 
    assertEquals("From: Unipaivakirja line: 163", true, fbak.delete()); 
    assertEquals("From: Unipaivakirja line: 164", true, fhbak.delete()); 
    assertEquals("From: Unipaivakirja line: 165", true, dir.delete()); 
  } // Generated by ComTest END
}
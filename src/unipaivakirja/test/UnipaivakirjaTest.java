package unipaivakirja.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import java.util.Collection;
import java.util.Iterator;
import unipaivakirja.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2022.05.11 13:08:24 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class UnipaivakirjaTest {



  // Generated by ComTest BEGIN
  /** testLisaa34 */
  @Test
  public void testLisaa34() {    // Unipaivakirja: 34
    Unipaivakirja unipaivakirja = new Unipaivakirja(); 
    Kayttaja nea = new Kayttaja("Nea"), ansku = new Kayttaja("Ansku"); 
    nea.rekisteroi(); ansku.rekisteroi(); 
    assertEquals("From: Unipaivakirja line: 38", 0, unipaivakirja.getKayttajia()); 
    unipaivakirja.lisaa(nea); assertEquals("From: Unipaivakirja line: 39", 1, unipaivakirja.getKayttajia()); 
    unipaivakirja.lisaa(ansku); assertEquals("From: Unipaivakirja line: 40", 2, unipaivakirja.getKayttajia()); 
    unipaivakirja.lisaa(nea); assertEquals("From: Unipaivakirja line: 41", 3, unipaivakirja.getKayttajia()); 
    assertEquals("From: Unipaivakirja line: 42", 3, unipaivakirja.getKayttajia()); 
    assertEquals("From: Unipaivakirja line: 43", nea, unipaivakirja.annaKayttaja(0)); 
    assertEquals("From: Unipaivakirja line: 44", ansku, unipaivakirja.annaKayttaja(1)); 
    assertEquals("From: Unipaivakirja line: 45", nea, unipaivakirja.annaKayttaja(2)); 
    try {
    assertEquals("From: Unipaivakirja line: 46", nea, unipaivakirja.annaKayttaja(3)); 
    fail("Unipaivakirja: 46 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
    unipaivakirja.lisaa(nea); assertEquals("From: Unipaivakirja line: 47", 4, unipaivakirja.getKayttajia()); 
    unipaivakirja.lisaa(nea); assertEquals("From: Unipaivakirja line: 48", 5, unipaivakirja.getKayttajia()); 
    unipaivakirja.lisaa(nea); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAnnaKayttaja63 */
  @Test
  public void testAnnaKayttaja63() {    // Unipaivakirja: 63
    Unipaivakirja unipaivakirja = new Unipaivakirja(); 
    Kayttaja nea = new Kayttaja("Nea"), ansku = new Kayttaja("Ansku"); 
    nea.rekisteroi(); 
    nea.rekisteroi(); 
    unipaivakirja.lisaa(nea); 
    unipaivakirja.lisaa(ansku); 
    assertEquals("From: Unipaivakirja line: 70", nea, unipaivakirja.annaKayttaja(0)); 
    assertEquals("From: Unipaivakirja line: 71", ansku, unipaivakirja.annaKayttaja(1)); 
    try {
    unipaivakirja.annaKayttaja(5); 
    fail("Unipaivakirja: 72 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testEtsi214 */
  @Test
  public void testEtsi214() {    // Unipaivakirja: 214
    Unipaivakirja unip = new Unipaivakirja(); 
    Merkinta merk1 = new Merkinta(1); merk1.merkinnanLisays(); 
    merk1.setPvmDate("2022-03-12"); 
    unip.lisaa(merk1); 
    Collection<Merkinta> loytyneet = unip.etsi("2022-03-12", 1); 
    assertEquals("From: Unipaivakirja line: 222", 1, loytyneet.size()); 
    Iterator<Merkinta> it = loytyneet.iterator(); 
    assertEquals("From: Unipaivakirja line: 224", true, it.next() == merk1); 
  } // Generated by ComTest END
}
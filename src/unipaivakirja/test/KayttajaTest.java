package unipaivakirja.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import unipaivakirja.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2022.04.20 12:52:26 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class KayttajaTest {


  // Generated by ComTest BEGIN
  /** testGetNimi39 */
  @Test
  public void testGetNimi39() {    // Kayttaja: 39
    Kayttaja nea = new Kayttaja("Nea"); 
    nea.taytaNeaTiedoilla(); 
    { String _l_=nea.getNimi(),_r_="Nea"; if ( !_l_.matches(_r_) ) fail("From: Kayttaja line: 42" + " does not match: ["+ _l_ + "] != [" + _r_ + "]");}; 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testRekisteroi72 */
  @Test
  public void testRekisteroi72() {    // Kayttaja: 72
    Kayttaja nea = new Kayttaja("Nea"); 
    assertEquals("From: Kayttaja line: 74", 0, nea.getKayttajaId()); 
    nea.rekisteroi(); 
    Kayttaja ansku = new Kayttaja("Ansku"); 
    ansku.rekisteroi(); 
    int n1 = nea.getKayttajaId(); 
    int n2 = ansku.getKayttajaId(); 
    assertEquals("From: Kayttaja line: 80", n2-1, n1); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetKayttajaId94 */
  @Test
  public void testGetKayttajaId94() {    // Kayttaja: 94
    Kayttaja nea = new Kayttaja("Nea"); 
    nea.taytaNeaTiedoilla(); 
    assertEquals("From: Kayttaja line: 97", 1, nea.getKayttajaId()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testToString118 */
  @Test
  public void testToString118() {    // Kayttaja: 118
    Kayttaja kayttaja = new Kayttaja("Liisa"); 
    kayttaja.parse("3|Liisa|"); 
    assertEquals("From: Kayttaja line: 121", true, kayttaja.toString().startsWith("3|Liisa|")); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testParse135 */
  @Test
  public void testParse135() {    // Kayttaja: 135
    Kayttaja kayttaja = new Kayttaja("Liisa"); 
    kayttaja.parse("3|Liisa|"); 
    assertEquals("From: Kayttaja line: 138", 3, kayttaja.getKayttajaId()); 
    assertEquals("From: Kayttaja line: 139", true, kayttaja.toString().startsWith("3|Liisa|")); 
    kayttaja.rekisteroi(); 
    int n = kayttaja.getKayttajaId(); 
    kayttaja.parse(""+(n+20));  // Otetaan merkkijonosta vain tunnusnumero
    kayttaja.rekisteroi();  // ja tarkistetaan että seuraavalla kertaa tulee yhtä isompi
    assertEquals("From: Kayttaja line: 145", n+20+1, kayttaja.getKayttajaId()); 
  } // Generated by ComTest END
}
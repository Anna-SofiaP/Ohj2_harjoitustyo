package unipaivakirja.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.junit.*;
import unipaivakirja.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2022.03.08 14:22:35 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class MerkintaTest {


  // Generated by ComTest BEGIN
  /** testGetPvm32 */
  @Test
  public void testGetPvm32() {    // Merkinta: 32
    Merkinta pvm1 = new Merkinta(); 
    pvm1.taytaM1Tiedoilla(); 
    { String _l_=pvm1.getPvm(),_r_="16.3.2022 .*"; if ( !_l_.matches(_r_) ) fail("From: Merkinta line: 35" + " does not match: ["+ _l_ + "] != [" + _r_ + "]");}; 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testMerkinnanLisays118 */
  @Test
  public void testMerkinnanLisays118() {    // Merkinta: 118
    Merkinta pvm1 = new Merkinta(); 
    assertEquals("From: Merkinta line: 120", 0, pvm1.getMerkintaid()); 
    pvm1.merkinnanLisays(); 
    Merkinta pvm2 = new Merkinta(); 
    pvm2.merkinnanLisays(); 
    int n1 = pvm1.getMerkintaid(); 
    int n2 = pvm2.getMerkintaid(); 
    assertEquals("From: Merkinta line: 126", n2-1, n1); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testLaskeUnenmaara150 */
  @Test
  public void testLaskeUnenmaara150() {    // Merkinta: 150
    String nukkumaanKlo = "21:00"; 
    String heratysKlo = "6:00"; 
    SimpleDateFormat unenmaara = new SimpleDateFormat("HH:mm"); 
    Date nukkumaan = unenmaara.parse(nukkumaanKlo); 
    Date heratys = unenmaara.parse(heratysKlo); 
    assertEquals("From: Merkinta line: 156", "9 h 0 min", laskeUnenmaara()); 
    nukkumaanKlo = "1:45"; 
    heratysKlo = "9:00"; 
    unenmaara = new SimpleDateFormat("HH:mm"); 
    nukkumaan = unenmaara.parse(nukkumaanKlo); 
    heratys = unenmaara.parse(heratysKlo); 
    assertEquals("From: Merkinta line: 162", "7 h 15 min", laskeUnenmaara()); 
  } // Generated by ComTest END
}
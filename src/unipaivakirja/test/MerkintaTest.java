package unipaivakirja.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import static unipaivakirja.Merkinta.*;
import unipaivakirja.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2022.05.12 12:28:27 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class MerkintaTest {



  // Generated by ComTest BEGIN
  /** testGetPvm61 */
  @Test
  public void testGetPvm61() {    // Merkinta: 61
    Merkinta pvm1 = new Merkinta(); 
    pvm1.taytaM1Tiedoilla(); 
    assertEquals("From: Merkinta line: 64", "12.3.2022", pvm1.getPvm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testMerkinnanLisays154 */
  @Test
  public void testMerkinnanLisays154() {    // Merkinta: 154
    Merkinta pvm1 = new Merkinta(); 
    assertEquals("From: Merkinta line: 156", 0, pvm1.getMerkintaid()); 
    pvm1.merkinnanLisays(); 
    Merkinta pvm2 = new Merkinta(); 
    pvm2.merkinnanLisays(); 
    int n1 = pvm1.getMerkintaid(); 
    int n2 = pvm2.getMerkintaid(); 
    assertEquals("From: Merkinta line: 162", n2-1, n1); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testParse209 */
  @Test
  public void testParse209() {    // Merkinta: 209
    Merkinta merkinta = new Merkinta(3); 
    merkinta.parse("3|5|12.3.2015"); 
    assertEquals("From: Merkinta line: 212", 5, merkinta.getMerkintaid()); 
    assertEquals("From: Merkinta line: 213", true, merkinta.toString().startsWith("3|5|12.3.2015|"));  // on enemmäkin kuin 3 kenttää, siksi loppu | 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testToString234 */
  @Test
  public void testToString234() {    // Merkinta: 234
    Merkinta merkinta = new Merkinta(); 
    merkinta.parse("3|5|12.3.2015"); 
    assertEquals("From: Merkinta line: 237", true, merkinta.toString().startsWith("3|5|12.3.2015|"));  // on enemmäkin kuin 3 kenttää, siksi loppu |
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testLaskeUnenmaara272 */
  @Test
  public void testLaskeUnenmaara272() {    // Merkinta: 272
    Merkinta m1 = new Merkinta("21:20", "7:15"); 
    Merkinta m2 = new Merkinta("1:00", "10:45"); 
    Merkinta m3 = new Merkinta("22:00", "2:00"); 
    Merkinta m4 = new Merkinta("5:10", "09:55"); 
    Merkinta m5 = new Merkinta("21:30", "6:30"); 
    Merkinta m6 = new Merkinta("22:15", "8:15"); 
    Merkinta m7 = new Merkinta("22:00", "7:00"); 
    assertEquals("From: Merkinta line: 280", "9 h 55 min", m1.laskeUnenmaara("21:20", "07:15")); 
    assertEquals("From: Merkinta line: 281", "9 h 45 min", m2.laskeUnenmaara("1:00", "10:45")); 
    assertEquals("From: Merkinta line: 282", "4 h 0 min", m3.laskeUnenmaara("22:00", "2:00")); 
    assertEquals("From: Merkinta line: 283", "4 h 45 min", m4.laskeUnenmaara("5:10", "09:55")); 
    assertEquals("From: Merkinta line: 284", "9 h 0 min", m5.laskeUnenmaara("21:30", "6:30")); 
    assertEquals("From: Merkinta line: 285", "10 h 0 min", m6.laskeUnenmaara("22:15", "8:15")); 
    assertEquals("From: Merkinta line: 286", "9 h 0 min", m7.laskeUnenmaara("22:00", "7:00")); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testKellonajanTarkistus543 */
  @Test
  public void testKellonajanTarkistus543() {    // Merkinta: 543
    assertEquals("From: Merkinta line: 544", null, kellonajanTarkistus("15:05")); 
    assertEquals("From: Merkinta line: 545", null, kellonajanTarkistus("09:00")); 
    assertEquals("From: Merkinta line: 546", null, kellonajanTarkistus("23:46")); 
    assertEquals("From: Merkinta line: 547", "Anna kellonaika", kellonajanTarkistus("")); 
    assertEquals("From: Merkinta line: 548", "Anna kellonaika", kellonajanTarkistus(null)); 
    assertEquals("From: Merkinta line: 549", "Tunnit liian isot tai pienet", kellonajanTarkistus("26:40")); 
    assertEquals("From: Merkinta line: 550", "Minuutit liian isot tai pienet", kellonajanTarkistus("00:67")); 
    assertEquals("From: Merkinta line: 551", null, kellonajanTarkistus("6:30")); 
    assertEquals("From: Merkinta line: 552", "Kaksoispiste puuttuu", kellonajanTarkistus("440")); 
    assertEquals("From: Merkinta line: 553", "Kaksoispiste puuttuu", kellonajanTarkistus("9")); 
    assertEquals("From: Merkinta line: 554", "Minuuteissa liian vähän merkkejä", kellonajanTarkistus("3:2")); 
    assertEquals("From: Merkinta line: 555", "Kellonajassa virheellisiä merkkejä", kellonajanTarkistus("oo:l0")); 
    assertEquals("From: Merkinta line: 556", "Tunnit liian isot tai pienet", kellonajanTarkistus("-4:30")); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testClone611 
   * @throws CloneNotSupportedException when error
   */
  @Test
  public void testClone611() throws CloneNotSupportedException {    // Merkinta: 611
    Merkinta merkinta = new Merkinta(); 
    merkinta.parse("1|2|2022-03-12"); 
    Merkinta kopio = merkinta.clone(); 
    assertEquals("From: Merkinta line: 616", merkinta.toString(), kopio.toString()); 
    merkinta.parse("1|4|2022-04-13"); 
    assertEquals("From: Merkinta line: 618", false, kopio.toString().equals(merkinta.toString())); 
  } // Generated by ComTest END
}
package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Test suite unipaivakirja-ohjelmalle
 * @author Omistaja
 * @version 6.4.2022
 */
@RunWith(Suite.class)
@SuiteClasses({
    unipaivakirja.test.KayttajaTest.class,
    unipaivakirja.test.KayttajatTest.class,
    unipaivakirja.test.KloTarkistusTest.class,
    unipaivakirja.test.MerkinnatTest.class,
    //unipaivakirja.test.MerkintaTest.class,
    //unipaivakirja.test.UnenlaatuListaTest.class,
    //unipaivakirja.test.UnipaivakirjaTest.class,
    //unipaivakirja.test.VireystilaListaTest.class,
    })
public class AllTests {
    //
   }

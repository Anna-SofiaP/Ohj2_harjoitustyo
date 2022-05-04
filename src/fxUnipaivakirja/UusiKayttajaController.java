/**
 * 
 */
package fxUnipaivakirja;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import unipaivakirja.Kayttaja;
import unipaivakirja.Unipaivakirja;

/**
 * @author Omistaja
 * @version 29.4.2022
 *
 */
public class UusiKayttajaController implements ModalControllerInterface<Kayttaja>{

    @FXML private TextField editKayttajanimi;
    @FXML private Label labelVirhe;
    
    @FXML void handlePeruuta() {
        //uusi = null;
        ModalController.closeStage(editKayttajanimi);
    }

    @FXML void handleSeuravaa() {
        tallennetaanko = true;
        tallennaUusiKayttaja();
        avaaKayttajanPaivakirja();
    }
    

    //-------------------------------------------------------------------------------------------
    
    
    private Unipaivakirja unipaivakirja;
    private Kayttaja uusi;
    private boolean tallennetaanko = false;
    
    /**
     * @param unipaivakirja jota käytetään tässä käyttöliittymässä
     */
    public void setUnipaivakirja(Unipaivakirja unipaivakirja) {
        this.unipaivakirja = unipaivakirja;
        //lueTiedosto();
        //haeMerkinnat(0);
    }
    
    
    private void tallennaUusiKayttaja() {
        uusi = new Kayttaja(editKayttajanimi.getText());
        uusi.rekisteroi();
        unipaivakirja.lisaa(uusi);
    }
    
    
    /**
     * Avaa valitun käyttäjän unipäiväkirjan
     */
    public void avaaKayttajanPaivakirja() {
        ModalController.closeStage(editKayttajanimi);
    } 

    
    @Override
    public Kayttaja getResult() {
        return uusi;
    }

    @Override
    public void handleShown() {
        //
    }

    @Override
    public void setDefault(Kayttaja oletus) {
        uusi = oletus;
    }

}

/**
 * 
 */
package fxUnipaivakirja;

import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import unipaivakirja.Kayttaja;
import unipaivakirja.SailoException;
import unipaivakirja.Unipaivakirja;


/**
 * @author Omistaja
 * @version 28.3.2022
 *
 */
public class AloitusikkunaController implements ModalControllerInterface<String>{
    
    @FXML private ComboBoxChooser<String> kayttajaValinta;
    
    @FXML void handleValitseKayttaja() {
        avaaKayttajanPaivakirja();
    }
    
    @FXML void handleUusiKayttaja() {
        uusiKayttaja();
    }
    
    @FXML void handleSulje() {
        ModalController.closeStage(kayttajaValinta);
    }
    
    
    /**
     * Mitä tehdään kun dialogi on näytetty
     */
    @Override
    public void handleShown() {
        //hakuehto.requestFocus();
    }

    
    
    //------------------------------------------------------
    
    private Unipaivakirja kayttajanUnipaivakirja;
    private String valittuKayttaja;
    private Kayttaja valittu;

    
    /**
     * Avaa valitun käyttäjän unipäiväkirjan
     * @return ??
     */
    public boolean avaaKayttajanPaivakirja() {
        //valittu = kayttajanUnipaivakirja.asetaKayttaja(kayttajaValinta.getSelectedText());
        //Dialogs.showMessageDialog(kayttajaValinta.getSelectedText());
        //kayttajaValinta.setSelectedIndex(0);
        //kayttajanUnipaivakirja.annaKayttaja(kayttajaValinta.getSelectedIndex());
        valittuKayttaja = kayttajaValinta.getSelectedText();
        ModalController.closeStage(kayttajaValinta);
        /*String valinta = kysyKayttaja(null, kayttajaValinta.getSelectedText());
        Kayttaja valittuKayttaja = new Kayttaja(valinta);*/
        return true;
    } 
    
    
    /**
     * @param unipaivakirja jota käytetään tässä käyttöliittymässä
     */
    public void setUnipaivakirja(Unipaivakirja unipaivakirja) {
        this.kayttajanUnipaivakirja = unipaivakirja;
    }
    
    
    /**
     * Alustaa tietyn käyttäjän unipäiväkirjan lukemalla sen 
     * valitun nimisestä tiedostosta
     * @param valittu käyttäjä, jonka unipäiväkirja avataan
     */
    protected void lueTiedosto(String valittu) {
        setTitle("Unipäiväkirja - " + valittu);
        String virhe = "Ei osata lukea vielä";  // TODO: tähän oikea tiedoston lukeminen
            Dialogs.showMessageDialog(virhe);
    }
    
    
    private void setTitle(String title) {
        ModalController.getStage(kayttajaValinta).setTitle(title);
    }
    
    
    /**
     * Luo uuden käyttäjän jota aletaan editoimaan 
     */
    protected void uusiKayttaja() {
        Kayttaja uusi = new Kayttaja("nea");
        uusi.rekisteroi();
        uusi.taytaNeaTiedoilla();
        kayttajanUnipaivakirja.lisaa(uusi);
    }

    
    @Override
    public String getResult() {
        return valittuKayttaja;
    }

    
    @Override
    public void setDefault(String arg0) {
        // TODO Auto-generated method stub
        
    }
    
    
    /**
     * @return TODO: mitä tähän?
     */
    public boolean voikoSulkea() {
        // TODO Auto-generated method stub
        return false;
    }

}

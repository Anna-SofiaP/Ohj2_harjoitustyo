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

    
    /**
     * Avaa valitun käyttäjän unipäiväkirjan
     * @return ??
     */
    public boolean avaaKayttajanPaivakirja() {
        //kayttajanUnipaivakirja.asetaKayttaja(kayttajaValinta.getSelectedText());
        Dialogs.showMessageDialog(kayttajaValinta.getSelectedText());
        kayttajaValinta.setSelectedIndex(0);
        //kayttajanUnipaivakirja.annaKayttaja(kayttajaValinta.getSelectedIndex());
        ModalController.closeStage(kayttajaValinta);
        /*String valinta = kysyKayttaja(null, kayttajaValinta.getSelectedText());
        Kayttaja valittuKayttaja = new Kayttaja(valinta);
        lueTiedosto(valittuKayttaja);*/
        return true;
    }   
    
    
    /**
     * Alustaa tietyn käyttäjän unipäiväkirjan lukemalla sen 
     * valitun nimisestä tiedostosta
     * @param valittu käyttäjä, jonka unipäiväkirja avataan
     */
    protected void lueTiedosto(Kayttaja valittu) {
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
        try {
            kayttajanUnipaivakirja.lisaa(uusi);
        } catch (SailoException e) {
            Dialogs.showMessageDialog("Ongelmia uuden luomisessa " + e.getMessage());
            return;
        }
        //hae(uusi.getKayttajaId());
    }
    
    
    /**
     * Luodaan käyttäjänkysymisdialogi ja palautetaan comboboxchooserista valittu
     * käyttäjänimi
     * @param modalityStage mille ollaan modaalisia, null = sovellukselle
     * @param oletus mitä käyttäjää näytetään oletuksena
     * @return null jos painetaan Cancel, muuten valittu nimi
     */
    public static String kysyKayttaja(Stage modalityStage, String oletus) {
        return ModalController.showModal(
                AloitusikkunaController.class.getResource("aloitusikkuna.fxml"),
                "Unipäiväkirja",
                modalityStage, oletus);
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

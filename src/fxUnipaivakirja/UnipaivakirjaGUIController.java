package fxUnipaivakirja;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import unipaivakirja.Kayttaja;
import unipaivakirja.SailoException;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import javafx.application.Platform;
import javafx.fxml.FXML;

/**
 * @author Omistaja
 * @version 10.2.2022
 *
 */
public class UnipaivakirjaGUIController implements Initializable{
    
    private String kayttajanimi = "Nea";
    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {  
        alusta();      
    }


    @FXML void handleAvaa() {
        avaa();
    }

    @FXML void handleHaku() {
        hae();
    }

    @FXML void handleLopeta() {
        tallenna();
        Platform.exit();
    }

    @FXML void handleOhje() {
        ohje();
    }

    @FXML void handlePoistaKayttaja() {
        Dialogs.showMessageDialog("Vielä ei osata poistaa käyttäjää");
    }

    @FXML void handlePoistaMerkinta() {
        Dialogs.showMessageDialog("Vielä ei osata poistaa merkintää");
    }

    @FXML void handlePoistu() {
        tallenna();
        Platform.exit();
    }

    @FXML void handleTallenna() {
        tallenna();
    }

    @FXML void handleTietoja() {
        ModalController.showModal(UnipaivakirjaGUIController.class.getResource(
                "Tietoja.fxml"), "Tietoja", null, "");
    }

    @FXML void handleTulosta() {
        TulostusController tulostusCtrl = TulostusController.tulosta(null); 
        tulostaValitut(tulostusCtrl.getTextArea()); 
    }

    @FXML void handleUusiKayttaja() {
        uusiKayttaja();
    }

    @FXML void handleUusiMerkinta() {
        Dialogs.showMessageDialog("Ei osata vielä lisätä uutta merkintää.");
    }
    
    //------------------------------------------------------------
    
    private Kayttaja kayttajanUnipaivakirja;
    
    /**
     * Tallentaa muokatut tiedot.
     */
    private void tallenna() {
        Dialogs.showMessageDialog("Tallennetaan, mutta ei toimi vielä!");
    }
    
    
    /**
     * Alustaa tietyn käyttäjän unipäiväkirjan lukemalla sen 
     * valitun nimisestä tiedostosta
     * @param nimi tiedosto josta tietyn käyttäjän unipäiväkirjan tiedot luetaan
     */
    protected void lueTiedosto(String nimi) {
        kayttajanimi = nimi;
        setTitle("Unipäiväkirja - " + kayttajanimi);
        String virhe = "Ei osata lukea vielä";  // TODO: tähän oikea tiedoston lukeminen
            Dialogs.showMessageDialog(virhe);
    }


    private void setTitle(String title) {
        ModalController.getStage(hakuehto).setTitle(title);
    }
    
    
    /**
     * Luo uuden käyttäjän jota aletaan editoimaan 
     */
    protected void uusiKayttaja() {
        Kayttaja uusi = new Kayttaja();
        uusi.rekisteroi();
        uusi.taytaNeaTiedoilla();
        try {
            kayttajanUnipaivakirja.lisaa(uusi);
        } catch (SailoException e) {
            Dialogs.showMessageDialog("Ongelmia uuden luomisessa " + e.getMessage());
            return;
        }
        hae(uusi.getKayttajaId());
    }

    
    
    /**
     * Näytetään harjoitustyöohjelman suunnitelma web-selaimessa.
     */
    private void ohje() {
        Desktop desktop = Desktop.getDesktop();
        try {
            URI uri = new URI("https://tim.jyu.fi/view/kurssit/tie/ohj2/2022k/ht/ansupaav");
            desktop.browse(uri);
        } catch (URISyntaxException e) {
            return;
        } catch (IOException e) {
            return;
        }
    }
    
    private void hae() {
        //jee
    }

    
    
}

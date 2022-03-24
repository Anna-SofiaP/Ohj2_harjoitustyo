package fxUnipaivakirja;

import java.awt.Desktop;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import unipaivakirja.Kayttaja;
import unipaivakirja.Merkinta;
import unipaivakirja.SailoException;
import unipaivakirja.Unenlaatu;
import unipaivakirja.Unipaivakirja;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import javafx.application.Platform;
import javafx.fxml.FXML;

import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.ListChooser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * Luokka unipäiväkirjan tapahtumien hoitamiseksi.
 * @author Omistaja
 * @version 10.2.2022
 * @version 8.3.2022
 *
 */
public class UnipaivakirjaGUIController implements Initializable{
    
    @FXML private ListChooser<Merkinta> chooserMerkinnat;
    @FXML private TextField hakuehto;
    @FXML private DatePicker kalenteri;
    @FXML private ComboBoxChooser<?> unenlaatuVal;
    @FXML private ComboBoxChooser<?> vireystilaVal;
    @FXML private ComboBoxChooser<?> kayttajaValinta;
    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {  
        alusta();      
    }

    @FXML void handleValitseKayttaja() {
        avaaKayttajanPaivakirja();
    }


    @FXML void handleAvaa() {
        handleValitseKayttaja(); //TODO: miten saadaan aloitusikkuna avautumaan?
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
        //TulostusController tulostusCtrl = TulostusController.tulosta(null); 
        //tulostaValitut(tulostusCtrl.getTextArea()); 
    }

    @FXML void handleUusiKayttaja() {
        uusiKayttaja();
    }

    @FXML void handleUusiMerkinta() {
        Dialogs.showMessageDialog("Ei osata vielä lisätä uutta merkintää.");
        uusiMerkinta();
    }
    
    //------------------------------------------------------------
    
    private Unipaivakirja kayttajanUnipaivakirja;
    private Merkinta merkintaKohdalla;
    private String kayttajanimi = "Nea";
    
    /**
     * Tallentaa muokatut tiedot.
     */
    private void tallenna() {
        Dialogs.showMessageDialog("Tallennetaan, mutta ei toimi vielä!");
    }
    
    
    /**
     * Alustaa tietyn käyttäjän unipäiväkirjan lukemalla sen 
     * valitun nimisestä tiedostosta
     * @param valittu käyttäjä, jonka unipäiväkirja avataan
     */
    protected void lueTiedosto(Kayttaja valittu) {
        kayttajanimi = valittu.getNimi();
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
    
    
    /**
     * Tekee tarvittavat muut alustukset, nyt vaihdetaan GridPanen tilalle
     * yksi iso tekstikenttä, johon voidaan tulostaa jäsenten tiedot.
     * Alustetaan myös käyttäjälistan kuuntelija 
     */
    protected void alusta() {
        /*panelJasen.setContent(areaJasen);
        areaJasen.setFont(new Font("Courier New", 12));
        panelJasen.setFitToHeight(true);*/
        
        chooserMerkinnat.clear();
        chooserMerkinnat.addSelectionListener(e -> naytaMerkinta());
    }
    
    
    /**
     * Näyttää listasta valitun unipäiväkirjamerkinnän tiedot, tilapäisesti yhteen isoon edit-kenttään
     */
    protected void naytaMerkinta() {
        merkintaKohdalla = chooserMerkinnat.getSelectedObject();

        if (merkintaKohdalla == null) return;

        
        
        /*areaJasen.setText("");
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(areaJasen)) {
            jasenKohdalla.tulosta(os);
        }*/
    }

    
    /**
     * Lisää uuden merkinnän valitulle käyttäjälle
     */
    public void uusiMerkinta() {
        //Unenlaatu uusiUnenlaatu = new Unenlaatu(unenlaatuVal.getSelectedText());
        //Vireystila uusiVireystila = new Vireystila(vireystilaVal.getSelectedText());
    }
    
    /**
     * Avaa valitun käyttäjän unipäiväkirjan
     * @return ??
     */
    public boolean avaaKayttajanPaivakirja() {
        Kayttaja valittuKayttaja = new Kayttaja(kayttajaValinta.getSelectedText());
        lueTiedosto(valittuKayttaja);
        return true;
    }    
    
    
    private void hae() {
        //jee
    }

    
    /**
     * @param unipaivakirja jota käytetään tässä käyttöliittymässä
     */
    public void setUnipaivakirja(Unipaivakirja unipaivakirja) {
        this.kayttajanUnipaivakirja = unipaivakirja;
        naytaMerkinta();
    }

    
    /**
     * @return TODO: mitä tähän?
     */
    public boolean voikoSulkea() {
        // TODO Auto-generated method stub
        return false;
    }
    
    

    
    
}

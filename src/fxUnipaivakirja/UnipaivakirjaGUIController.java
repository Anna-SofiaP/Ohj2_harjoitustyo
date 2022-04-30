package fxUnipaivakirja;

import java.awt.Desktop;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import unipaivakirja.Kayttaja;
import unipaivakirja.Merkinnat;
import unipaivakirja.Merkinta;
import unipaivakirja.SailoException;
import unipaivakirja.Unenlaatu;
import unipaivakirja.Unipaivakirja;
import unipaivakirja.Vireystila;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.TextAreaOutputStream;
import javafx.application.Platform;
import javafx.fxml.FXML;

import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.ListChooser;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;

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
    //@FXML private ComboBoxChooser<String> kayttajaValinta;
    @FXML private ScrollPane panelMerkinta;
    
    @FXML private DatePicker kalenteri;
    @FXML private TextField editNukkumaan;
    @FXML private TextField editHeratys;
    @FXML private TextField editUnenmaara;
    @FXML private TextArea editLisatiedot;
    @FXML private TextField editUnenlaatu;
    @FXML private TextField editVireystila;
    
    @Override
    public void initialize(URL url, ResourceBundle bundle) { 
        setUnipaivakirja(new Unipaivakirja());
        valittuKayttaja = ModalController.<Kayttaja,AloitusikkunaController>showModal(UnipaivakirjaGUIController.class.getResource(
                "aloitusikkuna.fxml"), "Valitse käyttäjä", null, null, ctrl -> ctrl.setUnipaivakirja(kayttajanUnipaivakirja));
        alusta();
        haeMerkinnat(0);
    }


    @FXML void handleAvaa() {
        avaa();
    }


    @FXML void handleHaku() {
        //hae();
    }

    @FXML void handleLopeta() {
        tallenna();
        Platform.exit();
    }

    @FXML void handleOhje() {
        ohje();
    }

    @FXML void handlePoistaKayttaja() {
        //Dialogs.showMessageDialog("Vielä ei osata poistaa käyttäjää");
        poistaKayttaja();
    }

    @FXML void handlePoistaMerkinta() {
        //Dialogs.showMessageDialog("Vielä ei osata poistaa merkintää");
        poistaMerkinta();
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
        //Dialogs.showMessageDialog("Ei osata vielä lisätä uutta merkintää.");
        uusiMerkinta();
    }
    
    //------------------------------------------------------------
    
    private Unipaivakirja kayttajanUnipaivakirja;
    private Merkinta merkintaKohdalla;
    private Kayttaja valittuKayttaja;
    
    
    public void avaa() {
        valittuKayttaja = ModalController.<Kayttaja,AloitusikkunaController>showModal(UnipaivakirjaGUIController.class.getResource(
                "aloitusikkuna.fxml"), "Valitse käyttäjä", null, null, ctrl -> ctrl.setUnipaivakirja(kayttajanUnipaivakirja));
        alusta();
        haeMerkinnat(0);
    }
    
    
    /**
     * Tallentaa muokatut tiedot.
     * @return null, jos onnistuu, muuten virhe tekstinä
     */
    private String tallenna() {
        try {
            kayttajanUnipaivakirja.talleta();
            Dialogs.showMessageDialog("Muutokset tallennettu!");
            return null;
        } catch(SailoException ex) {
            Dialogs.showMessageDialog("Tallennuksessa ongelmia! " + ex.getMessage());
            return ex.getMessage();
        }
        
    }
    
    
    /**
     * Alustaa tietyn käyttäjän merkinnat lukemalla valitun nimisestä tiedostosta
     * @return null, jos tiedoston lukeminen onnistui, muuten virhe
     */
    protected String lueTiedosto() {
        setTitle("Unipäiväkirja - " + valittuKayttaja.getNimi());
        try {
            kayttajanUnipaivakirja.lueTiedostosta("data");
        } catch (SailoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }  //TODO: tähän tulee sen käyttäjän id, joka on valittu comboboxchooserista?
        return null;
    }


    private void setTitle(String title) {
        //ModalController.getStage(kayttajaValinta).setTitle(title);
    }
    
    
    private void poistaMerkinta() {
        Merkinta merkinta = merkintaKohdalla;
        if (merkinta == null) return;
        if ( !Dialogs.showQuestionDialog("Merkinnän poisto", "Poistetaanko merkintä: " + merkinta.getPvmDate(), "Kyllä", "Ei"))
            return;
        kayttajanUnipaivakirja.poista(merkinta);
        haeMerkinnat(0);
    }
    
    
    private void poistaKayttaja() {
        Kayttaja kayttaja = valittuKayttaja;
        if (kayttaja == null) return;
        if ( !Dialogs.showQuestionDialog("Käyttäjän poisto", "Poistetaanko käyttäjä: " + kayttaja.getNimi(), "Kyllä", "Ei"))
            return;
        kayttajanUnipaivakirja.poista(kayttaja);
        avaa();
    }
    
    
    private void uusiKayttaja() {
        valittuKayttaja = ModalController.<Kayttaja,UusiKayttajaController>showModal(UnipaivakirjaGUIController.class.getResource(
                "Uusikayttaja.fxml"), "Luo uusi käyttäjä", null, null, ctrl -> ctrl.setUnipaivakirja(kayttajanUnipaivakirja));
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
        chooserMerkinnat.clear();
        chooserMerkinnat.addSelectionListener(e -> naytaMerkinta());
        
        //edits = new TextField[]{editNukkumaan, editHeratys, editUnenmaara};
    }
    
    
    /**
     * Näyttää listasta valitun unipäiväkirjamerkinnän tiedot
     */
    protected void naytaMerkinta() {
        merkintaKohdalla = chooserMerkinnat.getSelectedObject();

        if (merkintaKohdalla == null) {
            return;
        }
        
        naytaUnimerkinta();
    }
    
    
    /**
     * Asettaa unimerkinnän tiedot oikeille paikoilleen
     */
    public void naytaUnimerkinta() {
        kalenteri.setValue(merkintaKohdalla.getPvmDate());
        editNukkumaan.setText(merkintaKohdalla.getNukkumaanKlo());
        editHeratys.setText(merkintaKohdalla.getHeratysKlo());
        editUnenmaara.setText(merkintaKohdalla.getUnenmaara());
        editLisatiedot.setText(merkintaKohdalla.getLisatiedot());
        editUnenlaatu.setText(merkintaKohdalla.getUnenlaatu());
        editVireystila.setText(merkintaKohdalla.getVireystila());
    }

    
    /**
     * Lisää uuden merkinnän valitulle käyttäjälle
     */
    public void uusiMerkinta() {
        Merkinta uusi = new Merkinta(valittuKayttaja.getKayttajaId());
        uusi = MerkintaDialogiController.kysyMerkinta(null, uusi, kayttajanUnipaivakirja);
        if (uusi == null) return;
        uusi.merkinnanLisays();
        kayttajanUnipaivakirja.lisaa(uusi);
        haeMerkinnat(uusi.getMerkintaid());
    }
    
    
    /**
     * Avaa valitun käyttäjän unipäiväkirjan
     * @param valittuKayttaja käyttäjä joka on valittu comboboxchooserista
     * @return ??
     */
    /*public boolean avaaKayttajanPaivakirja(String valittuKayttaja) {
        //valittu = "Nea";
        //ModalController.closeStage(kayttajaValinta);
        //String valinta = "Nea";
        //Kayttaja valittuKayttaja = new Kayttaja("Nea");
        lueTiedosto(kysyKayttaja(null, valittuKayttaja));
        //lueTiedosto(kysyKayttaja(null, ""));
        return true;
    }*/
    
    
    /**
     * Luodaan käyttäjänkysymisdialogi ja palautetaan comboboxchooserista valittu
     * käyttäjänimi
     * @param modalityStage mille ollaan modaalisia, null = sovellukselle
     * @param oletus mitä käyttäjää näytetään oletuksena
     * @return null jos painetaan Cancel, muuten valittu nimi
     */
    /*public static String kysyKayttaja(Stage modalityStage, String oletus) {
        return ModalController.showModal(
                AloitusikkunaController.class.getResource("aloitusikkuna.fxml"),
                "Unipäiväkirja",
                modalityStage, oletus);
    }*/
    
    
    /**
     * Tulostaa käyttäjän merkinnät
     * @param os tietovirta johon tulostetaan
     * @param merkinta tulostettava merkinta
     */
    public void tulosta(PrintStream os, final Merkinta merkinta) {
        os.println("----------------------------------------------");
        merkinta.tulosta(os);
        os.println("----------------------------------------------");   
    }

    
    
    /**
     * haetaan käyttäjän merkintöjen tiedot listaan
     * @param merkintaid ??
     */
    private void haeMerkinnat(int merkintaid) {
        chooserMerkinnat.clear();

        int index = 0;
        List<Merkinta> merkinnat = kayttajanUnipaivakirja.annaKayttajanMerkinnat(valittuKayttaja.getKayttajaId());
        //tässä haetaan merkinnöistä kaikki merkinnät joiden käyttäjäid täsmää tämänhetkiseen käyttäjään
        for (int i = 0; i < merkinnat.size(); i++) { //TODO: nollan tilalle jotain!
            Merkinta merkinta = merkinnat.get(i);
            if (merkinta.getMerkintaid() == merkintaid) index = i;
            chooserMerkinnat.add(merkinta.getPvm(), merkinta);
        }
        chooserMerkinnat.setSelectedIndex(index); // tästä tulee muutosviesti joka näyttää merkinnän

    }

    
    /**
     * @param unipaivakirja jota käytetään tässä käyttöliittymässä
     */
    public void setUnipaivakirja(Unipaivakirja unipaivakirja) {
        this.kayttajanUnipaivakirja = unipaivakirja;
        //lueTiedosto();
        //haeMerkinnat(0);
    }

    
    /**
     * @return TODO: mitä tähän?
     */
    public boolean voikoSulkea() {
        // TODO Auto-generated method stub
        return false;
    }
    
    
    /**
     * @return ??       //TODO: mitä tähän?
     */
    public String getResult() {
        return "testi";
    }

    
    /**
     * @param arg0 ??   //TODO: mitä tähän?
     */
    public void setDefault(String arg0) {
        // TODO Auto-generated method stub
        
    }
    
    

    
    
}

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
    @FXML private TextField editLisatiedot;
    @FXML private ComboBoxChooser<Unenlaatu> editUnenlaatu;
    @FXML private ComboBoxChooser<Vireystila> editVireystila;
    
    @Override
    public void initialize(URL url, ResourceBundle bundle) { 
        setUnipaivakirja(new Unipaivakirja());
        valittuKayttaja = ModalController.<Kayttaja,AloitusikkunaController>showModal(UnipaivakirjaGUIController.class.getResource(
                "aloitusikkuna.fxml"), "Valitse käyttäjä", null, null, ctrl -> ctrl.setUnipaivakirja(kayttajanUnipaivakirja));
        alusta();
        haeMerkinnat(0);
    }

    /*@FXML void handleValitseKayttaja(String valittuKayttaja) {
        avaaKayttajanPaivakirja(valittuKayttaja);
    }*/


    @FXML void handleAvaa() {
        //handleValitseKayttaja(valitseKayttaja());
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
        //Dialogs.showMessageDialog("Ei osata vielä lisätä uutta merkintää.");
        uusiMerkinta();
    }
    
    //------------------------------------------------------------
    
    private Unipaivakirja kayttajanUnipaivakirja;
    private Merkinta merkintaKohdalla;
    private Kayttaja valittuKayttaja;
    //private TextArea tekstilaatikko = new TextArea();
    private TextField edits[];
    
    
    /*public String valitseKayttaja() {
        valittu = kayttajaValinta.getSelectedText();
        return valittu;
    }*/
    
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
    
    
    /**
     * Luo uuden käyttäjän jota aletaan editoimaan 
     */
    protected void uusiKayttaja() {
        Kayttaja uusi = new Kayttaja("nea");
        uusi.rekisteroi();
        uusi.taytaNeaTiedoilla();
        kayttajanUnipaivakirja.lisaa(uusi);
    }
    
    
    /**
     * Tyhjentää tekstikentät
     * @param edits taulukko, jossa tyhjennettäviä tekstikenttiä
     * @param pvm kalenterivalikko
     * @param unenlaatu unenlaatuvalikko
     * @param vireystila vireystilavalikko
     */
    public static void tyhjenna(TextField[] edits, DatePicker pvm, ComboBoxChooser<Unenlaatu> unenlaatu, ComboBoxChooser<Vireystila> vireystila) {
        for (TextField edit : edits)
            edit.setText("");
        pvm.setValue(null);
        unenlaatu.setValue(null);
        vireystila.setValue(null);
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
        
        edits = new TextField[]{editNukkumaan, editHeratys, editUnenmaara, editLisatiedot};
    }
    
    
    /**
     * Näyttää listasta valitun unipäiväkirjamerkinnän tiedot
     */
    protected void naytaMerkinta() {
        merkintaKohdalla = chooserMerkinnat.getSelectedObject();

        if (merkintaKohdalla == null) {
            return;
        }
        
        //tekstilaatikko.setText("");
        //try (PrintStream os = TextAreaOutputStream.getTextPrintStream(tekstilaatikko)) {
            //merkintaKohdalla.tulosta(os);
        //}
        
        naytaUnimerkinta(edits, merkintaKohdalla, kalenteri, editUnenlaatu, editVireystila);
    }
    
    
    public void naytaUnimerkinta(TextField[] edits, Merkinta merkinta, DatePicker pvm, ComboBoxChooser<Unenlaatu> unenlaatu, ComboBoxChooser<Vireystila> vireystila) {
        pvm.setValue(merkinta.getPvmDate());
        edits[0].setText(merkinta.getNukkumaanKlo());
        edits[1].setText(merkinta.getHeratysKlo());
        edits[2].setText(merkinta.laskeUnenmaara());
        edits[3].setText(merkinta.getLisatiedot());
        
    }

    
    /**
     * Lisää uuden merkinnän valitulle käyttäjälle
     */
    public void uusiMerkinta() {
        //if ( merkintaKohdalla == null ) return; 
        Merkinta merk = new Merkinta(valittuKayttaja.getKayttajaId()); 
        merk.merkinnanLisays(); 
        merk.taytaM1Tiedoilla(); 
        kayttajanUnipaivakirja.lisaa(merk);
        haeMerkinnat(merk.getMerkintaid());
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

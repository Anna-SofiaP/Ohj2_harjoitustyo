package fxUnipaivakirja;

import java.awt.Desktop;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import unipaivakirja.Kayttaja;
import unipaivakirja.Merkinta;
import unipaivakirja.SailoException;
import unipaivakirja.Unipaivakirja;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.TextAreaOutputStream;
import javafx.application.Platform;
import javafx.fxml.FXML;

import fi.jyu.mit.fxgui.ListChooser;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
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
        avaa(true);
        if (valittuKayttaja == null) {
            Platform.exit();
            return;
        }
        alusta();
        haeMerkinnat(0);
    }


    @FXML void handleAvaa() {
        avaa(false);
    }
    
    
    @FXML void handleMuokkaaMerkintaa() {
        muokkaaMerkintaa();
    }


    @FXML void handleHaku() {
        hae(0);
    }

    @FXML void handleLopeta() {
        tallenna();
        Platform.exit();
    }

    @FXML void handleOhje() {
        ohje();
    }

    @FXML void handlePoistaKayttaja() {
        poistaKayttaja();
    }

    @FXML void handlePoistaMerkinta() {
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
        TulostusController tulostusCtrl = TulostusController.tulosta(null); 
        tulostaValitut(tulostusCtrl.getTextArea()); 
    }

    @FXML void handleUusiKayttaja() {
        uusiKayttaja();
    }

    @FXML void handleUusiMerkinta() {
        uusiMerkinta();
    }
    
    //------------------------------------------------------------
    
    private Unipaivakirja kayttajanUnipaivakirja;
    private Merkinta merkintaKohdalla;
    private Kayttaja valittuKayttaja;
    
    
    /**
     * Kysyy aloitusikkunacontrollerilta uutta käyttäjää ja avaa valitun käyttäjän perusteella
     * kyseisen käyttäjän unipäiväkirjan
     * @param suljetaan true jos suljetaan koko ohjelma, false jos vain ikkuna
     */
    public void avaa(boolean suljetaan) {
        Kayttaja kayttaja = ModalController.<Kayttaja,AloitusikkunaController>showModal(UnipaivakirjaGUIController.class.getResource(
                "aloitusikkuna.fxml"), "Valitse käyttäjä", null, null, ctrl -> ctrl.setUnipaivakirja(kayttajanUnipaivakirja));
        if (kayttaja == null && suljetaan) {
            Platform.exit();
            return;
        }
        if (kayttaja == null) return;
        valittuKayttaja = kayttaja;
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
        try {
            kayttajanUnipaivakirja.lueTiedostosta("data");
        } catch (SailoException e) {
            //
            e.printStackTrace();
        }  //
        return null;
    }
    
    
    /**
     * Poistaa listasta valitun merkinnän
     */
    private void poistaMerkinta() {
        Merkinta merkinta = merkintaKohdalla;
        if (merkinta == null) return;
        if ( !Dialogs.showQuestionDialog("Merkinnän poisto", "Poistetaanko merkintä: " + merkinta.getPvmDate(), "Kyllä", "Ei"))
            return;
        kayttajanUnipaivakirja.poista(merkinta);
        haeMerkinnat(0);
    }
    
    
    /**
     * Poistaa tällä hetkellä valittuna olevan käyttäjän ja sen merkinnät
     */
    private void poistaKayttaja() {
        Kayttaja kayttaja = valittuKayttaja;
        if (kayttaja == null) return;
        if ( !Dialogs.showQuestionDialog("Käyttäjän poisto", "Poistetaanko käyttäjä: " + kayttaja.getNimi(), "Kyllä", "Ei"))
            return;
        kayttajanUnipaivakirja.poista(kayttaja);
        avaa(true);
    }
    
    
    /**
     * Luo uuden käyttäjän kysymis -dialogin. Tallentaa käyttäjän tietorakenteeseen.
     */
    private void uusiKayttaja() {
        Kayttaja uusiKayttaja = ModalController.<Kayttaja,UusiKayttajaController>showModal(UnipaivakirjaGUIController.class.getResource(
                "Uusikayttaja.fxml"), "Luo uusi käyttäjä", null, null, ctrl -> ctrl.setUnipaivakirja(kayttajanUnipaivakirja));
        if (uusiKayttaja == null) return;
        valittuKayttaja = uusiKayttaja;
        haeMerkinnat(0);
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
     * Muokataan listasta valittua merkintää
     */
    private void muokkaaMerkintaa() { 
        if ( merkintaKohdalla == null ) return; 
        try { 
            Merkinta merkinta; 
            merkinta = MerkintaDialogiController.kysyMerkinta(null, merkintaKohdalla, kayttajanUnipaivakirja);    
            if ( merkinta == null ) return; 
            merkintaKohdalla = merkinta;
            kayttajanUnipaivakirja.muokkaa(merkinta); 
            haeMerkinnat(merkinta.getMerkintaid()); 
        } catch (SailoException e) { 
            Dialogs.showMessageDialog(e.getMessage()); 
        } 
    } 

    
    
    /**
     * Tulostaa käyttäjän merkinnät
     * @param os tietovirta johon tulostetaan
     * @param merkinta tulostettava merkinta
     */
    public void tulosta(PrintStream os, final Merkinta merkinta) {
        os.println("----------------------------------------------");
        merkinta.tulosta(os); 
    }
    
    
    /**
     * Tulostaa listassa olevan merkinnän tekstialueeseen
     * @param text alue johon tulostetaan
     */
    public void tulostaValitut(TextArea text) {
        Merkinta merkinta = merkintaKohdalla;
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(text)) {
            os.println("Tulostetaan merkintä " + merkinta.getPvmDate());
            tulosta(os, merkinta);
        }
    }   

    
    
    /**
     * haetaan käyttäjän merkintöjen tiedot listaan
     * @param merkintaid sen merkinnän id-numero, joka haetaan
     */
    private void haeMerkinnat(int merkintaid) {
        chooserMerkinnat.clear();

        int index = 0;
        List<Merkinta> merkinnat = kayttajanUnipaivakirja.annaKayttajanMerkinnat(valittuKayttaja.getKayttajaId());
        //tässä haetaan merkinnöistä kaikki merkinnät joiden käyttäjäid täsmää tämänhetkiseen käyttäjään
        for (int i = 0; i < merkinnat.size(); i++) {
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
        lueTiedosto();
    }
    
    
    /**
     * Hakee tietyn merkinnän merkintälistasta hakuehdon perusteella
     * @param mnr merkinnän id-numero
     */
    protected void hae(int mnr) {
        int mnro = mnr; // mnro merkinnän numero, joka aktivoidaan haun jälkeen 
        if ( mnro <= 0 ) { 
            Merkinta kohdalla = merkintaKohdalla; 
            if ( kohdalla != null ) mnro = kohdalla.getMerkintaid(); 
        }
        
        String ehto = hakuehto.getText(); 
                
        chooserMerkinnat.clear();

        int index = 0;
        Collection<Merkinta> merkinnat;
        merkinnat = kayttajanUnipaivakirja.etsi(ehto, valittuKayttaja.getKayttajaId());
        int i = 0;
        for (Merkinta merkinta : merkinnat) {
            if (merkinta.getMerkintaid() == mnro) index = i;
            chooserMerkinnat.add(merkinta.getPvmDate().toString(), merkinta);
            i++;
        }
        chooserMerkinnat.setSelectedIndex(index); // tästä tulee muutosviesti joka näyttää merkinnän

    }

    
    /**
     * @return false
     */
    public boolean voikoSulkea() {
        // TODO Auto-generated method stub
        return false;
    }
    
    
    /**
     * @return testi
     */
    public String getResult() {
        return "testi";
    }

    
    /**
     * @param arg0 ??
     */
    public void setDefault(@SuppressWarnings("unused") String arg0) {
        // TODO Auto-generated method stub
        
    }
    
}

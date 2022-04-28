/**
 * 
 */
package fxUnipaivakirja;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.ohj2.Mjonot;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import unipaivakirja.Merkinta;
import unipaivakirja.Unenlaatu;
import unipaivakirja.Unipaivakirja;
import unipaivakirja.Vireystila;

/**
 * @author Omistaja
 * @version 27.4.2022
 *
 */
public class MerkintaDialogiController implements ModalControllerInterface<Merkinta>,Initializable{
    
    @FXML private TextField editHeratys;
    @FXML private TextArea editLisatiedot;
    @FXML private TextField editNukkumaan;
    @FXML private ComboBoxChooser<String> editUnenlaatu;
    @FXML private TextField editUnenmaara;
    @FXML private ComboBoxChooser<String> editVireystila;
    @FXML private DatePicker kalenteri;
    @FXML private GridPane gridMerkinta;
    @FXML private ScrollPane panelMerkinta;
    @FXML private Label labelVirhe;
    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        //
    }

    @FXML private void handlePeru() {
        merkintaKohdalla = null;
        ModalController.closeStage(labelVirhe);
    }

    @FXML private void handleOK() {
        tallennetaanko = true;
        tallennaMuutokset();
        /*if ( merkintaKohdalla != null && merkintaKohdalla.getPvmDate().toString().trim().equals("") ) {
            naytaVirhe("Nimi ei saa olla tyhjä");
            return;
        }*/
        ModalController.closeStage(labelVirhe);

    }
    
    
    //------------------------------------------------------------------------------------------
    
    private Merkinta merkintaKohdalla;
    private Unipaivakirja unipaivakirja;
    private static Merkinta apumerkinta = new Merkinta();
    private boolean tallennetaanko = false;
    private final Collection<String> unenlaatu = new ArrayList<String>(Arrays.asList("erittäin huono", "huono",
            "kohtalainen", "hyvä", 
            "erittäin hyvä"));
    private final Collection<String> vireystila = new ArrayList<String>(Arrays.asList("erittäin väsynyt", "väsynyt",
             "ihan jees", "pirteä", 
             "erittäin energinen"));

    
    /**
     * Tyhjentää tekstikentät
     */
    public void tyhjenna() {
        // voi tyhjentää kaikki kentät
    }
    
    
    /**
     * Tekee tarvittavat muut alustukset. Mm laittaa edit-kentistä tulevan
     * tapahtuman menemään kasitteleMuutosJaseneen-metodiin.
     */
    protected void alusta() {        
        /*editNukkumaan.setOnKeyReleased( e -> kasitteleMuutosMerkintaan((TextField)(e.getSource())));
        editHeratys.setOnKeyReleased( e -> kasitteleMuutosMerkintaan((TextField)(e.getSource())));
        editUnenmaara.setOnKeyReleased( e -> kasitteleMuutosMerkintaan((TextField)(e.getSource())));
        kalenteri.setOnKeyReleased( e -> kasitteleMuutosMerkintaan((DatePicker)(e.getSource())));
        editLisatiedot.setOnKeyReleased( e -> kasitteleMuutosMerkintaan((TextArea)(e.getSource())));
        editUnenlaatu.setOnKeyReleased( e -> kasitteleMuutosMerkintaan((ComboBoxChooser<Unenlaatu>)(e.getSource()), (ComboBoxChooser<Vireystila>)(e.getSource())));
        je je*/
        
        editUnenlaatu.clear();
        editVireystila.clear();
        for (String teksti : unenlaatu) {
            editUnenlaatu.add(teksti);
        }
        for (String teksti : vireystila) {
            editVireystila.add(teksti);
        }
    }
    
    
    public void tallennaMuutokset() {
        merkintaKohdalla.setPvmDate(kalenteri.getValue().toString());
        merkintaKohdalla.setHeratysKlo(editHeratys.getText());
        merkintaKohdalla.setNukkumaanKlo(editNukkumaan.getText());
        String unenmaara = merkintaKohdalla.laskeUnenmaara(editNukkumaan.getText(), editHeratys.getText());
        merkintaKohdalla.setUnenmaara(unenmaara);
        merkintaKohdalla.setLisatiedot(editLisatiedot.getText());
        merkintaKohdalla.setUnenlaatu(editUnenlaatu.getSelectedObject());
        merkintaKohdalla.setVireystila(editVireystila.getSelectedObject());
    }
    
    
    @Override
    public void setDefault(Merkinta oletus) {
        merkintaKohdalla = oletus;
    }

    
    @Override
    public Merkinta getResult() {
        if (tallennetaanko == false) return null;
        return merkintaKohdalla;
    }
    
    
    /**
     * Mitä tehdään kun dialogi on näytetty
     */
    @Override
    public void handleShown() {
        alusta();
        kalenteri.requestFocus();
        naytaMerkinta();
    }  

    
    /**
     * Näytetään merkinnän tiedot eri komponentteihin
     */
    public void naytaMerkinta() {
        if (merkintaKohdalla == null) return;
        editNukkumaan.setText(merkintaKohdalla.getUnenmaara());
        editHeratys.setText(merkintaKohdalla.getNukkumaanKlo());
        editUnenmaara.setText(merkintaKohdalla.getHeratysKlo());
        editLisatiedot.setText(merkintaKohdalla.getLisatiedot());
        kalenteri.setValue(merkintaKohdalla.getPvmDate());
        editUnenlaatu.setValue(null);
        editVireystila.setValue(null);
    }
    
    
    /**
     * Luodaan merkinnän kysymisdialogi ja palautetaan sama tietue muutettuna tai null
     * TODO: korjattava toimimaan
     * @param modalityStage mille ollaan modaalisia, null = sovellukselle
     * @param oletus mitä dataan näytetään oletuksena
     * @param unipaivakirja käyttäjän unipäiväkirja
     * @return null jos painetaan Cancel, muuten täytetty tietue
     */
    public static Merkinta kysyMerkinta(Stage modalityStage, Merkinta oletus, Unipaivakirja unipaivakirja) {
        return ModalController.<Merkinta, MerkintaDialogiController>showModal(
                    MerkintaDialogiController.class.getResource("MerkinnanMuokkausView.fxml"),
                    "Unipaivakirja",
                    modalityStage, oletus, ctrl -> ctrl.setUnipaivakirja(unipaivakirja) 
                );
    }

    
    private void setUnipaivakirja(Unipaivakirja unipaivakirja) {
        this.unipaivakirja = unipaivakirja;
    }
   

}

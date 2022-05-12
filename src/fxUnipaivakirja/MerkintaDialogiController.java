/**
 * 
 */
package fxUnipaivakirja;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import unipaivakirja.Merkinta;
import unipaivakirja.Unipaivakirja;

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
        if (!tallennaMuutokset()) return;
        tallennetaanko = true;
        ModalController.closeStage(labelVirhe);

    }
    
    
    //------------------------------------------------------------------------------------------
    
    private Merkinta merkintaKohdalla;
    private Unipaivakirja unipaivakirja;
    private boolean tallennetaanko = false;
    private final List<String> unenlaatu = new ArrayList<String>(Arrays.asList("erittäin huono", "huono",
            "kohtalainen", "hyvä", 
            "erittäin hyvä"));
    private final List<String> vireystila = new ArrayList<String>(Arrays.asList("erittäin väsynyt", "väsynyt",
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
        editUnenlaatu.clear();
        editVireystila.clear();
        for (int i = 0; i < unenlaatu.size(); i++) {
            editUnenlaatu.add(unenlaatu.get(i));
            if (unenlaatu.get(i).equals(merkintaKohdalla.getUnenlaatu()))
                editUnenlaatu.setSelectedIndex(i);
        }
        for (int i = 0; i < vireystila.size(); i++) {
            editVireystila.add(vireystila.get(i));
            if (vireystila.get(i).equals(merkintaKohdalla.getVireystila()))
                editVireystila.setSelectedIndex(i);
        }
    }
    
    
    /**
     * Tallentaa unipäiväkirjamerkinnän editointikenttiin tehdyt muutokset.
     * @return true, jos tallennetaan, muuten false
     */
    public boolean tallennaMuutokset() {
        String virhe = null;
        virhe = merkintaKohdalla.setPvmDate(kalenteri.getValue().toString());
        if (virhe != null) {
            naytaVirhe(virhe); return false;
        }
        virhe = merkintaKohdalla.setNukkumaanKlo(editNukkumaan.getText());
        if (virhe != null) {
            naytaVirhe(virhe); return false;
        }
        virhe = merkintaKohdalla.setHeratysKlo(editHeratys.getText());
        if (virhe != null) {
            naytaVirhe(virhe); return false;
        }
        String unenmaara = merkintaKohdalla.laskeUnenmaara(editNukkumaan.getText(), editHeratys.getText());
        virhe = merkintaKohdalla.setUnenmaara(unenmaara);
        if (virhe != null) {
            naytaVirhe(virhe); return false;
        }
        virhe = merkintaKohdalla.setLisatiedot(editLisatiedot.getText());
        if (virhe != null) {
            naytaVirhe(virhe); return false;
        }
        virhe = merkintaKohdalla.setUnenlaatu(editUnenlaatu.getSelectedObject());
        if (virhe != null) {
            naytaVirhe(virhe); return false;
        }
        virhe = merkintaKohdalla.setVireystila(editVireystila.getSelectedObject());
        if (virhe != null) {
            naytaVirhe(virhe); return false;
        }
        return true;
    }
    
    
    /**
     * Näyttää virheen merkinnänmuokkaus-dialogissa
     * @param virhe virheteksti joka näytetään
     */
    private void naytaVirhe(String virhe) {
        if ( virhe == null || virhe.isEmpty() ) {
            labelVirhe.setText("");
            labelVirhe.getStyleClass().removeAll("virhe");
            return;
        }
        labelVirhe.setText(virhe);
        labelVirhe.getStyleClass().add("virhe");
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
        editNukkumaan.setText(merkintaKohdalla.getNukkumaanKlo());
        editHeratys.setText(merkintaKohdalla.getHeratysKlo());
        editUnenmaara.setText(merkintaKohdalla.getUnenmaara());
        editLisatiedot.setText(merkintaKohdalla.getLisatiedot());
        kalenteri.setValue(merkintaKohdalla.getPvmDate());
    }
    
    
    /**
     * Luodaan merkinnän kysymisdialogi ja palautetaan sama tietue muutettuna tai null
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

    
    /**
     * Asettaa unipäiväkirjan
     * @param unipaivakirja asetettava unipäiväkirja
     */
    private void setUnipaivakirja(Unipaivakirja unipaivakirja) {
        this.unipaivakirja = unipaivakirja;
    }
   

}

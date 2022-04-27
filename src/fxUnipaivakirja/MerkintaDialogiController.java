/**
 * 
 */
package fxUnipaivakirja;

import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import unipaivakirja.Merkinta;

/**
 * @author Omistaja
 * @version 27.4.2022
 *
 */
public class MerkintaDialogiController implements ModalControllerInterface<Merkinta>,Initializable{
    
    @FXML private TextField editHeratys;
    @FXML private TextArea editLisatiedot;
    @FXML private TextField editNukkumaan;
    @FXML private ComboBoxChooser<?> editUnenlaatu;
    @FXML private TextField editUnenmaara;
    @FXML private ComboBoxChooser<?> editVireystila;
    @FXML private DatePicker kalenteri;
    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();
    }

    @FXML void handlePeru() {
        merkintaKohdalla = null;
        ModalController.closeStage(null);
    }

    @FXML
    void handleTallenna() {
        
    }
    
    
    //------------------------------------------------------------------------------------------
    
    private Merkinta merkintaKohdalla;


}

package fxUnipaivakirja;

import fi.jyu.mit.fxgui.Dialogs;
import javafx.application.Platform;
import javafx.fxml.FXML;

/**
 * @author Omistaja
 * @version 10.2.2022
 *
 */
public class UnipaivakirjaGUIController {

    @FXML void handleApua() {
        //apua();
    }

    @FXML void handleAvaa() {
        //avaa();
    }

    @FXML void handleHaku() {
        //hae();
    }

    @FXML void handleLopeta() {
        tallenna();
        Platform.exit();
    }

    @FXML void handleOhje() {
        //ohje();
    }

    @FXML void handlePoistaKayttaja() {
        //poistaKayttaja();
    }

    @FXML void handlePoistaMerkinta() {
        //poistaMerkinta();
    }

    @FXML void handlePoistu() {
        tallenna();
        Platform.exit();
    }

    @FXML void handleTallenna() {
        tallenna();
    }

    @FXML void handleTietoja() {
        //tietoja();
    }

    @FXML void handleTulosta() {
        //tulosta();
    }

    @FXML void handleUusiKayttaja() {
        //uusiKayttaja();
    }

    @FXML void handleUusiMerkinta() {
        //uusiMerkinta();
    }
    
    //------------------------------------------------------------
    
    private void tallenna() {
        Dialogs.showMessageDialog("Tallennetaan, mutta ei toimi vielä!");
    }
    
    
    /*private boolean avaa() {
         
    }*/

}

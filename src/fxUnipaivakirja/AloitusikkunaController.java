/**
 * 
 */
package fxUnipaivakirja;

import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import unipaivakirja.Kayttaja;
import unipaivakirja.SailoException;
import unipaivakirja.Unipaivakirja;


/**
 * @author Omistaja
 * @version 28.3.2022
 *
 */
public class AloitusikkunaController implements ModalControllerInterface<Kayttaja>{
    
    @FXML private ComboBoxChooser<Kayttaja> kayttajaValinta;
    
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
        listaaKayttajat();
    }

    
    
    //------------------------------------------------------
    
    private Unipaivakirja kayttajanUnipaivakirja;
    private Kayttaja valittuKayttaja;
    
    
    /**
     * Avaa valitun käyttäjän unipäiväkirjan
     */
    public void avaaKayttajanPaivakirja() {
        valittuKayttaja = kayttajaValinta.getSelectedObject();
        ModalController.closeStage(kayttajaValinta);
    } 
    
    
    /**
     * @param unipaivakirja jota käytetään tässä käyttöliittymässä
     */
    public void setUnipaivakirja(Unipaivakirja unipaivakirja) {
        this.kayttajanUnipaivakirja = unipaivakirja;
    }
    
    
    /**
     * Listaa käyttäjät käyttäjä-valikkoon
     */
    public void listaaKayttajat() {
        kayttajaValinta.clear();
        for (Kayttaja kayttaja : kayttajanUnipaivakirja.annaKayttajat()) {
            kayttajaValinta.add(kayttaja.getNimi(), kayttaja);
        }
    }
    
    
    /**
     * Alustaa tietyn käyttäjän unipäiväkirjan lukemalla sen 
     * valitun nimisestä tiedostosta
     * @param tiedosto tiedosto, mistä käyttäjät luetaan
     * @return null, jos tiedoston luku onnistuu, jos ei niin virhe
     */
    protected String lueTiedosto(String tiedosto) {
        try {
            kayttajanUnipaivakirja.lueTiedostosta(tiedosto);
            return null;
        } catch (SailoException e) {
            String virhe = e.getMessage(); 
            if ( virhe != null ) Dialogs.showMessageDialog(virhe);
            return virhe;
        }
    }
    
    /**
     * Luo uuden käyttäjän kysymis -dialogin. Tallentaa uuden käyttäjän tietorakenteeseen.
     */
    private void uusiKayttaja() {
        Kayttaja uusiKayttaja = ModalController.<Kayttaja,UusiKayttajaController>showModal(UnipaivakirjaGUIController.class.getResource(
                "Uusikayttaja.fxml"), "Luo uusi käyttäjä", null, null, ctrl -> ctrl.setUnipaivakirja(kayttajanUnipaivakirja));
        if (uusiKayttaja == null) return;
        valittuKayttaja = uusiKayttaja;
        ModalController.closeStage(kayttajaValinta);
    }

    
    @Override
    public Kayttaja getResult() {
        return valittuKayttaja;
    }

    
    @Override
    public void setDefault(Kayttaja arg0) {
        // TODO Auto-generated method stub
        
    }

}

package fxUnipaivakirja;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import unipaivakirja.Unipaivakirja;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * @author Omistaja
 * @version 20.1.2022
 *
 */
public class UnipaivakirjaMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {                                                    //aloitusikkuna.fxml??
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("UnipaivakirjaGUIView.fxml"));
            final Pane root = ldr.load();
            final UnipaivakirjaGUIController unipaivakirjaCtrl = (UnipaivakirjaGUIController) ldr.getController();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("unipaivakirja.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Unipaivakirja");
            primaryStage.show();
            
            primaryStage.setOnCloseRequest((event) -> {
                if (!unipaivakirjaCtrl.voikoSulkea()) event.consume();
            });
            
            Unipaivakirja unipaivakirja = new Unipaivakirja();
            unipaivakirjaCtrl.setUnipaivakirja(unipaivakirja);
            
            if (!unipaivakirjaCtrl.avaaKayttajanPaivakirja()) Platform.exit();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Käynnistetään käyttöliittymä
     * @param args Ei käytössä
     */
    public static void main(String[] args) {
        launch(args);
    }
}
package it.polimi.ingsw.Client.views.GUI;

import it.polimi.ingsw.Client.views.View;
import it.polimi.ingsw.messages.AnsPlayerAlrUsedCCExcMsg;
import it.polimi.ingsw.messages.CannotPlayCCMsg;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class PlayerAlrUsedCCExcScene extends View {
    private AnsPlayerAlrUsedCCExcMsg playerAlrUsedCCExcMsg;

    public PlayerAlrUsedCCExcScene(AnsPlayerAlrUsedCCExcMsg playerAlrUsedCCExcMsg) {
        this.playerAlrUsedCCExcMsg = playerAlrUsedCCExcMsg;
    }
    @Override
    public void run(){
        Platform.runLater(() -> {
            CannotPlayCCMsg ccMsg= new CannotPlayCCMsg();
            JavaFXMain.getCurrentApplication().getClient().getServerHandler().sendCommandMessage(ccMsg);
            Alert alert = new Alert(Alert.AlertType.ERROR, "Hai già usato questa carta!", ButtonType.OK);
            alert.showAndWait();
        });
    }
}

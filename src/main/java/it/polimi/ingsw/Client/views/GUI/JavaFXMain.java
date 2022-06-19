package it.polimi.ingsw.Client.views.GUI;

import it.polimi.ingsw.Client.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;

public class JavaFXMain extends Application {


    private Stage primaryStage;
    private static JavaFXMain currentApplication;
    private Client client;


    private int nplayers;

    public static void main(String[] args)
    {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        currentApplication = this;
        this.primaryStage = primaryStage;
        client = new Client();
       /*
        primaryStage.setOnCloseRequest((event) -> {
            if (serverHandler.isConnected())
                serverHandler.closeConnection();
        });*/
        /*
        this.serverHandler = new ServerHandler();
        /*
        this.serverHandler.setConnectionClosedObserver(() -> {
            Platform.runLater(() -> {
                if (primaryStage.isShowing()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "The connection was closed.", ButtonType.OK);
                    alert.showAndWait();
                    switchToLoginScene();
                }
            });
        });*/
        switchToConnectionScene();
        primaryStage.show();
    }

    public static JavaFXMain getCurrentApplication() {
        return currentApplication;
    }


    public Client getClient() {
        return client;
    }


    public void switchToLoginScene()
    {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/LoginScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Scene sc = new Scene(root);
        primaryStage.setScene(sc);
        primaryStage.setTitle("Login");
        primaryStage.sizeToScene();
    }

    public void switchToConnectionScene()
    {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/ConnectionScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Scene sc = new Scene(root);
        primaryStage.setScene(sc);
        primaryStage.setTitle("Connection");
        primaryStage.sizeToScene();
    }

    public void switchToExpertModeScene()
    {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/ExpertModeScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Scene sc = new Scene(root);
        primaryStage.setScene(sc);
        primaryStage.setTitle("Login");
        primaryStage.sizeToScene();
    }
    public void switchToWaitingScene()
    {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/WaitingScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Scene sc = new Scene(root);
        primaryStage.setScene(sc);
        primaryStage.setTitle("Login");
        primaryStage.sizeToScene();
    }
    public void switchToNumberOfPlayerScene()
    {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/NumberOfPlayerScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Scene sc = new Scene(root);
        primaryStage.setScene(sc);
        primaryStage.setTitle("NumberOfPlayer");
        primaryStage.sizeToScene();
    }

    public void switchToChoiceCharacterCardScene()
    {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/ChoiceCharacterCardScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Scene sc = new Scene(root);
        primaryStage.setScene(sc);
        primaryStage.setTitle("CharacterCard");
        primaryStage.sizeToScene();
    }

    public void switchToIslandTitleScene()
    {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/IslandTiles.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Scene sc = new Scene(root);
        primaryStage.setScene(sc);
        primaryStage.setTitle("CharacterCard");
        primaryStage.sizeToScene();
    }

    public void switchToCardAssistantScene()
    {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/AssistantCardScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Scene sc = new Scene(root);
        primaryStage.setScene(sc);
        primaryStage.setTitle("CharacterCard");
        primaryStage.sizeToScene();
    }
    public void switchToShowPlayerScene()
    {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/ShowPlayerScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Scene sc = new Scene(root);
        primaryStage.setScene(sc);
        primaryStage.setTitle("CharacterCard");
        primaryStage.sizeToScene();
    }

    public void switchToTurnDecideScene()
    {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/TurnDecidedScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Scene sc = new Scene(root);
        primaryStage.setScene(sc);
        primaryStage.setTitle("orderofPlayers");
        primaryStage.sizeToScene();
    }

    public int getNplayers() {
        return nplayers;
    }

    public void setNplayers(int nplayers) {
        this.nplayers = nplayers;
    }
    public void switchToMoveStudent1Scene()
    {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/MoveStudent1Scene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Scene sc = new Scene(root);
        primaryStage.setScene(sc);
        primaryStage.setTitle("CharacterCard");
        primaryStage.sizeToScene();
    }
    public void switchToMoveStudent2Scene()
    {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/MoveStudent2Scene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Scene sc = new Scene(root);
        primaryStage.setScene(sc);
        primaryStage.setTitle("Eryantis");
        primaryStage.sizeToScene();
    }
    public void switchToMoveStudent3Scene()
    {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/MoveStudent3Scene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Scene sc = new Scene(root);
        primaryStage.setScene(sc);
        primaryStage.setTitle("Eryantis");
        primaryStage.sizeToScene();
    }
    public void switchToMoveStudent4Scene()
    {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/MoveStudent4Scene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Scene sc = new Scene(root);
        primaryStage.setScene(sc);
        primaryStage.setTitle("Eryantis");
        primaryStage.sizeToScene();
    }

    public void switchToMS1DiningOrIslandScene()
    {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/MS1DiningOrIslandScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Scene sc = new Scene(root);
        primaryStage.setScene(sc);
        primaryStage.setTitle("Eryantis");
        primaryStage.sizeToScene();
    }
    public void switchToMS2DiningOrIslandScene()
    {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/MS2DiningOrIslandScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Scene sc = new Scene(root);
        primaryStage.setScene(sc);
        primaryStage.setTitle("Eryantis");
        primaryStage.sizeToScene();
    }
    public void switchToMS3DiningOrIslandScene()
    {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/MS3DiningOrIslandScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Scene sc = new Scene(root);
        primaryStage.setScene(sc);
        primaryStage.setTitle("Eryantis");
        primaryStage.sizeToScene();
    }
    public void switchToMS4DiningOrIslandScene()
    {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/MS4DiningOrIslandScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Scene sc = new Scene(root);
        primaryStage.setScene(sc);
        primaryStage.setTitle("Eryantis");
        primaryStage.sizeToScene();
    }
    public void switchToAskForMotherNatureScene()
    {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/AskForMotherNatureScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Scene sc = new Scene(root);
        primaryStage.setScene(sc);
        primaryStage.setTitle("Eryantis");
        primaryStage.sizeToScene();
    }
    public void switchToAskForMN3PlayersScene()
    {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/AskForMN3PlayersScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Scene sc = new Scene(root);
        primaryStage.setScene(sc);
        primaryStage.setTitle("Eryantis");
        primaryStage.sizeToScene();
    }


}

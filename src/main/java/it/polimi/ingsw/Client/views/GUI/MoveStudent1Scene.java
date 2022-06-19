package it.polimi.ingsw.Client.views.GUI;

import it.polimi.ingsw.Model.Color;
import it.polimi.ingsw.Model.IslandTiles;
import it.polimi.ingsw.messages.AnsAskCAMsg;
import it.polimi.ingsw.messages.AnsFirstPlayerTurnMsg;
import it.polimi.ingsw.messages.MoveStudent1Msg;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.*;


public class MoveStudent1Scene {
    private static Color color;
    private static int location;
    @FXML
    private ImageView imageIsland;
    @FXML
    private ImageView imageFirstSchoolBoard;
    @FXML
    private ImageView imageSecondSchoolBoard;
    @FXML
    private ImageView imageThirdSchoolBoard;
    @FXML
    private ImageView imageFourthSchoolBoard;
    @FXML
    private Label showSchoolBoard1;
    @FXML
    private Label showSchoolBoard2;
    @FXML
    private Label showSchoolBoard3;
    @FXML
    private Label showSchoolBoard4;

    private static AnsAskCAMsg answerMsg;

    public static void setAnswerMsg(AnsAskCAMsg answerMsg) {
        MoveStudent1Scene.answerMsg = answerMsg;
    }

    public void initialize() {
        if(answerMsg.GetPlayers().size()==2){
            imageFourthSchoolBoard.setImage(null);
            imageThirdSchoolBoard.setImage(null);
            imageFourthSchoolBoard.setOnMouseClicked(null);
            imageThirdSchoolBoard.setOnMouseClicked(null);
            showSchoolBoard4.setText("");
            showSchoolBoard3.setText("");
            showSchoolBoard1.setText("Plancia di" + answerMsg.GetPlayers().get(0));
            showSchoolBoard2.setText("Plancia di" + answerMsg.GetPlayers().get(1));
        }else if(answerMsg.GetPlayers().size()==3){
            imageFourthSchoolBoard.setImage(null);
            imageFourthSchoolBoard.setOnMouseClicked(null);
            showSchoolBoard4.setText("");
            showSchoolBoard1.setText("Plancia di" + answerMsg.GetPlayers().get(0));
            showSchoolBoard2.setText("Plancia di" + answerMsg.GetPlayers().get(1));
            showSchoolBoard3.setText("Plancia di" + answerMsg.GetPlayers().get(2));
        }else{
            showSchoolBoard1.setText("Plancia di" + answerMsg.GetPlayers().get(0));
            showSchoolBoard2.setText("Plancia di" + answerMsg.GetPlayers().get(1));
            showSchoolBoard3.setText("Plancia di" + answerMsg.GetPlayers().get(2));
            showSchoolBoard4.setText("Plancia di" + answerMsg.GetPlayers().get(3));
        }
    }


    public void BlueSelected(){
        color=Color.Blue;
        JavaFXMain.getCurrentApplication().switchToMS1DiningOrIslandScene();
    }
    public void YellowSelected(){
        color=Color.Yellow;
        JavaFXMain.getCurrentApplication().switchToMS1DiningOrIslandScene();
    }
    public void GreenSelected(){
        color=Color.Green;
        JavaFXMain.getCurrentApplication().switchToMS1DiningOrIslandScene();
    }
    public void RedSelected(){
        color=Color.Red;
        JavaFXMain.getCurrentApplication().switchToMS1DiningOrIslandScene();
    }
    public void PinkSelected(){
        color=Color.Pink;
        JavaFXMain.getCurrentApplication().switchToMS1DiningOrIslandScene();
    }
    public void DiningClicked(ActionEvent event){
        location=12;
        MoveStudent1Msg moveStudent1Msg= new MoveStudent1Msg(color, location);
        JavaFXMain.getCurrentApplication().getClient().getServerHandler().sendCommandMessage(moveStudent1Msg);
    }
    public void IslandClicked(ActionEvent event){
        IslandTilesScene.setOnlyObserv(false);
        JavaFXMain.getCurrentApplication().switchToIslandTitleScene();
    }

    public void showGeneralBoardSelected(){
        IslandTilesScene.setOnlyObserv(true);
        IslandTilesScene.setNumberOfSceneToComeBack(1);
        IslandTilesScene.setIslands(answerMsg.GetGB().GetIslands());

    }

    public void showSchoolBoard1Selected(){
        SchoolBoardScene.setSchoolBoardToShow(answerMsg.GetGB().getSchoolBoard().get(0));
        SchoolBoardScene.setNumberOfSceneToComeBack(1);
        JavaFXMain.getCurrentApplication().switchToSchoolBoardScene();

    }
    public void showSchoolBoard2Selected(){
        SchoolBoardScene.setSchoolBoardToShow(answerMsg.GetGB().getSchoolBoard().get(1));
        SchoolBoardScene.setNumberOfSceneToComeBack(1);
        JavaFXMain.getCurrentApplication().switchToSchoolBoardScene();

    }
    public void showSchoolBoard3Selected(){
        SchoolBoardScene.setSchoolBoardToShow(answerMsg.GetGB().getSchoolBoard().get(2));
        SchoolBoardScene.setNumberOfSceneToComeBack(1);
        JavaFXMain.getCurrentApplication().switchToSchoolBoardScene();
    }

    public void showSchoolBoard4Selected(){
        SchoolBoardScene.setSchoolBoardToShow(answerMsg.GetGB().getSchoolBoard().get(3));
        SchoolBoardScene.setNumberOfSceneToComeBack(1);
        JavaFXMain.getCurrentApplication().switchToSchoolBoardScene();
    }


}

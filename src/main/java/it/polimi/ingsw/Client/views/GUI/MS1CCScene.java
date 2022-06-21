package it.polimi.ingsw.Client.views.GUI;

import it.polimi.ingsw.Model.Color;
import it.polimi.ingsw.messages.AnsAskCAMsg;
import it.polimi.ingsw.messages.AnsPlayAfterCCMsg;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class MS1CCScene {
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

    private static AnsPlayAfterCCMsg answerMsg;

    public static void setAnswerMsg(AnsPlayAfterCCMsg answerMsg) {
        MS1CCScene.answerMsg = answerMsg;
    }

    public static AnsPlayAfterCCMsg getAnswerMsg() {
        return answerMsg;
    }

    public static Color getColor() {
        return color;
    }

    public void initialize() {
        if(answerMsg.GetPlayers().size()==2){
            imageFourthSchoolBoard.setVisible(false);
            imageFourthSchoolBoard.setOnMouseClicked(null);
            imageThirdSchoolBoard.setOnMouseClicked(null);
            imageThirdSchoolBoard.setVisible(false);
            showSchoolBoard4.setText("");
            showSchoolBoard3.setText("");
            showSchoolBoard1.setText("Plancia di " + answerMsg.GetPlayers().get(0).getNickName());
            showSchoolBoard2.setText("Plancia di " + answerMsg.GetPlayers().get(1).getNickName());
        }else if(answerMsg.GetPlayers().size()==3){
            imageFourthSchoolBoard.setVisible(false);
            imageFourthSchoolBoard.setOnMouseClicked(null);
            showSchoolBoard4.setText("");
            showSchoolBoard1.setText("Plancia di " + answerMsg.GetPlayers().get(0));
            showSchoolBoard2.setText("Plancia di " + answerMsg.GetPlayers().get(1));
            showSchoolBoard3.setText("Plancia di " + answerMsg.GetPlayers().get(2));
        }else{
            showSchoolBoard1.setText("Plancia di " + answerMsg.GetPlayers().get(0));
            showSchoolBoard2.setText("Plancia di " + answerMsg.GetPlayers().get(1));
            showSchoolBoard3.setText("Plancia di " + answerMsg.GetPlayers().get(2));
            showSchoolBoard4.setText("Plancia di " + answerMsg.GetPlayers().get(3));
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

    public void showGeneralBoardSelected(){
        IslandTilesScene.setOnlyObserv(true);
        IslandTilesScene.setNumberOfSceneToComeBack(1);
        IslandTilesScene.setIslands(answerMsg.GetGB().GetIslands());
        JavaFXMain.getCurrentApplication().switchToIslandTitleScene();
    }

    public void showSchoolBoard1Selected(){

        SchoolBoardScene.setNumberOfSceneToComeBack(1);
        SchoolBoardScene.setPlayer(answerMsg.GetPlayers().get(0));
        JavaFXMain.getCurrentApplication().switchToSchoolBoardScene();

    }
    public void showSchoolBoard2Selected(){

        SchoolBoardScene.setNumberOfSceneToComeBack(1);
        SchoolBoardScene.setPlayer(answerMsg.GetPlayers().get(1));
        JavaFXMain.getCurrentApplication().switchToSchoolBoardScene();

    }
    public void showSchoolBoard3Selected(){

        SchoolBoardScene.setNumberOfSceneToComeBack(1);
        SchoolBoardScene.setPlayer(answerMsg.GetPlayers().get(2));
        JavaFXMain.getCurrentApplication().switchToSchoolBoardScene();
    }

    public void showSchoolBoard4Selected(){
        SchoolBoardScene.setNumberOfSceneToComeBack(1);
        SchoolBoardScene.setPlayer(answerMsg.GetPlayers().get(3));
        JavaFXMain.getCurrentApplication().switchToSchoolBoardScene();
    }
}
package it.polimi.ingsw.messages;

import it.polimi.ingsw.Client.ServerHandler;
import it.polimi.ingsw.Client.views.CC11View;
import it.polimi.ingsw.Client.views.CC3View;
import it.polimi.ingsw.Client.views.CC9View;
import it.polimi.ingsw.Client.views.GUI.CC11SceneView;
import it.polimi.ingsw.Model.CharacterCard;
import it.polimi.ingsw.Model.GeneralBoard;
import it.polimi.ingsw.Model.Player;

import java.util.ArrayList;

public class AnsCC11Msg extends AnswerMsg{
    private GeneralBoard generalBoard;
    private ArrayList<Player> players= new ArrayList<Player>();
    private CharacterCard characterCard;



    public AnsCC11Msg(CommandMsg parent, GeneralBoard generalBoard,ArrayList<Player> players, CharacterCard characterCard){
        super(parent);
        this.generalBoard= generalBoard;
        this.players=players;
        this.characterCard=characterCard;
    }
    @Override
    public void processMessage(ServerHandler serverHandler)
    {
        CC11SceneView c= new CC11SceneView(this);
        c.run();
        //serverHandler.getClient().transitionToView(new CC11View(this));
    }
    public GeneralBoard GetGB(){return generalBoard;}
    public ArrayList<Player> GetPlayers(){return players;}
    public CharacterCard GetCharacterCard() {
        return characterCard;
    }
}

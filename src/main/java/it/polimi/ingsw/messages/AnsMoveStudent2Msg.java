package it.polimi.ingsw.messages;

import it.polimi.ingsw.Client.ServerHandler;
import it.polimi.ingsw.Client.views.MoveStudent2View;
import it.polimi.ingsw.Client.views.MoveStudent3View;

public class AnsMoveStudent2Msg extends AnswerMsg{
    private String name;

    public AnsMoveStudent2Msg(CommandMsg parent, String name){
        super(parent);
        this.name = name;
    }
    @Override
    public void processMessage(ServerHandler serverHandler)
    {
        serverHandler.getClient().transitionToView(new MoveStudent3View(this));
    }
    public String GetPlayer(){
        return name;
    }
}

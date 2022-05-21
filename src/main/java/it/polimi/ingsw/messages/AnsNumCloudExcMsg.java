package it.polimi.ingsw.messages;

import it.polimi.ingsw.Client.ServerHandler;
import it.polimi.ingsw.Client.views.NumCloudExcView;
import it.polimi.ingsw.Client.views.NumStepExcView;

public class AnsNumCloudExcMsg extends AnswerMsg{
    private String name;

    public AnsNumCloudExcMsg(CommandMsg parent, String name){
        super(parent);
        this.name = name;
    }
    @Override
    public void processMessage(ServerHandler serverHandler)
    {
        serverHandler.getClient().transitionToView(new NumCloudExcView(this));
    }
    public String GetPlayer(){
        return name;
    }
}
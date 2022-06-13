package it.polimi.ingsw.messages;

import it.polimi.ingsw.Client.ServerHandler;
import it.polimi.ingsw.Client.views.LobbyView;
import it.polimi.ingsw.Client.views.PrintAnswerView;

public class AnsGameCreatedMsg extends AnswerMsg{
    public enum Status
    {
        INVALID,
        VALID
    }

    AnsGameCreatedMsg.Status MoveStatus;
    public AnsGameCreatedMsg(CommandMsg parent, AnsGameCreatedMsg.Status MoveStatus)
    {
        super(parent);
        this.MoveStatus = MoveStatus;

    }

    @Override
    public void processMessage(ServerHandler serverHandler)
    {
        System.out.println("ok bro");
       /** serverHandler.getClient().transitionToView(new LobbyView(this)); CLI NON CANCELLARE*/
    }

    public AnsGameCreatedMsg.Status getMoveStatus() {
        return MoveStatus;
    }
}



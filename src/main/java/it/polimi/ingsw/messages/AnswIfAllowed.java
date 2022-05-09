package it.polimi.ingsw.messages;

import it.polimi.ingsw.Client.ServerHandler;
import it.polimi.ingsw.Client.views.PrintAnswerView;

public class AnswIfAllowed extends AnswerMsg {

    public enum Status
    {
        INVALID,
        VALID
    }

    Status MoveStatus;
    public AnswIfAllowed(CommandMsg parent, Status MoveStatus)
    {
        super(parent);
        this.MoveStatus = MoveStatus;

    }

    @Override
    public void processMessage(ServerHandler serverHandler)
    {
        serverHandler.getClient().transitionToView(new PrintAnswerView(this));
    }

    public Status getMoveStatus() {
        return MoveStatus;
    }
}

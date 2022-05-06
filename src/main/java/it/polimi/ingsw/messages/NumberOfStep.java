package it.polimi.ingsw.messages;

import it.polimi.ingsw.Controller.*;
import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Server.*;

import java.io.IOException;

public class NumberOfStep extends CommandMsg{
    private int Steps;
    private Player player;
    private CharacterCard CC;

    public NumberOfStep(int steps, Player player, CharacterCard cc) throws IOException {
        Steps = steps;
        this.player = player;
        CC= cc;
    }

    @Override
    public void processMessage(ClientHandler clientHandler)
    {
        AnswIfAllowed answerMsg;
        GameController game = clientHandler.getGame();


    if (true /*game.CheckNumberOfSteps(Steps, player, CC)*/) {
      answerMsg = new AnswIfAllowed(this, AnswIfAllowed.Status.VALID);
    } else {
        answerMsg = new AnswIfAllowed(this, AnswIfAllowed.Status.INVALID);

    }

   /* clientHandler.sendAnswerMessage(answerMsg);*/
  }

}

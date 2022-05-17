package it.polimi.ingsw.messages;

import it.polimi.ingsw.Controller.GameController;
import it.polimi.ingsw.Server.ClientHandler;

import java.io.IOException;
import java.util.ArrayList;

public class AskCAMsg extends CommandMsg{
    public AskCAMsg(){}
    @Override
    public void processMessage(ClientHandler clientHandler) throws IOException {
        AnsAskCAMsg answerMsg;
        GameController game = clientHandler.getGame();
        String nickname;
        synchronized (game) {
            Boolean flag = game.CheckIfAllPlayedCardAssistant();
            try {
                while (!clientHandler.getNickname().equals(game.getChoosenPlayer().GetPlayerTurn().getNickName())) {
                    System.out.println("dormo " + clientHandler.getNickname());
                    game.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            nickname= game.getChoosenPlayer().GetPlayerTurn().getNickName();
            answerMsg = new AnsAskCAMsg(this, nickname);
            clientHandler.sendAnswerMessage(answerMsg);
        }
    }
}

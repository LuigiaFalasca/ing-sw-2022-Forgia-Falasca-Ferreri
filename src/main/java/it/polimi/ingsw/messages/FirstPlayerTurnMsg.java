package it.polimi.ingsw.messages;

import it.polimi.ingsw.Controller.GameController;
import it.polimi.ingsw.Model.Player;
import it.polimi.ingsw.Server.ClientHandler;

import java.io.IOException;

public class FirstPlayerTurnMsg extends CommandMsg {
    @Override
    public void processMessage(ClientHandler clientHandler) throws IOException {
        AnsFirstPlayerTurnMsg answerMsg;
        GameController game = clientHandler.getGame();
        String name;
        synchronized (game) {
            if (!game.getSetFirstTurn()) {
                game.SetFirstPlayerTurn();
                game.setSetFirstTurn(true);
            }
            System.out.println("tra poco dormo");
            if(!game.getChoosenPlayer().EndOfAllTurn()) {
                name = game.getChoosenPlayer().GetPlayerTurn().getNickName();
                try{
                    while(!clientHandler.getNickname().equals(name)) {
                        System.out.println("dormo "+clientHandler.getNickname() + " "+ name );
                        game.wait();
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                game.notifyAll();
                answerMsg = new AnsFirstPlayerTurnMsg(this, name);
                System.out.println("mi sveglio");
                clientHandler.sendAnswerMessage(answerMsg);
            }
        }
    }
}

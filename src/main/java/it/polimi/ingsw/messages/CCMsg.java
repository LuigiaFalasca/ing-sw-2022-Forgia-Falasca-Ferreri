package it.polimi.ingsw.messages;

import it.polimi.ingsw.Controller.GameController;
import it.polimi.ingsw.Exception.CharacterCardNotInTableException;
import it.polimi.ingsw.Exception.NotEnoughCoinException;
import it.polimi.ingsw.Exception.PlayerAlreadyUsedCharacterCard;
import it.polimi.ingsw.Server.ClientHandler;

import java.io.IOException;

public class CCMsg extends CommandMsg{
    private int numcard;
    public CCMsg(int numcard){
        this.numcard= numcard;
    }

    @Override
    public void processMessage(ClientHandler clientHandler) throws IOException {
        GameController game = clientHandler.getGame();
        synchronized (game) {
            if(game.CheckIfShouldAskForCharacterCard(game.getChoosenPlayer().GetPlayerTurn())){
                try{
                    game.CharacterCardInTable(numcard);
                    try{
                        game.CheckIfPlayerCanPlayCharacterCard(game.getChoosenPlayer().GetPlayerTurn());
                        try{
                            game.CheckIfEnoughMoney(game.getChoosenPlayer().GetPlayerTurn(), game.getCharacterCardChosen());
                            if(numcard==1){
                                AnsCC1Msg ansCC1Msg=new AnsCC1Msg(this,game.getGameModel().getGeneralBoard(), game.getGameModel().getPlayers(), game.getCharacterCardChosen());
                                clientHandler.sendAnswerMessage(ansCC1Msg);
                            }else if(numcard==2){
                                game.UseEffectOfCharacterCard(game.getChoosenPlayer().GetPlayerTurn(), game.getCharacterCardChosen());
                                AnsPlayAfterCCMsg answerMsg = new AnsPlayAfterCCMsg(this, game.getChoosenPlayer().GetPlayerTurn().getNickName(), game.getGameModel().getGeneralBoard(), game.getGameModel().getPlayers(), game.getCountmodexpview());
                                clientHandler.sendAnswerMessage(answerMsg);
                            }else if(numcard==3){
                                AnsCC3Msg ansCC3Msg= new AnsCC3Msg(this, game.getGameModel().getGeneralBoard(), game.getGameModel().getPlayers());
                                clientHandler.sendAnswerMessage(ansCC3Msg);
                            }else if(numcard==4){
                                game.UseEffectOfCharacterCard(game.getChoosenPlayer().GetPlayerTurn(), game.getCharacterCardChosen());
                                AnsPlayAfterCCMsg answerMsg = new AnsPlayAfterCCMsg(this, game.getChoosenPlayer().GetPlayerTurn().getNickName(), game.getGameModel().getGeneralBoard(), game.getGameModel().getPlayers(), game.getCountmodexpview());
                                clientHandler.sendAnswerMessage(answerMsg);
                            }else if(numcard==5){
                                AnsCC5Msg ansCC5Msg= new AnsCC5Msg(this, game.getGameModel().getGeneralBoard(), game.getGameModel().getPlayers());
                                clientHandler.sendAnswerMessage(ansCC5Msg);
                            }else if(numcard==6){
                                game.UseEffectOfCharacterCard(game.getChoosenPlayer().GetPlayerTurn(), game.getCharacterCardChosen());
                                AnsPlayAfterCCMsg answerMsg = new AnsPlayAfterCCMsg(this, game.getChoosenPlayer().GetPlayerTurn().getNickName(), game.getGameModel().getGeneralBoard(), game.getGameModel().getPlayers(), game.getCountmodexpview());
                                clientHandler.sendAnswerMessage(answerMsg);
                            }else if(numcard==7){
                                AnsCC7Msg ansCC7Msg= new AnsCC7Msg(this, game.getGameModel().getGeneralBoard(), game.getGameModel().getPlayers(), game.getCharacterCardChosen());
                                clientHandler.sendAnswerMessage(ansCC7Msg);
                            }else if(numcard==8){
                                game.UseEffectOfCharacterCard(game.getChoosenPlayer().GetPlayerTurn(), game.getCharacterCardChosen());
                                AnsPlayAfterCCMsg answerMsg = new AnsPlayAfterCCMsg(this, game.getChoosenPlayer().GetPlayerTurn().getNickName(), game.getGameModel().getGeneralBoard(), game.getGameModel().getPlayers(), game.getCountmodexpview());
                                clientHandler.sendAnswerMessage(answerMsg);
                            }else if(numcard==9){
                                AnsCC9Msg ansCC9Msg= new AnsCC9Msg(this, game.getGameModel().getGeneralBoard(), game.getGameModel().getPlayers());
                                clientHandler.sendAnswerMessage(ansCC9Msg);
                            }else if(numcard==10){
                                AnsCC10Msg ansCC10Msg= new AnsCC10Msg(this,game.getGameModel().getGeneralBoard(), game.getGameModel().getPlayers() );
                                clientHandler.sendAnswerMessage(ansCC10Msg);
                            }else if(numcard==11){
                                AnsCC11Msg ansCC11Msg= new AnsCC11Msg(this, game.getGameModel().getGeneralBoard(), game.getGameModel().getPlayers(), game.getCharacterCardChosen());
                                clientHandler.sendAnswerMessage(ansCC11Msg);
                            }else if(numcard==12){
                                AnsCC12Msg ansCC12Msg= new AnsCC12Msg(this, game.getGameModel().getGeneralBoard(), game.getGameModel().getPlayers());
                                clientHandler.sendAnswerMessage(ansCC12Msg);
                            }
                        }catch (NotEnoughCoinException e){
                            AnsNotEnoughCoinsExcMsg ansNotEnoughCoinsExcMsg= new AnsNotEnoughCoinsExcMsg(this);
                            clientHandler.sendAnswerMessage(ansNotEnoughCoinsExcMsg);
                        }
                    }catch (PlayerAlreadyUsedCharacterCard e){
                        AnsPlayerAlrUsedCCExcMsg ansPlayerAlrUsedCCExcMsg= new AnsPlayerAlrUsedCCExcMsg(this);
                        clientHandler.sendAnswerMessage(ansPlayerAlrUsedCCExcMsg);
                    }
                }catch (CharacterCardNotInTableException e){
                    AnsCCNotInTableExcMsg answerMsg= new AnsCCNotInTableExcMsg(this);
                    clientHandler.sendAnswerMessage(answerMsg);
                }

            }else{
                AnsCannotPlayCCMsg ansCannotPlayCCMsg= new AnsCannotPlayCCMsg(this, game.getChoosenPlayer().GetPlayerTurn().getNickName());
                clientHandler.sendAnswerMessage(ansCannotPlayCCMsg);
            }

        }
    }
}

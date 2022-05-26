package it.polimi.ingsw.Client.views;

import it.polimi.ingsw.messages.*;

import java.util.Scanner;

public class LobbyView extends View{
    /** The view of the lobby */

    private AnsGameCreatedMsg answerMsg;


    public LobbyView(AnsGameCreatedMsg answerMsg)
    {
        this.answerMsg = answerMsg;
    }


    @Override
    public void run()
    {

        System.out.println("LOBBY");
        Scanner scanner = new Scanner(System.in);
        AnsGameCreatedMsg.Status moveStatus = answerMsg.getMoveStatus();
        if(moveStatus==AnsGameCreatedMsg.Status.VALID) {
            System.out.println("Creazione Partita");
            System.out.println("Inserire numero di giocatori: da 2 a 4 giocatori");
            Boolean ex=false;
            int numplayers=0;
            while(!ex) {
                try {
                    numplayers = Integer.parseInt(scanner.nextLine());
                    ex=true;
                } catch (NumberFormatException e) {
                    System.out.println("Errore: Inserire numero di giocatori: da 2 a 4 giocatori");
                    ex=false;
                }
            }
            System.out.println("Modalità esperta? true o false");
            boolean modexp = Boolean.parseBoolean(scanner.nextLine());
            NewGameMsg newgame = new NewGameMsg(numplayers, modexp);
            getOwner().getServerHandler().sendCommandMessage(newgame);
        }else{
            System.out.println("Ti stai unendo a una partita:");
            GameStartedMsg gameStartedMsg= new GameStartedMsg();
            getOwner().getServerHandler().sendCommandMessage(gameStartedMsg);




        }

    }
}

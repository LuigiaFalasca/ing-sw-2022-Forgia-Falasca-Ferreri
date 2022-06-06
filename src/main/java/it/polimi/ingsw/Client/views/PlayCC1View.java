package it.polimi.ingsw.Client.views;

import it.polimi.ingsw.Model.Color;
import it.polimi.ingsw.Model.IslandTiles;
import it.polimi.ingsw.Model.SchoolBoard;
import it.polimi.ingsw.messages.AnsAskCAMsg;
import it.polimi.ingsw.messages.CCMsg;
import it.polimi.ingsw.messages.MoveStudent1Msg;

import java.util.ArrayList;
import java.util.Scanner;

public class PlayCC1View extends View{
    AnsAskCAMsg answerMsg;
    public PlayCC1View(AnsAskCAMsg answerMsg)
    {
        this.answerMsg = answerMsg;
    }
    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        Displayer displayer = new Displayer();
        System.out.println(answerMsg.GetPlayer() + " è il tuo turno!");
        displayer.displayAllSchoolboard(answerMsg.GetGB().getSchoolBoard(),answerMsg.GetPlayers());
        displayer.showAllIsland(answerMsg.GetGB().GetIslands());
        for(int i=0;i<answerMsg.GetPlayers().size();i++){
            if(answerMsg.GetPlayers().get(i).getNickName().equals(answerMsg.GetPlayer())) {
                displayer.displayWallet(answerMsg.GetPlayers().get(i).getNumberCoins());
            }
        }
        displayer.displayAllcharactercard(answerMsg.GetCharacterCards());
        System.out.println("Vuoi giocare una carta personaggio? si/no");
        String card= scanner.nextLine();
        boolean f=false;
        while(!f) {
            if(card.equals("si")){
                System.out.println("Qual'è il numero della carta personaggio che vuoi giocare?");
                f=true;
                int numcard=Integer.parseInt(scanner.nextLine());
                CCMsg ccMsg= new CCMsg(numcard);
                getOwner().getServerHandler().sendCommandMessage(ccMsg);
            }else if(card.equals("no")){
                f=true;
                Boolean flag=false;
                int count=0;
                int isl;
                Color cdef=Color.Blue;
                while(!flag) {
                    if(count==0) {
                        System.out.println("Scegli il colore del primo studente che vuoi spostare");
                    }else{
                        System.out.println("Errore inserimento colore:Seleziona un colore valido");
                    }
                    String colorchosen = scanner.nextLine();
                    Color[] colors = Color.values();
                    for (int i = 0; i < 5 && !flag; i++) {
                        if (colors[i].getName().equals(colorchosen)) {
                            cdef=colors[i];
                            flag = true;
                        }
                    }
                    count++;
                }
                Color student= cdef;
                System.out.println("Vuoi spostarlo nella sala o su un'isola? sala/isola");
                String ris= scanner.nextLine();
                if(ris.equals("sala")){
                    isl=12;
                    MoveStudent1Msg moveStudentMsg= new MoveStudent1Msg(student, isl);
                    getOwner().getServerHandler().sendCommandMessage(moveStudentMsg);
                }else{
                    System.out.println("Su quale isola vuoi spostarlo?");
                    isl=Integer.parseInt(scanner.nextLine());
                    MoveStudent1Msg moveStudentMsg= new MoveStudent1Msg(student, isl);
                    getOwner().getServerHandler().sendCommandMessage(moveStudentMsg);
                }
            }else{
                System.out.println("Errore inserimento! Riprova");
                card= scanner.nextLine();
            }
        }
    }
}

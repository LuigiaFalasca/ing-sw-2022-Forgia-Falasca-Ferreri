package it.polimi.ingsw.Client.views;

import it.polimi.ingsw.Model.Color;
import it.polimi.ingsw.Model.IslandTiles;
import it.polimi.ingsw.Model.SchoolBoard;
import it.polimi.ingsw.messages.*;

import java.util.ArrayList;
import java.util.Scanner;

public class PlayCC4Players3View extends View{
    AnsMoveStudent3Msg answerMsg;
    public PlayCC4Players3View(AnsMoveStudent3Msg answerMsg)
    {
        this.answerMsg = answerMsg;
    }
    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        Displayer displayer = new Displayer();
        ArrayList<SchoolBoard> schoolBoards = new ArrayList<SchoolBoard>();
        for (int i = 0; i < answerMsg.GetGB().getSchoolBoard().size(); i++) {
            schoolBoards.add(answerMsg.GetGB().getSchoolBoard().get(i));
        }
        displayer.displayAllSchoolboard(schoolBoards, answerMsg.GetPlayers());
        ArrayList<IslandTiles> islandTiles = new ArrayList<>();
        for (int i = 0; i < answerMsg.GetGB().GetIslands().size(); i++) {
            islandTiles.add(answerMsg.GetGB().GetIslands().get(i));
        }
        displayer.showAllIsland(islandTiles);
        /**stampa Charactercards**/
        for(int i=0;i<answerMsg.GetPlayers().size();i++){
            if(answerMsg.GetPlayers().get(i).getNickName().equals(answerMsg.GetPlayer())) {
                displayer.displayWallet(answerMsg.GetPlayers().get(i).getNumberCoins());
            }
        }
        displayer.displayAllcharactercard(answerMsg.GetCharacterCards());
        System.out.println("Vuoi giocare una carta personaggio? si/no");
        String card = scanner.nextLine();
        if (card.equals("si")) {
            System.out.println("Qual'è il numero della carta personaggio che vuoi giocare?");
            int numcard = Integer.parseInt(scanner.nextLine());
            CCMsg ccMsg = new CCMsg(numcard);
            getOwner().getServerHandler().sendCommandMessage(ccMsg);
        } else {
            Boolean flag = false;
            int count = 0;
            int isl;
            Color cdef = Color.Blue;
            while (!flag) {
                if (count == 0) {
                    System.out.println("Scegli il colore del terzo studente che vuoi spostare");
                } else {
                    System.out.println("Errore inserimento colore:Seleziona un colore valido");
                }
                String colorchosen = scanner.nextLine();
                Color[] colors = Color.values();
                for (int i = 0; i < 5 && !flag; i++) {
                    if (colors[i].getName().equals(colorchosen)) {
                        cdef = colors[i];
                        flag = true;
                    }
                }
                count++;
            }
            Color student = cdef;
            System.out.println("Vuoi spostarlo nella sala o su un'isola? sala/isola");
            String ris = scanner.nextLine();
            if (ris.equals("sala")) {
                isl = 12;
                MoveStudent4Msg moveStudentMsg = new MoveStudent4Msg(student, isl);
                getOwner().getServerHandler().sendCommandMessage(moveStudentMsg);
            } else {
                System.out.println("Su quale isola vuoi spostarlo?");
                isl = Integer.parseInt(scanner.nextLine());
                MoveStudent4Msg moveStudentMsg = new MoveStudent4Msg(student, isl);
                getOwner().getServerHandler().sendCommandMessage(moveStudentMsg);
            }
        }
    }
}

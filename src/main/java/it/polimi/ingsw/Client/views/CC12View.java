package it.polimi.ingsw.Client.views;

import it.polimi.ingsw.Model.Color;
import it.polimi.ingsw.Model.IslandTiles;
import it.polimi.ingsw.Model.SchoolBoard;
import it.polimi.ingsw.messages.AnsCC11Msg;
import it.polimi.ingsw.messages.AnsCC12Msg;
import it.polimi.ingsw.messages.CC11Msg;
import it.polimi.ingsw.messages.CC12Msg;

import java.util.ArrayList;
import java.util.Scanner;

public class CC12View extends View{
    AnsCC12Msg answerMsg;
    public CC12View(AnsCC12Msg answerMsg)
    {
        this.answerMsg = answerMsg;
    }
    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean flag = false;
        int count = 0;
        Color cdef = Color.Blue;
        Displayer displayer= new Displayer();
        displayer.displayAllSchoolboard(answerMsg.GetGB().getSchoolBoard(),answerMsg.GetPlayers());
        displayer.showAllIsland(answerMsg.GetGB().GetIslands());
        while (!flag) {
            if (count == 0) {
                System.out.println("Scegli il colore di studente");
            } else {
                System.out.println("Colore dello studente invalido! Riprova");
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
        Color color = cdef;
        CC12Msg cc12Msg= new CC12Msg(color);
        getOwner().getServerHandler().sendCommandMessage(cc12Msg);
    }
}

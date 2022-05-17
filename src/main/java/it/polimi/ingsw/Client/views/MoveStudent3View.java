package it.polimi.ingsw.Client.views;

import it.polimi.ingsw.Model.Color;
import it.polimi.ingsw.messages.*;

import java.util.Scanner;

public class MoveStudent3View extends View{
    AnsMoveStudent2Msg answerMsg;
    public MoveStudent3View(AnsMoveStudent2Msg answerMsg)
    {
        this.answerMsg = answerMsg;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        int isl=0;
        System.out.println(answerMsg.GetPlayer() + " scegli il terzo studente!");
        Color student= Color.valueOf(scanner.nextLine());
        System.out.println("Vuoi spostarlo nella sala a su un'isola? sala/isola");
        String ris= scanner.nextLine();
        if(ris.equals("sala")){
            isl=12;
            MoveStudent3Msg moveStudentMsg= new MoveStudent3Msg(student, isl);
            getOwner().getServerHandler().sendCommandMessage(moveStudentMsg);
        }else{
            System.out.println("Su quale isola vuoi spostarlo?");
            isl=Integer.parseInt(scanner.nextLine());
            MoveStudent3Msg moveStudentMsg= new MoveStudent3Msg(student, isl);
            getOwner().getServerHandler().sendCommandMessage(moveStudentMsg);
        }
    }
}
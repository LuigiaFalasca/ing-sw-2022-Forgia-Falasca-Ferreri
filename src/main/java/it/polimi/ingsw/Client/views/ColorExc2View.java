package it.polimi.ingsw.Client.views;

import it.polimi.ingsw.Model.Color;
import it.polimi.ingsw.messages.AnsColorExc2Msg;
import it.polimi.ingsw.messages.AnsColorExcMsg;
import it.polimi.ingsw.messages.MoveStudent1Msg;
import it.polimi.ingsw.messages.MoveStudent2Msg;

import java.util.Scanner;

public class ColorExc2View extends View{
    AnsColorExc2Msg answerMsg;
    public ColorExc2View(AnsColorExc2Msg answerMsg)
    {
        this.answerMsg = answerMsg;
    }
    @Override
    public void run() {
        int isl=0;
        System.out.println("Scelta non consentita! Scegli di nuovo il colore di uno studente:");
        Scanner scanner = new Scanner(System.in);
        Color student= Color.valueOf(scanner.nextLine());
        System.out.println("Vuoi spostarlo nella sala a su un'isola? sala/isola");
        String ris= scanner.nextLine();
        if(ris.equals("sala")){
            isl=12;
            MoveStudent2Msg moveStudentMsg= new MoveStudent2Msg(student, isl);
            getOwner().getServerHandler().sendCommandMessage(moveStudentMsg);
        }else{
            System.out.println("Su quale isola vuoi spostarlo?");
            isl=Integer.parseInt(scanner.nextLine());
            MoveStudent2Msg moveStudentMsg= new MoveStudent2Msg(student, isl);
            getOwner().getServerHandler().sendCommandMessage(moveStudentMsg);
        }
    }
}
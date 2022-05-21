package it.polimi.ingsw.Client.views;

import it.polimi.ingsw.Model.Color;
import it.polimi.ingsw.messages.AnsIslandExc1Msg;
import it.polimi.ingsw.messages.MoveStudent1Msg;

import java.util.Scanner;

public class IslandExc1View extends View{
    AnsIslandExc1Msg answerMsg;
    public IslandExc1View(AnsIslandExc1Msg answerMsg)
    {
        this.answerMsg = answerMsg;
    }
    @Override
    public void run() {
        int isl = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Scelta non consentita!");
        Boolean flag = false;
        int count = 0;
        Color cdef = Color.Blue;
        while (!flag) {
            if (count == 0) {
                System.out.println("Scegli il colore dello studente che vuoi spostare");
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
            MoveStudent1Msg moveStudentMsg = new MoveStudent1Msg(student, isl);
            getOwner().getServerHandler().sendCommandMessage(moveStudentMsg);
        } else {
            System.out.println("Su quale isola vuoi spostarlo?");
            isl = Integer.parseInt(scanner.nextLine());
            MoveStudent1Msg moveStudentMsg = new MoveStudent1Msg(student, isl);
            getOwner().getServerHandler().sendCommandMessage(moveStudentMsg);
        }
    }
}

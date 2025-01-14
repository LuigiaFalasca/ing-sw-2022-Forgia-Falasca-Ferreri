package it.polimi.ingsw.Client.views;

import it.polimi.ingsw.messages.AnsNumCloudExcMsg;
import it.polimi.ingsw.messages.AnsNumStepExcMsg;
import it.polimi.ingsw.messages.CloudMsg;
import it.polimi.ingsw.messages.NumStepMNMsg;

import java.util.Scanner;

public class NumCloudExcView extends View{
    AnsNumCloudExcMsg answerMsg;
    public NumCloudExcView(AnsNumCloudExcMsg answerMsg)
    {
        this.answerMsg = answerMsg;
    }
    @Override
    public void run() {
        Scanner scanner= new Scanner(System.in);
        int cloud;
        System.out.println("Numero nuvola invalido! Riprova");
        boolean ex=false;
        while(!ex) {
            try {
                cloud = Integer.parseInt(scanner.nextLine());
                ex = true;
                CloudMsg cloudMsg = new CloudMsg(cloud);
                getOwner().getServerHandler().sendCommandMessage(cloudMsg);
            } catch (NumberFormatException e) {
                System.out.println("Errore: Inserire numero corretto");
                ex = false;
            }
        }
    }
}

package it.polimi.ingsw.messages;

import it.polimi.ingsw.Client.ServerHandler;
import it.polimi.ingsw.Server.ClientHandler;

import java.io.IOException;

import static java.lang.Thread.sleep;


public class PingClient implements Runnable {
    private ServerHandler serverHandler;
    public PingClient(ServerHandler serverHandler) {
        this.serverHandler = serverHandler;
    }




    @Override
    public void run() {
        try {
            handleClientDisconnection();
        } catch (IOException e) {
            System.out.println("IO exception partita finita " + this.serverHandler.getServer().getInetAddress() + " connection dropped");
            System.exit(130);
        }

    }
    private void handleClientDisconnection() throws IOException
    {
        try {
            while (true) {
                sleep(7500);
                this.sendPing();
            }

        }catch(ClassCastException e){
            System.out.println("invalid stream from client:ClassCastException");
        } catch (InterruptedException e) {
            System.out.println("Interrupted exception");
        }
    }
    public void sendPing() throws IOException
    {
        synchronized (this.serverHandler) {
            PingCMSG ping = new PingCMSG();
            this.serverHandler.getOutput().writeObject(ping);
            this.serverHandler.getOutput().flush();
            this.serverHandler.getOutput().reset();
        }
    }
}

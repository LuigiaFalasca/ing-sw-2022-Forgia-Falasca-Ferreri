package it.polimi.ingsw.Client;

import it.polimi.ingsw.Client.views.GUI.JavaFXMain;
import it.polimi.ingsw.Client.views.IdleView;
import it.polimi.ingsw.Client.views.TitleView;
import it.polimi.ingsw.Client.views.View;
import it.polimi.ingsw.Model.Color;

import java.io.*;
import java.net.Socket;
import java.util.*;



public class Client implements Runnable
{

    public static boolean GUI;
    private ServerHandler serverHandler;
    private boolean shallTerminate;
    private View nextView;
    private View currentView;



    public static void main(String[] args)
    {
        /* Instantiate a new Client. The main thread will become the
         * thread where user interaction is handled. */
        System.out.println(Color.Blue+"digitare GUI per l'interfaccia grafica altrimenti altro per la CLI"+Color.Reset);
        Scanner in =new Scanner(System.in);
        String simulazioneargs =in.nextLine();  
        if(simulazioneargs.equals("GUI")){
            Client.GUI=true;
        }else{
            Client.GUI=false;
        }
        if(GUI){
            JavaFXMain.main(args);
        }else{
            Client client = new Client();
            client.run();
        }

    }


@Override
    public void run()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digitare indirizzo IP server");
        String ip = scanner.nextLine();
        System.out.println("Digitare numero di Porta ");
        int socketPort=4567;
        try{
            socketPort = Integer.parseInt(scanner.nextLine());
        }catch (NumberFormatException e){
            System.out.println("numero di porta non valido!,inserito automaticamente numero di porta 4567");
        }


        /* Open connection to the server and start a thread for handling
         * communication. */

        Socket server;
        try {
            if(socketPort<=1024 || socketPort>65535){
                System.out.println("numero di porta non valido!,inserito automaticamente numero di porta 4567");
                socketPort=4567;
            }

            server = new Socket(ip, socketPort);
            System.out.println("Connesso al Server: IP="+ip+", Port="+socketPort);
        } catch (IOException e) {
            System.out.println("server non raggiungibile ,riavviare l'applicazione");
            return;
        }
        serverHandler = new ServerHandler(server, this);
        Thread serverHandlerThread = new Thread(serverHandler, "server_" + server.getInetAddress().getHostAddress());
        serverHandlerThread.start();

        /* Run the state machine handling the views */

        nextView = new TitleView();
        runViewStateMachine();

        /* We are going to stop the application, so ask the server thread
         * to stop as well. Note that we are invoking the stop() method on
         * ServerHandler, not on Thread */

        serverHandler.stop();
    }


    /**
     * The handler object responsible for communicating with the server.
     * @return The server handler.
     */

    public ServerHandler getServerHandler()
    {
        return serverHandler;
    }


    /**
     * Calls the run() method on the current view until the application
     * must be stopped.
     * When no view should be displayed, and the application is not yet
     * terminating, the IdleView is displayed.
     * @apiNote The current view can be changed at any moment by using
     * transitionToView().
     */

    private void runViewStateMachine()
    {
        boolean stop;

        synchronized (this) {
            stop = shallTerminate;
            currentView = nextView;
            nextView = null;
        }
        while (!stop) {
            if (currentView == null) {
                currentView = new IdleView();
            }
            currentView.setOwner(this);
            currentView.run();

            synchronized (this) {
                stop = shallTerminate;
                currentView = nextView;
                nextView = null;
            }
        }


    }


    /**
     * Transitions the view thread to a given view.
     * @param newView The view to transition to.
     */

    public synchronized void transitionToView(View newView)
    {
        this.nextView = newView;
        currentView.stopInteraction();
    }


    /**
     * Terminates the application as soon as possible.
     */

    public synchronized void terminate()
    {
        if (!shallTerminate) {
            /* Signal to the view handler loop that it should exit. */

            shallTerminate = true;
            currentView.stopInteraction();
        }
    }

    public void setServerHandler(ServerHandler serverHandler) {
        this.serverHandler = serverHandler;
    }

}

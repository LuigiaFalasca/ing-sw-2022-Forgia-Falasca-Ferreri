package it.polimi.ingsw.Client;

import it.polimi.ingsw.Client.views.GUI.JavaFXMain;
import it.polimi.ingsw.Client.views.IdleView;
import it.polimi.ingsw.Client.views.TitleView;
import it.polimi.ingsw.Client.views.View;

import java.io.*;
import java.net.Socket;
import java.util.*;

/**
 * Client for the Mastermind game.
 */

public class Client implements Runnable
{
    private ServerHandler serverHandler;
    private boolean shallTerminate;
    private View nextView;
    private View currentView;


    /**
    public static void main(String[] args)
    {
        /* Instantiate a new Client. The main thread will become the
         * thread where user interaction is handled. */
    /**
        Client client = new Client();
        client.run();
    }
    **/
    public static void main(String[] args)
    {
        JavaFXMain.main(args);}


@Override
    public void run()
    {
        Scanner scanner = new Scanner(System.in);
       /* a fine implementazione
        System.out.println("IP address of server?");
        String ip = scanner.nextLine();
        System.out.println("Server port?");
        int socketPort = Integer.parseInt(scanner.nextLine());*/

        /* Open connection to the server and start a thread for handling
         * communication. */

        Socket server;
        try {
            server = new Socket("127.0.0.1", 4567);
            System.out.println("Connesso al Server: IP=127.0.0.1 , Port=4567");
        } catch (IOException e) {
            System.out.println("server unreachable");
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

}

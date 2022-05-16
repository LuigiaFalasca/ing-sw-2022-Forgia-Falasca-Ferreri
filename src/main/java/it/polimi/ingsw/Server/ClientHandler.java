package it.polimi.ingsw.Server;

import it.polimi.ingsw.Controller.GameController;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


import it.polimi.ingsw.messages.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


/**
 * A class that represents the client inside the server.
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable
{
    public Socket getClient() {
        return client;
    }

    private Socket client;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private GameController game;
    private String nickname;


    /**
     * Initializes a new handler using a specific socket connected to
     * a client.
     * @param client The socket connection to the client.
     */

    ClientHandler(Socket client,GameController gamecontroller)
    {
        this.client = client;
        this.game = gamecontroller;
    }


    /**
     * Connects to the client and runs the event loop.
     */

    @Override
    public void run()
    {
        try {
            output = new ObjectOutputStream(client.getOutputStream());
            input = new ObjectInputStream(client.getInputStream());
        } catch (IOException e) {
            System.out.println("could not open connection to " + client.getInetAddress());
            return;
        }

        System.out.println("Connected to " + client.getInetAddress());

        try {
            handleClientConnection();
        } catch (IOException e) {
            System.out.println("client " + client.getInetAddress() + " connection dropped");
        }

        try {
            client.close();
        } catch (IOException e) { }
    }


    /**
     * An event loop that receives messages from the client and processes
     * them in the order they are received.
     * @throws IOException If a communication error occurs.
     */

    private void handleClientConnection() throws IOException
    {
        try {
            while (true) {
                /* read commands from the client, process them, and send replies */

                Object next = input.readObject();
                CommandMsg command = (CommandMsg)next;
                command.processMessage(this);
            }
        } catch (ClassNotFoundException | ClassCastException e) {
            System.out.println("invalid stream from client");
        }
    }


    /**
     * The game instance associated with this client.
     * @return The game instance.
     */
    public GameController getGame()
    {
        return game;
    }


    /**
     * Sends a message to the client.
     * @param answerMsg The message to be sent.
     * @throws IOException If a communication error occurs.
     **/

    public void sendAnswerMessage(AnswerMsg answerMsg) throws IOException
    {
        output.writeObject((Object)answerMsg);
    }
    public String getNickname(){return nickname;}
    public void setNickname(String nickname){this.nickname= nickname;}
}

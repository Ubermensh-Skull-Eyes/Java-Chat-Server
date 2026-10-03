package Server;

import java.io.IOException;

public class ChatServer{
    //Create a constructor to read port number from client to connect to the port
    public ChatServer(int portNumber) throws IOException{
        //TODO:handle;
    }
    // use main method to use server as a stand alone application
    public static void main(String[] args) {
        // Get the # 
        int port = Integer.parseInt(args[0]);

        //server object to accept more connections
        new ChatServer(port);
    }
}
package Server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;



public class ChatServer{
    //hash map to store dataoutput and socket to call if reued.
    HashMap<Socket,DataOutputStream> outputStreams = new HashMap<>();
    //Create a constructor to read port number from client to connect to the port
    public ChatServer(int portNumber) throws IOException{
        listen(portNumber);
    }
    // use main method to use server as a stand alone application
    public static void main(String[] args) throws Exception {
        // Get the # 
        int port = Integer.parseInt(args[0]);

        //server object to accept more connections
        new ChatServer(port);
    }
    private void listen(int portNumber) throws IOException{
        //starts listening to a server
        ServerSocket ss = new ServerSocket(portNumber);
        System.out.println("Listening to the port :- "+ss);
        //accepting connections
        while(true){
            //get a new connection
            Socket newSocket = ss.accept();
            System.out.println("Connected to :- "+ss);
             //writing data to the other side
             DataOutputStream dout = new DataOutputStream(newSocket.getOutputStream());
             //save this stream so we don't need to create again
             outputStreams.put(newSocket,dout);
             //create a new thread for client and forget
        }
    }
}
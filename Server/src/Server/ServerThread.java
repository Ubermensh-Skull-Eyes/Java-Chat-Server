package Server;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class ServerThread extends Thread {
    //contructor
    ChatServer server;
    Socket socket;
    public ServerThread(ChatServer s, Socket newSocket){
        this.server = s;
        this.socket = newSocket;

        //start thread;
        start();
    }

    //this will runs in a new thread
    public void run(){
        // DataInputStream to read what client is sending through socket
        try{
            DataInputStream din = new DataInputStream(socket.getInputStream());
            while(true){
                //read message
                String message = din.readUTF();
                System.out.println("Sending ..."+message);
                // server send to all clients
            }
        }        
        catch(EOFException e){
                //Nothing to do here
        }
        catch(IOException e){
            e.printStackTrace();
        }finally{
            //remove dead connection
        }
    } 
}

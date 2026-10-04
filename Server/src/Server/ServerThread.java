package Server;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class ServerThread extends Thread {
    //contructor
    private ChatServer server;
    private Socket socket;
    public ServerThread(ChatServer s, Socket newSocket){
        this.server = s;
        this.socket = newSocket;

        //start thread;
        start();
    }

    //this will runs in a new thread
    //constructor  
    public void run(){
        try{
            // DataInputStream to read what client is sending through socket
            DataInputStream din = new DataInputStream(socket.getInputStream());
            while(true){
                //read message
                String message = din.readUTF();
                System.out.println("Sending ..."+message);
                // server send to all clients
                server.sendToAll(message);
            }
        }        
        catch(EOFException e){
                //Nothing to do here
        }
        catch(IOException e){
            e.printStackTrace();
        }finally{
            //remove dead connection
            server.removeConnection(socket);
        }
    } 
}

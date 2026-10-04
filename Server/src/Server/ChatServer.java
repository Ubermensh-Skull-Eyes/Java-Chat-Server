package Server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Hashtable;





public class ChatServer{
    private ServerSocket ss;
    //hashtable to store dataoutput and socket to call if reued.
    private Hashtable outputStreams = new Hashtable<>();
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
        ss = new ServerSocket(portNumber);
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
             new ServerThread(this,newSocket);
        }
    }
    // Get enumeration of all OutputStreams 
    Enumeration getOutputStreams(){
        return  outputStreams.elements();
    }
    //send message to all client 
    void sendToAll(String message){
        //we sunchronized because other thread might call
        //removeConnection() this will mess up
        synchronized(outputStreams){
            for(Enumeration e = getOutputStreams(); e.hasMoreElements();){
                //get output stream
                DataOutputStream dout = (DataOutputStream)e.nextElement();
                //and send the message
                try{
                    dout.writeUTF(message);
                } catch(IOException ie){
                    System.out.println(ie);
                }
            
            }
        }
    }
    // Remove a socket
    void removeConnection(Socket s){
        //synchronized to secure sendALL
        synchronized(outputStreams){
            System.out.println("Removing connection "+s);
            //remove it from hashtable/list
            outputStreams.remove(s);
            try{
                s.close();
            } catch(IOException ie){
                System.out.println("Error closing "+s);
                ie.printStackTrace();
            }
        }
    }
}
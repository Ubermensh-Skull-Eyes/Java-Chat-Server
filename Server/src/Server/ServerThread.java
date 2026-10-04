package Server;
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
}

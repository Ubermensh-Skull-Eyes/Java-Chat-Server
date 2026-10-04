package Client;
import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class Client extends Panel implements Runnable{
    private TextField tf = new TextField();
    private TextArea ta = new TextArea();

    private Socket socket;
    
    private DataInputStream din;
    private DataOutputStream dout;

    //Constructor
    public  Client(String host , int port){
        //Setting up GUI
        setLayout( new BorderLayout() );
        add( "North", tf );
        add( "Center", ta );
        
        //recieve message from annonymous class
        tf.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                processMessage( e.getActionCommand() );
            }
        } );

        // connecting to the server

        try{
            //Initiate a connection
            socket = new Socket(host,port);
            System.out.println("We are connected to :- "+socket);
            //create data io stream
            din = new DataInputStream(socket.getInputStream());
            dout = new DataOutputStream(socket.getOutputStream());
            //start background thread
            new Thread(this).start();
        }catch(IOException e){
            System.out.println(e);
        }
    }
    //get triggered when user type something
    private void processMessage(String message){
        try{
            //send it to the server
            dout.writeUTF(message);
            //clear text box
            tf.setText("");
        }catch(IOException e){
            System.out.println(e);
        }
    }
    public void run(){
        try{
            //receive message one by one
            while(true){
                String message = din.readUTF();
                //print to next window
                ta.append(message+'\n');
            }
        }catch(IOException e){
            System.out.println(e);
        }
    }
     public static void main(String[] args) {
        String host = args.length > 0 ? args[0] : "localhost";
        int port = args.length > 1 ? Integer.parseInt(args[1]) : 5000;

        Frame f = new Frame("Chat Client");
        f.add("Center", new Client(host, port));
        f.setSize(400, 300);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        f.setVisible(true);
    }
}
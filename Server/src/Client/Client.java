package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.plaf.PanelUI;
import javax.swing.plaf.metal.MetalBorders.TextFieldBorder;

public class Client extends PanelUI implements Runnable{
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
            new Thread(this).run();
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
}
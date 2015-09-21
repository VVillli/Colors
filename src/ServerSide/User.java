package ServerSide;
import java.io.*;
import java.util.*;
import java.net.*;

public class User {

	private Socket socket;		 
    private boolean connected;
    private Inport inport;
    private class Inport extends Thread
    {
        private ObjectInputStream in;
        
        public void run()
        {	 
            try{
                in = new ObjectInputStream(socket.getInputStream());
            }		 
            catch(IOException e)		 
            {		 
                System.out.println("Could not get input stream from "+toString());
                return;		 
            }
           System.out.println(socket+" has connected input.");
            // Enter process loop
            while(true){

            
            }
        }
     }
    
	 public void purge()
     {
         // Close everything
         try{ 
             connected = false; 
             socket.close(); 
         }
 
         catch(IOException e) 
         { 
             System.out.println("Could not purge "+socket+".");
         }
     }
	 
	 public User(Socket newSocket){
         socket = newSocket; 
         connected = true;
         inport = new Inport();
         inport.start();
     }
 
     public boolean isConnected()
     {
         return connected;
     }
    
    public String toString()
    {
        return new String(socket.toString());
    }
}

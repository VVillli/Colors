package ServerSide;
import java.io.*;
import java.util.*;
import java.net.*;	
public class Server extends Thread{
			private static final int ServerPort = 111111;
			private static ServerSocket serverSocket;		
		    private static BufferedReader bufferedReader;
		    private static InetAddress hostAddress; 
		    private Socket socket;
		    private ArrayList<Player> players = new ArrayList<Player>();
		    
		    public static void main(String[] args)
		    {		
		        // Wait for client to connect on 63400
		    	try{
		    		hostAddress = InetAddress.getLocalHost();
		    		
		    	}
		    	catch(UnknownHostException e){
		    		System.out.println("Could not get the host address.");	
		    		return;
		     	}
		    	System.out.println("Server host address is: "+hostAddress);

		        try{
		            serverSocket = new ServerSocket(ServerPort, 0, hostAddress);
		        }
		        catch(IOException e)
		        {    	
		            System.out.println("Server Socket failure");
		        }
		        
		        System.out.println("Socket "+serverSocket+" created.");
		    }
		    
		    public void run()
		        {
		            // Announce the starting of the process
		            System.out.println("Room has been started.");
		            // Enter the main loop
		            while(true)
		            {
		                for(int i = 0;i < players.size();i++)
		    
		                {
		    
		                   
		    
		                 
		    
		                }
		    
		                try{
		                  socket = serverSocket.accept();
		                }
		                catch(IOException e)
		                {
		                    System.out.println("Could not get a client.");
		                }
		                // Client has connected
		    
		                System.out.println("Client "+socket+" has connected.");
		    
		                // Add user to list
		    
		                players.add(new Player(socket));
		    
		            }


}


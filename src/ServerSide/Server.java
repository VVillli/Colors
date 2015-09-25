package ServerSide;




import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread
{ 
	
 protected Socket clientSocket1;
 protected Socket clientSocket2;

 private static final int PORT = 9005;
 private ArrayList<Integer> usersScores;
 
 public static void main(String[] args) throws IOException 
   { 
    ServerSocket serverSocket = null; 
    
    try { 
         serverSocket = new ServerSocket(PORT); 
         System.out.println ("Connection Socket Created");
         try { 
              while (true)
                 {
                  System.out.println ("Waiting for Connection");
                  new Server (serverSocket.accept()); 
                 }
             } 
         catch (IOException e) 
             { 
              System.err.println("Accept failed."); 
              System.exit(1); 
             } 
        } 
    catch (IOException e) 
        { 
         System.err.println("Could not listen on port: " + PORT); 
         System.exit(1); 
        } 
    finally
        {
         try {
              serverSocket.close(); 
             }
         catch (IOException e)
             { 
              System.err.println("Could not close port: " + PORT); 
              System.exit(1); 
             } 
        }
   }

 private Server (Socket clientSoc)
   {
    clientSocket1 = clientSoc;
    usersScores = new ArrayList<Integer>(2);
    start();
   }

 public void run()
   {
    System.out.println ("New Communication Thread Started");
    usersScores.add(0);
    usersScores.add(0);
    while(true){
    	
    for(int i = 0; i < 2; i++){
    	try { 
	         PrintWriter out1 = new PrintWriter(clientSocket1.getOutputStream(), true); 
	         BufferedReader in1 = new BufferedReader( new InputStreamReader(clientSocket1.getInputStream())); 
		     String inputLine = in1.readLine(); 
		     System.out.println ("Client " + i + " >> " + inputLine); 
	         System.out.println ("Client " + i + " << " + usersScores.get(i)); 
	         out1.println(usersScores.get(1)); 
	         if (inputLine.equals("Bye")) 
	                  break; 	
	         out1.close(); 
	         in1.close(); 
	         clientSocket1.close(); 
	        } 
	    catch (IOException e) 
	        { 
	         System.err.println("Problem with Communication Server");
	         System.exit(1); 
	        }
    }
    }
   }
} 
/*
import java.io.*;
import java.util.*;
import java.net.*;	
public class Server extends Thread{
	
			private static final int ServerPort = 63400;
			private static ServerSocket serverSocket;		
		    private static BufferedReader bufferedReader;
		    private static InetAddress hostAddress; 
		    private Socket socket;
		    private ArrayList<User> users = new ArrayList<User>();
		    
		    public Server(){
		    	
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
		    
		    public static void main(String[] args)
		    {	
		    	Server server = new Server();
		    	server.run();
		    }
		    
		    public void run()
		        {
		            // Announce the starting of the process
		            System.out.println("Game has been started.");
		            // Enter the main loop
		            while(true)
		            {
		                for(int i = 0;i < users.size();i++)
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
		    
		               // users.add(new User(socket, new Player() ));
		    
		            }


		        }
}

*/
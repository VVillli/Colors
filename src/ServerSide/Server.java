package ServerSide;




import java.net.*; 
import java.io.*; 

public class Server extends Thread
{ 
 protected Socket clientSocket;
 private static final int PORT = 63500;
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
    clientSocket = clientSoc;
    start();
   }

 public void run()
   {
    System.out.println ("New Communication Thread Started");

    try { 
         PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), 
                                      true); 
         BufferedReader in = new BufferedReader( 
                 new InputStreamReader( clientSocket.getInputStream())); 

         String inputLine; 

         while ((inputLine = in.readLine()) != null) 
             { 
              System.out.println ("Server --> " + inputLine); 
              out.println(inputLine); 

              if (inputLine.equals("Bye.")) 
                  break; 
             } 

         out.close(); 
         in.close(); 
         clientSocket.close(); 
        } 
    catch (IOException e) 
        { 
         System.err.println("Problem with Communication Server");
         System.exit(1); 
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
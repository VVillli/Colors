package ServerSide;




import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;



public class Server extends Thread
{ 
	
 protected Socket clientSocket1;
 protected Socket clientSocket2;

 private static final int PORT = 9005;
 private int score1;
 private int score2;
 public ConnectionHandeler c1;
 public ConnectionHandeler c2;

 //private ArrayList<Integer> usersScores;
 
 public static void main(String[] args) throws IOException 
   { 
    ServerSocket serverSocket = null; 
    
    try { 
         serverSocket = new ServerSocket(PORT); 
         System.out.println ("Connection Socket Created");
         while (true)
		     {
		      System.out.println ("Waiting for Connection");
		      new Server (serverSocket).start(); 
		      System.out.println ("Connection");

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

 public Server (ServerSocket socket)
   {
    
    try {
    	
    	clientSocket1 = socket.accept();
	    //System.out.println ("Waiting for Client #2 Connection");
		//clientSocket2 = socket.accept();
    	
    	
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  
    score1 = 0;
    score2 = 0;
  //  usersScores = new ArrayList<Integer>(2);
   }

 public void run()
   {
    System.out.println ("New Communication Thread Started");
    
   // while(true){
    	//  c2 =  new ConnectionHandeler(clientSocket1);
    	//  c2.start();
    	  c1 =  new ConnectionHandeler(clientSocket1);
    	  c1.start();
    //	c1.setTheirScore(c2.getYourScore());
    	//c2.setTheirScore(c1.getYourScore());
    //}
    
    /*
    while(true){
    	
    	// player 1
    	
    	try { 
	         PrintWriter out1 = new PrintWriter(clientSocket1.getOutputStream(), true); 
	         BufferedReader in1 = new BufferedReader( new InputStreamReader(clientSocket1.getInputStream())); 
		     String inputLine = in1.readLine(); 
		     System.out.println ("Client " + 1 + " >> " + inputLine); 
	         System.out.println ("Client " + 1 + " << " + score1);
	         if (inputLine.equals("Bye")) 
	                  break; 	
	         out1.close(); 
	         in1.close(); 
	         clientSocket1.close(); 
	        } 
	    catch (IOException e) 
	        { 
	         System.err.println("Problem with Communication Server");
	        }
    	
    	//player 2
    	
    	try { 
	         PrintWriter out2 = new PrintWriter(clientSocket2.getOutputStream(), true); 
	         BufferedReader in2 = new BufferedReader( new InputStreamReader(clientSocket2.getInputStream())); 
		     String inputLine = in2.readLine(); 
		     System.out.println ("Client " + 1 + " >> " + inputLine); 
	         System.out.println ("Client " + 1 + " << " + score1);
	         if (inputLine.equals("Bye")) 
	                  break; 	
	         out2.close(); 
	         in2.close(); 
	         clientSocket1.close(); 
	        } 
	    catch (IOException e) 
	        { 
	         System.err.println("Problem with Communication Server"); 
	        }
   	
    	
    	
    	
    }*/
    
    }
	 private class ConnectionHandeler extends Thread implements Closeable{
			Socket leSocket;
			boolean p = true;
			private int yourScore;
			private int theirScore;
			ConnectionHandeler(Socket socket){
				this.leSocket = socket;
				yourScore = 0;
				theirScore = 0;
			     //System.out.println ("Created CH "); 
			     System.out.println("Client "+socket+" has connected.");

			}
			public void run(){
			     System.out.println (" CH Running"); 

				while(true){
				      
				
				try { 
			         PrintWriter out1 = new PrintWriter(leSocket.getOutputStream()); 
			         BufferedReader in1 = new BufferedReader( new InputStreamReader(leSocket.getInputStream())); 
			         System.out.println(in1.readLine());
				    /* setYourScore( Integer.parseInt(in1.readLine())); 
				     System.out.println ("Client " + 1 + " >> " + yourScore); 
			         System.out.println ("Client " + 1 + " << " + getTheirScore());
			         out1.println(yourScore + ":" + getTheirScore()); 
			         *///clientSocket1.close(); 
			        } 
			    catch (IOException e) 
			        { 
			         System.err.println("Problem with Communication Server: ");
			         e.printStackTrace();
			        }
				}
			}
			@Override
			public void close(){
				p= false;
			}
			
			public void setYourScore(int newScore){ 
				yourScore = newScore;
			}
			public int getYourScore(){ 
				return theirScore;
			}
			public void setTheirScore(int newScore){
				theirScore = newScore;
			}
			public int getTheirScore(){
			 
				return theirScore;
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
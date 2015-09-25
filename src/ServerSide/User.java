package ServerSide;

import gameObjects.Player;

import java.io.*;
import java.net.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;

public class User extends Thread{

	
	private String hostname;
	private int port;
	Socket socketClient;
	private Player player;
	private int score;
	private int theirScore;
	boolean connected;
	
	
	 //Socket s1=null;
	 String line=null;
	 BufferedReader br=null;
	 BufferedReader is=null;
	 PrintWriter os=null;
	
	 
	public User(String hostname, int port, Player player) {
		this.hostname = hostname;
		this.port = port;
		this.player = player;
		score = 0;
		theirScore = 0;
		connected = false;
		
		
		 try {
			 	socketClient=new Socket(hostname, 9005); // You can use static final constant PORT_NUM
		        br= new BufferedReader(new InputStreamReader(System.in));
		        is=new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
		        os= new PrintWriter(socketClient.getOutputStream());
		    }
		    catch (IOException e){
		        //e.printStackTrace();
		        System.err.print("IO Exception");
		        return;
		    }
	}

	public void connect() {
		System.out.println("Attempting to connect to " + hostname + ":" + port);
		try {
			socketClient = new Socket(hostname, port);
			System.out.println("Connection Established");
			connected = true;
		} catch (IOException e) {System.out.println("Connection Failed, Continuing"); return;}
		
	}

	public void read() {
		try {
			String userInput;
			
	       // ObjectInputStream inFromServer = new ObjectInputStream(socketClient.getInputStream());
			while ((userInput = is.readLine()) != null) {
				String[] input = userInput.split(":");
				newScore(Integer.parseInt(input[0]));
				theirScore = Integer.parseInt(input[1]);
				System.out.println("Server >> " + "Your Score" + input[0] + "Their Score" + input[1]);
				
				
			}		
		} catch (IOException e) {
		}
	}
	
	public void send(String message) throws IOException {
		System.out.println("Server << " + message);
		os.write(message+"/n");
//	writer.newLine();
//	writer.flush();

	}
	
	public Player getPlayer(){
		return player;
		
	}

	public void run() {
		// Creating a User object
		//User client = new User("localhost", port, player);
		this.connect();
		while (connected){
			try {
				if(player.getScore() > score){
					send(/*Integer.toString(player.getScore())*/ "Hi");
					//wait(100);
				//	read();
				}
				read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
/*
			try {
				System.out.println("Looping");
				if(player.getScore() > score){
					System.out.println("Sending Scores");
						send(Integer.toString(score));
					//this.read();
				}
			this.read();	
			} catch (UnknownHostException e) {
				System.err.println("Host unknown. Cannot establish connection");
			} catch (IOException e) {
				System.err.println("Cannot establish connection. Server may not be up."+ e.getMessage());
			}*/ 
		}
	}
	
	public void newScore(int score){
		this.score = score;
	}
	
	public int getScore(){
		return score;
		
	}
	
	public int getEScore(){
		return theirScore;	
	}
}
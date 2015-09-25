package ServerSide;

import gameObjects.Player;

import java.io.*;
import java.net.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
	public User(String hostname, int port, Player player) {
		this.hostname = hostname;
		this.port = port;
		this.player = player;
		score = 0;
		theirScore = 0;
		connected = false;
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
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			
	       // ObjectInputStream inFromServer = new ObjectInputStream(socketClient.getInputStream());
			while ((userInput = stdIn.readLine()) != null) {
				System.out.println(userInput);
				System.out.println("Server >> " + userInput);
			}
		} catch (IOException e) {
		}
	}

	public void send(String message) throws IOException {
		try {
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			
			System.out.println("Server << " + message);
			writer.write(message);
			writer.newLine();
			writer.flush();
		} catch (IOException e) {
		}

	}
	
	public Player getPlayer(){
		return player;
		
	}

	public void run() {
		// Creating a User object
		//User client = new User("localhost", port, player);
			this.connect();
		while (connected){
			while(true){
		try {

			if(player.getScore() > score){
				this.send("hi");
				this.read();
			}

		} catch (UnknownHostException e) {
			System.err.println("Host unknown. Cannot establish connection");
		} catch (IOException e) {
			System.err
					.println("Cannot establish connection. Server may not be up."
							+ e.getMessage());
		}
		}
		}
	}
	
	public void increaseScore(){
		score++;
	}
	
	public int getScore(){
		return score;
		
	}
	
}
/*
 * import gameObjects.Player;
 * 
 * import java.io.*; import java.util.*; import java.net.*;
 * 
 * public class User extends Thread{
 * 
 * private Socket socket; private boolean connected; private Player player;
 * private ObjectInputStream in;
 * 
 * public void run() { try{ in = new ObjectInputStream(socket.getInputStream());
 * } catch(IOException e) {
 * System.out.println("Could not get input stream from "+toString()); return; }
 * System.out.println(socket+" has connected input."); // Enter process loop
 * while(true){
 * 
 * 
 * } }
 * 
 * public void purge() { // Close everything try{ connected = false;
 * socket.close(); }
 * 
 * catch(IOException e) { System.out.println("Could not purge "+socket+"."); } }
 * 
 * public User(Socket newSocket, Player player){ socket = newSocket; connected =
 * true; this.player = player; }
 * 
 * public boolean isConnected() { return connected; }
 * 
 * public Socket getSocket() { return socket; }
 * 
 * public String toString() { return new String(socket.toString()); } }
 */
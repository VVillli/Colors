// echo server
package ServerSide;
//echo server
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
	
	public static int score1;
	public static int score3;
public static void main(String args[]){


 Socket s=null;
 ServerSocket ss2=null;
 score1 = 0;
 score3 = 0;
 System.out.println("Server Listening......");
 try{
     ss2 = new ServerSocket(9005); // can also use static final PORT_NUM , when defined

 }
 catch(IOException e){
 e.printStackTrace();
 System.out.println("Server error");

 }
 int num = 0;
 
 while(true){
     try{
         s= ss2.accept();
         System.out.println("connection Established");
         ServerThread st=new ServerThread(s, num);
         st.start();
         num++;
     }

 catch(Exception e){
     e.printStackTrace();
     System.out.println("Connection Error");

 }
 }

}

}

class ServerThread extends Thread{  

 String line=null;
 BufferedReader  is = null;
 PrintWriter os=null;
 Socket s=null;
 int num;
 public ServerThread(Socket s, int number){
	 num = number;
     this.s=s;
 }

 public void run() {
 try{
     is= new BufferedReader(new InputStreamReader(s.getInputStream()));
     os=new PrintWriter(s.getOutputStream());
     System.out.println("Making reder/writer");

 }catch(IOException e){
     System.out.println("IO error in server thread");
 }

 try {
     System.out.println("Reading");

     line = is.readLine();
     
     System.out.println("Client " + num + " >> "+line);
     while(line.compareTo("QUIT")!=0){
    	 
    	 String line2;
    	 if(num == 1){
    		 Server.score1 = Integer.parseInt(line);
    		 line2 = Integer.toString(Server.score3);
    	 }
    	 else{
    		 Server.score3 = Integer.parseInt(line);
    		 line2 = Integer.toString(Server.score1);
    	 }
         os.println(line + ":"+ line2);
         os.flush();
         System.out.println("Client " + num+ "<< "+line);
         line=is.readLine();
     }   
 } catch (IOException e) {

     line=this.getName(); //reused String line for getting thread name
     System.out.println("IO Error/ Client "+line+" terminated abruptly");
 }
 catch(NullPointerException e){
     line=this.getName(); //reused String line for getting thread name
     System.out.println("Client "+line+" Closed");
 }

 finally{    
 try{
     System.out.println("Connection Closing..");
     if (is!=null){
         is.close(); 
         System.out.println(" Socket Input Stream Closed");
     }

     if(os!=null){
         os.close();
         System.out.println("Socket Out Closed");
     }
     if (s!=null){
     s.close();
     System.out.println("Socket Closed");
     }

     }
 catch(IOException ie){
     System.out.println("Socket Close Error");
 }
 }//end finally
 }
}
/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
public int score1;
public int score2;
public static void main(String args[]){


    Socket s=null;
    ServerSocket ss2=null;
    System.out.println("Server Listening......");
    try{
        ss2 = new ServerSocket(9005); // can also use static final PORT_NUM , when defined

    }
    catch(IOException e){
    e.printStackTrace();
    System.out.println("Server error");

    }

    
        try{
            s= ss2.accept();
            System.out.println("connection Established");
            ServerThread st=new ServerThread(s);
            st.start();
        }
        catch(Exception e){
        e.printStackTrace();
        System.out.println("Connection Error");

        }
        
        
        	
    

}

}

class ServerThread extends Thread{  

    String line=null;
    BufferedReader  is = null;
    PrintWriter os=null;
    Socket s=null;

    public ServerThread(Socket s){
        this.s=s;
    }

    public void run() {
    	
    try{
        is= new BufferedReader(new InputStreamReader(s.getInputStream()));
        os=new PrintWriter(s.getOutputStream());
        System.out.println("Connected to " + s.getInetAddress());

    }catch(IOException e){
        System.out.println("IO error in server thread");
    }
    while(true){
	    try {
	        line=is.readLine();
	        System.out.println("Client >> "+line);
	        while(line.compareTo("QUIT")!=0){
	            os.println(line+":" + line);
	            os.flush();
	            System.out.println("Client << "+line + ":" +line);
	            line=is.readLine();
	        }   
	    } catch (IOException e) {
	
	        line=this.getName(); //reused String line for getting thread name
	        System.out.println("IO Error/ Client "+line+" terminated abruptly");
	    }
	    catch(NullPointerException e){
	        line=this.getName(); //reused String line for getting thread name
	        System.out.println("Client "+line+" Closed");
	    }
	
	    
	    
	    finally{    
	    try{
	        System.out.println("Connection Closing..");
	        if (is!=null){
	            is.close(); 
	            System.out.println(" Socket Input Stream Closed");
	        }
	
	        if(os!=null){
	            os.close();
	            System.out.println("Socket Out Closed");
	        }
	        if (s!=null){
	        s.close();
	        System.out.println("Socket Closed");
	        }
	
	        }
	    catch(IOException ie){
	        System.out.println("Socket Close Error");
	    }
	    }//end finally
	    }
    }
}*/
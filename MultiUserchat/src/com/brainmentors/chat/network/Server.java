package com.brainmentors.chat.network;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.brainmentors.chat.utils.ConfigReader;

public class Server {
	ServerSocket serverSocket;
	ArrayList<ServerWorker> Workers = new ArrayList<>();//contains all the clients sockets
	
	public Server() throws IOException {
		int PORT = Integer.parseInt(ConfigReader.getValue("PORTNO"));
		serverSocket = new ServerSocket(PORT);
		System.out.println("Server starts and waiting for client to join...");
		handleClientRequest();
	}
	//Multiple Clients handshaking...
	public void handleClientRequest() throws IOException {
		while(true) {
			Socket clientSocket = serverSocket.accept();///Handshaking process...
			//Per Client Per Thread
			ServerWorker serverWorker = new ServerWorker(clientSocket,this);//creating a new worker or thread
			Workers.add(serverWorker);
			serverWorker.start();
		}
		
	}
	
	
	
	
	
	//Single client
	
	/*public Server() throws IOException {
		int PORT = Integer.parseInt(ConfigReader.getValue("PORTNO"));
		serverSocket = new ServerSocket(PORT);
		System.out.println("Server Started and Waiting for Client Connection...");
		Socket socket = serverSocket.accept();///Handshaking process...
		System.out.println("Client joins the Server");
		InputStream in = socket.getInputStream();   //reads Bytes from the network...
		byte arr[] = in.readAllBytes();
		String str = new String(arr); //convert the bytes message into String.
		System.out.println("Message recieved from Client.."+str);
		in.close();
		socket.close();
	}*/

	public static void main(String[] args) throws IOException {
		
		Server server = new Server();
		// TODO Auto-generated method stub

	}

}

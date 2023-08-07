package com.brainmentors.chat.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

///Thread == Worker
///Worker need a job to perform
///For a job give runnable
///Once a job is created via runnable we should write the job logic inside the run() function
///Assign the job to the thread
public class ServerWorker extends Thread {

	private Socket clientSocket;
	private InputStream in;
	private OutputStream out;
	private Server server;
	public ServerWorker(Socket clientSocket, Server server) throws IOException {
		this.server = server;
		this.clientSocket=clientSocket;
		in = clientSocket.getInputStream();// read the client data..
		out = clientSocket.getOutputStream();//write the client data..
		System.out.println("New Client comes...");
	}
	@Override
	public void run() {
		// job to perform
		//read the data from client and broadcast the data to all..
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String line;
		try {
			while(true) {
			line = br.readLine();// need hai /n ki..
			System.out.println("line Read.."+line);
			if(line.equalsIgnoreCase(" quit ")) {
				break;//client chat end
			}
			//out.write(line.getBytes());//client send the data..
			//broadcast to all clients..
			for(ServerWorker serverWorker:server.Workers) {
				line = line + "\n";
				serverWorker.out.write(line.getBytes());
			}
		  }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		finally {
			try {
			if(br!=null) {
				br.close();
			}
			if(in!=null) {
				in.close();
			}
			if(out!=null) {
				out.close();
			}
			if(clientSocket!=null) {
				clientSocket.close();
			}
		}
			catch(Exception ex) {
				ex.printStackTrace();
				
			}
			
		
			
		}
	
	}
    
}

package com.hit.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.hit.services.CacheUnitController;

@SuppressWarnings("deprecation")
public class Server implements Observer //Constructor
{
	private CacheUnitController<String> controller = null;
	private Socket someClient;
	private ServerSocket server;
	private	boolean	state;
	private	String	m_command;
	private final boolean OFFLINE;
	private final boolean ONLINE;


	public Server() throws ClassNotFoundException 
	{
		this.ONLINE = true;
		this.OFFLINE = false;
		
		try 
		{
			//creating a server socket and setting server state to online
			server = new ServerSocket(12345);
			state = OFFLINE;
			controller = new CacheUnitController<String>();
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			System.out.println("ERROR: Cannot connect to the server. check if the port is already in used");
			e.printStackTrace();
		}
	}

	void start()
	{
		ObjectOutputStream os = null;
		//System.out.println("server started...");
		//System.out.flush();
		Executor exec =Executors.newFixedThreadPool(2);
		HandleRequest<String> hr;
		
		while(state)
		{
			try {

				someClient = server.accept();
				System.out.println("New client just connected\n");
				if (state)
				{
					hr=new HandleRequest<String>(someClient,controller);
					exec.execute(hr);
				} 
				//in case server logged out while processing
				else {
					os = new ObjectOutputStream(someClient.getOutputStream());
					os.writeObject("Server has stopped working\n");
				}				

			} 
			catch (IOException e) 
			{
			// TODO Auto-generated catch block
			e.printStackTrace();
			}

		}
		
		try 
		{
			server.close();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) 
	{
		m_command = (String) arg1;

		//works with lower case only
		if (m_command.equals("start") && (state == OFFLINE))
		{
			state = ONLINE;
			System.out.println("Server is now online");
			Runnable runnable_th = new Runnable() 
			{
				@Override
				public void run() {
					//calling start() method of this class
					start();
				}
			};
			
			//starting the new thread
			new Thread(runnable_th).start();
			
		} 
		else if ( ((m_command.equals("stop"))||(m_command.equals("close")) ) && (state == ONLINE))
		{
			state = OFFLINE;
			System.out.println("Server is now shutdown");

		}
	}
}

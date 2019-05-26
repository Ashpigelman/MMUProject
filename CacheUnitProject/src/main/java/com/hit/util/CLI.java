package com.hit.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Observable;
import java.util.Scanner;

@SuppressWarnings("deprecation")
public class CLI extends Observable implements Runnable
{
	private InputStream	in;
	private OutputStream out;
	private String	command;
	private	boolean	m_server_stat;
	private final boolean	OFFLINE;
	private final boolean	ONLINE;

	public CLI(InputStream in,OutputStream out)
	{
		this.in	= in;
		this.out = out;
		this.m_server_stat	= false;
		this.command = null;
		this.OFFLINE = false;
		this.ONLINE	= true;
	}

	public void write(String string) throws IOException
	{
		try {
			out.write((string + "\n").getBytes());
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("resource")
	@Override
	public void run()
	{
		Scanner scanner = new Scanner(in);
		do
		{
			//working with lower case only!
			try {
				write("Please enter your command:");
			} 
			catch (IOException e1)
			{
				e1.printStackTrace();
			}
			command = scanner.next();
			switch (command)
			{
				case "start":
					if (!m_server_stat)
					{
						try 
						{
							
							//output and change state of server (true = online)
							out.write("Starting server...\n".getBytes());
							m_server_stat = ONLINE;
							
							//notify observers
							setChanged();	//marking this object as changed-object
											//this method is necessary for notifyObservers which uses method hasChanged()
							
							notifyObservers(command);	//notifying server about a change "start server"

						} 
						catch (IOException e)
						{
							e.printStackTrace();
						}
					//server's already online
					} 
					else 
					{
						try 
						{
							out.write("Server's already online\n".getBytes());
						} 
						catch (IOException e)
						{
							e.printStackTrace();
						}
					}
					break;
					
				case "close":
					if (m_server_stat)
					{
						try {
								out.write("Shutdown server...\n".getBytes());
								m_server_stat = OFFLINE;
								
								setChanged();	//marking this object as changed-object
												//this method is necessary for notifyObservers which uses method hasChanged()
								
								notifyObservers(command);	//notifying server about a change "start server"
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					}
					else
					{
							try 
							{
								out.write("Server's already offline".getBytes());
							} 
							catch (IOException e)
							{
								e.printStackTrace();
							}
					}
				break;
					
				default:
					try 
					{
						out.write("Not a valid command".getBytes());
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
			}
		} 
		while (true);
	}

}

package com.hit.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class CacheUnitClient 
{
	private InetAddress localaddr;
	private Socket server;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private String cacheContent=null;

	public CacheUnitClient() throws IOException
	{
	}

	@SuppressWarnings("static-access")
	public String send(String request)
	{
		String swaps=null;
		Integer sp=0;
		try 
		{
			localaddr=InetAddress.getLocalHost();
			server=new Socket(localaddr.getHostAddress(),12345);
			out=new ObjectOutputStream(server.getOutputStream());
			in=new ObjectInputStream(server.getInputStream());
			out.writeObject(request);
			out.flush();
			if(request.contains("GET"))
			cacheContent=(String)in.readObject();
			sp=(Integer) in.readObject();
			swaps=sp.toString(sp.intValue());
			
		}
		catch (ClassNotFoundException | IOException e) 
		{
			System.out.println("connection eror");
			e.printStackTrace();
		}
		return  swaps;//data to GUI string or map
	}
	
	public String getCacheContent()
	{
		return cacheContent;
	}
}

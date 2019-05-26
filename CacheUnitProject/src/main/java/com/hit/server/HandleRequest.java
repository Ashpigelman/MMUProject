package com.hit.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.net.Socket;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hit.dm.DataModel;
import com.hit.services.CacheUnitController;

public class HandleRequest<T> extends Object implements java.lang.Runnable
{
	private Socket s;
	private CacheUnitController<T> controller;
	private ObjectInputStream r;//reader
	private ObjectOutputStream w;//writer
    
	public HandleRequest(Socket s,CacheUnitController<T> controller)//Constructor
	{
		this.s = s;
		this.controller = controller; 
	}
	
	@Override
	public void run()
	{
		try 
		{
			String action;
			r=new ObjectInputStream((s.getInputStream()));
			w=new ObjectOutputStream(s.getOutputStream());
			//get request string
			String req=(String) r.readObject();
			Type ref = new TypeToken<Request<DataModel<T>[]>>(){}.getType();
			Request<DataModel<T>[]> request = new Gson().fromJson(req, ref);

			action= request.getHeaders().get("action");  
			if(action.equals("UPDATE"))
			{
				controller.update(request.getBody());
				Integer swaps=controller.cacheUnitService.getCache().getNumOfSwaps();
				w.writeObject(swaps);
				w.flush();

			}
			else if(action.equals("GET"))
			{
				String cacheContent=" ";
				controller.get(request.getBody());
				cacheContent=controller.cacheUnitService.getCache().algo.getCacheContent();
				w.writeObject(cacheContent);
				w.flush();
				w.writeObject(controller.cacheUnitService.getCache().getNumOfSwaps());
				w.flush();

			}
			else if(action.equals("DELETE"))
			{
				controller.delete(request.getBody());
			}
		} 
		catch (IOException | ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				r.close();
				w.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}

		}
	}
}

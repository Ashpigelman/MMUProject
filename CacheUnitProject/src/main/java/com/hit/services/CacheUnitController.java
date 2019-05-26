package com.hit.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import com.hit.dm.DataModel;

public class CacheUnitController<T>
{
	 public CacheUnitService<T> cacheUnitService;
	
	public CacheUnitController()throws FileNotFoundException, ClassNotFoundException, IOException //Constructor
	{
		try 
		{
			cacheUnitService =new CacheUnitService<T>();
		} 
		catch (Exception e) 
		{
			System.out.println("Can't Define service");
			e.printStackTrace();
		}
	}
	
	public boolean update(DataModel<T>[] dataModels)
	{
		return cacheUnitService.update(dataModels);
	}
	
	public boolean delete(DataModel<T>[] dataModels)
	{
		return cacheUnitService.delete(dataModels);
	}
	
	public DataModel<T>[] get(DataModel<T>[] dataModels) throws ClassNotFoundException, IOException
	{
		return cacheUnitService.get(dataModels);
	}

}

package com.hit.services;

import java.io.IOException;
import java.io.Serializable;

import com.hit.algorithm.LRUAlgoCacheImpl;
import com.hit.dao.DaoFileImpl;
import com.hit.dao.IDao;
import com.hit.dm.DataModel;
import com.hit.memory.CacheUnit;

public class CacheUnitService<T>
{
	private IDao<Serializable,DataModel<T>> dao;
	private CacheUnit<T> cache;
	private Long[] ids;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CacheUnitService() throws Exception 
	{
		dao=new DaoFileImpl("C:\\Users\\user\\Desktop\\MMUProjectEnd\\CacheUnitProject\\src\\main\\resource\\datasource.txt");
		cache= new CacheUnit<T>(new LRUAlgoCacheImpl<Long,DataModel<T>>(3),dao);
	}

	boolean delete(DataModel<T>[] dataModels) 
	{
		for(int i=0;i<dataModels.length;i++)
		{
			dao.delete(dataModels[i]);
		}
		return true;
	}
	
	DataModel<T>[] get(DataModel<T>[] dataModels) throws ClassNotFoundException, IOException
	{
		ids=new Long[dataModels.length];
		for(int i=0;i<dataModels.length;i++)
		{
			ids[i]=dataModels[i].getDataModelId();
		}
		return cache.getDataModels(ids);
	}
	
	boolean update(DataModel<T>[] dataModels) 
	{
		for(int i=0;i<dataModels.length;i++)
		{
			dao.save(dataModels[i]);
		}
		return true;
	}
	
	public CacheUnit<T> getCache() {
		return cache;
	}


}

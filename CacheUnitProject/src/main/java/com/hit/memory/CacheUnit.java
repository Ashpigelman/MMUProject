package com.hit.memory;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import com.hit.algorithm.IAlgoCache;
import com.hit.dao.IDao;
import com.hit.dm.DataModel;

public class CacheUnit<T>
{
	public IAlgoCache<Long,DataModel<T>> algo;
	private IDao<Serializable,DataModel<T>> dao;
	public HashMap<String,String> model;
	private Integer numOfSwaps = 0;

	public CacheUnit(IAlgoCache<Long,DataModel<T>> algo, IDao<Serializable,DataModel<T>> dao)//Constructor
	{
		this.algo = algo;
		this.dao = dao;
		model=new HashMap<String, String>();
	}
	
	public int getNumOfSwaps()
	{
		return numOfSwaps;
	}
	
	@SuppressWarnings("unchecked")
	public DataModel<T>[] getDataModels(Long[] ids) throws ClassNotFoundException,IOException
	{
		DataModel<T>[] dms=new DataModel[ids.length];
		DataModel<T> tmp=new DataModel<T>(null,null);
		int d=0;
		for(int i=0;i<ids.length;i++)
		{
			tmp=algo.getElement(ids[i]);
			if(tmp!=null)
			{
				dms[d++]=tmp;
			}
			else if(dao.find(ids[i])!=null) 
			{
					algo.putElement(ids[i],dao.find(ids[i]));
					dms[d++]=algo.getElement(ids[i]);
					numOfSwaps++;
			}
		}
		return dms;
	}
}

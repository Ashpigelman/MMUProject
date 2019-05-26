package com.hit.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import com.hit.dm.DataModel;


public class DaoFileImpl<T> implements IDao<Long,DataModel<T>>
{
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private HashMap<Long,DataModel<T>> fileHmap;
	private String filePath;
	boolean empty=true;


	@SuppressWarnings("unchecked")
	public DaoFileImpl(String filePath) throws FileNotFoundException, IOException, ClassNotFoundException //Constructor
	{
		this.setFilePath(filePath);
		output=new ObjectOutputStream(new FileOutputStream(filePath,false));
		input=new ObjectInputStream(new FileInputStream(filePath));
		fileHmap=new HashMap<Long, DataModel<T>>();
		if(!empty)
		{
			fileHmap=(HashMap<Long,DataModel<T>>)input.readObject();
		}
	}
		
	@Override
	public void save(DataModel<T> entity)
	{
		fileHmap.put(entity.getDataModelId(),entity);
		try 
		{
			output.flush();
			output.close();
			output=new ObjectOutputStream(new FileOutputStream(filePath,false));
			output.writeObject(fileHmap);
			output.flush();
			empty=false;

		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	
	}

	@Override
	public void delete(DataModel<T> entity)
	{
		fileHmap.remove(entity.getDataModelId(),entity);
		try 
		{
			output.flush();
			output.close();
			output=new ObjectOutputStream(new FileOutputStream(filePath,false));
			output.writeObject(fileHmap);
			output.flush();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public DataModel<T> find(Long id)
	{
		if(fileHmap.get(id) != null)
		{
			return fileHmap.get(id);
		}
		return null;
	}
	
	//get and set
	public String getFilePath() 
	{
		return filePath;
	}

	public void setFilePath(String filePath) 
	{
		this.filePath = filePath;
	}
	
}

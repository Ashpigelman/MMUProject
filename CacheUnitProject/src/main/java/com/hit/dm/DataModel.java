package com.hit.dm;

import java.io.Serializable;

public class DataModel<T> implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private T content;

	public DataModel(java.lang.Long id,T content)//Constructor
	{
		this.setDataModelId(id);
		this.setContent(content);
	} 
	
	@Override
	public int hashCode() 
	{
		return this.id.hashCode();
	}
	
	@Override
	public boolean equals(java.lang.Object obj)
	{
		if(id.hashCode() == obj.hashCode()) 
			return true;
		return false;
	}
	
	@Override
	public String toString()
	{
		return "id:" + this.id.toString() + ",content:" + this.content.toString();
	}
	
	//set and get 
	public Long getDataModelId() {
		
		return this.id;
	}
	
	public void setDataModelId(java.lang.Long id)
	{
		this.id = id;
	}
	
	public T getContent()
	{
		return content;
	}
	
	public void setContent(T content) 
	{
		this.content = content;
	}
}

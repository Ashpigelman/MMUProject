package com.hit.algorithm;

import java.util.HashMap;
import java.util.LinkedList;

import com.hit.algorithm.IAlgoCache;

public abstract class AbstractAlgoCache<K,V> extends Object implements IAlgoCache<K,V> 
{
	private int capacity;
	public LinkedList<V> pLinked;
	HashMap<K, V> map;
	
	public AbstractAlgoCache(int capacity) 
	{
		this.setCapacity(capacity);
	}

	public int getCapacity() 
	{
		return capacity;
	}

	public void setCapacity(int capacity)
	{
		// Verify a positive capacity
		if(capacity > 0)
		{
			this.capacity = capacity;
		}
	}
	
	public abstract V getElement(K key);

	public abstract V putElement(K key, V value); 

	public abstract void removeElement(K key);
	
}

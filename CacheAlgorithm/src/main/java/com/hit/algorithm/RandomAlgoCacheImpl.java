package com.hit.algorithm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class RandomAlgoCacheImpl<K,V> extends AbstractAlgoCache<K,V> 
{
	public RandomAlgoCacheImpl(int capacity) throws Exception
	{
		super(capacity);
		pLinked=new LinkedList<V>();
		map=new HashMap<K,V>();
	}

	@Override
	public V getElement(K key)
	{
		if(map.containsKey(key))
			return map.get(key);
		return null;
	}

	@Override
	public V putElement(K key, V value) 
	{
		V replaced=value;
		int rnd=0;
		if(getCapacity()<pLinked.size()+1)
		{	
			rnd=(int) ((Math.random())%getCapacity());
			replaced=pLinked.get(rnd);
			pLinked.remove(rnd);
			
			if(!map.containsKey(key))
			{
				map.put(key, value);	
			}
			
			pLinked.addFirst(value);
		}
		else
		{
			map.put(key, value);
			pLinked.addFirst(value);
		}

		return replaced;
	}

	@Override
	public void removeElement(K key)
	{
		V last=map.get(key);
		map.remove(key);
		pLinked.remove(last);
	}

	@Override
	public String getCacheContent()
	{
		String content="";
		Iterator<V> it=pLinked.iterator();
		while(it.hasNext())
		{
			content+=it.next().toString()+"\n";
		}
		return content;
	}

}

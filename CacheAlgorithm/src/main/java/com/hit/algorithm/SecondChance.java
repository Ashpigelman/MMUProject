package com.hit.algorithm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class SecondChance<K,V> extends AbstractAlgoCache<K,V> {

	public class ChanceCheck<Ke,Val>
	{
		ChanceCheck()
		{
			c=0;
		}
		int c;
		Ke k;
		Val v;

	}

	public LinkedList<ChanceCheck<K,V>> pLinkedSec=new LinkedList<ChanceCheck<K,V>>();

	public SecondChance(int capacity) throws Exception
	{
		super(capacity);
		map=new HashMap<K,V>();
	}

	@Override
	public V getElement(K key) 
	{
		ChanceCheck<K,V> value=new ChanceCheck<K,V>();
		value.v=map.get(key);
		if(map.containsKey(key))
		{
			for(int i=0;i<getCapacity();i++) 
			{
				if(pLinkedSec.get(i).v==map.get(key))
					if(pLinkedSec.get(i).c>2)
					{
						pLinkedSec.remove(pLinkedSec.get(i));
						pLinkedSec.addLast(value);
					}
				return value.v;
			}
		}
		return null;
	}

	@Override
	public V putElement(K key, V value) 
	{
		V replaced=value;
		ChanceCheck<K,V> tmp =new ChanceCheck<K,V>();
		tmp.v=value;
		tmp.c++;
		tmp.k=key;

		if(pLinkedSec.size()+1>getCapacity()) 
		{
			if(map.containsKey(key)) 
			{
				for(int i=0;i<getCapacity();i++) 
				{
					if(pLinkedSec.get(i).v==value)
						if(pLinkedSec.get(i).c>2)
						{
							pLinkedSec.remove(pLinkedSec.get(i));
							pLinkedSec.addLast(tmp);
						}
						else pLinkedSec.get(i).c++;
				}

			}
			else
			{
				replaced=pLinkedSec.getFirst().v;
				map.remove(pLinkedSec.getFirst().k);
				pLinkedSec.removeFirst();
				pLinkedSec.addLast(tmp);
				map.put(key, value);
			}
		}
		else
		{
			pLinkedSec.addLast(tmp);
			map.put(key, value);
		}
		return replaced;
	}

	@Override
	public void removeElement(K key)
	{
		V last=map.get(key);
		map.remove(key);
		pLinkedSec.remove(last);

	}
	
	public void printList() 
	{
		System.out.print("SecondChance result\n");
		for(int i=0;i<getCapacity();i++)
		{
			System.out.print(pLinkedSec.get(i).v+" ");
		}
		System.out.print("\n");	
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

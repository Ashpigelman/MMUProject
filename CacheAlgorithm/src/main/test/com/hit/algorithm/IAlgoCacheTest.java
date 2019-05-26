package com.hit.algorithm;

import org.junit.Test;
public class IAlgoCacheTest 
{
	Integer key, value;

	@Test
	public  void testLRUAlgoCacheImpl() throws Exception //only for LRU
	{
		LRUAlgoCacheImpl<Integer,Integer> lru=new LRUAlgoCacheImpl<Integer,Integer>(3);

		lru.putElement(2,2);
		lru.putElement(3,3);
		lru.putElement(4,4);
		lru.putElement(2,2);
		lru.putElement(1,1);
		lru.putElement(3,3);
		lru.putElement(7,7);
		lru.putElement(5,5);
		lru.putElement(4,4);
		lru.putElement(3,3);
		lru.printList();
	}
	
	@Test
	public  void testMRUAlgoCacheImpl() throws Exception //only for MRU
	{
		MRUAlgoCacheImpl<Integer,Integer> mru=new MRUAlgoCacheImpl<Integer,Integer>(3);

		mru.putElement(2,2);
		mru.putElement(3,3);
		mru.putElement(4,4);
		mru.putElement(2,2);
		mru.putElement(1,1);
		mru.putElement(3,3);
		mru.putElement(7,7);
		mru.putElement(5,5);
		mru.putElement(4,4);
		mru.putElement(3,3);
		mru.printList();
		
	}
	
	@Test
	public  void testRandomAlgoCacheImpl() throws Exception //only for SecondChance
	{
		SecondChance<Integer,Integer> sc=new SecondChance<Integer,Integer>(3);
		sc.putElement(2,2);
		sc.putElement(3,3);
		sc.putElement(4,4);
		sc.putElement(2,2);
		sc.putElement(1,1);
		sc.putElement(3,3);
		sc.putElement(5,5);
		sc.putElement(1,1);
		sc.putElement(2,2);
		sc.putElement(3,3);
		sc.putElement(4,4);
		sc.putElement(5,5);
		sc.printList();
	}
	
}



package com.hit.algorithm;

public interface IAlgoCache<K, V> 
{
	public V getElement(K key);
	
	public V putElement(K key, V value);
	
	public void removeElement(K key);
		
	public String getCacheContent();

}

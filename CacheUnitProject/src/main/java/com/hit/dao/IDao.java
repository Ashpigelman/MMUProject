package com.hit.dao;

public interface IDao<ID extends java.io.Serializable,T>
{
	// Saves a given entity.
	void save(T entity);
	
	// Deletes a given entity.
	void delete(T entity) ;
	
	// Retrieves an entity by its id.
	T find(ID id) ;
}

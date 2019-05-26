package com.hit.server;

import java.io.Serializable;
import java.util.Map;

public class Request<T> implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Map<String, String> headers;
    private T body;

	public Request(Map<String,String> headers,T body)
	{
        this.headers = headers;
        this.body = body;
	}
	
	public java.util.Map<String,String> getHeaders()
	{
		 return headers;
	}
	
	public void setHeaders(Map<String,String> headers)
	{
		 this.headers = headers;
	}
		
	public T getBody()
	{
		 return body;
	}
	
	public void setBody(T body)
	{
		 this.body = body;
	}
	
	@Override
	public String toString()
	{
		return super.toString();
	}
	
}

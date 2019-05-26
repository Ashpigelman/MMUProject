package com.hit.server;

import com.hit.util.CLI;

public class CacheUnitServerDriver extends java.lang.Object
{	
	@SuppressWarnings("deprecation")
	public static void main(java.lang.String[] args) throws ClassNotFoundException
	{
			CLI cli = new CLI(System.in, System.out);
			Server server = null;
			server = new Server();
			cli.addObserver(server);
			new Thread(cli).start();
	}
}

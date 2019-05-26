package com.hit.controller;

import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecation")
public interface Controller extends Observer
{
	void update(Observable obs, Object obj);

}

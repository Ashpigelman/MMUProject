package com.hit.driver;

import java.io.IOException;

import com.hit.controller.CacheUnitController;
import com.hit.controller.Controller;
import com.hit.model.CacheUnitModel;
import com.hit.model.Model;
import com.hit.view.CacheUnitView;
import com.hit.view.View;

public class Driver 
{
	public Driver() 
	{
	}

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException 
	{
		Model model = new CacheUnitModel();
		View view = new CacheUnitView();
		Controller controller = new CacheUnitController(view, model);
		((CacheUnitModel)model).addObserver(controller);
		((CacheUnitView)view).addObserver(controller);
		view.start();
	}

}

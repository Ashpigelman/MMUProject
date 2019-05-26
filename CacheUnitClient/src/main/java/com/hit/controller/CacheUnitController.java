package com.hit.controller;

import java.util.Observable;
import com.hit.model.Model;
import com.hit.view.View;

@SuppressWarnings("deprecation")
public class CacheUnitController implements Controller
{
	View view;
	Model model;
	
	public CacheUnitController(View view,Model model)
	{
		this.model=model;
		this.view=view;
	}

	@Override
	public void update(Observable arg0, Object arg1) 
	{
		if(arg0 instanceof View)
			model.updateModelData(arg1);
		else
			if(arg0 instanceof Model)
				view.updateUIData(arg1);
	}

}

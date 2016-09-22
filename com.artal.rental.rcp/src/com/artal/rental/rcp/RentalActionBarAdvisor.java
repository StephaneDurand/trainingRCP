package com.artal.rental.rcp;

import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.jface.action.ToolBarManager;

public class RentalActionBarAdvisor extends ActionBarAdvisor {
	private IAction quitAction;
	private IAction preferencesAction;

	public RentalActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void fillMenuBar(IMenuManager menuBar) {
		// TODO Auto-generated method stub
		super.fillMenuBar(menuBar);
		
		MenuManager menuManager = new MenuManager("New MenuManager");
		menuManager.setMenuText("File");
		menuBar.add(menuManager);
		menuManager.add(quitAction);
	}
	
	@Override
	protected void fillCoolBar(ICoolBarManager coolBar) {
		// TODO Auto-generated method stub
		super.fillCoolBar(coolBar);
		
		ToolBarManager toolBarManager = new ToolBarManager();
		coolBar.add(toolBarManager);
		toolBarManager.add(preferencesAction);
	}

	@Override
	protected void makeActions(IWorkbenchWindow window) {
		// TODO Auto-generated method stub
		super.makeActions(window);
		{
			quitAction = ActionFactory.QUIT.create(window);
			quitAction.setText("Exit");
			register(quitAction);
		}
		{
			preferencesAction = ActionFactory.PREFERENCES.create(window);
			preferencesAction.setText("Preferences");
			register(preferencesAction);
		}
	}
}

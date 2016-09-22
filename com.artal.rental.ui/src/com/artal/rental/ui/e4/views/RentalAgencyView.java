package com.artal.rental.ui.e4.views;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;

import com.artal.rental.core.RentalCoreActivator;
import com.artal.rental.ui.views.RentalProvider;
import com.opcoach.training.rental.RentalAgency;

public class RentalAgencyView {

	private TreeViewer tv;

	public RentalAgencyView() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void createPartControl(Composite parent) {
		tv = new TreeViewer(parent);
		RentalProvider rt = new RentalProvider();
		tv.setContentProvider(rt);
		tv.setLabelProvider(rt);
		Collection<RentalAgency> agencies = new ArrayList<RentalAgency>();
		agencies.add(RentalCoreActivator.getAgency());
		tv.setInput(agencies);
		tv.expandAll();
		// E34 getSite().setSelectionProvider(tv);
		MenuManager menuManager = new MenuManager();
		Menu menu = menuManager.createContextMenu(tv.getControl());
		tv.getControl().setMenu(menu);
		//E34 getSite().registerContextMenu(menuManager, tv);

	}

	@Focus
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	
//E34	
//	@Override
//	public void propertyChange(PropertyChangeEvent event) {
//		tv.refresh();
//		tv.expandAll();
//		
//	}
}

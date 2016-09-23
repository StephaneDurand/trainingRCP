package com.artal.rental.ui.e4.views;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;

import com.artal.rental.core.RentalCoreActivator;
import com.artal.rental.ui.views.RentalProvider;
import com.opcoach.training.rental.RentalAgency;

public class RentalAgencyView {

	private TreeViewer tv;
	
	@Inject
	private ESelectionService selectionService;

	public RentalAgencyView() {
		// TODO Auto-generated constructor stub
	}

	@Inject
	public RentalAgencyView(Composite parent) {
		tv = new TreeViewer(parent);
		RentalProvider rt = new RentalProvider();
		tv.setContentProvider(rt);
		tv.setLabelProvider(rt);
		Collection<RentalAgency> agencies = new ArrayList<RentalAgency>();
		agencies.add(RentalCoreActivator.getAgency());
		tv.setInput(agencies);
		tv.expandAll();
		provideSelection();
		MenuManager menuManager = new MenuManager();
		Menu menu = menuManager.createContextMenu(tv.getControl());
		tv.getControl().setMenu(menu);
		//E34 getSite().registerContextMenu(menuManager, tv);

	}
	
	private void provideSelection() {
		tv.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection sel = (IStructuredSelection) event.getSelection();
				selectionService.setSelection(sel.size() == 1 ? sel.getFirstElement() : sel.toArray());
				
			}
		});
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
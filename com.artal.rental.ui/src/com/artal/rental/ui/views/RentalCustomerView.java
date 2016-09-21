package com.artal.rental.ui.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import com.artal.rental.core.RentalCoreActivator;
import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;

import org.eclipse.swt.widgets.Group;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;

public class RentalCustomerView extends ViewPart implements ISelectionListener {

	private Label lblCustomername;

	public RentalCustomerView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Group grpCustomerName = new Group(parent, SWT.NONE);
		grpCustomerName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		grpCustomerName.setText("Customer Name");
		grpCustomerName.setLayout(new GridLayout(1, false));
		
		lblCustomername = new Label(grpCustomerName, SWT.NONE);
		lblCustomername.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));


	}

	@Override
	public void setFocus() {

	}
	
	public void setCustomerName(Customer cust) {
		if (cust == null) {
			lblCustomername.setText("Information unavailable");	
		}
		else {
			lblCustomername.setText(cust.getDisplayName());
		}
	}
	
	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		getSite().getPage().addSelectionListener(this);
	}
	
	@Override
	public void dispose() {
		getSite().getPage().removeSelectionListener(this);
		super.dispose();
	}
	
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (selection.isEmpty()) {
			return;
		}
		if (selection instanceof IStructuredSelection) {
			Object selected = ((IStructuredSelection) selection).getFirstElement();
			Customer c = Platform.getAdapterManager().getAdapter(selected, Customer.class);
			setCustomerName(c);
			
		}
	}
}
	


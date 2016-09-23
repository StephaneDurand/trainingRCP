package com.artal.rental.ui.e4.views;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.services.adapter.Adapter;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import com.opcoach.training.rental.Customer;

public class RentalCustomerView {

	private Label lblCustomername;

	public RentalCustomerView() {
		// TODO Auto-generated constructor stub
	}

	@Inject
	public RentalCustomerView(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Group grpCustomerName = new Group(parent, SWT.NONE);
		grpCustomerName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		grpCustomerName.setText("Customer Name");
		grpCustomerName.setLayout(new GridLayout(1, false));
		
		lblCustomername = new Label(grpCustomerName, SWT.NONE);
		lblCustomername.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));


	}

	@Focus
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
	
	@Inject @Optional
	public void selectCustomer(@Named(IServiceConstants.ACTIVE_SELECTION)  Object o,
			Adapter adapter) {
		if (o == null) {
			setCustomerName(null);
		}
		setCustomerName(adapter.adapt(o, Customer.class));
	}
	
	@Inject @Optional
	public void selectCustomers(@Named(IServiceConstants.ACTIVE_SELECTION) Object[] selection) {
		if (selection == null) {
			setCustomerName(null);
			return;
		}
		for (Object o : selection) {
			if (o instanceof Customer) {
				setCustomerName((Customer) o);
			}
		}
		
	}

	
}
	


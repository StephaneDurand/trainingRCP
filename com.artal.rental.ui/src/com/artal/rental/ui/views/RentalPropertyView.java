package com.artal.rental.ui.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

import com.artal.rental.core.RentalCoreActivator;
import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;

public class RentalPropertyView extends ViewPart {

	private Label rentedObjectLabel;
	
	private Label customerNameLabel;
	
	

	public RentalPropertyView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Group infoGroup =new Group(parent, SWT.NONE);
		infoGroup.setText("Informations");
		infoGroup.setLayout(new GridLayout(2, false));
		
		rentedObjectLabel = new Label(infoGroup, SWT.BORDER);
		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		gd.horizontalAlignment = SWT.FILL;
		rentedObjectLabel.setLayoutData(gd);		
				
		customerNameLabel = new Label(infoGroup, SWT.BORDER);
		GridData gd1 = new GridData();
		gd1.horizontalSpan = 2;
		gd1.horizontalAlignment = SWT.FILL;
		rentedObjectLabel.setLayoutData(gd);	
		
		setRental(RentalCoreActivator.getAgency().getRentals().get(0));

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	
	public void setRental(Rental rental) {
		rentedObjectLabel.setText(rental.getRentedObject().getName());
		customerNameLabel.setText("Lou� �: " + rental.getCustomer().getDisplayName());
	}
	


}

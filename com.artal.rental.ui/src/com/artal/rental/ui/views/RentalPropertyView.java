package com.artal.rental.ui.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

import com.artal.rental.core.RentalCoreActivator;
import com.opcoach.training.rental.Rental;

public class RentalPropertyView extends ViewPart {

	private Label rentedObjectLabel;
	
	private Label customerNameLabel;
	private Group dateGroup;
	private Label debutLabel;
	private Label finLabel;
	private Label loueLabel;
	private Label lblDu;
	private Label lblAu;
	
	

	public RentalPropertyView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Group infoGroup =new Group(parent, SWT.NONE);
		infoGroup.setText("Informations");
		infoGroup.setLayout(new GridLayout(2, false));
		
		rentedObjectLabel = new Label(infoGroup, SWT.NONE);
		GridData gd = new GridData();
		gd.grabExcessVerticalSpace = true;
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalSpan = 2;
		gd.horizontalAlignment = SWT.FILL;
		rentedObjectLabel.setLayoutData(gd);		
		GridData gd1 = new GridData();
		gd1.horizontalSpan = 2;
		gd1.horizontalAlignment = SWT.FILL;
		rentedObjectLabel.setLayoutData(gd);	
		
		loueLabel = new Label(infoGroup, SWT.NONE);
		loueLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1));
		loueLabel.setText("Lou\u00E9 \u00E0: ");
		
		customerNameLabel = new Label(infoGroup, SWT.NONE);
		customerNameLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1));
		
		dateGroup = new Group(parent, SWT.NONE);
		dateGroup.setText("Dates de location");
		dateGroup.setLayout(new GridLayout(2, false));
		
		lblDu = new Label(dateGroup, SWT.NONE);
		lblDu.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1));
		lblDu.setText("du: ");
		
		debutLabel = new Label(dateGroup, SWT.NONE);
		debutLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1));
		
		lblAu = new Label(dateGroup, SWT.NONE);
		lblAu.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1));
		lblAu.setText("au: ");
				
		finLabel = new Label(dateGroup, SWT.NONE);
		finLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1));
				
		setRental(RentalCoreActivator.getAgency().getRentals().get(0));

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	
	public void setRental(Rental rental) {
		rentedObjectLabel.setText(rental.getRentedObject().getName());
		customerNameLabel.setText(rental.getCustomer().getDisplayName());
		debutLabel.setText(rental.getStartDate().toString());
		finLabel.setText(rental.getEndDate().toString());
	}
}

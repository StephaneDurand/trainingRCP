package com.artal.rental.ui.views;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import com.artal.rental.core.RentalCoreActivator;
import com.opcoach.training.rental.Rental;

public class RentalPropertyView extends ViewPart implements ISelectionListener {

	private Label rentedObjectLabel;
	
	private Label customerNameLabel;
	private Group dateGroup;
	private Label debutLabel;
	private Label finLabel;
	private Label loueLabel;
	private Label lblDu;
	private Label lblAu;
	private Composite dateComposite;
	private Composite infoComposite;
	
	

	public RentalPropertyView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Group infoGroup =new Group(parent, SWT.NONE);
		infoGroup.setText("Informations");
		infoGroup.setLayout(new GridLayout(1, false));
		GridData gd1 = new GridData();
		gd1.horizontalSpan = 2;
		gd1.horizontalAlignment = SWT.FILL;
		
		infoComposite = new Composite(infoGroup, SWT.BORDER);
		infoComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		infoComposite.setLayout(new GridLayout(2, false));
		
		rentedObjectLabel = new Label(infoComposite, SWT.NONE);
		rentedObjectLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		
		loueLabel = new Label(infoComposite, SWT.NONE);
		loueLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		loueLabel.setText("Lou\u00E9 \u00E0: ");
		
		customerNameLabel = new Label(infoComposite, SWT.NONE);
		customerNameLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		dateGroup = new Group(parent, SWT.NONE);
		dateGroup.setText("Dates de location");
		dateGroup.setLayout(new GridLayout(1, false));
		
		dateComposite = new Composite(dateGroup, SWT.BORDER);
		dateComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		dateComposite.setLayout(new GridLayout(2, false));
		
		lblDu = new Label(dateComposite, SWT.NONE);
		lblDu.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblDu.setText("du: ");
		
		debutLabel = new Label(dateComposite, SWT.NONE);
		debutLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		lblAu = new Label(dateComposite, SWT.NONE);
		lblAu.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblAu.setText("au: ");
		
		finLabel = new Label(dateComposite, SWT.NONE);
		finLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
				
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
		if (selection instanceof IStructuredSelection) {
			Object selected = ((IStructuredSelection) selection).getFirstElement();
			if (selected instanceof Rental) {
				setRental((Rental) selected);
			}
		}
	}
}

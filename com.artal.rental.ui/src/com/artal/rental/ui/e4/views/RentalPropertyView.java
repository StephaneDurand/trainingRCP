package com.artal.rental.ui.e4.views;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;


public class RentalPropertyView {

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
	
	@Inject
	private RentalAgency agency;
	
	

	public RentalPropertyView() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void createPartControl(Composite parent, RentalAgency agency) {
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
		debutLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblAu = new Label(dateComposite, SWT.NONE);
		lblAu.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblAu.setText("au: ");
		
		finLabel = new Label(dateComposite, SWT.NONE);
		finLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		setLabelAsDragSource(rentedObjectLabel);
		setLabelAsDragSource(debutLabel);
		setLabelAsDragSource(finLabel);
		setLabelAsDragSource(customerNameLabel);
		
		setRental(agency.getRentals().get(0));

	}

	@Focus
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	
	public void setRental(Rental rental) {
		rentedObjectLabel.setText(rental.getRentedObject().getName());
		customerNameLabel.setText(rental.getCustomer().getDisplayName());
		debutLabel.setText(rental.getStartDate().toString());
		finLabel.setText(rental.getEndDate().toString());
	}
	
//	@Override
//	public void init(IViewSite site) throws PartInitException {
//		super.init(site);
//		getSite().getPage().addSelectionListener(this);
//	}
//	
//	@Override
//	public void dispose() {
//		getSite().getPage().removeSelectionListener(this);
//		super.dispose();
//	}
	@Inject @Optional
	public void receiveSelection(@Named(IServiceConstants.ACTIVE_SELECTION) Rental r) {
		if (r == null) {
			return;
		}
		setRental(r);
	}
	
	
	public void setLabelAsDragSource(final Label label) {
		DragSource source = new DragSource(label, DND.DROP_MOVE | DND.DROP_COPY);
		
		source.setTransfer(new Transfer[] { TextTransfer.getInstance() });
		
		source.addDragListener(new DragSourceAdapter() {
			public void dragSetData(DragSourceEvent event) {
				if (TextTransfer.getInstance().isSupportedType(event.dataType)) {
					event.data = label.getText();
				}
			}
		});
	}
}

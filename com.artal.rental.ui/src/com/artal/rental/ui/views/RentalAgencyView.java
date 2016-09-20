package com.artal.rental.ui.views;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.artal.rental.core.RentalCoreActivator;
import com.opcoach.training.rental.RentalAgency;

public class RentalAgencyView extends ViewPart {

	public RentalAgencyView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		TreeViewer tv = new TreeViewer(parent);
		RentalProvider rt = new RentalProvider();
		tv.setContentProvider(rt);
		tv.setLabelProvider(rt);
		Collection<RentalAgency> agencies = new ArrayList<RentalAgency>();
		agencies.add(RentalCoreActivator.getAgency());
		tv.setInput(agencies);
		tv.expandAll();

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}

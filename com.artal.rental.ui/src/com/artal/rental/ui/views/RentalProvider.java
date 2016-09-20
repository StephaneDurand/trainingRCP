package com.artal.rental.ui.views;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import com.artal.rental.ui.RentalUIActivator;
import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;

public class RentalProvider extends LabelProvider implements ITreeContentProvider, IColorProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof Collection<?>) {
			return ((Collection<?>) inputElement).toArray();
		}
		return null;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof RentalAgency) {
			//return ((RentalAgency) parentElement).getCustomers().toArray();
			ArrayList<Node> nodes = new ArrayList<>();
			nodes.add(new Node(Node.CUSTOMER, (RentalAgency) parentElement));
			nodes.add(new Node(Node.LOCATION, (RentalAgency) parentElement));
			nodes.add(new Node(Node.RENTAL_OBJS, (RentalAgency) parentElement));
			return nodes.toArray();
		}
		else if (parentElement instanceof Node){
			return ((Node) parentElement).getChildren();
		}
		return null;
	}

	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		return (element instanceof RentalAgency || element instanceof Node);
	}
	
	@Override
	public String getText(Object element) {
		if (element instanceof RentalAgency) {
			return ((RentalAgency) element).getName();
		}
		if (element instanceof Node) {
			return ((Node) element).toString();
		}
		if (element instanceof RentalObject) {
			return ((RentalObject) element).getName();
		}
		if (element instanceof Customer) {
			return ((Customer) element).getDisplayName();
		}
		return super.getText(element);
	}

	private class Node {
		
		public Node(String label, RentalAgency agency) {
			super();
			this.label = label;
			this.agency = agency;
		}

		public static final String CUSTOMER = "Customers";
		public static final String LOCATION = "Locations";
		public static final String RENTAL_OBJS = "Objets à louer";
		
		private String label;
		
		private RentalAgency agency;

	
		public Object[] getChildren() {
			if (label.equalsIgnoreCase(CUSTOMER)) {
				return agency.getCustomers().toArray();			
			}
			if (label.equalsIgnoreCase(LOCATION)) {
				return agency.getRentals().toArray();
			}
			if (label.equalsIgnoreCase(RENTAL_OBJS)) {
				return agency.getObjectsToRent().toArray();
			}
			return null;
		}
		
		@Override
		public String toString() {
			return label;
		}
		
	}

	@Override
	public Color getForeground(Object element) {
		if (element instanceof Customer) {
			return Display.getCurrent().getSystemColor(SWT.COLOR_BLUE);
		}
		if (element instanceof RentalAgency) {
			return Display.getCurrent().getSystemColor(SWT.COLOR_RED);
		}
		if (element instanceof Node) {
			return Display.getCurrent().getSystemColor(SWT.COLOR_DARK_YELLOW);
		}
		if (element instanceof RentalObject) {
			return Display.getCurrent().getSystemColor(SWT.COLOR_GREEN);
		}
		if (element instanceof Rental) {
			return Display.getCurrent().getSystemColor(SWT.COLOR_DARK_MAGENTA);
		}
		return null;
	}

	@Override
	public Color getBackground(Object element) {
		return Display.getCurrent().getSystemColor(SWT.COLOR_WHITE);
	}
	
	@Override
	public Image getImage(Object element) {
		if (element instanceof Customer) {
			return RentalUIActivator.getDefault().getImageRegistry().get(RentalUIActivator.IMG_CUSTOMER);
		}
		if (element instanceof RentalAgency) {
			return RentalUIActivator.getDefault().getImageRegistry().get(RentalUIActivator.IMG_AGENCY);
		}
		if (element instanceof RentalObject) {
			return RentalUIActivator.getDefault().getImageRegistry().get(RentalUIActivator.IMG_OBJECT);
		}
		if (element instanceof Rental) {
			return RentalUIActivator.getDefault().getImageRegistry().get(RentalUIActivator.IMG_RENTAL);
		}
		if (element instanceof Node) {
			if (((Node) element).toString().equalsIgnoreCase(Node.CUSTOMER)) {
				return RentalUIActivator.getDefault().getImageRegistry().get(RentalUIActivator.IMG_CUSTOMER);
			}
			if (((Node) element).toString().equalsIgnoreCase(Node.LOCATION)) {
				return RentalUIActivator.getDefault().getImageRegistry().get(RentalUIActivator.IMG_RENTAL);
			}
			if (((Node) element).toString().equalsIgnoreCase(Node.RENTAL_OBJS)) {
				return RentalUIActivator.getDefault().getImageRegistry().get(RentalUIActivator.IMG_OBJECT);
			}
		}
		return null;
	}
	
}

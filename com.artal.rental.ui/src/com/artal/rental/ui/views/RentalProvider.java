package com.artal.rental.ui.views;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;

public class RentalProvider extends LabelProvider implements ITreeContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		Object[] result = null;
		if (inputElement instanceof Collection<?>) {
			return ((Collection) inputElement).toArray();
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
			nodes.add(new Node(Node.RENTAL_OBBS, (RentalAgency) parentElement));
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
		public static final String RENTAL_OBBS = "Objets à louer";
		
		private String label;
		
		private RentalAgency agency;

	
		public Object[] getChildren() {
			if (label.equalsIgnoreCase(CUSTOMER)) {
				return agency.getCustomers().toArray();
			
			}
			if (label.equalsIgnoreCase(LOCATION)) {
				return agency.getRentals().toArray();
			}
			if (label.equalsIgnoreCase(RENTAL_OBBS)) {
				return agency.getObjectsToRent().toArray();
			}
			return null;
		}
		
		@Override
		public String toString() {
			return label;
		}
		
	}
}

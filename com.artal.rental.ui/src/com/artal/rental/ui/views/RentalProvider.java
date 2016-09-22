package com.artal.rental.ui.views;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.preference.PreferenceStore;
import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import com.artal.rental.ui.PaletteDesc;
import com.artal.rental.ui.RentalUIActivator;
import com.artal.rental.ui.pref.RentalColorPreferences;
import com.artal.rental.ui.pref.RentalPreferences;
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
		String display = super.getText(element);
		if (element instanceof RentalAgency) {
			display = ((RentalAgency) element).getName();
		}
		if (element instanceof Node) {
			display = ((Node) element).toString();
			if (RentalUIActivator.getDefault().getPreferenceStore().getBoolean(RentalPreferences.DISPLAY_NUMBER)) {
				display += " (" + ((Node) element).getChildren().length + ")";
			}
			
		}
		if (element instanceof RentalObject) {
			display = ((RentalObject) element).getName();
		}
		if (element instanceof Customer) {
			display = ((Customer) element).getDisplayName();
		}
		return display;
	}

	public class Node {

		public static final String CUSTOMER = "Customers";
		public static final String LOCATION = "Locations";
		public static final String RENTAL_OBJS = "Objets à louer";
		
		private String label;
		
		private RentalAgency agency;
		
		public Node(String label, RentalAgency agency) {
			super();
			this.label = label;
			this.agency = agency;
		}


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

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((agency == null) ? 0 : agency.hashCode());
			result = prime * result + ((label == null) ? 0 : label.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (agency == null) {
				if (other.agency != null)
					return false;
			} else if (!agency.equals(other.agency))
				return false;
			if (label == null) {
				if (other.label != null)
					return false;
			} else if (!label.equals(other.label))
				return false;
			return true;
		}

		private RentalProvider getOuterType() {
			return RentalProvider.this;
		}
		
		
		
	}

	@Override
	public Color getForeground(Object element) {
		String palId = RentalUIActivator.getDefault().getPreferenceStore().getString("PREF_PALETTE");
		PaletteDesc pDesc = RentalUIActivator.getDefault().getPalettesMap().get(palId);
		return pDesc.getProvider().getForeground(element);
//		if (element instanceof Customer) {
//			return getAColor(RentalUIActivator.getDefault().getPreferenceStore().getString(RentalColorPreferences.P_CUST_COLOR));
//		}
//		if (element instanceof RentalAgency) {
//			return getAColor(RentalUIActivator.getDefault().getPreferenceStore().getString(RentalColorPreferences.P_AGENCY_COLOR));
//		}
//		if (element instanceof Node) {
//			return Display.getCurrent().getSystemColor(SWT.COLOR_DARK_YELLOW);
//		}
//		if (element instanceof RentalObject) {
//			return getAColor(RentalUIActivator.getDefault().getPreferenceStore().getString(RentalColorPreferences.P_OBJECT_COLOR));
//		}
//		if (element instanceof Rental) {
//			return getAColor(RentalUIActivator.getDefault().getPreferenceStore().getString(RentalColorPreferences.P_RENTAL_COLOR));
//		}
//		return null;
	}

	@Override
	public Color getBackground(Object element) {
		String palId = RentalUIActivator.getDefault().getPreferenceStore().getString("PREF_PALETTE");
		PaletteDesc pDesc = RentalUIActivator.getDefault().getPalettesMap().get(palId);
		return pDesc.getProvider().getBackground(element);
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
	
	private Color getAColor(String rgbKey) {
		ColorRegistry colorRegistry = JFaceResources.getColorRegistry();
		
		Color col = colorRegistry.get(rgbKey);
		if (col == null) {
			colorRegistry.put(rgbKey, StringConverter.asRGB(rgbKey));
			col = colorRegistry.get(rgbKey);
		}
		return col;
	}
	
	
	
}

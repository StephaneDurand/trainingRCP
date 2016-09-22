package com.artal.rental.ui.palettes;

import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import com.artal.rental.ui.RentalUIActivator;
import com.artal.rental.ui.pref.RentalColorPreferences;
import com.artal.rental.ui.views.RentalProvider.Node;
import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;

public class DefaultPalette implements IColorProvider {

	public DefaultPalette() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Color getForeground(Object element) {
		if (element instanceof Customer) {
			return getAColor(RentalUIActivator.getDefault().getPreferenceStore().getString(RentalColorPreferences.P_CUST_COLOR));
		}
		if (element instanceof RentalAgency) {
			return getAColor(RentalUIActivator.getDefault().getPreferenceStore().getString(RentalColorPreferences.P_AGENCY_COLOR));
		}
		if (element instanceof Node) {
			return Display.getCurrent().getSystemColor(SWT.COLOR_DARK_YELLOW);
		}
		if (element instanceof RentalObject) {
			return getAColor(RentalUIActivator.getDefault().getPreferenceStore().getString(RentalColorPreferences.P_OBJECT_COLOR));
		}
		if (element instanceof Rental) {
			return getAColor(RentalUIActivator.getDefault().getPreferenceStore().getString(RentalColorPreferences.P_RENTAL_COLOR));
		}
		return null;
	}

	@Override
	public Color getBackground(Object element) {
		return Display.getCurrent().getSystemColor(SWT.COLOR_WHITE);
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
